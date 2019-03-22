package com.bewitchment.common.item;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.item.ItemBroom;
import com.bewitchment.common.entity.misc.EntityBroom;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class ItemElderBroom extends ItemBroom
{
	public ItemElderBroom()
	{
		super(Bewitchment.MOD_ID, "broom_elder", Bewitchment.proxy.tab, new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/broom_elder.png"));
	}
	
	@Override
	public void handleMovement(EntityBroom broom, Vec3d look, float front, float strafe, float up)
	{
		if (broom.getFuel() > 0)
		{
			broom.setFuel(broom.getFuel() - 1);
			if (Math.abs(broom.motionX) < 1) broom.motionX += strafe * (1/6f);
			broom.motionY += up / 10;
			if (Math.abs(broom.motionZ) < 1) broom.motionZ += front * (1/6f);
			
			broom.motionX /= 1.1;
			broom.motionY /= up > 0 ? 1.025 : 1;
			broom.motionZ /= 1.1;
		}
		broom.motionY -= 0.02;
	}
	
	@Override
	public void onDismount(EntityBroom broom, EntityLivingBase mounter)
	{
	}
}