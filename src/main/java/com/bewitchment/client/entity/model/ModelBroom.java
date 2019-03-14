package com.bewitchment.client.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBroom extends ModelBase
{
	public ModelRenderer handle;
	public ModelRenderer twigs_bound;
	public ModelRenderer twigs;
	float time1;
	float time2;
	
	public ModelBroom()
	{
		textureWidth = 64;
		textureHeight = 32;
		handle = new ModelRenderer(this, 0, 0);
		handle.setRotationPoint(-13, 16, 0);
		handle.addBox(0, 0, 0, 16, 1, 1, 0.4f);
		twigs_bound = new ModelRenderer(this, 0, 2);
		twigs_bound.setRotationPoint(17, -1, -1);
		twigs_bound.addBox(0, 0, 0, 1, 3, 3, 0.5f);
		twigs = new ModelRenderer(this, 39, 0);
		twigs.setRotationPoint(18f, -2, -2);
		twigs.addBox(0, 0, 0, 6, 5, 5, 0.5f);
		
		handle.addChild(twigs_bound);
		handle.addChild(twigs);
	}

	@Override
	public void render(Entity entity, float limbSwingAmount, float f1, float f2, float f3, float f4, float f5)
	{
		GlStateManager.pushMatrix();
		GlStateManager.translate(handle.offsetX, handle.offsetY, handle.offsetZ);
		GlStateManager.translate(handle.rotationPointX * f5, handle.rotationPointY * f5, handle.rotationPointZ * f5);
		GlStateManager.scale(1.2d, 1, 1);
		GlStateManager.translate(-handle.offsetX, -handle.offsetY, -handle.offsetZ);
		GlStateManager.translate(-handle.rotationPointX * f5, -handle.rotationPointY * f5, -handle.rotationPointZ * f5);
		handle.render(f5);
		GlStateManager.popMatrix();
		time1 = MathHelper.sin(entity.ticksExisted * 0.10471975512f);
		time2 = MathHelper.cos(entity.ticksExisted * 0.10471975512f);
		if (entity.getPassengers().isEmpty())
		{
			handle.setRotationPoint(-13 + 0.2f * time1, 16f + 0.3f * time1, 0.2f * time2);
			handle.rotateAngleX = time1 * 0.05235987755f - limbSwingAmount * time1 * 0.17453292516f;
			handle.rotateAngleY = time2 * 0.05235987755f - limbSwingAmount * time1 * 0.17453292516f;
		}
		else
		{
			handle.setRotationPoint(-13 + 0.02f * time1, 16f + 0.03f * time1, 0.02f * time2);
			handle.rotateAngleX = time1 * 0.01745329251f;
			handle.rotateAngleY = time2 * 0.01745329251f;
		}
	}
}