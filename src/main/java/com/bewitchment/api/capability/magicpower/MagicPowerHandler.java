package com.bewitchment.api.capability.magicpower;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.block.tile.entity.TileEntityDistillery;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MagicPowerHandler
{
	public static final ResourceLocation CAP = new ResourceLocation(Bewitchment.MOD_ID, "magic_power");
	
	@SubscribeEvent
	public void attachCapabilityE(AttachCapabilitiesEvent<Entity> event)
	{
		if (event.getObject() instanceof EntityPlayer) event.addCapability(CAP, new MagicPowerProvider());
	}
	
	@SubscribeEvent
	public void attachCapabilityTE(AttachCapabilitiesEvent<TileEntity> event)
	{
		if (event.getObject() instanceof TileEntityDistillery) event.addCapability(CAP, new MagicPowerProvider());
	}
	
	@SubscribeEvent
	public void livingTick(LivingEvent.LivingUpdateEvent event)
	{
		if (event.getEntityLiving() instanceof EntityPlayer)
		{
			MagicPowerCapability cap = event.getEntityLiving().getCapability(MagicPowerProvider.CAPABILITY, null);
			if (cap.getMaxAmount() <= 0)
			{
				cap.setAmount(800);
				cap.setMaxAmount(800);
				cap.setBonusAmount(0);
			}
		}
	}
	
	@SubscribeEvent
	public void clonePlayer(PlayerEvent.Clone event)
	{
		MagicPowerCapability oldC = event.getOriginal().getCapability(MagicPowerProvider.CAPABILITY, null), newC = event.getEntityPlayer().getCapability(MagicPowerProvider.CAPABILITY, null);
		newC.setAmount(oldC.getAmount());
		newC.setMaxAmount(oldC.getMaxAmount());
		newC.setBonusAmount(oldC.getBonusAmount());
	}
}