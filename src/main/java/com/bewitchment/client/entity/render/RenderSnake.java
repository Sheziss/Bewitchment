package com.bewitchment.client.entity.render;

import com.bewitchment.Bewitchment;
import com.bewitchment.client.entity.model.ModelSnake;
import com.bewitchment.common.entity.EntitySnake;
import com.bewitchment.common.entity.util.FLEntityTameable;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSnake extends RenderLiving<EntitySnake>
{
	private static final ResourceLocation[] TEX = {
		new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/snake_0.png"),
		new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/snake_1.png"),
		new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/snake_2.png"),
		new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/snake_3.png"),
		new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/snake_4.png"),
		new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/snake_5.png")};
	
	public RenderSnake(RenderManager manager)
	{
		super(manager, new ModelSnake(), 0.3f);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntitySnake entity)
	{
		return TEX[entity.getDataManager().get(FLEntityTameable.SKIN)];
	}
	
	@Override
	protected void preRenderCallback(EntitySnake entity, float partialTickTime)
	{
		super.preRenderCallback(entity, partialTickTime);
		if (entity.isChild()) GlStateManager.scale(0.4, 0.4, 0.4);
		else GlStateManager.scale(0.6, 0.6, 0.6);
	}
}