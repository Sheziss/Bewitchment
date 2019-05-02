package com.bewitchment.common.ritual;

import com.bewitchment.Bewitchment;
import com.bewitchment.Util;
import com.bewitchment.api.registry.Ritual;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.block.tile.entity.TileEntityGlyph;
import com.bewitchment.common.entity.living.*;
import com.bewitchment.registry.ModObjects;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;

public class RitualCallOfTheWild extends Ritual {
	public RitualCallOfTheWild() {
		super(Bewitchment.MODID, "call_of_the_wild", Arrays.asList(Ingredient.fromStacks(new ItemStack(ModObjects.oak_spirit)), Ingredient.fromStacks(new ItemStack(ModObjects.spruce_heart)), Ingredient.fromStacks(new ItemStack(ModObjects.birch_soul)), Ingredient.fromStacks(new ItemStack(ModObjects.chrysanthemum)), Ingredient.fromStacks(new ItemStack(ModObjects.moonbell)), Ingredient.fromStacks(Util.getOres("treeLeaves"))), Arrays.asList(), Arrays.asList(), 135, 1050, 3, GlyphType.ANY, GlyphType.ANY, GlyphType.ANY);
	}

	@Override
	public void onFinished(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time) {
		if (!world.isRemote) {
			for (int i = 0; i < world.rand.nextInt(3) + 1; i++) {
				EntityLiving entity = null;
				int rand = world.rand.nextInt(7);
				if (rand == 0) entity = new EntityBlindworm(world);
				else if (rand == 1) entity = new EntityLizard(world);
				else if (rand == 2) entity = new EntityNewt(world);
				else if (rand == 3) entity = new EntityOwl(world);
				else if (rand == 4) entity = new EntityRaven(world);
				else if (rand == 5) entity = new EntitySnake(world);
				else entity = new EntityToad(world);
				entity.onInitialSpawn(world.getDifficultyForLocation(pos), null);
				entity.setLocationAndAngles(pos.getX() + world.rand.nextInt(11) - 1, pos.getY(), pos.getZ() + world.rand.nextInt(11) - 1, world.rand.nextInt(360), 0);
				for (EntityPlayerMP player : world.getEntitiesWithinAABB(EntityPlayerMP.class, entity.getEntityBoundingBox().grow(50)))
					CriteriaTriggers.SUMMONED_ENTITY.trigger(player, entity);
				world.spawnEntity(entity);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onRandomDisplayTick(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time) {
		for (int i = 0; i < 20; i++) {
			double cx = pos.getX() + 0.5, cy = pos.getY() + 0.5, cz = pos.getZ() + 0.5;
			double sx = cx + world.rand.nextGaussian() * 0.5, sy = cy + world.rand.nextGaussian() * 0.5, sz = cz + world.rand.nextGaussian() * 0.5;
			world.spawnParticle(EnumParticleTypes.CLOUD, sx, sy, sz, 0.6 * (sx - cx), 0.6 * (sy - cy), 0.6 * (sz - cz));
		}
	}
}