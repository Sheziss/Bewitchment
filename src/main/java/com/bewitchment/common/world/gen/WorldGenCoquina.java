package com.bewitchment.common.world.gen;

import java.util.Random;

import com.bewitchment.registry.ModObjects;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCoquina implements IWorldGenerator
{
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator generator, IChunkProvider provider)
	{
		BlockPos position = world.getHeight(new BlockPos(chunkX * 16 + rand.nextInt(16), 0, chunkZ * 16 + rand.nextInt(16)));
		if (BiomeDictionary.getBiomes(BiomeDictionary.Type.BEACH).contains(world.getBiome(position)) || BiomeDictionary.getBiomes(BiomeDictionary.Type.OCEAN).contains(world.getBiome(position)))
		{
			while (world.getBlockState(position.down()).getBlock() == Blocks.SAND)
			{
				for (int i = 0; i < 3; i++)
				{
					int x = rand.nextInt(2);
					int y = rand.nextInt(2);
					int z = rand.nextInt(2);
					for (BlockPos blockpos : BlockPos.getAllInBox(position.add(-x, -y, -z), position.add(x, y, z))) if (blockpos.distanceSq(position) <= Math.pow((x + y + z) * .333f + 0.5f, 2)) world.setBlockState(blockpos, ModObjects.coquina.getDefaultState(), 2);
					position = position.add(rand.nextInt(2) - 1, -rand.nextInt(2), rand.nextInt(2) - 1);
				}
				position = position.down();
			}
		}
	}
}