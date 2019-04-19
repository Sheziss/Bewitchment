package com.bewitchment.client.model.entity.spirits.demons;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created by Joseph on 4/18/2019.
 */
public class ModelInfernalSteed extends ModelBase {

	public ModelRenderer body;
	public ModelRenderer harness;
	public ModelRenderer lForeLeg01;
	public ModelRenderer rForeLeg01;
	public ModelRenderer lHindLeg01;
	public ModelRenderer rHindLeg01;
	public ModelRenderer neck;
	public ModelRenderer tail01;
	public ModelRenderer lWing01;
	public ModelRenderer rWing01;
	public ModelRenderer saddleBase;
	public ModelRenderer lChest;
	public ModelRenderer rChest;
	public ModelRenderer lForeLeg02;
	public ModelRenderer lForeHoof;
	public ModelRenderer lForelegFur;
	public ModelRenderer rForeLeg02;
	public ModelRenderer rForeHoof;
	public ModelRenderer rForelegFur;
	public ModelRenderer lHindLeg02;
	public ModelRenderer lHindHoof;
	public ModelRenderer lHindLegFur;
	public ModelRenderer rHindLeg02;
	public ModelRenderer rHindHoof;
	public ModelRenderer rHindLegFur;
	public ModelRenderer head;
	public ModelRenderer mane;
	public ModelRenderer maneR;
	public ModelRenderer maneL;
	public ModelRenderer upperJaw;
	public ModelRenderer lowerJaw;
	public ModelRenderer lEar;
	public ModelRenderer rEar;
	public ModelRenderer biteL;
	public ModelRenderer biteR;
	public ModelRenderer lLeash;
	public ModelRenderer rLeash;
	public ModelRenderer beard;
	public ModelRenderer tail02;
	public ModelRenderer tail03;
	public ModelRenderer tail04;
	public ModelRenderer tailFur01;
	public ModelRenderer tailFur02L;
	public ModelRenderer tailFur02R;
	public ModelRenderer tailFur03;
	public ModelRenderer lWing02;
	public ModelRenderer lMembrane01;
	public ModelRenderer lWing03;
	public ModelRenderer lMembrane02;
	public ModelRenderer lWing04;
	public ModelRenderer lMembrane03;
	public ModelRenderer lMembrane04;
	public ModelRenderer rWing02;
	public ModelRenderer rMembrane01;
	public ModelRenderer rWing03;
	public ModelRenderer rMembrane02;
	public ModelRenderer rWing04;
	public ModelRenderer rMembrane03;
	public ModelRenderer rMembrane04;
	public ModelRenderer saddleBack;
	public ModelRenderer saddleFront;
	public ModelRenderer saddleL01;
	public ModelRenderer saddleR01;
	public ModelRenderer saddleL02;
	public ModelRenderer saddleR02;

