package com.bewitchment.api.capability.magicpower;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MagicPowerMessage implements IMessage
{
	public int amount, maxAmount, bonusAmount;
	
	public MagicPowerMessage()
	{
	}
	
	public MagicPowerMessage(int amount, int maxAmount, int bonusAmount)
	{
		this.amount = amount;
		this.maxAmount = maxAmount;
		this.bonusAmount = bonusAmount;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		amount = buf.readInt();
		maxAmount = buf.readInt();
		bonusAmount = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(amount);
		buf.writeInt(maxAmount);
		buf.writeInt(bonusAmount);
	}
	
	public static class Handler implements IMessageHandler<MagicPowerMessage, IMessage>
	{
		@Override
		public IMessage onMessage(MagicPowerMessage message, MessageContext ctx)
		{
			if (ctx.side.isClient())
			{
				Minecraft.getMinecraft().addScheduledTask(() ->
				{
					MagicPower cap = Minecraft.getMinecraft().player.getCapability(MagicPower.CAPABILITY, null);
					cap.setAmount(message.amount);
					cap.setMaxAmount(message.maxAmount);
					cap.setBonusAmount(message.bonusAmount);
				});
			}
			return null;
		}
	}
}