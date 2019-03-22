package com.bewitchment.common.ritual;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Ritual;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.block.tile.entity.TileEntityGlyph;

import net.minecraft.entity.player.EntityPlayer;

public class RitualHighMoon extends Ritual
{
	public RitualHighMoon()
	{
		super(Bewitchment.MOD_ID, "high_moon", ofi(), ofe(), ofs(), 100, 100, 0, GlyphType.NORMAL, null, null);
	}
	
	@Override
	public boolean isValid(TileEntityGlyph tile, EntityPlayer caster)
	{
		return tile.getWorld().isDaytime();
	}
	
	@Override
	public void onFinished(TileEntityGlyph tile, EntityPlayer caster)
	{
		if (!tile.getWorld().isRemote) tile.getWorld().setWorldTime(tile.getWorld().getWorldTime() + (41600 - (tile.getWorld().getWorldTime() % 24000)) % 24000);
	}
}