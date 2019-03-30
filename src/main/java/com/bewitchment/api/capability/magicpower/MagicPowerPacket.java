package com.bewitchment.api.capability.magicpower;

import com.bewitchment.common.CommonProxy;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MagicPowerPacket implements IMessage
{
	public int index;
	
	public MagicPowerPacket(int index)
	{
		this.index = index;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		index = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(index);
	}
	
	public class Handler implements IMessageHandler<MagicPowerPacket, IMessage>
	{
		@Override
		public IMessage onMessage(MagicPowerPacket message, MessageContext ctx)
		{
			if (ctx.side.isServer())
			{
				ctx.getServerHandler().player.getServer().addScheduledTask(new Runnable()
				{
					@Override
					public void run()
					{
						CommonProxy.WRAPPER.sendTo(message, ctx.getServerHandler().player);
					}
				});
			}
			return null;
		}
	}
}