package net.matos.elementalrealms.enchantment;

import com.mojang.serialization.MapCodec;
import net.matos.elementalrealms.ElementalRealms;
import net.matos.elementalrealms.enchantment.custom.FungusWalkEnchantmentEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, ElementalRealms.MOD_ID);

    public static final RegistryObject<MapCodec<? extends EnchantmentEntityEffect>> FUNGUS_WALK =
            ENTITY_ENCHANTMENT_EFFECTS.register("fungus_walk", () -> FungusWalkEnchantmentEffect.CODEC);



    public static void register(IEventBus eventBus){
        ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);
    }
}
