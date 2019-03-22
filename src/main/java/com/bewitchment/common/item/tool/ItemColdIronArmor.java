package com.bewitchment.common.item.tool;

import java.util.List;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.common.item.util.ModItemArmor;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemColdIronArmor extends ModItemArmor
{
	public ItemColdIronArmor(String name, ArmorMaterial mat, EntityEquipmentSlot slot)
	{
		super(name, mat, slot);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced)
	{
		tooltip.add(TextFormatting.GRAY + I18n.format("tooltip." + "armor_description_" + getArmorMaterial().name()));
	}
	
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
	}
	
	private byte getColdIronArmor(EntityLivingBase entity)
	{
		byte fin = 0;
		for (ItemStack stack : entity.getArmorInventoryList()) if (stack.getItem() instanceof ItemColdIronArmor) fin++;
		return fin;
	}
}