package com.bewitchment.client.model.entity.living;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * blindworm - cybercat5555
 * Created using Tabula 5.1.0
 */
public class ModelBlindworm extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer bodyB;
    public ModelRenderer neck00;
    public ModelRenderer tail00;
    public ModelRenderer neck01;
    public ModelRenderer head;
    public ModelRenderer snout;
    public ModelRenderer lowerJaw;
    public ModelRenderer leftEye;
    public ModelRenderer rightEye;
    public ModelRenderer headB;
    public ModelRenderer tail01;
    public ModelRenderer tail01b;
    public ModelRenderer tail02;
    public ModelRenderer tail03;

    public ModelBlindworm() {
        this.textureWidth = 64;
        this.textureHeight = 65;
        this.lowerJaw = new ModelRenderer(this, 15, 8);
        this.lowerJaw.setRotationPoint(0, 0.9f, 1);
        this.lowerJaw.addBox(-2, -0.5f, -4.8f, 4, 1, 5, 0);
        this.setRotateAngle(lowerJaw, -0.09f, 0, 0);
        this.leftEye = new ModelRenderer(this, 32, 0);
        this.leftEye.setRotationPoint(1.4f, -0.2f, 0);
        this.leftEye.addBox(-0.5f, -2, -1, 2, 2, 2, 0);
        this.setRotateAngle(leftEye, 0, 0.12f, -0.2f);
        this.body = new ModelRenderer(this, 25, 15);
        this.body.setRotationPoint(0, 22.5f, 1.9f);
        this.body.addBox(-1.8f, -1.49f, -6, 4, 3, 9, 0);
        this.tail00 = new ModelRenderer(this, 0, 33);
        this.tail00.setRotationPoint(0, 0, 3.4f);
        this.tail00.addBox(-2, -1.49f, -0.5f, 4, 3, 7, 0);
        this.tail03 = new ModelRenderer(this, 43, 54);
        this.tail03.setRotationPoint(0, 0.5f, 5.9f);
        this.tail03.addBox(-0.5f, -0.5f, -0.5f, 1, 1, 6, 0);
        this.tail01b = new ModelRenderer(this, 22, 55);
        this.tail01b.setRotationPoint(0, 0.5f, 0);
        this.tail01b.addBox(-1.5f, 0, -1, 3, 1, 7, 0);
        this.neck01 = new ModelRenderer(this, 0, 24);
        this.neck01.setRotationPoint(0, 0, -3.6f);
        this.neck01.addBox(-2, -1.5f, -3.2f, 4, 3, 3, 0);
        this.snout = new ModelRenderer(this, 0, 10);
        this.snout.setRotationPoint(0, -0.8f, -1.9f);
        this.snout.addBox(-2, -1, -2.2f, 4, 2, 2, 0);
        this.setRotateAngle(snout, 0.09f, 0, 0);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0, 0.3f, -3.9f);
        this.head.addBox(-1.6f, -2, -2.5f, 4, 3, 5, 0);
        this.setRotateAngle(head, 0.14f, 0, 0);
        this.neck00 = new ModelRenderer(this, 0, 16);
        this.neck00.setRotationPoint(0, 0, -5.9f);
        this.neck00.addBox(-2, -1.5f, -3.9f, 4, 3, 4, 0);
        this.bodyB = new ModelRenderer(this, 25, 28);
        this.bodyB.setRotationPoint(0, 0, 0);
        this.bodyB.addBox(-2.2f, -1.49f, -6, 1, 3, 9, 0);
        this.tail02 = new ModelRenderer(this, 41, 40);
        this.tail02.setRotationPoint(0, 0.5f, 6.7f);
        this.tail02.addBox(-1, -1, -1, 2, 2, 7, 0);
        this.tail01 = new ModelRenderer(this, 23, 45);
        this.tail01.setRotationPoint(0, 0, 5.9f);
        this.tail01.addBox(-1.5f, -1.1f, -1, 3, 2, 7, 0);
        this.rightEye = new ModelRenderer(this, 32, 0);
        this.rightEye.mirror = true;
        this.rightEye.setRotationPoint(-1.3f, -0.2f, 0);
        this.rightEye.addBox(-1.5f, -2, -1, 2, 2, 2, 0);
        this.setRotateAngle(rightEye, 0, -0.12f, 0.2f);
        this.headB = new ModelRenderer(this, 19, 0);
        this.headB.setRotationPoint(0, 0, 0);
        this.headB.addBox(-2.3f, -2, -2.5f, 1, 3, 5, 0);
        this.head.addChild(this.lowerJaw);
        this.head.addChild(this.leftEye);
        this.body.addChild(this.tail00);
        this.tail02.addChild(this.tail03);
        this.tail01.addChild(this.tail01b);
        this.neck00.addChild(this.neck01);
        this.head.addChild(this.snout);
        this.neck01.addChild(this.head);
        this.body.addChild(this.neck00);
        this.body.addChild(this.bodyB);
        this.tail01.addChild(this.tail02);
        this.tail00.addChild(this.tail01);
        this.head.addChild(this.rightEye);
        this.head.addChild(this.headB);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float age, float yaw, float pitch, float scale) {
        this.body.render(scale);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer renderer, float x, float y, float z) {
        renderer.rotateAngleX = x;
        renderer.rotateAngleY = y;
        renderer.rotateAngleZ = z;
    }
}