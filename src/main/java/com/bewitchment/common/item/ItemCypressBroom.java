package com.bewitchment.common.item;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.entity.misc.EntityBroom;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class ItemCypressBroom extends ItemBroom
{
	public ItemCypressBroom()
	{
		super("broom_cypress", Bewitchment.proxy.tab, new ResourceLocation(Bewitchment.MOD_ID, "textures/entity/broom_cypress.png"));
	}
	
	@Override
	public void handleMovement(EntityBroom broom, Vec3d look, float front, float strafe, float up)
	{
		if (front >= 0)
		{
			broom.motionX += 0.1 * look.x / 8;
			broom.motionY += 0.1 * look.y / 8;
			broom.motionZ += 0.1 * look.z / 8;
		}
		if (broom.motionX * broom.motionX + broom.motionZ * broom.motionZ > 1)
		{
			Vec3d limit = new Vec3d(broom.motionX, 0, broom.motionZ).normalize();
			broom.motionX = limit.x;
			broom.motionZ = limit.z;
		}
	}
	
	@Override
	public void onDismount(EntityBroom broom, EntityLivingBase mounter)
	{
	}
}