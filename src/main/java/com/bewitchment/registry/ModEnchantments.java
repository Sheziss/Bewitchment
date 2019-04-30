package com.bewitchment.registry;

import com.bewitchment.common.enchantment.EnchantmentSpiritProtection;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ModEnchantments {
	public static final List<Enchantment> REGISTRY = new ArrayList<>();

	public static final EnchantmentSpiritProtection spirit_protection = new EnchantmentSpiritProtection();

	public static void preInit() {
		for (Enchantment enchantment : REGISTRY) ForgeRegistries.ENCHANTMENTS.register(enchantment);
	}
}