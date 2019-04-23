package com.bewitchment.common.entity.misc;

import com.bewitchment.api.entity.misc.EntityBroom;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityYewBroom extends EntityBroom {
	public BlockPos originalPos;
	public int originalDimension;

	public EntityYewBroom(World world) {
		super(world);
	}

	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
		boolean flag = super.processInitialInteract(player, hand);
		if (flag) {
			originalPos = getPosition();
			originalDimension = player.dimension;
		}
		return flag;
	}

	@Override
	protected void handleMovement(Vec3d look, float front, float strafe, float up) {
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

	@Override
	public void setDead() {
		if (isBeingRidden()) {
			Entity rider = getControllingPassenger();
			if (rider != null && dimension == originalDimension) {
				setPositionAndRotation(originalPos.getX(), originalPos.getY(), originalPos.getZ(), rotationYaw, rotationPitch);
				rider.setPositionAndRotation(originalPos.getX(), originalPos.getY(), originalPos.getZ(), rider.rotationYaw, rider.rotationPitch);
			}
		}
		super.setDead();
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		tag.setLong("originalPos", originalPos.toLong());
		tag.setInteger("originalDimension", originalDimension);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		originalPos = BlockPos.fromLong(tag.getLong("originalPos"));
		originalDimension = tag.getInteger("originalDimension");
	}
}