package com.bewitchment.common.fortune;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Fortune;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class FortuneIllness extends Fortune
{
	public FortuneIllness()
	{
		super(Bewitchment.MOD_ID, "illness", 1);
	}
	
	@Override
	public boolean apply(EntityPlayer player)
	{
		player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 215, 1 + player.world.getDifficulty().ordinal(), false, false));
		player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 215, 1 + player.world.getDifficulty().ordinal(), false, false));
		player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 215, 1 + player.world.getDifficulty().ordinal(), false, false));
		return true;
	}
	
	@Override
	public boolean canBeUsed(EntityPlayer player)
	{
		return true;
	}
	
	@Override
	public boolean isInstant(EntityPlayer player)
	{
		return player.getRNG().nextDouble() < 0.0005;
	}
	
	@Override
	public boolean isNegative()
	{
		return true;
	}
}