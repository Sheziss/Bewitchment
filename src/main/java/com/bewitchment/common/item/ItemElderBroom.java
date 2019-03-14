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
		super("broom_elder", Bewitchment.proxy.tab, new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/broom_elder.png"));
	}
	
	@Override
	public void handleMovement(EntityBroom broom, Vec3d look, float front, float strafe, float up)
	{
		if (broom.getFuel() > 0)
		{
			broom.setFuel(broom.getFuel() - 1);
			Vec3d horizontal_axis = look.crossProduct(new Vec3d(0, 1, 0)).normalize().scale(-strafe / 10);
			broom.motionX += front * (horizontal_axis.x + look.x) / 20;
			broom.motionY += up / 60 - 0.005;
			broom.motionZ += front * (horizontal_axis.z + look.z) / 20;
			if (broom.motionX * broom.motionX + broom.motionY * broom.motionY + broom.motionZ * broom.motionZ > 8)
			{
				Vec3d limit = new Vec3d(broom.motionX, broom.motionY, broom.motionZ).normalize().scale(2);
				broom.motionX = limit.x;
				broom.motionY = limit.y;
				broom.motionZ = limit.z;
			}
		}
		broom.motionY -= 0.005;
	}
	
	@Override
	public void onDismount(EntityBroom broom, EntityLivingBase mounter)
	{
	}
}