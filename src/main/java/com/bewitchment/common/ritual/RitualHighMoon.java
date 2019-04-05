package com.bewitchment.common.ritual;

import java.util.Arrays;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Ritual;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.block.tile.entity.TileEntityGlyph;
import com.bewitchment.registry.ModObjects;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.oredict.OreDictionary;

public class RitualHighMoon extends Ritual
{
	public RitualHighMoon()
	{
		super(Bewitchment.MOD_ID, "high_moon",
				Arrays.asList(
						Ingredient.fromStacks(Bewitchment.proxy.toArray(OreDictionary.getOres("ingotSilver"))),
						Ingredient.fromStacks(new ItemStack(Items.NETHERBRICK)),
						Ingredient.fromStacks(new ItemStack(ModObjects.hellebore))),
				Arrays.asList(),
				Arrays.asList(),
				100, 100, 0, GlyphType.NORMAL, null, null);
	}
	
	@Override
	public boolean isValid(TileEntityGlyph tile, EntityPlayer caster)
	{
		return tile.getWorld().isDaytime();
	}
	
	@Override
	public void onFinished(TileEntityGlyph tile, EntityPlayer caster)
	{
		if (!tile.getWorld().isRemote) tile.getWorld().setWorldTime(tile.getWorld().getWorldTime() + (41600 - (tile.getWorld().getWorldTime() % 24000)) % 24000);
	}
}