package net.matos.elementalrealms.datagen;

import net.matos.elementalrealms.ElementalRealms;
import net.matos.elementalrealms.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ElementalRealms.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.TERRAITE);
        stairsBlock(ModBlocks.TERRAITE_STAIRS.get(), blockTexture(ModBlocks.TERRAITE.get()));
        blockItem(ModBlocks.TERRAITE_STAIRS);
        slabBlock(ModBlocks.TERRAITE_SLAB.get(), blockTexture(ModBlocks.TERRAITE.get()), blockTexture(ModBlocks.TERRAITE.get()));
        blockItem(ModBlocks.TERRAITE_SLAB);
        wallBlock(ModBlocks.TERRAITE_WALL.get(), blockTexture(ModBlocks.TERRAITE.get()));
        blockItem(ModBlocks.TERRAITE_WALL);

        blockWithItem(ModBlocks.POLISHED_TERRAITE);
        stairsBlock(ModBlocks.POLISHED_TERRAITE_STAIRS.get(), blockTexture(ModBlocks.POLISHED_TERRAITE.get()));
        blockItem(ModBlocks.POLISHED_TERRAITE_STAIRS);
        slabBlock(ModBlocks.POLISHED_TERRAITE_SLAB.get(), blockTexture(ModBlocks.POLISHED_TERRAITE.get()), blockTexture(ModBlocks.POLISHED_TERRAITE.get()));
        blockItem(ModBlocks.POLISHED_TERRAITE_SLAB);
        wallBlock(ModBlocks.POLISHED_TERRAITE_WALL.get(), blockTexture(ModBlocks.POLISHED_TERRAITE.get()));
        blockItem(ModBlocks.POLISHED_TERRAITE_WALL);
        buttonBlock(ModBlocks.POLISHED_TERRAITE_BUTTON.get(), blockTexture(ModBlocks.POLISHED_TERRAITE.get()));
        blockItem(ModBlocks.POLISHED_TERRAITE_BUTTON);


        blockWithItem(ModBlocks.TERRAITE_BRICKS);
        stairsBlock(ModBlocks.TERRAITE_BRICK_STAIRS.get(), blockTexture(ModBlocks.TERRAITE_BRICKS.get()));
        blockItem(ModBlocks.TERRAITE_BRICK_STAIRS);
        slabBlock(ModBlocks.TERRAITE_BRICK_SLAB.get(), blockTexture(ModBlocks.TERRAITE_BRICKS.get()), blockTexture(ModBlocks.TERRAITE_BRICKS.get()));
        blockItem(ModBlocks.TERRAITE_BRICK_SLAB);
        wallBlock(ModBlocks.TERRAITE_BRICK_WALL.get(), blockTexture(ModBlocks.TERRAITE_BRICKS.get()));
        blockItem(ModBlocks.TERRAITE_BRICK_WALL);


        blockWithItem(ModBlocks.GEOCLUSTER);

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockItem(RegistryObject<? extends Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("elementalrealms:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockItem(RegistryObject<? extends Block> blockRegistryObject, String appendix) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("elementalrealms:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + appendix));
    }
}
