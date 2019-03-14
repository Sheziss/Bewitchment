package com.bewitchment.common.block.tile.container.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class FLSlot extends SlotItemHandler
{
	public FLSlot(IItemHandler handler, int index, int xPosition, int yPosition)
	{
		super(handler, index, xPosition, yPosition);
	}
	
	@Override
    public boolean isItemValid(ItemStack stack)
    {
		return getItemHandler().isItemValid(getSlotIndex(), stack);
    }
}