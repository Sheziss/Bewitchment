package com.bewitchment.common.block;

import com.bewitchment.common.block.tile.entity.TileEntityGemBowl;
import com.bewitchment.common.block.util.ModBlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.crafting.IInfusionStabiliserExt;

@Optional.Interface(iface = "thaumcraft.api.crafting.IInfusionStabiliserExt", modid = "thaumcraft")
public class BlockGemBowl extends ModBlockContainer implements IInfusionStabiliserExt {
	private static final AxisAlignedBB BOX = new AxisAlignedBB(0.25, 0, 0.25, 0.75, 0.1875, 0.75);

	public BlockGemBowl() {
		super(null, "gem_bowl", Material.IRON, SoundType.METAL, 1, 1, "pickaxe", 0, -1);
		setLightOpacity(0);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityGemBowl();
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		return BOX;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing face, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && !player.isSneaking()) {
			TileEntityGemBowl tile = ((TileEntityGemBowl) world.getTileEntity(pos));
			ItemStack stack = player.getHeldItem(hand);
			if (stack.isEmpty() && !tile.inventory.getStackInSlot(0).isEmpty())
				player.setHeldItem(hand, tile.inventory.getStackInSlot(0).splitStack(1));
			else if (!stack.isEmpty() && tile.inventory.getStackInSlot(0).isEmpty()) {
				for (int id : OreDictionary.getOreIDs(stack)) {
					if (TileEntityGemBowl.gainMap.keySet().contains(OreDictionary.getOreName(id))) {
						tile.inventory.setStackInSlot(0, stack.splitStack(1));
						break;
					}
				}
			}
			tile.markDirty();
		}
		return super.onBlockActivated(world, pos, state, player, hand, face, hitX, hitY, hitZ);
	}

	@Override
	@Optional.Method(modid = "thaumcraft")
	public boolean canStabaliseInfusion(World world, BlockPos pos) {
		return true;
	}

	@Override
	@Optional.Method(modid = "thaumcraft")
	public float getStabilizationAmount(World world, BlockPos pos) {
		return 0.7f;
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing face, float hitX, float hitY, float hitZ, int meta, EntityLivingBase entity, EnumHand hand) {
		return getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.fromAngle(entity.rotationYaw));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.HORIZONTALS[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(BlockHorizontal.FACING).getHorizontalIndex();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, BlockHorizontal.FACING);
	}
}