package com.bewitchment.common.block.tile.container;

import com.bewitchment.common.block.tile.container.util.ModContainer;
import com.bewitchment.common.block.tile.container.util.ModSlot;
import com.bewitchment.common.block.tile.entity.TileEntityDistillery;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ContainerDistillery extends ModContainer
{
	public final TileEntityDistillery tile;
	
	public ContainerDistillery(InventoryPlayer inventory, TileEntityDistillery tile)
	{
		this.tile = tile;
		IItemHandler up = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
		IItemHandler down = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
		int ui = 0, di = 0;
		addSlotToContainer(new ModSlot(tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH), 0, 80, 58));
		for (int i = 0; i < 3; i++)
		{
			addSlotToContainer(new ModSlot(up, ui++, 18, 18 * (i + 1) - 1));
			addSlotToContainer(new ModSlot(up, ui++, 36, 18 * (i + 1) - 1));
			addSlotToContainer(new ModSlot(down, di++, 124, 18 * (i + 1) - 1));
			addSlotToContainer(new ModSlot(down, di++, 142, 18 * (i + 1) - 1));
		}
		addPlayerSlots(inventory);
	}
}