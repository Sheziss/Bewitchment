package com.bewitchment.common.ritual;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Ritual;
import com.bewitchment.common.block.BlockGlyph;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.block.tile.entity.TileEntityGlyph;
import com.bewitchment.registry.ModObjects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class RitualDrawing extends Ritual {
	private final int[][] circle;
	private GlyphType type;

	public RitualDrawing(String name, List<Ingredient> inputItems, int time, int starting_power, int running_power, GlyphType small, GlyphType medium, GlyphType big, int[][] circle) {
		super(Bewitchment.MODID, name, inputItems, Arrays.asList(), Arrays.asList(), time, starting_power, running_power, small, medium, big);
		this.circle = circle;
	}

	@Override
	public boolean isValid(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time) {
		for (int x = 0; x < circle.length; x++) {
			for (int z = 0; z < circle.length; z++) {
				BlockPos pos0 = pos.add(x - circle.length / 2, 0, z - circle.length / 2);
				if (circle[x][z] == 1 && !world.isAirBlock(pos0) && !world.getBlockState(pos0).getBlock().isReplaceable(world, pos0) && world.getBlockState(pos0).getBlock() != ModObjects.glyph)
					return false;
			}
		}
		Item item = caster.getHeldItemOffhand().getItem();
		return (item == ModObjects.chalk_normal || item == ModObjects.chalk_nether || item == ModObjects.chalk_ender) && (caster.isCreative() || caster.getHeldItemOffhand().getItemDamage() >= circle.length);
	}

	@Override
	public void onFinished(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time) {
		for (int x = 0; x < circle.length; x++) {
			for (int z = 0; z < circle.length; z++) {
				if (circle[x][z] == 1)
					world.setBlockState(pos.add(x - circle.length / 2, 0, z - circle.length / 2), ModObjects.glyph.getDefaultState().withProperty(BlockGlyph.TYPE, type));
			}
		}
	}

	@Override
	public void onStarted(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time) {
		Item item = caster.getHeldItemOffhand().getItem();
		GlyphType type = item == ModObjects.chalk_normal ? GlyphType.NORMAL : item == ModObjects.chalk_nether ? GlyphType.NETHER : GlyphType.ENDER;
		this.type = type;
		if (!caster.isCreative()) caster.getHeldItemOffhand().damageItem(circle.length, caster);
	}
}