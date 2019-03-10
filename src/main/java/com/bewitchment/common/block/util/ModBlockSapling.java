package com.bewitchment.common.block.util;

import java.util.Random;

import com.bewitchment.common.world.gen.tree.util.WorldGenModTree;

import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ModBlockSapling extends ModBlockBush implements IGrowable
{
    private static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.1, 0, 0.1, 0.9, 0.8, 0.9);
    
    public final Class<? extends WorldGenModTree> gen;
	
	public ModBlockSapling(String name, Class<? extends WorldGenModTree> gen, String... oreNames)
	{
		super(name, oreNames);
		this.gen = gen;
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
		WorldGenModTree generator = null;
		try {generator = gen.getDeclaredConstructor(boolean.class).newInstance(false);}
		catch (Exception e) {e.printStackTrace();}
		if (!world.isRemote && world.getLightFromNeighbors(pos.up()) >= 9 && world.getBlockState(pos.up()).getBlock().canBeReplacedByLeaves(world.getBlockState(pos.up()), world, pos.up()) && generator.canSaplingGrow(world, pos) && rand.nextBoolean()) generator.generate(world, rand, pos);
	}
}