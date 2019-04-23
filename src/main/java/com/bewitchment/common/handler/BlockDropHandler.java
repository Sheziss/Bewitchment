package com.bewitchment.common.handler;

import java.util.Random;
import java.util.function.Predicate;

import com.bewitchment.registry.ModObjects;

import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockDropHandler {
	private static int getFortuneDropAmount(Random rand, int fortuneLevel) {
		return fortuneLevel > 0 ? Math.max(0, rand.nextInt(fortuneLevel + 2)) : 1;
	}

	private static void replaceDrop(HarvestDropsEvent event, Predicate<IBlockState> predicate, ItemStack out, int chance, boolean replaceAlways, boolean replaceIfTest, boolean ignoreSilkTouch) {
		boolean test = predicate.test(event.getState());
		if (test && (ignoreSilkTouch ? true : !event.isSilkTouching())) {
			if (replaceAlways || (replaceIfTest && test)) event.getDrops().clear();
			if (event.getWorld().rand.nextInt(100) <= chance) event.getDrops().add(out);
		}
	}

	@SubscribeEvent
	public void harvestDrops(HarvestDropsEvent event) {
		replaceDrop(event, s -> s.getBlock() == ModObjects.salt_ore, new ItemStack(ModObjects.salt, getFortuneDropAmount(event.getWorld().rand, event.getFortuneLevel())), 100, true, false, false);
		replaceDrop(event, s -> s.getBlock() == ModObjects.amethyst_ore, new ItemStack(ModObjects.amethyst, getFortuneDropAmount(event.getWorld().rand, event.getFortuneLevel())), 100, true, false, false);
		replaceDrop(event, s -> s.getBlock() == ModObjects.garnet_ore, new ItemStack(ModObjects.garnet, getFortuneDropAmount(event.getWorld().rand, event.getFortuneLevel())), 100, true, false, false);
		replaceDrop(event, s -> s.getBlock() == ModObjects.moonstone_ore, new ItemStack(ModObjects.moonstone, getFortuneDropAmount(event.getWorld().rand, event.getFortuneLevel())), 100, true, false, false);

		replaceDrop(event, s -> s.getBlock() == ModObjects.leaves_cypress, new ItemStack(ModObjects.sapling_cypress), 5, true, false, false);
		replaceDrop(event, s -> s.getBlock() == ModObjects.leaves_elder, new ItemStack(ModObjects.sapling_elder), 5, true, false, false);
		replaceDrop(event, s -> s.getBlock() == ModObjects.leaves_juniper, new ItemStack(ModObjects.sapling_juniper), 5, true, false, false);
		replaceDrop(event, s -> s.getBlock() == ModObjects.leaves_yew, new ItemStack(ModObjects.sapling_yew), 5, true, false, false);
		
		replaceDrop(event, s -> s.getBlock() == ModObjects.leaves_juniper, new ItemStack(ModObjects.juniper_berries), 1, false, false, false);
		replaceDrop(event, s -> s.getBlock() == ModObjects.leaves_yew, new ItemStack(ModObjects.yew_aril), 1, false, false, false);

		replaceDrop(event, s -> s.getBlock() == Blocks.DEADBUSH, new ItemStack(ModObjects.seed_white_sage), 15, false, true, false);
		replaceDrop(event, s -> s.getBlock() == Blocks.RED_FLOWER && s.getValue(Blocks.RED_FLOWER.getTypeProperty()) == EnumFlowerType.ALLIUM, new ItemStack(ModObjects.seed_garlic), 15, false, true, false);
		replaceDrop(event, s -> (s.getBlock() == Blocks.LEAVES && s.getValue(BlockOldLeaf.VARIANT) == EnumType.OAK) || (s.getBlock() == Blocks.LEAVES2 && s.getValue(BlockNewLeaf.VARIANT) == EnumType.DARK_OAK), new ItemStack(ModObjects.oak_apple_gall), 5, false, true, false);
	}
}