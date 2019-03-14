package com.bewitchment.common.item.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bewitchment.Bewitchment;
import com.bewitchment.registry.IOreDictionaryContainer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItemDoor extends ItemDoor implements IOreDictionaryContainer
{
	private final List<String> ore_dictionary_names = new ArrayList<String>();
	
	public final FLBlockDoor door;
	
	public ModItemDoor(String name, Block base, String... ore_dictionary_names)
	{
		this(name, base, new FLBlockDoor("block_" + name, base), ore_dictionary_names);
	}
	
	private ModItemDoor(String name, Block base, FLBlockDoor door, String... ore_dictionary_names)
	{
		super(door);
		Bewitchment.proxy.registerValues(this, name, ore_dictionary_names);
		this.door = door;
		this.door.drop = new ItemStack(this);
	}
	
	@Override
	public List<String> getOreDictionaryNames()
	{
		return ore_dictionary_names;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag)
	{
		String tip = "tooltip." + getTranslationKey().substring(5);
		if (!I18n.format(tip).equals(tip)) tooltip.add(TextFormatting.GRAY + I18n.format(tip));
	}
	
	public static class FLBlockDoor extends BlockDoor
	{
		public ItemStack drop;
		
		public FLBlockDoor(String name, Block base)
		{
			super(base.getDefaultState().getMaterial());
			Bewitchment.proxy.registerValues(this, name, base);
			setCreativeTab(null);
		}
		
		@Override
		public ItemStack getItem(World world, BlockPos pos, IBlockState state)
		{
			return drop;
		}
		
		@Override
		public Item getItemDropped(IBlockState state, Random rand, int fortune)
		{
			return state.getValue(HALF) == EnumDoorHalf.UPPER ? Items.AIR : drop.getItem();
		}
		
		@Override
		@SideOnly(Side.CLIENT)
		public BlockRenderLayer getRenderLayer()
		{
			return getDefaultState().getMaterial() == Material.ICE || getDefaultState().getMaterial() == Material.GLASS ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.CUTOUT;
		}
	}
}