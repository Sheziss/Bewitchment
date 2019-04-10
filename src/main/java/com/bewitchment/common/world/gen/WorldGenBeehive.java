package com.bewitchment.common.world.gen;

import com.bewitchment.Bewitchment;
import com.bewitchment.registry.ModObjects;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenBeehive implements IWorldGenerator {
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator generator, IChunkProvider provider) {
		if (world.provider instanceof WorldProviderSurface && rand.nextFloat() < Bewitchment.proxy.config.beehive_chance) {
			int x = chunkX * 16 + rand.nextInt(16), z = chunkZ * 16 + rand.nextInt(16), y = world.getHeight(x, z) - 1;
			BlockPos pos = new BlockPos(x, y, z);
			if (world.getBlockState(pos).getBlock().isLeaves(world.getBlockState(pos), world, pos)) {
				while (pos.getY() > 0 && world.getBlockState(pos).getBlock().isLeaves(world.getBlockState(pos), world, pos))
					pos = pos.down();
				world.setBlockState(pos, ModObjects.beehive.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.HORIZONTALS[rand.nextInt(4)]));
			}
		}
	}
}