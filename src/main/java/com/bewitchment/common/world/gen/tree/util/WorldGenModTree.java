package com.bewitchment.common.world.gen.tree.util;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public abstract class WorldGenModTree extends WorldGenAbstractTree
{
	public WorldGenModTree(boolean notify)
	{
		super(notify);
	}
	
	public abstract boolean canSaplingGrow(World world, BlockPos pos);
	
	protected int generateTrunk(World world, IBlockState state, BlockPos pos, Random rand, int minHeight, int maxHeight)
	{
		int height = minHeight + rand.nextInt(maxHeight - minHeight + 1);
		for (int i = 0; i < height; i++) if (world.getBlockState(pos.up(i)).getBlock().canBeReplacedByLeaves(world.getBlockState(pos.up(i)), world, pos.up(i)) || i == 0) world.setBlockState(pos.up(i), state);
		return height;
	}
}