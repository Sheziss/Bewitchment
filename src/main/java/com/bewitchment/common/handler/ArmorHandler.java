package com.bewitchment.common.handler;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.capability.transformation.Transformation;
import com.bewitchment.api.capability.transformation.Transformation.TransformationType;
import com.bewitchment.common.item.tool.ItemColdIronArmor;
import com.bewitchment.common.item.tool.ItemSilverArmor;

import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ArmorHandler {
	@SubscribeEvent
	public void onHurt(LivingHurtEvent event)
	{
		if (getColdIronArmor(event.getEntityLiving()) > 0)
		{
			if (event.getSource().getTrueSource() instanceof EntityLivingBase)
			{
				EntityLivingBase living = (EntityLivingBase) event.getSource().getTrueSource();
				EntityLivingBase attacked = event.getEntityLiving();
				if (living.getCreatureAttribute() == BewitchmentAPI.DEMON)
				{
					if (!event.getSource().isProjectile()) living.attackEntityFrom(DamageSource.causeThornsDamage(attacked), EnchantmentThorns.getDamage(getColdIronArmor(attacked), attacked.getRNG()));
					event.setAmount(event.getAmount() * (1 - 0.05f * getColdIronArmor(attacked)));
				}
				if (living.getCreatureAttribute() == BewitchmentAPI.SPIRIT) event.setAmount(event.getAmount() * 0.8f);
				if (event.getSource().isFireDamage()) event.setAmount(event.getAmount() * 0.95f);
			}
		}
		if (getSilverArmor(event.getEntityLiving()) > 0)
		{
			if (event.getSource().getTrueSource() instanceof EntityLivingBase)
			{
				if (((EntityLivingBase) event.getSource().getTrueSource()).getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) event.setAmount(event.getAmount() * 0.9f);
				if (event.getSource().getTrueSource() instanceof EntityPlayer && event.getSource().getTrueSource().getCapability(Transformation.CAPABILITY, null).getTransformation() == TransformationType.WEREWOLF)
				{
					event.setAmount(event.getAmount() * 0.9f);
					event.getSource().getTrueSource().attackEntityFrom(DamageSource.causeThornsDamage(event.getEntityLiving()), MathHelper.clamp(event.getAmount() / 2, 1, 4));
				}
			}
		}
	}
	
	private byte getColdIronArmor(EntityLivingBase entity)
	{
		byte fin = 0;
		for (ItemStack stack : entity.getArmorInventoryList()) if (stack.getItem() instanceof ItemColdIronArmor) fin++;
		return fin;
	}
	
	private byte getSilverArmor(EntityLivingBase entity)
	{
		byte fin = 0;
		for (ItemStack stack : entity.getArmorInventoryList()) if (stack.getItem() instanceof ItemSilverArmor) fin++;
		return fin;
	}
}