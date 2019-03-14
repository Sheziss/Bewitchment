package com.bewitchment.registry;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.Bewitchment;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Bewitchment.MOD_ID)
public class ModSounds
{
	public static final List<SoundEvent> REGISTRY = new ArrayList<>();
	
	public static final SoundEvent BROOM_SWEEP = createSoundEvent(Bewitchment.MOD_ID, "broom_sweep");
	public static final SoundEvent OWL_HOOT = createSoundEvent(Bewitchment.MOD_ID, "owl_hoot");
	
	@SubscribeEvent
	public static void registerSounds(Register<SoundEvent> event)
	{
		for (SoundEvent sound : REGISTRY) event.getRegistry().register(sound);
	}
	
	private static final SoundEvent createSoundEvent(String mod_id, String name)
	{
		ResourceLocation id = new ResourceLocation(mod_id, name);
		SoundEvent event = new SoundEvent(id).setRegistryName(id);
		REGISTRY.add(event);
		return event;
	}
}