package com.bewitchment.common.block.tile.entity.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public class TileEntityAltarStorage extends ModTileEntity implements IAltarStorage {
	private BlockPos altarPos;

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		if (altarPos != null) tag.setLong("altarPos", altarPos.toLong());
		return super.writeToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		if (tag.hasKey("altarPos")) setAltarPosition(BlockPos.fromLong(tag.getLong("altarPos")));
	}

	@Override
	public BlockPos getAltarPosition() {
		return altarPos;
	}

	@Override
	public void setAltarPosition(BlockPos pos) {
		altarPos = pos;
	}
}