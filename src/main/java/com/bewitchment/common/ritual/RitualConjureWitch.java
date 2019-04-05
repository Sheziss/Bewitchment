package com.bewitchment.common.ritual;

import java.util.Arrays;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Ritual;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.block.tile.entity.TileEntityGlyph;
import com.bewitchment.registry.ModObjects;

import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RitualConjureWitch extends Ritual
{
	public RitualConjureWitch()
	{
		super(Bewitchment.MOD_ID, "conjure_witch",
				Arrays.asList(
						Ingredient.fromStacks(new ItemStack(ModObjects.athame, 1, Short.MAX_VALUE)),
						Ingredient.fromStacks(new ItemStack(Items.APPLE)),
						Ingredient.fromStacks(new ItemStack(ModObjects.pentacle)),
						Ingredient.fromStacks(new ItemStack(Items.POISONOUS_POTATO))),
				Arrays.asList(),
				Arrays.asList(),
				200, 500, 3, GlyphType.NETHER, GlyphType.ENDER, GlyphType.NETHER);
	}
	
	@Override
	public void onFinished(TileEntityGlyph tile, EntityPlayer caster)
	{
		if (!tile.getWorld().isRemote)
		{
			EntityWitch entity = new EntityWitch(tile.getWorld());
			entity.setLocationAndAngles(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ(), tile.getWorld().rand.nextInt(360), 0);
			entity.onInitialSpawn(tile.getWorld().getDifficultyForLocation(tile.getPos()), null);
			tile.getWorld().spawnEntity(entity);
			if (tile.getWorld().rand.nextFloat() < 0.1f) entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 6000, 2, false, false));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onRandomDisplayTick(TileEntityGlyph tile)
	{
		for (int i = 0; i < 20; i++)
		{
			double cx = tile.getPos().getX() + 0.5, cy = tile.getPos().getY() + 0.5, cz = tile.getPos().getZ() + 0.5;
			double sx = cx + tile.getWorld().rand.nextGaussian() * 0.5, sy = cy + tile.getWorld().rand.nextGaussian() * 0.5, sz = cz + tile.getWorld().rand.nextGaussian() * 0.5;
			tile.getWorld().spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, sx, sy, sz, 0.6 * (sx - cx), 0.6 * (sy - cy), 0.6 * (sz - cz));
		}
	}
}