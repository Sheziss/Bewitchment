package com.bewitchment.api.capability.magicpower;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.CommonProxy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MagicPowerHandler
{
	private static final ResourceLocation CAP = new ResourceLocation(Bewitchment.MOD_ID, "magicPower");
	
	@SubscribeEvent
	public void attachCapabilityE(AttachCapabilitiesEvent<Entity> event)
	{
		if (event.getObject() instanceof EntityPlayer) event.addCapability(CAP, new MagicPower());
	}
	
	@SubscribeEvent
	public void clonePlayer(PlayerEvent.Clone event)
	{
		event.getEntityPlayer().getCapability(MagicPower.CAPABILITY, null).deserializeNBT(event.getOriginal().getCapability(MagicPower.CAPABILITY, null).serializeNBT());
	}
	
	@SubscribeEvent
	public void livingTick(LivingEvent.LivingUpdateEvent event)
	{
		if (!event.getEntityLiving().world.isRemote && event.getEntityLiving().world.getTotalWorldTime() % 20 == 0 && event.getEntityLiving() instanceof EntityPlayer)
		{
			MagicPower cap = event.getEntityLiving().getCapability(MagicPower.CAPABILITY, null);
			if (cap.getMaxAmount() <= 0)
			{
				cap.setAmount(800);
				cap.setMaxAmount(800);
				cap.setBonusAmount(0);
			}
			CommonProxy.WRAPPER.sendTo(new MagicPowerMessage(cap.getAmount(), cap.getMaxAmount(), cap.getBonusAmount()), (EntityPlayerMP) event.getEntityLiving());
		}
	}
}