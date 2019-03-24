package com.bewitchment.client.model.entity.living;

import com.bewitchment.common.entity.living.EntitySnake;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSnake extends ModelBase
{
	private static final int COIL_ANIMATION_LENGTH = 160;
	public ModelRenderer neck01a;
	public ModelRenderer neck01b;
	public ModelRenderer neck02;
	public ModelRenderer body01;
	public ModelRenderer head;
	public ModelRenderer tongue;
	public ModelRenderer upperJawM;
	public ModelRenderer upperJawL;
	public ModelRenderer upperJawR;
	public ModelRenderer lowerJaw;
	public ModelRenderer eyes;
	public ModelRenderer lfang;
	public ModelRenderer rfang;
	public ModelRenderer tail01;
	public ModelRenderer tail01b;
	public ModelRenderer tail02;
	public ModelRenderer tail03;
	public ModelRenderer tail03b;
	public ModelRenderer tail04;
	public ModelRenderer tail05;
	
	public ModelSnake()
	{
		textureWidth = 64;
		textureHeight = 64;
		tongue = new ModelRenderer(this, 25, 0);
		tongue.setRotationPoint(0, 0.4f, -2);
		tongue.addBox(-1.5f, 0, -5, 3, 0, 7, 0);
		tail03b = new ModelRenderer(this, 22, 55);
		tail03b.setRotationPoint(0, 0.5f, 0);
		tail03b.addBox(-1.5f, 0, -1, 3, 1, 7, 0);
		upperJawR = new ModelRenderer(this, 17, 0);
		upperJawR.mirror = true;
		upperJawR.setRotationPoint(-1.9f, -0.9f, -3);
		upperJawR.addBox(-0.5f, -1, -4.07f, 2, 2, 5, 0);
		setRotateAngle(upperJawR, 0, -0.19198621771937624f, 0);
		upperJawL = new ModelRenderer(this, 17, 0);
		upperJawL.setRotationPoint(0.9f, -0.9f, -3);
		upperJawL.addBox(-0.5f, -1, -3.7f, 2, 2, 5, 0);
		setRotateAngle(upperJawL, 0, 0.19198621771937624f, 0);
		tail03 = new ModelRenderer(this, 23, 45);
		tail03.setRotationPoint(0, 0, 5.9f);
		tail03.addBox(-1.5f, -1.1f, -1, 3, 2, 7, 0);
		lfang = new ModelRenderer(this, 0, 0);
		lfang.setRotationPoint(1.4f, -0.5f, -3.6f);
		lfang.addBox(0, 0, -0.5f, 0, 2, 1, 0);
		eyes = new ModelRenderer(this, 40, 0);
		eyes.setRotationPoint(0, -2, -3.9f);
		eyes.addBox(-2, -0.5f, -0.5f, 4, 1, 1, 0);
		tail01b = new ModelRenderer(this, 46, 18);
		tail01b.setRotationPoint(0, 0, 0);
		tail01b.addBox(-2.3f, -1.5f, 0.5f, 2, 3, 7, 0);
		tail05 = new ModelRenderer(this, 43, 54);
		tail05.setRotationPoint(0, 0.5f, 7.8f);
		tail05.addBox(-0.5f, -0.5f, -0.6f, 1, 1, 8, 0);
		rfang = new ModelRenderer(this, 0, 0);
		rfang.setRotationPoint(-1.4f, -0.5f, -3.6f);
		rfang.addBox(0, 0, -0.5f, 0, 2, 1, 0);
		body01 = new ModelRenderer(this, 0, 31);
		body01.setRotationPoint(0, 0, 1.2f);
		body01.addBox(-2.5f, -1.8f, -0.4f, 5, 4, 8, 0);
		tail04 = new ModelRenderer(this, 41, 40);
		tail04.setRotationPoint(0, 0.5f, 5.8f);
		tail04.addBox(-1, -1, -1, 2, 2, 9, 0);
		neck01b = new ModelRenderer(this, 43, 28);
		neck01b.setRotationPoint(0, 0, 0);
		neck01b.addBox(-2.3f, -1.49f, -6, 2, 3, 8, 0);
		head = new ModelRenderer(this, 0, 22);
		head.setRotationPoint(0, 0.8f, -5.9f);
		head.addBox(-2.5f, -2.4f, -3, 5, 3, 3, 0);
		lowerJaw = new ModelRenderer(this, 0, 8);
		lowerJaw.setRotationPoint(0, 0.1f, -2);
		lowerJaw.addBox(-1.5f, -0.5f, -5, 3, 1, 5, 0);
		neck01a = new ModelRenderer(this, 27, 33);
		neck01a.setRotationPoint(0, 22.5f, -3.9f);
		neck01a.addBox(-0.7f, -1.49f, -6, 3, 3, 8, 0);
		tail02 = new ModelRenderer(this, 0, 45);
		tail02.setRotationPoint(0, 0, 7.4f);
		tail02.addBox(-2, -1.49f, -0.5f, 4, 3, 7, 0);
		neck02 = new ModelRenderer(this, 0, 45);
		neck02.setRotationPoint(0, 0, -5.9f);
		neck02.addBox(-2, -1.5f, -6, 4, 3, 7, 0);
		upperJawM = new ModelRenderer(this, 0, 0);
		upperJawM.setRotationPoint(0, -1.3f, -2.8f);
		upperJawM.addBox(-1.5f, -1.1f, -4.4f, 3, 2, 5, 0);
		setRotateAngle(upperJawM, 0.08726646259971647f, 0, 0);
		tail01 = new ModelRenderer(this, 27, 22);
		tail01.setRotationPoint(0, 0, 6.7f);
		tail01.addBox(-0.7f, -1.5f, 0.6f, 3, 3, 7, 0);
		head.addChild(tongue);
		tail03.addChild(tail03b);
		head.addChild(upperJawR);
		head.addChild(upperJawL);
		tail02.addChild(tail03);
		upperJawM.addChild(lfang);
		head.addChild(eyes);
		tail01.addChild(tail01b);
		tail04.addChild(tail05);
		upperJawM.addChild(rfang);
		neck01a.addChild(body01);
		tail03.addChild(tail04);
		neck01a.addChild(neck01b);
		neck02.addChild(head);
		head.addChild(lowerJaw);
		tail01.addChild(tail02);
		neck01a.addChild(neck02);
		head.addChild(upperJawM);
		body01.addChild(tail01);
	}
	
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float age, float rotationYaw, float rotationPitch, float scale)
	{
		float pticks = Minecraft.getMinecraft().getRenderPartialTicks();
		float time = (entity.ticksExisted + pticks) * 0.2f;
		float angle = 0.34906585039f;
		EntitySnake snek = (EntitySnake) entity;
		if (snek.isSitting())
		{
			if (snek.animation_timer < COIL_ANIMATION_LENGTH)
			{
				snek.animation_timer++;
				int timer = snek.animation_timer;
				neck01a.rotateAngleY = neck01a.rotateAngleY + (-0.09110618695f - neck01a.rotateAngleY) * timer / COIL_ANIMATION_LENGTH;
				neck02.rotateAngleY = neck02.rotateAngleY + (-1.27478848566f - neck02.rotateAngleY) * timer / COIL_ANIMATION_LENGTH;
				neck02.rotateAngleX = neck02.rotateAngleX + (-0.27314402793f - neck02.rotateAngleX) * timer / COIL_ANIMATION_LENGTH;
				body01.rotateAngleY = body01.rotateAngleY + (1.09397237515f - body01.rotateAngleY) * timer / COIL_ANIMATION_LENGTH;
				tail01.rotateAngleY = tail01.rotateAngleY + (0.95609136424f - tail01.rotateAngleY) * timer / COIL_ANIMATION_LENGTH;
				tail02.rotateAngleY = tail02.rotateAngleY + (1.27478848566f - tail02.rotateAngleY) * timer / COIL_ANIMATION_LENGTH;
				tail03.rotateAngleY = tail03.rotateAngleY + (0.81960661673f - tail03.rotateAngleY) * timer / COIL_ANIMATION_LENGTH;
				tail04.rotateAngleY = tail04.rotateAngleY + (1.54810704652f - tail04.rotateAngleY) * timer / COIL_ANIMATION_LENGTH;
				tail05.rotateAngleY = tail05.rotateAngleY + (1.59348560707f - tail05.rotateAngleY) * timer / COIL_ANIMATION_LENGTH;
				head.rotateAngleX = head.rotateAngleX + (0.13665928043f - head.rotateAngleX) * timer / COIL_ANIMATION_LENGTH;
				head.rotateAngleY = head.rotateAngleY + (-0.77405352325f - head.rotateAngleY) * timer / COIL_ANIMATION_LENGTH;
				head.rotateAngleZ = head.rotateAngleZ + (-0.18203784098f - head.rotateAngleZ) * timer / COIL_ANIMATION_LENGTH;
			}
			else
			{
				neck01a.offsetX = -0.3f;
				neck01a.rotateAngleY = -0.09110618695f;
				neck02.rotateAngleY = -1.27478848566f;
				neck02.rotateAngleX = -0.27314402793f;
				body01.rotateAngleY = 1.09397237515f;
				tail01.rotateAngleY = 0.95609136424f;
				tail02.rotateAngleY = 1.27478848566f;
				tail03.rotateAngleY = 0.81960661673f;
				tail04.rotateAngleY = 1.54810704652f;
				tail05.rotateAngleY = 1.59348560707f;
				head.rotateAngleX = 0.13665928043f;
				head.rotateAngleY = -0.77405352325f;
				head.rotateAngleZ = -0.18203784098f;
			}
		}
		else if (snek.motionX != 0 || snek.motionZ != 0)
		{
			neck01a.offsetX = 0.3f * MathHelper.cos(time);
			neck01a.rotateAngleY = angle * MathHelper.sin(time);
			neck02.rotateAngleY = angle * MathHelper.sin(time - 5);
			neck02.rotateAngleX = 0;
			body01.rotateAngleY = angle * MathHelper.sin(time + 5);
			tail01.rotateAngleY = angle * MathHelper.sin(time + 11);
			tail02.rotateAngleY = angle * MathHelper.sin(time + 4);
			tail03.rotateAngleY = angle * MathHelper.sin(time + 2);
			tail04.rotateAngleY = angle * MathHelper.sin(time + 3);
			tail05.rotateAngleY = angle / 4f * MathHelper.sin(time + 2);
			head.rotateAngleY = neck02.rotateAngleY;
			head.rotateAngleZ = 0.174532925f * MathHelper.cos(time - 5);
			head.rotateAngleX = 0;
			head.rotateAngleZ = 0;
			snek.animation_timer = 0;
		}
		else
		{
			// TODO: This is the cause of the clipping. Additionally, boxes probably
			// shouldn't be added during render() ... may be a memory leak.
			// this.neck01b.addBox(-2.3f, -1.49f, -6, 2, 3, 8, MathHelper.sin(time));
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