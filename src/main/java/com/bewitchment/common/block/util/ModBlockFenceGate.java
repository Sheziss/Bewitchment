package com.bewitchment.common.block.util;

import com.bewitchment.Bewitchment;
import com.bewitchment.registry.util.IOreDictionaryContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class ModBlockFenceGate extends BlockFenceGate implements IOreDictionaryContainer {
	private final List<String> oreDictionaryNames = new ArrayList<String>();

	public ModBlockFenceGate(String name, Block base, String... oreDictionaryNames) {
		super(EnumType.OAK);
		Bewitchment.proxy.registerValues(this, name, base, oreDictionaryNames);
	}

	@Override
	public List<String> getOreDictionaryNames() {
		return oreDictionaryNames;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return getDefaultState().getMaterial() == Material.ICE || getDefaultState().getMaterial() == Material.GLASS ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.CUTOUT;
	}
}