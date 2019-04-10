package com.bewitchment.common.ritual;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Ritual;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.block.tile.entity.TileEntityGlyph;
import com.bewitchment.registry.ModObjects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Arrays;

public class RitualHighMoon extends Ritual {
	public RitualHighMoon() {
		super(Bewitchment.MOD_ID, "high_moon",
				Arrays.asList(
						Ingredient.fromStacks(Bewitchment.proxy.getOres("ingotSilver")),
						Ingredient.fromStacks(new ItemStack(Items.NETHERBRICK)),
						Ingredient.fromStacks(new ItemStack(ModObjects.hellebore))),
				Arrays.asList(),
				Arrays.asList(),
				100, 800, 0, GlyphType.NORMAL, null, null);
	}

	@Override
	public boolean isValid(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time) {
		return world.isDaytime();
	}

	@Override
	public void onFinished(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time) {
		if (!world.isRemote)
			world.setWorldTime(world.getWorldTime() + (41600 - (world.getWorldTime() % 24000)) % 24000);
	}
}