package com.bewitchment.common.block.util;

import java.util.Random;

import com.bewitchment.common.registry.ModBlocks;
import com.bewitchment.common.world.gen.tree.WorldGenCypressTree;
import com.bewitchment.common.world.gen.tree.WorldGenElderTree;
import com.bewitchment.common.world.gen.tree.WorldGenJuniperTree;
import com.bewitchment.common.world.gen.tree.WorldGenYewTree;

import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ModBlockSapling extends ModBlockBush implements IGrowable
{
    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.1, 0, 0.1, 0.9, 0.8, 0.9);
	
	public ModBlockSapling(String name, String... oreNames)
	{
		super(name, oreNames);
	}
	
	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        if (!world.isRemote)
        {
            super.updateTick(world, pos, state, rand);
            if (world.isAreaLoaded(pos, 1) && world.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) this.grow(world, rand, pos, state);
        }
    }
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return SAPLING_AABB;
	}
    
	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
	{
		return true;
	}
	
	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
	{
		return rand.nextFloat() < 0.45f;
	}
	
	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state)
	{
		if (canSaplingGrow(world, pos) && rand.nextBoolean())
		{
			if (this == ModBlocks.sapling_cypress) new WorldGenCypressTree(true).generate(world, rand, pos);
			else if (this == ModBlocks.sapling_elder) new WorldGenElderTree(true).generate(world, rand, pos);
			else if (this == ModBlocks.sapling_juniper) new WorldGenJuniperTree(true).generate(world, rand, pos);
			else if (this == ModBlocks.sapling_yew) new WorldGenYewTree(true).generate(world, rand, pos);
		}
	}
	
	private boolean canSaplingGrow(World world, BlockPos pos)
	{
		if (!world.isRemote)
		{
			if (!world.getBlockState(pos.up()).getBlock().canBeReplacedByLeaves(world.getBlockState(pos.up()), world, pos.up())) return false;
			else if (this == ModBlocks.sapling_cypress)
			{
				for (int x = -1; x < 2; x++)
				{
					for (int z = -1; z < 2; z++)
					{
						for (int y = 0; y < 8; y++)
						{
							BlockPos current = pos.up(2).add(x, y, z);
							if (!world.getBlockState(current).getBlock().canBeReplacedByLeaves(world.getBlockState(current), world, current)) return false;
						}
					}
				}
			}
			else if (this == ModBlocks.sapling_elder)
			{
				for (int x = -1; x < 2; x++)
				{
					for (int z = -1; z < 2; z++)
					{
						for (int y = 0; y < 1; y++)
						{
							BlockPos current = pos.up(2).add(x, y, z);
							if (!world.getBlockState(current).getBlock().canBeReplacedByLeaves(world.getBlockState(current), world, current)) return false;
						}
					}
				}
			}
			else if (this == ModBlocks.sapling_juniper)
			{
				for (int x = -2; x < 3; x++)
				{
					for (int z = -2; z < 3; z++)
					{
						for (int y = 0; y < 2; y++)
						{
							BlockPos current = pos.up(2).add(x, y, z);
							if (!world.getBlockState(current).getBlock().canBeReplacedByLeaves(world.getBlockState(current), world, current)) return false;
						}
					}
				}
			}
			else if (this == ModBlocks.sapling_yew)
			{
				boolean flag = false;
				for (int x = 0; x >= -1; x--)
				{
					for (int z = 0; z >= -1; z--)
					{
						if (world.getBlockState(pos.add(x, 0, z)).getBlock() == ModBlocks.sapling_yew && world.getBlockState(pos.add(x + 1, 0, z)).getBlock() == ModBlocks.sapling_yew && world.getBlockState(pos.add(x, 0, z + 1)).getBlock() == ModBlocks.sapling_yew && world.getBlockState(pos.add(x + 1, 0, z + 1)).getBlock() == ModBlocks.sapling_yew) flag = true;
					}
				}
				if (!flag) return false;
				for (int x = -2; x < 3; x++)
				{
					for (int z = -2; z < 3; z++)
					{
						for (int y = 0; y < 3; y++)
						{
							BlockPos current = pos.up(2).add(x, y, z);
							if (!world.getBlockState(current).getBlock().canBeReplacedByLeaves(world.getBlockState(current), world, current)) return false;
						}
					}
				}
			}
			return true;
		}
		return false;
	}
}