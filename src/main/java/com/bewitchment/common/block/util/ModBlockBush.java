package com.bewitchment.common.block.util;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.Bewitchment;
import com.bewitchment.registry.IOreDictionaryContainer;

import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ModBlockBush extends BlockBush implements IOreDictionaryContainer
{
	private final List<String> ore_dictionary_names = new ArrayList<String>();
	
	public ModBlockBush(String name, String... ore_dictionary_names)
	{
		super();
		Bewitchment.proxy.registerValues(this, name, Material.PLANTS, SoundType.PLANT, 0, 0, "", 0, ore_dictionary_names);
	}
	
	@Override
	public List<String> getOreDictionaryNames()
	{
		return ore_dictionary_names;
	}
}