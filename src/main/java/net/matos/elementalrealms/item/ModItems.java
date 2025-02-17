package net.matos.elementalrealms.item;

import net.matos.elementalrealms.ElementalRealms;
import net.matos.elementalrealms.item.custom.ModFoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ElementalRealms.MOD_ID);

    public static final RegistryObject<Item> VERINDITE_CRYSTAL = ITEMS.register("verindite_crystal",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EMBEROOT = ITEMS.register("emberoot",
            () -> new Item(new Item.Properties().food(ModFoodProperties.EMBEROOT)));


    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
