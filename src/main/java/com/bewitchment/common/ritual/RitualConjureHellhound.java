package com.bewitchment.common.ritual;

import java.util.Arrays;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Ritual;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.block.tile.entity.TileEntityGlyph;
import com.bewitchment.common.entity.spirits.demons.EntityHellhound;
import com.bewitchment.registry.ModObjects;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RitualConjureHellhound extends Ritual
{
	public RitualConjureHellhound()
	{
		super(Bewitchment.MOD_ID, "conjure_hellhound",
				Arrays.asList(
						Ingredient.fromStacks(new ItemStack(ModObjects.athame, 1, Short.MAX_VALUE)),
						Ingredient.fromStacks(Bewitchment.proxy.getOres("obsidian")),
						Ingredient.fromStacks(new ItemStack(Blocks.SOUL_SAND)),
						Ingredient.fromStacks(new ItemStack(ModObjects.tongue_of_dog)),
						Ingredient.fromStacks(new ItemStack(ModObjects.hellebore)),
						Ingredient.fromStacks(new ItemStack(ModObjects.snake_venom)),
						Ingredient.fromStacks(new ItemStack(Items.BLAZE_POWDER))),
				Arrays.asList(),
				Arrays.asList(),
				400, 3850, 3, GlyphType.NETHER, GlyphType.NETHER, null);
	}
	
	@Override
	public void onFinished(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time)
	{
		if (!world.isRemote)
		{
			EntityHellhound entity = new EntityHellhound(world);
			entity.onInitialSpawn(world.getDifficultyForLocation(pos), null);
			entity.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), world.rand.nextInt(360), 0);
			for (EntityPlayerMP player : world.getEntitiesWithinAABB(EntityPlayerMP.class, entity.getEntityBoundingBox().grow(50))) CriteriaTriggers.SUMMONED_ENTITY.trigger(player, entity);
			world.spawnEntity(entity);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onRandomDisplayTick(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time)
	{
		for (int i = 0; i < 20; i++)
		{
			double cx = pos.getX() + 0.5, cy = pos.getY() + 0.5, cz = pos.getZ() + 0.5;
			double sx = cx + world.rand.nextGaussian() * 0.5, sy = cy + world.rand.nextGaussian() * 0.5, sz = cz + world.rand.nextGaussian() * 0.5;
			world.spawnParticle(EnumParticleTypes.FLAME, sx, sy, sz, 0.6 * (sx - cx), 0.6 * (sy - cy), 0.6 * (sz - cz));
		}
	}
}