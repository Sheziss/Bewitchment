package com.bewitchment.common.block.tile.entity;

import com.bewitchment.common.block.tile.entity.util.ModTileEntity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityPlacedItem extends ModTileEntity
{
	public final ItemStackHandler inventory = new ItemStackHandler(18)
	{
		@Override
		public int getSlotLimit(int slot)
		{
			return 1;
		}
		
		@Override
		protected void onContentsChanged(int slot)
	    {
			markDirty();
	    }
	};
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag)
	{
		tag.setTag("inventory", inventory.serializeNBT());
		return super.writeToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		inventory.deserializeNBT(tag.getCompoundTag("inventory"));
	}
}