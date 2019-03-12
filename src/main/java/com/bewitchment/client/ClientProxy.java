package com.bewitchment.client;

import com.bewitchment.client.block.tile.render.RenderTileEntityPlacedItem;
import com.bewitchment.client.entity.render.RenderBlindworm;
import com.bewitchment.client.entity.render.RenderBroom;
import com.bewitchment.client.entity.render.RenderLizard;
import com.bewitchment.client.entity.render.RenderNewt;
import com.bewitchment.client.entity.render.RenderOwl;
import com.bewitchment.client.entity.render.RenderSnake;
import com.bewitchment.common.CommonProxy;
import com.bewitchment.common.block.tile.entity.TileEntityPlacedItem;
import com.bewitchment.common.entity.EntityBlindworm;
import com.bewitchment.common.entity.EntityLizard;
import com.bewitchment.common.entity.EntityNewt;
import com.bewitchment.common.entity.EntityOwl;
import com.bewitchment.common.entity.EntitySnake;
import com.bewitchment.common.entity.misc.EntityBroom;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
		RenderingRegistry.registerEntityRenderingHandler(EntityBlindworm.class, RenderBlindworm::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityLizard.class, RenderLizard::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityNewt.class, RenderNewt::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityOwl.class, RenderOwl::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySnake.class, RenderSnake::new);
		
		RenderingRegistry.registerEntityRenderingHandler(EntityBroom.class, RenderBroom::new);
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlacedItem.class, new RenderTileEntityPlacedItem());
	}
}