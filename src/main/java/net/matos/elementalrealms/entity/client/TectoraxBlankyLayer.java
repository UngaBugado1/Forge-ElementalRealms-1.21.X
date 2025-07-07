package net.matos.elementalrealms.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.matos.elementalrealms.ElementalRealms;
import net.matos.elementalrealms.entity.custom.TectoraxEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;

public class TectoraxBlankyLayer extends RenderLayer<TectoraxEntity, TectoraxModel<TectoraxEntity>> {

    private final TectoraxModel<TectoraxEntity> model;

    private static final ResourceLocation[] DYE_LOCATION = new ResourceLocation[]{
            ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "textures/entity/tectorax/armor/blankies/white.png"),
            ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "textures/entity/tectorax/armor/blankies/orange.png"),
            ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "textures/entity/tectorax/armor/blankies/magenta.png"),
            ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "textures/entity/tectorax/armor/blankies/light_blue.png"),
            ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "textures/entity/tectorax/armor/blankies/yellow.png"),
            ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "textures/entity/tectorax/armor/blankies/lime.png"),
            ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "textures/entity/tectorax/armor/blankies/pink.png"),
            ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "textures/entity/tectorax/armor/blankies/gray.png"),
            ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "textures/entity/tectorax/armor/blankies/light_gray.png"),
            ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "textures/entity/tectorax/armor/blankies/cyan.png"),
            ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "textures/entity/tectorax/armor/blankies/purple.png"),
            ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "textures/entity/tectorax/armor/blankies/blue.png"),
            ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "textures/entity/tectorax/armor/blankies/brown.png"),
            ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "textures/entity/tectorax/armor/blankies/green.png"),
            ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "textures/entity/tectorax/armor/blankies/red.png"),
            ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "textures/entity/tectorax/armor/blankies/black.png")
    };


    public TectoraxBlankyLayer(RenderLayerParent<TectoraxEntity, TectoraxModel<TectoraxEntity>> parent) {
        super(parent);
        this.model = new TectoraxModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.TECTORAX));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight,
                       TectoraxEntity entity, float limbSwing, float limbSwingAmount, float partialTick,
                       float ageInTicks, float netHeadYaw, float headPitch) {

        DyeColor carpetColor = entity.getSwag();
        if (carpetColor == null) {
            return; // No carpet to render
        }

        // Copy model state from parent model
        this.getParentModel().copyPropertiesTo(this.model);
        this.model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTick);
        this.model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        ResourceLocation texture = DYE_LOCATION[carpetColor.getId()];
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(texture));
        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);

    }
}
