package com.bewitchment.common.block.util;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.Bewitchment;
import com.bewitchment.registry.util.IOreDictionaryContainer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlockFence extends BlockFence implements IOreDictionaryContainer
{
	private final List<String> oreDictionaryNames = new ArrayList<String>();
	
	public ModBlockFence(String name, Block base, String... oreDictionaryNames)
	{
		super(base.getDefaultState().getMaterial(), base.getDefaultState().getMaterial().getMaterialMapColor());
		Bewitchment.proxy.registerValues(this, name, base, oreDictionaryNames);
	}
	
	@Override
	public List<String> getOreDictionaryNames()
	{
		return oreDictionaryNames;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer()
	{
		return getDefaultState().getMaterial() == Material.ICE || getDefaultState().getMaterial() == Material.GLASS ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.CUTOUT;
	}
}