package com.bewitchment;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ModConfig extends Configuration {
	public final List<String> broomSweepables;

	public final int cypressChance, elderChance, juniperChance, yewChance;

	public final int silverSize, silverChance, silverMin, silverMax, saltSize, saltChance, saltMin, saltMax, amethystSize, amethystChance, amethystMin, amethystMax, garnetSize, garnetChance, garnetMin, garnetMax, moonstoneSize, moonstoneChance, moonstoneMin, moonstoneMax;
	public final List<String> blindwormBiomes, lizardBiomes, newtBiomes, owlBiomes, ravenBiomes, snakeBiomes, toadBiomes, blackDogBiomes, hellhoundBiomes, alphaHellhoundBiomes, serpentBiomes;

	public final int maxGrimoirePower;

	public ModConfig(File file) {
		super(file);
		load();
		broomSweepables = Arrays.asList(getStringList("broomSweepables", "misc", new String[]{Blocks.REDSTONE_WIRE.getTranslationKey()}, "The list of blocks that the broom will sweep when right clicked on. This includes salt and glyphs by default, so you don't need to add those."));

		cypressChance = getInt("cypressChance", "treeGen", 20, 0, Byte.MAX_VALUE, "The chance for cypress trees to spawn. Set to 0 to disable.");
		elderChance = getInt("elderChance", "treeGen", 20, 0, Byte.MAX_VALUE, "The chance for elder trees to spawn. Set to 0 to disable.");
		juniperChance = getInt("juniperChance", "treeGen", 20, 0, Byte.MAX_VALUE, "The chance for juniper trees to spawn. Set to 0 to disable.");
		yewChance = getInt("yewChance", "treeGen", 20, 0, Byte.MAX_VALUE, "The chance for elder trees to spawn. Set to 0 to disable.");

		silverSize = getInt("silverSize", "oreGen", 4, 0, Byte.MAX_VALUE, "The size of silver ore veins.");
		silverChance = getInt("silverChance", "oreGen", 8, 0, Byte.MAX_VALUE, "The chance for silver ore veins to spawn.");
		silverMin = getInt("silverMin", "oreGen", 10, 0, 0, "The minimum height for silver ore veins to spawn.");
		silverMax = getInt("silverMax", "oreGen", 128, 0, 255, "The maximum height for silver ore veins to spawn.");

		saltSize = getInt("saltSize", "oreGen", 2, 0, Byte.MAX_VALUE, "The size of salt ore veins.");
		saltChance = getInt("saltChance", "oreGen", 6, 0, Byte.MAX_VALUE, "The chance for salt ore veins to spawn.");
		saltMin = getInt("saltMin", "oreGen", 10, 0, 0, "The minimum height for salt ore veins to spawn.");
		saltMax = getInt("saltMax", "oreGen", 120, 0, 255, "The maximum height for salt ore veins to spawn.");

		amethystSize = getInt("amethystSize", "oreGen", 2, 0, Byte.MAX_VALUE, "The size of amethyst ore veins.");
		amethystChance = getInt("amethystChance", "oreGen", 6, 0, Byte.MAX_VALUE, "The chance for amethyst ore veins to spawn.");
		amethystMin = getInt("amethystMin", "oreGen", 10, 0, 0, "The minimum height for amethyst ore veins to spawn.");
		amethystMax = getInt("amethystMax", "oreGen", 64, 0, 255, "The maximum height for amethyst ore veins to spawn.");

		garnetSize = getInt("garnetSize", "oreGen", 2, 0, Byte.MAX_VALUE, "The size of garnet ore veins.");
		garnetChance = getInt("garnetChance", "oreGen", 6, 0, Byte.MAX_VALUE, "The chance for garnet ore veins to spawn.");
		garnetMin = getInt("garnetMin", "oreGen", 12, 0, 0, "The minimum height for garnet ore veins to spawn.");
		garnetMax = getInt("garnetMax", "oreGen", 80, 0, 255, "The maximum height for garnet ore veins to spawn.");

		moonstoneSize = getInt("moonstoneSize", "oreGen", 2, 0, Byte.MAX_VALUE, "The size of moonstone ore veins.");
		moonstoneChance = getInt("moonstoneChance", "oreGen", 6, 0, Byte.MAX_VALUE, "The chance for moonstone ore veins to spawn.");
		moonstoneMin = getInt("moonstoneMin", "oreGen", 16, 0, 0, "The minimum height for moonstone ore veins to spawn.");
		moonstoneMax = getInt("moonstoneMax", "oreGen", 120, 0, 255, "The maximum height for moonstone ore veins to spawn.");

		//Todo: Switch to spawn system used in the master branch, it should probably go in the rewrite as well.
		blindwormBiomes = Arrays.asList(getStringList("blindwormBiomes", "mobSpawns", new String[]{Type.FOREST.getName()}, "The list of BiomeDictionary types that the blindworm will spawn in."));
		lizardBiomes = Arrays.asList(getStringList("lizardBiomes", "mobSpawns", new String[]{Type.FOREST.getName()}, "The list of BiomeDictionary types that the lizard will spawn in."));
		newtBiomes = Arrays.asList(getStringList("newtBiomes", "mobSpawns", new String[]{Type.SWAMP.getName()}, "The list of BiomeDictionary types that the newt will spawn in."));
		owlBiomes = Arrays.asList(getStringList("owlBiomes", "mobSpawns", new String[]{Type.FOREST.getName(), Type.DENSE.getName()}, "The list of BiomeDictionary types that the owl will spawn in."));
		ravenBiomes = Arrays.asList(getStringList("ravenBiomes", "mobSpawns", new String[]{Type.PLAINS.getName(), Type.WASTELAND.getName()}, "The list of BiomeDictionary types that the raven will spawn in."));
		snakeBiomes = Arrays.asList(getStringList("snakeBiomes", "mobSpawns", new String[]{Type.PLAINS.getName(), Type.HILLS.getName()}, "The list of BiomeDictionary types that the snake will spawn in."));
		toadBiomes = Arrays.asList(getStringList("toadBiomes", "mobSpawns", new String[]{Type.SWAMP.getName()}, "The list of BiomeDictionary types that the toad will spawn in."));
		blackDogBiomes = Arrays.asList(getStringList("blackDogBiomes", "mobSpawns", new String[]{Type.PLAINS.getName(), Type.WASTELAND.getName(), Type.FOREST.getName(), Type.SPOOKY.getName()}, "The list of BiomeDictionary types that the black dog will spawn in."));
		hellhoundBiomes = Arrays.asList(getStringList("hellhoundBiomes", "mobSpawns", new String[]{Type.NETHER.getName()}, "The list of BiomeDictionary types that the hellhound will spawn in."));
		alphaHellhoundBiomes = Arrays.asList(getStringList("alphaHellhoundBiomes", "mobSpawns", new String[]{Type.NETHER.getName()}, "The list of BiomeDictionary types that the alpha hellhound will spawn in."));
		serpentBiomes = Arrays.asList(getStringList("serpentBiomes", "mobSpawns", new String[]{Type.NETHER.getName()}, "The list of BiomeDictionary types that the serpent will spawn in."));

		maxGrimoirePower = getInt("maxGrimoirePower", "items", 5000, 1, Integer.MAX_VALUE, "The total amount of Magic Power the Grimoire Magia can hold.");
		save();
	}
}