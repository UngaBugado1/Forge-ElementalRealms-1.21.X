package net.matos.elementalrealms.util;

import net.matos.elementalrealms.ElementalRealms;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {

        public static final TagKey<Block> NEEDS_TERRAVALE_TOOL = createTag("needs_terravale_tool");
        public static final TagKey<Block> INCORRECT_FOR_TERRAVALE_TOOL = createTag("incorrect_from_terravale");
        public static final TagKey<Block> FUNGUS_BLOCKS = createTag("fungus_blocks");
        public static final TagKey<Block> ARCHAIC_LOGS = createTag("archaic_logs");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> ARCHAIC_LOGS = createTag("archaic_logs");
        public static final TagKey<Item> ARCHAIC_PLANKS = createTag("archaic_planks");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, name));
        }
    }
}
