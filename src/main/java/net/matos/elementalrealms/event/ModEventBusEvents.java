package net.matos.elementalrealms.event;

import net.matos.elementalrealms.ElementalRealms;
import net.matos.elementalrealms.entity.ModEntities;
import net.matos.elementalrealms.entity.client.ModModelLayers;
import net.matos.elementalrealms.entity.client.TectoraxModel;
import net.matos.elementalrealms.entity.custom.TectoraxEntity;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ElementalRealms.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents{

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayers.TECTORAX, TectoraxModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.TECTORAX_ARMOR, TectoraxModel::createBodyLayer);
    }


    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.TECTORAX.get(), TectoraxEntity.createAttributes().build());
    }

}
