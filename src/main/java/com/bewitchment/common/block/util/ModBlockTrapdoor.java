package com.bewitchment.common.block.util;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.Bewitchment;
import com.bewitchment.registry.IOreDictionaryContainer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlockTrapdoor extends BlockTrapDoor implements IOreDictionaryContainer
{
	private final List<String> oreDictionaryNames = new ArrayList<String>();
	
	public ModBlockTrapdoor(String name, Block base, String... oreDictionaryNames)
	{
		super(base.getDefaultState().getMaterial());
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