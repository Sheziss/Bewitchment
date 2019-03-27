package com.bewitchment.common.block.tile.entity.util;

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
		markDirty();
		return super.writeToNBT(tag);
	}
	
	public static boolean isEmpty(ItemStackHandler handler)
	{
		for (int i = 0; i < handler.getSlots(); i++) if (!handler.getStackInSlot(i).isEmpty()) return false;
		return true;
	}
	
	public static int getFirstEmptySlot(ItemStackHandler handler)
	{
		for (int i = 0; i < handler.getSlots(); i++) if (handler.getStackInSlot(i).isEmpty()) return i;
		return -1;
	}
	
	public static void clear(ItemStackHandler handler)
	{
		for (int i = 0; i < handler.getSlots(); i++) handler.setStackInSlot(i, ItemStack.EMPTY);
	}
}