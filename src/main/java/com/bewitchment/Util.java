package com.bewitchment;

import com.bewitchment.registry.ModObjects;
import com.bewitchment.registry.util.IOreDictionaryContainer;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class Util {
	@SuppressWarnings("deprecation")
	public static void registerValues(Block block, String name, Block base, String... oreDictionaryNames) {
		registerValues(block, name, base.getDefaultState().getMaterial(), base.getSoundType(), base.getBlockHardness(null, null, null), base.getExplosionResistance(null) * 5, base.getHarvestTool(base.getDefaultState()), base.getHarvestLevel(base.getDefaultState()), oreDictionaryNames);
	}

	public static void registerValues(Block block, String name, Material mat, SoundType sound, float hardness, float resistance, String tool, int level, String... oreDictionaryNames) {
		block.setRegistryName(new ResourceLocation(Bewitchment.MODID, name));
		block.setTranslationKey(block.getRegistryName().toString().replace(":", "."));
		block.setCreativeTab(Bewitchment.proxy.tab);
		ObfuscationReflectionHelper.setPrivateValue(Block.class, block, sound, "blockSoundType", "field_149762_H");
		block.setHardness(hardness);
		block.setResistance(resistance);
		block.setHarvestLevel(tool, level);
		if (mat == Material.CARPET) Blocks.FIRE.setFireInfo(block, 60, 20);
		if (mat == Material.CLOTH || mat == Material.LEAVES) Blocks.FIRE.setFireInfo(block, 30, 60);
		if (mat == Material.PLANTS) Blocks.FIRE.setFireInfo(block, 60, 100);
		if (mat == Material.TNT || mat == Material.VINE) Blocks.FIRE.setFireInfo(block, 15, 100);
		if (mat == Material.WOOD) Blocks.FIRE.setFireInfo(block, 5, 20);
		if (mat == Material.ICE) block.setDefaultSlipperiness(0.98f);
		if (block instanceof IOreDictionaryContainer)
			for (String ore : oreDictionaryNames) ((IOreDictionaryContainer) block).getOreDictionaryNames().add(ore);
		ModObjects.REGISTRY.add(block);
	}

	public static void registerValues(Item item, String name, String... oreDictionaryNames) {
		item.setRegistryName(new ResourceLocation(Bewitchment.MODID, name));
		item.setTranslationKey(item.getRegistryName().toString().replace(":", "."));
		item.setCreativeTab(Bewitchment.proxy.tab);
		if (item instanceof IOreDictionaryContainer)
			for (String ore : oreDictionaryNames) ((IOreDictionaryContainer) item).getOreDictionaryNames().add(ore);
		ModObjects.REGISTRY.add(item);
	}

	public static boolean areStacksEqual(ItemStack stack0, ItemStack stack1) {
		return stack0.getItem() == stack1.getItem() && (stack0.getMetadata() == stack1.getMetadata() || stack1.getMetadata() == Short.MAX_VALUE);
	}

	public static final boolean isRelated(ItemStack stack, String oreDictionaryEntry) {
		for (ItemStack ore : OreDictionary.getOres(oreDictionaryEntry)) {
			if (ore.getItem() == stack.getItem()) return true;
			if (stack.getItem() instanceof ItemSword) {
				ToolMaterial mat = ObfuscationReflectionHelper.getPrivateValue(ItemSword.class, ((ItemSword) stack.getItem()), 1);
				return mat.getRepairItemStack().getItem() == ore.getItem();
			}
			if (stack.getItem() instanceof ItemTool) {
				ToolMaterial mat = ObfuscationReflectionHelper.getPrivateValue(ItemTool.class, ((ItemTool) stack.getItem()), 4);
				return mat.getRepairItemStack().getItem() == ore.getItem();
			}
			if (stack.getItem() instanceof ItemHoe) {
				ToolMaterial mat = ObfuscationReflectionHelper.getPrivateValue(ItemHoe.class, ((ItemHoe) stack.getItem()), 1);
				return mat.getRepairItemStack().getItem() == ore.getItem();
			}
			if (stack.getItem() instanceof ItemArmor)
				return ((ItemArmor) stack.getItem()).getArmorMaterial().getRepairItemStack().getItem() == ore.getItem();
		}
		return false;
	}

	public static String[] toArray(List<String> list) {
		return list.toArray(new String[list.size()]);
	}

	public static ItemStack[] getOres(String... oreNames) {
		List<ItemStack> ret = new ArrayList<>();
		for (String ore : oreNames) for (ItemStack stack : OreDictionary.getOres(ore)) ret.add(stack);
		return ret.toArray(new ItemStack[ret.size()]);
	}

	public static boolean areISListsEqual(List<Ingredient> ings, List<ItemStack> stacks) {
		List<ItemStack> checklist = new ArrayList<>();
		for (ItemStack stack : stacks) {
			for (int i = 0; i < stack.getCount(); i++) {
				ItemStack copy = stack.copy();
				copy.setCount(1);
				checklist.add(copy);
			}
		}
		if (ings.size() == checklist.size()) {
			List<Ingredient> removalList = new ArrayList<>(ings);
			for (ItemStack stack : checklist) {
				Ingredient found = null;
				for (Ingredient ing : removalList) {
					if (ing.apply(stack)) {
						found = ing;
						break;
					}
				}
				if (found == null) return false;
				removalList.remove(found);
			}
			return removalList.isEmpty();
		}
		return false;
	}

	public static void giveAndConsumeItem(EntityPlayer player, EnumHand hand, ItemStack stack) {
		if (!player.isCreative()) player.getHeldItem(hand).shrink(1);
		if (player.getHeldItem(hand).isEmpty()) player.setHeldItem(hand, stack);
		else if (!player.inventory.addItemStackToInventory(stack)) player.dropItem(stack, false);
	}
}