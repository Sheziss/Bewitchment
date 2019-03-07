package com.bewitchment.common.world.gen.tree;

import java.util.Random;

import com.bewitchment.common.registry.ModBlocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenCypressTree extends WorldGenAbstractTree
{
	public WorldGenCypressTree(boolean notify)
	{
		super(notify);
	}
	
	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		int h = generateTrunk(world, ModBlocks.log_cypress.getDefaultState(), pos, rand, 5, 13);
		for (int y = -h + 2; y < 2; y++)
		{
			boolean cross = y <= -1;
			boolean core = y > -1;
			boolean full = y >= -h + 3 && y <= -h / 2;
			for (int x = -1; x <= 1; x++)
			{
				for (int z = -1; z <= 1; z++)
				{
					BlockPos current = pos.up(h).add(x, y, z);
					if (world.getBlockState(current).getBlock().canBeReplacedByLeaves(world.getBlockState(current), world, current) && ((core && z == 0 && x == 0) || full || (cross && (z == 0 || x == 0)))) world.setBlockState(current, ModBlocks.leaves_cypress.getDefaultState());
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