package com.bewitchment.common.item.food;

import com.bewitchment.common.item.util.ModItemFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemLavender extends ModItemFood {
	public ItemLavender() {
		super("lavender", 1, 2, false, "cropLavender", "listAllherb");
		setAlwaysEdible();
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);
		player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 10));
	}
}