package com.bewitchment.common.block.tile.container;

import com.bewitchment.common.block.tile.container.util.FLContainer;
import com.bewitchment.common.block.tile.container.util.FLSlot;
import com.bewitchment.common.block.tile.entity.TileEntityDistillery;

import net.minecraft.entity.player.InventoryPlayer;

public class ContainerDistillery extends FLContainer
{
	public final TileEntityDistillery tile;
	
	public ContainerDistillery(InventoryPlayer inventory, TileEntityDistillery tile)
	{
		this.tile = tile;
		int index = 0;
		addSlotToContainer(new FLSlot(tile, index++, 80, 58));
		for (int i = 0; i < 3; i++)
		{
			addSlotToContainer(new FLSlot(tile, index++, 18, 18 * (i + 1) - 1));
			addSlotToContainer(new FLSlot(tile, index++, 36, 18 * (i + 1) - 1));
		}
		for (int i = 0; i < 3; i++)
		{
			addSlotToContainer(new FLSlot(tile, index++, 124, 18 * (i + 1) - 1));
			addSlotToContainer(new FLSlot(tile, index++, 142, 18 * (i + 1) - 1));
		}
		addPlayerSlots(inventory);
	}
}