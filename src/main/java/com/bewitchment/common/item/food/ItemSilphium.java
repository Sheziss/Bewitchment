package com.bewitchment.common.item.food;

import com.bewitchment.common.item.util.ModItemFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemSilphium extends ModItemFood {
	public ItemSilphium() {
		super("silphium", 1, 2, false, "cropSilphium", "listAllherb", "listAllspice", "listAllgreenveggie");
		setAlwaysEdible();
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);
		player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 15));
	}
}