package com.bewitchment.common.item.food;

import com.bewitchment.common.item.util.ModItemFood;
import com.bewitchment.registry.ModObjects;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemBowlOfStew extends ModItemFood {
	public ItemBowlOfStew() {
		super("bowl_of_stew", 0, 0, false);
	}

	public static ItemStack setFoodContents(List<ItemStack> items) {
		ItemStack stack = new ItemStack(ModObjects.bowl_of_stew);
		int healAmount = 0;
		float saturationAmount = 0;
		if (!items.isEmpty()) {
			if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
			stack.getTagCompound().setTag("items", new NBTTagList());
			for (ItemStack stack0 : items) {
				stack.getTagCompound().getTagList("items", Constants.NBT.TAG_COMPOUND).appendTag(stack0.serializeNBT());
				healAmount += ((ItemFood) stack0.getItem()).getHealAmount(stack0);
				saturationAmount += ((ItemFood) stack0.getItem()).getSaturationModifier(stack0);
			}
			stack.getTagCompound().setInteger("healAmount", healAmount);
			stack.getTagCompound().setFloat("saturationAmount", saturationAmount);
		}
		return stack;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase living) {
		super.onItemUseFinish(stack, world, living);
		return new ItemStack(Items.BOWL);
	}

	@Override
	public int getHealAmount(ItemStack stack) {
		return stack.hasTagCompound() && stack.getTagCompound().hasKey("healAmount") ? stack.getTagCompound().getInteger("healAmount") : 0;
	}

	@Override
	public float getSaturationModifier(ItemStack stack) {
		return stack.hasTagCompound() && stack.getTagCompound().hasKey("saturationAmount") ? stack.getTagCompound().getFloat("saturationAmount") : 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("items")) if (!Loader.isModLoaded("applecore"))
			tooltip.add(I18n.format("tooltip.bewitchment.filled_bowl_full", getHealAmount(stack), getSaturationModifier(stack)));
		else tooltip.add(I18n.format("tooltip.bewitchment.filled_bowl"));
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("items")) {
			NBTTagList list = stack.getTagCompound().getTagList("items", Constants.NBT.TAG_COMPOUND);
			for (int i = 0; i < list.tagCount(); i++) {
				ItemStack stack0 = new ItemStack(list.getCompoundTagAt(i));
				if (stack0.getItem() instanceof ItemFood) {
					try {
						ReflectionHelper.findMethod(ItemFood.class, "onFoodEaten", "func_77849_c", ItemStack.class, World.class, EntityPlayer.class).invoke(stack0.getItem(), stack0, world, player);
					}
					catch (Exception e) {
					}
				}
			}
		}
	}
}