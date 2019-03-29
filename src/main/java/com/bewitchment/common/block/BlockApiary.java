package com.bewitchment.common.block;

import java.util.Random;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.CommonProxy.ModGui;
import com.bewitchment.common.block.tile.entity.TileEntityApiary;
import com.bewitchment.common.block.tile.entity.util.ModTileEntity;
import com.bewitchment.common.block.util.ModBlockContainer;
import com.bewitchment.registry.ModParticles;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockApiary extends ModBlockContainer
{
	private static final AxisAlignedBB BOX = new AxisAlignedBB(0.12, 0, 0.12, 0.88, 1, 0.88);
	
	public BlockApiary()
	{
		super(Bewitchment.instance, "apiary", Material.WOOD, SoundType.WOOD, 2, 2, "axe", 0, ModGui.APIARY.ordinal());
		setDefaultState(blockState.getBaseState().withProperty(BlockHorizontal.FACING, EnumFacing.SOUTH));
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityApiary();
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return BOX;
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing face, float hitX, float hitY, float hitZ, int meta, EntityLivingBase living, EnumHand hand)
	{
		return getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.fromAngle(living.rotationYaw));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
	{
		if (!ModTileEntity.isEmpty(((TileEntityApiary) world.getTileEntity(pos)).inventory)) Bewitchment.proxy.spawnParticle(ModParticles.BEE, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.HORIZONTALS[meta & 3]);
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