package com.bewitchment.common.item.util;

import com.bewitchment.Util;
import com.bewitchment.registry.util.IOreDictionaryContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ModItemDoor extends ItemDoor implements IOreDictionaryContainer {
	public final ModBlockDoor door;
	private final List<String> oreDictionaryNames = new ArrayList<String>();

	public ModItemDoor(String name, Block base, String... oreDictionaryNames) {
		this(name, base, new ModBlockDoor("block_" + name, base), oreDictionaryNames);
	}

	private ModItemDoor(String name, Block base, ModBlockDoor door, String... oreDictionaryNames) {
		super(door);
		Util.registerValues(this, name, oreDictionaryNames);
		this.door = door;
		this.door.drop = new ItemStack(this);
	}

	@Override
	public List<String> getOreDictionaryNames() {
		return oreDictionaryNames;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
		String tip = "tooltip." + getTranslationKey().substring(5);
		if (!I18n.format(tip).equals(tip)) tooltip.add(TextFormatting.GRAY + I18n.format(tip));
	}

	public static class ModBlockDoor extends BlockDoor {
		public ItemStack drop;

		public ModBlockDoor(String name, Block base) {
			super(base.getDefaultState().getMaterial());
			Util.registerValues(this, name, base);
			setCreativeTab(null);
		}

		@Override
		@SideOnly(Side.CLIENT)
		public BlockRenderLayer getRenderLayer() {
			return getDefaultState().getMaterial() == Material.ICE || getDefaultState().getMaterial() == Material.GLASS ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.CUTOUT;
		}

		@Override
		public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
			return drop;
		}

		@Override
		public Item getItemDropped(IBlockState state, Random rand, int fortune) {
			return state.getValue(HALF) == EnumDoorHalf.UPPER ? Items.AIR : drop.getItem();
		}
	}
}