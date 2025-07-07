package net.matos.elementalrealms.worldgen.tree;

import net.matos.elementalrealms.ElementalRealms;
import net.matos.elementalrealms.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
        public static final TreeGrower ARCHAIC = new TreeGrower(ElementalRealms.MOD_ID + ":archaic",
                Optional.of(ModConfiguredFeatures.ARCHAIC_TREE_BIG_KEY),
                Optional.of(ModConfiguredFeatures.ARCHAIC_TREE_SMALL_KEY),
                Optional.empty());
}
