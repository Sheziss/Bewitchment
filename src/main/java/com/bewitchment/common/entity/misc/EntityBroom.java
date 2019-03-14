package com.bewitchment.common.entity.misc;

import java.lang.reflect.Field;

import com.bewitchment.api.capability.magicpower.MagicPowerProvider;
import com.bewitchment.api.item.ItemBroom;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class EntityBroom extends Entity
{
	private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntityBroom.class, DataSerializers.VARINT), FUEL = EntityDataManager.createKey(EntityBroom.class, DataSerializers.VARINT);
	
	public ItemStack item;
	public BlockPos original_position;
	public int original_dimension;
	
	public EntityBroom(World world)
	{
		super(world);
		setSize(0.7f, 0.7f);
	}
	
	public EntityBroom(World world, double x, double y, double z, ItemBroom item)
	{
		this(world);
		setPosition(x, y, z);
		original_position = new BlockPos(x, y, z);
		original_dimension = world.provider.getDimension();
		this.item = new ItemStack(item);
		dataManager.set(TYPE, item.type);
		dataManager.setDirty(TYPE);
	}
	
	private static Field jumpField(String isJumping, String field, Class<EntityLivingBase> base)
	{
		try {return ObfuscationReflectionHelper.findField(base, field);}
		catch (Exception e) {return ObfuscationReflectionHelper.findField(base, isJumping);}
	}
	
	@Override
	public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand)
	{
		if (!player.isRiding() && !player.isSneaking())
		{
			player.rotationYaw = rotationYaw;
			player.rotationPitch = rotationPitch;
			player.startRiding(this);
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.PASS;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (isEntityInvulnerable(source) || getControllingPassenger() != null) return false;
		if (!world.isRemote && source.getTrueSource() instanceof EntityPlayer)
		{
			ItemStack stack = ItemStack.EMPTY;
			EntityItem entity = new EntityItem(world, source.getTrueSource().posX, source.getTrueSource().posY, source.getTrueSource().posZ, stack);
			setDead();
			entity.onCollideWithPlayer((EntityPlayer) source.getTrueSource());
			return true;
		}
		return false;
	}
	
	@Override
	public boolean canBeCollidedWith()
	{
		return !isDead;
	}
	
	@Override
	public boolean canBePushed()
	{
		return true;
	}
	
	@Override
	public boolean canPassengerSteer()
	{
		return true;
	}
	
	@Override
	public Entity getControllingPassenger()
	{
		return getPassengers().size() == 0 ? null : getPassengers().get(0);
	}
	
	public int getFuel()
	{
		return dataManager.get(FUEL);
	}
	
	@Override
	public double getMountedYOffset()
	{
		return 0.4;
	}
	
	@Override
	public ItemStack getPickedResult(RayTraceResult target)
	{
		return item != null ? item : super.getPickedResult(target);
	}
	
	public int getType()
	{
		return dataManager.get(TYPE);
	}
	
	@Override
	public void onEntityUpdate()
	{
		super.onEntityUpdate();
		if (item != null && dataManager.get(TYPE) != ((ItemBroom) item.getItem()).type)
		{
			dataManager.set(TYPE, ((ItemBroom) item.getItem()).type);
			dataManager.setDirty(TYPE);
		}
		doBlockCollisions();
		float friction = 0.98f;
		if (onGround) friction = 0.8f;
		motionX *= friction;
		motionY *= friction;
		motionZ *= friction;
		EntityPlayer rider = (EntityPlayer) getControllingPassenger();
		if (isBeingRidden())
		{
			setFuel(100);
			if (rider != null && item != null)
			{
				if (dataManager.get(FUEL) < 5 && rider.getCapability(MagicPowerProvider.CAPABILITY, null).drain(30)) dataManager.set(FUEL, 100);
				float front = rider.moveForward, strafe = rider.moveStrafing, up = 0;
				try {up = jumpField("field_70703_bu", "isJumping", EntityLivingBase.class).getBoolean(rider) ? 1 : 0;}
				catch (Exception e) {e.printStackTrace();}
				((ItemBroom) item.getItem()).handleMovement(this, rider.getLookVec(), front, strafe, up);
				setRotationYawHead(rider.rotationYaw);
			}
		}
		else if (!collidedVertically)
		{
			motionY -= 0.009;
			if (motionY < -0.5) motionY = -0.5;
		}
		if (collidedHorizontally)
		{
			if (prevPosX == posX) motionX = 0;
			if (prevPosZ == posZ) motionZ = 0;
		}
		if (collidedVertically && prevPosY == posY) motionY = 0;
		if (isBeingRidden()) setSize(0.7f, rider.height);
		move(MoverType.SELF, motionX, motionY, motionZ);
		if (isBeingRidden()) setSize(0.7f, 0.7f);
	}
	
	public void setFuel(int fuel)
	{
		dataManager.set(FUEL, fuel);
	}
	
	@Override
	public void setRotationYawHead(float rotation)
	{
		prevRotationYaw = rotationYaw;
		rotationYaw = rotation;
	}
	
	@Override
	protected boolean canBeRidden(Entity entity)
	{
		return entity instanceof EntityPlayer;
	}
	
	@Override
	protected void updateFallState(double y, boolean onGround, IBlockState state, BlockPos pos)
	{
	}
	
	@Override
	protected void entityInit()
	{
		dataManager.register(TYPE, 0);
		dataManager.register(FUEL, 0);
		setEntityBoundingBox(new AxisAlignedBB(getPosition()).contract(0, 1, 0));
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound tag)
	{
		tag.setInteger("type", dataManager.get(TYPE));
		tag.setInteger("fuel", dataManager.get(FUEL));
		tag.setTag("item", item.serializeNBT());
		tag.setDouble("original_x", original_position.getX());
		tag.setDouble("original_y", original_position.getY());
		tag.setDouble("original_z", original_position.getZ());
		tag.setInteger("original_dimension", original_dimension);
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound tag)
	{
		dataManager.set(TYPE, tag.getInteger("type"));
		dataManager.set(FUEL, tag.getInteger("fuel"));
		item = new ItemStack(tag.getCompoundTag("item"));
		original_position = new BlockPos(tag.getDouble("original_x"), tag.getDouble("original_y"), tag.getDouble("original_z"));
		original_dimension = tag.getInteger("original_dimension");
	}
}