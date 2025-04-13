package net.matos.elementalrealms.item.client;

import net.matos.elementalrealms.ElementalRealms;
import net.matos.elementalrealms.item.custom.AmethystArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AmethystArmorModel extends GeoModel<AmethystArmorItem> {
    private static final ResourceLocation MODEL = ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "geo/amethyst_armor.geo.json");
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "textures/armor/amethyst_armor.png");
    private static final ResourceLocation ANIMATION = ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "animations/amethyst_armor.animation.json");

    @Override
    public ResourceLocation getModelResource(AmethystArmorItem object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(AmethystArmorItem object) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(AmethystArmorItem object) {
        return ANIMATION;
    }
}

