package com.bewitchment.common.world.gen;

import com.bewitchment.Bewitchment;
import com.bewitchment.registry.ModObjects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenOres implements IWorldGenerator {
	private final WorldGenerator silver, salt, amethyst, garnet;

	public WorldGenOres() {
		silver = new WorldGenMinable(ModObjects.ore_silver.getDefaultState(), Bewitchment.proxy.config.silverSize);
		salt = new WorldGenMinable(ModObjects.ore_salt.getDefaultState(), Bewitchment.proxy.config.saltSize);
		amethyst = new WorldGenMinable(ModObjects.ore_amethyst.getDefaultState(), Bewitchment.proxy.config.amethystSize);
		garnet = new WorldGenMinable(ModObjects.ore_garnet.getDefaultState(), Bewitchment.proxy.config.garnetSize);
	}

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator generator, IChunkProvider provider) {
		if (world.provider instanceof WorldProviderSurface) // for (int j = 0; j < 32; j++) // uncomment this for mega ores
		{
			for (int i = 0; i < Bewitchment.proxy.config.silverChance; i++)
				silver.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.silverMax - Bewitchment.proxy.config.silverMin) + Bewitchment.proxy.config.silverMin, chunkZ * 16 + rand.nextInt(16)));
			for (int i = 0; i < Bewitchment.proxy.config.saltChance; i++)
				salt.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.saltMax - Bewitchment.proxy.config.saltMin) + Bewitchment.proxy.config.saltMin, chunkZ * 16 + rand.nextInt(16)));
			for (int i = 0; i < Bewitchment.proxy.config.amethystChance; i++)
				amethyst.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.amethystMax - Bewitchment.proxy.config.amethystMin) + Bewitchment.proxy.config.amethystMin, chunkZ * 16 + rand.nextInt(16)));
			for (int i = 0; i < Bewitchment.proxy.config.garnetChance; i++)
				garnet.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.garnetMax - Bewitchment.proxy.config.garnetMin) + Bewitchment.proxy.config.garnetMin, chunkZ * 16 + rand.nextInt(16)));
		}
	}
}