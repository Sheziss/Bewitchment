package com.bewitchment.common.block;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCandleSmall extends BlockCandle
{
	private static final AxisAlignedBB BOX = new AxisAlignedBB(0.38, 0, 0.38, 0.62, 0.5, 0.62);
	
	public BlockCandleSmall(String name)
	{
		super(name);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return BOX;
	}
	
	@Override
	public int getLightValue(IBlockState state)
	{
		return state.getValue(LIT) ? 9 : 0;
	}
	
	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
	{
		if (state.getValue(LIT)) world.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 0.7, pos.getZ() + 0.5, 0, 0, 0);
	}
}