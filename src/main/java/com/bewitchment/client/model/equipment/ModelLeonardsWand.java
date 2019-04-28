package com.bewitchment.client.model.equipment;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * Created by Joseph on 4/18/2019.
 */
public class ModelLeonardsWand extends ModelBase {

	public ModelRenderer wand01;
	public ModelRenderer wandSpikes;
	public ModelRenderer wand02a;
	public ModelRenderer wand04;
	public ModelRenderer wand06La;
	public ModelRenderer wand06Ra;
	public ModelRenderer wand02b;
	public ModelRenderer wand02c;
	public ModelRenderer wand02d;
	public ModelRenderer wand03;
	public ModelRenderer wand05;
	public ModelRenderer wand06Lb;
	public ModelRenderer wand06Rb;

	public ModelLeonardsWand() {
		this.textureWidth  = 32;
		this.textureHeight = 32;
		this.wand06La      = new ModelRenderer(this, 0, 11);
		this.wand06La.setRotationPoint(0.5F, -4.4F, 0.0F);
		this.wand06La.addBox(-0.5F, -0.7F, -0.5F, 2, 1, 1, 0.0F);
		this.setRotateAngle(wand06La, 0.0F, 0.0F, 0.22689280275926282F);
		this.wandSpikes = new ModelRenderer(this, 17, 0);
		this.wandSpikes.setRotationPoint(0.0F, -0.9F, 0.0F);
		this.wandSpikes.addBox(-2.52F, -5.0F, 0.0F, 5, 5, 0, 0.0F);
		this.wand04 = new ModelRenderer(this, 0, 3);
		this.wand04.setRotationPoint(0.0F, -4.4F, 0.0F);
		this.wand04.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.wand06Ra        = new ModelRenderer(this, 0, 11);
		this.wand06Ra.mirror = true;
		this.wand06Ra.setRotationPoint(-0.5F, -4.4F, 0.0F);
		this.wand06Ra.addBox(-1.5F, -0.7F, -0.5F, 2, 1, 1, 0.0F);
		this.setRotateAngle(wand06Ra, 0.0F, 0.0F, -0.22689280275926282F);
		this.wand06Rb = new ModelRenderer(this, 0, 14);
		this.wand06Rb.setRotationPoint(-1.1F, -0.1F, 0.0F);
		this.wand06Rb.addBox(-1.8F, -0.7F, -0.49F, 2, 1, 1, 0.0F);
		this.setRotateAngle(wand06Rb, 0.0F, 0.0F, -0.8726646259971648F);
		this.wand02a = new ModelRenderer(this, 11, 0);
		this.wand02a.setRotationPoint(0.0F, 1.5F, 0.0F);
		this.wand02a.addBox(-0.8F, -5.5F, -0.79F, 1, 11, 1, 0.0F);
		this.wand05 = new ModelRenderer(this, 0, 0);
		this.wand05.setRotationPoint(0.0F, -1.6F, 0.0F);
		this.wand05.addBox(-1.5F, -1.0F, -0.5F, 3, 1, 1, 0.0F);
		this.wand03 = new ModelRenderer(this, 11, 13);
		this.wand03.setRotationPoint(0.0F, 5.5F, 0.0F);
		this.wand03.addBox(-0.5F, -0.3F, -0.5F, 1, 3, 1, 0.0F);
		this.wand02d = new ModelRenderer(this, 11, 0);
		this.wand02d.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.wand02d.addBox(-0.21F, -5.5F, -0.2F, 1, 11, 1, 0.0F);
		this.wand02c = new ModelRenderer(this, 11, 0);
		this.wand02c.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.wand02c.addBox(-0.21F, -5.5F, -0.8F, 1, 11, 1, 0.0F);
		this.setRotateAngle(wand02c, 0.0F, 3.141592653589793F, 0.0F);
		this.wand06Lb = new ModelRenderer(this, 0, 14);
		this.wand06Lb.setRotationPoint(1.1F, -0.1F, 0.0F);
		this.wand06Lb.addBox(-0.2F, -0.7F, -0.49F, 2, 1, 1, 0.0F);
		this.setRotateAngle(wand06Lb, 0.0F, 0.0F, 0.8726646259971648F);
		this.wand01 = new ModelRenderer(this, 0, 7);
		this.wand01.setRotationPoint(0.0F, 5.9F, 0.0F);
		this.wand01.addBox(-1.5F, -4.8F, -1.0F, 3, 1, 2, 0.0F);
		this.wand02b = new ModelRenderer(this, 11, 0);
		this.wand02b.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.wand02b.addBox(-0.2F, -5.5F, -0.2F, 1, 11, 1, 0.0F);
		this.setRotateAngle(wand02b, 0.0F, 1.5707963267948966F, 0.0F);
		this.wand01.addChild(this.wand06La);
		this.wand01.addChild(this.wand04);
		this.wand01.addChild(this.wand06Ra);
		this.wand06Ra.addChild(this.wand06Rb);
		this.wand01.addChild(this.wand02a);
		this.wand04.addChild(this.wand05);
		this.wand02a.addChild(this.wand03);
		this.wand02a.addChild(this.wand02d);
		this.wand02a.addChild(this.wand02c);
		this.wand06La.addChild(this.wand06Lb);
		this.wand02a.addChild(this.wand02b);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(this.wandSpikes.offsetX, this.wandSpikes.offsetY, this.wandSpikes.offsetZ);
		GlStateManager.translate(this.wandSpikes.rotationPointX * f5, this.wandSpikes.rotationPointY * f5, this.wandSpikes.rotationPointZ * f5);
		GlStateManager.scale(0.6D, 0.6D, 0.8D);
		GlStateManager.translate(-this.wandSpikes.offsetX, -this.wandSpikes.offsetY, -this.wandSpikes.offsetZ);
		GlStateManager.translate(-this.wandSpikes.rotationPointX * f5, -this.wandSpikes.rotationPointY * f5, -this.wandSpikes.rotationPointZ * f5);
		this.wandSpikes.render(f5);
		GlStateManager.popMatrix();
		this.wand01.render(f5);
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
