package com.bewitchment.common.ritual;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Ritual;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.block.tile.entity.TileEntityGlyph;
import com.bewitchment.registry.ModObjects;

import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RitualConjureWitch extends Ritual
{
	public RitualConjureWitch()
	{
		super(Bewitchment.MOD_ID, "conjure_witch", ofi(), ofe(), ofs(), 200, 500, 3, GlyphType.NETHER, GlyphType.ENDER, GlyphType.NETHER);
	}
	
	@Override
	public ItemStack[] getOutput(TileEntityGlyph tile)
	{
		List<ItemStack> out = new ArrayList<>();
		for (ItemStack stack : super.getOutput(tile)) out.add(stack);
		for (int i = 0; i < tile.inventory.getSlots(); i++)
		{
			ItemStack stack = tile.inventory.getStackInSlot(i);
			if (stack.getItem() == ModObjects.athame)
			{
				stack.setItemDamage(stack.getItemDamage() + 50);
				if (stack.getItemDamage() >= stack.getMaxDamage()) stack.shrink(1);
				if (!stack.isEmpty()) out.add(stack);
			}
		}
		return out.toArray(new ItemStack[out.size()]);
	}
	
	@Override
	public void onFinished(TileEntityGlyph tile, EntityPlayer caster)
	{
		if (!tile.getWorld().isRemote)
		{
			EntityWitch entity = new EntityWitch(tile.getWorld());
			entity.setLocationAndAngles(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ(), tile.getWorld().rand.nextInt(360), 0);
			entity.onInitialSpawn(tile.getWorld().getDifficultyForLocation(tile.getPos()), null);
			tile.getWorld().spawnEntity(entity);
			if (tile.getWorld().rand.nextFloat() < 0.1f) entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 6000, 2, false, false));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onRandomDisplayTick(TileEntityGlyph tile)
	{
		for (int i = 0; i < 20; i++)
		{
			double cx = tile.getPos().getX() + 0.5, cy = tile.getPos().getY() + 0.5, cz = tile.getPos().getZ() + 0.5;
			double sx = cx + tile.getWorld().rand.nextGaussian() * 0.5, sy = cy + tile.getWorld().rand.nextGaussian() * 0.5, sz = cz + tile.getWorld().rand.nextGaussian() * 0.5;
			tile.getWorld().spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, sx, sy, sz, 0.6 * (sx - cx), 0.6 * (sy - cy), 0.6 * (sz - cz));
		}
	}
}