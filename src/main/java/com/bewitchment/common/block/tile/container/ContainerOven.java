package com.bewitchment.common.block.tile.container;

import com.bewitchment.common.block.tile.container.util.ModContainer;
import com.bewitchment.common.block.tile.container.util.ModSlot;
import com.bewitchment.common.block.tile.entity.TileEntityOven;

import net.minecraft.entity.player.InventoryPlayer;

public class ContainerOven extends ModContainer
{
	public final TileEntityOven tile;
	
	public ContainerOven(InventoryPlayer inventory, TileEntityOven tile)
	{
		this.tile = tile;
		int index = 0;
		addSlotToContainer(new ModSlot(tile.inventory, index++, 44, 55));
		addSlotToContainer(new ModSlot(tile.inventory, index++, 80, 55));
		addSlotToContainer(new ModSlot(tile.inventory, index++, 44, 19));
		addSlotToContainer(new ModSlot(tile.inventory, index++, 116, 19));
		addSlotToContainer(new ModSlot(tile.inventory, index++, 116, 55));
		addPlayerSlots(inventory);
	}
}