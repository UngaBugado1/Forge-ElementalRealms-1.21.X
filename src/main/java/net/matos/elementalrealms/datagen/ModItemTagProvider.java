package net.matos.elementalrealms.datagen;

import net.matos.elementalrealms.ElementalRealms;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
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
//       tag(ModTags.Items.TRANSFORMABLE_ITEMS)
//                .add(ModItems.ORE.get())
//                .add(Items.COAL)
//                .add(Items.STICK)
//                .add(Items.COMPASS);
   }
}
