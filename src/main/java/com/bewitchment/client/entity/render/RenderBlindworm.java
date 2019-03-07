package com.bewitchment.client.entity.render;

import com.bewitchment.Bewitchment;
import com.bewitchment.client.entity.model.ModelBlindworm;
import com.bewitchment.common.entity.EntityBlindworm;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBlindworm extends RenderLiving<EntityBlindworm>
{
	private static final ResourceLocation TEX = new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/blindworm.png");
	
	public RenderBlindworm(RenderManager manager)
	{
		super(manager, new ModelBlindworm(), 0.1f);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityBlindworm entity)
	{
		return TEX;
	}
	
	@Override
	protected void preRenderCallback(EntityBlindworm entity, float partialTickTime)
	{
		super.preRenderCallback(entity, partialTickTime);
		if (entity.isChild()) GlStateManager.scale(0.4, 0.4, 0.4);
		else GlStateManager.scale(0.6, 0.6, 0.6);
	}
}