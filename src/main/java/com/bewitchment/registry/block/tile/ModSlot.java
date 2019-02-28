package com.bewitchment.registry.block.tile;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ModSlot extends Slot
{
	public ModSlot(IInventory inventory, int index, int xPosition, int yPosition)
	{
		super(inventory, index, xPosition, yPosition);
	}
	
	@Override
    public boolean isItemValid(ItemStack stack)
    {
		return inventory.isItemValidForSlot(this.getSlotIndex(), stack);
    }
}