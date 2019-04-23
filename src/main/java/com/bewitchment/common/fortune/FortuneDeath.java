package com.bewitchment.common.fortune;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Fortune;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class FortuneDeath extends Fortune {
	public FortuneDeath() {
		super(Bewitchment.MODID, "death", 1);
	}

	@Override
	public boolean apply(EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(MobEffects.WITHER, 215, 1 + player.world.getDifficulty().ordinal(), false, false));
		return true;
	}

	@Override
	public boolean canBeUsed(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean isInstant(EntityPlayer player) {
		return player.getRNG().nextDouble() < 0.0005;
	}

	@Override
	public boolean isNegative() {
		return true;
	}
}