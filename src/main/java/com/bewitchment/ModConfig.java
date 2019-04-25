package com.bewitchment;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.bewitchment.registry.ModObjects;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.config.Configuration;

public class ModConfig extends Configuration {
	public final List<String> broomSweepables;

	public final int silverSize, silverChance, silverMin, silverMax,
			saltSize, saltChance, saltMin, saltMax,
			amethystSize, amethystChance, amethystMin, amethystMax,
			garnetSize, garnetChance, garnetMin, garnetMax,
			moonstoneSize, moonstoneChance, moonstoneMin, moonstoneMax;

	public ModConfig(File file) {
		super(file);
		load();
		broomSweepables = Arrays.asList(getStringList("broomSweepables", "misc", new String[]{Blocks.REDSTONE_WIRE.getTranslationKey(), ModObjects.glyph.getTranslationKey(), ModObjects.salt_barrier.getTranslationKey()}, "The list of blocks that the broom will sweep when right clicked on."));
		
		silverSize = getInt("silverSize", "ore", 4, 0, Byte.MAX_VALUE, "The size of silver ore veins.");
		silverChance = getInt("silverChance", "ore", 8, 0, Byte.MAX_VALUE, "The chance for silver ore veins to spawn.");
		silverMin = getInt("silverMin", "ore", 10, 0, 0, "The minimum height for silver ore veins to spawn.");
		silverMax = getInt("silverMax", "ore", 128, 0, 255, "The maximum height for silver ore veins to spawn.");

		saltSize = getInt("saltSize", "ore", 2, 0, Byte.MAX_VALUE, "The size of salt ore veins.");
		saltChance = getInt("saltChance", "ore", 6, 0, Byte.MAX_VALUE, "The chance for salt ore veins to spawn.");
		saltMin = getInt("saltMin", "ore", 10, 0, 0, "The minimum height for salt ore veins to spawn.");
		saltMax = getInt("saltMax", "ore", 120, 0, 255, "The maximum height for salt ore veins to spawn.");

		amethystSize = getInt("amethystSize", "ore", 2, 0, Byte.MAX_VALUE, "The size of amethyst ore veins.");
		amethystChance = getInt("amethystChance", "ore", 6, 0, Byte.MAX_VALUE, "The chance for amethyst ore veins to spawn.");
		amethystMin = getInt("amethystMin", "ore", 10, 0, 0, "The minimum height for amethyst ore veins to spawn.");
		amethystMax = getInt("amethystMax", "ore", 64, 0, 255, "The maximum height for amethyst ore veins to spawn.");

		garnetSize = getInt("garnetSize", "ore", 2, 0, Byte.MAX_VALUE, "The size of garnet ore veins.");
		garnetChance = getInt("garnetChance", "ore", 6, 0, Byte.MAX_VALUE, "The chance for garnet ore veins to spawn.");
		garnetMin = getInt("garnetMin", "ore", 12, 0, 0, "The minimum height for garnet ore veins to spawn.");
		garnetMax = getInt("garnetMax", "ore", 80, 0, 255, "The maximum height for garnet ore veins to spawn.");

		moonstoneSize = getInt("moonstoneSize", "ore", 2, 0, Byte.MAX_VALUE, "The size of moonstone ore veins.");
		moonstoneChance = getInt("moonstoneChance", "ore", 6, 0, Byte.MAX_VALUE, "The chance for moonstone ore veins to spawn.");
		moonstoneMin = getInt("moonstoneMin", "ore", 16, 0, 0, "The minimum height for moonstone ore veins to spawn.");
		moonstoneMax = getInt("moonstoneMax", "ore", 120, 0, 255, "The maximum height for moonstone ore veins to spawn.");
		save();
	}
}