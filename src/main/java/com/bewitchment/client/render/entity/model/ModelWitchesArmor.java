package com.bewitchment.client.render.entity.model;


import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Witches_attire - Ingoleth
 * Created using Tabula 5.1.0
 */
public class ModelWitchesArmor extends ModelBiped {
    public ModelRenderer legLeft;
    public ModelRenderer legRight;
    public ModelRenderer hat1;
    public ModelRenderer Body;
    public ModelRenderer tunicLeftBack;
    public ModelRenderer tunicLeftFront;
    public ModelRenderer tunicLeft;
    public ModelRenderer tunicLeftBack_1;
    public ModelRenderer tunicFront;
    public ModelRenderer tunicLeft_1;
    public ModelRenderer tunicRightFront;
    public ModelRenderer tunicRightBack;
    public ModelRenderer tunicRight;
    public ModelRenderer tunicRightFront_1;
    public ModelRenderer tunicRightBack_1;
    public ModelRenderer tunicRight_1;
    public ModelRenderer hat2;
    public ModelRenderer hatWing;
    public ModelRenderer hat3;
    public ModelRenderer armRight;
    public ModelRenderer armLeft;
    public ModelRenderer shoulderRight;
    public ModelRenderer sleeveRight;
    public ModelRenderer shoulderLeft;
    public ModelRenderer sleeveLeft;

