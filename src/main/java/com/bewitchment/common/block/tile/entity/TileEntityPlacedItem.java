package com.bewitchment.common.block.tile.entity;

import com.bewitchment.common.block.tile.entity.util.FLTileEntity;

import net.minecraft.item.ItemStack;

public class TileEntityPlacedItem extends FLTileEntity
{
	public TileEntityPlacedItem()
	{
		super(1);
	}
	
	@Override
	public boolean isItemValid(int slot, ItemStack stack)
	{
		return true;
	}
}