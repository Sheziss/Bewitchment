package com.bewitchment.common.block.util;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.Bewitchment;
import com.bewitchment.registry.IOreDictionaryContainer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlockPressurePlate extends BlockPressurePlate implements IOreDictionaryContainer
{
	private final List<String> ore_dictionary_names = new ArrayList<String>();
	
	public ModBlockPressurePlate(String name, Block base, String... ore_dictionary_names)
	{
		super(base.getDefaultState().getMaterial(), Sensitivity.EVERYTHING);
		Bewitchment.proxy.registerValues(this, name, base, ore_dictionary_names);
	}
	
	@Override
	public List<String> getOreDictionaryNames()
	{
		return ore_dictionary_names;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer()
	{
		return getDefaultState().getMaterial() == Material.ICE || getDefaultState().getMaterial() == Material.GLASS ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.CUTOUT;
	}
}