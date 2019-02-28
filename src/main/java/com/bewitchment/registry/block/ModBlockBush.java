package com.bewitchment.registry.block;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.core.Bewitchment;
import com.bewitchment.registry.IOreName;
import com.bewitchment.registry.ModBlocks;

import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.util.ResourceLocation;

public class ModBlockBush extends BlockBush implements IOreName
{
	private final List<String> oreNames = new ArrayList<String>();
	
	public ModBlockBush(String name, String... oreNames)
	{
		super();
		this.setRegistryName(new ResourceLocation(Bewitchment.MOD_ID, name));
		this.setTranslationKey(this.getRegistryName().toString());
		this.setCreativeTab(Bewitchment.proxy.tab);
		this.setSoundType(SoundType.PLANT);
		this.setHardness(0);
		for (String ore : oreNames) this.oreNames.add(ore);
		ModBlocks.REGISTRY.add(this);
	}
	
	@Override
	public List<String> getOreNames()
	{
		return oreNames;
	}
}