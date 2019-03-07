package com.bewitchment.common.handler;

import java.util.Random;

import com.bewitchment.common.registry.ModBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class EventHandler
{
	@SubscribeEvent
	public void playerTick(PlayerTickEvent event)
	{
		if (event.side == Side.SERVER && event.phase == Phase.START)
		{
			World world = event.player.world;
			if (world.getTotalWorldTime() % 20 == 0 && BiomeDictionary.hasType(world.getBiome(event.player.getPosition()), Type.FOREST))
			{
				Random rand = event.player.getRNG();
				if (world.provider.getDimension() == 0 && world.provider.getMoonPhase(world.getWorldTime()) == 4 && !world.isDaytime() && event.player.getRNG().nextDouble() < 0.2)
				{
					MutableBlockPos pos = new MutableBlockPos(event.player.getPosition().add((rand.nextInt(7) - 3) * 10, 0, (rand.nextInt(7) - 3) * 10));
					int y = pos.getY();
					for (int i = -5; i <= 5; i++)
					{
						pos.setY(y + i);
						if ((world.isAirBlock(pos) || world.getBlockState(pos).getBlock().isReplaceable(world, pos)) && world.getBlockState(pos.down()).getBlock() == Blocks.DIRT) world.setBlockState(pos, ModBlocks.moonbell.getDefaultState());
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void livingUpdate(LivingEvent.LivingUpdateEvent event)
	{
		if (event.getEntityLiving().world.getBlockState(event.getEntityLiving().getPosition()).getBlock() == ModBlocks.honey.getBlock())
		{
			event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 60));
		}
	}
}