package com.bewitchment.common.item.food;

import com.bewitchment.common.item.util.ModItemFood;
import com.bewitchment.registry.ModPotions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemAconitum extends ModItemFood
{
	public ItemAconitum()
	{
		super("aconitum", 2, 0.6f, false, "cropAconitum");
		setAlwaysEdible();
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player)
	{
		super.onFoodEaten(stack, world, player);
		player.addPotionEffect(new PotionEffect(MobEffects.POISON, 20));
		player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 20));
		player.addPotionEffect(new PotionEffect(ModPotions.wolfsbane, 20));
	}
}