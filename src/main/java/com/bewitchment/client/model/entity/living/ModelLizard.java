package com.bewitchment.client.model.entity.living;

import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelLizard extends AdvancedModelBase
{
	public AdvancedModelRenderer body01;
	public AdvancedModelRenderer body02;
	public AdvancedModelRenderer neck;
	public AdvancedModelRenderer lArm01;
	public AdvancedModelRenderer rArm01;
	public AdvancedModelRenderer tail01;
	public AdvancedModelRenderer lLeg01;
	public AdvancedModelRenderer rLeg01;
	public AdvancedModelRenderer tail02;
	public AdvancedModelRenderer tail03;
	public AdvancedModelRenderer tail04;
	public AdvancedModelRenderer tail05;
	public AdvancedModelRenderer lLeg02;
	public AdvancedModelRenderer lfoot;
	public AdvancedModelRenderer lBToes;
	public AdvancedModelRenderer rLeg02;
	public AdvancedModelRenderer rfoot;
	public AdvancedModelRenderer rBToes;
	public AdvancedModelRenderer head;
	public AdvancedModelRenderer snout;
	public AdvancedModelRenderer lowerJaw;
	public AdvancedModelRenderer lEye;
	public AdvancedModelRenderer rEye;
	public AdvancedModelRenderer snoutb;
	public AdvancedModelRenderer lArm02;
	public AdvancedModelRenderer lforefoot;
	public AdvancedModelRenderer lfToes01;
	public AdvancedModelRenderer rArm02;
	public AdvancedModelRenderer rforefoot;
	public AdvancedModelRenderer rfToes01;
	
	public ModelLizard()
	{
		textureWidth = 64;
		textureHeight = 64;
		rArm01 = new AdvancedModelRenderer(this, 0, 28);
		rArm01.mirror = true;
		rArm01.setRotationPoint(-2.4f, -0.5f, -5.5f);
		rArm01.addBox(-3.5f, -1, -1, 4, 2, 2, 0);
		setRotateAngle(rArm01, 0.06981317007977318f, 0.3490658503988659f, -0.2792526803190927f);
		tail03 = new AdvancedModelRenderer(this, 46, 20);
		tail03.setRotationPoint(0, 0, 4.8f);
		tail03.addBox(-1, -1, -0.2f, 2, 2, 6, 0);
		setRotateAngle(tail03, 0.05235987755982988f, 0, 0);
		body01 = new AdvancedModelRenderer(this, 0, 0);
		body01.setRotationPoint(0, 21.2f, 1);
		body01.addBox(-2.5f, -2.7f, -8, 5, 4, 7, 0);
		setRotateAngle(body01, -0.13962634015954636f, 0, 0);
		lBToes = new AdvancedModelRenderer(this, 12, 52);
		lBToes.setRotationPoint(0, 0.4f, -0.6f);
		lBToes.addBox(-2.4f, -0.5f, -5.2f, 5, 0, 6, 0);
		lLeg01 = new AdvancedModelRenderer(this, 15, 29);
		lLeg01.setRotationPoint(2, -1.3f, 6.2f);
		lLeg01.addBox(-0.5f, -1, -1, 5, 2, 2, 0);
		setRotateAngle(lLeg01, 0.03490658503988659f, 0.6108652381980153f, 0);
		rBToes = new AdvancedModelRenderer(this, 12, 52);
		rBToes.mirror = true;
		rBToes.setRotationPoint(0, 0.4f, -0.6f);
		rBToes.addBox(-3.5f, -0.5f, -5.2f, 5, 0, 6, 0);
		setRotateAngle(rBToes, -0.017453292519943295f, 0, 0);
		neck = new AdvancedModelRenderer(this, 29, 0);
		neck.setRotationPoint(0, -0.7f, -7.7f);
		neck.addBox(-2, -2, -2.6f, 4, 4, 3, 0);
		setRotateAngle(neck, 0.13962634015954636f, 0, 0);
		lEye = new AdvancedModelRenderer(this, 37, 38);
		lEye.setRotationPoint(1.5f, -0.2f, 0);
		lEye.addBox(-0.5f, -2, -1, 2, 2, 2, 0);
		setRotateAngle(lEye, 0, 0.12217304763960307f, -0.19198621771937624f);
		rEye = new AdvancedModelRenderer(this, 37, 38);
		rEye.mirror = true;
		rEye.setRotationPoint(-1.5f, -0.2f, 0);
		rEye.addBox(-1.5f, -2, -1, 2, 2, 2, 0);
		setRotateAngle(rEye, 0, -0.12217304763960307f, 0.19198621771937624f);
		snoutb = new AdvancedModelRenderer(this, 31, 28);
		snoutb.setRotationPoint(0, -0.8f, -1.9f);
		snoutb.addBox(1.2f, -1, -2.8f, 1, 2, 3, 0);
		setRotateAngle(snoutb, 0.08726646259971647f, 0, 0);
		tail05 = new AdvancedModelRenderer(this, 51, 39);
		tail05.setRotationPoint(0, 0, 5.8f);
		tail05.addBox(-0.5f, -0.5f, -0.2f, 1, 1, 5, 0);
		setRotateAngle(tail05, 0.08726646259971647f, 0, 0);
		body02 = new AdvancedModelRenderer(this, 0, 15);
		body02.setRotationPoint(0, 0, -1);
		body02.addBox(-2.5f, -2.6f, -0.2f, 5, 4, 8, 0);
		setRotateAngle(body02, 0.10471975511965977f, 0, 0);
		rArm02 = new AdvancedModelRenderer(this, 0, 33);
		rArm02.mirror = true;
		rArm02.setRotationPoint(-2.7f, 0, 0);
		rArm02.addBox(-1, -0.8f, -1, 2, 4, 2, 0);
		setRotateAngle(rArm02, -0.17453292519943295f, 0, 0.2617993877991494f);
		snout = new AdvancedModelRenderer(this, 31, 22);
		snout.setRotationPoint(0, -0.8f, -1.9f);
		snout.addBox(-2.3f, -1, -2.8f, 4, 2, 3, 0);
		setRotateAngle(snout, 0.08726646259971647f, 0, 0);
		lfToes01 = new AdvancedModelRenderer(this, 0, 48);
		lfToes01.setRotationPoint(0, 0.2f, -0.6f);
		lfToes01.addBox(-1.5f, 0, -5, 3, 0, 5, 0);
		setRotateAngle(lfToes01, 0.05235987755982988f, 0, 0);
		lowerJaw = new AdvancedModelRenderer(this, 34, 29);
		lowerJaw.setRotationPoint(0, 1.3f, 1.1f);
		lowerJaw.addBox(-2, -0.5f, -5.6f, 4, 1, 6, 0);
		setRotateAngle(lowerJaw, -0.15707963267948966f, 0, 0);
		tail01 = new AdvancedModelRenderer(this, 43, 0);
		tail01.setRotationPoint(0, -0.8f, 7.7f);
		tail01.addBox(-2, -1.5f, -0.2f, 4, 3, 4, 0);
		setRotateAngle(tail01, -0.08726646259971647f, 0, 0);
		rLeg02 = new AdvancedModelRenderer(this, 17, 36);
		rLeg02.mirror = true;
		rLeg02.setRotationPoint(-3.5f, -0.3f, 0.1f);
		rLeg02.addBox(-1, -0.3f, -1, 2, 5, 2, 0);
		setRotateAngle(rLeg02, 0.3665191429188092f, 0, 0.20943951023931953f);
		head = new AdvancedModelRenderer(this, 25, 13);
		head.setRotationPoint(0, 0, -4.2f);
		head.addBox(-2.5f, -2, -2.5f, 5, 3, 5, 0);
		setRotateAngle(head, 0.13962634015954636f, 0, 0);
		lfoot = new AdvancedModelRenderer(this, 17, 45);
		lfoot.setRotationPoint(0.2f, 4.5f, 0.1f);
		lfoot.addBox(-1, -0.5f, -3, 2, 1, 4, 0);
		setRotateAngle(lfoot, -0.3490658503988659f, -0.6806784082777886f, 0.45378560551852565f);
		rfoot = new AdvancedModelRenderer(this, 17, 45);
		rfoot.mirror = true;
		rfoot.setRotationPoint(0.2f, 4.5f, -0.1f);
		rfoot.addBox(-1.5f, -0.5f, -3, 2, 1, 4, 0);
		setRotateAngle(rfoot, -0.3490658503988659f, 0.6806784082777886f, -0.45378560551852565f);
		lforefoot = new AdvancedModelRenderer(this, 0, 42);
		lforefoot.setRotationPoint(0.1f, 2.6f, 0);
		lforefoot.addBox(-1, 0, -1.9f, 2, 1, 3, 0);
		setRotateAngle(lforefoot, 0.17453292519943295f, 0, 0);
		rfToes01 = new AdvancedModelRenderer(this, 0, 48);
		rfToes01.mirror = true;
		rfToes01.setRotationPoint(-0, 0.2f, -0.6f);
		rfToes01.addBox(-1.5f, 0, -5, 3, 0, 5, 0);
		setRotateAngle(rfToes01, 0.05235987755982988f, 0, 0);
		lLeg02 = new AdvancedModelRenderer(this, 17, 36);
		lLeg02.setRotationPoint(3.5f, -0.3f, 0.1f);
		lLeg02.addBox(-1, -0.3f, -1, 2, 5, 2, 0);
		setRotateAngle(lLeg02, 0.3665191429188092f, 0, -0.20943951023931953f);
		rLeg01 = new AdvancedModelRenderer(this, 15, 29);
		rLeg01.mirror = true;
		rLeg01.setRotationPoint(-2, -1.3f, 6.2f);
		rLeg01.addBox(-4.5f, -1, -1, 5, 2, 2, 0);
		setRotateAngle(rLeg01, 0.03490658503988659f, -0.6108652381980153f, 0);
		lArm02 = new AdvancedModelRenderer(this, 0, 33);
		lArm02.setRotationPoint(2.7f, 0, 0);
		lArm02.addBox(-1, -0.8f, -1, 2, 4, 2, 0);
		setRotateAngle(lArm02, -0.17453292519943295f, 0, -0.2617993877991494f);
		lArm01 = new AdvancedModelRenderer(this, 0, 28);
		lArm01.setRotationPoint(2.4f, -0.5f, -5.5f);
		lArm01.addBox(-0.5f, -1, -1, 4, 2, 2, 0);
		setRotateAngle(lArm01, 0.06981317007977318f, -0.3490658503988659f, 0.2792526803190927f);
		tail02 = new AdvancedModelRenderer(this, 45, 10);
		tail02.setRotationPoint(0, 0, 3.8f);
		tail02.addBox(-1.5f, -1.5f, -0.2f, 3, 3, 5, 0);
		setRotateAngle(tail02, -0.08726646259971647f, 0, 0);
		rforefoot = new AdvancedModelRenderer(this, 0, 42);
		rforefoot.mirror = true;
		rforefoot.setRotationPoint(-0.1f, 2.6f, 0);
		rforefoot.addBox(-1, 0, -1.9f, 2, 1, 3, 0);
		setRotateAngle(rforefoot, 0.17453292519943295f, 0, 0);
		tail04 = new AdvancedModelRenderer(this, 50, 30);
		tail04.setRotationPoint(0, 0, 5.9f);
		tail04.addBox(-0.5f, -1, -0.2f, 1, 2, 6, 0);
		setRotateAngle(tail04, 0.08726646259971647f, 0, 0);
		body01.addChild(rArm01);
		tail02.addChild(tail03);
		lfoot.addChild(lBToes);
		body02.addChild(lLeg01);
		rfoot.addChild(rBToes);
		body01.addChild(neck);
		head.addChild(lEye);
		head.addChild(rEye);
		head.addChild(snoutb);
		tail04.addChild(tail05);
		body01.addChild(body02);
		rArm01.addChild(rArm02);
		head.addChild(snout);
		lforefoot.addChild(lfToes01);
		head.addChild(lowerJaw);
		body02.addChild(tail01);
		rLeg01.addChild(rLeg02);
		neck.addChild(head);
		lLeg02.addChild(lfoot);
		rLeg02.addChild(rfoot);
		lArm02.addChild(lforefoot);
		rforefoot.addChild(rfToes01);
		lLeg01.addChild(lLeg02);
		body02.addChild(rLeg01);
		lArm01.addChild(lArm02);
		body01.addChild(lArm01);
		tail01.addChild(tail02);
		rArm02.addChild(rforefoot);
		tail03.addChild(tail04);
		updateDefaultPose();
	}
	
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float age, float rotationYaw, float rotationPitch, float scale)
	{
		body01.render(scale);
	}
	
	public void setRotateAngle(AdvancedModelRenderer renderer, float x, float y, float z)
	{
		renderer.rotateAngleX = x;
		renderer.rotateAngleY = y;
		renderer.rotateAngleZ = z;
	}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float age, float rotationYaw, float rotationPitch, float scale, Entity entity)
	{
		super.setRotationAngles(limbSwing, limbSwingAmount, age, rotationYaw, rotationPitch, scale, entity);
		resetToDefaultPose();
		lArm01.rotateAngleY = MathHelper.cos(limbSwing * 0.66f) * 1.4f * limbSwingAmount;
		rArm01.rotateAngleY = -lArm01.rotateAngleY;
		lLeg01.rotateAngleY = -lArm01.rotateAngleY;
		rLeg01.rotateAngleY = lArm01.rotateAngleY;
		tail01.rotateAngleY = -lArm01.rotateAngleY / 3;
	}
}