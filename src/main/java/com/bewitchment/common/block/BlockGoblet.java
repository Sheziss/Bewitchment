package com.bewitchment.common.block;

import com.bewitchment.common.block.util.ModBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockGoblet extends ModBlock {
	public static final PropertyBool FULL = PropertyBool.create("full");

	private static final AxisAlignedBB BOX = new AxisAlignedBB(0.375, 0, 0.375, 0.625, 0.375, 0.625);

	public BlockGoblet() {
		super("goblet", Material.IRON, SoundType.METAL, 0.3f, 1, "pickaxe", 0);
		setDefaultState(blockState.getBaseState().withProperty(FULL, false));
		setLightOpacity(0);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		return BOX;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult result, World world, BlockPos pos, EntityPlayer player) {
		ItemStack stack = new ItemStack(this);
		if (state.getValue(FULL)) {
			stack.setTagCompound(new NBTTagCompound());
			stack.getTagCompound().setBoolean("full", true);
		}
		return stack;
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean canProvidePower(IBlockState state) {
		return state.getValue(FULL);
	}

	@Override
	public int getWeakPower(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		return state.getValue(FULL) ? 8 : 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
		if (stack.hasTagCompound() && stack.getTagCompound().getBoolean("full"))
			tooltip.add(new TextComponentTranslation("tooltip.goblet_full").getFormattedText());
	}

	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		ItemStack empty = new ItemStack(this);
		list.add(empty);
		ItemStack full = new ItemStack(this);
		full.setTagCompound(new NBTTagCompound());
		full.getTagCompound().setBoolean("full", true);
		list.add(full);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase living, EnumHand hand) {
		return getDefaultState().withProperty(FULL, living.getHeldItem(hand).hasTagCompound() && living.getHeldItem(hand).getTagCompound().getBoolean("full") ? true : false);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(FULL, meta == 1 ? true : false);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FULL) ? 1 : 0;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FULL);
	}
}