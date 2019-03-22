package com.bewitchment.common.item.food;

import com.bewitchment.common.item.util.ModItemFood;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemHeart extends ModItemFood
{
	public ItemHeart()
	{
		super("heart", 8, 1.6f, true);
		setAlwaysEdible();
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player)
	{
		super.onFoodEaten(stack, world, player);
		player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 750, 3));
		player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 750, 3));
	}
}