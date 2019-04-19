package com.bewitchment.common.handler;

import com.bewitchment.registry.ModObjects;
import net.minecraft.block.*;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class BlockDropHandler {
	private static int getFortuneDropAmount(Random rand, int fortuneLevel) {
		return fortuneLevel > 0 ? Math.max(0, rand.nextInt(fortuneLevel + 2)) : 1;
	}

	private static void replaceDrop(HarvestDropsEvent event, Block block, ItemStack out, int chance, boolean replace, boolean ignoreSilkTouch) {
		if (event.getState().getBlock() == block && (ignoreSilkTouch ? true : !event.isSilkTouching())) {
			if (replace) event.getDrops().clear();
			if (event.getWorld().rand.nextInt(100) <= chance) event.getDrops().add(out);
		}
	}

	@SubscribeEvent
	public static void dropController(BlockEvent.HarvestDropsEvent event) {
		if (event.getState().getBlock() == Blocks.RED_FLOWER && event.getState().getValue(Blocks.RED_FLOWER.getTypeProperty()) == BlockFlower.EnumFlowerType.ALLIUM && event.getWorld().rand.nextInt(6) == 0) {
			//Allium -> garlic seeds
			event.getDrops().clear();
			event.getDrops().add(new ItemStack(ModObjects.seed_garlic, 1));
		} else if (((event.getState().getBlock() == Blocks.LEAVES && event.getState().getValue(BlockOldLeaf.VARIANT) == BlockPlanks.EnumType.OAK) || (event.getState().getBlock() == Blocks.LEAVES2 && event.getState().getValue(BlockNewLeaf.VARIANT) == BlockPlanks.EnumType.DARK_OAK)) && event.getWorld().rand.nextInt(24) == 0 && event.getState().getValue(BlockLeaves.DECAYABLE)) {
			//Oak and dark oak -> apple gall
			event.getDrops().clear();
			event.getDrops().add(new ItemStack(ModObjects.oak_apple_gall, 1));

		}
	}

	@SubscribeEvent
	public void harvestDrops(HarvestDropsEvent event) {
		replaceDrop(event, ModObjects.salt_ore, new ItemStack(ModObjects.salt, getFortuneDropAmount(event.getWorld().rand, event.getFortuneLevel())), 100, true, false);
		replaceDrop(event, ModObjects.amethyst_ore, new ItemStack(ModObjects.amethyst, getFortuneDropAmount(event.getWorld().rand, event.getFortuneLevel())), 100, true, false);
		replaceDrop(event, ModObjects.garnet_ore, new ItemStack(ModObjects.garnet, getFortuneDropAmount(event.getWorld().rand, event.getFortuneLevel())), 100, true, false);
		replaceDrop(event, ModObjects.moonstone_ore, new ItemStack(ModObjects.moonstone, getFortuneDropAmount(event.getWorld().rand, event.getFortuneLevel())), 100, true, false);

		replaceDrop(event, ModObjects.leaves_juniper, new ItemStack(ModObjects.juniper_berries), 15, false, false);
		replaceDrop(event, ModObjects.leaves_yew, new ItemStack(ModObjects.yew_aril), 15, false, false);

		replaceDrop(event, Blocks.DEADBUSH, new ItemStack(ModObjects.seed_white_sage), 15, false, false);
		replaceDrop(event, Blocks.DEADBUSH, new ItemStack(ModObjects.seed_sagebrush), 15, false, false);
	}
}