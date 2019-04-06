package com.bewitchment.common.block;

import java.util.Random;

import com.bewitchment.api.capability.extendedplayer.ExtendedPlayer;
import com.bewitchment.api.capability.extendedplayer.ExtendedPlayer.TransformationType;
import com.bewitchment.common.block.util.ModBlock;
import com.bewitchment.registry.ModObjects;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWitchFire extends ModBlock
{
	private static final AxisAlignedBB BOX = new AxisAlignedBB(0, 0, 0, 1, 0.0025, 1);
	
	private static final SoundType SOUND = new SoundType(0.6f, 0.9f, SoundEvents.BLOCK_FIRE_EXTINGUISH, null, null, null, null);
	
	public BlockWitchFire(String name)
	{
		super(name, Material.FIRE, SOUND, 0, 0, "", 0);
		setLightOpacity(0);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return BOX;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return NULL_AABB;
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face)
	{
		return BlockFaceShape.UNDEFINED;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public ItemStack getItem(World world, BlockPos pos, IBlockState state)
	{
		return world.getBlockState(pos.down()).getBlock().getItem(world, pos.down(), world.getBlockState(pos.down()));
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
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return this == ModObjects.witchfire ? 11 : this == ModObjects.endfire ? 2 : this == ModObjects.frostfire ? 7 : 15;
	}
	
	@Override
	public int quantityDropped(Random rand)
	{
		return 0;
	}
	
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos to, Block block, BlockPos from)
	{
		if (!world.getBlockState(to.down()).isFullBlock()) world.destroyBlock(to, false);
	}
	
	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
	{
		if (rand.nextBoolean()) world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + rand.nextDouble(), pos.getY() + rand.nextDouble(), pos.getZ() + rand.nextDouble(), 0, 0, 0);
	}
	
	@Override
	public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		if (entity instanceof EntityPlayer && entity.getCapability(ExtendedPlayer.CAPABILITY, null).getTransformation() == TransformationType.VAMPIRE) entity.attackEntityFrom(DamageSource.IN_FIRE, 1);
	}
}