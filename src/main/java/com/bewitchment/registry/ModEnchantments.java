package com.bewitchment.registry;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.enchantment.EnchantmentSpiritProtection;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = Bewitchment.MODID)
public class ModEnchantments {
	public static final List<Enchantment> REGISTRY = new ArrayList<>();

	public static final EnchantmentSpiritProtection spirit_protection = new EnchantmentSpiritProtection();

	@SubscribeEvent
	public static void registerEnchantments(Register<Enchantment> event) {
		for (Enchantment enchantment : REGISTRY) event.getRegistry().register(enchantment);
	}
}