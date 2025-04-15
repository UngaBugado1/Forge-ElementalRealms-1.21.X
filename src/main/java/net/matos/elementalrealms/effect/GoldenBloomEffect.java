package net.matos.elementalrealms.effect;

import net.matos.elementalrealms.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoldenBloomEffect extends MobEffect {

    private static final Logger LOGGER = LoggerFactory.getLogger("ElementalRealms");

    public GoldenBloomEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        // Regeneration
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

        // Speed boost if standing on fungus block
        applyFungusBoost(entity, amplifier);

        return true;
    }

    private void applyFungusBoost(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide) return;

        Level level = entity.level();
        BlockPos blockPos = entity.blockPosition().below();
        BlockState blockState = level.getBlockState(blockPos);

        if (blockState.is(ModTags.Blocks.FUNGUS_BLOCKS)) {
            // Speed Boost
            double baseSpeed = 0.1D + (0.05D * amplifier);
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(1.0D + baseSpeed, 1.0D, 1.0D + baseSpeed));

            // Cosmetic particles
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                        ParticleTypes.HAPPY_VILLAGER,
                        entity.getX(), entity.getY() + 1, entity.getZ(),
                        2, 0.2, 0.2, 0.2, 0.01
                );
            }

            LOGGER.info("Golden Bloom effect: speed boosted on {}", blockState.getBlock().getName().getString());
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
