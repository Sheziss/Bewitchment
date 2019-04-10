package com.bewitchment.common.ritual;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Ritual;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.block.tile.entity.TileEntityGlyph;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.List;

public class RitualHungryFlames extends Ritual {
	public RitualHungryFlames() {
		super(Bewitchment.MOD_ID, "hungry_flames",
				Arrays.asList(
						Ingredient.fromStacks(new ItemStack(Items.BLAZE_ROD)),
						Ingredient.fromStacks(new ItemStack(Items.COAL, 1, Short.MAX_VALUE))),
				Arrays.asList(),
				Arrays.asList(),
				3600, 300, 4, GlyphType.NETHER, null, null);
	}

	@Override
	public void onUpdate(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time) {
		if (!world.isRemote && world.getTotalWorldTime() % 40 == 0) {
			List<EntityItem> smeltables = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos).grow(3), entity -> !FurnaceRecipes.instance().getSmeltingResult(entity.getItem()).isEmpty());
			for (EntityItem entity : smeltables) {
				ItemStack stack = entity.getItem().copy();
				if (!stack.getItem().hasContainerItem(stack)) {
					if (world.rand.nextDouble() < 0.7)
						InventoryHelper.spawnItemStack(world, entity.posX, entity.posY, entity.posZ, FurnaceRecipes.instance().getSmeltingResult(stack.splitStack(1)).copy());
					else world.spawnEntity(new EntityXPOrb(world, entity.posX, entity.posY, entity.posZ, 2));
				}
				entity.setItem(stack);
			}
			if (world.rand.nextDouble() < 0.1 && world.getGameRules().getBoolean("doFireTick")) {
				int y = pos.getY() - 5 + world.rand.nextInt(12);
				if (y < 1) y = 1;
				if (y > world.getActualHeight()) y = world.getActualHeight() - 2;
				BlockPos pos0 = new BlockPos(pos.getX() - 5 + world.rand.nextInt(12), y, pos.getZ() - 5 + world.rand.nextInt(12));
				for (EnumFacing face : EnumFacing.VALUES) {
					if (world.getBlockState(pos0).getBlock().getFlammability(world, pos0, face) > 0 && !world.isAirBlock(pos0.offset(face)) && world.getBlockState(pos0.offset(face)).getBlock() != Blocks.FIRE)
						world.setBlockState(pos0, Blocks.FIRE.getDefaultState());
				}
			}
		}
	}

	@Override
	public void onStopped(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time) {
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onRandomDisplayTick(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time) {
		world.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5 + world.rand.nextGaussian() * 2, pos.getY() + world.rand.nextFloat() * 0.5, pos.getZ() + 0.5 + world.rand.nextGaussian() * 2, 0.05 * world.rand.nextFloat(), 0.1 * world.rand.nextFloat(), 0.05 * world.rand.nextFloat());
	}
}