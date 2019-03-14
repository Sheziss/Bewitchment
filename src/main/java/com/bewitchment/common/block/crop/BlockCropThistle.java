package com.bewitchment.common.block.crop;

import java.util.Random;

import com.bewitchment.common.block.util.ModBlockCrop;
import com.bewitchment.registry.ModObjects;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCropThistle extends ModBlockCrop
{
	public BlockCropThistle()
	{
		super("crop_thistle", ModObjects.seed_thistle, new ItemStack(ModObjects.thistle), 3);
	}
	
	@Override
	public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		if (entity instanceof EntityLivingBase) entity.attackEntityFrom(DamageSource.CACTUS, 0.5f);
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