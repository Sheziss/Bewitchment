package com.bewitchment;

import com.bewitchment.registry.ModObjects;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ModConfig extends Configuration {
	public String[] broomSweepables;

	public int silverSize, silverChance, silverMin, silverMax,
			saltSize, saltChance, saltMin, saltMax,
			amethystSize, amethystChance, amethystMin, amethystMax,
			garnetSize, garnetChance, garnetMin, garnetMax,
			moonstoneSize, moonstoneChance, moonstoneMin, moonstoneMax;

	public ModConfig(File file) {
		super(file);
		load();
		broomSweepables = this.getStringList("broomSweepables", "misc", new String[]{Blocks.REDSTONE_WIRE.getTranslationKey(), ModObjects.glyph.getTranslationKey(), ModObjects.salt_barrier.getTranslationKey()}, "The list of blocks that the broom will sweep when right clicked on.");

		silverSize = this.getInt("silverSize", "ore", 4, 0, Byte.MAX_VALUE, "The size of silver ore veins.");
		silverChance = this.getInt("silverChance", "ore", 8, 0, Byte.MAX_VALUE, "The chance for silver ore veins to spawn.");
		silverMin = this.getInt("silverMin", "ore", 10, 0, 0, "The minimum height for silver ore veins to spawn.");
		silverMax = this.getInt("silverMax", "ore", 128, 0, 255, "The maximum height for silver ore veins to spawn.");

		saltSize = this.getInt("saltSize", "ore", 2, 0, Byte.MAX_VALUE, "The size of salt ore veins.");
		saltChance = this.getInt("saltChance", "ore", 6, 0, Byte.MAX_VALUE, "The chance for salt ore veins to spawn.");
		saltMin = this.getInt("saltMin", "ore", 10, 0, 0, "The minimum height for salt ore veins to spawn.");
		saltMax = this.getInt("saltMax", "ore", 120, 0, 255, "The maximum height for salt ore veins to spawn.");

		amethystSize = this.getInt("amethystSize", "ore", 2, 0, Byte.MAX_VALUE, "The size of amethyst ore veins.");
		amethystChance = this.getInt("amethystChance", "ore", 6, 0, Byte.MAX_VALUE, "The chance for amethyst ore veins to spawn.");
		amethystMin = this.getInt("amethystMin", "ore", 10, 0, 0, "The minimum height for amethyst ore veins to spawn.");
		amethystMax = this.getInt("amethystMax", "ore", 64, 0, 255, "The maximum height for amethyst ore veins to spawn.");

		garnetSize = this.getInt("garnetSize", "ore", 2, 0, Byte.MAX_VALUE, "The size of garnet ore veins.");
		garnetChance = this.getInt("garnetChance", "ore", 6, 0, Byte.MAX_VALUE, "The chance for garnet ore veins to spawn.");
		garnetMin = this.getInt("garnetMin", "ore", 12, 0, 0, "The minimum height for garnet ore veins to spawn.");
		garnetMax = this.getInt("garnetMax", "ore", 80, 0, 255, "The maximum height for garnet ore veins to spawn.");

		moonstoneSize = this.getInt("moonstoneSize", "ore", 2, 0, Byte.MAX_VALUE, "The size of moonstone ore veins.");
		moonstoneChance = this.getInt("moonstoneChance", "ore", 6, 0, Byte.MAX_VALUE, "The chance for moonstone ore veins to spawn.");
		moonstoneMin = this.getInt("moonstoneMin", "ore", 16, 0, 0, "The minimum height for moonstone ore veins to spawn.");
		moonstoneMax = this.getInt("moonstoneMax", "ore", 120, 0, 255, "The maximum height for moonstone ore veins to spawn.");
		save();
	}
}