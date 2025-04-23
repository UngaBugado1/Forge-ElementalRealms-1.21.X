package net.matos.elementalrealms.effect;

import net.matos.elementalrealms.ElementalRealms;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ElementalRealms.MOD_ID);

    public static final RegistryObject<MobEffect> GOLDEN_BLOOM_EFFECT =
            MOB_EFFECTS.register("golden_bloom", () -> new GoldenBloomEffect(MobEffectCategory.BENEFICIAL, 0xffbd2a));






    public static void register(IEventBus eventBus)
    {
        MOB_EFFECTS.register(eventBus);
    }
}
