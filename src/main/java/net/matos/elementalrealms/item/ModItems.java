package net.matos.elementalrealms.item;

import net.matos.elementalrealms.ElementalRealms;
import net.matos.elementalrealms.item.custom.*;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
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

   public static final RegistryObject<Item> TERRA_WARHAMMER = ITEMS.register("terra_warhammer",
          () -> new HammerItem(ModToolTiers.TERRAVALE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.TERRAVALE, 3, -3.5f))));


    public static final RegistryObject<Item> AMETHYST_HELMET = ITEMS.register("amethyst_helmet",
            () -> new AmethystArmorItem(ModArmorMaterials.AMETHYST_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> AMETHYST_CHESTPLATE = ITEMS.register("amethyst_chestplate",
            () -> new AmethystArmorItem(ModArmorMaterials.AMETHYST_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> AMETHYST_LEGGINGS = ITEMS.register("amethyst_leggings",
            () -> new AmethystArmorItem(ModArmorMaterials.AMETHYST_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> AMETHYST_BOOTS = ITEMS.register("amethyst_boots",
            () -> new AmethystArmorItem(ModArmorMaterials.AMETHYST_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> GOLDEN_ESSENCE = ITEMS.register("golden_essence",
            () -> new GoldenEssenceItem());




    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
