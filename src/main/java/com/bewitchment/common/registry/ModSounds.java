package com.bewitchment.common.registry;

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
	public static final List<SoundEvent> REGISTRY = new ArrayList<SoundEvent>();
	
	public static final SoundEvent BROOM_SWEEP = createSoundEvent("broom_sweep");
	public static final SoundEvent OWL_HOOT = createSoundEvent("owl_hoot");
	
	private static SoundEvent createSoundEvent(String name)
	{
		ResourceLocation id = new ResourceLocation(Bewitchment.MOD_ID, name);
		SoundEvent event = new SoundEvent(id).setRegistryName(id);
		REGISTRY.add(event);
		return event;
	}
	
	@SubscribeEvent
	public static void register(Register<SoundEvent> event)
	{
		for (SoundEvent sound : REGISTRY) event.getRegistry().register(sound);
	}
}