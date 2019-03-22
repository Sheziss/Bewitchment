package com.bewitchment.common.item.food;

import com.bewitchment.common.item.util.ModItemFood;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGinger extends ModItemFood
{
	public ItemGinger()
	{
		super("ginger", 5, 0.8f, false, "cropGinger", "listAllspice");
		setAlwaysEdible();
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player)
	{
		super.onFoodEaten(stack, world, player);
		player.setFire(10);
	}
}