package com.bewitchment.common.entity.misc;

import com.bewitchment.api.entity.misc.EntityBroom;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityCypressBroom extends EntityBroom
{
	public EntityCypressBroom(World world)
	{
		super(world);
	}
	
	@Override
	protected void handleMovement(Vec3d look, float front, float strafe, float up)
	{
		if (front >= 0) {
			motionX += 0.1 * look.x / 8;
			motionY += 0.1 * look.y / 8;
			motionZ += 0.1 * look.z / 8;
		}
		if (motionX * motionX + motionZ * motionZ > 1) {
			Vec3d limit = new Vec3d(motionX, 0, motionZ).normalize();
			motionX = limit.x;
			motionZ = limit.z;
		}
	}
}