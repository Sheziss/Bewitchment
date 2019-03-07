package com.bewitchment.common.world.gen.tree;

import java.util.Random;

import com.bewitchment.common.registry.ModBlocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenYewTree extends WorldGenAbstractTree
{
	public WorldGenYewTree(boolean notify)
	{
		super(notify);
	}
	
	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		int h1 = generateTrunk(world, ModBlocks.log_yew.getDefaultState(), pos, rand, 4, 6);
		int h2 = generateTrunk(world, ModBlocks.log_yew.getDefaultState(), world.getBlockState(pos.east()).getBlock() == ModBlocks.sapling_yew ? pos.east() : pos.west(), rand, 4, 6);
		int h3 = generateTrunk(world, ModBlocks.log_yew.getDefaultState(), world.getBlockState(pos.east().north()).getBlock() == ModBlocks.sapling_yew ? pos.east().north() : world.getBlockState(pos.east().south()).getBlock() == ModBlocks.sapling_yew ? pos.east().south() : world.getBlockState(pos.west().north()).getBlock() == ModBlocks.sapling_yew ? pos.west().north() : pos.west().south(), rand, 4, 6);
		int h4 = generateTrunk(world, ModBlocks.log_yew.getDefaultState(), world.getBlockState(pos.north()).getBlock() == ModBlocks.sapling_yew ? pos.north() : pos.south(), rand, 4, 6);
		int hMin = Math.min(Math.min(h1, h2), Math.min(h3, h4));
		int hMax = Math.max(Math.max(h1, h2), Math.max(h3, h4));
		for (int x = -2; x < 4; x++)
		{
			for (int z = -3; z < 3; z++)
			{
				for (int y = -2; y < hMax - hMin + 2; y++)
				{
					BlockPos current = pos.up(hMin).add(x, y, z);
					if (world.getBlockState(current).getBlock().canBeReplacedByLeaves(world.getBlockState(current), world, current) && !(((x == -2 || x == 3 || z == -3 || z == 2) && (rand.nextDouble() < 0.1 || y >= hMax - hMin)) && ((x == -1 || x == 2 || z == -2 || z == 1) && y == hMax - hMin + 1) || (x == -2 && z == -3) || (x == -2 && z == 2) || (x == 3 && z == -3) || (x == 3 && z == 2))) world.setBlockState(current, ModBlocks.leaves_yew.getDefaultState());
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