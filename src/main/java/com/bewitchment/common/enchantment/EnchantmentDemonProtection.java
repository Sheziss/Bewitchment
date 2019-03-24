package com.bewitchment.common.enchantment;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.common.enchantment.util.ModEnchantment;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentDemonProtection extends ModEnchantment
{
	public EnchantmentDemonProtection()
	{
		super("demon_protection", Rarity.UNCOMMON, 3);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void livingHurt(LivingHurtEvent event)
	{
		if (event.getEntityLiving() instanceof EntityPlayer) applyEnchantment(event, getTotalLevelOnPlayer((EntityPlayer) event.getEntityLiving()));
	}
	
	public void applyEnchantment(LivingHurtEvent event, int level)
	{
		if (event.getSource().getTrueSource() instanceof EntityLivingBase && ((EntityLivingBase) event.getSource().getTrueSource()).getCreatureAttribute() == BewitchmentAPI.SPIRIT) event.setAmount(event.getAmount() * (1 - 0.05f * level));
	}
}