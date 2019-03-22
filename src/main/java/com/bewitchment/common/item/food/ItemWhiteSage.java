package com.bewitchment.common.item.food;

import com.bewitchment.common.item.util.ModItemFood;
import com.bewitchment.registry.ModPotions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemWhiteSage extends ModItemFood
{
	public ItemWhiteSage()
	{
		super("white_sage", 2, 0.2f, false, "cropWhiteSage");
		setAlwaysEdible();
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player)
	{
		super.onFoodEaten(stack, world, player);
		player.addPotionEffect(new PotionEffect(ModPotions.magical_boon, 20));
	}
}