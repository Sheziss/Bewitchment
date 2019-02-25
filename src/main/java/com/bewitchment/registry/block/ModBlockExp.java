package com.bewitchment.registry.block;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ModBlockExp extends ModBlock
{
	public ModBlockExp(String name, Material mat, SoundType sound, float hardness, float resistance, String tool, int level, String... oreNames)
	{
		super(name, mat, sound, hardness, resistance, tool, level, oreNames);
	}
	
	@Override
	public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune)
	{
		return MathHelper.getInt(world instanceof World ? ((World) world).rand : new Random(), 1, 6);
	}
}