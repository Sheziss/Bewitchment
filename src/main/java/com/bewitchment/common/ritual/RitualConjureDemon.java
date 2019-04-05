package com.bewitchment.common.ritual;

import java.util.Arrays;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Ritual;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.block.tile.entity.TileEntityGlyph;
import com.bewitchment.common.entity.spirits.demons.EntityDemon;
import com.bewitchment.common.entity.spirits.demons.EntityDemoness;
import com.bewitchment.registry.ModObjects;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class RitualConjureDemon extends Ritual
{
	public RitualConjureDemon()
	{
		super(Bewitchment.MOD_ID, "conjure_demon",
				Arrays.asList(
						Ingredient.fromStacks(new ItemStack(ModObjects.athame, 1, Short.MAX_VALUE)),
						Ingredient.fromStacks(new ItemStack(ModObjects.heart)),
						Ingredient.fromStacks(new ItemStack(ModObjects.hellebore)),
						Ingredient.fromStacks(new ItemStack(ModObjects.hellhound_horn)),
						Ingredient.fromStacks(new ItemStack(ModObjects.liquid_wroth)),
						Ingredient.fromStacks(Bewitchment.proxy.toArray(OreDictionary.getOres("ingotGold"))),
						Ingredient.fromStacks(new ItemStack(Items.ENDER_PEARL)),
						Ingredient.fromStacks(new ItemStack(Items.GHAST_TEAR))),
				Arrays.asList(EntityRegistry.getEntry(EntityVillager.class)),
				Arrays.asList(),
				616, 4750, 10, GlyphType.NETHER, GlyphType.NETHER, GlyphType.NETHER);
	}
	
	@Override
	public void onFinished(TileEntityGlyph tile, EntityPlayer caster)
	{
		if (!tile.getWorld().isRemote)
		{
			for (int i = 0; i < tile.getWorld().rand.nextInt(3) + 1; i++)
			{
				EntityDemon entity = tile.getWorld().rand.nextBoolean() ? new EntityDemon(tile.getWorld()) : new EntityDemoness(tile.getWorld());
				entity.onInitialSpawn(tile.getWorld().getDifficultyForLocation(tile.getPos()), null);
				entity.setLocationAndAngles(tile.getPos().getX() + tile.getWorld().rand.nextInt(11) - 1, tile.getPos().getY(), tile.getPos().getZ() + tile.getWorld().rand.nextInt(11) - 1, tile.getWorld().rand.nextInt(360), 0);
				for (EntityPlayerMP player : tile.getWorld().getEntitiesWithinAABB(EntityPlayerMP.class, entity.getEntityBoundingBox().grow(50))) CriteriaTriggers.SUMMONED_ENTITY.trigger(player, entity);
				tile.getWorld().spawnEntity(entity);
			}
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