package com.bewitchment.common.ritual;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Ritual;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.block.tile.entity.TileEntityGlyph;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;

public class RitualPerception extends Ritual
{
	public RitualPerception()
	{
		super(Bewitchment.MOD_ID, "perception", ofi(), ofe(), ofs(), -1, 700, 3, GlyphType.NORMAL, GlyphType.NORMAL, GlyphType.NORMAL);
	}
	
	@Override
	public void onUpdate(TileEntityGlyph tile, EntityPlayer caster)
	{
		if (!tile.getWorld().isRemote && tile.getWorld().getWorldTime() % 100 == 0)
		{
			for (EntityLivingBase entity : tile.getWorld().getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(tile.getPos()).grow(20)))
			{
				entity.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 110, 0, false, false));
			}
		}
	}
}