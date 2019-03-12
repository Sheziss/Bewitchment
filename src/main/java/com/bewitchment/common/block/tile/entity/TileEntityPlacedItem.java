package com.bewitchment.common.block.tile.entity;

import moriyashiine.froglib.common.block.tile.FLTileEntity;
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