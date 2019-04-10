package com.bewitchment;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ModConfig extends Configuration {
	public int silverSize, silverChance, silverMin, silverMax,
			saltSize, saltChance, saltMin, saltMax,
			alexandriteSize, alexandriteChance, alexandriteMin, alexandriteMax,
			amethystSize, amethystChance, amethystMin, amethystMax,
			bloodstoneSize, bloodstoneChance, bloodstoneMin, bloodstoneMax,
			garnetSize, garnetChance, garnetMin, garnetMax,
			jasperSize, jasperChance, jasperMin, jasperMax,
			malachiteSize, malachiteChance, malachiteMin, malachiteMax,
			nuummiteSize, nuummiteChance, nuummiteMin, nuummiteMax,
			tigersEyeSize, tigersEyeChance, tigersEyeMin, tigersEyeMax,
			tourmalineSize, tourmalineChance, tourmalineMin, tourmalineMax;

	public float beehive_chance;

	public ModConfig(File file) {
		super(file);
		load();
		silverSize = this.getInt("silverSize", "ore", 4, 0, Byte.MAX_VALUE, "The size of silver ore veins.");
		silverChance = this.getInt("silverChance", "ore", 8, 0, Byte.MAX_VALUE, "The chance for silver ore veins to spawn.");
		silverMin = this.getInt("silverMin", "ore", 10, 0, 0, "The minimum height for silver ore veins to spawn.");
		silverMax = this.getInt("silverMax", "ore", 128, 0, 255, "The maximum height for silver ore veins to spawn.");

		saltSize = this.getInt("saltSize", "ore", 2, 0, Byte.MAX_VALUE, "The size of salt ore veins.");
		saltChance = this.getInt("saltChance", "ore", 6, 0, Byte.MAX_VALUE, "The chance for salt ore veins to spawn.");
		saltMin = this.getInt("saltMin", "ore", 10, 0, 0, "The minimum height for salt ore veins to spawn.");
		saltMax = this.getInt("saltMax", "ore", 128, 0, 255, "The maximum height for salt ore veins to spawn.");

		alexandriteSize = this.getInt("alexandriteSize", "ore", 2, 0, Byte.MAX_VALUE, "The size of alexandrite ore veins.");
		alexandriteChance = this.getInt("alexandriteChance", "ore", 4, 0, Byte.MAX_VALUE, "The chance for alexandrite ore veins to spawn.");
		alexandriteMin = this.getInt("alexandriteMin", "ore", 9, 0, 0, "The minimum height for alexandrite ore veins to spawn.");
		alexandriteMax = this.getInt("alexandriteMax", "ore", 40, 0, 255, "The maximum height for alexandrite ore veins to spawn.");

		amethystSize = this.getInt("amethystSize", "ore", 2, 0, Byte.MAX_VALUE, "The size of amethyst ore veins.");
		amethystChance = this.getInt("amethystChance", "ore", 6, 0, Byte.MAX_VALUE, "The chance for amethyst ore veins to spawn.");
		amethystMin = this.getInt("amethystMin", "ore", 10, 0, 0, "The minimum height for amethyst ore veins to spawn.");
		amethystMax = this.getInt("amethystMax", "ore", 65, 0, 255, "The maximum height for amethyst ore veins to spawn.");

		bloodstoneSize = this.getInt("bloodstoneSize", "ore", 2, 0, Byte.MAX_VALUE, "The size of bloodstone ore veins.");
		bloodstoneChance = this.getInt("bloodstoneChance", "ore", 6, 0, Byte.MAX_VALUE, "The chance for bloodstone ore veins to spawn.");
		bloodstoneMin = this.getInt("bloodstoneMin", "ore", 11, 0, 0, "The minimum height for bloodstone ore veins to spawn.");
		bloodstoneMax = this.getInt("bloodstoneMax", "ore", 100, 0, 255, "The maximum height for bloodstone ore veins to spawn.");

		garnetSize = this.getInt("garnetSize", "ore", 2, 0, Byte.MAX_VALUE, "The size of garnet ore veins.");
		garnetChance = this.getInt("garnetChance", "ore", 6, 0, Byte.MAX_VALUE, "The chance for garnet ore veins to spawn.");
		garnetMin = this.getInt("garnetMin", "ore", 12, 0, 0, "The minimum height for garnet ore veins to spawn.");
		garnetMax = this.getInt("garnetMax", "ore", 65, 0, 255, "The maximum height for garnet ore veins to spawn.");

		jasperSize = this.getInt("jasperSize", "ore", 2, 0, Byte.MAX_VALUE, "The size of jasper ore veins.");
		jasperChance = this.getInt("jasperChance", "ore", 6, 0, Byte.MAX_VALUE, "The chance for jasper ore veins to spawn.");
		jasperMin = this.getInt("jasperMin", "ore", 13, 0, 0, "The minimum height for jasper ore veins to spawn.");
		jasperMax = this.getInt("jasperMax", "ore", 80, 0, 255, "The maximum height for jasper ore veins to spawn.");

		malachiteSize = this.getInt("malachiteSize", "ore", 2, 0, Byte.MAX_VALUE, "The size of malachite ore veins.");
		malachiteChance = this.getInt("malachiteChance", "ore", 6, 0, Byte.MAX_VALUE, "The chance for malachite ore veins to spawn.");
		malachiteMin = this.getInt("malachiteMin", "ore", 14, 0, 0, "The minimum height for malachite ore veins to spawn.");
		malachiteMax = this.getInt("malachiteMax", "ore", 80, 0, 255, "The maximum height for malachite ore veins to spawn.");

		nuummiteSize = this.getInt("nuummiteSize", "ore", 2, 0, Byte.MAX_VALUE, "The size of nuummite ore veins.");
		nuummiteChance = this.getInt("nuummiteChance", "ore", 6, 0, Byte.MAX_VALUE, "The chance for nuummite ore veins to spawn.");
		nuummiteMin = this.getInt("nuummiteMin", "ore", 15, 0, 0, "The minimum height for nuummite ore veins to spawn.");
		nuummiteMax = this.getInt("nuummiteMax", "ore", 80, 0, 255, "The maximum height for nuummite ore veins to spawn.");

		tigersEyeSize = this.getInt("tigersEyeSize", "ore", 2, 0, Byte.MAX_VALUE, "The size of tigers_eye ore veins.");
		tigersEyeChance = this.getInt("tigersEyeChance", "ore", 6, 0, Byte.MAX_VALUE, "The chance for tigers_eye ore veins to spawn.");
		tigersEyeMin = this.getInt("tigersEyeMin", "ore", 16, 0, 0, "The minimum height for tigers_eye ore veins to spawn.");
		tigersEyeMax = this.getInt("tigersEyeMax", "ore", 60, 0, 255, "The maximum height for tigers_eye ore veins to spawn.");

		tourmalineSize = this.getInt("tourmalineSize", "ore", 6, 0, Byte.MAX_VALUE, "The size of tourmaline ore veins.");
		tourmalineChance = this.getInt("tourmalineChance", "ore", 2, 0, Byte.MAX_VALUE, "The chance for tourmaline ore veins to spawn.");
		tourmalineMin = this.getInt("tourmalineMin", "ore", 17, 0, 255, "The minimum height for tourmaline ore veins to spawn.");
		tourmalineMax = this.getInt("tourmalineMax", "ore", 60, 0, 255, "The maximum height for tourmaline ore veins to spawn.");

		beehive_chance = getFloat("beehive_chance", "world", 0.45f, 0, 1, "The chance for beehives to spawn on trees");
		save();
	}
}