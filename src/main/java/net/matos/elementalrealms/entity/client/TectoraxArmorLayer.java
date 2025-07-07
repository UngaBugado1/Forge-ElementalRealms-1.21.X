package net.matos.elementalrealms.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.matos.elementalrealms.ElementalRealms;
import net.matos.elementalrealms.entity.custom.TectoraxEntity;
import net.matos.elementalrealms.item.ModItems;
import net.matos.elementalrealms.item.custom.TectoraxArmorItem;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

public class TectoraxArmorLayer extends RenderLayer<TectoraxEntity, TectoraxModel<TectoraxEntity>> {

    private final TectoraxModel<TectoraxEntity> model;
    private Map<Item, ResourceLocation> ARMOR_MAP = Map.of(
            ModItems.TECTORAX_ARMOR.get(),
            ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "textures/entity/tectorax/armor/tectorax_armor.png"));

    public TectoraxArmorLayer(RenderLayerParent<TectoraxEntity, TectoraxModel<TectoraxEntity>> renderer, EntityModelSet models) {
        super(renderer);
        this.model = new TectoraxModel<>(models.bakeLayer(ModModelLayers.TECTORAX_ARMOR));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight,
                       TectoraxEntity livingEntity, float limbSwing, float limbSwingAmount,
                       float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        if (livingEntity.isWearingBodyArmor()) {
            ItemStack itemstack = livingEntity.getBodyArmorItem();
            if (itemstack.getItem() instanceof TectoraxArmorItem armorItem) {
                this.getParentModel().copyPropertiesTo(this.model);
                this.model.prepareMobModel(livingEntity, limbSwing, limbSwingAmount, partialTick);
                this.model.setupAnim(livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(ARMOR_MAP.get(armorItem)));
                this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
            }
        }
    }
}
