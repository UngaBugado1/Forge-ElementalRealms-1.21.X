package net.matos.elementalrealms.item.client;


import net.matos.elementalrealms.ElementalRealms;
import net.matos.elementalrealms.item.custom.AmethystArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public final class AmethystArmorRenderer extends GeoArmorRenderer<AmethystArmorItem> {
    public AmethystArmorRenderer() {
        super(new DefaultedItemGeoModel<>(ResourceLocation.fromNamespaceAndPath(
                ElementalRealms.MOD_ID, "armor/amethyst_armor"
        )));
    }
}
