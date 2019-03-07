package com.bewitchment.common.world.gen;

import java.util.Random;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.registry.ModBlocks;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenOres implements IWorldGenerator
{
	private final WorldGenerator silver, salt, alexandrite, amethyst, bloodstone, garnet, jasper, malachite, nuummite, tigers_eye, tourmaline;
	
	public WorldGenOres()
	{
		this.silver = new WorldGenMinable(ModBlocks.ore_silver.getDefaultState(), Bewitchment.proxy.config.silver_size);
		this.salt = new WorldGenMinable(ModBlocks.ore_salt.getDefaultState(), Bewitchment.proxy.config.salt_size);
		this.alexandrite = new WorldGenMinable(ModBlocks.ore_alexandrite.getDefaultState(), Bewitchment.proxy.config.alexandrite_size);
		this.amethyst = new WorldGenMinable(ModBlocks.ore_amethyst.getDefaultState(), Bewitchment.proxy.config.amethyst_size);
		this.bloodstone = new WorldGenMinable(ModBlocks.ore_bloodstone.getDefaultState(), Bewitchment.proxy.config.bloodstone_size);
		this.garnet = new WorldGenMinable(ModBlocks.ore_garnet.getDefaultState(), Bewitchment.proxy.config.garnet_size);
		this.jasper = new WorldGenMinable(ModBlocks.ore_jasper.getDefaultState(), Bewitchment.proxy.config.jasper_size);
		this.malachite = new WorldGenMinable(ModBlocks.ore_malachite.getDefaultState(), Bewitchment.proxy.config.malachite_size);
		this.nuummite = new WorldGenMinable(ModBlocks.ore_nuummite.getDefaultState(), Bewitchment.proxy.config.nuummite_size);
		this.tigers_eye = new WorldGenMinable(ModBlocks.ore_tigers_eye.getDefaultState(), Bewitchment.proxy.config.tigers_eye_size);
		this.tourmaline = new WorldGenMinable(ModBlocks.ore_tourmaline.getDefaultState(), Bewitchment.proxy.config.tourmaline_size);
	}
	
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator generator, IChunkProvider provider)
	{
		if (world.provider.getDimension() == 0)
		{
//			for (int j = 0; j < 32; j++) // uncomment this for mega ores
			{
				for (int i = 0; i < Bewitchment.proxy.config.silver_chance; i++) silver.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.silver_max - Bewitchment.proxy.config.silver_min) + Bewitchment.proxy.config.silver_min, chunkZ * 16 + rand.nextInt(16)));
				for (int i = 0; i < Bewitchment.proxy.config.salt_chance; i++) salt.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.salt_max - Bewitchment.proxy.config.salt_min) + Bewitchment.proxy.config.salt_min, chunkZ * 16 + rand.nextInt(16)));
				for (int i = 0; i < Bewitchment.proxy.config.alexandrite_chance; i++) alexandrite.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.alexandrite_max - Bewitchment.proxy.config.alexandrite_min) + Bewitchment.proxy.config.alexandrite_min, chunkZ * 16 + rand.nextInt(16)));
				for (int i = 0; i < Bewitchment.proxy.config.amethyst_chance; i++) amethyst.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.amethyst_max - Bewitchment.proxy.config.amethyst_min) + Bewitchment.proxy.config.amethyst_min, chunkZ * 16 + rand.nextInt(16)));
				for (int i = 0; i < Bewitchment.proxy.config.bloodstone_chance; i++) bloodstone.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.bloodstone_max - Bewitchment.proxy.config.bloodstone_min) + Bewitchment.proxy.config.bloodstone_min, chunkZ * 16 + rand.nextInt(16)));
				for (int i = 0; i < Bewitchment.proxy.config.garnet_chance; i++) garnet.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.garnet_max - Bewitchment.proxy.config.garnet_min) + Bewitchment.proxy.config.garnet_min, chunkZ * 16 + rand.nextInt(16)));
				for (int i = 0; i < Bewitchment.proxy.config.jasper_chance; i++) jasper.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.jasper_max - Bewitchment.proxy.config.jasper_min) + Bewitchment.proxy.config.jasper_min, chunkZ * 16 + rand.nextInt(16)));
				for (int i = 0; i < Bewitchment.proxy.config.malachite_chance; i++) malachite.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.malachite_max - Bewitchment.proxy.config.malachite_min) + Bewitchment.proxy.config.malachite_min, chunkZ * 16 + rand.nextInt(16)));
				for (int i = 0; i < Bewitchment.proxy.config.nuummite_chance; i++) nuummite.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.nuummite_max - Bewitchment.proxy.config.nuummite_min) + Bewitchment.proxy.config.nuummite_min, chunkZ * 16 + rand.nextInt(16)));
				for (int i = 0; i < Bewitchment.proxy.config.tigers_eye_chance; i++) tigers_eye.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.tigers_eye_max - Bewitchment.proxy.config.tigers_eye_min) + Bewitchment.proxy.config.tigers_eye_min, chunkZ * 16 + rand.nextInt(16)));
				for (int i = 0; i < Bewitchment.proxy.config.tourmaline_chance; i++) tourmaline.generate(world, rand, new BlockPos(chunkX * 16 + rand.nextInt(16), rand.nextInt(Bewitchment.proxy.config.tourmaline_max - Bewitchment.proxy.config.tourmaline_min) + Bewitchment.proxy.config.tourmaline_min, chunkZ * 16 + rand.nextInt(16)));
			}
		}
	}
}