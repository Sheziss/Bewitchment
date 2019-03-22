package com.bewitchment.client.entity.render.hostile;

import com.bewitchment.Bewitchment;
import com.bewitchment.client.entity.model.hostile.ModelBlackDog;
import com.bewitchment.common.entity.hostile.EntityBlackDog;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBlackDog extends RenderLiving<EntityBlackDog>
{
	private static final ResourceLocation[] TEX = {
		new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/black_dog_0.png"),
		new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/black_dog_1.png"),
		new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/black_dog_2.png"),
		new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/black_dog_3.png"),
		new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/black_dog_4.png")};
	
	public RenderBlackDog(RenderManager manager)
	{
		super(manager, new ModelBlackDog(), 0.3f);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityBlackDog entity)
	{
		return TEX[entity.getDataManager().get(EntityBlackDog.SKIN)];
	}
	
	@Override
	protected void preRenderCallback(EntityBlackDog entity, float partialTickTime)
	{
		super.preRenderCallback(entity, partialTickTime);
		GlStateManager.scale(1.8, 1.8, 1.8);
	}
}