package com.bewitchment.registry;

import com.bewitchment.Bewitchment;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = Bewitchment.MODID)
public class ModSounds {
	public static final List<SoundEvent> REGISTRY = new ArrayList<>();

	public static final SoundEvent BROOM_SWEEP = createSoundEvent("broom_sweep");
	public static final SoundEvent CHALK_SCRIBBLE = createSoundEvent("chalk_scribble");
	public static final SoundEvent OWL_HOOT = createSoundEvent("owl_hoot");
	public static final SoundEvent RAVEN_CRY = createSoundEvent("raven_cry");
	public static final SoundEvent TOAD_DEATH = createSoundEvent("toad_death");
	public static final SoundEvent TOAD_HURT = createSoundEvent("toad_hurt");
	public static final SoundEvent TOAD_IDLE = createSoundEvent("toad_idle");

	@SubscribeEvent
	public static void registerSounds(Register<SoundEvent> event) {
		for (SoundEvent sound : REGISTRY) event.getRegistry().register(sound);
	}

	private static final SoundEvent createSoundEvent(String name) {
		ResourceLocation id = new ResourceLocation(Bewitchment.MODID, name);
		SoundEvent event = new SoundEvent(id).setRegistryName(id);
		REGISTRY.add(event);
		return event;
	}
}