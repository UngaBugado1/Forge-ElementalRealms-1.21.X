package net.matos.elementalrealms.datagen;

import net.matos.elementalrealms.ElementalRealms;
import net.matos.elementalrealms.block.ModBlocks;
import net.matos.elementalrealms.block.custom.EmberootCropBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

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

        makeCrop(((CropBlock) ModBlocks.EMBEROOT_CROP.get()),"emberoot_crop_stage", "emberoot_crop_stage");

        logBlock(ModBlocks.ARCHAIC_LOG.get());
        axisBlock(ModBlocks.ARCHAIC_WOOD.get(), blockTexture(ModBlocks.ARCHAIC_LOG.get()), blockTexture(ModBlocks.ARCHAIC_LOG.get()));
        logBlock(ModBlocks.STRIPPED_ARCHAIC_LOG.get());
        axisBlock(ModBlocks.STRIPPED_ARCHAIC_WOOD.get(), blockTexture(ModBlocks.STRIPPED_ARCHAIC_LOG.get()), blockTexture(ModBlocks.STRIPPED_ARCHAIC_LOG.get()));

        blockItem(ModBlocks.ARCHAIC_LOG);
        blockItem(ModBlocks.ARCHAIC_WOOD);
        blockItem(ModBlocks.STRIPPED_ARCHAIC_LOG);
        blockItem(ModBlocks.STRIPPED_ARCHAIC_WOOD);

        blockWithItem(ModBlocks.ARCHAIC_PLANKS);

        leavesBlock(ModBlocks.ARCHAIC_LEAVES);
        saplingBlock(ModBlocks.ARCHAIC_SAPLING);


    }


    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((EmberootCropBlock) block).getAgeProperty()),
                ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "block/" + textureName + state.getValue(((EmberootCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
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
