package com.bewitchment.common.handler;

import com.bewitchment.common.registry.ModBlocks;
import com.bewitchment.common.registry.ModItems;

import moriyashiine.froglib.FrogLib;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockDropHandler
{
	@SubscribeEvent
	public void harvestDrops(HarvestDropsEvent event)
	{
		FrogLib.replaceDrop(event, ModBlocks.ore_salt, new ItemStack(ModItems.salt, FrogLib.getFortuneDropAmount(event.getWorld().rand, event.getFortuneLevel())), 100, true, false);
		FrogLib.replaceDrop(event, ModBlocks.ore_alexandrite, new ItemStack(ModItems.gem_alexandrite, FrogLib.getFortuneDropAmount(event.getWorld().rand, event.getFortuneLevel())), 100, true, false);
		FrogLib.replaceDrop(event, ModBlocks.ore_amethyst, new ItemStack(ModItems.gem_amethyst, FrogLib.getFortuneDropAmount(event.getWorld().rand, event.getFortuneLevel())), 100, true, false);
		FrogLib.replaceDrop(event, ModBlocks.ore_bloodstone, new ItemStack(ModItems.gem_bloodstone, FrogLib.getFortuneDropAmount(event.getWorld().rand, event.getFortuneLevel())), 100, true, false);
		FrogLib.replaceDrop(event, ModBlocks.ore_garnet, new ItemStack(ModItems.gem_garnet, FrogLib.getFortuneDropAmount(event.getWorld().rand, event.getFortuneLevel())), 100, true, false);
		FrogLib.replaceDrop(event, ModBlocks.ore_jasper, new ItemStack(ModItems.gem_jasper, FrogLib.getFortuneDropAmount(event.getWorld().rand, event.getFortuneLevel())), 100, true, false);
		FrogLib.replaceDrop(event, ModBlocks.ore_malachite, new ItemStack(ModItems.gem_malachite, FrogLib.getFortuneDropAmount(event.getWorld().rand, event.getFortuneLevel())), 100, true, false);
		FrogLib.replaceDrop(event, ModBlocks.ore_nuummite, new ItemStack(ModItems.gem_nuummite, FrogLib.getFortuneDropAmount(event.getWorld().rand, event.getFortuneLevel())), 100, true, false);
		FrogLib.replaceDrop(event, ModBlocks.ore_tigers_eye, new ItemStack(ModItems.gem_tigers_eye, FrogLib.getFortuneDropAmount(event.getWorld().rand, event.getFortuneLevel())), 100, true, false);
		FrogLib.replaceDrop(event, ModBlocks.ore_tourmaline, new ItemStack(ModItems.gem_tourmaline, FrogLib.getFortuneDropAmount(event.getWorld().rand, event.getFortuneLevel())), 100, true, false);
		
		FrogLib.replaceDrop(event, ModBlocks.leaves_juniper, new ItemStack(ModItems.juniper_berries), 15, false, false);
		FrogLib.replaceDrop(event, ModBlocks.leaves_yew, new ItemStack(ModItems.yew_aril), 15, false, false);
	}
}