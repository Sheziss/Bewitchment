package com.bewitchment.client.entity.model;

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
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.rArm01 = new AdvancedModelRenderer(this, 0, 28);
		this.rArm01.mirror = true;
		this.rArm01.setRotationPoint(-2.4f, -0.5f, -5.5f);
		this.rArm01.addBox(-3.5f, -1, -1, 4, 2, 2, 0);
		this.setRotateAngle(rArm01, 0.06981317007977318f, 0.3490658503988659f, -0.2792526803190927f);
		this.tail03 = new AdvancedModelRenderer(this, 46, 20);
		this.tail03.setRotationPoint(0, 0, 4.8f);
		this.tail03.addBox(-1, -1, -0.2f, 2, 2, 6, 0);
		this.setRotateAngle(tail03, 0.05235987755982988f, 0, 0);
		this.body01 = new AdvancedModelRenderer(this, 0, 0);
		this.body01.setRotationPoint(0, 21.2f, 1);
		this.body01.addBox(-2.5f, -2.7f, -8, 5, 4, 7, 0);
		this.setRotateAngle(body01, -0.13962634015954636f, 0, 0);
		this.lBToes = new AdvancedModelRenderer(this, 12, 52);
		this.lBToes.setRotationPoint(0, 0.4f, -0.6f);
		this.lBToes.addBox(-2.4f, -0.5f, -5.2f, 5, 0, 6, 0);
		this.lLeg01 = new AdvancedModelRenderer(this, 15, 29);
		this.lLeg01.setRotationPoint(2, -1.3f, 6.2f);
		this.lLeg01.addBox(-0.5f, -1, -1, 5, 2, 2, 0);
		this.setRotateAngle(lLeg01, 0.03490658503988659f, 0.6108652381980153f, 0);
		this.rBToes = new AdvancedModelRenderer(this, 12, 52);
		this.rBToes.mirror = true;
		this.rBToes.setRotationPoint(0, 0.4f, -0.6f);
		this.rBToes.addBox(-3.5f, -0.5f, -5.2f, 5, 0, 6, 0);
		this.setRotateAngle(rBToes, -0.017453292519943295f, 0, 0);
		this.neck = new AdvancedModelRenderer(this, 29, 0);
		this.neck.setRotationPoint(0, -0.7f, -7.7f);
		this.neck.addBox(-2, -2, -2.6f, 4, 4, 3, 0);
		this.setRotateAngle(neck, 0.13962634015954636f, 0, 0);
		this.lEye = new AdvancedModelRenderer(this, 37, 38);
		this.lEye.setRotationPoint(1.5f, -0.2f, 0);
		this.lEye.addBox(-0.5f, -2, -1, 2, 2, 2, 0);
		this.setRotateAngle(lEye, 0, 0.12217304763960307f, -0.19198621771937624f);
		this.rEye = new AdvancedModelRenderer(this, 37, 38);
		this.rEye.mirror = true;
		this.rEye.setRotationPoint(-1.5f, -0.2f, 0);
		this.rEye.addBox(-1.5f, -2, -1, 2, 2, 2, 0);
		this.setRotateAngle(rEye, 0, -0.12217304763960307f, 0.19198621771937624f);
		this.snoutb = new AdvancedModelRenderer(this, 31, 28);
		this.snoutb.setRotationPoint(0, -0.8f, -1.9f);
		this.snoutb.addBox(1.2f, -1, -2.8f, 1, 2, 3, 0);
		this.setRotateAngle(snoutb, 0.08726646259971647f, 0, 0);
		this.tail05 = new AdvancedModelRenderer(this, 51, 39);
		this.tail05.setRotationPoint(0, 0, 5.8f);
		this.tail05.addBox(-0.5f, -0.5f, -0.2f, 1, 1, 5, 0);
		this.setRotateAngle(tail05, 0.08726646259971647f, 0, 0);
		this.body02 = new AdvancedModelRenderer(this, 0, 15);
		this.body02.setRotationPoint(0, 0, -1);
		this.body02.addBox(-2.5f, -2.6f, -0.2f, 5, 4, 8, 0);
		this.setRotateAngle(body02, 0.10471975511965977f, 0, 0);
		this.rArm02 = new AdvancedModelRenderer(this, 0, 33);
		this.rArm02.mirror = true;
		this.rArm02.setRotationPoint(-2.7f, 0, 0);
		this.rArm02.addBox(-1, -0.8f, -1, 2, 4, 2, 0);
		this.setRotateAngle(rArm02, -0.17453292519943295f, 0, 0.2617993877991494f);
		this.snout = new AdvancedModelRenderer(this, 31, 22);
		this.snout.setRotationPoint(0, -0.8f, -1.9f);
		this.snout.addBox(-2.3f, -1, -2.8f, 4, 2, 3, 0);
		this.setRotateAngle(snout, 0.08726646259971647f, 0, 0);
		this.lfToes01 = new AdvancedModelRenderer(this, 0, 48);
		this.lfToes01.setRotationPoint(0, 0.2f, -0.6f);
		this.lfToes01.addBox(-1.5f, 0, -5, 3, 0, 5, 0);
		this.setRotateAngle(lfToes01, 0.05235987755982988f, 0, 0);
		this.lowerJaw = new AdvancedModelRenderer(this, 34, 29);
		this.lowerJaw.setRotationPoint(0, 1.3f, 1.1f);
		this.lowerJaw.addBox(-2, -0.5f, -5.6f, 4, 1, 6, 0);
		this.setRotateAngle(lowerJaw, -0.15707963267948966f, 0, 0);
		this.tail01 = new AdvancedModelRenderer(this, 43, 0);
		this.tail01.setRotationPoint(0, -0.8f, 7.7f);
		this.tail01.addBox(-2, -1.5f, -0.2f, 4, 3, 4, 0);
		this.setRotateAngle(tail01, -0.08726646259971647f, 0, 0);
		this.rLeg02 = new AdvancedModelRenderer(this, 17, 36);
		this.rLeg02.mirror = true;
		this.rLeg02.setRotationPoint(-3.5f, -0.3f, 0.1f);
		this.rLeg02.addBox(-1, -0.3f, -1, 2, 5, 2, 0);
		this.setRotateAngle(rLeg02, 0.3665191429188092f, 0, 0.20943951023931953f);
		this.head = new AdvancedModelRenderer(this, 25, 13);
		this.head.setRotationPoint(0, 0, -4.2f);
		this.head.addBox(-2.5f, -2, -2.5f, 5, 3, 5, 0);
		this.setRotateAngle(head, 0.13962634015954636f, 0, 0);
		this.lfoot = new AdvancedModelRenderer(this, 17, 45);
		this.lfoot.setRotationPoint(0.2f, 4.5f, 0.1f);
		this.lfoot.addBox(-1, -0.5f, -3, 2, 1, 4, 0);
		this.setRotateAngle(lfoot, -0.3490658503988659f, -0.6806784082777886f, 0.45378560551852565f);
		this.rfoot = new AdvancedModelRenderer(this, 17, 45);
		this.rfoot.mirror = true;
		this.rfoot.setRotationPoint(0.2f, 4.5f, -0.1f);
		this.rfoot.addBox(-1.5f, -0.5f, -3, 2, 1, 4, 0);
		this.setRotateAngle(rfoot, -0.3490658503988659f, 0.6806784082777886f, -0.45378560551852565f);
		this.lforefoot = new AdvancedModelRenderer(this, 0, 42);
		this.lforefoot.setRotationPoint(0.1f, 2.6f, 0);
		this.lforefoot.addBox(-1, 0, -1.9f, 2, 1, 3, 0);
		this.setRotateAngle(lforefoot, 0.17453292519943295f, 0, 0);
		this.rfToes01 = new AdvancedModelRenderer(this, 0, 48);
		this.rfToes01.mirror = true;
		this.rfToes01.setRotationPoint(-0, 0.2f, -0.6f);
		this.rfToes01.addBox(-1.5f, 0, -5, 3, 0, 5, 0);
		this.setRotateAngle(rfToes01, 0.05235987755982988f, 0, 0);
		this.lLeg02 = new AdvancedModelRenderer(this, 17, 36);
		this.lLeg02.setRotationPoint(3.5f, -0.3f, 0.1f);
		this.lLeg02.addBox(-1, -0.3f, -1, 2, 5, 2, 0);
		this.setRotateAngle(lLeg02, 0.3665191429188092f, 0, -0.20943951023931953f);
		this.rLeg01 = new AdvancedModelRenderer(this, 15, 29);
		this.rLeg01.mirror = true;
		this.rLeg01.setRotationPoint(-2, -1.3f, 6.2f);
		this.rLeg01.addBox(-4.5f, -1, -1, 5, 2, 2, 0);
		this.setRotateAngle(rLeg01, 0.03490658503988659f, -0.6108652381980153f, 0);
		this.lArm02 = new AdvancedModelRenderer(this, 0, 33);
		this.lArm02.setRotationPoint(2.7f, 0, 0);
		this.lArm02.addBox(-1, -0.8f, -1, 2, 4, 2, 0);
		this.setRotateAngle(lArm02, -0.17453292519943295f, 0, -0.2617993877991494f);
		this.lArm01 = new AdvancedModelRenderer(this, 0, 28);
		this.lArm01.setRotationPoint(2.4f, -0.5f, -5.5f);
		this.lArm01.addBox(-0.5f, -1, -1, 4, 2, 2, 0);
		this.setRotateAngle(lArm01, 0.06981317007977318f, -0.3490658503988659f, 0.2792526803190927f);
		this.tail02 = new AdvancedModelRenderer(this, 45, 10);
		this.tail02.setRotationPoint(0, 0, 3.8f);
		this.tail02.addBox(-1.5f, -1.5f, -0.2f, 3, 3, 5, 0);
		this.setRotateAngle(tail02, -0.08726646259971647f, 0, 0);
		this.rforefoot = new AdvancedModelRenderer(this, 0, 42);
		this.rforefoot.mirror = true;
		this.rforefoot.setRotationPoint(-0.1f, 2.6f, 0);
		this.rforefoot.addBox(-1, 0, -1.9f, 2, 1, 3, 0);
		this.setRotateAngle(rforefoot, 0.17453292519943295f, 0, 0);
		this.tail04 = new AdvancedModelRenderer(this, 50, 30);
		this.tail04.setRotationPoint(0, 0, 5.9f);
		this.tail04.addBox(-0.5f, -1, -0.2f, 1, 2, 6, 0);
		this.setRotateAngle(tail04, 0.08726646259971647f, 0, 0);
		this.body01.addChild(this.rArm01);
		this.tail02.addChild(this.tail03);
		this.lfoot.addChild(this.lBToes);
		this.body02.addChild(this.lLeg01);
		this.rfoot.addChild(this.rBToes);
		this.body01.addChild(this.neck);
		this.head.addChild(this.lEye);
		this.head.addChild(this.rEye);
		this.head.addChild(this.snoutb);
		this.tail04.addChild(this.tail05);
		this.body01.addChild(this.body02);
		this.rArm01.addChild(this.rArm02);
		this.head.addChild(this.snout);
		this.lforefoot.addChild(this.lfToes01);
		this.head.addChild(this.lowerJaw);
		this.body02.addChild(this.tail01);
		this.rLeg01.addChild(this.rLeg02);
		this.neck.addChild(this.head);
		this.lLeg02.addChild(this.lfoot);
		this.rLeg02.addChild(this.rfoot);
		this.lArm02.addChild(this.lforefoot);
		this.rforefoot.addChild(this.rfToes01);
		this.lLeg01.addChild(this.lLeg02);
		this.body02.addChild(this.rLeg01);
		this.lArm01.addChild(this.lArm02);
		this.body01.addChild(this.lArm01);
		this.tail01.addChild(this.tail02);
		this.rArm02.addChild(this.rforefoot);
		this.tail03.addChild(this.tail04);
		this.updateDefaultPose();
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale)
	{
		this.body01.render(scale);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.resetToDefaultPose();
		this.lArm01.rotateAngleY = MathHelper.cos(f * 0.66f) * 1.4f * f1;
		this.rArm01.rotateAngleY = -this.lArm01.rotateAngleY;
		this.lLeg01.rotateAngleY = -this.lArm01.rotateAngleY;
		this.rLeg01.rotateAngleY = this.lArm01.rotateAngleY;
		this.tail01.rotateAngleY = -this.lArm01.rotateAngleY / 3;
	}

	public void setRotateAngle(AdvancedModelRenderer AdvancedModelRenderer, float x, float y, float z)
	{
		AdvancedModelRenderer.rotateAngleX = x;
		AdvancedModelRenderer.rotateAngleY = y;
		AdvancedModelRenderer.rotateAngleZ = z;
	}
}