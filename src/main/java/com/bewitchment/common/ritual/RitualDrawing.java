package com.bewitchment.common.ritual;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Ritual;
import com.bewitchment.common.block.BlockGlyph;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.block.tile.entity.TileEntityGlyph;
import com.bewitchment.registry.ModObjects;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;

public class RitualDrawing extends Ritual
{
	private final int[][] circle;
	private GlyphType type;
	
	public RitualDrawing(String name, int time, int starting_power, int running_power, GlyphType small, GlyphType medium, GlyphType big, int[][] circle)
	{
		super(Bewitchment.MOD_ID, name, ofi(), ofe(), ofs(), time, starting_power, running_power, small, medium, big);
		this.circle = circle;
	}
	
	@Override
	public boolean isValid(TileEntityGlyph tile, EntityPlayer caster)
	{
		for (int x = 0; x < circle.length; x++)
		{
			for (int z = 0; z < circle.length; z++)
			{
				BlockPos pos = tile.getPos().add(x - circle.length / 2, 0, z - circle.length / 2);
				if (!tile.getWorld().isAirBlock(pos) && !tile.getWorld().getBlockState(pos).getBlock().isReplaceable(tile.getWorld(), pos) && tile.getWorld().getBlockState(pos).getBlock() != ModObjects.glyph) return false;
			}
		}
		Item item = caster.getHeldItemOffhand().getItem();
		return (item == ModObjects.chalk_normal || item == ModObjects.chalk_nether || item == ModObjects.chalk_ender) && (caster.isCreative() || caster.getHeldItemOffhand().getItemDamage() >= circle.length);
	}
	
	@Override
	public void onFinished(TileEntityGlyph tile, EntityPlayer caster)
	{
		for (int x = 0; x < circle.length; x++)
		{
			for (int z = 0; z < circle.length; z++)
			{
				if (circle[x][z] == 1) tile.getWorld().setBlockState(tile.getPos().add(x - circle.length / 2, 0, z - circle.length / 2), ModObjects.glyph.getDefaultState().withProperty(BlockGlyph.TYPE, type));
			}
		}
	}
	
	@Override
	public void onStarted(TileEntityGlyph tile, EntityPlayer caster)
	{
		Item item = caster.getHeldItemOffhand().getItem();
		GlyphType type = item == ModObjects.chalk_normal ? GlyphType.NORMAL : item == ModObjects.chalk_nether ? GlyphType.NETHER : GlyphType.ENDER;
		this.type = type;
		if (!caster.isCreative()) caster.getHeldItemOffhand().damageItem(circle.length, caster);
	}
}