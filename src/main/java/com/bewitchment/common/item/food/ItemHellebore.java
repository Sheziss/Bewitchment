package com.bewitchment.common.item.food;

import com.bewitchment.common.item.util.ModItemFood;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemHellebore extends ModItemFood
{
	public ItemHellebore()
	{
		super("hellebore", 2, 0.2f, false, "cropHellebore");
		setAlwaysEdible();
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player)
	{
		super.onFoodEaten(stack, world, player);
		player.addPotionEffect(new PotionEffect(MobEffects.UNLUCK, 10));
		player.addPotionEffect(new PotionEffect(MobEffects.WITHER, 10));
	}
}