package com.bewitchment.common.block.tile.entity;

import com.bewitchment.common.block.tile.entity.util.ModTileEntity;

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
	};
	
	@Override
	public ItemStackHandler[] getInventories()
	{
		return new ItemStackHandler[] {inventory};
	}
}