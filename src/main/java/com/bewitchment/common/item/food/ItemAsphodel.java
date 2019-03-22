package com.bewitchment.common.item.food;

import com.bewitchment.common.item.util.ModItemFood;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemAsphodel extends ModItemFood
{
	public ItemAsphodel()
	{
		super("asphodel", 2, 0.2f, false, "cropAsphodel");
		setAlwaysEdible();
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player)
	{
		super.onFoodEaten(stack, world, player);
		player.addPotionEffect(new PotionEffect(MobEffects.UNLUCK, 20));
	}
}