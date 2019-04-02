package com.bewitchment.common.block;

import java.util.Random;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.block.util.ModBlockFalling;
import com.bewitchment.common.entity.misc.EntityBeeSwarm;
import com.bewitchment.registry.ModObjects;
import com.bewitchment.registry.ModParticles;
import com.bewitchment.registry.ModSounds;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBeehive extends ModBlockFalling
{
	private static final AxisAlignedBB BOX = new AxisAlignedBB(0.18, 0, 0.18, 0.82, 1, 0.82);
	
	public BlockBeehive()
	{
		super("beehive", Material.GRASS, SoundType.GROUND, 1, 1, "shears", 0);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return BOX;
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face)
	{
		return BlockFaceShape.UNDEFINED;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return ModObjects.empty_honeycomb;
	}
	
	@Override
	public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face)
	{
		return true;
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
	public void onEndFalling(World world, BlockPos pos, IBlockState to, IBlockState from)
	{
		world.destroyBlock(pos, false);
		if (!world.isRemote)
		{
			Entity entity = new EntityBeeSwarm(world);
			entity.setPositionAndUpdate(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
			world.spawnEntity(entity);
		}
	}
	
	@Override
	public int quantityDropped(Random rand)
	{
		return rand.nextInt(5);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
	{
		if (rand.nextInt(10) == 0) Bewitchment.proxy.spawnParticle(ModParticles.BEE, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
		if (rand.nextInt(25) == 0) world.playSound(null, pos, ModSounds.BUZZ, SoundCategory.BLOCKS, 0.2f, 1);
	}
	
	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (!world.isRemote)
		{
			IBlockState above = world.getBlockState(pos.up());
			if (!above.getBlock().isLeaves(above, world, pos.up()) && world.isAirBlock(pos.down()))
			{
				if (!fallInstantly)
				{
					EntityFallingBlock entity = new EntityFallingBlock(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, world.getBlockState(pos));
					entity.setHurtEntities(false);
					entity.shouldDropItem = false;
					world.spawnEntity(entity);
				}
			}
		}
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing face, float hitX, float hitY, float hitZ, int meta, EntityLivingBase living, EnumHand hand)
	{
		return getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.fromAngle(living.rotationYaw));
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