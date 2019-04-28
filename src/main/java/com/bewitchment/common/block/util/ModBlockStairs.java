package com.bewitchment.common.block.util;

import com.bewitchment.Util;
import com.bewitchment.registry.util.IOreDictionaryContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.ArrayList;
import java.util.List;

public class ModBlockStairs extends BlockStairs implements IOreDictionaryContainer {
	private final List<String> oreDictionaryNames = new ArrayList<String>();

	public ModBlockStairs(String name, Block base, String... oreDictionaryNams) {
		super(base.getDefaultState());
		Util.registerValues(this, name, base, oreDictionaryNams);
	}

	@Override
	public List<String> getOreDictionaryNames() {
		return oreDictionaryNames;
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		return state.getMaterial() != Material.ICE && state.getMaterial() != Material.GLASS && super.doesSideBlockRendering(state, world, pos, face);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		return state.getMaterial() == Material.ICE || state.getMaterial() == Material.GLASS ? state != world.getBlockState(pos.offset(face)) || world.getBlockState(pos.offset(face)).getBlock() != this && super.shouldSideBeRendered(state, world, pos, face) : super.shouldSideBeRendered(state, world, pos, face);
	}
}