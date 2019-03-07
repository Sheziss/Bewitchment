package com.bewitchment.common.handler;

import java.util.Random;

import com.bewitchment.common.registry.ModBlocks;
import com.bewitchment.common.registry.ModItems;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockDropHandler
{
	@SubscribeEvent
	public void harvestDrops(HarvestDropsEvent event)
	{
		replaceDrop(event, ModBlocks.ore_salt, new ItemStack(ModItems.salt, fortuneDrop(event.getFortuneLevel(), event.getWorld().rand)), 100, true, false);
		replaceDrop(event, ModBlocks.ore_alexandrite, new ItemStack(ModItems.gem_alexandrite, fortuneDrop(event.getFortuneLevel(), event.getWorld().rand)), 100, true, false);
		replaceDrop(event, ModBlocks.ore_amethyst, new ItemStack(ModItems.gem_amethyst, fortuneDrop(event.getFortuneLevel(), event.getWorld().rand)), 100, true, false);
		replaceDrop(event, ModBlocks.ore_bloodstone, new ItemStack(ModItems.gem_bloodstone, fortuneDrop(event.getFortuneLevel(), event.getWorld().rand)), 100, true, false);
		replaceDrop(event, ModBlocks.ore_garnet, new ItemStack(ModItems.gem_garnet, fortuneDrop(event.getFortuneLevel(), event.getWorld().rand)), 100, true, false);
		replaceDrop(event, ModBlocks.ore_jasper, new ItemStack(ModItems.gem_jasper, fortuneDrop(event.getFortuneLevel(), event.getWorld().rand)), 100, true, false);
		replaceDrop(event, ModBlocks.ore_malachite, new ItemStack(ModItems.gem_malachite, fortuneDrop(event.getFortuneLevel(), event.getWorld().rand)), 100, true, false);
		replaceDrop(event, ModBlocks.ore_nuummite, new ItemStack(ModItems.gem_nuummite, fortuneDrop(event.getFortuneLevel(), event.getWorld().rand)), 100, true, false);
		replaceDrop(event, ModBlocks.ore_tigers_eye, new ItemStack(ModItems.gem_tigers_eye, fortuneDrop(event.getFortuneLevel(), event.getWorld().rand)), 100, true, false);
		replaceDrop(event, ModBlocks.ore_tourmaline, new ItemStack(ModItems.gem_tourmaline, fortuneDrop(event.getFortuneLevel(), event.getWorld().rand)), 100, true, false);
		
		replaceDrop(event, ModBlocks.leaves_juniper, new ItemStack(ModItems.juniper_berries), 15, false, false);
		replaceDrop(event, ModBlocks.leaves_yew, new ItemStack(ModItems.yew_aril), 15, false, false);
	}
	
	private void replaceDrop(HarvestDropsEvent event, Block block, ItemStack out, int chance, boolean replace, boolean ignoreSilkTouch)
	{
		if (event.getState().getBlock() == block && (ignoreSilkTouch ? true : !event.isSilkTouching()))
		{
			if (replace) event.getDrops().clear();
			if (event.getWorld().rand.nextInt(100) <= chance) event.getDrops().add(out);
		}
	}
	
	private int fortuneDrop(int fortune, Random rand)
	{
		return fortune > 0 ? Math.max(0, rand.nextInt(fortune + 2)) : 1;
	}
}