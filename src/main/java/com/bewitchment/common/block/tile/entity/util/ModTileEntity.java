package com.bewitchment.common.block.tile.entity.util;

import com.bewitchment.Bewitchment;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.ItemStackHandler;

public abstract class ModTileEntity extends TileEntity
{
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
	public void markDirty()
	{
		super.markDirty();
		world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 2);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag)
	{
		for (int i = 0; i < getInventories().length; i++) tag.setTag("inventory_" + i, getInventories()[i].serializeNBT());
		markDirty();
		return super.writeToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		for (int i = 0; i < getInventories().length; i++) getInventories()[i].deserializeNBT(tag.getCompoundTag("inventory_" + i));
	}
	
	public ItemStackHandler[] getInventories()
	{
		return new ItemStackHandler[] {};
	}
	
	public static boolean isEmpty(ItemStackHandler handler)
	{
		for (int i = 0; i < handler.getSlots(); i++) if (!handler.getStackInSlot(i).isEmpty()) return false;
		return true;
	}
	
	public static int getFirstEmptySlot(ItemStackHandler handler)
	{
		return getFirstValidSlot(handler, ItemStack.EMPTY);
	}
	
	public static int getFirstValidSlot(ItemStackHandler handler, ItemStack stack)
	{
		boolean hasEmpty = false;
		for (int i = 0; i < handler.getSlots(); i++)
		{
			if (Bewitchment.proxy.areStacksEqual(handler.getStackInSlot(i), stack)) return i;
			if (handler.getStackInSlot(i).isEmpty()) hasEmpty = true;
		}
		return hasEmpty ? getFirstEmptySlot(handler) : -1;
	}
	
	public static void clear(ItemStackHandler handler)
	{
		for (int i = 0; i < handler.getSlots(); i++) handler.setStackInSlot(i, ItemStack.EMPTY);
	}
}