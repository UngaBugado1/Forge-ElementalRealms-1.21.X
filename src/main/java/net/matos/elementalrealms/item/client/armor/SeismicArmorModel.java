package net.matos.elementalrealms.item.client.armor;

import net.matos.elementalrealms.ElementalRealms;
import net.matos.elementalrealms.item.custom.AmethystArmorItem;
import net.matos.elementalrealms.item.custom.SeismicArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SeismicArmorModel extends GeoModel<SeismicArmorItem> {
    private static final ResourceLocation MODEL = ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "geo/seismic_armor.geo.json");
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "textures/armor/seismic_armor.png");
    private static final ResourceLocation ANIMATION = ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "animations/seismic_armor.animation.json");

    @Override
    public ResourceLocation getModelResource(SeismicArmorItem object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(SeismicArmorItem object) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(SeismicArmorItem object) {
        return ANIMATION;
    }
}

