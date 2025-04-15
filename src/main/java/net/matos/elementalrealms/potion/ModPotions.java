package net.matos.elementalrealms.potion;

import net.matos.elementalrealms.ElementalRealms;
import net.matos.elementalrealms.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS=
            DeferredRegister.create(ForgeRegistries.POTIONS, ElementalRealms.MOD_ID);

     //public static final RegistryObject<Potion> GOLDEN_ESSENCE = POTIONS.register("golden_essence",
    // () -> new Potion(new MobEffectInstance(ModEffects.GOLDEN_BLOOM_EFFECT.getHolder().get(), 600, 0)));

    public static void register(IEventBus eventBus){
        POTIONS.register(eventBus);
    }
}
