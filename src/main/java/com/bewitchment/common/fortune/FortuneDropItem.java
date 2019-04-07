package com.bewitchment.common.fortune;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.capability.extendedplayer.ExtendedPlayer;
import com.bewitchment.api.registry.Fortune;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FortuneDropItem extends Fortune {
	public FortuneDropItem() {
		super(Bewitchment.MOD_ID, "drop_item", 7);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public boolean apply(EntityPlayer player) {
		player.getCapability(ExtendedPlayer.CAPABILITY, null).setFortuneActive(true);
		return true;
	}

	@Override
	public boolean canBeUsed(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean isInstant(EntityPlayer player) {
		return player.getRNG().nextDouble() < 0.0005;
	}

	@Override
	public boolean isNegative() {
		return true;
	}

	@SubscribeEvent
	public void rightClick(RightClickItem event) {
		if (event.getEntityPlayer() != null) {
			ExtendedPlayer cap = event.getEntityPlayer().getCapability(ExtendedPlayer.CAPABILITY, null);
			if (cap.getFortune() == this && cap.isFortuneActive() && event.getEntityPlayer().dropItem(true) != null)
				cap.setFortuneRemoveable(true);
		}
	}
}