    public ModelWitchesArmor() {
        this.textureWidth = 64;
        this.textureHeight = 128;
        this.tunicRightBack_1 = new ModelRenderer(this, 53, 77);
        this.tunicRightBack_1.setRotationPoint(-0.009999999776482582F, 10.0F, -1.0F);
        this.tunicRightBack_1.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1, 0.0F);
        this.setRotateAngle(tunicRightBack_1, 0.2617993877991494F, 0.0F, 0.0F);
        this.tunicFront = new ModelRenderer(this, 53, 77);
        this.tunicFront.setRotationPoint(-0.009999999776482582F, 10.0F, -1.0F);
        this.tunicFront.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1, 0.0F);
        this.setRotateAngle(tunicFront, 0.2617993877991494F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 3, 81);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, -0.01F, -2.0F, 8, 12, 4, 0.0F);
        this.legRight = new ModelRenderer(this, 0, 49);
        this.legRight.mirror = true;
        this.legRight.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.legRight.addBox(-2.0F, 0.0F, -2.0F, 0, 0, 0, 0.0F);
        this.tunicRightFront_1 = new ModelRenderer(this, 53, 77);
        this.tunicRightFront_1.setRotationPoint(-0.009999999776482582F, 10.0F, 0.9999998807907104F);
        this.tunicRightFront_1.addBox(0.0F, 0.0F, -1.0F, 4, 2, 1, 0.0F);
        this.setRotateAngle(tunicRightFront_1, -0.2617993877991494F, 0.0F, 0.0F);
        this.armRight = new ModelRenderer(this, 47, 81);
        this.armRight.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.armRight.addBox(-3.0F, -1.9F, -2.0F, 4, 12, 4, 0.0F);
        this.hat1 = new ModelRenderer(this, 2, 100);
        this.hat1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.hat1.addBox(-4.5F, -10.5F, -4.5F, 9, 5, 9, 0.0F);
        this.tunicLeft = new ModelRenderer(this, 23, 65);
        this.tunicLeft.setRotationPoint(-2.010000228881836F, 0.0F, -2.0999999046325684F);
        this.tunicLeft.addBox(0.0F, 0.0F, -1.0F, 4, 10, 1, 0.0F);
        this.setRotateAngle(tunicLeft, 0.12217304763960307F, -1.5707963267948966F, 0.0F);
        this.legLeft = new ModelRenderer(this, 3, 44);
        this.legLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.legLeft.addBox(-2.0F, 0.0F, -2.0F, 0, 0, 0, 0.0F);
        this.tunicLeftFront = new ModelRenderer(this, 53, 65);
        this.tunicLeftFront.setRotationPoint(2.009999990463257F, 0.0F, -2.0999999046325684F);
        this.tunicLeftFront.addBox(0.0F, 0.0F, -1.0F, 4, 10, 1, 0.0F);
        this.setRotateAngle(tunicLeftFront, -3.01941960595019F, 0.0F, 3.141592653589793F);
        this.tunicLeft_1 = new ModelRenderer(this, 23, 77);
        this.tunicLeft_1.setRotationPoint(-0.009999999776482582F, 10.0F, -1.0F);
        this.tunicLeft_1.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1, 0.0F);
        this.setRotateAngle(tunicLeft_1, 0.2617993877991494F, 0.0F, 0.0F);
        this.tunicRightFront = new ModelRenderer(this, 53, 65);
        this.tunicRightFront.setRotationPoint(-1.9900000095367432F, 0.0F, -2.0999999046325684F);
        this.tunicRightFront.addBox(0.0F, 0.0F, 0.0F, 4, 10, 1, 0.0F);
        this.setRotateAngle(tunicRightFront, -0.12217304763960307F, 0.0F, 0.0F);
        this.hat2 = new ModelRenderer(this, 39, 107);
        this.hat2.setRotationPoint(0.0F, -10.5F, -3.0F);
        this.hat2.addBox(-3.0F, -4.0F, 0.0F, 6, 4, 6, 0.0F);
        this.setRotateAngle(hat2, -0.2617993877991494F, 0.0F, 0.0F);
        this.tunicRight_1 = new ModelRenderer(this, 23, 77);
        this.tunicRight_1.setRotationPoint(-0.009999999776482582F, 10.0F, 0.9999998807907104F);
        this.tunicRight_1.addBox(0.0F, 0.0F, -1.0F, 4, 2, 1, 0.0F);
        this.setRotateAngle(tunicRight_1, -0.2617993877991494F, 0.0F, 0.0F);
        this.sleeveRight = new ModelRenderer(this, 49, 98);
        this.sleeveRight.setRotationPoint(1.01F, 10.1F, 2.0F);
        this.sleeveRight.addBox(-4.0F, -4.0F, 0.0F, 4, 4, 2, 0.0F);
        this.setRotateAngle(sleeveRight, 0.5235987755982988F, 0.0F, 0.0F);
        this.hatWing = new ModelRenderer(this, 0, 115);
        this.hatWing.setRotationPoint(0.0F, -6.0F, 0.0F);
        this.hatWing.addBox(-6.0F, 0.0F, -6.0F, 12, 1, 12, 0.0F);
        this.setRotateAngle(hatWing, 0.03490658503988659F, 0.0F, 0.06981317007977318F);
        this.tunicRight = new ModelRenderer(this, 23, 65);
        this.tunicRight.setRotationPoint(2.010000228881836F, 0.0F, -2.0999999046325684F);
        this.tunicRight.addBox(0.0F, 0.0F, 0.0F, 4, 10, 1, 0.0F);
        this.setRotateAngle(tunicRight, -0.12217304763960307F, -1.5707963267948966F, 0.0F);
        this.shoulderRight = new ModelRenderer(this, 0, 64);
        this.shoulderRight.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shoulderRight.addBox(-1.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F);
        this.setRotateAngle(shoulderRight, 0.0F, 3.141592653589793F, -0.08726646259971647F);
        this.tunicLeftBack = new ModelRenderer(this, 53, 65);
        this.tunicLeftBack.setRotationPoint(2.009999990463257F, 0.0F, 2.0999999046325684F);
        this.tunicLeftBack.addBox(0.0F, 0.0F, 0.0F, 4, 10, 1, 0.0F);
        this.setRotateAngle(tunicLeftBack, 3.01941960595019F, 0.0F, 3.141592653589793F);
        this.armLeft = new ModelRenderer(this, 47, 81);
        this.armLeft.mirror = true;
        this.armLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.armLeft.addBox(-1.0F, -1.9F, -2.0F, 4, 12, 4, 0.0F);
        this.hat3 = new ModelRenderer(this, 51, 118);
        this.hat3.setRotationPoint(1.399999976158142F, -4.0F, 1.5F);
        this.hat3.addBox(-3.0F, -4.0F, 0.0F, 3, 4, 3, 0.0F);
        this.setRotateAngle(hat3, -0.3490658503988659F, 0.0F, 0.0F);
        this.tunicLeftBack_1 = new ModelRenderer(this, 53, 77);
        this.tunicLeftBack_1.setRotationPoint(-0.009999999776482582F, 10.0F, 0.9999998807907104F);
        this.tunicLeftBack_1.addBox(0.0F, 0.0F, -1.0F, 4, 2, 1, 0.0F);
        this.setRotateAngle(tunicLeftBack_1, -0.2617993877991494F, 0.0F, 0.0F);
        this.tunicRightBack = new ModelRenderer(this, 53, 65);
        this.tunicRightBack.setRotationPoint(-1.9900000095367432F, 0.0F, 2.0999999046325684F);
        this.tunicRightBack.addBox(0.0F, 0.0F, -1.0F, 4, 10, 1, 0.0F);
        this.setRotateAngle(tunicRightBack, 0.12217304763960307F, 0.0F, 0.0F);
        this.sleeveLeft = new ModelRenderer(this, 49, 98);
        this.sleeveLeft.setRotationPoint(0.99F, 10.1F, 2.0F);
        this.sleeveLeft.addBox(-2.0F, -4.0F, 0.0F, 4, 4, 2, 0.0F);
        this.setRotateAngle(sleeveLeft, 0.5235987755982988F, 0.0F, 0.0F);
        this.shoulderLeft = new ModelRenderer(this, 0, 64);
        this.shoulderLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shoulderLeft.addBox(-1.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F);
        this.setRotateAngle(shoulderLeft, 0.0F, 0.0F, 0.08726646259971647F);
        

        
       
        this.legLeft.addChild(this.tunicLeftBack);
        this.tunicLeftBack.addChild(this.tunicLeftBack_1);
        this.legLeft.addChild(this.tunicLeft);
        this.tunicLeft.addChild(this.tunicLeft_1);
        this.legLeft.addChild(this.tunicLeftFront);
        this.tunicLeftFront.addChild(this.tunicFront);
        
        this.legRight.addChild(this.tunicRight); 
        this.tunicRight.addChild(this.tunicRight_1);
        this.legRight.addChild(this.tunicRightBack);
        this.tunicRightBack.addChild(this.tunicRightBack_1);
        this.legRight.addChild(this.tunicRightFront);
        this.tunicRightFront.addChild(this.tunicRightFront_1);
        
        this.hat1.addChild(this.hat2);
        this.hat2.addChild(this.hat3);
        this.hat1.addChild(this.hatWing);
        
        this.armLeft.addChild(this.sleeveLeft);
        this.armLeft.addChild(this.shoulderLeft);
        this.armRight.addChild(this.sleeveRight);
        this.armRight.addChild(this.shoulderRight);
        
        this.bipedBody.addChild(this.Body);
        this.bipedHead.addChild(this.hat1);
        this.bipedLeftArm.addChild(this.armLeft);
        this.bipedRightArm.addChild(this.armRight);
        this.bipedRightLeg.addChild(this.legLeft);
        this.bipedLeftLeg.addChild(this.legRight);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        super.render(entity, f, f1, f2, f3, f4, f5);

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
