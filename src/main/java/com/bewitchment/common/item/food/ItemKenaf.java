package com.bewitchment.common.item.food;

import com.bewitchment.common.item.util.ModItemFood;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemKenaf extends ModItemFood
{
	public ItemKenaf()
	{
		super("kenaf", 3, 0.8f, false, "cropKenaf");
		setAlwaysEdible();
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player)
	{
		super.onFoodEaten(stack, world, player);
		player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 10));
	}
}