package com.bewitchment.common.block.crop;

import java.util.Random;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.registry.ModItems;

import moriyashiine.froglib.common.block.FLBlockCrop;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCropKenaf extends FLBlockCrop
{
	private static final AxisAlignedBB[] KENAF_AABB = {new AxisAlignedBB(0, 0, 0, 1, 0.125, 1), new AxisAlignedBB(0, 0, 0, 1, 0.375, 1), new AxisAlignedBB(0, 0, 0, 1, 0.625, 1), new AxisAlignedBB(0, 0, 0, 1, 0.75, 1), new AxisAlignedBB(0, 0, 0, 1, 1, 1)};
	
	public BlockCropKenaf()
	{
		super(Bewitchment.MOD_ID, "crop_kenaf", Bewitchment.proxy.tab, ModItems.seed_kenaf, new ItemStack(ModItems.kenaf), 4);
		this.setDefaultState(blockState.getBaseState().withProperty(AGE, 0).withProperty(TOP, false));
	}
	
	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		super.updateTick(world, pos, state, rand);
		if (rand.nextInt(5) == 0) grow(world, pos, state);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return KENAF_AABB[state.getValue(AGE)];
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos.down());
		return state.getBlock().canSustainPlant(state, world, pos, EnumFacing.UP, this) || state.getBlock() == this;
	}
	
	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
	{
		Block block = world.getBlockState(pos.down()).getBlock();
		return block == this || getSeed().soil.contains(block) ? true : canPlaceBlockAt(world, pos);
	}
	
	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
    {
        return state.getValue(TOP) ? super.canGrow(world, pos, state, isClient) : !state.getValue(TOP) && world.getBlockState(pos.up()).getBlock() != this;
    }
	
	@Override
	public void grow(World world, BlockPos pos, IBlockState state)
    {
		if (isMaxAge(state) && world.isAirBlock(pos.up()) && !state.getValue(TOP)) world.setBlockState(pos.up(), (world.getBlockState(pos.down()).getBlock() == this ? getDefaultState().withProperty(TOP, true) : getDefaultState()));
		else world.setBlockState(pos, getDefaultState().withProperty(AGE, Math.min(state.getValue(AGE) + getBonemealAgeIncrease(world), getMaxAge())).withProperty(TOP, state.getValue(TOP)));
    }
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(AGE, meta & 7).withProperty(TOP, (meta & 8) > 0);
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(AGE) | ((state.getValue(TOP) ? 1 : 0) << 3);
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, AGE, TOP);
	}
}