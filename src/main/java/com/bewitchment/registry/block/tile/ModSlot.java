package com.bewitchment.registry.block.tile;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ModSlot extends SlotItemHandler
{
	public ModSlot(IItemHandler handler, int index, int xPosition, int yPosition)
	{
		super(handler, index, xPosition, yPosition);
	}
	
	@Override
    public boolean isItemValid(ItemStack stack)
    {
		return getItemHandler().isItemValid(getSlotIndex(), stack);
    }
}