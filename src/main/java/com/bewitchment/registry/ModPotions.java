package com.bewitchment.registry;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.potion.PotionSunWard;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = Bewitchment.MODID)
public class ModPotions {
	public static final List<Potion> REGISTRY = new ArrayList<>();

	public static final Potion sun_ward = new PotionSunWard();
	public static final Potion wolfsbane = null;
	public static final Potion absence = null;
	public static final Potion garlicked = null;
	public static final Potion demons_bane = null;
	public static final Potion holy_water = null;

	@SubscribeEvent
	public static void registerEntities(Register<Potion> event) {
		for (Potion potion : REGISTRY) event.getRegistry().register(potion);
	}
}