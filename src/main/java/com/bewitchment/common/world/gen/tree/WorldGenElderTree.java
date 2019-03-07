package com.bewitchment.common.world.gen.tree;

import java.util.Random;

import com.bewitchment.common.registry.ModBlocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenElderTree extends WorldGenAbstractTree
{
	public WorldGenElderTree(boolean notify)
	{
		super(notify);
	}
	
	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		int h = generateTrunk(world, ModBlocks.log_elder.getDefaultState(), pos, rand, 3, 5);
		for (int x = -2; x < 3; x++)
		{
			for (int z = -2; z < 3; z++)
			{
				for (int y = -2; y < 1; y++)
				{
					BlockPos current = pos.up(h).add(x, y, z);
					if (world.getBlockState(current).getBlock().canBeReplacedByLeaves(world.getBlockState(current), world, current) && ((Math.abs(z) != 2 || Math.abs(x) != 2) || rand.nextDouble() < 0.2) && ((y < 0 || (x < 2 && z < 2 && x > -2 && z > -2)))) world.setBlockState(current, ModBlocks.leaves_elder.getDefaultState());
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