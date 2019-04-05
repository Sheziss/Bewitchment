package com.bewitchment.common.ritual;

import java.util.Arrays;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Ritual;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.block.tile.entity.TileEntityGlyph;
import com.bewitchment.registry.ModObjects;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class RitualConjureGhast extends Ritual
{
	public RitualConjureGhast()
	{
		super(Bewitchment.MOD_ID, "conjure_ghast",
				Arrays.asList(
						Ingredient.fromStacks(new ItemStack(ModObjects.athame, 1, Short.MAX_VALUE)),
						Ingredient.fromStacks(Bewitchment.proxy.toArray(OreDictionary.getOres("dustGlowstone"))),
						Ingredient.fromStacks(new ItemStack(Blocks.SOUL_SAND)),
						Ingredient.fromStacks(new ItemStack(ModObjects.ebb_of_death)),
						Ingredient.fromStacks(new ItemStack(ModObjects.hellebore)),
						Ingredient.fromStacks(new ItemStack(ModObjects.wormwood)),
						Ingredient.fromStacks(new ItemStack(ModObjects.ectoplasm))),
				Arrays.asList(),
				Arrays.asList(),
				200, 500, 3, GlyphType.NETHER, GlyphType.NETHER, GlyphType.NETHER);
	}
	
	@Override
	public void onFinished(TileEntityGlyph tile, EntityPlayer caster)
	{
		if (!tile.getWorld().isRemote)
		{
			EntityGhast entity = new EntityGhast(tile.getWorld());
			entity.setLocationAndAngles(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ(), tile.getWorld().rand.nextInt(360), 0);
			for (EntityPlayerMP player : tile.getWorld().getEntitiesWithinAABB(EntityPlayerMP.class, entity.getEntityBoundingBox().grow(50))) CriteriaTriggers.SUMMONED_ENTITY.trigger(player, entity);
			tile.getWorld().spawnEntity(entity);
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
			tile.getWorld().spawnParticle(EnumParticleTypes.FLAME, sx, sy, sz, 0.6 * (sx - cx), 0.6 * (sy - cy), 0.6 * (sz - cz));
		}
	}
}