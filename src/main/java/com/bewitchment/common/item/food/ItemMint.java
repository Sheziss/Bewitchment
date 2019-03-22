package com.bewitchment.common.item.food;

import com.bewitchment.common.item.util.ModItemFood;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMint extends ModItemFood
{
	public ItemMint()
	{
		super("mint", 2, 0.2f, false, "cropMint", "cropSpiceleaf", "listAllspice", "listAllgreenveggie");
		setAlwaysEdible();
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player)
	{
		super.onFoodEaten(stack, world, player);
		player.extinguish();
	}
}