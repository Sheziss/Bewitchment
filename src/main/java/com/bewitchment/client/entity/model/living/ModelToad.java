package com.bewitchment.client.entity.model.living;

import com.bewitchment.common.entity.living.EntityToad;

import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelToad extends AdvancedModelBase
{
	public ModelRenderer stomach;
	public ModelRenderer head01;
	public ModelRenderer lArm01;
	public ModelRenderer rArm01;
	public ModelRenderer lLeg01;
	public ModelRenderer rLeg01;
	public ModelRenderer head02;
	public ModelRenderer jaw;
	public ModelRenderer lEye;
	public ModelRenderer rEye;
	public ModelRenderer lArm02;
	public ModelRenderer lHand;
	public ModelRenderer rArm02;
	public ModelRenderer rHand;
	public ModelRenderer lLeg02;
	public ModelRenderer lLeg03;
	public ModelRenderer rLeg02;
	public ModelRenderer rLeg03;

	public ModelToad()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.head01 = new ModelRenderer(this, 0, 17);
		this.head01.setRotationPoint(0, -1.0118749141693115f, 0.6827225685119629f);
		this.head01.addBox(-2.5f, -1.5f, -3, 5, 5, 4, 0);
		this.setRotateAngle(head01, -0.41887902047863906f, 0, 0);
		this.lLeg02 = new ModelRenderer(this, 39, 19);
		this.lLeg02.setRotationPoint(0.9f, 3.6f, -0.5f);
		this.lLeg02.addBox(-1.2f, -1, 0, 2, 2, 5, 0);
		this.setRotateAngle(lLeg02, 0.45378560551852565f, 0.13962634015954636f, 0.19198621771937624f);
		this.lHand = new ModelRenderer(this, 3, 27);
		this.lHand.setRotationPoint(0.1f, 4.5f, -0.3f);
		this.lHand.addBox(-1.5f, 0, -2.7f, 3, 0, 4, 0);
		this.setRotateAngle(lHand, 0.7330382858376184f, 0.08726646259971647f, 0.45378560551852565f);
		this.rArm02 = new ModelRenderer(this, 32, 25);
		this.rArm02.mirror = true;
		this.rArm02.setRotationPoint(-1, -0.1f, 2.8f);
		this.rArm02.addBox(-1.01f, 0, -1, 2, 5, 2, 0);
		this.setRotateAngle(rArm02, 0.45378560551852565f, 0, 0);
		this.lEye = new ModelRenderer(this, 27, 0);
		this.lEye.setRotationPoint(1.3f, -0.5f, -2.3f);
		this.lEye.addBox(0, -2.1f, -1.5f, 2, 2, 3, 0);
		this.setRotateAngle(lEye, 0, 0.19198621771937624f, 0.13962634015954636f);
		this.lLeg01 = new ModelRenderer(this, 22, 16);
		this.lLeg01.setRotationPoint(3.3f, -0.7989158034324646f, 7.941141605377197f);
		this.lLeg01.addBox(0, -1, -1.5f, 2, 5, 3, 0);
		this.setRotateAngle(lLeg01, -0.512306872707822f, 0.06843323235500261f, -0.1795160990731347f);
		this.rLeg01 = new ModelRenderer(this, 22, 16);
		this.rLeg01.mirror = true;
		this.rLeg01.setRotationPoint(-3.3f, -0.7989158034324646f, 7.941141605377197f);
		this.rLeg01.addBox(-2, -1, -1.5f, 2, 5, 3, 0);
		this.setRotateAngle(rLeg01, -0.512306872707822f, -0.06843323235500261f, 0.1795160990731347f);
		this.lArm01 = new ModelRenderer(this, 16, 25);
		this.lArm01.setRotationPoint(3, 0.517690896987915f, 1.1627533435821533f);
		this.lArm01.addBox(0, -1, -1, 2, 2, 5, 0);
		this.setRotateAngle(lArm01, -0.8158759115445331f, 0.09894094522554002f, -0.261566760677931f);
		this.jaw = new ModelRenderer(this, 37, 10);
		this.jaw.setRotationPoint(0, 0.4f, -0.6f);
		this.jaw.addBox(-3, 0, -5, 6, 2, 6, 0);
		this.lLeg03 = new ModelRenderer(this, 49, 25);
		this.lLeg03.setRotationPoint(-0.1f, 0.6f, 4.3f);
		this.lLeg03.addBox(-1.2f, -0.5f, -5, 2, 1, 5, 0);
		this.setRotateAngle(lLeg03, 0.41887902047863906f, -0.33161255787892263f, 0);
		this.rLeg02 = new ModelRenderer(this, 39, 19);
		this.rLeg02.mirror = true;
		this.rLeg02.setRotationPoint(-0.9f, 3.6f, -0.2f);
		this.rLeg02.addBox(-1.2000000476837158f, -1, 0, 2, 2, 5, 0);
		this.setRotateAngle(rLeg02, 0.45378560551852565f, -0.13962634015954636f, -0.19198621771937624f);
		this.rHand = new ModelRenderer(this, 3, 27);
		this.rHand.mirror = true;
		this.rHand.setRotationPoint(-0.1f, 4.5f, -0.3f);
		this.rHand.addBox(-1.5f, 0, -2.7f, 3, 0, 4, 0);
		this.setRotateAngle(rHand, 0.7330382858376184f, -0.08726646259971647f, -0.45378560551852565f);
		this.rArm01 = new ModelRenderer(this, 16, 25);
		this.rArm01.mirror = true;
		this.rArm01.setRotationPoint(-3, 0.517690896987915f, 1.1627533435821533f);
		this.rArm01.addBox(-2, -1, -1, 2, 2, 5, 0);
		this.setRotateAngle(rArm01, -0.8158759115445331f, -0.09894094522554002f, 0.261566760677931f);
		this.rEye = new ModelRenderer(this, 27, 0);
		this.rEye.mirror = true;
		this.rEye.setRotationPoint(-1.3f, -0.5f, -2.3f);
		this.rEye.addBox(-2, -2.1f, -1.5f, 2, 2, 3, 0);
		this.setRotateAngle(rEye, 0, -0.19198621771937624f, -0.13962634015954636f);
		this.lArm02 = new ModelRenderer(this, 32, 25);
		this.lArm02.setRotationPoint(1, -0.1f, 2.8f);
		this.lArm02.addBox(-0.99f, 0, -1, 2, 5, 2, 0);
		this.setRotateAngle(lArm02, 0.45378560551852565f, 0, 0);
		this.head02 = new ModelRenderer(this, 37, 0);
		this.head02.setRotationPoint(0, -0.20000000298023224f, -2.2f);
		this.head02.addBox(-3, -1.5f, -5.7f, 6, 2, 6, 0);
		this.setRotateAngle(head02, 0.8726646259971648f, 0, 0);
		this.rLeg03 = new ModelRenderer(this, 49, 25);
		this.rLeg03.mirror = true;
		this.rLeg03.setRotationPoint(0.1f, 0.6f, 4.3f);
		this.rLeg03.addBox(-1.2000000476837158f, -0.5f, -5, 2, 1, 5, 0);
		this.setRotateAngle(rLeg03, 0.41887902047863906f, 0.33161255787892263f, 0);
		this.stomach = new ModelRenderer(this, 0, 0);
		this.stomach.setRotationPoint(0, 17.7f, -1.9f);
		this.stomach.addBox(-4, -2.5f, 0, 8, 6, 10, 0);
		this.setRotateAngle(stomach, -0.3665191429188092f, 0, 0);
		this.stomach.addChild(this.head01);
		this.lLeg01.addChild(this.lLeg02);
		this.lArm02.addChild(this.lHand);
		this.rArm01.addChild(this.rArm02);
		this.head02.addChild(this.lEye);
		this.stomach.addChild(this.lLeg01);
		this.stomach.addChild(this.rLeg01);
		this.stomach.addChild(this.lArm01);
		this.head02.addChild(this.jaw);
		this.lLeg02.addChild(this.lLeg03);
		this.rLeg01.addChild(this.rLeg02);
		this.rArm02.addChild(this.rHand);
		this.stomach.addChild(this.rArm01);
		this.head02.addChild(this.rEye);
		this.lArm01.addChild(this.lArm02);
		this.head01.addChild(this.head02);
		this.rLeg02.addChild(this.rLeg03);
	}
	
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float age, float rotationYaw, float rotationPitch, float scale)
	{
		this.stomach.render(scale);
		// Must reset the animations after rendering so it doesn't show up on all entities
		this.stomach.offsetY = 0;
		this.stomach.rotateAngleX = -0.36651914291f;
		this.lLeg01.rotateAngleX = -0.51225413546f;
		this.lLeg02.rotateAngleX = 0.45378560551f;
		this.rLeg01.rotateAngleX = lLeg01.rotateAngleX;
		this.rLeg02.rotateAngleX = lLeg02.rotateAngleX;
		this.rLeg03.rotateAngleX = lLeg03.rotateAngleX;
		this.lArm01.rotateAngleX = -0.8159414253f;
		this.lArm02.rotateAngleX = 0.45378560551f;
		this.rArm01.rotateAngleX = lArm01.rotateAngleX;
		this.rArm02.rotateAngleX = lArm02.rotateAngleX;
	}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float age, float rotationYaw, float rotationPitch, float scale, Entity entity)
	{
		if (entity.onGround) setRotateAngle(head02, (float) (0.6981317007977318f + rotationPitch * Math.PI / 360), (float) (rotationYaw * Math.PI / 360), 0);
	}
	
	@Override
	public void setLivingAnimations(EntityLivingBase living, float limbSwing, float limbSwingAmount, float partialTickTime)
	{
		super.setLivingAnimations(living, limbSwing, limbSwingAmount, partialTickTime);
		float time = living.ticksExisted * 0.2f;
		this.jaw.rotationPointY = (float) (0.5f + 0.02 * MathHelper.sin(time));
		EntityToad toad = (EntityToad) living;
		if (toad.isSitting())
		{
			lLeg03.rotateAngleY = 1.0471975512f;
			rLeg03.rotateAngleY = -1.0471975512f;
		}
		else
		{
			lLeg03.rotateAngleY = -0.6f;
			rLeg03.rotateAngleY = 0.6f;
		}
		float timer = toad.getDataManager().get(EntityToad.ANIMATION_TIME);
		if (limbSwingAmount > 0.1 || timer != 0)
		{
			toad.getDataManager().set(EntityToad.ANIMATION_TIME, toad.getDataManager().get(EntityToad.ANIMATION_TIME) + 1);
			timer = toad.getDataManager().get(EntityToad.ANIMATION_TIME);
			if (timer < 25)
			{
				this.stomach.offsetY = toad.getDataManager().get(EntityToad.ANIMATION_HEIGHT) + (-1.5f - toad.getDataManager().get(EntityToad.ANIMATION_HEIGHT)) * (timer / 100);
				toad.getDataManager().set(EntityToad.ANIMATION_HEIGHT, stomach.offsetY);
				this.stomach.rotateAngleX = this.stomach.rotateAngleX + (0 - this.stomach.rotateAngleX) * (timer / 100f);
				this.lLeg01.rotateAngleX = this.lLeg01.rotateAngleX + (1.3962634016f - this.lLeg01.rotateAngleX) * (timer / 100f);
				this.lLeg02.rotateAngleX = this.lLeg02.rotateAngleX + (-1.3962634016f - this.lLeg02.rotateAngleX) * (timer / 100f);
				this.lLeg03.rotateAngleX = this.lLeg03.rotateAngleX + (2.35619449019f - this.lLeg03.rotateAngleX) * (timer / 100f);
				this.rLeg01.rotateAngleX = lLeg01.rotateAngleX;
				this.rLeg02.rotateAngleX = lLeg02.rotateAngleX;
				this.rLeg03.rotateAngleX = lLeg03.rotateAngleX;
				this.lArm01.rotateAngleX = this.lArm01.rotateAngleX + (-2.35619449019f - this.lArm01.rotateAngleX) * (timer / 100f);
				this.lArm02.rotateAngleX = this.lArm02.rotateAngleX + (1.3962634016f - this.lArm02.rotateAngleX) * (timer / 100f);
				this.rArm01.rotateAngleX = lArm01.rotateAngleX;
				this.rArm02.rotateAngleX = lArm02.rotateAngleX;

			}
			else if (timer < 50)
			{
				this.stomach.offsetY = toad.getDataManager().get(EntityToad.ANIMATION_HEIGHT) + -toad.getDataManager().get(EntityToad.ANIMATION_HEIGHT) * ((timer - 25) / 100);
				toad.getDataManager().set(EntityToad.ANIMATION_HEIGHT, stomach.offsetY);
				this.stomach.rotateAngleX = this.stomach.rotateAngleX + (0.36651914291f - this.stomach.rotateAngleX) * ((timer - 24f) / 100f);
				this.lLeg01.rotateAngleX = this.lLeg01.rotateAngleX + (-0.51225413546f - this.lLeg01.rotateAngleX) * ((timer - 24f) / 100f);
				this.lLeg02.rotateAngleX = this.lLeg02.rotateAngleX + (0.45378560551f - this.lLeg02.rotateAngleX) * ((timer - 24f) / 100f);
				this.lLeg03.rotateAngleX = this.lLeg03.rotateAngleX + (0.41887902047f - this.lLeg03.rotateAngleX) * ((timer - 24f) / 100f);
				this.rLeg01.rotateAngleX = lLeg01.rotateAngleX;
				this.rLeg02.rotateAngleX = lLeg02.rotateAngleX;
				this.rLeg03.rotateAngleX = lLeg03.rotateAngleX;
				this.lArm01.rotateAngleX = this.lArm01.rotateAngleX + (-0.8159414253f - this.lArm01.rotateAngleX) * ((timer - 24f) / 100f);
				this.lArm02.rotateAngleX = this.lArm02.rotateAngleX + (0.45378560551f - this.lArm02.rotateAngleX) * ((timer - 24f) / 100f);
				this.rArm01.rotateAngleX = lArm01.rotateAngleX;
				this.rArm02.rotateAngleX = lArm02.rotateAngleX;

			}
			else if (timer < 75) this.stomach.rotateAngleX = this.stomach.rotateAngleX + (-0.36651914291f - this.stomach.rotateAngleX) * ((timer - 49f) / 100f);
			else
			{
				toad.getDataManager().set(EntityToad.ANIMATION_TIME, 0);
				this.stomach.offsetY = 0;
				toad.getDataManager().set(EntityToad.ANIMATION_HEIGHT, 0f);
				this.stomach.rotateAngleX = -0.36651914291f;
				this.lLeg01.rotateAngleX = -0.51225413546f;
				this.lLeg02.rotateAngleX = 0.45378560551f;
				this.rLeg01.rotateAngleX = lLeg01.rotateAngleX;
				this.rLeg02.rotateAngleX = lLeg02.rotateAngleX;
				this.rLeg03.rotateAngleX = lLeg03.rotateAngleX;
				this.lArm01.rotateAngleX = -0.8159414253f;
				this.lArm02.rotateAngleX = 0.45378560551f;
				this.rArm01.rotateAngleX = lArm01.rotateAngleX;
				this.rArm02.rotateAngleX = lArm02.rotateAngleX;
			}
		}
		else
		{
			toad.getDataManager().set(EntityToad.ANIMATION_TIME, 0);
			this.stomach.offsetY = 0;
			toad.getDataManager().set(EntityToad.ANIMATION_HEIGHT, 0f);
		}
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer renderer, float x, float y, float z)
	{
		renderer.rotateAngleX = x;
		renderer.rotateAngleY = y;
		renderer.rotateAngleZ = z;
	}
}