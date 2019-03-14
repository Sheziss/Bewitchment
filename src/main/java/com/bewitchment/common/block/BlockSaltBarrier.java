package com.bewitchment.common.block;

import java.util.List;
import java.util.Random;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.capability.transformation.Transformation;
import com.bewitchment.common.block.util.ModBlock;
import com.bewitchment.registry.ModObjects;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSaltBarrier extends ModBlock
{
	protected enum EnumAttachPosition implements IStringSerializable
	{
		UP("up"), SIDE("side"), NONE("none");
		
		private final String name;
		
		EnumAttachPosition(String name)
		{
			this.name = name;
		}
		
		@Override
		public String getName()
		{
			return name;
		}
		
		@Override
		public String toString()
		{
			return name;
		}
	}
	
	public static final PropertyEnum<EnumAttachPosition> NORTH = PropertyEnum.<EnumAttachPosition>create("north", EnumAttachPosition.class), EAST = PropertyEnum.<EnumAttachPosition>create("east", EnumAttachPosition.class), SOUTH = PropertyEnum.<EnumAttachPosition>create("south", EnumAttachPosition.class), WEST = PropertyEnum.<EnumAttachPosition>create("west", EnumAttachPosition.class);
	private static final AxisAlignedBB[] WIRE_AABB = { new AxisAlignedBB(0.1875, 0, 0.1875, 0.8125, 0.0625, 0.8125), new AxisAlignedBB(0.1875, 0, 0.1875, 0.8125, 0.0625, 1), new AxisAlignedBB(0, 0, 0.1875, 0.8125, 0.0625, 0.8125), new AxisAlignedBB(0, 0, 0.1875, 0.8125, 0.0625, 1), new AxisAlignedBB(0.1875, 0, 0, 0.8125, 0.0625, 0.8125), new AxisAlignedBB(0.1875, 0, 0, 0.8125, 0.0625, 1), new AxisAlignedBB(0, 0, 0, 0.8125, 0.0625, 0.8125), new AxisAlignedBB(0, 0, 0, 0.8125, 0.0625, 1), new AxisAlignedBB(0.1875, 0, 0.1875, 1, 0.0625, 0.8125), new AxisAlignedBB(0.1875, 0, 0.1875, 1, 0.0625, 1), new AxisAlignedBB(0, 0, 0.1875, 1, 0.0625, 0.8125), new AxisAlignedBB(0, 0, 0.1875, 1, 0.0625, 1), new AxisAlignedBB(0.1875, 0, 0, 1, 0.0625, 0.8125), new AxisAlignedBB(0.1875, 0, 0, 1, 0.0625, 1), new AxisAlignedBB(0, 0, 0, 1, 0.0625, 0.8125), new AxisAlignedBB(0, 0, 0, 1, 0.0625, 1) };
	
	private static final AxisAlignedBB WALL = new AxisAlignedBB(0, -5, 0, 1, 5, 1);
	
	public BlockSaltBarrier()
	{
		super("salt_barrier", Material.CIRCUITS, SoundType.CLOTH, 0, 0, "", 0);
		setCreativeTab(null);
		setDefaultState(blockState.getBaseState().withProperty(NORTH, EnumAttachPosition.NONE).withProperty(EAST, EnumAttachPosition.NONE).withProperty(SOUTH, EnumAttachPosition.NONE).withProperty(WEST, EnumAttachPosition.NONE));
	}
	
	private static int getAABBIndex(IBlockState state)
	{
		int i = 0;
		boolean north = state.getValue(NORTH) != EnumAttachPosition.NONE;
		boolean east = state.getValue(EAST) != EnumAttachPosition.NONE;
		boolean south = state.getValue(SOUTH) != EnumAttachPosition.NONE;
		boolean west = state.getValue(WEST) != EnumAttachPosition.NONE;
		if (north || south && !east && !west) i |= 1 << EnumFacing.NORTH.getHorizontalIndex();
		if (east || west && !north && !south) i |= 1 << EnumFacing.EAST.getHorizontalIndex();
		if (south || north && !east && !west) i |= 1 << EnumFacing.SOUTH.getHorizontalIndex();
		if (west || east && !north && !south) i |= 1 << EnumFacing.WEST.getHorizontalIndex();
		return i;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB box, List<AxisAlignedBB> boxes, Entity entity, boolean wut)
	{
		if (entity instanceof EntityLivingBase)
		{
			EnumCreatureAttribute attribute = ((EntityLivingBase) entity).getCreatureAttribute();
			if (attribute == EnumCreatureAttribute.UNDEAD || attribute == BewitchmentAPI.DEMON || attribute == BewitchmentAPI.SPIRIT)
				addCollisionBoxToList(pos, box, boxes, WALL);
		}
		if (entity instanceof EntityBlaze || entity instanceof EntityGhast || entity instanceof EntityVex /* || entity instanceof EntityBatSwarm */) addCollisionBoxToList(pos, box, boxes, WALL);
		if (entity instanceof EntityPlayer && !((EntityPlayer) entity).isCreative() && !entity.getCapability(Transformation.CAPABILITY, null).getTransformation().canCrossSalt) addCollisionBoxToList(pos, box, boxes, WALL);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos)
	{
		return world.getBlockState(pos.down()).isTopSolid() || world.getBlockState(pos.down()).getBlock() == Blocks.GLOWSTONE;
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face)
	{
		return BlockFaceShape.UNDEFINED;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return WIRE_AABB[getAABBIndex(state.getActualState(world, pos))];
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return NULL_AABB;
	}
	
	@Override
	public ItemStack getItem(World world, BlockPos pos, IBlockState state)
	{
		return new ItemStack(getItemDropped(state, world.rand, 0));
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return ModObjects.salt;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.CUTOUT;
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
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos from)
	{
		if (!world.isRemote && !canPlaceBlockAt(world, pos))
		{
			dropBlockAsItem(world, pos, state, 0);
			world.setBlockToAir(pos);
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirror)
	{
		return mirror == Mirror.LEFT_RIGHT ? state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(SOUTH, state.getValue(NORTH)) : mirror == Mirror.FRONT_BACK ? state.withProperty(EAST, state.getValue(WEST)).withProperty(WEST, state.getValue(EAST)) : super.withMirror(state, mirror);
	}
	
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot)
	{
		return rot == Rotation.CLOCKWISE_180 ? state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(EAST, state.getValue(WEST)) .withProperty(SOUTH, state.getValue(NORTH)).withProperty(WEST, state.getValue(EAST)) : rot == Rotation.COUNTERCLOCKWISE_90 ? state.withProperty(NORTH, state.getValue(EAST)).withProperty(EAST, state.getValue(SOUTH)).withProperty(SOUTH, state.getValue(WEST)).withProperty(WEST, state.getValue(NORTH)) : rot == Rotation.CLOCKWISE_90 ? state.withProperty(NORTH, state.getValue(WEST)).withProperty(EAST, state.getValue(NORTH)).withProperty(SOUTH, state.getValue(EAST)).withProperty(WEST, state.getValue(SOUTH)) : state;
	}
	
	protected boolean canConnectTo(IBlockState state, EnumFacing face, IBlockAccess world, BlockPos pos)
	{
		return state.getBlock() == this;
	}
	
	protected boolean canConnectUpwardsTo(IBlockAccess world, BlockPos pos)
	{
		return canConnectTo(world.getBlockState(pos), null, world, pos);
	}
	
	protected EnumAttachPosition getAttachPosition(IBlockAccess world, BlockPos pos, EnumFacing face)
	{
		BlockPos pos0 = pos.offset(face);
		IBlockState state = world.getBlockState(pos.offset(face));
		if (!canConnectTo(world.getBlockState(pos0), face, world, pos0) && (state.isNormalCube() || !canConnectUpwardsTo(world, pos0.down()))) return !world.getBlockState(pos.up()).isNormalCube() && (world.getBlockState(pos0).isSideSolid(world, pos0, EnumFacing.UP) || world.getBlockState(pos0).getBlock() == Blocks.GLOWSTONE) && canConnectUpwardsTo(world, pos0.up()) ? state.isBlockNormalCube() ? EnumAttachPosition.UP : EnumAttachPosition.SIDE : EnumAttachPosition.NONE;
		return EnumAttachPosition.SIDE;
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return state.withProperty(NORTH, getAttachPosition(world, pos, EnumFacing.NORTH)).withProperty(SOUTH, getAttachPosition(world, pos, EnumFacing.SOUTH)).withProperty(EAST, getAttachPosition(world, pos, EnumFacing.EAST)).withProperty(WEST, getAttachPosition(world, pos, EnumFacing.WEST));
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState();
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return 0;
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, NORTH, EAST, SOUTH, WEST);
	}
}