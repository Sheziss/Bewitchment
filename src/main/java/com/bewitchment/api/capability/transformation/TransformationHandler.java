package com.bewitchment.api.capability.transformation;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.capability.transformation.TransformationCapability.Transformation;
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
	public static final ResourceLocation CAP_TRANSFORMATION = new ResourceLocation(Bewitchment.MOD_ID, "transformation");
	
	@SubscribeEvent
	public void attachCapabilityE(AttachCapabilitiesEvent<Entity> event)
	{
		if (event.getObject() instanceof EntityPlayer) event.addCapability(CAP_TRANSFORMATION, new TransformationProvider());
	}
	
	@SubscribeEvent
	public void clonePlayer(PlayerEvent.Clone event)
	{
		event.getEntityPlayer().getCapability(TransformationProvider.TRANSFORMATION, null).setTransformation(event.getOriginal().getCapability(TransformationProvider.TRANSFORMATION, null).getTransformation());
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void breakBlock(BlockEvent.BreakEvent event)
	{
		if (!event.getPlayer().getCapability(TransformationProvider.TRANSFORMATION, null).getTransformation().canCrossSalt && event.getState().getBlock() == ModObjects.salt_barrier || event.getPlayer().world.getBlockState(event.getPos().up()).getBlock() == ModObjects.salt_barrier) event.setCanceled(true);
	}
	
	@SubscribeEvent
	public void livingTick(LivingEvent.LivingUpdateEvent event)
	{
		if (event.getEntityLiving() instanceof EntityPlayer && ((EntityPlayer) event.getEntityLiving()).getCapability(TransformationProvider.TRANSFORMATION, null).getTransformation() == null) event.getEntityLiving().getCapability(TransformationProvider.TRANSFORMATION, null).setTransformation(Transformation.NONE);
	}
}