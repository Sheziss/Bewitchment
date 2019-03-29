package com.bewitchment.common.block.tile.container;

import com.bewitchment.common.block.tile.container.util.ModContainer;
import com.bewitchment.common.block.tile.container.util.ModSlot;
import com.bewitchment.common.block.tile.entity.TileEntityOven;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ContainerOven extends ModContainer
{
	public final TileEntityOven tile;
	
	public ContainerOven(InventoryPlayer inventory, TileEntityOven tile)
	{
		this.tile = tile;
		IItemHandler up = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
		IItemHandler down = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
		int ui = 0, di = 0;
		addSlotToContainer(new ModSlot(up, ui++, 44, 55));
		addSlotToContainer(new ModSlot(up, ui++, 80, 55));
		addSlotToContainer(new ModSlot(up, ui++, 44, 19));
		addSlotToContainer(new ModSlot(down, di++, 116, 19));
		addSlotToContainer(new ModSlot(down, di++, 116, 55));
		addPlayerSlots(inventory);
	}
}