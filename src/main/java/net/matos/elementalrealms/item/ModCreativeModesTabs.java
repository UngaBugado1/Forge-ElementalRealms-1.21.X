package net.matos.elementalrealms.item;

import net.matos.elementalrealms.ElementalRealms;
import net.matos.elementalrealms.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModesTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ElementalRealms.MOD_ID);

public static final RegistryObject<CreativeModeTab> TERRAVALE_ITEMS_TAB = CREATIVE_MODE_TABS.register("terravale_items_tab",
        () -> CreativeModeTab.builder()
                .icon(() -> new ItemStack(ModItems.VERINDITE_CRYSTAL.get()))
                .title(Component.translatable("creativetab.elementalrealms.terravale_items"))
                .displayItems((itemDisplayParameters, output) ->
                {
                 output.accept(ModItems.VERINDITE_CRYSTAL.get());
                 output.accept(ModBlocks.CHISELED_TERRAITE.get());
                 output.accept(ModBlocks.TERRAITE.get());
                 output.accept(ModBlocks.TERRAITE_STAIRS.get());
                 output.accept(ModBlocks.TERRAITE_SLAB.get());
                 output.accept(ModBlocks.TERRAITE_WALL.get());
                 output.accept(ModBlocks.POLISHED_TERRAITE.get());
                 output.accept(ModBlocks.POLISHED_TERRAITE_STAIRS.get());
                 output.accept(ModBlocks.POLISHED_TERRAITE_SLAB.get());
                 output.accept(ModBlocks.POLISHED_TERRAITE_WALL.get());
                 output.accept(ModBlocks.POLISHED_TERRAITE_BUTTON.get());
                 output.accept(ModBlocks.TERRAITE_BRICKS.get());
                 output.accept(ModBlocks.TERRAITE_BRICK_STAIRS.get());
                 output.accept(ModBlocks.TERRAITE_BRICK_SLAB.get());
                 output.accept(ModBlocks.TERRAITE_BRICK_WALL.get());
                 output.accept(ModBlocks.GEOCLUSTER.get());
                 output.accept(ModBlocks.ARCHAIC_LOG.get());
                 output.accept(ModBlocks.ARCHAIC_WOOD.get());
                 output.accept(ModBlocks.STRIPPED_ARCHAIC_LOG.get());
                 output.accept(ModBlocks.STRIPPED_ARCHAIC_WOOD.get());
                 output.accept(ModBlocks.ARCHAIC_PLANKS.get());
                 output.accept(ModBlocks.ARCHAIC_LEAVES.get());
                 output.accept(ModBlocks.ARCHAIC_SAPLING.get());
                 output.accept(ModItems.EMBEROOT.get());
                 output.accept(ModItems.TERRA_WARHAMMER.get());
                 output.accept(ModItems.AMETHYST_HELMET.get());
                 output.accept(ModItems.AMETHYST_CHESTPLATE.get());
                 output.accept(ModItems.AMETHYST_LEGGINGS.get());
                 output.accept(ModItems.AMETHYST_BOOTS.get());
                 output.accept(ModItems.GOLDEN_ESSENCE.get());
                }).build());




    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
