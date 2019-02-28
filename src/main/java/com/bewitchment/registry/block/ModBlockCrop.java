package com.bewitchment.registry.block;

import java.util.Random;

import com.bewitchment.core.Bewitchment;
import com.bewitchment.registry.ModBlocks;
import com.bewitchment.registry.item.ModItemSeed;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ModBlockCrop extends BlockCrops
{
	private static final PropertyBool TOP = PropertyBool.create("top");
	
	private int maxAge;
	private ModItemSeed seed;
	private Item crop;
	
	public ModBlockCrop(String name, int maxAge)
	{
		super();
		this.setRegistryName(new ResourceLocation(Bewitchment.MOD_ID, name));
		this.setTranslationKey(this.getRegistryName().toString());
		this.setCreativeTab(null);
		this.maxAge = maxAge;
		ModBlocks.REGISTRY.add(this);
	}
	
	@Override
	public int getMaxAge()
	{
		return maxAge;
	}
	
	@Override
	public ModItemSeed getSeed()
	{
		return seed;
	}
	
	public ModBlockCrop setSeed(ModItemSeed seed)
	{
		this.seed = seed;
		return this;
	}
	
	@Override
	public Item getCrop()
	{
		return crop;
	}
	
	public ModBlockCrop setCrop(Item crop)
	{
		this.crop = crop;
		return this;
	}
	
	public static class Belladonna extends ModBlockCrop
	{		
		public Belladonna(String name, int maxAge)
		{
			super(name, maxAge);
		}
		
		@Override
		public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
		{
			super.updateTick(world, pos, state, rand);
			if (isMaxAge(state) && rand.nextInt(10) <= 3)
			{
				int i = rand.nextInt(4);
				BlockPos pos0 = i == 0 ? pos.north() : i == 1 ? pos.south() : i == 2 ? pos.east() : pos.west();
				if (world.getBlockState(pos0.down()).getBlock().canSustainPlant(state, world, pos, EnumFacing.UP, this) && getSeed().soil.contains(world.getBlockState(pos0.down()).getBlock()) && world.isAirBlock(pos0)) world.setBlockState(pos0, getDefaultState());
			}
		}
	}
	
	public static class Kelp extends ModBlockCrop
	{
		private static final AxisAlignedBB[] KELP_AABB = {new AxisAlignedBB(0, 0, 0, 1, 0.125, 1), new AxisAlignedBB(0, 0, 0, 1, 0.25, 1), new AxisAlignedBB(0, 0, 0, 1, 0.375, 1), new AxisAlignedBB(0, 0, 0, 1, 0.5, 1), new AxisAlignedBB(0, 0, 0, 1, 0.75, 1), new AxisAlignedBB(0, 0, 0, 1, 0.95, 1), new AxisAlignedBB(0, 0, 0, 1, 1, 1), new AxisAlignedBB(0, 0, 0, 1, 1, 1)};
		
		public Kelp(String name, int maxAge)
		{
			super(name, maxAge);
		}
		
		@Override
		public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
		{
			super.updateTick(world, pos, state, rand);
			if (rand.nextInt(2) == 0) grow(world, pos, state);
		}
		
		@Override
		public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
		{
			return KELP_AABB[state.getValue(AGE)];
		}
		
		@Override
		public Material getMaterial(IBlockState state)
		{
			return Material.WATER;
		}
		
		@Override
		public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
		{
			Block block = world.getBlockState(pos.down()).getBlock();
			return block == this || getSeed().soil.contains(block) ? true : canPlaceBlockAt(world, pos);
		}
		
		@Override
		public boolean canPlaceBlockAt(World world, BlockPos pos)
		{
			return world.getBlockState(pos.up()).getMaterial() == Material.WATER && world.getBlockState(pos.up(2)).getMaterial() == Material.WATER;
		}
		
		@Override
		public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
	    {
	        return world.getBlockState(pos.up()).getBlock() != this && (isMaxAge(state) ? world.getBlockState(pos.up(2)).getMaterial() == Material.WATER : true);
	    }
		
		@Override
		public boolean isReplaceable(IBlockAccess world, BlockPos pos)
		{
			return false;
		}
		
		@Override
		public void grow(World world, BlockPos pos, IBlockState state)
	    {
			if (isMaxAge(state) && world.getBlockState(pos.up()).getBlock() == Blocks.WATER && world.getBlockState(pos.up(2)).getBlock() == Blocks.WATER) world.setBlockState(pos.up(), getDefaultState());
			else super.grow(world, pos, state);
	    }
		
		@Override
		public void onPlayerDestroy(World world, BlockPos pos, IBlockState state)
		{
			world.setBlockState(pos, Blocks.WATER.getDefaultState());
		}
		
		@Override
		protected void checkAndDropBlock(World world, BlockPos pos, IBlockState state)
		{
			if (!canBlockStay(world, pos, state))
			{
				this.dropBlockAsItem(world, pos, state, 0);
				world.setBlockState(pos, Blocks.WATER.getDefaultState());
			}
		}
		
		@Override
		protected BlockStateContainer createBlockState()
		{
			return new BlockStateContainer(this, AGE, BlockLiquid.LEVEL);
		}
	}
	
	public static class Kenaf extends ModBlockCrop
	{
		private static final AxisAlignedBB[] KENAF_AABB = {new AxisAlignedBB(0, 0, 0, 1, 0.125, 1), new AxisAlignedBB(0, 0, 0, 1, 0.375, 1), new AxisAlignedBB(0, 0, 0, 1, 0.625, 1), new AxisAlignedBB(0, 0, 0, 1, 0.75, 1), new AxisAlignedBB(0, 0, 0, 1, 1, 1)};
		
		public Kenaf(String name, int maxAge)
		{
			super(name, maxAge);
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
	
	public static class Mint extends ModBlockCrop
	{
		public Mint(String name, int maxAge)
		{
			super(name, maxAge);
		}
		
		@Override
		public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
		{
			super.updateTick(world, pos, state, rand);
			if (isMaxAge(state) && rand.nextInt(10) <= 3)
			{
				int i = rand.nextInt(4);
				BlockPos pos0 = i == 0 ? pos.north() : i == 1 ? pos.south() : i == 2 ? pos.east() : pos.west();
				if (world.getBlockState(pos0.down()).getBlock().canSustainPlant(state, world, pos, EnumFacing.UP, this) && getSeed().soil.contains(world.getBlockState(pos0.down()).getBlock()) && world.isAirBlock(pos0)) world.setBlockState(pos0, getDefaultState());
			}
		}
	}
	
	public static class Silphium extends ModBlockCrop
	{
		private static final AxisAlignedBB[] SILPHIUM_AABB_BOTTOM = {new AxisAlignedBB(0, 0, 0, 1, 0.125, 1), new AxisAlignedBB(0, 0, 0, 1, 0.375, 1), new AxisAlignedBB(0, 0, 0, 1, 0.5, 1), new AxisAlignedBB(0, 0, 0, 1, 0.625, 1), new AxisAlignedBB(0, 0, 0, 1, 0.75, 1), new AxisAlignedBB(0, 0, 0, 1, 1, 1)};
		private static final AxisAlignedBB[] SILPHIUM_AABB_TOP = {new AxisAlignedBB(0, 0, 0, 1, 0.125, 1), new AxisAlignedBB(0, 0, 0, 1, 0.375, 1), new AxisAlignedBB(0, 0, 0, 1, 0.5, 1), new AxisAlignedBB(0, 0, 0, 1, 0.5, 1), new AxisAlignedBB(0, 0, 0, 1, 0.55, 1), new AxisAlignedBB(0, 0, 0, 1, 0.625, 1)};
		
		public Silphium(String name, int maxAge)
		{
			super(name, maxAge);
			this.setDefaultState(blockState.getBaseState().withProperty(AGE, 0).withProperty(TOP, false));
		}
				
		@Override
		public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
		{
			checkAndDropBlock(world, pos, state);
			if (rand.nextBoolean() && world.getBiome(pos).canRain()) grow(world, pos, state);
		}
		
		@Override
		public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
		{
			return state.getValue(TOP) ? SILPHIUM_AABB_TOP[state.getValue(AGE)] : SILPHIUM_AABB_BOTTOM[state.getValue(AGE)];
		}
		
		@Override
		public Item getItemDropped(IBlockState state, Random rand, int fortune)
		{
			return state.getValue(TOP) && isMaxAge(state) ? getCrop() : getSeed();
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
	        return state.getValue(TOP) ? super.canGrow(world, pos, state, isClient) : world.getBlockState(pos.up()).getBlock() != this;
	    }
		
		@Override
		public void grow(World world, BlockPos pos, IBlockState state)
	    {
			if (isMaxAge(state) && world.isAirBlock(pos.up()) && !state.getValue(TOP)) world.setBlockState(pos.up(), getDefaultState().withProperty(TOP, true));
			else world.setBlockState(pos, getDefaultState().withProperty(AGE, Math.min(state.getValue(AGE) + getBonemealAgeIncrease(world), getMaxAge())).withProperty(TOP, state.getValue(TOP)));
	    }
		
		@Override
		public void breakBlock(World world, BlockPos pos, IBlockState state)
		{
			super.breakBlock(world, pos, state);
			if (state.getValue(TOP)) world.setBlockToAir(pos.down());
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
	
	public static class Thistle extends ModBlockCrop
	{
		public Thistle(String name, int maxAge)
		{
			super(name, maxAge);
		}
		
		@Override
		public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
		{
			super.updateTick(world, pos, state, rand);
			if (isMaxAge(state) && rand.nextInt(10) <= 3)
			{
				int i = rand.nextInt(4);
				BlockPos pos0 = i == 0 ? pos.north() : i == 1 ? pos.south() : i == 2 ? pos.east() : pos.west();
				if (world.getBlockState(pos0.down()).getBlock().canSustainPlant(state, world, pos, EnumFacing.UP, this) && getSeed().soil.contains(world.getBlockState(pos0.down()).getBlock()) && world.isAirBlock(pos0)) world.setBlockState(pos0, getDefaultState());
			}
		}
		
		@Override
		public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity)
		{
			if (entity instanceof EntityLivingBase) entity.attackEntityFrom(DamageSource.CACTUS, 0.5f);
		}
	}
}