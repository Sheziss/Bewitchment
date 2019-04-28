package com.bewitchment.common.block.tile.entity.util;

import net.minecraft.util.math.BlockPos;

public interface IAltarStorage {
	BlockPos getAltarPosition();

	void setAltarPosition(BlockPos pos);
}