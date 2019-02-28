package com.bewitchment.registry;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.api.distillery.IDistilleryRecipe;
import com.bewitchment.api.oven.IOvenRecipe;
import com.bewitchment.api.ritual.IRitual;
import com.bewitchment.api.spinningthread.ISpinningThreadRecipe;
import com.bewitchment.core.Main;

import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModRecipes
{
	@EventBusSubscriber(modid = Main.MOD_ID)
	public static class Distillery
	{
		public static final List<IDistilleryRecipe> REGISTRY = new ArrayList<IDistilleryRecipe>();
		
		@SubscribeEvent
		public static void register(Register<IDistilleryRecipe> event)
		{
			for (IDistilleryRecipe recipe : REGISTRY) event.getRegistry().register(recipe);
		}
	}
	
	@EventBusSubscriber(modid = Main.MOD_ID)
	public static class Oven
	{
		public static final List<IOvenRecipe> REGISTRY = new ArrayList<IOvenRecipe>();
		
		@SubscribeEvent
		public static void register(Register<IOvenRecipe> event)
		{
			for (IOvenRecipe recipe : REGISTRY) event.getRegistry().register(recipe);
		}
	}
	
	@EventBusSubscriber(modid = Main.MOD_ID)
	public static class Ritual
	{
		public static final List<IRitual> REGISTRY = new ArrayList<IRitual>();
		
		@SubscribeEvent
		public static void register(Register<IRitual> event)
		{
			for (IRitual ritual : REGISTRY) event.getRegistry().register(ritual);
		}
	}
	
	@EventBusSubscriber(modid = Main.MOD_ID)
	public static class SpinningThread
	{
		public static final List<ISpinningThreadRecipe> REGISTRY = new ArrayList<ISpinningThreadRecipe>();
		
		@SubscribeEvent
		public static void register(Register<ISpinningThreadRecipe> event)
		{
			for (ISpinningThreadRecipe recipe : REGISTRY) event.getRegistry().register(recipe);
		}
	}
}