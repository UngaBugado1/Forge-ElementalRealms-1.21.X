package net.matos.elementalrealms.worldgen;

import net.matos.elementalrealms.ElementalRealms;
import net.matos.elementalrealms.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> SMALL_ARCHAIC_PLACED_KEY = registerKey("small_archaic_placed");
    public static final ResourceKey<PlacedFeature> BIG_ARCHAIC_PLACED_KEY = registerKey("big_archaic_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

    register(context, SMALL_ARCHAIC_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ARCHAIC_TREE_SMALL_KEY),
            VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.1f, 1),
                    ModBlocks.ARCHAIC_SAPLING.get()));

        register(context, BIG_ARCHAIC_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ARCHAIC_TREE_BIG_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.1f, 1),
                        ModBlocks.ARCHAIC_SAPLING.get()));






    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
