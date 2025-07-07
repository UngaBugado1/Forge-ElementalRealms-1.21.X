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

        tag(BlockTags.LOGS)
                .add(ModBlocks.ARCHAIC_LOG.get());

        tag(BlockTags.WALLS)
                .add(ModBlocks.TERRAITE_WALL.get())
                .add(ModBlocks.POLISHED_TERRAITE_WALL.get())
                .add(ModBlocks.TERRAITE_BRICK_WALL.get());

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.ARCHAIC_LOG.get())
                .add(ModBlocks.ARCHAIC_WOOD.get())
                .add(ModBlocks.STRIPPED_ARCHAIC_LOG.get())
                .add(ModBlocks.STRIPPED_ARCHAIC_WOOD.get());

        tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.ARCHAIC_FENCE.get());

        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.ARCHAIC_FENCE_GATE.get());

        tag(BlockTags.STAIRS)
                .add(ModBlocks.ARCHAIC_STAIRS.get())
                .add(ModBlocks.TERRAITE_STAIRS.get())
                .add(ModBlocks.POLISHED_TERRAITE_STAIRS.get())
                .add(ModBlocks.TERRAITE_BRICK_STAIRS.get());

        tag(BlockTags.SLABS)
                .add(ModBlocks.ARCHAIC_SLAB.get())
                .add(ModBlocks.TERRAITE_SLAB.get())
                .add(ModBlocks.POLISHED_TERRAITE_SLAB.get())
                .add(ModBlocks.TERRAITE_BRICK_SLAB.get());

        tag(BlockTags.STONE_BUTTONS)
                .add(ModBlocks.POLISHED_TERRAITE_BUTTON.get());

    }
}
