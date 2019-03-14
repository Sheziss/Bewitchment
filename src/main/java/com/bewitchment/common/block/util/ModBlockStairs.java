package com.bewitchment.common.block.util;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.Bewitchment;
import com.bewitchment.registry.IOreDictionaryContainer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class ModBlockStairs extends BlockStairs implements IOreDictionaryContainer
{
	private final List<String> ore_dictionary_names = new ArrayList<String>();
	
	public ModBlockStairs(String name, Block base, String... ore_dictionary_names)
	{
		super(base.getDefaultState());
		Bewitchment.proxy.registerValues(this, name, base, ore_dictionary_names);
	}
	
	@Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face)
    {
		return state.getMaterial() == Material.ICE || state.getMaterial() == Material.GLASS ? false : super.doesSideBlockRendering(state, world, pos, face);
    }
	
	@Override
	public List<String> getOreDictionaryNames()
	{
		return ore_dictionary_names;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face)
	{
		return state.getMaterial() == Material.ICE || state.getMaterial() == Material.GLASS ? state != world.getBlockState(pos.offset(face)) ? true : world.getBlockState(pos.offset(face)).getBlock() != this && super.shouldSideBeRendered(state, world, pos, face): super.shouldSideBeRendered(state, world, pos, face);
	}
}