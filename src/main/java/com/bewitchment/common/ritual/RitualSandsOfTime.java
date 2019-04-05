package com.bewitchment.common.ritual;

import java.util.Arrays;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Ritual;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.block.tile.entity.TileEntityGlyph;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.oredict.OreDictionary;

public class RitualSandsOfTime extends Ritual
{
	public RitualSandsOfTime()
	{
		super(Bewitchment.MOD_ID, "sands_of_time",
				Arrays.asList(
						Ingredient.fromStacks(Bewitchment.proxy.toArray(OreDictionary.getOres("sand"))),
						Ingredient.fromStacks(Bewitchment.proxy.toArray(OreDictionary.getOres("oreDiamond")))),
				Arrays.asList(),
				Arrays.asList(),
				-1, 1000, 5, GlyphType.NORMAL, GlyphType.NORMAL, GlyphType.NORMAL);
	}
	
	@Override
	public boolean canBePerformedRemotely()
	{
		return false;
	}
	
	@Override
	public void onUpdate(TileEntityGlyph tile, EntityPlayer caster)
	{
		if (!tile.getWorld().isRemote) tile.getWorld().setWorldTime(tile.getWorld().getWorldTime() + 5);
	}
}