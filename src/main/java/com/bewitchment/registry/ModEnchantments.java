package com.bewitchment.registry;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.enchantment.EnchantmentDesperateWard;
import com.bewitchment.common.enchantment.EnchantmentNaturalAttuning;
import com.bewitchment.common.enchantment.EnchantmentPotentWard;
import com.bewitchment.common.enchantment.EnchantmentSpiritProtection;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Bewitchment.MODID)
public class ModEnchantments {
	public static final List<Enchantment> REGISTRY = new ArrayList<>();

	public static final Enchantment desperate_ward = new EnchantmentDesperateWard();
	public static final Enchantment potent_ward = new EnchantmentPotentWard();

	public static final Enchantment natural_attuning = new EnchantmentNaturalAttuning();

	public static final EnchantmentSpiritProtection spirit_protection = new EnchantmentSpiritProtection();

	@SubscribeEvent
	public static void registerEnchantments(Register<Enchantment> event) {
		for (Enchantment enchantment : REGISTRY) event.getRegistry().register(enchantment);
	}
}