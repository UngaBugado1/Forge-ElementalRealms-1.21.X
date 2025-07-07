package net.matos.elementalrealms.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.matos.elementalrealms.ElementalRealms;
import net.matos.elementalrealms.entity.custom.TectoraxEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TectoraxRenderer extends MobRenderer<TectoraxEntity, TectoraxModel<TectoraxEntity>> {
    public TectoraxRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new TectoraxModel(pContext.bakeLayer(ModModelLayers.TECTORAX)), 1.5f);
        this.addLayer(new TectoraxArmorLayer(this, pContext.getModelSet()));
        this.addLayer(new TectoraxBlankyLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(TectoraxEntity tectoraxEntity) {
        return ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "textures/entity/tectorax/tectorax.png");
    }

    @Override
    public void render(TectoraxEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        if(pEntity.isBaby()){
            pPoseStack.scale(0.25f, 0.25f, 0.25f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
