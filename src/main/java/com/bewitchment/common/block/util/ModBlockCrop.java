package com.bewitchment.common.block.util;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.item.util.ModItemSeed;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModBlockCrop extends BlockCrops
{
	protected static final PropertyBool TOP = PropertyBool.create("top");
	
	private final int maxAge;
	private final ModItemSeed seed;
	private final ItemStack crop;
	
	public ModBlockCrop(String name, ModItemSeed seed, ItemStack crop, int maxAge)
	{
		super();
		Bewitchment.proxy.registerValues(this, name, Material.PLANTS, SoundType.PLANT, 0, 0, "", 0);
		setCreativeTab(null);
		this.maxAge = maxAge;
		this.seed = seed;
		this.crop = crop;
	}
	
	@Override
	public Item getCrop()
	{
		return crop.getItem();
	}
	
	@Override
	public int getMaxAge()
	{
		return maxAge;
	}
	
	@Override
	public ModItemSeed getSeed()
	{
		return seed;
	}
}