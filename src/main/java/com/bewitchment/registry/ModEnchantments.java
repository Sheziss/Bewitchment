package com.bewitchment.registry;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.enchantment.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = Bewitchment.MOD_ID)
public class ModEnchantments {
	public static final List<Enchantment> REGISTRY = new ArrayList<>();

	public static final Enchantment desperate_ward = new EnchantmentDesperateWard();
	public static final Enchantment potent_ward = new EnchantmentPotentWard();

	public static final Enchantment natural_attuning = new EnchantmentNaturalAttuning();

	public static final EnchantmentDemonProtection demon_protection = new EnchantmentDemonProtection();
	public static final EnchantmentSpiritProtection spirit_protection = new EnchantmentSpiritProtection();

	@SubscribeEvent
	public static void registerEnchantments(Register<Enchantment> event) {
		for (Enchantment enchantment : REGISTRY) event.getRegistry().register(enchantment);
	}
}