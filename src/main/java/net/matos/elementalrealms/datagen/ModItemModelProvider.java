package net.matos.elementalrealms.datagen;


import net.matos.elementalrealms.ElementalRealms;
import net.matos.elementalrealms.block.ModBlocks;
import net.matos.elementalrealms.item.ModItems;
import net.matos.elementalrealms.potion.ModPotions;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ElementalRealms.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.VERINDITE_CRYSTAL.get());
        basicItem(ModItems.EMBEROOT.get());
        basicItem(ModItems.TECTORAX_ARMOR.get());
        basicItem(ModItems.TECTORAX_HORN.get());

        wallItem(ModBlocks.TERRAITE_WALL, ModBlocks.TERRAITE);
        wallItem(ModBlocks.POLISHED_TERRAITE_WALL, ModBlocks.POLISHED_TERRAITE);
        wallItem(ModBlocks.TERRAITE_BRICK_WALL, ModBlocks.TERRAITE_BRICKS);
        buttonItem(ModBlocks.POLISHED_TERRAITE_BUTTON, ModBlocks.POLISHED_TERRAITE);

        saplingItem(ModBlocks.ARCHAIC_SAPLING);

        buttonItem(ModBlocks.ARCHAIC_BUTTON, ModBlocks.ARCHAIC_PLANKS);
        fenceItem(ModBlocks.ARCHAIC_FENCE, ModBlocks.ARCHAIC_PLANKS);

        simpleBlockItem(ModBlocks.ARCHAIC_DOOR);

        withExistingParent(ModItems.TECTORAX_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }


    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID,"block/" + item.getId().getPath()));
    }


    public void buttonItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void fenceItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }


    public void wallItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<? extends Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID,"item/" + item.getId().getPath()));
    }
}
