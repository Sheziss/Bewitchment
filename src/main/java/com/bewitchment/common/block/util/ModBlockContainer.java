package com.bewitchment.common.block.util;

import com.bewitchment.Bewitchment;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;

public abstract class ModBlockContainer extends BlockContainer
{
	private final Object mod_instance;
	private final int gui_id;
	
	public ModBlockContainer(Object mod_instance, String name, Material mat, SoundType sound, float hardness, float resistance, String tool, int level, int gui_id)
	{
		super(mat);
		Bewitchment.proxy.registerValues(this, name, mat, sound, hardness, resistance, tool, level);
		this.mod_instance = mod_instance;
		this.gui_id = gui_id;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		if (!world.isRemote && world.getGameRules().getBoolean("doTileDrops") && hasTileEntity(state) && world.getTileEntity(pos) instanceof IItemHandler) for (int i = 0; i < ((IItemHandler)world.getTileEntity(pos)).getSlots(); i++) InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), ((IItemHandler)world.getTileEntity(pos)).getStackInSlot(i));
		super.breakBlock(world, pos, state);
	}
	
	@Override
	public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return state.getMaterial() == Material.WOOD;
    }
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face)
	{
		return BlockFaceShape.UNDEFINED;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer()
	{
		return getDefaultState().getMaterial() == Material.ICE || getDefaultState().getMaterial() == Material.GLASS ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
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
	public boolean isWood(IBlockAccess world, BlockPos pos)
    {
		return world.getBlockState(pos).getMaterial() == Material.WOOD;
    }
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing face, float hitX, float hitY, float hitZ)
	{
		if (player.isSneaking()) return false;
		if (gui_id > -1) player.openGui(mod_instance, gui_id, world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face)
	{
		return super.shouldSideBeRendered(state, world, pos, face) && (state.getMaterial() == Material.ICE || state.getMaterial() == Material.GLASS ? world.getBlockState(pos.offset(face)).getBlock() != this : true);
	}
}