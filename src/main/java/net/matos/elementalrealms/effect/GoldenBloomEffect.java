package net.matos.elementalrealms.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;

public class GoldenBloomEffect extends MobEffect {

    public GoldenBloomEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        // Regeneration effect
        if (entity.getHealth() < entity.getMaxHealth()) {
            float regenAmount = 0.5F + amplifier * 0.2F; // Regenerate more at higher levels
            entity.heal(regenAmount);
        }

        // Hunger/saturation boost every 5 seconds
        if (entity.tickCount % 100 == 0 && entity instanceof Player player) {
            if (player.getFoodData().needsFood()) {
                player.getFoodData().eat(1, 0.3F);
            }
        }

        // Debug: Check what block the player is stepping on
        debugBlockUnderfoot(entity);

        return true;
    }

    // Function to debug what block the player is stepping on
    private void debugBlockUnderfoot(LivingEntity entity) {
        if (entity.getCommandSenderWorld().isClientSide) return; // Don't apply on the client side, only on the server side

        BlockPos entityPos = entity.blockPosition();
        Level world = entity.getCommandSenderWorld(); // Correct way to get the level
        BlockState blockState = world.getBlockState(entityPos.below()); // Check block below the player

        // Print the block's name/type to the console for debugging
        System.out.println("Player is stepping on: " + blockState.getBlock().getName().getString());
    }
}
