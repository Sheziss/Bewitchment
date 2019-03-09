package com.bewitchment.common.block.tile.entity;

import com.bewitchment.common.block.tile.util.ModTileEntity;

import net.minecraft.item.ItemStack;

public class TileEntityPlacedItem extends ModTileEntity
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