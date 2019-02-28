package com.bewitchment.registry.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bewitchment.core.Bewitchment;
import com.bewitchment.registry.IOreName;
import com.bewitchment.registry.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlockDoor extends BlockDoor implements IOreName
{
	public Item drop;
	
	private final List<String> oreNames = new ArrayList<String>();
	
	@SuppressWarnings("deprecation")
	public ModBlockDoor(String name, Block base, String... oreNames)
	{
		super(base.getDefaultState().getMaterial());
		this.setRegistryName(new ResourceLocation(Bewitchment.MOD_ID, name));
		this.setTranslationKey(this.getRegistryName().toString());
		this.setCreativeTab(Bewitchment.proxy.tab);
		this.setHardness(base.getBlockHardness(null, null, null));
		this.setResistance(base.getExplosionResistance(null)*5);
		this.setHarvestLevel(base.getHarvestTool(base.getDefaultState()), base.getHarvestLevel(base.getDefaultState()));
		this.setSoundType(base.getSoundType());
		if (base.getDefaultState().getMaterial() == Material.CARPET) Blocks.FIRE.setFireInfo(this, 60, 20);
		if (base.getDefaultState().getMaterial() == Material.CLOTH || base.getDefaultState().getMaterial() == Material.LEAVES) Blocks.FIRE.setFireInfo(this, 30, 60);
		if (base.getDefaultState().getMaterial() == Material.PLANTS) Blocks.FIRE.setFireInfo(this, 60, 100);
		if (base.getDefaultState().getMaterial() == Material.TNT || base.getDefaultState().getMaterial() == Material.VINE) Blocks.FIRE.setFireInfo(this, 15, 100);
		if (base.getDefaultState().getMaterial() == Material.WOOD) Blocks.FIRE.setFireInfo(this, 5, 20);
		if (base.getDefaultState().getMaterial() == Material.ICE) this.setDefaultSlipperiness(0.98f);
		for (String ore : oreNames) this.oreNames.add(ore);
		ModBlocks.REGISTRY.add(this);
	}
	
	@Override
	public List<String> getOreNames()
	{
		return oreNames;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer()
	{
		return getDefaultState().getMaterial() == Material.ICE || getDefaultState().getMaterial() == Material.GLASS ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public ItemStack getItem(World world, BlockPos pos, IBlockState state)
	{
		return new ItemStack(drop);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return state.getValue(HALF) == EnumDoorHalf.UPPER ? Items.AIR : drop;
	}
}