package com.bewitchment.common.item.food;

import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.common.item.util.ModItemFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemMandrakeRoot extends ModItemFood {
	public ItemMandrakeRoot() {
		super("mandrake_root", 1, 2, false, "cropMandrake");
		setAlwaysEdible();
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);
		player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 35, 1));
		player.getCapability(MagicPower.CAPABILITY, null).fill(25);
	}
}