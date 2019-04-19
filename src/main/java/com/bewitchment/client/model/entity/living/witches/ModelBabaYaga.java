package com.bewitchment.client.model.entity.living.witches;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created by Joseph on 4/18/2019.
 */
public class ModelBabaYaga extends ModelBase {

	public ModelRenderer body;
	public ModelRenderer robe;
	public ModelRenderer mortar01;
	public ModelRenderer lArm;
	public ModelRenderer rArm;
	public ModelRenderer head;
	public ModelRenderer pestal01;
	public ModelRenderer pestal02;
	public ModelRenderer nose;
	public ModelRenderer hat01;
	public ModelRenderer mhair01;
	public ModelRenderer mhair02;
	public ModelRenderer lHair01;
	public ModelRenderer rHair01;
	public ModelRenderer wart;
	public ModelRenderer hat02;
	public ModelRenderer hat03;
	public ModelRenderer hat04;
	public ModelRenderer mortar02;
	public ModelRenderer mortar03;
	public ModelRenderer mortar04;
	public ModelRenderer mortar05;

	public ModelBabaYaga() {
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.mortar04 = new ModelRenderer(this, 76, 0);
		this.mortar04.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.mortar04.addBox(2.5F, 0.0F, -2.5F, 4, 10, 5, 0.0F);
		this.pestal01 = new ModelRenderer(this, 34, 43);
		this.pestal01.setRotationPoint(-2.3F, 7.8F, 0.0F);
		this.pestal01.addBox(-1.5F, -1.5F, -4.4F, 3, 3, 13, 0.0F);
		this.setRotateAngle(pestal01, 0.0F, -0.3490658503988659F, 0.0F);
		this.hat01 = new ModelRenderer(this, 0, 64);
		this.hat01.setRotationPoint(-5.0F, -10.03F, -5.0F);
		this.hat01.addBox(0.0F, 0.0F, 0.0F, 10, 2, 10, 0.0F);
		this.rHair01 = new ModelRenderer(this, 83, 38);
		this.rHair01.setRotationPoint(-3.5F, -7.3F, 0.6F);
		this.rHair01.addBox(-0.5F, 0.0F, -3.5F, 1, 10, 7, 0.0F);
		this.setRotateAngle(rHair01, 0.6981317007977318F, 0.0F, 0.22689280275926282F);
		this.nose = new ModelRenderer(this, 24, 0);
		this.nose.setRotationPoint(0.0F, -2.0F, 0.0F);
		this.nose.addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, 0.0F);
		this.setRotateAngle(nose, 0.0F, 0.0F, 0.04363323129985824F);
		this.mortar01 = new ModelRenderer(this, 40, 0);
		this.mortar01.setRotationPoint(0.0F, 10.5F, 0.0F);
		this.mortar01.addBox(-6.5F, 0.0F, -6.2F, 13, 10, 4, 0.0F);
		this.mortar03 = new ModelRenderer(this, 76, 0);
		this.mortar03.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.mortar03.addBox(-6.5F, 0.0F, -2.5F, 4, 10, 5, 0.0F);
		this.hat04 = new ModelRenderer(this, 0, 95);
		this.hat04.setRotationPoint(1.75F, -2.0F, 2.0F);
		this.hat04.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
		this.setRotateAngle(hat04, -0.20943951023931953F, 0.0F, 0.10471975511965977F);
		this.mortar02 = new ModelRenderer(this, 94, 57);
		this.mortar02.mirror = true;
		this.mortar02.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.mortar02.addBox(-6.5F, 0.0F, 2.2F, 13, 10, 4, 0.0F);
		this.mortar05 = new ModelRenderer(this, 84, 8);
		this.mortar05.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.mortar05.addBox(-5.5F, 9.7F, -5.5F, 11, 4, 11, 0.0F);
		this.mhair02 = new ModelRenderer(this, 82, 27);
		this.mhair02.setRotationPoint(0.0F, -4.8F, 3.7F);
		this.mhair02.addBox(-3.0F, -1.0F, 0.0F, 6, 8, 1, 0.0F);
		this.setRotateAngle(mhair02, 0.6981317007977318F, 0.0F, 0.0F);
		this.pestal02 = new ModelRenderer(this, 37, 59);
		this.pestal02.setRotationPoint(0.0F, 0.0F, 8.4F);
		this.pestal02.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 9, 0.0F);
		this.hat03 = new ModelRenderer(this, 0, 87);
		this.hat03.setRotationPoint(1.75F, -4.0F, 2.0F);
		this.hat03.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
		this.setRotateAngle(hat03, -0.10471975511965977F, 0.0F, 0.05235987755982988F);
		this.lArm = new ModelRenderer(this, 44, 22);
		this.lArm.setRotationPoint(3.7F, 3.0F, -1.0F);
		this.lArm.addBox(0.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.setRotateAngle(lArm, -0.5235987755982988F, 0.15707963267948966F, 0.0F);
		this.lHair01 = new ModelRenderer(this, 65, 38);
		this.lHair01.setRotationPoint(3.5F, -7.3F, 0.6F);
		this.lHair01.addBox(-0.5F, 0.0F, -3.5F, 1, 10, 7, 0.0F);
		this.setRotateAngle(lHair01, 0.6981317007977318F, 0.0F, -0.22689280275926282F);
		this.robe = new ModelRenderer(this, 0, 38);
		this.robe.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.robe.addBox(-4.0F, 0.0F, -3.0F, 8, 11, 6, 0.5F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
		this.mhair01 = new ModelRenderer(this, 63, 25);
		this.mhair01.setRotationPoint(0.0F, -7.2F, 3.7F);
		this.mhair01.addBox(-3.5F, -1.0F, 0.0F, 7, 10, 1, 0.0F);
		this.setRotateAngle(mhair01, 0.8726646259971648F, 0.0F, 0.0F);
		this.body = new ModelRenderer(this, 16, 20);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
		this.hat02 = new ModelRenderer(this, 0, 76);
		this.hat02.setRotationPoint(1.75F, -4.0F, 2.0F);
		this.hat02.addBox(0.0F, 0.0F, 0.0F, 7, 4, 7, 0.0F);
		this.setRotateAngle(hat02, -0.05235987755982988F, 0.0F, 0.02617993877991494F);
		this.rArm = new ModelRenderer(this, 44, 22);
		this.rArm.mirror = true;
		this.rArm.setRotationPoint(-4.0F, 3.0F, -1.0F);
		this.rArm.addBox(-4.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.setRotateAngle(rArm, -0.8726646259971648F, 0.10471975511965977F, 0.0F);
		this.wart = new ModelRenderer(this, 0, 0);
		this.wart.mirror = true;
		this.wart.setRotationPoint(0.0F, -2.0F, 0.0F);
		this.wart.addBox(0.0F, 3.0F, -6.75F, 1, 1, 1, 0.0F);
		this.mortar01.addChild(this.mortar04);
		this.rArm.addChild(this.pestal01);
		this.head.addChild(this.hat01);
		this.head.addChild(this.rHair01);
		this.head.addChild(this.nose);
		this.mortar01.addChild(this.mortar03);
		this.hat03.addChild(this.hat04);
		this.mortar01.addChild(this.mortar02);
		this.mortar01.addChild(this.mortar05);
		this.head.addChild(this.mhair02);
		this.pestal01.addChild(this.pestal02);
		this.hat02.addChild(this.hat03);
		this.body.addChild(this.lArm);
		this.head.addChild(this.lHair01);
		this.body.addChild(this.head);
		this.head.addChild(this.mhair01);
		this.hat01.addChild(this.hat02);
		this.body.addChild(this.rArm);
		this.nose.addChild(this.wart);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.mortar01.render(f5);
		this.robe.render(f5);
		this.body.render(f5);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
