package com.bewitchment.common;

import com.bewitchment.registry.ModObjects;
import net.minecraft.block.*;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Joseph on 4/14/2019.
 */
@Mod.EventBusSubscriber
public class HarvestEvents {

	//Todo: Increase drop rates here for seeds.
	@SubscribeEvent
	public static void dropController(BlockEvent.HarvestDropsEvent event) {
		if (event.getState().getBlock() == Blocks.RED_FLOWER && event.getState().getValue(Blocks.RED_FLOWER.getTypeProperty()) == BlockFlower.EnumFlowerType.ALLIUM && event.getWorld().rand.nextInt(6) == 0) {
			//Allium -> garlic seeds
			event.getDrops().clear();
			event.getDrops().add(new ItemStack(ModObjects.seed_garlic, 1));
		} else if ((event.getState().getBlock() == Blocks.DEADBUSH && event.getWorld().rand.nextInt(8) == 0)) {
			//Dead bush -> sage/sagebrush
			event.getDrops().clear();
			if (event.getWorld().rand.nextBoolean()) {
				event.getDrops().add(new ItemStack(ModObjects.seed_sagebrush, 1));
			} else {
				event.getDrops().add(new ItemStack(ModObjects.seed_white_sage, 1));
			}
		} else if (((event.getState().getBlock() == Blocks.LEAVES && event.getState().getValue(BlockOldLeaf.VARIANT) == BlockPlanks.EnumType.OAK) || (event.getState().getBlock() == Blocks.LEAVES2 && event.getState().getValue(BlockNewLeaf.VARIANT) == BlockPlanks.EnumType.DARK_OAK)) && event.getWorld().rand.nextInt(24) == 0 && event.getState().getValue(BlockLeaves.DECAYABLE)) {
			//Oak and dark oak -> apple gall
			event.getDrops().clear();
			event.getDrops().add(new ItemStack(ModObjects.oak_apple_gall, 1));
		}
	}
}