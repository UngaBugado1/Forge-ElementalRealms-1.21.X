package net.matos.elementalrealms.item.custom;

import net.matos.elementalrealms.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class    ModFoodProperties {
    public static final FoodProperties EMBEROOT = new FoodProperties.Builder().nutrition(3).saturationModifier(0.25f)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 120), 0.80f)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60), 0.20f).alwaysEdible().build();
}
