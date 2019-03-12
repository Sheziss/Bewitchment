package com.bewitchment.common.block.tile.container;

import com.bewitchment.common.block.tile.entity.TileEntityOven;

import moriyashiine.froglib.common.block.tile.container.ModContainer;
import moriyashiine.froglib.common.block.tile.container.ModSlot;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;

public class ContainerOven extends ModContainer
{
	public final TileEntityOven tile;
	
	public ContainerOven(InventoryPlayer inventory, TileEntityOven tile)
	{
		this.tile = tile;
		int index = 0;
		addSlotToContainer(new ModSlot(tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH), index++, 44, 55));
		addSlotToContainer(new ModSlot(tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH), index++, 80, 55));
		addSlotToContainer(new ModSlot(tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP), index++, 44, 19));
		addSlotToContainer(new ModSlot(tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN), index++, 116, 19));
		addSlotToContainer(new ModSlot(tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN), index++, 116, 55));
		addPlayerSlots(inventory);
	}
}