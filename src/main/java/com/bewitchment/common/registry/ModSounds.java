package com.bewitchment.common.registry;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.Bewitchment;

import moriyashiine.froglib.FrogLib;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Bewitchment.MOD_ID)
public class ModSounds
{
	public static final List<SoundEvent> REGISTRY = new ArrayList<SoundEvent>();
	
	public static final SoundEvent BROOM_SWEEP = reg(FrogLib.createSoundEvent(Bewitchment.MOD_ID, "broom_sweep"));
	public static final SoundEvent OWL_HOOT = reg(FrogLib.createSoundEvent(Bewitchment.MOD_ID, "owl_hoot"));
	
	@SubscribeEvent
	public static void registerSounds(Register<SoundEvent> event)
	{
		for (SoundEvent sound : REGISTRY) event.getRegistry().register(sound);
	}
	
	private static SoundEvent reg(SoundEvent sound)
	{
		REGISTRY.add(sound);
		return sound;
	}
}