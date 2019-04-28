package com.bewitchment.common.block.util;

import com.bewitchment.Util;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class ModBlockCrop extends BlockCrops {
	private final Item seed, crop;

	public ModBlockCrop(String name, Item seed, Item crop) {
		super();
		Util.registerValues(this, name, Material.PLANTS, SoundType.PLANT, 0, 0, "", 0);
		setCreativeTab(null);
		this.seed = seed;
		this.crop = crop;
	}

	@Override
	public Item getCrop() {
		return crop;
	}

	@Override
	public Item getSeed() {
		return seed;
	}
}