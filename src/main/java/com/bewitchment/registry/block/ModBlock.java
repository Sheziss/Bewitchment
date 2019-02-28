package com.bewitchment.registry.block;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.core.Bewitchment;
import com.bewitchment.registry.IOreName;
import com.bewitchment.registry.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlock extends Block implements IOreName
{
	private final List<String> oreNames = new ArrayList<String>();
	
	public ModBlock(String name, Material mat, SoundType sound, float hardness, float resistance, String tool, int level, String... oreNames)
	{
		super(mat);
		this.setRegistryName(new ResourceLocation(Bewitchment.MOD_ID, name));
		this.setTranslationKey(this.getRegistryName().toString());
		this.setCreativeTab(Bewitchment.proxy.tab);
		this.setSoundType(sound);
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setHarvestLevel(tool, level);
		if (mat == Material.CARPET) Blocks.FIRE.setFireInfo(this, 60, 20);
		if (mat == Material.CLOTH || mat == Material.LEAVES) Blocks.FIRE.setFireInfo(this, 30, 60);
		if (mat == Material.PLANTS) Blocks.FIRE.setFireInfo(this, 60, 100);
		if (mat == Material.TNT || mat == Material.VINE) Blocks.FIRE.setFireInfo(this, 15, 100);
		if (mat == Material.WOOD) Blocks.FIRE.setFireInfo(this, 5, 20);
		if (mat == Material.ICE) this.setDefaultSlipperiness(0.98f);
		for (String ore : oreNames) this.oreNames.add(ore);
		ModBlocks.REGISTRY.add(this);
	}
	
	@Override
	public List<String> getOreNames()
	{
		return oreNames;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer()
	{
		return getDefaultState().getMaterial() == Material.ICE || getDefaultState().getMaterial() == Material.GLASS ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return state.getMaterial() == Material.WOOD;
    }
	
	@Override
	public boolean isWood(IBlockAccess world, BlockPos pos)
    {
		return world.getBlockState(pos).getMaterial() == Material.WOOD;
    }
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean isFullCube(IBlockState state)
    {
		return state.getMaterial() == Material.ICE || state.getMaterial() == Material.GLASS ? false : super.isFullCube(state);
    }
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return state.getMaterial() == Material.ICE || state.getMaterial() == Material.GLASS ? false : super.isOpaqueCube(state);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face)
	{
		return super.shouldSideBeRendered(state, world, pos, face) && (state.getMaterial() == Material.ICE || state.getMaterial() == Material.GLASS ? world.getBlockState(pos.offset(face)).getBlock() != this : true);
	}
}