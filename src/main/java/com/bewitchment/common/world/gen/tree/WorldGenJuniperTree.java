package com.bewitchment.common.world.gen.tree;

import java.util.ArrayList;
import java.util.Random;

import com.bewitchment.common.registry.ModBlocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenJuniperTree extends WorldGenAbstractTree
{
	public WorldGenJuniperTree(boolean notify)
	{
		super(notify);
	}
	
	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		int h = generateTrunk(world, ModBlocks.log_juniper.getDefaultState(), pos, rand, 2, 4);
		EnumFacing branchOffset = EnumFacing.HORIZONTALS[rand.nextInt(4)];
		BlockPos branching = pos.up(h).offset(branchOffset);
		ArrayList<BlockPos> logs = new ArrayList<BlockPos>();
		if (world.getBlockState(branching).getBlock().canBeReplacedByLeaves(world.getBlockState(branching), world, branching))
		{
			world.setBlockState(branching, ModBlocks.log_juniper.getDefaultState());
			logs.add(branching);
		}
		BlockPos other = branching.offset(branchOffset.getOpposite(), 2);
		if (world.getBlockState(other).getBlock().canBeReplacedByLeaves(world.getBlockState(other), world, other))
		{
			world.setBlockState(other, ModBlocks.log_juniper.getDefaultState());
			logs.add(other);
		}
		for (int i = 0; i < h / 2; i++)
		{
			BlockPos current = branching.up().offset(branchOffset, i + 1);
			if (world.getBlockState(current).getBlock().canBeReplacedByLeaves(world.getBlockState(current), world, current))
			{
				logs.add(current);
				world.setBlockState(current, ModBlocks.log_juniper.getDefaultState());
			}
		}
		for (BlockPos pos0 : logs)
		{
			for (EnumFacing face : EnumFacing.VALUES)
			{
				BlockPos pos1 = pos0.offset(face);
				if (world.getBlockState(pos1).getBlock().canBeReplacedByLeaves(world.getBlockState(pos1), world, pos1)) world.setBlockState(pos1, ModBlocks.leaves_juniper.getDefaultState());
				for (EnumFacing face0 : EnumFacing.VALUES)
				{
					if (face0 != EnumFacing.DOWN)
					{
						BlockPos pos2 = pos0.offset(face).offset(face0);
						if (world.getBlockState(pos2).getBlock().canBeReplacedByLeaves(world.getBlockState(pos2), world, pos2) && rand.nextDouble() < 0.8) world.setBlockState(pos2, ModBlocks.leaves_juniper.getDefaultState());
					}
				}
			}
		}
		return true;
	}
	
	private int generateTrunk(World world, IBlockState state, BlockPos pos, Random rand, int minHeight, int maxHeight)
	{
		int height = minHeight + rand.nextInt(maxHeight - minHeight + 1);
		for (int i = 0; i < height; i++) if (world.getBlockState(pos.up(i)).getBlock().canBeReplacedByLeaves(world.getBlockState(pos.up(i)), world, pos.up(i)) || i == 0) world.setBlockState(pos.up(i), state);
		return height;
	}
}