package net.matos.elementalrealms.sound;

import net.matos.elementalrealms.ElementalRealms;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ElementalRealms.MOD_ID);


    // public static final RegistryObject<SoundEvent> ITEM_USE = registerSoundEvent("item_use")

    // public static final RegistryObject<SoundEvent> BLOCK_BREAK = registerSoundEvent("block_break");
    // public static final RegistryObject<SoundEvent> BLOCK_STEP = registerSoundEvent("block_step");
    // public static final RegistryObject<SoundEvent> BLOCK_PLACE = registerSoundEvent("block_place");
    // public static final RegistryObject<SoundEvent> BLOCK_HIT = registerSoundEvent("block_hit");
    // public static final RegistryObject<SoundEvent> BLOCK_FALL = registerSoundEvent("block_fall");

    // public static final ForgeSoundType BLOCK_SOUNDS = new ForgeSoundType(1f, 1f,
    //       ModSounds.BLOCK_BREAK, ModSounds.BLOCK_STEP, ModSounds.BLOCK_PLACE, ModSounds.BLOCK_HIT, ModSounds.BLOCK_FALL);



    private static RegistryObject<SoundEvent> registerSoundEvent(String name){
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, name)));
    }


    public static void register(IEventBus eventBus)
    {
        SOUND_EVENTS.register(eventBus);
    }
}
