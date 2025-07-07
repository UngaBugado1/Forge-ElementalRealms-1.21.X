package net.matos.elementalrealms.entity.ai;

import net.matos.elementalrealms.entity.custom.TectoraxEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class TectoraxAttackGoal extends MeleeAttackGoal {
    private final TectoraxEntity tectorax;
    private final double attackReach;
    private final double customSpeedModifier;

    private int attackDelay = 10;
    private int ticksUntilNextAttack = 30;
    private boolean shouldCountTillNextAttack = true;

    public TectoraxAttackGoal(TectoraxEntity tectorax, double speedModifier, boolean followTargetEvenIfNotSeen) {
        this(tectorax, speedModifier, followTargetEvenIfNotSeen, 3.0D);
    }

    public TectoraxAttackGoal(TectoraxEntity tectorax, double speedModifier, boolean followTargetEvenIfNotSeen, double attackReach) {
        super(tectorax, speedModifier, followTargetEvenIfNotSeen);
        this.tectorax = tectorax;
        this.attackReach = attackReach;
        this.customSpeedModifier = speedModifier; // Store your own copy
    }

    @Override
    public void start() {
        super.start();
        attackDelay = 10;
        ticksUntilNextAttack = 30;
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity enemy) {
        if (tectorax.canAttack(enemy) && isEnemyWithinAttackDistance(enemy)) {
            shouldCountTillNextAttack = true;

            if (isTimeToStartAttackAnimation()) {
                tectorax.setAttacking(true); // This starts the animation!
            }

            if (isTimeToAttack()) {
                this.mob.getLookControl().setLookAt(enemy.getX(), enemy.getEyeY(), enemy.getZ());
                performAttack(enemy);
            }
        } else {
            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            tectorax.setAttacking(false);
            tectorax.attackAnimationTimeout = 0;
        }
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity enemy) {
        return this.tectorax.distanceTo(enemy) <= this.attackReach;
    }

    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay * 2);
    }

    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    protected boolean isTimeToStartAttackAnimation() {
        return this.ticksUntilNextAttack <= attackDelay;
    }

    protected void performAttack(LivingEntity enemy) {
        this.resetAttackCooldown();
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.doHurtTarget(enemy);
    }

    @Override
    public void tick() {
        super.tick();

        if (shouldCountTillNextAttack) {
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
        }

        LivingEntity target = this.mob.getTarget();
        if (target != null) {
            double distance = this.mob.distanceTo(target);
            if (distance > this.attackReach - 0.25) {
                // Move toward target if too far
                this.mob.getNavigation().moveTo(target, this.customSpeedModifier);
            } else {
                // Stop moving when close enough to attack
                this.mob.getNavigation().stop();
            }
        }
    }


    @Override
    public void stop() {
        tectorax.setAttacking(false);
        super.stop();
    }
}

