package com.bewitchment.registry.block.tile;

import com.bewitchment.core.Bewitchment;
import com.bewitchment.core.CommonProxy.ModGui;
import com.bewitchment.registry.block.ModBlock;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

public class BlockDistillery extends ModBlock implements ITileEntityProvider
{
	private static final AxisAlignedBB BBOX_X = new AxisAlignedBB(0.125, 0, 0, 0.875, 0.6875, 1), BBOX_Z = new AxisAlignedBB(0, 0, 0.125, 1, 0.6875, 0.875);
	
	public BlockDistillery(String name)
	{
		super(name, Material.IRON, SoundType.METAL, 5, 5, "pickaxe", 0);
		this.setDefaultState(blockState.getBaseState().withProperty(BlockHorizontal.FACING, EnumFacing.SOUTH));
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityDistillery();
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing face, float hitX, float hitY, float hitZ)
	{
		player.openGui(Bewitchment.instance, ModGui.DISTILLERY.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		if (!world.isRemote && world.getGameRules().getBoolean("doTileDrops") && hasTileEntity(state) && world.getTileEntity(pos) instanceof IItemHandler) for (int i = 0; i < ((IItemHandler)world.getTileEntity(pos)).getSlots(); i++) InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), ((IItemHandler)world.getTileEntity(pos)).getStackInSlot(i));
		super.breakBlock(world, pos, state);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return state.getValue(BlockHorizontal.FACING).getAxis() == Axis.Z ? BBOX_X : BBOX_Z;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return state.getValue(BlockHorizontal.FACING).getAxis() == Axis.Z ? BBOX_X : BBOX_Z;
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face)
	{
		return BlockFaceShape.UNDEFINED;
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing face, float hitX, float hitY, float hitZ, int meta, EntityLivingBase entity, EnumHand hand)
	{
		return getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.fromAngle(entity.rotationYaw));
	}
	
	@Override
	public boolean isFullBlock(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
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