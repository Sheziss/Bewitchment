package com.bewitchment.common.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.common.block.tile.entity.TileEntityPlacedItem;
import com.bewitchment.common.block.tile.entity.TileEntityWitchesAltar;
import com.bewitchment.common.block.util.ModBlockContainer;
import com.bewitchment.registry.ModObjects;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWitchesAltar extends ModBlockContainer
{
	public static final PropertyAltar TYPE = new PropertyAltar("type", AltarType.class, Arrays.asList(AltarType.values()));
	
	public BlockWitchesAltar(String color)
	{
		super(null, "witches_altar" + (color.isEmpty() ? "" : "_" + color), Material.ROCK, SoundType.STONE, 2, 30, "pickaxe", 0, -1);
		setTranslationKey(new ResourceLocation(Bewitchment.MOD_ID, "witches_altar").toString().replace(":", "."));
		setDefaultState(blockState.getBaseState().withProperty(TYPE, AltarType.UNFORMED));
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return meta == AltarType.TILE.ordinal() ? new TileEntityWitchesAltar() : null;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return state.getValue(TYPE) == AltarType.TILE;
	}
	
	@Override
	public EnumPushReaction getPushReaction(IBlockState state)
	{
		return EnumPushReaction.BLOCK;
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(ModObjects.witches_altar_unformed);
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos)
	{
		for (BlockPos pos0 : new BlockPos[] {pos.north(), pos.south(), pos.east(), pos.west(), pos.west().north(), pos.west().south(), pos.east().north(), pos.east().south()})
		{
			IBlockState state = world.getBlockState(pos0);
			if (state.getBlock() instanceof BlockWitchesAltar && state.getValue(TYPE) != AltarType.UNFORMED) return false;
		}
		return true;
	}
	
	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		return false;
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
				if (item == Item.getItemFromBlock(Blocks.CARPET) && !player.isSneaking())
				{
					int color = stack.getMetadata();
					if (state.getValue(TYPE) == AltarType.UNFORMED && !tryFormAltar(world, pos, color)) return false;
					Block altar = getAltarWithColor(color);
					refreshAltarContainers(world, pos);
					if (world.getBlockState(pos).getBlock() != altar)
					{
						TileEntityWitchesAltar tile = (TileEntityWitchesAltar) world.getTileEntity(getAltarPosition(world, pos));
						for (BlockPos pos0 : getAltarPositions(world, pos)) world.setBlockState(pos0, altar.getDefaultState().withProperty(TYPE, world.getBlockState(pos0).getValue(TYPE)));
						if (tile != null)
						{
							int amount = 0, maxAmount = 0;
							amount = tile.magic_power.getAmount();
							maxAmount = tile.magic_power.getMaxAmount();
							tile = (TileEntityWitchesAltar) world.getTileEntity(getAltarPosition(world, pos));
							tile.magic_power.setAmount(amount);
							tile.magic_power.setMaxAmount(maxAmount);
						}
						if (!player.isCreative()) stack.shrink(1);
					}
				}
				else if (world.getBlockState(pos.up()).getBlock().isReplaceable(world, pos.up()) && (TileEntityWitchesAltar.SWORD_MULTIPLIER_VALUES.containsKey(item) || TileEntityWitchesAltar.SWORD_RADIUS_VALUES.containsKey(item) || item == ModObjects.pentacle || item == Items.BUCKET || item == Items.GOLDEN_APPLE || item == ModObjects.demonic_heart || item == ModObjects.heart || item == Items.GOLDEN_CARROT || item == ModObjects.glass_jar || item == Items.NETHER_STAR))
				{
					world.setBlockState(pos.up(), ModObjects.placed_item.getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.fromAngle(player.rotationYaw)));
					((TileEntityPlacedItem) world.getTileEntity(pos.up())).inventory.setStackInSlot(0, stack.splitStack(1));
				}
			}
			if (hand == EnumHand.MAIN_HAND && stack.isEmpty() && world.getBlockState(pos).getValue(TYPE) != AltarType.UNFORMED)
			{
				TileEntityWitchesAltar tile = (TileEntityWitchesAltar) world.getTileEntity(getAltarPosition(world, pos));
				MagicPower cap = tile.getCapability(MagicPower.CAPABILITY, null);
				player.sendStatusMessage(new TextComponentString(cap.getAmount() + "/" + cap.getMaxAmount() + " (x" + tile.multiplier + ")"), true);
			}
			return true;
		}
		return false;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		if (state.getValue(TYPE) == AltarType.TILE) refreshAltarContainers(world, pos);
		if (getAltarPositions(world, pos).size() < 6) for (BlockPos pos0 : getAltarPositions(world, pos)) world.setBlockState(pos0, ModObjects.witches_altar_unformed.getDefaultState());
		super.breakBlock(world, pos, state);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(TYPE, AltarType.values()[meta]);
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(TYPE).ordinal();
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, TYPE);
	}
	
	public static BlockPos getAltarPosition(IBlockAccess world, BlockPos pos)
	{
		for (int x = -1; x <= 1; x++)
		{
			for (int z = -1; z <= 1; z++)
			{
				BlockPos pos0 = pos.add(x, 0, z);
				IBlockState state = world.getBlockState(pos0);
				if (state.getBlock() instanceof BlockWitchesAltar && state.getValue(TYPE) == AltarType.TILE) return pos0;
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
				if (world.getBlockState(pos0).getBlock() instanceof BlockWitchesAltar) positions.add(pos0);
			}
		}
		return positions;
	}
	
	public static BlockPos getNearestAltar(World world, BlockPos pos)
	{
		if (pos != null)
		{
			for (BlockPos pos0 : BlockPos.getAllInBoxMutable(pos.add(-8, -8, -8), pos.add(8, 8, 8)))
			{
				IBlockState state = world.getBlockState(getAltarPosition(world, pos0));
				if (state.getBlock() instanceof BlockWitchesAltar && state.getValue(TYPE) == AltarType.TILE) return getAltarPosition(world, pos0);
			}
		}
		return null;
	}
	
	private void refreshAltarContainers(World world, BlockPos pos)
	{
		for (BlockPos pos0 : BlockPos.getAllInBoxMutable(pos.add(-8, -8, -8), pos.add(8, 8, 8)))
		{
			if (world.getBlockState(pos0).getBlock() instanceof ModBlockContainer) ((ModBlockContainer) world.getBlockState(pos0).getBlock()).refreshAltarPos(world, pos0);;
		}
	}
	
	private Block getAltarWithColor(int color)
	{
		for (Object obj : ModObjects.REGISTRY)
		{
			if (obj instanceof BlockWitchesAltar)
			{
				Block altar = (BlockWitchesAltar) obj;
				if (altar.getRegistryName().toString().replace("light_gray", "silver").substring(25).replaceFirst("_", "").equals(EnumDyeColor.values()[color].getName())) return altar;
			}
		}
		return ModObjects.witches_altar_unformed;
	}
	
	private boolean tryFormAltar(World world, BlockPos pos, int color)
	{
		for (BlockPos pos0 : new BlockPos[] { pos.north().north().east(), pos.north().north().west(), pos.north().east().east(), pos.north().west().west(), pos.south().south().east(), pos.south().south().west(), pos.south().east().east(), pos.south().west().west() }) if (world.getBlockState(pos0).getBlock() instanceof BlockWitchesAltar && world.getBlockState(pos0).getValue(TYPE) == AltarType.UNFORMED && tryFormMultiblock(world, pos, pos0, color)) return true;
		if (tryFormMultiblock(world, pos.east(), pos.south().west(), color)) return true;
		if (tryFormMultiblock(world, pos.east(), pos.north().west(), color)) return true;
		if (tryFormMultiblock(world, pos.north(), pos.south().west(), color)) return true;
		if (tryFormMultiblock(world, pos.north(), pos.south().east(), color)) return true;
		return false;
	}
	
	private boolean tryFormMultiblock(World world, BlockPos pos0, BlockPos pos1, int color)
	{
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
				if (!(world.getBlockState(checked).getBlock() instanceof BlockWitchesAltar && world.getBlockState(checked).getValue(TYPE) == AltarType.UNFORMED) && i >= sx && i <= ex && j >= sz && j <= ez) return false;
				if (world.getBlockState(checked).getBlock() instanceof BlockWitchesAltar && (i < sx || i > ex || j < sz || j > ez)) return false;
			}
		}
		if (ex - sx < ez - sz)
		{
			world.setBlockState(new BlockPos(sx, y, sz + 1), getAltarWithColor(color).getDefaultState().withProperty(TYPE, AltarType.TILE));
			world.setBlockState(new BlockPos(ex, y, sz + 1), getAltarWithColor(color).getDefaultState().withProperty(TYPE, AltarType.SIDE));
		}
		else
		{
			world.setBlockState(new BlockPos(sx + 1, y, sz), getAltarWithColor(color).getDefaultState().withProperty(TYPE, AltarType.TILE));
			world.setBlockState(new BlockPos(sx + 1, y, ez), getAltarWithColor(color).getDefaultState().withProperty(TYPE, AltarType.SIDE));
		}
		world.setBlockState(new BlockPos(sx, y, sz), getAltarWithColor(color).getDefaultState().withProperty(TYPE, AltarType.CORNER));
		world.setBlockState(new BlockPos(sx, y, ez), getAltarWithColor(color).getDefaultState().withProperty(TYPE, AltarType.CORNER));
		world.setBlockState(new BlockPos(ex, y, sz), getAltarWithColor(color).getDefaultState().withProperty(TYPE, AltarType.CORNER));
		world.setBlockState(new BlockPos(ex, y, ez), getAltarWithColor(color).getDefaultState().withProperty(TYPE, AltarType.CORNER));
		return true;
	}
	
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
}