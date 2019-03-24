package com.bewitchment.client.render.entity.misc;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.entity.misc.EntityBeeSwarm;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBeeSwarm extends Render<EntityBeeSwarm>
{
	private static final ResourceLocation TEX = new ResourceLocation(Bewitchment.MOD_ID, "textures/blocks/nothing.png");
	
	public RenderBeeSwarm(RenderManager manager)
	{
		super(manager);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityBeeSwarm entity)
	{
		return TEX;
	}
}