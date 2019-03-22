package com.bewitchment.client.entity.model.living;

import com.bewitchment.common.entity.living.EntityOwl;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelOwl extends ModelBase
{
	public ModelRenderer owlBody;
	public ModelRenderer tail;
	public ModelRenderer tailRight;
	public ModelRenderer tailLeft;
	public ModelRenderer owlHead;
	public ModelRenderer owlRightClaw;
	public ModelRenderer owlLeftClaw;
	public ModelRenderer wingLeft01;
	public ModelRenderer wingRight01;
	public ModelRenderer owlBodyfluff;
	public ModelRenderer owlBeak;
	public ModelRenderer owlRightEar;
	public ModelRenderer owlLeftEar;
	public ModelRenderer rTalon01;
	public ModelRenderer rTalon02;
	public ModelRenderer rTalon03;
	public ModelRenderer rTalon04;
	public ModelRenderer lTalon01;
	public ModelRenderer lTalon02;
	public ModelRenderer lTalon03;
	public ModelRenderer lTalon04;
	public ModelRenderer wingLeft02;
	public ModelRenderer wingLeftAlt01a;
	public ModelRenderer wingLeft03;
	public ModelRenderer wingLeftAlt02a;
	public ModelRenderer wingLeftAlt03a;
	public ModelRenderer wingLeftAlt03b;
	public ModelRenderer wingLeftAlt03c;
	public ModelRenderer wingLeftAlt02b;
	public ModelRenderer wingLeftAlt02c;
	public ModelRenderer wingLeftAlt01b;
	public ModelRenderer wingLeftAlt01c;
	public ModelRenderer wingRight02;
	public ModelRenderer wingRightAlt01a;
	public ModelRenderer wingRight03;
	public ModelRenderer wingRightAlt02a;
	public ModelRenderer wingRightAlt03a;
	public ModelRenderer wingRightAlt03b;
	public ModelRenderer wingRightAlt03c;
	public ModelRenderer wingRightAlt02b;
	public ModelRenderer wingRightAlt02c;
	public ModelRenderer wingRightAlt01b;
	public ModelRenderer wingRightAlt01c;
	
	public ModelOwl()
	{
		textureWidth = 64;
		textureHeight = 64;
		lTalon03 = new ModelRenderer(this, 56, 0);
		lTalon03.addBox(-0.5f, -1, 0, 1, 2, 3, 0);
		wingRightAlt02a = new ModelRenderer(this, 0, 45);
		wingRightAlt02a.mirror = true;
		wingRightAlt02a.addBox(-6, 0, -0.9900000095367432f, 6, 2, 2, 0);
		owlHead = new ModelRenderer(this, 28, 0);
		owlHead.addBox(-5, -4, -4, 10, 8, 8, 0);
		owlBeak = new ModelRenderer(this, 19, 1);
		owlBeak.addBox(0, 0, 0.4000000059604645f, 2, 4, 1, 0);
		wingLeftAlt01c = new ModelRenderer(this, 18, 16);
		wingLeftAlt01c.addBox(0, 0, 0, 6, 8, 0, 0);
		lTalon02 = new ModelRenderer(this, 19, 8);
		lTalon02.addBox(-0.5f, -1, -3, 1, 2, 3, 0);
		wingLeft03 = new ModelRenderer(this, 0, 30);
		wingLeft03.addBox(0, 0, -1, 6, 8, 2, 0);
		wingRight03 = new ModelRenderer(this, 0, 30);
		wingRight03.mirror = true;
		wingRight03.addBox(-6, 0, -2, 6, 8, 2, 0);
		wingRightAlt03b = new ModelRenderer(this, 49, 60);
		wingRightAlt03b.mirror = true;
		wingRightAlt03b.addBox(-3, 0, -0.5f, 6, 2, 1, 0);
		tail = new ModelRenderer(this, 25, 45);
		tail.addBox(0, 0, 0, 4, 2, 8, 0);
		rTalon01 = new ModelRenderer(this, 19, 8);
		rTalon01.mirror = true;
		rTalon01.addBox(-0.5f, -1, -3, 1, 2, 3, 0);
		owlBody = new ModelRenderer(this, 24, 16);
		owlBody.addBox(-6, -8, -4, 12, 14, 8, 0);
		wingRightAlt03a = new ModelRenderer(this, 0, 49);
		wingRightAlt03a.mirror = true;
		wingRightAlt03a.addBox(-6, 0, -1, 6, 2, 2, 0);
		tailRight = new ModelRenderer(this, 42, 40);
		tailRight.mirror = true;
		tailRight.addBox(-4, 0, 0, 4, 2, 6, 0);
		wingRight01 = new ModelRenderer(this, 0, 8);
		wingRight01.mirror = true;
		wingRight01.addBox(-6, 0, -1, 6, 8, 2, 0);
		wingLeftAlt02a = new ModelRenderer(this, 0, 45);
		wingLeftAlt02a.addBox(0, 0, -0.9900000095367432f, 6, 2, 2, 0);
		wingRightAlt02b = new ModelRenderer(this, 49, 57);
		wingRightAlt02b.mirror = true;
		wingRightAlt02b.addBox(-3, 0, -0.5099999904632568f, 6, 2, 1, 0);
		owlLeftClaw = new ModelRenderer(this, 28, 56);
		owlLeftClaw.addBox(-1.5f, 0, -5, 3, 2, 6, 0);
		lTalon04 = new ModelRenderer(this, 56, 0);
		lTalon04.addBox(-0.5f, -1, 0, 1, 2, 3, 0);
		owlLeftEar = new ModelRenderer(this, 27, 1);
		owlLeftEar.addBox(-1, -4, -1, 2, 4, 2, 0);
		wingRightAlt01b = new ModelRenderer(this, 49, 54);
		wingRightAlt01b.mirror = true;
		wingRightAlt01b.addBox(-3, 0, -0.5f, 6, 2, 1, 0);
		wingRightAlt01a = new ModelRenderer(this, 0, 41);
		wingRightAlt01a.mirror = true;
		wingRightAlt01a.addBox(-6, 0, -1, 6, 2, 2, 0);
		owlBodyfluff = new ModelRenderer(this, 0, 0);
		owlBodyfluff.addBox(-3.5f, 0, -1, 7, 5, 2, 0);
		wingLeftAlt01b = new ModelRenderer(this, 49, 54);
		wingLeftAlt01b.addBox(-3, 0, -0.5f, 6, 2, 1, 0);
		owlRightClaw = new ModelRenderer(this, 28, 56);
		owlRightClaw.addBox(-1.5f, 0, -5, 3, 2, 6, 0);
		wingLeft02 = new ModelRenderer(this, 0, 19);
		wingLeft02.addBox(0, 0, -0.9900000095367432f, 6, 8, 2, 0);
		wingLeftAlt03a = new ModelRenderer(this, 0, 49);
		wingLeftAlt03a.addBox(0, 0, -2, 6, 2, 2, 0);
		wingRightAlt02c = new ModelRenderer(this, 19, 42);
		wingRightAlt02c.mirror = true;
		wingRightAlt02c.addBox(0, 0, 0.10000000149011612f, 6, 8, 0, 0);
		wingRightAlt01c = new ModelRenderer(this, 18, 16);
		wingRightAlt01c.mirror = true;
		wingRightAlt01c.addBox(0, 0, 0, 6, 8, 0, 0);
		wingLeftAlt02c = new ModelRenderer(this, 19, 42);
		wingLeftAlt02c.addBox(0, 0, 0.10000000149011612f, 6, 8, 0, 0);
		wingRightAlt03c = new ModelRenderer(this, 0, 53);
		wingRightAlt03c.mirror = true;
		wingRightAlt03c.addBox(0, -2.9000000953674316f, 0, 12, 11, 0, 0);
		wingLeft01 = new ModelRenderer(this, 0, 8);
		wingLeft01.addBox(0, 0, -1, 6, 8, 2, 0);
		wingRight02 = new ModelRenderer(this, 0, 19);
		wingRight02.mirror = true;
		wingRight02.addBox(-6, 0, -1.9900000095367432f, 6, 8, 2, 0);
		lTalon01 = new ModelRenderer(this, 19, 8);
		lTalon01.addBox(-0.5f, -1, -3, 1, 2, 3, 0);
		wingLeftAlt01a = new ModelRenderer(this, 0, 41);
		wingLeftAlt01a.addBox(0, 0, -1, 6, 2, 2, 0);
		rTalon04 = new ModelRenderer(this, 56, 0);
		rTalon04.mirror = true;
		rTalon04.addBox(-0.5f, -1, 0, 1, 2, 3, 0);
		wingLeftAlt03b = new ModelRenderer(this, 49, 60);
		wingLeftAlt03b.addBox(-3, 0, -0.5f, 6, 2, 1, 0);
		wingLeftAlt02b = new ModelRenderer(this, 49, 57);
		wingLeftAlt02b.addBox(-3, 0, -0.5099999904632568f, 6, 2, 1, 0);
		owlRightEar = new ModelRenderer(this, 27, 1);
		owlRightEar.mirror = true;
		owlRightEar.addBox(-1, -4, -1, 2, 4, 2, 0);
		tailLeft = new ModelRenderer(this, 42, 40);
		tailLeft.addBox(0, 0, 0, 4, 2, 6, 0);
		wingLeftAlt03c = new ModelRenderer(this, 0, 53);
		wingLeftAlt03c.addBox(0, -2.9000000953674316f, 0, 12, 11, 0, 0);
		rTalon02 = new ModelRenderer(this, 19, 8);
		rTalon02.mirror = true;
		rTalon02.addBox(-0.5f, -1, -3, 1, 2, 3, 0);
		rTalon03 = new ModelRenderer(this, 56, 0);
		rTalon03.mirror = true;
		rTalon03.addBox(-0.5f, -1, 0, 1, 2, 3, 0);
		owlLeftClaw.addChild(lTalon03);
		wingRight02.addChild(wingRightAlt02a);
		owlBody.addChild(owlHead);
		owlHead.addChild(owlBeak);
		wingLeftAlt01b.addChild(wingLeftAlt01c);
		owlLeftClaw.addChild(lTalon02);
		wingLeft02.addChild(wingLeft03);
		wingRight02.addChild(wingRight03);
		wingRightAlt03a.addChild(wingRightAlt03b);
		owlBody.addChild(tail);
		owlRightClaw.addChild(rTalon01);
		wingRight03.addChild(wingRightAlt03a);
		owlBody.addChild(tailRight);
		owlBody.addChild(wingRight01);
		wingLeft02.addChild(wingLeftAlt02a);
		wingRightAlt02a.addChild(wingRightAlt02b);
		owlBody.addChild(owlLeftClaw);
		owlLeftClaw.addChild(lTalon04);
		owlHead.addChild(owlLeftEar);
		wingRightAlt01a.addChild(wingRightAlt01b);
		wingRight01.addChild(wingRightAlt01a);
		owlBody.addChild(owlBodyfluff);
		wingLeftAlt01a.addChild(wingLeftAlt01b);
		owlBody.addChild(owlRightClaw);
		wingLeft01.addChild(wingLeft02);
		wingLeft03.addChild(wingLeftAlt03a);
		wingRightAlt02b.addChild(wingRightAlt02c);
		wingRightAlt01b.addChild(wingRightAlt01c);
		wingLeftAlt02b.addChild(wingLeftAlt02c);
		wingRightAlt03b.addChild(wingRightAlt03c);
		owlBody.addChild(wingLeft01);
		wingRight01.addChild(wingRight02);
		owlLeftClaw.addChild(lTalon01);
		wingLeft01.addChild(wingLeftAlt01a);
		owlRightClaw.addChild(rTalon04);
		wingLeftAlt03a.addChild(wingLeftAlt03b);
		wingLeftAlt02a.addChild(wingLeftAlt02b);
		owlHead.addChild(owlRightEar);
		owlBody.addChild(tailLeft);
		wingLeftAlt03b.addChild(wingLeftAlt03c);
		owlRightClaw.addChild(rTalon02);
		owlRightClaw.addChild(rTalon03);
		setWanderingStance();
	}
	
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float age, float rotationYaw, float rotationPitch, float scale)
	{
		GlStateManager.pushMatrix();
		GlStateManager.translate(owlBody.offsetX, owlBody.offsetY, owlBody.offsetZ);
		GlStateManager.translate(owlBody.rotationPointX * scale, owlBody.rotationPointY * scale, owlBody.rotationPointZ * scale);
		GlStateManager.scale(1, 1, 1);
		GlStateManager.translate(-owlBody.offsetX, -owlBody.offsetY, -owlBody.offsetZ);
		GlStateManager.translate(-owlBody.rotationPointX * scale, -owlBody.rotationPointY * scale, -owlBody.rotationPointZ * scale);
		owlBody.render(scale);
		GlStateManager.popMatrix();
	}
	
	@Override
	public void setLivingAnimations(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTickTime)
	{
		super.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTickTime);
		EntityOwl owl = (EntityOwl) entity;
		if (owl.onGround)
		{
			if (owl.isSitting())
			{
				setSittingStance();
				float time = (owl.ticksExisted + partialTickTime) * 0.10471975512f;
				owlBody.rotateAngleX = 0.08726646259f + 0.00484813681f * MathHelper.sin(time);

			}
			else
			{
				setWanderingStance();
				float time = (owl.ticksExisted + partialTickTime) * 0.10471975512f;
				owlBody.rotateAngleX = 0.08726646259f + 0.00484813681f * MathHelper.sin(time);
			}
		}
		else
		{
			setflyingStance();
			float time = (owl.ticksExisted + partialTickTime) / 4.71238898f;
			wingRight01.rotateAngleY = 0.26179938779914943f + 1.047166666666666f * MathHelper.cos(time);
			wingLeft01.rotateAngleY = -wingRight01.rotateAngleY;
			wingRight02.rotateAngleY = -0.52359877559f + 0.34906585039f * MathHelper.sin(time);
			wingLeft02.rotateAngleY = -wingRight02.rotateAngleY;
			wingRight03.rotateAngleY = wingRight01.rotateAngleY / 4;
			wingLeft03.rotateAngleY = -wingRight03.rotateAngleY;
			tail.rotateAngleX = -1.0471975511965976f + wingRight03.rotateAngleY / 4;
			tailRight.rotateAngleX = -0.8726646259971648f + tail.rotateAngleX + 1.0471975511965976f;
			tailLeft.rotateAngleX = tailRight.rotateAngleX;
			owlRightClaw.rotateAngleX = wingRight03.rotateAngleY / 4;
			owlLeftClaw.rotateAngleX = owlRightClaw.rotateAngleX;
		}
	}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float age, float rotationYaw, float rotationPitch, float scale, Entity entity)
	{
		if (entity.onGround) setRotationAngles(owlHead, (float) (rotationPitch * Math.PI / 360), (float) (rotationYaw * Math.PI / 360), 0);
	}
	
	public void setRotationAngles(ModelRenderer renderer, float x, float y, float z)
	{
		renderer.rotateAngleX = x;
		renderer.rotateAngleY = y;
		renderer.rotateAngleZ = z;
	}
	
	private void setflyingStance()
	{
		wingLeftAlt03a.setRotationPoint(0, 0, 1);
		wingRightAlt01a.setRotationPoint(0, 0, 0);
		owlRightClaw.setRotationPoint(-3, 6, -1.5f);
		this.setRotationAngles(owlRightClaw, 0.8726646259971648f, 0.08726646259971647f, 0);
		wingRight02.setRotationPoint(-6, 0, 1);
		this.setRotationAngles(wingRight02, 0, -0.4363323129985824f, 0);
		wingRightAlt03b.setRotationPoint(-3, 1.7999999523162842f, 0);
		owlBeak.setRotationPoint(-1, 0, -5);
		this.setRotationAngles(owlBeak, -0.08726646259971647f, 0, 0);
		owlLeftEar.setRotationPoint(4, -3.4000000953674316f, 0);
		this.setRotationAngles(owlLeftEar, -0.3490658503988659f, 0, 0.2617993877991494f);
		wingRight03.setRotationPoint(-6, 0, 0);
		this.setRotationAngles(wingRight03, 0, -0.3490658503988659f, 0);
		owlHead.setRotationPoint(0, -12, 0);
		this.setRotationAngles(owlHead, -1.48352986419518f, 0, 0);
		lTalon01.setRotationPoint(0.699999988079071f, 1, -3.5999999046325684f);
		this.setRotationAngles(lTalon01, -0.17453292519943295f, -0.10471975511965977f, 0);
		wingRightAlt01b.setRotationPoint(-3, 1.7999999523162842f, 0);
		wingLeftAlt01a.setRotationPoint(0, 0, 0);
		wingLeftAlt03c.setRotationPoint(-3, 0.30000001192092896f, 0);
		wingRightAlt02c.setRotationPoint(-3, 1.2999999523162842f, 0);
		wingLeft02.setRotationPoint(6, 0, 0);
		this.setRotationAngles(wingLeft02, 0, 0.4363323129985824f, 0);
		owlRightEar.setRotationPoint(-4, -3.4000000953674316f, 0);
		this.setRotationAngles(owlRightEar, -0.3490658503988659f, 0, -0.2617993877991494f);
		wingRight01.setRotationPoint(-6, -7, 0);
		this.setRotationAngles(wingRight01, -0.08726646259971647f, 0.6108652381980153f, 0);
		tail.setRotationPoint(-2, 2.5f, 4);
		this.setRotationAngles(tail, -1.0471975511965976f, 0, 0);
		wingRightAlt02a.setRotationPoint(0, 0, -1);
		wingRightAlt02b.setRotationPoint(-3, 1.7999999523162842f, 0);
		rTalon03.setRotationPoint(-0.699999988079071f, 1.2000000476837158f, 0.5f);
		this.setRotationAngles(rTalon03, -0.17453292519943295f, -0.06981317007977318f, 0);
		wingLeftAlt03b.setRotationPoint(3, 1.7999999523162842f, -1);
		tailRight.setRotationPoint(-2, 2.5f, 4);
		this.setRotationAngles(tailRight, -0.8726646259971648f, -0.4363323129985824f, -0.17453292519943295f);
		lTalon02.setRotationPoint(-0.699999988079071f, 1, -3.5999999046325684f);
		this.setRotationAngles(lTalon02, -0.17453292519943295f, 0.10471975511965977f, 0);
		wingLeftAlt02c.setRotationPoint(-3, 1.2999999523162842f, 0);
		wingLeftAlt01b.setRotationPoint(3, 1.7999999523162842f, 0);
		wingLeft01.setRotationPoint(6, -7, 0);
		this.setRotationAngles(wingLeft01, -0.08726646259971647f, -0.6108652381980153f, 0);
		owlBodyfluff.setRotationPoint(0, -6.400000095367432f, -3.0999999046325684f);
		this.setRotationAngles(owlBodyfluff, -0.3665191429188092f, 0, 0);
		wingLeftAlt01c.setRotationPoint(-3, 1.7999999523162842f, 0);
		wingRightAlt03c.setRotationPoint(-9, 0.30000001192092896f, 0);
		owlBody.setRotationPoint(0, 16, 0);
		this.setRotationAngles(owlBody, 1.48352986419518f, 0, 0);
		wingRightAlt03a.setRotationPoint(0, 0, -1);
		wingLeftAlt02b.setRotationPoint(3, 1.7999999523162842f, 0);
		tailLeft.setRotationPoint(2, 2.5f, 4);
		this.setRotationAngles(tailLeft, -0.8726646259971648f, 0.4363323129985824f, 0.17453292519943295f);
		rTalon04.setRotationPoint(0.699999988079071f, 1.2000000476837158f, 0.5f);
		this.setRotationAngles(rTalon04, -0.17453292519943295f, 0.06981317007977318f, 0);
		wingLeft03.setRotationPoint(6, 0, 0);
		this.setRotationAngles(wingLeft03, 0, 0.3490658503988659f, 0);
		wingLeftAlt02a.setRotationPoint(0, 0, 0);
		lTalon04.setRotationPoint(-0.699999988079071f, 1.2000000476837158f, 0.5f);
		this.setRotationAngles(lTalon04, -0.17453292519943295f, -0.06981317007977318f, 0);
		wingRightAlt01c.setRotationPoint(-3, 1.7999999523162842f, 0);
		owlLeftClaw.setRotationPoint(3, 6, -1.5f);
		this.setRotationAngles(owlLeftClaw, 0.8726646259971648f, -0.08726646259971647f, 0);
		lTalon03.setRotationPoint(0.6000000238418579f, 1.2000000476837158f, 0.5f);
		this.setRotationAngles(lTalon03, -0.17453292519943295f, 0.06981317007977318f, 0);
		rTalon01.setRotationPoint(-0.699999988079071f, 1, -3.5999999046325684f);
		this.setRotationAngles(rTalon01, -0.17453292519943295f, 0.10471975511965977f, 0);
		rTalon02.setRotationPoint(0.699999988079071f, 1, -3.5999999046325684f);
		this.setRotationAngles(rTalon02, -0.17453292519943295f, -0.10471975511965977f, 0);
	}
	
	private void setSittingStance()
	{
		setWanderingStance();
		this.setRotationAngles(owlLeftClaw, -0.08726646259971647f, -0.3f, 0);
		this.setRotationAngles(owlRightClaw, -0.08726646259971647f, 0.3f, 0);
		this.setRotationAngles(owlBody, 0.08726646259971647f, 0, 0);
	}
	
	private void setWanderingStance()
	{
		lTalon03.setRotationPoint(0.6000000238418579f, 1.2000000476837158f, 0.5f);
		this.setRotationAngles(lTalon03, -0.17453292519943295f, 0.06981317007977318f, 0);
		wingRightAlt02a.setRotationPoint(0, 0, -1);
		wingRightAlt02b.setRotationPoint(-3, 1.7999999523162842f, 0);
		owlHead.setRotationPoint(0, -12, 0);
		this.setRotationAngles(owlHead, -0.06981317007977318f, 0, 0);
		owlBeak.setRotationPoint(-1, 0, -5);
		this.setRotationAngles(owlBeak, -0.08726646259971647f, 0, 0);
		wingLeftAlt01c.setRotationPoint(-3, 1.7999999523162842f, 0);
		lTalon02.setRotationPoint(-0.699999988079071f, 1, -3.5999999046325684f);
		this.setRotationAngles(lTalon02, -0.17453292519943295f, 0.10471975511965977f, 0);
		wingLeft03.setRotationPoint(6, 0, 0);
		this.setRotationAngles(wingLeft03, 0, 0, 0.3490658503988659f);
		wingRight03.setRotationPoint(-6, 0, 0);
		this.setRotationAngles(wingRight03, 0, 0, -0.3490658503988659f);
		wingRightAlt03b.setRotationPoint(-3, 1.7999999523162842f, 0);
		tail.setRotationPoint(-2, 2.5f, 4);
		this.setRotationAngles(tail, -1.0471975511965976f, 0, 0);
		rTalon01.setRotationPoint(-0.699999988079071f, 1, -3.5999999046325684f);
		this.setRotationAngles(rTalon01, -0.17453292519943295f, 0.10471975511965977f, 0);
		owlBody.setRotationPoint(0, 16, 0);
		this.setRotationAngles(owlBody, 0.08726646259971647f, 0, 0);
		wingRightAlt03a.setRotationPoint(0, 0, -1);
		tailRight.setRotationPoint(-2, 2.5f, 4);
		this.setRotationAngles(tailRight, -0.8726646259971648f, -0.4363323129985824f, -0.17453292519943295f);
		wingRight01.setRotationPoint(-6, -7, 0);
		this.setRotationAngles(wingRight01, -0.08726646259971647f, 0.6108652381980153f, -1.2217304763960306f);
		wingLeftAlt02a.setRotationPoint(0, 0, 0);
		wingRightAlt02b.setRotationPoint(-3, 1.7999999523162842f, 0);
		owlLeftClaw.setRotationPoint(3, 6, -1.5f);
		this.setRotationAngles(owlLeftClaw, 0.08726646259971647f, -0.08726646259971647f, 0);
		lTalon04.setRotationPoint(-0.699999988079071f, 1.2000000476837158f, 0.5f);
		this.setRotationAngles(lTalon04, -0.17453292519943295f, -0.06981317007977318f, 0);
		owlLeftEar.setRotationPoint(4, -3.4000000953674316f, 0);
		this.setRotationAngles(owlLeftEar, -0.3490658503988659f, 0, 0.2617993877991494f);
		wingRightAlt01b.setRotationPoint(-3, 1.7999999523162842f, 0);
		wingRightAlt01a.setRotationPoint(0, 0, 0);
		owlBodyfluff.setRotationPoint(0, -6.400000095367432f, -3.0999999046325684f);
		this.setRotationAngles(owlBodyfluff, -0.3665191429188092f, 0, 0);
		wingLeftAlt01b.setRotationPoint(3, 1.7999999523162842f, 0);
		owlRightClaw.setRotationPoint(-3, 6, -1.5f);
		this.setRotationAngles(owlRightClaw, 0.08726646259971647f, 0.08726646259971647f, 0);
		wingLeft02.setRotationPoint(6, 0, 0);
		this.setRotationAngles(wingLeft02, 0, 0, 0.4363323129985824f);
		wingLeftAlt03a.setRotationPoint(0, 0, 1);
		wingRightAlt02c.setRotationPoint(-3, 1.2999999523162842f, 0);
		wingRightAlt01c.setRotationPoint(-3, 1.7999999523162842f, 0);
		wingLeftAlt02c.setRotationPoint(-3, 1.2999999523162842f, 0);
		wingRightAlt03c.setRotationPoint(-9, 0.30000001192092896f, 0);
		wingLeft01.setRotationPoint(6, -7, 0);
		this.setRotationAngles(wingLeft01, -0.08726646259971647f, -0.6108652381980153f, 1.2217304763960306f);
		wingRight02.setRotationPoint(-6, 0, 1);
		this.setRotationAngles(wingRight02, 0, 0, -0.4363323129985824f);
		lTalon01.setRotationPoint(0.699999988079071f, 1, -3.5999999046325684f);
		this.setRotationAngles(lTalon01, -0.17453292519943295f, -0.10471975511965977f, 0);
		wingLeftAlt01a.setRotationPoint(0, 0, 0);
		rTalon04.setRotationPoint(0.699999988079071f, 1.2000000476837158f, 0.5f);
		this.setRotationAngles(rTalon04, -0.17453292519943295f, 0.06981317007977318f, 0);
		wingLeftAlt03b.setRotationPoint(3, 1.7999999523162842f, -1);
		wingLeftAlt02b.setRotationPoint(3, 1.7999999523162842f, 0);
		owlRightEar.setRotationPoint(-4, -3.4000000953674316f, 0);
		this.setRotationAngles(owlRightEar, -0.3490658503988659f, 0, -0.2617993877991494f);
		tailLeft.setRotationPoint(2, 2.5f, 4);
		this.setRotationAngles(tailLeft, -0.8726646259971648f, 0.4363323129985824f, 0.17453292519943295f);
		wingLeftAlt03c.setRotationPoint(-3, 0.30000001192092896f, 0);
		rTalon02.setRotationPoint(0.699999988079071f, 1, -3.5999999046325684f);
		this.setRotationAngles(rTalon02, -0.17453292519943295f, -0.10471975511965977f, 0);
		rTalon03.setRotationPoint(-0.699999988079071f, 1.2000000476837158f, 0.5f);
		this.setRotationAngles(rTalon03, -0.17453292519943295f, -0.06981317007977318f, 0);
	}
}