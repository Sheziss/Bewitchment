package com.bewitchment.common.handler;

import java.util.Random;

import com.bewitchment.common.entity.misc.EntityBroom;
import com.bewitchment.common.item.ItemBroom;
import com.bewitchment.registry.ModObjects;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class EventHandler 
{
	@SubscribeEvent
	public void livingUpdate(LivingEvent.LivingUpdateEvent event)
	{
		if (event.getEntityLiving().world.getBlockState(event.getEntityLiving().getPosition()).getBlock() == ModObjects.honey.getBlock()) event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 60));
	}
	
	@SubscribeEvent
	public void playerTick(PlayerTickEvent event)
	{
		if (event.side == Side.SERVER && event.phase == Phase.START)
		{
			World world = event.player.world;
			if (world.getTotalWorldTime() % 20 == 0 && BiomeDictionary.hasType(world.getBiome(event.player.getPosition()), Type.FOREST))
			{
				Random rand = event.player.getRNG();
				if (world.provider.getDimension() == 0 && world.provider.getMoonPhase(world.getWorldTime()) == 4 && !world.isDaytime() && rand.nextDouble() < 0.2)
				{
					MutableBlockPos pos = new MutableBlockPos(event.player.getPosition().add((rand.nextInt(7) - 3) * 10, 0, (rand.nextInt(7) - 3) * 10));
					int y = pos.getY();
					for (int i = -5; i <= 5; i++)
					{
						pos.setY(y + i);
						if ((world.isAirBlock(pos) || world.getBlockState(pos).getBlock().isReplaceable(world, pos)) && world.getBlockState(pos.down()).getBlock() == Blocks.DIRT) world.setBlockState(pos, ModObjects.moonbell.getDefaultState());
					}
				}
			}
		}
	}
	
	@SubscribeEvent(receiveCanceled = false, priority = EventPriority.LOWEST)
	public void unmount(EntityMountEvent event)
	{
		if (event.getEntityBeingMounted() instanceof EntityBroom && event.isDismounting())
		{
			EntityBroom broom = (EntityBroom) event.getEntityBeingMounted();
			EntityPlayer source = (EntityPlayer) event.getEntityMounting();
			if (!source.isDead)
			{
				if (broom.item != null) ((ItemBroom) broom.item.getItem()).onDismount(broom, source);
				if (!broom.world.isRemote)
				{
					EntityItem entity = new EntityItem(broom.world, source.posX, source.posY, source.posZ, broom.item);
					broom.world.spawnEntity(entity);
					broom.setDead();
					entity.onCollideWithPlayer(source);
				}
			}
		}
	}
}