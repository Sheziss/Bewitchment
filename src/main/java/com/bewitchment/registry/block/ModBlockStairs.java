package com.bewitchment.registry.block;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.core.Bewitchment;
import com.bewitchment.registry.IOreName;
import com.bewitchment.registry.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class ModBlockStairs extends BlockStairs implements IOreName
{
	private final List<String> oreNames = new ArrayList<String>();
	
	public ModBlockStairs(String name, Block original, String tool, int level, String... oreNames)
	{
		super(original.getDefaultState());
		this.setRegistryName(new ResourceLocation(Bewitchment.MOD_ID, name));
		this.setTranslationKey(this.getRegistryName().toString());
		this.setCreativeTab(Bewitchment.proxy.tab);
		this.setHarvestLevel(tool, level);
		if (original.getDefaultState().getMaterial() == Material.CARPET) Blocks.FIRE.setFireInfo(this, 60, 20);
		if (original.getDefaultState().getMaterial() == Material.CLOTH || original.getDefaultState().getMaterial() == Material.LEAVES) Blocks.FIRE.setFireInfo(this, 30, 60);
		if (original.getDefaultState().getMaterial() == Material.PLANTS) Blocks.FIRE.setFireInfo(this, 60, 100);
		if (original.getDefaultState().getMaterial() == Material.TNT || original.getDefaultState().getMaterial() == Material.VINE) Blocks.FIRE.setFireInfo(this, 15, 100);
		if (original.getDefaultState().getMaterial() == Material.WOOD) Blocks.FIRE.setFireInfo(this, 5, 20);
		for (String ore : oreNames) this.oreNames.add(ore);
		ModBlocks.REGISTRY.add(this);
	}
	
	@Override
	public List<String> getOreNames()
	{
		return oreNames;
	}
	
	@Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face)
    {
		return state.getMaterial() == Material.ICE || state.getMaterial() == Material.GLASS ? false : super.doesSideBlockRendering(state, world, pos, face);
    }
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face)
	{
		return state.getMaterial() == Material.ICE || state.getMaterial() == Material.GLASS ? state != world.getBlockState(pos.offset(face)) ? true : world.getBlockState(pos.offset(face)).getBlock() != this && super.shouldSideBeRendered(state, world, pos, face): super.shouldSideBeRendered(state, world, pos, face);
	}
}