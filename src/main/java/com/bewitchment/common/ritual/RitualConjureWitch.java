package com.bewitchment.common.ritual;

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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;

public class RitualConjureWitch extends Ritual {
	public RitualConjureWitch() {
		super(Bewitchment.MODID, "conjure_witch", Arrays.asList(Ingredient.fromStacks(new ItemStack(ModObjects.athame, 1, Short.MAX_VALUE)), Ingredient.fromStacks(new ItemStack(Items.APPLE)), Ingredient.fromStacks(new ItemStack(ModObjects.pentacle)), Ingredient.fromStacks(new ItemStack(Items.POISONOUS_POTATO))), Arrays.asList(), Arrays.asList(), 200, 3000, 3, GlyphType.NETHER, GlyphType.ENDER, GlyphType.NETHER);
	}

	@Override
	public void onFinished(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time) {
		if (!world.isRemote) {
			EntityWitch entity = new EntityWitch(world);
			entity.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), world.rand.nextInt(360), 0);
			entity.onInitialSpawn(world.getDifficultyForLocation(pos), null);
			world.spawnEntity(entity);
			if (world.rand.nextFloat() < 0.1f)
				entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 6000, 2, false, false));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onRandomDisplayTick(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time) {
		for (int i = 0; i < 20; i++) {
			double cx = pos.getX() + 0.5, cy = pos.getY() + 0.5, cz = pos.getZ() + 0.5;
			double sx = cx + world.rand.nextGaussian() * 0.5, sy = cy + world.rand.nextGaussian() * 0.5, sz = cz + world.rand.nextGaussian() * 0.5;
			world.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, sx, sy, sz, 0.6 * (sx - cx), 0.6 * (sy - cy), 0.6 * (sz - cz));
		}
	}
}