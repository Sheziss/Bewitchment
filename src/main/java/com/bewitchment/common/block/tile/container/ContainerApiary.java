package com.bewitchment.common.block.tile.container;

import com.bewitchment.common.block.tile.container.util.ModContainer;
import com.bewitchment.common.block.tile.container.util.ModSlot;
import com.bewitchment.common.block.tile.entity.TileEntityApiary;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerApiary extends ModContainer {
	public ContainerApiary(InventoryPlayer inventory, TileEntityApiary tile) {
		int index = 0;
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 6; y++) {
				addSlotToContainer(new ModSlot(tile.inventory, index++, 35 + y * 18, 16 + x * 18, 1));
			}
		}
		addPlayerSlots(inventory);
	}
}