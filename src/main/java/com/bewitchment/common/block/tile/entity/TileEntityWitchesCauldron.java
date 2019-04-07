package com.bewitchment.common.block.tile.entity;

import java.util.List;

import com.bewitchment.common.block.tile.entity.util.TileEntityAltarStorage;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityWitchesCauldron extends TileEntityAltarStorage implements ITickable
{
	public final ItemStackHandler inventory = new ItemStackHandler(Byte.MAX_VALUE);
	public final FluidTank tank = new FluidTank(Fluid.BUCKET_VOLUME);
	
	private static final AxisAlignedBB collectionZone = new AxisAlignedBB(0, 0, 0, 1, 0.65, 1);
	
	@Override
	public void update()
	{
		if (!world.isRemote)
		{
			if (tank.getFluid() != null && tank.getFluid().getFluid().getTemperature() >= FluidRegistry.LAVA.getTemperature())
			{
				if (world.rand.nextInt(100) == 0)
	            {
	                world.spawnParticle(EnumParticleTypes.LAVA, getPos().getX() + 0.5, getPos().getY() + 0.5, getPos().getZ() + 0.5, 0, 0, 0);
	                world.playSound(null, getPos(), SoundEvents.BLOCK_LAVA_POP, SoundCategory.BLOCKS, 0.2f + world.rand.nextFloat() * 0.2f, 0.9f + world.rand.nextFloat() * 0.15f);
	            }
	            if (world.rand.nextInt(200) == 0) world.playSound(null, getPos(), SoundEvents.BLOCK_LAVA_AMBIENT, SoundCategory.BLOCKS, 0.2f + world.rand.nextFloat() * 0.2f, 0.9f + world.rand.nextFloat() * 0.15f);
			}
			if (world.getTotalWorldTime() % 5 == 0) insertNextItem();
		}
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing face)
	{
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ? CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(tank) : super.getCapability(capability, face);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing face)
	{
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, face);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag)
	{
		tag.setTag("tank", tank.writeToNBT(new NBTTagCompound()));
		return super.writeToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		tank.readFromNBT(tag.getCompoundTag("tank"));
	}
	
	@Override
	public ItemStackHandler[] getInventories()
	{
		return new ItemStackHandler[] {inventory};
	}
	
	private ItemStack insertNextItem()
	{
		List<EntityItem> list = world.getEntitiesWithinAABB(EntityItem.class, collectionZone.offset(getPos()));
		if (!list.isEmpty() && tank.getFluidAmount() > 0)
		{
			EntityItem entity = list.get(0);
			ItemStack stack = entity.getItem();
			if (canAccept(stack))
			{
				world.playSound(null, getPos(), SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS, 1, (float) (0.2 * world.rand.nextDouble()) + 1);
				return inventory.insertItem(getFirstEmptySlot(inventory), stack.splitStack(1), false);
			}
		}
		return ItemStack.EMPTY;
	}
	
	private boolean canAccept(ItemStack stack)
	{
		return true;
	}
}