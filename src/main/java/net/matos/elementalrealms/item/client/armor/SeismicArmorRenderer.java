package net.matos.elementalrealms.item.client.armor;


import net.matos.elementalrealms.ElementalRealms;
import net.matos.elementalrealms.item.custom.AmethystArmorItem;
import net.matos.elementalrealms.item.custom.SeismicArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public final class SeismicArmorRenderer extends GeoArmorRenderer<SeismicArmorItem> {
    public SeismicArmorRenderer() {
        super(new DefaultedItemGeoModel<>(ResourceLocation.fromNamespaceAndPath(
                ElementalRealms.MOD_ID, "armor/seismic_armor"
        )));
    }
}
