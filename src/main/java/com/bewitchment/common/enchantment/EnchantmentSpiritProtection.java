package com.bewitchment.common.enchantment;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.common.enchantment.util.ModEnchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentSpiritProtection extends ModEnchantment {
	public EnchantmentSpiritProtection() {
		super("spirit_protection", Rarity.UNCOMMON, 4, EnumEnchantmentType.ARMOR, EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void livingHurt(LivingHurtEvent event) {
		if (event.getEntityLiving() instanceof EntityPlayer)
			applyEnchantment(event, getTotalLevelOnPlayer((EntityPlayer) event.getEntityLiving()));
	}

	public void applyEnchantment(LivingHurtEvent event, int level) {
		if (level > 0 && event.getSource().getTrueSource() instanceof EntityLivingBase && BewitchmentAPI.isWeakToColdIron((EntityLivingBase) event.getSource().getTrueSource()))
			event.setAmount(event.getAmount() * (1 - 0.05f * level));
	}
}