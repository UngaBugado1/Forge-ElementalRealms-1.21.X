package net.matos.elementalrealms.item.custom;

import net.matos.elementalrealms.item.client.armor.SeismicArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public final class SeismicArmorItem extends ArmorItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public SeismicArmorItem(Holder<ArmorMaterial> armorMaterial, Type type, Properties properties) {
        super(armorMaterial, type, properties);
    }

    private static final Map<UUID, Integer> crouchTicks = new HashMap<>();

    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        if (!level.isClientSide && player.getItemBySlot(EquipmentSlot.HEAD).equals(stack)) {
            UUID playerId = player.getUUID();

            if (player.isCrouching()) {
                int ticks = crouchTicks.getOrDefault(playerId, 0) + 1;
                crouchTicks.put(playerId, ticks);

                if (ticks >= 60) {
                    // After 3 seconds of crouching
                    MobEffectInstance slowness = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2, 4, false, true, true);
                    MobEffectInstance resistance = new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2, 3, false, true, true);
                    MobEffectInstance currentSlowness = player.getEffect(MobEffects.MOVEMENT_SLOWDOWN);
                    MobEffectInstance currentResistance = player.getEffect(MobEffects.DAMAGE_RESISTANCE);

                    if (currentSlowness == null || currentSlowness.getAmplifier() < slowness.getAmplifier()) {
                        player.addEffect(slowness);
                    }

                    if (currentResistance == null || currentResistance.getAmplifier() < resistance.getAmplifier()) {
                        player.addEffect(resistance);
                    }
                }
            } else {
                crouchTicks.remove(playerId); // Reset when not crouching
            }
        }
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public <T extends LivingEntity> HumanoidModel<?> getGeoArmorRenderer(@Nullable T livingEntity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot, @Nullable HumanoidModel<T> original) {
                if (this.renderer == null)
                    this.renderer = new SeismicArmorRenderer();

                return this.renderer;
            }
        });
    }

    private PlayState predicate(AnimationState animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}