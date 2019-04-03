package com.bewitchment.common.block;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.CommonProxy.ModGui;
import com.bewitchment.common.block.tile.entity.TileEntityTarotTable;
import com.bewitchment.common.block.util.ModBlockContainer;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTarotTable extends ModBlockContainer
{
	public BlockTarotTable()
	{
		super(Bewitchment.instance, "tarot_table", Material.ROCK, SoundType.STONE, 3, 3, "pickaxe", 0, ModGui.TAROT.ordinal());
		setDefaultState(blockState.getBaseState().withProperty(BlockHorizontal.FACING, EnumFacing.SOUTH));
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityTarotTable();
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing face, float hitX, float hitY, float hitZ, int meta, EntityLivingBase entity, EnumHand hand)
	{
		return getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.fromAngle(entity.rotationYaw));
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.HORIZONTALS[meta]);
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(BlockHorizontal.FACING).getHorizontalIndex();
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, BlockHorizontal.FACING);
	}
}