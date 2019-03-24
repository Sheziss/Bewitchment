package com.bewitchment.common.enchantment;

import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.common.enchantment.util.ModEnchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentDesperateWard extends ModEnchantment
{
	public EnchantmentDesperateWard()
	{
		super("desperate_ward", Rarity.COMMON, 3);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	protected boolean canApplyTogether(Enchantment enchantment)
	{
		return !(enchantment instanceof EnchantmentPotentWard);
	}
	
	@SubscribeEvent
	public void livingHurt(LivingHurtEvent event)
	{
		if (event.getSource().isMagicDamage() && event.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			MagicPower cap = player.getCapability(MagicPower.CAPABILITY, null);
			if (cap.getAmount() * 5 < cap.getMaxAmount() && cap.drain(10)) event.setAmount(event.getAmount() * (1 - 0.04f * getMaxLevelOnPlayer(player)));
		}
	}
}