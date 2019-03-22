package com.bewitchment.common.item.food;

import com.bewitchment.common.item.util.ModItemFood;
import com.bewitchment.registry.ModPotions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemGarlic extends ModItemFood
{
	public ItemGarlic()
	{
		super("garlic", 2, 0.2f, false, "cropGarlic", "listAllherb");
		setAlwaysEdible();
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player)
	{
		super.onFoodEaten(stack, world, player);
		player.addPotionEffect(new PotionEffect(ModPotions.garlicked, 20));
	}
}