package com.bewitchment.common.block.crop;

import java.util.Random;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.registry.ModItems;

import moriyashiine.froglib.common.block.FLBlockCrop;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCropThistle extends FLBlockCrop
{
	public BlockCropThistle()
	{
		super(Bewitchment.MOD_ID, "crop_thistle", Bewitchment.proxy.tab, ModItems.seed_thistle, new ItemStack(ModItems.thistle), 3);
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
	
	@Override
	public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		if (entity instanceof EntityLivingBase) entity.attackEntityFrom(DamageSource.CACTUS, 0.5f);
	}
}