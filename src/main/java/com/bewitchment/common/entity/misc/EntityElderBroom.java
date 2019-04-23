package com.bewitchment.common.entity.misc;

import com.bewitchment.api.entity.misc.EntityBroom;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityElderBroom extends EntityBroom {
	public EntityElderBroom(World world) {
		super(world);
	}

	@Override
	protected void handleMovement(Vec3d look, float front, float strafe, float up) {
		if (Math.abs(motionX) < 1) motionX += strafe * (1 / 6f);
		motionY += up / 10;
		if (Math.abs(motionZ) < 1) motionZ += front * (1 / 6f);
		motionX /= 1.1;
		motionY /= up > 0 ? 1.025 : 1;
		motionZ /= 1.1;
		motionY -= 0.02;
	}
}