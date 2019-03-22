package com.bewitchment.common.block.tile.entity.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public abstract class ModTileEntity extends TileEntity implements IItemHandlerModifiable
{
	private ItemStackHandler inventory;
	
	protected ModTileEntity(int inventory_size)
	{
		inventory = new ItemStackHandler(inventory_size)
		{
			@Override
			protected void onContentsChanged(int slot)
		    {
				markDirty();
		    }
		};
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		return new SPacketUpdateTileEntity(getPos(), 0, writeToNBT(new NBTTagCompound()));
	}
	
	@Override
	public void onDataPacket(NetworkManager manager, SPacketUpdateTileEntity packet)
	{
		readFromNBT(packet.getNbtCompound());
	}
	
	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate)
	{
		return inventory.extractItem(slot, amount, simulate);
	}
	
	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
	{
		return inventory.insertItem(slot, stack, simulate);
	}
	
	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return inventory.getStackInSlot(slot);
	}
	
	@Override
	public void setStackInSlot(int slot, ItemStack stack)
	{
		inventory.setStackInSlot(slot, stack);
	}
	
	@Override
	public int getSlotLimit(int slot)
	{
		return inventory.getSlotLimit(slot);
	}
	
	@Override
	public int getSlots()
	{
		return inventory.getSlots();
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing face)
	{
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && getSlots() > 0 ? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(this) : super.getCapability(capability, face);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing face)
	{
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && getSlots() > 0 || super.hasCapability(capability, face);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag)
	{
		if (getSlots() > 0) tag.setTag("inventory", inventory.serializeNBT());
		markDirty();
		world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 2);
		return super.writeToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		if (getSlots() > 0) inventory.deserializeNBT(tag.getCompoundTag("inventory"));
	}
	
	public boolean isEmpty()
	{
		for (int i = 0; i < inventory.getSlots(); i++) if (!getStackInSlot(i).isEmpty()) return false;
		return true;
	}
	
	public int getFirstEmptySlot()
	{
		for (int i = 0; i < inventory.getSlots(); i++) if (getStackInSlot(i).isEmpty()) return i;
		return -1;
	}
	
	public void clear()
	{
		for (int i = 0; i < getSlots(); i++) setStackInSlot(i, ItemStack.EMPTY);
	}
}