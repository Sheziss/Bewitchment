package com.bewitchment.common.block.crop;

import java.util.Random;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.registry.ModItems;

import moriyashiine.froglib.common.block.ModBlockCrop;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCropKelp extends ModBlockCrop
{
	private static final AxisAlignedBB[] KELP_AABB = {new AxisAlignedBB(0, 0, 0, 1, 0.125, 1), new AxisAlignedBB(0, 0, 0, 1, 0.25, 1), new AxisAlignedBB(0, 0, 0, 1, 0.375, 1), new AxisAlignedBB(0, 0, 0, 1, 0.5, 1), new AxisAlignedBB(0, 0, 0, 1, 0.75, 1), new AxisAlignedBB(0, 0, 0, 1, 0.95, 1), new AxisAlignedBB(0, 0, 0, 1, 1, 1), new AxisAlignedBB(0, 0, 0, 1, 1, 1)};
	
	public BlockCropKelp()
	{
		super(Bewitchment.MOD_ID, "crop_kelp", Bewitchment.proxy.tab, ModItems.seed_kelp, new ItemStack(ModItems.kelp), 7);
	}
		
	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		super.updateTick(world, pos, state, rand);
		if (rand.nextInt(2) == 0) grow(world, pos, state);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return KELP_AABB[state.getValue(AGE)];
	}
	
	@Override
	public Material getMaterial(IBlockState state)
	{
		return Material.WATER;
	}
	
	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
	{
		Block block = world.getBlockState(pos.down()).getBlock();
		return block == this || getSeed().soil.contains(block) ? true : canPlaceBlockAt(world, pos);
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos)
	{
		return world.getBlockState(pos.up()).getMaterial() == Material.WATER && world.getBlockState(pos.up(2)).getMaterial() == Material.WATER;
	}
	
	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
    {
        return world.getBlockState(pos.up()).getBlock() != this && (isMaxAge(state) ? world.getBlockState(pos.up(2)).getMaterial() == Material.WATER : true);
    }
	
	@Override
	public boolean isReplaceable(IBlockAccess world, BlockPos pos)
	{
		return false;
	}
	
	@Override
	public void grow(World world, BlockPos pos, IBlockState state)
    {
		if (isMaxAge(state) && world.getBlockState(pos.up()).getBlock() == Blocks.WATER && world.getBlockState(pos.up(2)).getBlock() == Blocks.WATER) world.setBlockState(pos.up(), getDefaultState());
		else super.grow(world, pos, state);
    }
	
	@Override
	public void onPlayerDestroy(World world, BlockPos pos, IBlockState state)
	{
		world.setBlockState(pos, Blocks.WATER.getDefaultState());
	}
	
	@Override
	protected void checkAndDropBlock(World world, BlockPos pos, IBlockState state)
	{
		if (!canBlockStay(world, pos, state))
		{
			this.dropBlockAsItem(world, pos, state, 0);
			world.setBlockState(pos, Blocks.WATER.getDefaultState());
		}
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, AGE, BlockLiquid.LEVEL);
	}
}