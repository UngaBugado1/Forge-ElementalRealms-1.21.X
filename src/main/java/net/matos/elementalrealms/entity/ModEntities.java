package net.matos.elementalrealms.entity;

import net.matos.elementalrealms.ElementalRealms;

import net.matos.elementalrealms.entity.custom.TectoraxEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ElementalRealms.MOD_ID);

    public static final RegistryObject<EntityType<TectoraxEntity>> TECTORAX =
            ENTITY_TYPES.register("tectorax", () -> EntityType.Builder.of(TectoraxEntity::new, MobCategory.CREATURE)
                    .sized(2.8f, 2.1f).build("tectorax"));



    public static void register (IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
