package net.matos.elementalrealms.entity.ai;

import net.matos.elementalrealms.block.ModBlocks;
import net.matos.elementalrealms.entity.custom.TectoraxEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.BreedGoal;

public class TectoraxBreedGoal extends BreedGoal {

    private final TectoraxEntity tectorax;
    private boolean eggSpawned = false; // Flag to prevent multiple eggs

    public TectoraxBreedGoal(TectoraxEntity tectorax, double speed) {
        super(tectorax, speed);
        this.tectorax = tectorax;
    }

    @Override
    public boolean canUse() {
        // Only allow breeding if tamed AND the normal BreedGoal conditions pass
        return this.tectorax.isTame() && super.canUse();
    }

    @Override
    public void start() {
        super.start();
        eggSpawned = false; // Reset flag when breeding starts
    }

    @Override
    protected void breed() {
        if (!this.animal.level().isClientSide && !eggSpawned) {
            BlockPos eggPos = this.animal.blockPosition();

            // Place the egg block on the ground where the animal is
            this.animal.level().setBlock(eggPos, ModBlocks.TECTORAX_EGG.get().defaultBlockState(), 3);

            // Mark egg as spawned so it does not spawn again this cycle
            eggSpawned = true;

            // Reset breeding state so animal can breed again later
            this.animal.setInLoveTime(0);
            this.animal.resetLove();

            // Play breeding particles or sound if you want
            this.animal.level().broadcastEntityEvent(this.animal, (byte)18);
        }
    }
}
