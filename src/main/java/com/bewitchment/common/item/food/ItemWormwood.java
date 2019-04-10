package com.bewitchment.common.item.food;

import com.bewitchment.common.item.util.ModItemFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemWormwood extends ModItemFood {
	public ItemWormwood() {
		super("wormwood", 1, 2, false, "cropWormwood", "listAllspice");
		setAlwaysEdible();
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);
		player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 35));
	}
}