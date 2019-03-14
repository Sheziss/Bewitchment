package com.bewitchment;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ModConfig extends Configuration
{
	public int silver_size, silver_chance, silver_min, silver_max,
	salt_size, salt_chance, salt_min, salt_max,
	alexandrite_size, alexandrite_chance, alexandrite_min, alexandrite_max,
	amethyst_size, amethyst_chance, amethyst_min, amethyst_max,
	bloodstone_size, bloodstone_chance, bloodstone_min, bloodstone_max,
	garnet_size, garnet_chance, garnet_min, garnet_max,
	jasper_size, jasper_chance, jasper_min, jasper_max,
	malachite_size, malachite_chance, malachite_min, malachite_max,
	nuummite_size, nuummite_chance, nuummite_min, nuummite_max,
	tigers_eye_size, tigers_eye_chance, tigers_eye_min, tigers_eye_max,
	tourmaline_size, tourmaline_chance, tourmaline_min, tourmaline_max;
	
	public ModConfig(File file)
	{
		super(file);
		load();
		silver_size = this.getInt("silver_size", "ore", 4, 0, Integer.MAX_VALUE, "The size of silver ore veins.");
		silver_chance = this.getInt("silver_chance", "ore", 8, 0, Integer.MAX_VALUE, "The chance for silver ore veins to spawn.");
		silver_min = this.getInt("silver_min", "ore", 10, 0, Integer.MAX_VALUE, "The minimum height for silver ore veins to spawn.");
		silver_max = this.getInt("silver_max", "ore", 128, 0, Integer.MAX_VALUE, "The maximum height for silver ore veins to spawn.");
		salt_size = this.getInt("salt_size", "ore", 2, 0, Integer.MAX_VALUE, "The size of salt ore veins.");
		salt_chance = this.getInt("salt_chance", "ore", 6, 0, Integer.MAX_VALUE, "The chance for salt ore veins to spawn.");
		salt_min = this.getInt("salt_min", "ore", 10, 0, Integer.MAX_VALUE, "The minimum height for salt ore veins to spawn.");
		salt_max = this.getInt("salt_max", "ore", 128, 0, Integer.MAX_VALUE, "The maximum height for salt ore veins to spawn.");
		alexandrite_size = this.getInt("alexandrite_size", "ore", 2, 0, Integer.MAX_VALUE, "The size of alexandrite ore veins.");
		alexandrite_chance = this.getInt("alexandrite_chance", "ore", 4, 0, Integer.MAX_VALUE, "The chance for alexandrite ore veins to spawn.");
		alexandrite_min = this.getInt("alexandrite_min", "ore", 9, 0, Integer.MAX_VALUE, "The minimum height for alexandrite ore veins to spawn.");
		alexandrite_max = this.getInt("alexandrite_max", "ore", 40, 0, Integer.MAX_VALUE, "The maximum height for alexandrite ore veins to spawn.");
		amethyst_size = this.getInt("amethyst_size", "ore", 2, 0, Integer.MAX_VALUE, "The size of amethyst ore veins.");
		amethyst_chance = this.getInt("amethyst_chance", "ore", 6, 0, Integer.MAX_VALUE, "The chance for amethyst ore veins to spawn.");
		amethyst_min = this.getInt("amethyst_min", "ore", 10, 0, Integer.MAX_VALUE, "The minimum height for amethyst ore veins to spawn.");
		amethyst_max = this.getInt("amethyst_max", "ore", 65, 0, Integer.MAX_VALUE, "The maximum height for amethyst ore veins to spawn.");
		bloodstone_size = this.getInt("bloodstone_size", "ore", 2, 0, Integer.MAX_VALUE, "The size of bloodstone ore veins.");
		bloodstone_chance = this.getInt("bloodstone_chance", "ore", 6, 0, Integer.MAX_VALUE, "The chance for bloodstone ore veins to spawn.");
		bloodstone_min = this.getInt("bloodstone_min", "ore", 11, 0, Integer.MAX_VALUE, "The minimum height for bloodstone ore veins to spawn.");
		bloodstone_max = this.getInt("bloodstone_max", "ore", 100, 0, Integer.MAX_VALUE, "The maximum height for bloodstone ore veins to spawn.");
		garnet_size = this.getInt("garnet_size", "ore", 2, 0, Integer.MAX_VALUE, "The size of garnet ore veins.");
		garnet_chance = this.getInt("garnet_chance", "ore", 6, 0, Integer.MAX_VALUE, "The chance for garnet ore veins to spawn.");
		garnet_min = this.getInt("garnet_min", "ore", 12, 0, Integer.MAX_VALUE, "The minimum height for garnet ore veins to spawn.");
		garnet_max = this.getInt("garnet_max", "ore", 65, 0, Integer.MAX_VALUE, "The maximum height for garnet ore veins to spawn.");
		jasper_size = this.getInt("jasper_size", "ore", 2, 0, Integer.MAX_VALUE, "The size of jasper ore veins.");
		jasper_chance = this.getInt("jasper_chance", "ore", 6, 0, Integer.MAX_VALUE, "The chance for jasper ore veins to spawn.");
		jasper_min = this.getInt("jasper_min", "ore", 13, 0, Integer.MAX_VALUE, "The minimum height for jasper ore veins to spawn.");
		jasper_max = this.getInt("jasper_max", "ore", 80, 0, Integer.MAX_VALUE, "The maximum height for jasper ore veins to spawn.");
		malachite_size = this.getInt("malachite_size", "ore", 2, 0, Integer.MAX_VALUE, "The size of malachite ore veins.");
		malachite_chance = this.getInt("malachite_chance", "ore", 6, 0, Integer.MAX_VALUE, "The chance for malachite ore veins to spawn.");
		malachite_min = this.getInt("malachite_min", "ore", 14, 0, Integer.MAX_VALUE, "The minimum height for malachite ore veins to spawn.");
		malachite_max = this.getInt("malachite_max", "ore", 80, 0, Integer.MAX_VALUE, "The maximum height for malachite ore veins to spawn.");
		nuummite_size = this.getInt("nuummite_size", "ore", 2, 0, Integer.MAX_VALUE, "The size of nuummite ore veins.");
		nuummite_chance = this.getInt("nuummite_chance", "ore", 6, 0, Integer.MAX_VALUE, "The chance for nuummite ore veins to spawn.");
		nuummite_min = this.getInt("nuummite_min", "ore", 15, 0, Integer.MAX_VALUE, "The minimum height for nuummite ore veins to spawn.");
		nuummite_max = this.getInt("nuummite_max", "ore", 80, 0, Integer.MAX_VALUE, "The maximum height for nuummite ore veins to spawn.");
		tigers_eye_size = this.getInt("tigers_eye_size", "ore", 2, 0, Integer.MAX_VALUE, "The size of tigers_eye ore veins.");
		tigers_eye_chance = this.getInt("tigers_eye_chance", "ore", 6, 0, Integer.MAX_VALUE, "The chance for tigers_eye ore veins to spawn.");
		tigers_eye_min = this.getInt("tigers_eye_min", "ore", 16, 0, Integer.MAX_VALUE, "The minimum height for tigers_eye ore veins to spawn.");
		tigers_eye_max = this.getInt("tigers_eye_max", "ore", 60, 0, Integer.MAX_VALUE, "The maximum height for tigers_eye ore veins to spawn.");
		tourmaline_size = this.getInt("tourmaline_size", "ore", 6, 0, Integer.MAX_VALUE, "The size of tourmaline ore veins.");
		tourmaline_chance = this.getInt("tourmaline_chance", "ore", 2, 0, Integer.MAX_VALUE, "The chance for tourmaline ore veins to spawn.");
		tourmaline_min = this.getInt("tourmaline_min", "ore", 17, 0, Integer.MAX_VALUE, "The minimum height for tourmaline ore veins to spawn.");
		tourmaline_max = this.getInt("tourmaline_max", "ore", 60, 0, Integer.MAX_VALUE, "The maximum height for tourmaline ore veins to spawn.");
		save();
	}
}