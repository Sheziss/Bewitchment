package com.bewitchment.common.ritual;

import com.bewitchment.Bewitchment;
import com.bewitchment.Util;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;

public class RitualConjureDemon extends Ritual {
	public RitualConjureDemon() {
		super(Bewitchment.MODID, "conjure_demon", Arrays.asList(Ingredient.fromStacks(new ItemStack(ModObjects.athame, 1, Short.MAX_VALUE)), Ingredient.fromStacks(new ItemStack(ModObjects.heart)), Ingredient.fromStacks(new ItemStack(ModObjects.hellebore)), Ingredient.fromStacks(new ItemStack(ModObjects.hellhound_horn)), Ingredient.fromStacks(new ItemStack(ModObjects.liquid_wroth)), Ingredient.fromStacks(Util.getOres("ingotGold")), Ingredient.fromStacks(new ItemStack(Items.ENDER_PEARL)), Ingredient.fromStacks(new ItemStack(Items.GHAST_TEAR))), Arrays.asList(EntityRegistry.getEntry(EntityVillager.class)), Arrays.asList(), 616, 4750, 10, GlyphType.NETHER, GlyphType.NETHER, GlyphType.NETHER);
	}

	@Override
	public void onFinished(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time) {
		if (!world.isRemote) {
			for (int i = 0; i < world.rand.nextInt(3) + 1; i++) {
				EntityDemon entity = world.rand.nextBoolean() ? new EntityDemon(world) : new EntityDemoness(world);
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
			world.spawnParticle(EnumParticleTypes.FLAME, sx, sy, sz, 0.6 * (sx - cx), 0.6 * (sy - cy), 0.6 * (sz - cz));
		}
	}
}