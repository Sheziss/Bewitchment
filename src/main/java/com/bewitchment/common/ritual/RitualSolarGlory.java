package com.bewitchment.common.ritual;

import java.util.Arrays;

import com.bewitchment.Bewitchment;
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
import net.minecraftforge.oredict.OreDictionary;

public class RitualSolarGlory extends Ritual
{
	public RitualSolarGlory()
	{
		super(Bewitchment.MOD_ID, "solar_glory",
				Arrays.asList(
						Ingredient.fromStacks(Bewitchment.proxy.toArray(OreDictionary.getOres("ingotGold"))),
						Ingredient.fromStacks(new ItemStack(Items.NETHERBRICK)),
						Ingredient.fromStacks(new ItemStack(ModObjects.chrysanthemum))),
				Arrays.asList(),
				Arrays.asList(),
				100, 800, 0, GlyphType.NORMAL, null, null);
	}
	
	@Override
	public boolean isValid(TileEntityGlyph tile, EntityPlayer caster)
	{
		return !tile.getWorld().isDaytime() && caster.getCapability(ExtendedPlayer.CAPABILITY, null).getTransformation() != TransformationType.VAMPIRE;
	}
	
	@Override
	public void onFinished(TileEntityGlyph tile, EntityPlayer caster)
	{
		if (!tile.getWorld().isRemote)
		{
			for (EntityPlayer player0 : tile.getWorld().playerEntities)
			{
				if (player0.getCapability(ExtendedPlayer.CAPABILITY, null).getTransformation() == TransformationType.VAMPIRE) player0.addPotionEffect(new PotionEffect(ModPotions.sun_ward, 30 * 20));
			}
			tile.getWorld().setWorldTime(tile.getWorld().getWorldTime() + (30000 - (tile.getWorld().getWorldTime() % 24000)) % 24000);
		}
	}
}