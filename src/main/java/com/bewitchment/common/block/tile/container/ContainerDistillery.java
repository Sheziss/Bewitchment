package com.bewitchment.common.block.tile.container;

import com.bewitchment.common.block.tile.container.util.ModContainer;
import com.bewitchment.common.block.tile.container.util.ModSlot;
import com.bewitchment.common.block.tile.entity.TileEntityDistillery;

import net.minecraft.entity.player.InventoryPlayer;

public class ContainerDistillery extends ModContainer
{
	public final TileEntityDistillery tile;
	
	public ContainerDistillery(InventoryPlayer inventory, TileEntityDistillery tile)
	{
		this.tile = tile;
		int index = 0;
		addSlotToContainer(new ModSlot(tile, index++, 80, 58));
		for (int i = 0; i < 3; i++)
		{
			addSlotToContainer(new ModSlot(tile, index++, 18, 18 * (i + 1) - 1));
			addSlotToContainer(new ModSlot(tile, index++, 36, 18 * (i + 1) - 1));
		}
		for (int i = 0; i < 3; i++)
		{
			addSlotToContainer(new ModSlot(tile, index++, 124, 18 * (i + 1) - 1));
			addSlotToContainer(new ModSlot(tile, index++, 142, 18 * (i + 1) - 1));
		}
		addPlayerSlots(inventory);
	}
}