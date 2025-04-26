package net.matos.elementalrealms.datagen;

import net.matos.elementalrealms.ElementalRealms;
import net.matos.elementalrealms.block.ModBlocks;
import net.matos.elementalrealms.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture,
                              CompletableFuture<TagLookup<Block>> lookupCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, completableFuture, lookupCompletableFuture, ElementalRealms.MOD_ID, existingFileHelper);
    }

   @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.ARCHAIC_LOG.get().asItem())
                .add(ModBlocks.ARCHAIC_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_ARCHAIC_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_ARCHAIC_WOOD.get().asItem());

        tag(ItemTags.PLANKS)
                .add(ModBlocks.ARCHAIC_PLANKS.get().asItem());

       tag(ModTags.Items.ARCHAIC_LOGS)
               .add(
                       ModBlocks.ARCHAIC_LOG.get().asItem(),
                       ModBlocks.ARCHAIC_WOOD.get().asItem(),
                       ModBlocks.STRIPPED_ARCHAIC_LOG.get().asItem(),
                       ModBlocks.STRIPPED_ARCHAIC_WOOD.get().asItem()
               );

       tag(ItemTags.LOGS)
               .add(ModBlocks.ARCHAIC_LOG.get().asItem());

       tag(ItemTags.PLANKS)
               .add(ModBlocks.ARCHAIC_PLANKS.get().asItem());
   }
}