	public ModelInfernalSteed() {
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.tailFur02L = new ModelRenderer(this, 37, 37);
		this.tailFur02L.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tailFur02L.addBox(0.2F, -0.3F, 0.0F, 0, 4, 6, 0.0F);
		this.setRotateAngle(tailFur02L, 0.0F, 0.12217304763960307F, 0.0F);
		this.saddleFront = new ModelRenderer(this, 21, 102);
		this.saddleFront.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.saddleFront.addBox(-1.5F, -1.0F, -3.0F, 3, 1, 2, 0.0F);
		this.lHindLeg01 = new ModelRenderer(this, 93, 0);
		this.lHindLeg01.setRotationPoint(4.0F, -2.0F, 2.0F);
		this.lHindLeg01.addBox(-2.5F, -2.0F, -2.5F, 4, 9, 5, 0.0F);
		this.upperJaw = new ModelRenderer(this, 0, 48);
		this.upperJaw.setRotationPoint(0.0F, -8.45F, -1.5F);
		this.upperJaw.addBox(-2.0F, -1.5F, -6.0F, 4, 3, 6, 0.0F);
		this.saddleBase = new ModelRenderer(this, 0, 91);
		this.saddleBase.setRotationPoint(0.0F, -9.0F, -7.0F);
		this.saddleBase.addBox(-5.0F, 0.0F, -3.0F, 10, 1, 8, 0.0F);
		this.lowerJaw = new ModelRenderer(this, 22, 50);
		this.lowerJaw.setRotationPoint(0.0F, -6.0F, -2.0F);
		this.lowerJaw.addBox(-2.0F, -1.0F, -5.0F, 4, 2, 5, 0.0F);
		this.lHindLegFur = new ModelRenderer(this, 62, 10);
		this.lHindLegFur.setRotationPoint(-0.6F, 2.1F, 0.5F);
		this.lHindLegFur.addBox(-1.5F, -0.9F, -0.9F, 3, 6, 3, 0.0F);
		this.setRotateAngle(lHindLegFur, 0.41887902047863906F, 0.0F, 0.0F);
		this.lWing02 = new ModelRenderer(this, 0, 73);
		this.lWing02.setRotationPoint(6.1F, 0.0F, 0.0F);
		this.lWing02.addBox(0.0F, -1.0F, -1.5F, 15, 2, 3, 0.0F);
		this.setRotateAngle(lWing02, 0.0F, 2.6179938779914944F, 0.0F);
		this.neck = new ModelRenderer(this, 0, 0);
		this.neck.setRotationPoint(0.0F, -6.6F, -19.0F);
		this.neck.addBox(-2.05F, -9.8F, -2.0F, 4, 14, 8, 0.0F);
		this.setRotateAngle(neck, 0.5235987755982988F, 0.0F, 0.0F);
		this.tail02 = new ModelRenderer(this, 46, 2);
		this.tail02.setRotationPoint(0.0F, 0.0F, 6.8F);
		this.tail02.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 5, 0.0F);
		this.setRotateAngle(tail02, 0.20943951023931953F, 0.0F, 0.0F);
		this.rHindHoof = new ModelRenderer(this, 71, 25);
		this.rHindHoof.mirror = true;
		this.rHindHoof.setRotationPoint(0.0F, 5.0F, 0.0F);
		this.rHindHoof.addBox(-2.4F, 0.0F, -2.1F, 4, 3, 4, 0.0F);
		this.tailFur02R = new ModelRenderer(this, 50, 37);
		this.tailFur02R.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tailFur02R.addBox(-0.2F, -0.3F, 0.0F, 0, 4, 6, 0.0F);
		this.setRotateAngle(tailFur02R, 0.0F, -0.12217304763960307F, 0.0F);
		this.rWing02 = new ModelRenderer(this, 0, 73);
		this.rWing02.mirror = true;
		this.rWing02.setRotationPoint(-6.1F, 0.0F, 0.0F);
		this.rWing02.addBox(-15.0F, -1.0F, -1.5F, 15, 2, 3, 0.0F);
		this.setRotateAngle(rWing02, 0.0F, -2.6179938779914944F, 0.0F);
		this.rMembrane02 = new ModelRenderer(this, 39, 62);
		this.rMembrane02.mirror = true;
		this.rMembrane02.setRotationPoint(-7.5F, 0.0F, 0.0F);
		this.rMembrane02.addBox(-8.0F, 0.02F, 0.7F, 16, 0, 19, 0.0F);
		this.setRotateAngle(rMembrane02, 0.0F, 1.2217304763960306F, 0.0F);
		this.lWing03 = new ModelRenderer(this, 0, 80);
		this.lWing03.setRotationPoint(14.5F, 0.0F, 0.0F);
		this.lWing03.addBox(0.0F, -1.0F, -1.0F, 12, 2, 2, 0.0F);
		this.setRotateAngle(lWing03, 0.0F, -2.356194490192345F, 0.0F);
		this.rForeLeg02 = new ModelRenderer(this, 74, 14);
		this.rForeLeg02.mirror = true;
		this.rForeLeg02.setRotationPoint(0.0F, 7.0F, 0.0F);
		this.rForeLeg02.addBox(-1.1F, 0.0F, -1.6F, 3, 5, 3, 0.0F);
		this.lForeLeg02 = new ModelRenderer(this, 74, 14);
		this.lForeLeg02.setRotationPoint(0.0F, 7.0F, 0.0F);
		this.lForeLeg02.addBox(-1.9F, 0.0F, -1.6F, 3, 5, 3, 0.0F);
		this.lChest = new ModelRenderer(this, 60, 115);
		this.lChest.setRotationPoint(4.5F, -8.0F, 1.0F);
		this.lChest.addBox(-3.0F, 0.0F, 0.0F, 8, 8, 3, 0.0F);
		this.setRotateAngle(lChest, 0.0F, 1.5707963267948966F, 0.0F);
		this.tailFur03 = new ModelRenderer(this, 37, 30);
		this.tailFur03.setRotationPoint(0.0F, 0.3F, 0.5F);
		this.tailFur03.addBox(0.0F, 0.1F, 0.0F, 0, 4, 8, 0.0F);
		this.biteR = new ModelRenderer(this, 6, 112);
		this.biteR.mirror = true;
		this.biteR.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.biteR.addBox(-2.5F, -8.0F, -4.0F, 1, 2, 2, 0.0F);
		this.lMembrane03 = new ModelRenderer(this, 74, 62);
		this.lMembrane03.setRotationPoint(7.5F, 0.0F, 0.0F);
		this.lMembrane03.addBox(-6.5F, 0.01F, 0.7F, 12, 0, 22, 0.0F);
		this.saddleR02 = new ModelRenderer(this, 6, 106);
		this.saddleR02.mirror = true;
		this.saddleR02.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.saddleR02.addBox(-0.5F, 6.0F, -1.0F, 1, 2, 2, 0.0F);
		this.lLeash = new ModelRenderer(this, 25, 96);
		this.lLeash.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.lLeash.addBox(2.6F, -6.0F, -6.0F, 0, 3, 16, 0.0F);
		this.setRotateAngle(lLeash, -0.5235987755982988F, 0.0F, 0.0F);
		this.saddleL01 = new ModelRenderer(this, 0, 106);
		this.saddleL01.setRotationPoint(5.0F, 1.0F, 0.0F);
		this.saddleL01.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1, 0.0F);
		this.rWing04 = new ModelRenderer(this, 0, 80);
		this.rWing04.mirror = true;
		this.rWing04.setRotationPoint(-11.5F, 0.0F, 0.0F);
		this.rWing04.addBox(-12.0F, -0.5F, -1.0F, 12, 1, 2, 0.0F);
		this.setRotateAngle(rWing04, 0.0F, 0.41887902047863906F, 0.0F);
		this.saddleL02 = new ModelRenderer(this, 6, 106);
		this.saddleL02.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.saddleL02.addBox(-0.5F, 6.0F, -1.0F, 1, 2, 2, 0.0F);
		this.rHindLeg02 = new ModelRenderer(this, 96, 15);
		this.rHindLeg02.mirror = true;
		this.rHindLeg02.setRotationPoint(1.0F, 7.0F, 0.0F);
		this.rHindLeg02.addBox(-2.0F, 0.0F, -1.5F, 3, 5, 3, 0.0F);
		this.rMembrane04 = new ModelRenderer(this, 61, 85);
		this.rMembrane04.mirror = true;
		this.rMembrane04.setRotationPoint(-7.5F, 0.0F, 0.0F);
		this.rMembrane04.addBox(-14.0F, 0.0F, 0.7F, 20, 0, 22, 0.0F);
		this.setRotateAngle(rMembrane04, 0.0F, -0.7853981633974483F, 0.0F);
		this.lHindLeg02 = new ModelRenderer(this, 96, 15);
		this.lHindLeg02.setRotationPoint(0.0F, 7.0F, 0.0F);
		this.lHindLeg02.addBox(-2.0F, 0.0F, -1.5F, 3, 5, 3, 0.0F);
		this.rEar = new ModelRenderer(this, 26, 37);
		this.rEar.mirror = true;
		this.rEar.setRotationPoint(-1.35F, -10.0F, 4.6F);
		this.rEar.addBox(-1.0F, -2.0F, -0.5F, 2, 3, 1, 0.0F);
		this.lForeLeg01 = new ModelRenderer(this, 74, 0);
		this.lForeLeg01.setRotationPoint(4.0F, -1.9F, -17.0F);
		this.lForeLeg01.addBox(-1.9F, -1.0F, -2.1F, 3, 8, 4, 0.0F);
		this.lWing01 = new ModelRenderer(this, 0, 64);
		this.lWing01.setRotationPoint(4.7F, -5.9F, -13.3F);
		this.lWing01.addBox(0.0F, -1.5F, -1.5F, 7, 3, 3, 0.0F);
		this.setRotateAngle(lWing01, 0.0F, -1.3089969389957472F, 0.0F);
		this.harness = new ModelRenderer(this, 2, 109);
		this.harness.setRotationPoint(0.0F, 4.0F, -10.0F);
		this.harness.addBox(-2.5F, -10.1F, -7.0F, 5, 5, 12, 0.2F);
		this.setRotateAngle(harness, 0.5235987755982988F, 0.0F, 0.0F);
		this.rForeLeg01 = new ModelRenderer(this, 74, 0);
		this.rForeLeg01.mirror = true;
		this.rForeLeg01.setRotationPoint(-4.0F, -1.9F, -17.0F);
		this.rForeLeg01.addBox(-1.1F, -1.0F, -2.1F, 3, 8, 4, 0.0F);
		this.rHindLeg01 = new ModelRenderer(this, 93, 0);
		this.rHindLeg01.mirror = true;
		this.rHindLeg01.setRotationPoint(-4.0F, -2.0F, 2.0F);
		this.rHindLeg01.addBox(-1.5F, -2.0F, -2.5F, 4, 9, 5, 0.0F);
		this.rLeash = new ModelRenderer(this, 25, 96);
		this.rLeash.mirror = true;
		this.rLeash.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.rLeash.addBox(-2.6F, -6.0F, -6.0F, 0, 3, 16, 0.0F);
		this.setRotateAngle(rLeash, -0.5235987755982988F, 0.0F, 0.0F);
		this.tail04 = new ModelRenderer(this, 45, 11);
		this.tail04.setRotationPoint(0.0F, 0.0F, 5.8F);
		this.tail04.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 5, 0.0F);
		this.setRotateAngle(tail04, 0.17453292519943295F, 0.0F, 0.0F);
		this.biteL = new ModelRenderer(this, 6, 112);
		this.biteL.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.biteL.addBox(1.5F, -8.0F, -4.0F, 1, 2, 2, 0.0F);
		this.head = new ModelRenderer(this, 0, 35);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-2.5F, -10.0F, -1.5F, 5, 5, 7, 0.0F);
		this.beard = new ModelRenderer(this, 94, 26);
		this.beard.setRotationPoint(0.0F, 0.3F, -2.2F);
		this.beard.addBox(-1.5F, 0.0F, -1.5F, 3, 4, 3, 0.0F);
		this.setRotateAngle(beard, -0.41887902047863906F, 0.0F, 0.0F);
		this.lMembrane02 = new ModelRenderer(this, 39, 62);
		this.lMembrane02.setRotationPoint(7.5F, 0.0F, 0.0F);
		this.lMembrane02.addBox(-8.0F, 0.02F, 0.7F, 16, 0, 19, 0.0F);
		this.setRotateAngle(lMembrane02, 0.0F, -1.2217304763960306F, 0.0F);
		this.tailFur01 = new ModelRenderer(this, 39, 44);
		this.tailFur01.setRotationPoint(0.0F, 0.3F, 3.1F);
		this.tailFur01.addBox(0.0F, -0.4F, -0.7F, 0, 3, 5, 0.0F);
		this.setRotateAngle(tailFur01, -0.13962634015954636F, 0.0F, 0.0F);
		this.mane = new ModelRenderer(this, 111, 21);
		this.mane.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.mane.addBox(-1.0F, -10.3F, 5.0F, 2, 13, 2, 0.0F);
		this.rWing03 = new ModelRenderer(this, 0, 80);
		this.rWing03.mirror = true;
		this.rWing03.setRotationPoint(-14.5F, 0.0F, 0.0F);
		this.rWing03.addBox(-12.0F, -1.0F, -1.0F, 12, 2, 2, 0.0F);
		this.setRotateAngle(rWing03, 0.0F, 2.356194490192345F, 0.0F);
		this.lMembrane01 = new ModelRenderer(this, 25, 62);
		this.lMembrane01.setRotationPoint(3.6F, 0.0F, 0.0F);
		this.lMembrane01.addBox(-3.5F, 0.03F, 0.0F, 8, 0, 16, 0.0F);
		this.lForeHoof = new ModelRenderer(this, 71, 25);
		this.lForeHoof.setRotationPoint(0.0F, 5.0F, 0.0F);
		this.lForeHoof.addBox(-2.4F, 0.0F, -2.1F, 4, 3, 4, 0.0F);
		this.tail03 = new ModelRenderer(this, 44, 10);
		this.tail03.setRotationPoint(0.0F, 0.0F, 4.8F);
		this.tail03.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 6, 0.0F);
		this.setRotateAngle(tail03, 0.2792526803190927F, 0.0F, 0.0F);
		this.lEar = new ModelRenderer(this, 26, 37);
		this.lEar.setRotationPoint(1.35F, -10.0F, 4.6F);
		this.lEar.addBox(-1.0F, -2.0F, -0.5F, 2, 3, 1, 0.0F);
		this.rForeHoof = new ModelRenderer(this, 71, 25);
		this.rForeHoof.mirror = true;
		this.rForeHoof.setRotationPoint(0.0F, 5.0F, 0.0F);
		this.rForeHoof.addBox(-1.6F, 0.0F, -2.1F, 4, 3, 4, 0.0F);
		this.tail01 = new ModelRenderer(this, 44, 0);
		this.tail01.setRotationPoint(0.0F, -8.0F, 5.0F);
		this.tail01.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 7, 0.0F);
		this.setRotateAngle(tail01, -1.0471975511965976F, 0.0F, 0.0F);
		this.rChest = new ModelRenderer(this, 60, 115);
		this.rChest.mirror = true;
		this.rChest.setRotationPoint(-4.5F, -8.0F, 1.0F);
		this.rChest.addBox(-3.0F, 0.0F, -3.0F, 8, 8, 3, 0.0F);
		this.setRotateAngle(rChest, 0.0F, 1.5707963267948966F, 0.0F);
		this.rMembrane03 = new ModelRenderer(this, 74, 62);
		this.rMembrane03.mirror = true;
		this.rMembrane03.setRotationPoint(-7.5F, 0.0F, 0.0F);
		this.rMembrane03.addBox(-5.5F, 0.01F, 0.7F, 12, 0, 22, 0.0F);
		this.lWing04 = new ModelRenderer(this, 0, 80);
		this.lWing04.setRotationPoint(11.5F, 0.0F, 0.0F);
		this.lWing04.addBox(0.0F, -0.5F, -1.0F, 12, 1, 2, 0.0F);
		this.setRotateAngle(lWing04, 0.0F, -0.41887902047863906F, 0.0F);
		this.lHindHoof = new ModelRenderer(this, 71, 25);
		this.lHindHoof.setRotationPoint(0.0F, 5.0F, 0.0F);
		this.lHindHoof.addBox(-2.4F, 0.0F, -2.1F, 4, 3, 4, 0.0F);
		this.rForelegFur = new ModelRenderer(this, 62, 0);
		this.rForelegFur.mirror = true;
		this.rForelegFur.setRotationPoint(0.5F, 2.1F, 0.5F);
		this.rForelegFur.addBox(-1.5F, -0.9F, -0.9F, 3, 6, 3, 0.0F);
		this.setRotateAngle(rForelegFur, 0.41887902047863906F, 0.0F, 0.0F);
		this.maneL = new ModelRenderer(this, 83, 36);
		this.maneL.setRotationPoint(0.9F, -3.0F, 6.2F);
		this.maneL.addBox(0.0F, -6.4F, -3.2F, 2, 12, 4, 0.0F);
		this.saddleBack = new ModelRenderer(this, 0, 102);
		this.saddleBack.setRotationPoint(0.0F, 0.1F, 0.0F);
		this.saddleBack.addBox(-4.0F, -1.0F, 3.0F, 8, 1, 2, 0.0F);
		this.body = new ModelRenderer(this, 0, 0);
		this.body.setRotationPoint(0.0F, 11.0F, 9.0F);
		this.body.addBox(-5.0F, -8.0F, -19.0F, 10, 10, 24, 0.0F);
		this.rHindLegFur = new ModelRenderer(this, 62, 10);
		this.rHindLegFur.setRotationPoint(-0.4F, 2.1F, 0.5F);
		this.rHindLegFur.addBox(-1.5F, -0.9F, -0.9F, 3, 6, 3, 0.0F);
		this.setRotateAngle(rHindLegFur, 0.41887902047863906F, 0.0F, 0.0F);
		this.lMembrane04 = new ModelRenderer(this, 61, 85);
		this.lMembrane04.setRotationPoint(7.5F, 0.0F, 0.0F);
		this.lMembrane04.addBox(-6.0F, 0.0F, 0.7F, 20, 0, 22, 0.0F);
		this.setRotateAngle(lMembrane04, 0.0F, 0.7853981633974483F, 0.0F);
		this.rWing01 = new ModelRenderer(this, 0, 64);
		this.rWing01.mirror = true;
		this.rWing01.setRotationPoint(-4.7F, -5.9F, -13.3F);
		this.rWing01.addBox(-7.0F, -1.5F, -1.5F, 7, 3, 3, 0.0F);
		this.setRotateAngle(rWing01, 0.0F, 1.3089969389957472F, 0.0F);
		this.lForelegFur = new ModelRenderer(this, 62, 0);
		this.lForelegFur.setRotationPoint(-0.5F, 2.1F, 0.5F);
		this.lForelegFur.addBox(-1.5F, -0.9F, -0.9F, 3, 6, 3, 0.0F);
		this.setRotateAngle(lForelegFur, 0.41887902047863906F, 0.0F, 0.0F);
		this.rMembrane01 = new ModelRenderer(this, 25, 62);
		this.rMembrane01.mirror = true;
		this.rMembrane01.setRotationPoint(-3.6F, 0.0F, 0.0F);
		this.rMembrane01.addBox(-4.5F, 0.03F, 0.0F, 8, 0, 16, 0.0F);
		this.maneR = new ModelRenderer(this, 96, 33);
		this.maneR.setRotationPoint(-0.4F, -3.0F, 6.2F);
		this.maneR.addBox(-2.6F, -6.4F, -9.3F, 2, 12, 10, 0.0F);
		this.saddleR01 = new ModelRenderer(this, 0, 106);
		this.saddleR01.mirror = true;
		this.saddleR01.setRotationPoint(-5.0F, 1.0F, 0.0F);
		this.saddleR01.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1, 0.0F);
		this.tail04.addChild(this.tailFur02L);
		this.saddleBase.addChild(this.saddleFront);
		this.body.addChild(this.lHindLeg01);
		this.head.addChild(this.upperJaw);
		this.body.addChild(this.saddleBase);
		this.head.addChild(this.lowerJaw);
		this.lHindLeg02.addChild(this.lHindLegFur);
		this.lWing01.addChild(this.lWing02);
		this.body.addChild(this.neck);
		this.tail01.addChild(this.tail02);
		this.rHindLeg02.addChild(this.rHindHoof);
		this.tail04.addChild(this.tailFur02R);
		this.rWing01.addChild(this.rWing02);
		this.rWing02.addChild(this.rMembrane02);
		this.lWing02.addChild(this.lWing03);
		this.rForeLeg01.addChild(this.rForeLeg02);
		this.lForeLeg01.addChild(this.lForeLeg02);
		this.body.addChild(this.lChest);
		this.tail04.addChild(this.tailFur03);
		this.head.addChild(this.biteR);
		this.lWing03.addChild(this.lMembrane03);
		this.saddleR01.addChild(this.saddleR02);
		this.head.addChild(this.lLeash);
		this.saddleBase.addChild(this.saddleL01);
		this.rWing03.addChild(this.rWing04);
		this.saddleL01.addChild(this.saddleL02);
		this.rHindLeg01.addChild(this.rHindLeg02);
		this.rWing04.addChild(this.rMembrane04);
		this.lHindLeg01.addChild(this.lHindLeg02);
		this.head.addChild(this.rEar);
		this.body.addChild(this.lForeLeg01);
		this.body.addChild(this.lWing01);
		this.body.addChild(this.rForeLeg01);
		this.body.addChild(this.rHindLeg01);
		this.head.addChild(this.rLeash);
		this.tail03.addChild(this.tail04);
		this.head.addChild(this.biteL);
		this.neck.addChild(this.head);
		this.lowerJaw.addChild(this.beard);
		this.lWing02.addChild(this.lMembrane02);
		this.tail03.addChild(this.tailFur01);
		this.neck.addChild(this.mane);
		this.rWing02.addChild(this.rWing03);
		this.lWing01.addChild(this.lMembrane01);
		this.lForeLeg02.addChild(this.lForeHoof);
		this.tail02.addChild(this.tail03);
		this.head.addChild(this.lEar);
		this.rForeLeg02.addChild(this.rForeHoof);
		this.body.addChild(this.tail01);
		this.body.addChild(this.rChest);
		this.rWing03.addChild(this.rMembrane03);
		this.lWing03.addChild(this.lWing04);
		this.lHindLeg02.addChild(this.lHindHoof);
		this.rForeLeg02.addChild(this.rForelegFur);
		this.neck.addChild(this.maneL);
		this.saddleBase.addChild(this.saddleBack);
		this.rHindLeg02.addChild(this.rHindLegFur);
		this.lWing04.addChild(this.lMembrane04);
		this.body.addChild(this.rWing01);
		this.lForeLeg02.addChild(this.lForelegFur);
		this.rWing01.addChild(this.rMembrane01);
		this.neck.addChild(this.maneR);
		this.saddleBase.addChild(this.saddleR01);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.harness.render(f5);
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