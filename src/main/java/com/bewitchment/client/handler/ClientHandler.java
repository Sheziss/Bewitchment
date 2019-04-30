package com.bewitchment.client.handler;

import com.bewitchment.common.item.ItemLantern;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientHandler {
	@SubscribeEvent
	public void renderPlayerPre(RenderPlayerEvent.Pre event) {
		if (event.getEntityPlayer().getHeldItemMainhand().getItem() instanceof ItemLantern || event.getEntityPlayer().getHeldItemOffhand().getItem() instanceof ItemLantern) {
			event.getEntityPlayer().swingProgress = 0.18f;
			event.getEntityPlayer().swingingHand = event.getEntityPlayer().getHeldItemMainhand().getItem() instanceof ItemLantern ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
		}
	}
}