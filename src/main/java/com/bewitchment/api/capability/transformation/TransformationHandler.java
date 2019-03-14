package com.bewitchment.api.capability.transformation;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.capability.transformation.Transformation.TransformationType;
import com.bewitchment.registry.ModObjects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TransformationHandler
{
	private static final ResourceLocation CAP = new ResourceLocation(Bewitchment.MOD_ID, "transformation");
	
	@SubscribeEvent
	public void attachCapabilityE(AttachCapabilitiesEvent<Entity> event)
	{
		if (event.getObject() instanceof EntityPlayer) event.addCapability(CAP, new Transformation());
	}
	
	@SubscribeEvent
	public void clonePlayer(PlayerEvent.Clone event)
	{
		event.getEntityPlayer().getCapability(Transformation.CAPABILITY, null).deserializeNBT(event.getOriginal().getCapability(Transformation.CAPABILITY, null).serializeNBT());
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void breakBlock(BlockEvent.BreakEvent event)
	{
		if (!event.getPlayer().getCapability(Transformation.CAPABILITY, null).getTransformation().canCrossSalt && event.getState().getBlock() == ModObjects.salt_barrier || event.getPlayer().world.getBlockState(event.getPos().up()).getBlock() == ModObjects.salt_barrier) event.setCanceled(true);
	}
	
	@SubscribeEvent	
	public void livingTick(LivingEvent.LivingUpdateEvent event)
	{
		if (event.getEntityLiving() instanceof EntityPlayer && ((EntityPlayer) event.getEntityLiving()).getCapability(Transformation.CAPABILITY, null).getTransformation() == null) event.getEntityLiving().getCapability(Transformation.CAPABILITY, null).setTransformation(TransformationType.NONE);
	}
}