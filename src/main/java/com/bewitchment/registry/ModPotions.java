package com.bewitchment.registry;

import com.bewitchment.common.potion.PotionSunWard;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ModPotions {
	public static final List<Potion> REGISTRY = new ArrayList<>();

	public static final Potion sun_ward = new PotionSunWard();

	public static void preInit() {
		for (Potion potion : REGISTRY) ForgeRegistries.POTIONS.register(potion);
	}
}