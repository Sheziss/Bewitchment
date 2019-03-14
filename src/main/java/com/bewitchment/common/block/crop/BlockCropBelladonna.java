package com.bewitchment.common.block.crop;

import java.util.Random;

import com.bewitchment.common.block.util.ModBlockCrop;
import com.bewitchment.registry.ModObjects;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCropBelladonna extends ModBlockCrop
{
	public BlockCropBelladonna()
	{
		super("crop_belladonna", ModObjects.seed_belladonna, new ItemStack(ModObjects.belladonna), 4);
	}
	
	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		super.updateTick(world, pos, state, rand);
		if (isMaxAge(state) && rand.nextInt(10) <= 3)
		{
			int i = rand.nextInt(4);
			BlockPos pos0 = i == 0 ? pos.north() : i == 1 ? pos.south() : i == 2 ? pos.east() : pos.west();
			if (world.getBlockState(pos0.down()).getBlock().canSustainPlant(state, world, pos, EnumFacing.UP, this) && getSeed().soil.contains(world.getBlockState(pos0.down()).getBlock()) && world.isAirBlock(pos0)) world.setBlockState(pos0, getDefaultState());
		}
	}
}