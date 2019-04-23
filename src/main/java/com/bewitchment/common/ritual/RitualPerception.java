package com.bewitchment.common.ritual;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Ritual;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.block.tile.entity.TileEntityGlyph;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Arrays;

public class RitualPerception extends Ritual {
	public RitualPerception() {
		super(Bewitchment.MODID, "perception",
				Arrays.asList(
						Ingredient.fromStacks(Bewitchment.proxy.getOres("glowstone")),
						Ingredient.fromStacks(new ItemStack(Items.GOLDEN_CARROT))),
				Arrays.asList(),
				Arrays.asList(),
				-1, 700, 3, GlyphType.NORMAL, GlyphType.NORMAL, GlyphType.NORMAL);
	}

	@Override
	public void onUpdate(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time) {
		if (!world.isRemote && world.getWorldTime() % 100 == 0) {
			for (EntityLivingBase entity : world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos).grow(20))) {
				entity.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 110, 0, false, false));
			}
		}
	}

	@Override
	public void onStopped(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time) {
	}
}