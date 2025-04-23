package net.matos.elementalrealms.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.matos.elementalrealms.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record FungusWalkEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<FungusWalkEnchantmentEffect> CODEC = MapCodec.unit(FungusWalkEnchantmentEffect::new);

    private static final ResourceLocation FUNGUS_WALK_SPEED_ID =
            ResourceLocation.fromNamespaceAndPath("elementalrealms", "fungus_walk_speed_boost");

    @Override
    public void apply(ServerLevel level, int pEnchantmentLevel, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
        if (!(entity instanceof LivingEntity living)) return;

        BlockPos blockBelow = entity.blockPosition().below();
        boolean onFungus = level.getBlockState(blockBelow).is(ModTags.Blocks.FUNGUS_BLOCKS);

        var attribute = living.getAttribute(Attributes.MOVEMENT_SPEED);
        if (attribute == null) return;

        double boostAmount = switch (pEnchantmentLevel) {
            case 1 -> 0.28D;
            case 2 -> 0.45D;
            default -> 0.0D; // Optional: fallback
        };

        if (onFungus && boostAmount > 0) {
            // Replace or apply modifier manually
            if (attribute.hasModifier(FUNGUS_WALK_SPEED_ID)) {
                var current = attribute.getModifier(FUNGUS_WALK_SPEED_ID);
                if (current != null && current.amount() != boostAmount) {
                    attribute.removeModifier(FUNGUS_WALK_SPEED_ID);
                } else {
                    return; // Already applied correctly
                }
            }

            attribute.addTransientModifier(new AttributeModifier(
                    FUNGUS_WALK_SPEED_ID,
                    boostAmount,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            ));
        } else {
            // Not on fungus or level too low: remove boost
            if (attribute.hasModifier(FUNGUS_WALK_SPEED_ID)) {
                attribute.removeModifier(FUNGUS_WALK_SPEED_ID);
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
