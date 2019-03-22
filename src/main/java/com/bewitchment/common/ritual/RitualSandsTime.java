package com.bewitchment.common.ritual;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Ritual;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.block.tile.entity.TileEntityGlyph;

import net.minecraft.entity.player.EntityPlayer;

public class RitualSandsTime extends Ritual
{
	public RitualSandsTime()
	{
		super(Bewitchment.MOD_ID, "sands_of_time", ofi(), ofe(), ofs(), -1, 1000, 5, GlyphType.NORMAL, GlyphType.NORMAL, GlyphType.NORMAL);
	}
	
	@Override
	public boolean canBePerformedRemotely()
	{
		return false;
	}
	
	@Override
	public void onUpdate(TileEntityGlyph tile, EntityPlayer caster)
	{
		if (!tile.getWorld().isRemote) tile.getWorld().setWorldTime(tile.getWorld().getWorldTime() + 5);
	}
}