package com.bewitchment.client.entity.model;

import com.bewitchment.common.entity.EntityBlindworm;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBlindworm extends ModelBase
{
	public ModelRenderer neck01a;
	public ModelRenderer neck01b;
	public ModelRenderer neck02;
	public ModelRenderer body;
	public ModelRenderer neck03;
	public ModelRenderer head;
	public ModelRenderer snout;
	public ModelRenderer lowerJaw;
	public ModelRenderer lEye;
	public ModelRenderer rEye;
	public ModelRenderer headB;
	public ModelRenderer tail0a;
	public ModelRenderer tail01b;
	public ModelRenderer tail02;
	public ModelRenderer tail03;
	
	public ModelBlindworm()
	{
		textureWidth = 64;
		textureHeight = 65;
		neck02 = new ModelRenderer(this, 0, 16);
		neck02.setRotationPoint(0, 0, -5.9f);
		neck02.addBox(-2, -1.5f, -3.9f, 4, 3, 4, 0);
		rEye = new ModelRenderer(this, 32, 0);
		rEye.mirror = true;
		rEye.setRotationPoint(-1.3f, -0.2f, 0);
		rEye.addBox(-1.5f, -2, -1, 2, 2, 2, 0);
		setRotateAngle(rEye, 0, -0.12217304763960307f, 0.19198621771937624f);
		tail02 = new ModelRenderer(this, 41, 40);
		tail02.setRotationPoint(0, 0.5f, 6.7f);
		tail02.addBox(-1, -1, -1, 2, 2, 7, 0);
		neck01b = new ModelRenderer(this, 25, 28);
		neck01b.setRotationPoint(0, 0, 0);
		neck01b.addBox(-2.2f, -1.49f, -6, 1, 3, 9, 0);
		headB = new ModelRenderer(this, 19, 0);
		headB.setRotationPoint(0, 0, 0);
		headB.addBox(-2.3f, -2, -2.5f, 1, 3, 5, 0);
		head = new ModelRenderer(this, 0, 0);
		head.setRotationPoint(0, 0.3f, -3.9f);
		head.addBox(-1.6f, -2, -2.5f, 4, 3, 5, 0);
		setRotateAngle(head, 0.13962634015954636f, 0, 0);
		snout = new ModelRenderer(this, 0, 10);
		snout.setRotationPoint(0, -0.8f, -1.9f);
		snout.addBox(-2, -1, -2.2f, 4, 2, 2, 0);
		setRotateAngle(snout, 0.08726646259971647f, 0, 0);
		tail0a = new ModelRenderer(this, 23, 45);
		tail0a.setRotationPoint(0, 0, 5.9f);
		tail0a.addBox(-1.5f, -1.1f, -1, 3, 2, 7, 0);
		neck03 = new ModelRenderer(this, 0, 24);
		neck03.setRotationPoint(0, 0, -3.6f);
		neck03.addBox(-2, -1.5f, -3.2f, 4, 3, 3, 0);
		lowerJaw = new ModelRenderer(this, 15, 8);
		lowerJaw.setRotationPoint(0, 0.9f, 1);
		lowerJaw.addBox(-2, -0.5f, -4.8f, 4, 1, 5, 0);
		setRotateAngle(lowerJaw, -0.08726646259971647f, 0, 0);
		lEye = new ModelRenderer(this, 32, 0);
		lEye.setRotationPoint(1.3f, -0.2f, 0);
		lEye.addBox(-0.5f, -2, -1, 2, 2, 2, 0);
		setRotateAngle(lEye, 0, 0.12217304763960307f, -0.19198621771937624f);
		body = new ModelRenderer(this, 0, 33);
		body.setRotationPoint(0, 0, 3.4f);
		body.addBox(-2, -1.49f, -0.5f, 4, 3, 7, 0);
		tail01b = new ModelRenderer(this, 22, 55);
		tail01b.setRotationPoint(0, 0.5f, 0);
		tail01b.addBox(-1.5f, 0, -1, 3, 1, 7, 0);
		tail03 = new ModelRenderer(this, 43, 54);
		tail03.setRotationPoint(0, 0.5f, 5.9f);
		tail03.addBox(-0.5f, -0.5f, -0.5f, 1, 1, 6, 0);
		neck01a = new ModelRenderer(this, 25, 15);
		neck01a.setRotationPoint(0, 22.5f, 1.9f);
		neck01a.addBox(-1.8f, -1.49f, -6, 4, 3, 9, 0);
		neck01a.addChild(neck02);
		head.addChild(rEye);
		tail0a.addChild(tail02);
		neck01a.addChild(neck01b);
		head.addChild(headB);
		neck03.addChild(head);
		head.addChild(snout);
		body.addChild(tail0a);
		neck02.addChild(neck03);
		head.addChild(lowerJaw);
		head.addChild(lEye);
		neck01a.addChild(body);
		tail0a.addChild(tail01b);
		tail02.addChild(tail03);
	}
	
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale)
	{
		float pticks = Minecraft.getMinecraft().getRenderPartialTicks();
		float time = (entity.ticksExisted + pticks) * 0.2f;
		float angle = 0.34906585039f;
		EntityBlindworm blindworm = (EntityBlindworm) entity;
		if (blindworm.motionX != 0 || blindworm.motionZ != 0)
		{
			neck01a.offsetX = 0.3f * MathHelper.cos(time);
			neck01a.rotateAngleY = angle * MathHelper.sin(time);
			neck02.rotateAngleY = angle * MathHelper.sin(time - 5);
			neck02.rotateAngleX = 0;
			body.rotateAngleY = angle * MathHelper.sin(time + 5);
			tail01b.rotateAngleY = angle * MathHelper.sin(time + 11);
			tail02.rotateAngleY = angle * MathHelper.sin(time + 4);
			tail03.rotateAngleY = angle * MathHelper.sin(time + 2);
			head.rotateAngleY = neck02.rotateAngleY;
			head.rotateAngleZ = 0.174532925f * MathHelper.cos(time - 5);
			head.rotateAngleX = 0;
			head.rotateAngleZ = 0;
		}
		else
		{
			// TODO: This is the cause of the clipping. Additionally, boxes probably
			// shouldn't be added during render() ... may be a memory leak.
			// neck01b.addBox(-2.3f, -1.49f, -6, 2, 3, 8, MathHelper.sin(time));
		}
		neck01a.render(scale);
		head.rotateAngleY = 0.001f * MathHelper.sin(time);
	}
	
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}