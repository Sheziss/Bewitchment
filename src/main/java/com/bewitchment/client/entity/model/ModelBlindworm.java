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
		this.textureWidth = 64;
		this.textureHeight = 65;
		this.neck02 = new ModelRenderer(this, 0, 16);
		this.neck02.setRotationPoint(0.0f, 0.0f, -5.9f);
		this.neck02.addBox(-2.0f, -1.5f, -3.9f, 4, 3, 4, 0.0f);
		this.rEye = new ModelRenderer(this, 32, 0);
		this.rEye.mirror = true;
		this.rEye.setRotationPoint(-1.3f, -0.2f, 0.0f);
		this.rEye.addBox(-1.5f, -2.0f, -1.0f, 2, 2, 2, 0.0f);
		this.setRotateAngle(rEye, 0.0f, -0.12217304763960307f, 0.19198621771937624f);
		this.tail02 = new ModelRenderer(this, 41, 40);
		this.tail02.setRotationPoint(0.0f, 0.5f, 6.7f);
		this.tail02.addBox(-1.0f, -1.0f, -1.0f, 2, 2, 7, 0.0f);
		this.neck01b = new ModelRenderer(this, 25, 28);
		this.neck01b.setRotationPoint(0.0f, 0.0f, 0.0f);
		this.neck01b.addBox(-2.2f, -1.49f, -6.0f, 1, 3, 9, 0.0f);
		this.headB = new ModelRenderer(this, 19, 0);
		this.headB.setRotationPoint(0.0f, 0.0f, 0.0f);
		this.headB.addBox(-2.3f, -2.0f, -2.5f, 1, 3, 5, 0.0f);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0f, 0.3f, -3.9f);
		this.head.addBox(-1.6f, -2.0f, -2.5f, 4, 3, 5, 0.0f);
		this.setRotateAngle(head, 0.13962634015954636f, 0.0f, 0.0f);
		this.snout = new ModelRenderer(this, 0, 10);
		this.snout.setRotationPoint(0.0f, -0.8f, -1.9f);
		this.snout.addBox(-2.0f, -1.0f, -2.2f, 4, 2, 2, 0.0f);
		this.setRotateAngle(snout, 0.08726646259971647f, 0.0f, 0.0f);
		this.tail0a = new ModelRenderer(this, 23, 45);
		this.tail0a.setRotationPoint(0.0f, 0.0f, 5.9f);
		this.tail0a.addBox(-1.5f, -1.1f, -1.0f, 3, 2, 7, 0.0f);
		this.neck03 = new ModelRenderer(this, 0, 24);
		this.neck03.setRotationPoint(0.0f, 0.0f, -3.6f);
		this.neck03.addBox(-2.0f, -1.5f, -3.2f, 4, 3, 3, 0.0f);
		this.lowerJaw = new ModelRenderer(this, 15, 8);
		this.lowerJaw.setRotationPoint(0.0f, 0.9f, 1.0f);
		this.lowerJaw.addBox(-2.0f, -0.5f, -4.8f, 4, 1, 5, 0.0f);
		this.setRotateAngle(lowerJaw, -0.08726646259971647f, 0.0f, 0.0f);
		this.lEye = new ModelRenderer(this, 32, 0);
		this.lEye.setRotationPoint(1.3f, -0.2f, 0.0f);
		this.lEye.addBox(-0.5f, -2.0f, -1.0f, 2, 2, 2, 0.0f);
		this.setRotateAngle(lEye, 0.0f, 0.12217304763960307f, -0.19198621771937624f);
		this.body = new ModelRenderer(this, 0, 33);
		this.body.setRotationPoint(0.0f, 0.0f, 3.4f);
		this.body.addBox(-2.0f, -1.49f, -0.5f, 4, 3, 7, 0.0f);
		this.tail01b = new ModelRenderer(this, 22, 55);
		this.tail01b.setRotationPoint(0.0f, 0.5f, 0.0f);
		this.tail01b.addBox(-1.5f, 0.0f, -1.0f, 3, 1, 7, 0.0f);
		this.tail03 = new ModelRenderer(this, 43, 54);
		this.tail03.setRotationPoint(0.0f, 0.5f, 5.9f);
		this.tail03.addBox(-0.5f, -0.5f, -0.5f, 1, 1, 6, 0.0f);
		this.neck01a = new ModelRenderer(this, 25, 15);
		this.neck01a.setRotationPoint(0.0f, 22.5f, 1.9f);
		this.neck01a.addBox(-1.8f, -1.49f, -6.0f, 4, 3, 9, 0.0f);
		this.neck01a.addChild(this.neck02);
		this.head.addChild(this.rEye);
		this.tail0a.addChild(this.tail02);
		this.neck01a.addChild(this.neck01b);
		this.head.addChild(this.headB);
		this.neck03.addChild(this.head);
		this.head.addChild(this.snout);
		this.body.addChild(this.tail0a);
		this.neck02.addChild(this.neck03);
		this.head.addChild(this.lowerJaw);
		this.head.addChild(this.lEye);
		this.neck01a.addChild(this.body);
		this.tail0a.addChild(this.tail01b);
		this.tail02.addChild(this.tail03);
	}
	
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale)
	{
		float pticks = Minecraft.getMinecraft().getRenderPartialTicks();
		float time = ((entity.ticksExisted + pticks) * 0.2f);
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
			// TODO: This is the cause of the clipping. Additionally, boxes probably shouldn't be added during render() ... may be a memory leak.
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