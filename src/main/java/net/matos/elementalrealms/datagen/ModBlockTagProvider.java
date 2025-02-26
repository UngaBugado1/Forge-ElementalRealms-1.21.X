package net.matos.elementalrealms.datagen;

import net.matos.elementalrealms.ElementalRealms;
import net.matos.elementalrealms.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ElementalRealms.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.CHISELED_TERRAITE.get())

                .add(ModBlocks.TERRAITE.get())
                .add(ModBlocks.TERRAITE_STAIRS.get())
                .add(ModBlocks.TERRAITE_SLAB.get())
                .add(ModBlocks.TERRAITE_WALL.get())

                .add(ModBlocks.POLISHED_TERRAITE.get())
                .add(ModBlocks.POLISHED_TERRAITE_STAIRS.get())
                .add(ModBlocks.POLISHED_TERRAITE_SLAB.get())
                .add(ModBlocks.POLISHED_TERRAITE_WALL.get())
                .add(ModBlocks.POLISHED_TERRAITE_BUTTON.get())

                .add(ModBlocks.TERRAITE_BRICKS.get())
                .add(ModBlocks.TERRAITE_BRICK_STAIRS.get())
                .add(ModBlocks.TERRAITE_BRICK_SLAB.get())
                .add(ModBlocks.TERRAITE_BRICK_WALL.get())
                .add(ModBlocks.GEOCLUSTER.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.GEOCLUSTER.get());

        //tag(BlockTags.NEEDS_DIAMOND_TOOL)#
                //.add(ModBlocks.TEST_BLOCK.get());

        tag(BlockTags.WALLS)
                .add(ModBlocks.TERRAITE_WALL.get())
                .add(ModBlocks.POLISHED_TERRAITE_WALL.get())
                .add(ModBlocks.TERRAITE_BRICK_WALL.get());
    }
}
