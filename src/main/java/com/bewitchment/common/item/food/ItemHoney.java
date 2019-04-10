package com.bewitchment.common.item.food;

import com.bewitchment.common.item.util.ModItemFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemHoney extends ModItemFood {
	public ItemHoney() {
		super("honey", 2, 4, false, "itemHoney", "honeyDrop", "dropHoney", "foodHoneydrop", "listAllsugar");
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);
		player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 450));
		player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 450));
	}
}