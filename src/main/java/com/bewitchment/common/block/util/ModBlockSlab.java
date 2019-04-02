package com.bewitchment.common.block.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bewitchment.Bewitchment;
import com.bewitchment.registry.ModObjects;
import com.bewitchment.registry.util.IOreDictionaryContainer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPurpurSlab;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlockSlab extends BlockSlab implements IOreDictionaryContainer
{
	public Block half;
	
	private final List<String> oreDictionaryNames = new ArrayList<String>();
	
	private final boolean isDouble;
	
	public ModBlockSlab(String name, Block base, String... oreDictionaryNames)
	{
		this(name, base, false, oreDictionaryNames);
		ModBlockSlab double_slab = new ModBlockSlab(getRegistryName().getPath() + "_double", base, true);
		double_slab.setCreativeTab(null);
		this.half = this;
		double_slab.half = this;
		Item item = new ItemSlab(this, this, double_slab).setRegistryName(getRegistryName()).setTranslationKey(getTranslationKey());
		ModObjects.REGISTRY.add(item);
		Bewitchment.proxy.registerTexture(item);
	}
	
	public ModBlockSlab(String name, Block base, boolean isDouble, String... oreDictionaryNames)
	{
		super(base.getDefaultState().getMaterial());
		Bewitchment.proxy.registerValues(this, name, base, oreDictionaryNames);
		this.setDefaultState(isDouble ? blockState.getBaseState().withProperty(BlockPurpurSlab.VARIANT, BlockPurpurSlab.Variant.DEFAULT) : blockState.getBaseState().withProperty(BlockPurpurSlab.VARIANT, BlockPurpurSlab.Variant.DEFAULT).withProperty(HALF, EnumBlockHalf.BOTTOM));
		this.isDouble = isDouble;
		this.fullBlock = isDouble;
	}
	
	@Override
	public List<String> getOreDictionaryNames()
	{
		return oreDictionaryNames;
	}
	
	@Override
	public String getTranslationKey(int meta)
	{
		return super.getTranslationKey();
	}
	
	@Override
	public Comparable<?> getTypeForItem(ItemStack stack)
	{
		return BlockPurpurSlab.Variant.DEFAULT;
	}
	
	@Override
	public IProperty<?> getVariantProperty()
	{
		return BlockPurpurSlab.VARIANT;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer()
	{
		return getDefaultState().getMaterial() == Material.ICE || getDefaultState().getMaterial() == Material.GLASS ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public ItemStack getItem(World world, BlockPos pos, IBlockState state)
    {
        return new ItemStack(half);
    }

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(half);
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
		return state.getMaterial() == Material.ICE || state.getMaterial() == Material.GLASS ? state != world.getBlockState(pos.offset(face)) ? true : world.getBlockState(pos.offset(face)).getBlock() != this || !isFullCube(world.getBlockState(pos.offset(face))) && super.shouldSideBeRendered(state, world, pos, face) : super.shouldSideBeRendered(state, world, pos, face);
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