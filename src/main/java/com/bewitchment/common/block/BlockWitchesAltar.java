package com.bewitchment.common.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.common.block.tile.entity.TileEntityPlacedItem;
import com.bewitchment.common.block.tile.entity.TileEntityWitchesAltar;
import com.bewitchment.common.block.util.ModBlockContainer;
import com.bewitchment.registry.ModObjects;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWitchesAltar extends ModBlockContainer
{
	public enum AltarType implements IStringSerializable
	{
		UNFORMED, CORNER, SIDE, TILE;
		
		@Override
		public String getName()
		{
			return name().toLowerCase();
		}
	}
	
	public static class PropertyAltar extends PropertyEnum<AltarType>
	{
		protected PropertyAltar(String name, Class<AltarType> valueClass, Collection<AltarType> allowedValues)
		{
			super(name, valueClass, allowedValues);
		}
	}
	
	public static final PropertyAltar TYPE = new PropertyAltar("type", AltarType.class, Arrays.asList(AltarType.values()));
	
	public static final PropertyInteger COLOR = PropertyInteger.create("color", 0, 16);
	
	public BlockWitchesAltar()
	{
		super(null, "witches_altar", Material.ROCK, SoundType.STONE, 2, 30, "pickaxe", 0, -1);
		setDefaultState(blockState.getBaseState().withProperty(TYPE, AltarType.UNFORMED).withProperty(COLOR, 16));
	}
	
	public static BlockPos getNearestAltar(World world, BlockPos pos)
	{
		for (int x = -16; x <= 16; x++)
		{
			for (int y = -16; y <= 16; y++)
			{
				for (int z = -16; z <= 16; z++)
				{
					TileEntity tile = world.getTileEntity(getAltarPosition(world, pos.add(x, y, z)));
					if (tile.hasCapability(MagicPower.CAPABILITY, null)) return getAltarPosition(world, pos.add(x, y, z));
				}
			}
		}
		return null;
	}
	
	public static BlockPos getAltarPosition(IBlockAccess world, BlockPos pos)
	{
		for (int x = -1; x <= 1; x++)
		{
			for (int z = -1; z <= 1; z++)
			{
				BlockPos pos0 = pos.add(x, 0, z);
				IBlockState state = world.getBlockState(pos0);
				if (state.getBlock() == ModObjects.witches_altar && state.getValue(TYPE) == AltarType.TILE) return pos0;
			}
		}
		return pos;
	}
	
	public static List<BlockPos> getAltarPositions(World world, BlockPos pos)
	{
		List<BlockPos> positions = new ArrayList<>();
		for (int x = -1; x <= 1; x++)
		{
			for (int z = -1; z <= 1; z++)
			{
				BlockPos pos0 = getAltarPosition(world, pos).add(x, 0, z);
				if (world.getBlockState(pos0).getBlock() == ModObjects.witches_altar) positions.add(pos0);
			}
		}
		return positions;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		for (BlockPos pos0 : getAltarPositions(world, pos)) world.setBlockState(pos0, getDefaultState());
		super.breakBlock(world, pos, state);
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos)
	{
		for (BlockPos pos0 : new BlockPos[] { pos.north(), pos.south(), pos.east(), pos.west(), pos.west().north(), pos.west().south(), pos.east().north(), pos.east().south()})
		{
			IBlockState state = world.getBlockState(pos0);
			if (state.getBlock() == ModObjects.witches_altar && state.getValue(TYPE) != AltarType.UNFORMED) return false;
		}
		return true;
	}
	
	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return meta == AltarType.TILE.ordinal() ? new TileEntityWitchesAltar() : null;
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(TYPE).ordinal();
	}
	
	@Override
	public EnumPushReaction getPushReaction(IBlockState state)
	{
		return EnumPushReaction.BLOCK;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(TYPE, AltarType.values()[meta]);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return state.getValue(TYPE) == AltarType.TILE;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state)
	{
		return true;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return true;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return true;
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing face, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			ItemStack stack = player.getHeldItem(hand);
			Item item = stack.getItem();
			if (face == EnumFacing.UP)
			{
				if (stack.getItem() == Item.getItemFromBlock(Blocks.CARPET) && !player.isSneaking())
				{
					if (state.getValue(TYPE) == AltarType.UNFORMED && !tryFormAltar(world, pos)) return false;
					TileEntityWitchesAltar tile = (TileEntityWitchesAltar) world.getTileEntity(getAltarPosition(world, pos));
					int new_color = stack.getMetadata(), old_color = tile.color;
					if (new_color != old_color)
					{
						tile.color = new_color;
						if (!player.isCreative()) stack.shrink(1);
					}
					return true;
				}
				if (world.getBlockState(pos.up()).getBlock().isReplaceable(world, pos.up()) && (TileEntityWitchesAltar.SWORD_MULTIPLIER_VALUES.containsKey(item) || TileEntityWitchesAltar.SWORD_RADIUS_VALUES.containsKey(item) || item == ModObjects.pentacle || item == Items.BUCKET || item == Items.GOLDEN_APPLE || item == ModObjects.demonic_heart || item == ModObjects.heart || item == Items.GOLDEN_CARROT || item == ModObjects.glass_jar || item == Items.NETHER_STAR))
				{
					world.setBlockState(pos.up(), ModObjects.placed_item.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.fromAngle(player.rotationYaw)));
					((TileEntityPlacedItem) world.getTileEntity(pos.up())).setStackInSlot(0, stack.splitStack(1));
					return true;
				}
			}
			if (hand == EnumHand.MAIN_HAND && stack.isEmpty() && world.getBlockState(pos).getValue(TYPE) != AltarType.UNFORMED)
			{
				TileEntityWitchesAltar tile = (TileEntityWitchesAltar) world.getTileEntity(getAltarPosition(world, pos));
				if (tile != null)
				{
					MagicPower cap = tile.getCapability(MagicPower.CAPABILITY, null);
					player.sendStatusMessage(new TextComponentString(cap.getAmount() + "/" + cap.getMaxAmount() + " (x" + tile.multiplier + ")"), true);
				}
				return true;
			}
		}
		return false;
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, TYPE, COLOR);
	}
	
	private boolean tryFormAltar(World world, BlockPos pos)
	{
		for (BlockPos pos0 : new BlockPos[] { pos.north().north().east(), pos.north().north().west(), pos.north().east().east(), pos.north().west().west(), pos.south().south().east(), pos.south().south().west(), pos.south().east().east(), pos.south().west().west() }) if (world.getBlockState(pos0).getBlock() == ModObjects.witches_altar && world.getBlockState(pos0).getValue(TYPE) == AltarType.UNFORMED && tryFormMultiblock(world, pos, pos0)) return true;
		if (tryFormMultiblock(world, pos.east(), pos.south().west())) return true;
		if (tryFormMultiblock(world, pos.east(), pos.north().west())) return true;
		if (tryFormMultiblock(world, pos.north(), pos.south().west())) return true;
		if (tryFormMultiblock(world, pos.north(), pos.south().east())) return true;
		return false;
	}
	
	private boolean tryFormMultiblock(World world, BlockPos pos0, BlockPos pos1)
	{
		ArrayList<BlockPos> blocks = new ArrayList<>(6);
		int y = pos0.getY();
		int sx = Math.min(pos0.getX(), pos1.getX());
		int ex = Math.max(pos0.getX(), pos1.getX());
		int sz = Math.min(pos0.getZ(), pos1.getZ());
		int ez = Math.max(pos0.getZ(), pos1.getZ());
		for (int i = sx - 1; i <= ex + 1; i++)
		{
			for (int j = sz - 1; j <= ez + 1; j++)
			{
				BlockPos checked = new BlockPos(i, y, j);
				if (!(world.getBlockState(checked).getBlock() == ModObjects.witches_altar && world.getBlockState(checked).getValue(TYPE) == AltarType.UNFORMED) && i >= sx && i <= ex && j >= sz && j <= ez) return false;
				blocks.add(checked);
				if (world.getBlockState(checked).getBlock() == ModObjects.witches_altar && (i < sx || i > ex || j < sz || j > ez)) return false;
			}
		}
		if (ex - sx < ez - sz)
		{
			world.setBlockState(new BlockPos(sx, y, sz + 1), ModObjects.witches_altar.getDefaultState().withProperty(TYPE, AltarType.TILE));
			world.setBlockState(new BlockPos(ex, y, sz + 1), ModObjects.witches_altar.getDefaultState().withProperty(TYPE, AltarType.SIDE));
		}
		else
		{
			world.setBlockState(new BlockPos(sx + 1, y, sz), ModObjects.witches_altar.getDefaultState().withProperty(TYPE, AltarType.TILE));
			world.setBlockState(new BlockPos(sx + 1, y, ez), ModObjects.witches_altar.getDefaultState().withProperty(TYPE, AltarType.SIDE));
		}
		world.setBlockState(new BlockPos(sx, y, sz), ModObjects.witches_altar.getDefaultState().withProperty(TYPE, AltarType.CORNER));
		world.setBlockState(new BlockPos(sx, y, ez), ModObjects.witches_altar.getDefaultState().withProperty(TYPE, AltarType.CORNER));
		world.setBlockState(new BlockPos(ex, y, sz), ModObjects.witches_altar.getDefaultState().withProperty(TYPE, AltarType.CORNER));
		world.setBlockState(new BlockPos(ex, y, ez), ModObjects.witches_altar.getDefaultState().withProperty(TYPE, AltarType.CORNER));
		return true;
	}
}