package com.bewitchment.common.ritual;

import java.util.Arrays;

import com.bewitchment.Bewitchment;
import com.bewitchment.Util;
import com.bewitchment.api.capability.extendedplayer.ExtendedPlayer;
import com.bewitchment.api.capability.extendedplayer.ExtendedPlayer.TransformationType;
import com.bewitchment.api.registry.Ritual;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.block.tile.entity.TileEntityGlyph;
import com.bewitchment.registry.ModObjects;
import com.bewitchment.registry.ModPotions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualSolarGlory extends Ritual {
	public RitualSolarGlory() {
		super(Bewitchment.MODID, "solar_glory",
				Arrays.asList(
						Ingredient.fromStacks(Util.getOres("ingotGold")),
						Ingredient.fromStacks(new ItemStack(Items.NETHERBRICK)),
						Ingredient.fromStacks(new ItemStack(ModObjects.chrysanthemum))),
				Arrays.asList(),
				Arrays.asList(),
				100, 800, 0, GlyphType.NORMAL, null, null);
	}

	@Override
	public boolean isValid(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time) {
		return !world.isDaytime() && caster.getCapability(ExtendedPlayer.CAPABILITY, null).getTransformation() != TransformationType.VAMPIRE;
	}

	@Override
	public void onFinished(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time) {
		if (!world.isRemote) {
			for (EntityPlayer player : world.playerEntities) {
				if (player.getCapability(ExtendedPlayer.CAPABILITY, null).getTransformation() == TransformationType.VAMPIRE)
					player.addPotionEffect(new PotionEffect(ModPotions.sun_ward, 30 * 20));
			}
			world.setWorldTime(world.getWorldTime() + (30000 - (world.getWorldTime() % 24000)) % 24000);
		}
	}
}