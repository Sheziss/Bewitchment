package com.bewitchment.common.block.tile.entity;

import moriyashiine.froglib.common.block.tile.ModTileEntity;
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