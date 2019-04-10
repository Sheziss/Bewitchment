package com.bewitchment.common.enchantment;

import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.common.enchantment.util.ModEnchantment;
import net.minecraft.entity.player.EntityPlayer;

public class EnchantmentNaturalAttuning extends ModEnchantment {
	public EnchantmentNaturalAttuning() {
		super("natural_attuning", Rarity.RARE, 3);
	}

	public void onEquipped(EntityPlayer player) {
		player.getCapability(MagicPower.CAPABILITY, null).addBonus(getName(), (2 << getMaxLevelOnPlayer(player)) * 100);
	}

	public void onUnequipped(EntityPlayer player) {
		player.getCapability(MagicPower.CAPABILITY, null).removeBonus(getName());
	}
}