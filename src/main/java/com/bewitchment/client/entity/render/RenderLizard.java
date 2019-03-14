package com.bewitchment.client.entity.render;

import com.bewitchment.Bewitchment;
import com.bewitchment.client.entity.model.ModelLizard;
import com.bewitchment.common.entity.EntityLizard;
import com.bewitchment.common.entity.util.ModEntityTameable;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderLizard extends RenderLiving<EntityLizard>
{
	private static final ResourceLocation[] TEX = {
		new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/lizard_0.png"),
		new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/lizard_1.png"),
		new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/lizard_2.png"),
		new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/lizard_3.png")};
	
	public RenderLizard(RenderManager manager)
	{
		super(manager, new ModelLizard(), 0.1f);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityLizard entity)
	{
		return TEX[entity.getDataManager().get(ModEntityTameable.SKIN)];
	}
	
	@Override
	protected void preRenderCallback(EntityLizard entity, float partialTickTime)
	{
		super.preRenderCallback(entity, partialTickTime);
		if (entity.isChild()) GlStateManager.scale(0.4, 0.4, 0.4);
		else GlStateManager.scale(0.6, 0.6, 0.6);
	}
}