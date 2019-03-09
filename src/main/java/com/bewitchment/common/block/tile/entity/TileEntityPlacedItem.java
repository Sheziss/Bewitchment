package com.bewitchment.common.block.tile.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPlacedItem extends TileEntity
{
	private ItemStack stack = ItemStack.EMPTY;
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag)
	{
		NBTTagCompound tag0 = new NBTTagCompound();
		stack.writeToNBT(tag0);
		tag.setTag("item", tag0);
		return super.writeToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		stack = new ItemStack(tag.getCompoundTag("item"));
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		return new SPacketUpdateTileEntity(getPos(), 1, writeToNBT(new NBTTagCompound()));
	}
	
	@Override
	public void onDataPacket(NetworkManager manager, SPacketUpdateTileEntity packet)
	{
		readFromNBT(packet.getNbtCompound());
	}
	
	public void setItem(ItemStack stack)
	{
		this.stack = stack;
		markDirty();
	}
	
	public ItemStack getItem()
	{
		return stack.copy();
	}
}