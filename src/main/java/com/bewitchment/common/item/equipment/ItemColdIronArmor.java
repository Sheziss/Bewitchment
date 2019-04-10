package com.bewitchment.common.item.equipment;

import com.bewitchment.common.item.util.ModItemArmor;
import com.bewitchment.registry.ModEnchantments;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemColdIronArmor extends ModItemArmor {
	public ItemColdIronArmor(String name, ArmorMaterial mat, EntityEquipmentSlot slot) {
		super(name, mat, slot);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {
		ModEnchantments.demon_protection.applyEnchantment(event, getColdIronArmor(event.getEntityLiving()));
		ModEnchantments.spirit_protection.applyEnchantment(event, getColdIronArmor(event.getEntityLiving()));
	}

	private byte getColdIronArmor(EntityLivingBase entity) {
		byte fin = 0;
		for (ItemStack stack : entity.getArmorInventoryList()) if (stack.getItem() instanceof ItemColdIronArmor) fin++;
		return fin;
	}
}