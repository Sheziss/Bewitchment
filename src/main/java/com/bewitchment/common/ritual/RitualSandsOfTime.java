package com.bewitchment.common.ritual;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Ritual;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.block.tile.entity.TileEntityGlyph;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Arrays;

public class RitualSandsOfTime extends Ritual {
	public RitualSandsOfTime() {
		super(Bewitchment.MODID, "sands_of_time",
				Arrays.asList(
						Ingredient.fromStacks(Bewitchment.proxy.getOres("sand")),
						Ingredient.fromStacks(Bewitchment.proxy.getOres("oreDiamond"))),
				Arrays.asList(),
				Arrays.asList(),
				-1, 1000, 5, GlyphType.NORMAL, GlyphType.NORMAL, GlyphType.NORMAL);
	}

	@Override
	public boolean canBePerformedRemotely() {
		return false;
	}

	@Override
	public void onUpdate(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time) {
		if (!world.isRemote) world.setWorldTime(world.getWorldTime() + 5);
	}

	@Override
	public void onStopped(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time) {
	}
}