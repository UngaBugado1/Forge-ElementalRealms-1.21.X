package net.matos.elementalrealms.entity.client;

import net.matos.elementalrealms.ElementalRealms;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation TECTORAX = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "tectorax"), "main");
    public static final ModelLayerLocation TECTORAX_ARMOR = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "tectorax_armor"), "armor");
}
