package com.bewitchment.common.handler;

import com.bewitchment.Util;
import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.registry.ModEnchantments;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler
{
	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {
		if (!event.getEntityLiving().world.isRemote)
		{
			// Cold Iron Armor
			ModEnchantments.spirit_protection.applyEnchantment(event, getColdIronArmor(event.getEntityLiving()));
			
			// Cold Iron Tools
			if (event.getSource().getTrueSource() instanceof EntityLivingBase)
			{
				EntityLivingBase living = (EntityLivingBase) event.getSource().getTrueSource();
				if (BewitchmentAPI.isWeakToColdIron(event.getEntityLiving()) && Util.isRelated(living.getHeldItemMainhand(), "ingotColdIron"))
				{
					event.setAmount(event.getAmount() * 4);
					event.getSource().getTrueSource().attackEntityFrom(DamageSource.causeThornsDamage(event.getEntityLiving()), getColdIronArmor(event.getEntityLiving()));
				}
			}
			
			// Silver Armor
			if (getSilverArmor(event.getEntityLiving()) > 0)
			{
				if (event.getSource().getTrueSource() instanceof EntityLivingBase)
				{
					if (BewitchmentAPI.isWeakToSilver((EntityLivingBase) event.getSource().getTrueSource()))
					{
						event.setAmount(event.getAmount() * 0.9f);
						event.getSource().getTrueSource().attackEntityFrom(DamageSource.causeThornsDamage(event.getEntityLiving()), getSilverArmor(event.getEntityLiving()));
					}
				}
			}
			
			// Silver Tools
			if (event.getSource().getTrueSource() instanceof EntityLivingBase)
			{
				EntityLivingBase living = (EntityLivingBase) event.getSource().getTrueSource();
				if (BewitchmentAPI.isWeakToSilver(event.getEntityLiving()) && Util.isRelated(living.getHeldItemMainhand(), "ingotSilver")) event.setAmount(event.getAmount() * 4);
			}
		}
	}

	private byte getColdIronArmor(EntityLivingBase entity) {
		byte fin = 0;
		for (ItemStack stack : entity.getArmorInventoryList()) if (Util.isRelated(stack, "ingotColdIron")) fin++;
		return fin;
	}
	
	private byte getSilverArmor(EntityLivingBase entity) {
		byte fin = 0;
		for (ItemStack stack : entity.getArmorInventoryList()) if (Util.isRelated(stack, "ingotSilver")) fin++;
		return fin;
	}
}