package com.bewitchment.common.world.gen;

import java.util.Random;

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

public class WorldGenOres implements IWorldGenerator
{
	private final WorldGenerator silver, salt, alexandrite, amethyst, bloodstone, garnet, jasper, malachite, nuummite, tigersEye, tourmaline;
	
	public WorldGenOres()
	{
		silver = new WorldGenMinable(ModObjects.ore_silver.getDefaultState(), Bewitchment.proxy.config.silverSize);
		salt = new WorldGenMinable(ModObjects.ore_salt.getDefaultState(), Bewitchment.proxy.config.saltSize);
		alexandrite = new WorldGenMinable(ModObjects.ore_alexandrite.getDefaultState(), Bewitchment.proxy.config.alexandriteSize);
		amethyst = new WorldGenMinable(ModObjects.ore_amethyst.getDefaultState(), Bewitchment.proxy.config.amethystSize);
		bloodstone = new WorldGenMinable(ModObjects.ore_bloodstone.getDefaultState(), Bewitchment.proxy.config.bloodstoneSize);
		garnet = new WorldGenMinable(ModObjects.ore_garnet.getDefaultState(), Bewitchment.proxy.config.garnetSize);
		jasper = new WorldGenMinable(ModObjects.ore_jasper.getDefaultState(), Bewitchment.proxy.config.jasperSize);
		malachite = new WorldGenMinable(ModObjects.ore_malachite.getDefaultState(), Bewitchment.proxy.config.malachiteSize);
		nuummite = new WorldGenMinable(ModObjects.ore_nuummite.getDefaultState(), Bewitchment.proxy.config.nuummiteSize);
		tigersEye = new WorldGenMinable(ModObjects.ore_tigers_eye.getDefaultState(), Bewitchment.proxy.config.tigersEyeSize);
		tourmaline = new WorldGenMinable(ModObjects.ore_tourmaline.getDefaultState(), Bewitchment.proxy.config.tourmalineSize);
	}
	
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator generator, IChunkProvider provider)
	{
		if (world.provider instanceof WorldProviderSurface) // for (int j = 0; j < 32; j++) // uncomment this for mega ores
		{
			for (int i = 0; i < Bewitchment.proxy.config.silverChance; i++) silver.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.silverMax - Bewitchment.proxy.config.silverMin) + Bewitchment.proxy.config.silverMin, chunkZ * 16 + rand.nextInt(16)));
			for (int i = 0; i < Bewitchment.proxy.config.saltChance; i++) salt.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.saltMax - Bewitchment.proxy.config.saltMin) + Bewitchment.proxy.config.saltMin, chunkZ * 16 + rand.nextInt(16)));
			for (int i = 0; i < Bewitchment.proxy.config.alexandriteChance; i++) alexandrite.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt( Bewitchment.proxy.config.alexandriteMax - Bewitchment.proxy.config.alexandriteMin) + Bewitchment.proxy.config.alexandriteMin, chunkZ * 16 + rand.nextInt(16)));
			for (int i = 0; i < Bewitchment.proxy.config.amethystChance; i++) amethyst.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.amethystMax - Bewitchment.proxy.config.amethystMin) + Bewitchment.proxy.config.amethystMin, chunkZ * 16 + rand.nextInt(16)));
			for (int i = 0; i < Bewitchment.proxy.config.bloodstoneChance; i++) bloodstone.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.bloodstoneMax - Bewitchment.proxy.config.bloodstoneMin) + Bewitchment.proxy.config.bloodstoneMin, chunkZ * 16 + rand.nextInt(16)));
			for (int i = 0; i < Bewitchment.proxy.config.garnetChance; i++) garnet.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.garnetMax - Bewitchment.proxy.config.garnetMin) + Bewitchment.proxy.config.garnetMin, chunkZ * 16 + rand.nextInt(16)));
			for (int i = 0; i < Bewitchment.proxy.config.jasperChance; i++) jasper.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.jasperMax - Bewitchment.proxy.config.jasperMin) + Bewitchment.proxy.config.jasperMin, chunkZ * 16 + rand.nextInt(16)));
			for (int i = 0; i < Bewitchment.proxy.config.malachiteChance; i++) malachite.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.malachiteMax - Bewitchment.proxy.config.malachiteMin) + Bewitchment.proxy.config.malachiteMin, chunkZ * 16 + rand.nextInt(16)));
			for (int i = 0; i < Bewitchment.proxy.config.nuummiteChance; i++) nuummite.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.nuummiteMax - Bewitchment.proxy.config.nuummiteMin) + Bewitchment.proxy.config.nuummiteMin, chunkZ * 16 + rand.nextInt(16)));
			for (int i = 0; i < Bewitchment.proxy.config.tigersEyeChance; i++) tigersEye.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.tigersEyeMax - Bewitchment.proxy.config.tigersEyeMin) + Bewitchment.proxy.config.tigersEyeMin, chunkZ * 16 + rand.nextInt(16)));
			for (int i = 0; i < Bewitchment.proxy.config.tourmalineChance; i++) tourmaline.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.tourmalineMax - Bewitchment.proxy.config.tourmalineMin) + Bewitchment.proxy.config.tourmalineMin, chunkZ * 16 + rand.nextInt(16)));
		}
	}
}