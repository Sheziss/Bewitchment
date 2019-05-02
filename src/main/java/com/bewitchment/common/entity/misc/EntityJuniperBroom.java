package com.bewitchment.common.entity.misc;

import com.bewitchment.api.entity.misc.EntityBroom;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityJuniperBroom extends EntityBroom {
	public EntityJuniperBroom(World world) {
		super(world);
	}

	@Override
	protected void handleMovement(Vec3d look, float front, float strafe, float up) {
		if (front >= 0) {
			Vec3d horizontal_axis = look.crossProduct(new Vec3d(0, 1, 0)).normalize().scale(-strafe / 10);
			motionX += front * (horizontal_axis.x + look.x) / 20;
			motionY += up / 80 + front * (horizontal_axis.y + look.y) / 80;
			motionZ += front * (horizontal_axis.z + look.z) / 20;
			if (motionX * motionX + motionY * motionY + motionZ * motionZ > 1) {
				Vec3d limit = new Vec3d(motionX, motionY, motionZ).normalize().scale(2);
				motionX = limit.x;
				motionY = limit.y;
				motionZ = limit.z;
			}
		}
		else {
			motionX /= 1.1;
			motionY /= 1.1;
			motionZ /= 1.1;
		}
		motionY -= 0.002;
	}
}