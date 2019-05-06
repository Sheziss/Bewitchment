package com.bewitchment.common.block.tile.entity;

import com.bewitchment.common.block.tile.entity.util.TileEntityAltarStorage;
import com.bewitchment.registry.ModObjects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.List;

public class TileEntityWitchesCauldron extends TileEntityAltarStorage implements ITickable {
	private static final AxisAlignedBB collectionZone = new AxisAlignedBB(0, 0, 0, 1, 0.65, 1);
	public final ItemStackHandler inventory = new ItemStackHandler(Short.MAX_VALUE);
	public final FluidTank tank = new FluidTank(Fluid.BUCKET_VOLUME);

	@Override
	public void update() {
		boolean isLava = tank.getFluid() != null && tank.getFluid().getFluid().getTemperature() >= FluidRegistry.LAVA.getTemperature();
		if (isLava) {
			for (EntityLivingBase living : world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(getPos()))) {
				living.attackEntityFrom(DamageSource.LAVA, 4);
				living.setFire(15);
			}
			if (world.rand.nextInt(100) == 0) {
				if (world.isRemote)
					world.spawnParticle(EnumParticleTypes.LAVA, getPos().getX() + 0.5, getPos().getY() + 0.5, getPos().getZ() + 0.5, 0, 0, 0);
				else
					world.playSound(null, getPos(), SoundEvents.BLOCK_LAVA_POP, SoundCategory.BLOCKS, 0.2f + world.rand.nextFloat() * 0.2f, 0.9f + world.rand.nextFloat() * 0.15f);
			}
			if (!world.isRemote && world.rand.nextInt(200) == 0)
				world.playSound(null, getPos(), SoundEvents.BLOCK_LAVA_AMBIENT, SoundCategory.BLOCKS, 0.2f + world.rand.nextFloat() * 0.2f, 0.9f + world.rand.nextFloat() * 0.15f);
		}
		if (!world.isRemote) {
			if (world.getTotalWorldTime() % 5 == 0) insertNextItem(isLava);
		}
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing face) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ? CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(tank) : super.getCapability(capability, face);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing face) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, face);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setTag("tank", tank.writeToNBT(new NBTTagCompound()));
		return super.writeToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		tank.readFromNBT(tag.getCompoundTag("tank"));
	}

	@Override
	public ItemStackHandler[] getInventories() {
		return new ItemStackHandler[]{inventory};
	}

	private void insertNextItem(boolean isLava) {
		List<EntityItem> list = world.getEntitiesWithinAABB(EntityItem.class, collectionZone.offset(getPos()));
		if (!list.isEmpty()) {
			EntityItem entity = list.get(0);
			ItemStack stack = entity.getItem().splitStack(1);
			if (isLava) {
				world.playSound(null, getPos(), SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1, (float) (0.2 * world.rand.nextDouble()) + 1);
				stack.shrink(stack.getCount());
			} else {
				world.playSound(null, getPos(), SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS, 1, (float) (0.2 * world.rand.nextDouble()) + 1);
				if (stack.getItem() == ModObjects.wood_ash) {
					tank.drain(Fluid.BUCKET_VOLUME, true);
					clear(inventory);
				} else inventory.insertItem(getFirstEmptySlot(inventory), stack, false);
			}
			markDirty();
		}
	}
}