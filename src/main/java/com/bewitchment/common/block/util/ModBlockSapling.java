package com.bewitchment.common.block.util;

import com.bewitchment.common.world.gen.tree.util.WorldGenModTree;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class ModBlockSapling extends ModBlockBush implements IGrowable {
	public static final PropertyBool READY = PropertyBool.create("ready");

	private static final AxisAlignedBB BOX = new AxisAlignedBB(0.1, 0, 0.1, 0.9, 0.8, 0.9);

	private final Class<? extends WorldGenModTree> gen;

	public ModBlockSapling(String name, Class<? extends WorldGenModTree> gen, String... oreDictionaryNames) {
		super(name, oreDictionaryNames);
		setDefaultState(blockState.getBaseState().withProperty(READY, false));
		this.gen = gen;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		return BOX;
	}

	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
		return rand.nextFloat() < 0.45f;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		if (!state.getValue(READY)) world.setBlockState(pos, state.cycleProperty(READY));
		else {
			WorldGenModTree generator = null;
			try {
				generator = gen.getDeclaredConstructor(boolean.class).newInstance(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!world.isRemote && world.getBlockState(pos.up()).getBlock().canBeReplacedByLeaves(world.getBlockState(pos.up()), world, pos.up()) && generator.canSaplingGrow(world, pos))
				generator.generate(world, rand, pos);
		}
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote) {
			super.updateTick(world, pos, state, rand);
			if (world.isAreaLoaded(pos, 1) && world.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0)
				this.grow(world, rand, pos, state);
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(READY, meta == 0);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(READY) ? 1 : 0;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, READY);
	}
}