package com.bewitchment.client.entity.model.hostile;

import com.bewitchment.common.entity.hostile.EntityBlackDog;

import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBlackDog extends AdvancedModelBase
{
	public AdvancedModelRenderer lArm01;
	public AdvancedModelRenderer body;
	public AdvancedModelRenderer lHindLeg01;
	public AdvancedModelRenderer rHindLeg01;
	public AdvancedModelRenderer rArm01;
	public AdvancedModelRenderer chest;
	public AdvancedModelRenderer tail01;
	public AdvancedModelRenderer neck;
	public AdvancedModelRenderer mane02;
	public AdvancedModelRenderer mane01;
	public AdvancedModelRenderer head;
	public AdvancedModelRenderer lEar;
	public AdvancedModelRenderer rEar;
	public AdvancedModelRenderer muzzle;
	public AdvancedModelRenderer lowerJaw;
	public AdvancedModelRenderer lEar02;
	public AdvancedModelRenderer rEar02;
	
	private ModelAnimator animator;
	
	public ModelBlackDog()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.neck = new AdvancedModelRenderer(this, 0, 32);
		this.neck.setRotationPoint(0, -5.4f, 0.5f);
		this.neck.addBox(-2.5f, -2.5f, -4, 5, 5, 4, 0);
		this.setRotateAngle(neck, -1.5707963267948966f, 0, 0);
		this.lowerJaw = new AdvancedModelRenderer(this, 0, 43);
		this.lowerJaw.setRotationPoint(0, 2, -3.8f);
		this.lowerJaw.addBox(-1.5f, -0.4f, -3, 3, 1, 3, 0);
		this.rEar = new AdvancedModelRenderer(this, 16, 14);
		this.rEar.mirror = true;
		this.rEar.setRotationPoint(-2, -2, -2);
		this.rEar.addBox(-2, -1, -1, 2, 1, 2, 0);
		this.setRotateAngle(rEar, 0, 0, 0.5462880558742251f);
		this.muzzle = new AdvancedModelRenderer(this, 0, 10);
		this.muzzle.setRotationPoint(0, 0.7f, -3.9f);
		this.muzzle.addBox(-1.5f, -1, -3, 3, 2, 3, 0);
		this.tail01 = new AdvancedModelRenderer(this, 9, 18);
		this.tail01.setRotationPoint(0, 5.7f, 2);
		this.tail01.addBox(-1, 0, -1, 2, 9, 2, 0);
		this.setRotateAngle(tail01, -0.8726646259971648f, 0, 0);
		this.lEar = new AdvancedModelRenderer(this, 16, 14);
		this.lEar.setRotationPoint(2, -2, -2);
		this.lEar.addBox(0, -1, -1, 2, 1, 2, 0);
		this.setRotateAngle(lEar, 0, 0, -0.5462880558742251f);
		this.rHindLeg01 = new AdvancedModelRenderer(this, 0, 18);
		this.rHindLeg01.mirror = true;
		this.rHindLeg01.setRotationPoint(-1.5f, 16, 6);
		this.rHindLeg01.addBox(-1, 0, -1, 2, 8, 2, 0);
		this.lEar02 = new AdvancedModelRenderer(this, 39, 14);
		this.lEar02.setRotationPoint(1.9f, -0.9f, 0);
		this.lEar02.addBox(-0.5f, -0.1f, -1, 1, 3, 2, 0);
		this.setRotateAngle(lEar02, 0, 0, 0.22759093446006054f);
		this.lArm01 = new AdvancedModelRenderer(this, 0, 18);
		this.lArm01.setRotationPoint(1.5f, 16, -4);
		this.lArm01.addBox(-1, 0, -1, 2, 8, 2, 0);
		this.mane02 = new AdvancedModelRenderer(this, 28, 48);
		this.mane02.setRotationPoint(0, -1, 2.7f);
		this.mane02.addBox(-3.5f, -1, 0, 7, 2, 7, 0);
		this.setRotateAngle(mane02, -1.2915436464758039f, 0, 0);
		this.lHindLeg01 = new AdvancedModelRenderer(this, 0, 18);
		this.lHindLeg01.setRotationPoint(1.5f, 16, 6);
		this.lHindLeg01.addBox(-1, 0, -1, 2, 8, 2, 0);
		this.rArm01 = new AdvancedModelRenderer(this, 0, 18);
		this.rArm01.mirror = true;
		this.rArm01.setRotationPoint(-1.5f, 16, -4);
		this.rArm01.addBox(-1, 0, -1, 2, 8, 2, 0);
		this.rEar02 = new AdvancedModelRenderer(this, 39, 14);
		this.rEar02.mirror = true;
		this.rEar02.setRotationPoint(-1.9f, -0.9f, 0);
		this.rEar02.addBox(-0.5f, -0.1f, -1, 1, 3, 2, 0);
		this.setRotateAngle(rEar02, 0, 0, -0.22759093446006054f);
		this.body = new AdvancedModelRenderer(this, 18, 14);
		this.body.setRotationPoint(0, 14, 1);
		this.body.addBox(-3, -2, -3, 6, 9, 6, 0);
		this.setRotateAngle(body, 1.5707963267948966f, 0, 0);
		this.head = new AdvancedModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0, 0, -2.9f);
		this.head.addBox(-3, -3, -4, 6, 6, 4, 0);
		this.mane01 = new AdvancedModelRenderer(this, 0, 48);
		this.mane01.setRotationPoint(0, -1.8f, -3);
		this.mane01.addBox(-3, -1, 0, 6, 2, 7, 0);
		this.setRotateAngle(mane01, 0.4363323129985824f, 0, 0);
		this.chest = new AdvancedModelRenderer(this, 21, 0);
		this.chest.setRotationPoint(0, -4, 0);
		this.chest.addBox(-4, -3.5f, -3.01f, 8, 6, 7, 0);
		this.body.addChild(this.neck);
		this.head.addChild(this.lowerJaw);
		this.head.addChild(this.rEar);
		this.head.addChild(this.muzzle);
		this.body.addChild(this.tail01);
		this.head.addChild(this.lEar);
		this.lEar.addChild(this.lEar02);
		this.chest.addChild(this.mane02);
		this.rEar.addChild(this.rEar02);
		this.neck.addChild(this.head);
		this.neck.addChild(this.mane01);
		this.body.addChild(this.chest);
		this.updateDefaultPose();
		animator = ModelAnimator.create();
	}
	
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float age, float rotationYaw, float rotationPitch, float scale)
	{
		this.setRotationAngles(limbSwing, limbSwingAmount, age, rotationYaw, rotationPitch, scale, entity);
		this.animate((IAnimatedEntity) entity, limbSwing, limbSwingAmount, age, rotationYaw, rotationPitch, scale);
		this.rHindLeg01.render(scale);
		this.lArm01.render(scale);
		this.lHindLeg01.render(scale);
		this.rArm01.render(scale);
		this.body.render(scale);
	}
	
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float age, float rotationYaw, float rotationPitch, float scale, Entity entity)
	{
		super.setRotationAngles(limbSwing, limbSwingAmount, age, rotationYaw, rotationPitch, scale, entity);
		this.resetToDefaultPose();
		float globalSpeed = 1;
		float globalHeight = 1;
		bob(body, 0.3f * globalSpeed, 0.8f * globalHeight, false, limbSwing, limbSwingAmount);
		walk(rArm01, 0.6f, 0.5f, false, 0, 0.2f, limbSwing, limbSwingAmount);
		walk(lArm01, 0.6f, 0.5f, true, 0, 0.2f, limbSwing, limbSwingAmount);
		walk(rHindLeg01, 0.6f, 0.5f, true, 0, 0.2f, limbSwing, limbSwingAmount);
		walk(lHindLeg01, 0.6f, 0.5f, false, 0, 0.2f, limbSwing, limbSwingAmount);
	}
	
	public void animate(IAnimatedEntity entity, float limbSwing, float limbSwingAmount, float age, float rotationYaw, float rotationPitch, float scale)
	{
		this.resetToDefaultPose();
		this.setRotationAngles(limbSwing, limbSwingAmount, age, rotationYaw, rotationPitch, scale, (Entity) entity);
		animator.update(entity);
		animator.setAnimation(EntityBlackDog.BITE);
		animator.startKeyframe(20);
		animator.rotate(muzzle, -0.45f, 0, 0);
		animator.rotate(lowerJaw, 0.45f, 0, 0);
		animator.endKeyframe();
		animator.resetKeyframe(10);
	}
}