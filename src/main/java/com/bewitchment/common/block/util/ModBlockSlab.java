package com.bewitchment.common.block.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.registry.IOreName;
import com.bewitchment.common.registry.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPurpurSlab;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlockSlab extends BlockSlab implements IOreName
{
	public Block half;
	
	private final List<String> oreNames = new ArrayList<String>();
	
	private final boolean isDouble;
	
	@SuppressWarnings("deprecation")
	public ModBlockSlab(String name, Block base, boolean isDouble, String... oreNames)
	{
		super(base.getDefaultState().getMaterial());
		this.setRegistryName(new ResourceLocation(Bewitchment.MOD_ID, name));
		this.setTranslationKey(this.getRegistryName().toString());
		if (!isDouble) this.setCreativeTab(Bewitchment.proxy.tab);
		this.setHardness(base.getBlockHardness(null, null, null));
		this.setResistance(base.getExplosionResistance(null)*5);
		this.setHarvestLevel(base.getHarvestTool(base.getDefaultState()), base.getHarvestLevel(base.getDefaultState()));
		this.setSoundType(base.getSoundType());
		if (base.getDefaultState().getMaterial() == Material.CARPET) Blocks.FIRE.setFireInfo(this, 60, 20);
		if (base.getDefaultState().getMaterial() == Material.CLOTH || base.getDefaultState().getMaterial() == Material.LEAVES) Blocks.FIRE.setFireInfo(this, 30, 60);
		if (base.getDefaultState().getMaterial() == Material.PLANTS) Blocks.FIRE.setFireInfo(this, 60, 100);
		if (base.getDefaultState().getMaterial() == Material.TNT || base.getDefaultState().getMaterial() == Material.VINE) Blocks.FIRE.setFireInfo(this, 15, 100);
		if (base.getDefaultState().getMaterial() == Material.WOOD) Blocks.FIRE.setFireInfo(this, 5, 20);
		if (base.getDefaultState().getMaterial() == Material.ICE) this.setDefaultSlipperiness(0.98f);
		this.setDefaultState(isDouble ? blockState.getBaseState().withProperty(BlockPurpurSlab.VARIANT, BlockPurpurSlab.Variant.DEFAULT) : blockState.getBaseState().withProperty(BlockPurpurSlab.VARIANT, BlockPurpurSlab.Variant.DEFAULT).withProperty(HALF, EnumBlockHalf.BOTTOM));
		this.isDouble = isDouble;
		this.fullBlock = isDouble;
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
	public String getTranslationKey(int meta)
	{
		return super.getTranslationKey();
	}

	@Override
	public IProperty<?> getVariantProperty()
	{
		return BlockPurpurSlab.VARIANT;
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack)
	{
		return BlockPurpurSlab.Variant.DEFAULT;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(half);
    }
	
	@Override
	public ItemStack getItem(World world, BlockPos pos, IBlockState state)
    {
        return new ItemStack(half);
    }
	
	@Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face)
    {
		return state.getMaterial() == Material.ICE || state.getMaterial() == Material.GLASS ? false : super.doesSideBlockRendering(state, world, pos, face);
    }
	
	@Override
	public boolean isDouble()
	{
		return isDouble;
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
		return state.getMaterial() == Material.ICE || state.getMaterial() == Material.GLASS ? state != world.getBlockState(pos.offset(face)) ? true : world.getBlockState(pos.offset(face)).getBlock() != this && super.shouldSideBeRendered(state, world, pos, face): super.shouldSideBeRendered(state, world, pos, face);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return isDouble() ? getDefaultState().withProperty(BlockPurpurSlab.VARIANT, BlockPurpurSlab.Variant.DEFAULT) : getDefaultState().withProperty(BlockPurpurSlab.VARIANT, BlockPurpurSlab.Variant.DEFAULT).withProperty(HALF, meta == 0 ? EnumBlockHalf.BOTTOM : EnumBlockHalf.TOP);
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
        return !isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP ? 1 : 0;
	}
	
	@Override
	protected BlockStateContainer createBlockState()
    {
        return isDouble() ? new BlockStateContainer(this, BlockPurpurSlab.VARIANT) : new BlockStateContainer(this, HALF, BlockPurpurSlab.VARIANT);
    }
}