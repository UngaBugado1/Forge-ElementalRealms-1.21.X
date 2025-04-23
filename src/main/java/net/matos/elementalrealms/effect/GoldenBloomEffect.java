package net.matos.elementalrealms.effect;

import net.matos.elementalrealms.util.ModTags;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoldenBloomEffect extends MobEffect {

    private static final Logger LOGGER = LoggerFactory.getLogger("ElementalRealms");

    // New ResourceLocation ID for the attribute modifier
    public static final ResourceLocation GOLDEN_BLOOM_SPEED_BOOST_ID =
            ResourceLocation.fromNamespaceAndPath("elementalrealms", "golden_bloom_speed_boost");

    // AttributeModifier using ResourceLocation (new 1.21+ constructor)
    public static final AttributeModifier GOLDEN_BLOOM_SPEED_BOOST =
            new AttributeModifier(GOLDEN_BLOOM_SPEED_BOOST_ID, 0.2D, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

    public GoldenBloomEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        // Regeneration effect
        if (entity.getHealth() < entity.getMaxHealth()) {
            float regenAmount = 0.5F + amplifier * 0.2F;
            entity.heal(regenAmount);
        }

        // Saturation every 5 seconds
        if (entity.tickCount % 100 == 0 && entity instanceof Player player) {
            if (player.getFoodData().needsFood()) {
                player.getFoodData().eat(1, 0.3F);
            }
        }

        // Apply the speed boost when standing on FUNGUS_BLOCKS
        applyFungusBoost(entity);

        return true;
    }

    private void applyFungusBoost(LivingEntity entity) {
        if (entity.level().isClientSide) return;

        Level level = entity.level();
        BlockPos blockPos = entity.blockPosition().below();
        BlockState blockState = level.getBlockState(blockPos);
        var attributes = entity.getAttribute(Attributes.MOVEMENT_SPEED);

        boolean hasEffect = entity.hasEffect(ModEffects.GOLDEN_BLOOM_EFFECT.getHolder().get());

        if (blockState.is(ModTags.Blocks.FUNGUS_BLOCKS) && hasEffect) {
            if (attributes != null && !attributes.hasModifier(GOLDEN_BLOOM_SPEED_BOOST_ID)) {
                attributes.addTransientModifier(GOLDEN_BLOOM_SPEED_BOOST);
            }
        } else {
            if (attributes != null && attributes.hasModifier(GOLDEN_BLOOM_SPEED_BOOST_ID)) {
                attributes.removeModifier(GOLDEN_BLOOM_SPEED_BOOST_ID);
            }
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean isInstantenous() {
        return false;
    }

    @Override
    public void removeAttributeModifiers(AttributeMap pAttributeMap) {
        super.removeAttributeModifiers(pAttributeMap);

        var attribute = pAttributeMap.getInstance(Attributes.MOVEMENT_SPEED);
        if (attribute != null && attribute.hasModifier(GOLDEN_BLOOM_SPEED_BOOST_ID)) {
            attribute.removeModifier(GOLDEN_BLOOM_SPEED_BOOST);
            LOGGER.info("Golden Bloom: speed modifier removed on effect end");
        }
    }
}
