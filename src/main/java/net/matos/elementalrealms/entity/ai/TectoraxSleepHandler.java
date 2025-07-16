package net.matos.elementalrealms.entity.ai;

import net.matos.elementalrealms.entity.custom.TectoraxEntity;

public class TectoraxSleepHandler {

    public static void handle(TectoraxEntity tectorax) {
        boolean downstate = tectorax.isDownstate();
        int tickCount = tectorax.tickCount;

        // === KO / SLEEP / WAKE LOGIC ===
        if (!tectorax.isTame()) {
            if (downstate) {
                if (tectorax.getHealth() < 15.0F) {
                    if (tectorax.knockOutStartTick == -1) {
                        tectorax.knockOutAnimationState.start(tickCount);
                        tectorax.knockOutStartTick = tickCount;
                        tectorax.sleepAnimationState.stop();
                        tectorax.wakeUpAnimationState.stop();
                    } else {
                        long elapsedTicks = tickCount - tectorax.knockOutStartTick;
                        int knockOutDurationTicks = 19;
                        if (elapsedTicks >= knockOutDurationTicks) {
                            tectorax.knockOutAnimationState.stop();
                            tectorax.sleepAnimationState.startIfStopped(tickCount);
                            tectorax.wakeUpAnimationState.stop();
                        } else {
                            tectorax.sleepAnimationState.stop();
                            tectorax.wakeUpAnimationState.stop();
                        }
                    }
                } else {
                    tectorax.knockOutAnimationState.stop();
                    tectorax.sleepAnimationState.stop();
                    tectorax.wakeUpAnimationState.startIfStopped(tickCount);
                    tectorax.knockOutStartTick = -1;
                }
            } else {
                tectorax.knockOutAnimationState.stop();
                tectorax.sleepAnimationState.stop();
                tectorax.wakeUpAnimationState.stop();
                tectorax.knockOutStartTick = -1;
            }
        }

        // === WAKE-UP TRIGGER ON DOWNSTATE EXIT ===
        if (tectorax.wasInDownstate && !downstate) {
            System.out.println("[ANIM DEBUG] Starting wake-up animation after downstate exit");
            tectorax.wakeUpAnimationState.start(tickCount);
            tectorax.knockOutAnimationState.stop();
            tectorax.sleepAnimationState.stop();
            tectorax.knockOutStartTick = -1;
            tectorax.wakeUpStartTick = tickCount;
        }
        tectorax.wasInDownstate = downstate;

        // === WAKE-UP END HANDLER ===
        if (tectorax.wakeUpStartTick != -1) {
            long elapsed = tickCount - tectorax.wakeUpStartTick;
            if (elapsed >= 27) {
                tectorax.wakeUpAnimationState.stop();
                tectorax.wakeUpStartTick = -1;
            }
        }
    }
}