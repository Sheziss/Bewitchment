package com.bewitchment.common.enchantment;

import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.common.enchantment.util.ModEnchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentPotentWard extends ModEnchantment
{
	public EnchantmentPotentWard()
	{
		super("potent_ward", Rarity.VERY_RARE, 3);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	protected boolean canApplyTogether(Enchantment enchantment)
	{
		return !(enchantment instanceof EnchantmentDesperateWard);
	}
	
	@SubscribeEvent
	public void livingHurt(LivingHurtEvent event)
	{
		if (event.getSource().isMagicDamage() && event.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			MagicPower cap = player.getCapability(MagicPower.CAPABILITY, null);
			if (cap.getAmount() * 2 < cap.getMaxAmount() && cap.drain(150)) event.setAmount(Math.max(0, event.getAmount() - getMaxLevelOnPlayer(player)));
		}
	}
}