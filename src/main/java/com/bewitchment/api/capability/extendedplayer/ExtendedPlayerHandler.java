package com.bewitchment.api.capability.extendedplayer;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Fortune;
import com.bewitchment.registry.ModObjects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class ExtendedPlayerHandler {
	private static final ResourceLocation CAP = new ResourceLocation(Bewitchment.MODID, "extendedPlayer");

	@SubscribeEvent
	public void attachCapabilityE(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof EntityPlayer) event.addCapability(CAP, new ExtendedPlayer());
	}

	@SubscribeEvent
	public void clonePlayer(PlayerEvent.Clone event) {
		event.getEntityPlayer().getCapability(ExtendedPlayer.CAPABILITY, null).deserializeNBT(event.getOriginal().getCapability(ExtendedPlayer.CAPABILITY, null).serializeNBT());
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void breakBlock(BlockEvent.BreakEvent event) {
		if (!event.getPlayer().getCapability(ExtendedPlayer.CAPABILITY, null).getTransformation().canCrossSalt && (event.getState().getBlock() == ModObjects.salt_barrier || event.getPlayer().world.getBlockState(event.getPos().up()).getBlock() == ModObjects.salt_barrier))
			event.setCanceled(true);
	}

	@SubscribeEvent
	public void playerTick(PlayerTickEvent event) {
		if (!event.player.world.isRemote && event.phase == Phase.END) {
			ExtendedPlayer cap = event.player.getCapability(ExtendedPlayer.CAPABILITY, null);
			Fortune fortune = cap.getFortune();
			if (fortune != null) {
				if (cap.isFortuneRemoveable()) cap.setFortune(null);
				else {
					if (fortune.isInstant(event.player)) cap.setFortuneActive(true);
					if (cap.isFortuneActive() && fortune.apply(event.player)) cap.setFortune(null);
				}
			}
		}
	}
}