package com.bewitchment.api.entity.misc;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Field;

public abstract class EntityBroom extends Entity {
	private static Field isJumping;

	protected ItemStack item;

	public EntityBroom(World world) {
		super(world);
		setSize(0.7f, 0.7f);
	}

	protected static boolean getJump(EntityLivingBase rider) throws IllegalArgumentException, IllegalAccessException {
		if (isJumping == null) {
			isJumping = ObfuscationReflectionHelper.getPrivateValue(EntityLivingBase.class, rider, "isJumping", "field_70703_bu");
			isJumping.setAccessible(true);
		}
		return isJumping.getBoolean(rider);
	}

	@Override
	public Entity getControllingPassenger() {
		return getPassengers().size() == 0 ? null : getPassengers().get(0);
	}

	@Override
	public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {
		if (!player.isRiding() && !player.isSneaking()) {
			player.rotationYaw   = rotationYaw;
			player.rotationPitch = rotationPitch;
			player.startRiding(this);
			return EnumActionResult.SUCCESS;
		}
		return super.applyPlayerInteraction(player, vec, hand);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (getControllingPassenger() == null && source.getTrueSource() instanceof EntityPlayer) setDead();
		return super.attackEntityFrom(source, amount);
	}

	@Override
	public boolean canBeCollidedWith() {
		return !isDead;
	}

	@Override
	public boolean canBePushed() {
		return true;
	}

	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
		if (item == null) {
			item = player.getHeldItem(hand).splitStack(1);
			return true;
		}
		return super.processInitialInteract(player, hand);
	}

	@Override
	public double getMountedYOffset() {
		return 0.4;
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		doBlockCollisions();
		float friction = 0.98f;
		if (onGround) friction = 0.8f;
		motionX *= friction;
		motionY *= friction;
		motionZ *= friction;
		EntityPlayer rider = (EntityPlayer) getControllingPassenger();
		if (isBeingRidden()) {
			if (rider != null) {
				float front = rider.moveForward, strafe = rider.moveStrafing, up = 0;
				try {
					up = getJump(rider) ? 1 : 0;
				}
				catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				handleMovement(rider.getLookVec(), front, strafe, up);
			}
		}
		if (collidedHorizontally) {
			if (prevPosX == posX) motionX = 0;
			if (prevPosZ == posZ) motionZ = 0;
		}
		if (collidedVertically && prevPosY == posY) motionY = 0;
		if (isBeingRidden()) setSize(0.7f, rider.height);
		move(MoverType.SELF, motionX, motionY, motionZ);
		if (isBeingRidden()) setSize(0.7f, 0.7f);
	}

	@Override
	public void setDead() {
		if (!world.isRemote) InventoryHelper.spawnItemStack(world, posX, posY, posZ, item);
		super.setDead();
	}

	@Override
	protected void entityInit() {
	}

	@Override
	protected void updateFallState(double y, boolean onGround, IBlockState state, BlockPos pos) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
		if (item != null) tag.setTag("item", item.serializeNBT());
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {
		item = tag.hasKey("item") ? new ItemStack(tag.getCompoundTag("item")) : ItemStack.EMPTY;
	}

	protected abstract void handleMovement(Vec3d look, float front, float strafe, float up);
}