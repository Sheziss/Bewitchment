package com.bewitchment.registry.block.tile;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.core.Bewitchment;
import com.bewitchment.core.Bewitchment.API.DistilleryRecipe;
import com.bewitchment.registry.capability.MagicPower;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityDistillery extends ModTileEntity
{
	public static final int BURN_TIME = 1200;
	
	private MagicPower magic_power = MagicPower.Provider.CAPABILITY.getDefaultInstance();
	
	private String current_recipe = "";
	
	public int burn_time, progress, recipe_time;
	
	public TileEntityDistillery()
	{
		super(13);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag)
	{
		tag.setString("current_recipe", current_recipe);
		tag.setInteger("burn_time", burn_time);
		tag.setInteger("progress", progress);
		tag.setInteger("recipe_time", recipe_time);
		tag.setInteger("power", magic_power.getAmount());
		return super.writeToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		current_recipe = tag.getString("current_recipe");
		burn_time = tag.getInteger("burn_time");
		progress = tag.getInteger("progress");
		recipe_time = tag.getInteger("recipe_time");
		magic_power.setAmount(tag.getInteger("power"));
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		return new SPacketUpdateTileEntity(getPos(), 1, writeToNBT(new NBTTagCompound()));
	}
	
	@Override
	public void onDataPacket(NetworkManager manager, SPacketUpdateTileEntity packet)
	{
		readFromNBT(packet.getNbtCompound());
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing face)
	{
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == MagicPower.Provider.CAPABILITY || super.hasCapability(capability, face);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing face)
	{
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(this) : capability == MagicPower.Provider.CAPABILITY ? MagicPower.Provider.CAPABILITY.cast(magic_power) : super.getCapability(capability, face);
	}
	
	@Override
	public boolean isItemValid(int slot, ItemStack stack)
	{
		return slot == 0 ? stack.getItem() == Items.BLAZE_POWDER : slot < 7;
	}
	
	@Override
	public void update()
	{
		if (burn_time > 0) burn_time--;
		if (!current_recipe.isEmpty())
		{
			if (burn_time == 0 && !getStackInSlot(0).isEmpty())
			{
				burn_time = BURN_TIME;
				extractItem(0, 1, false);
			}
			else if (burn_time > 0)
			{
				if (progress < recipe_time)
				{
					if (true) // mp.drainAltarFirst(this.world.getClosestPlayer(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5, 5, false), this.getPos(), this.world.provider.getDimension(), 2)) {
					{
						progress++;
					}
				}
				else progress = 0;
			}
		}
		checkRecipe();
	}
	
	private boolean canOutputFit(DistilleryRecipe recipe)
	{
		ItemStackHandler sim = new ItemStackHandler(6);
		for (int i = 0; i < sim.getSlots(); i++) sim.setStackInSlot(i, getStackInSlot(i + 7).copy());
		for (ItemStack stack : recipe.getOutput())
		{
			ItemStack sim0 = stack.copy();
			for (int i = 0; (i < sim.getSlots() && !sim0.isEmpty()); i++) sim0 = sim.insertItem(i, sim0, false);
			if (!sim0.isEmpty()) return false;
		}
		return true;
	}
	
	private void checkRecipe()
	{
		List<ItemStack> inputStacks = new ArrayList<ItemStack>();
		for (int i = 1; i < 7; i++) inputStacks.add(getStackInSlot(i));
		DistilleryRecipe recipe = Bewitchment.API.REGISTRY_DISTILLERY.getValuesCollection().parallelStream().filter(dr -> dr.matches(inputStacks)).findFirst().orElse(null);
		if (recipe == null)
		{
			current_recipe = "";
			progress = 0;
			recipe_time = -1;
		}
		else if (!current_recipe.equals(recipe.getRegistryName().toString()) && canOutputFit(recipe))
		{
			current_recipe = recipe.getRegistryName().toString();
			recipe_time = recipe.getTime();
		}
		if (recipe != null && progress >= recipe_time)
		{
			int i;
			for (i = 1; i < 7; i++) extractItem(i, 1, false);
			for (ItemStack stack : recipe.getOutput()) insertItem(getStackInSlot(i).getItem() != stack.getItem() || getStackInSlot(i).getMetadata() != stack.getMetadata() || getStackInSlot(i).getCount() >= getStackInSlot(i).getMaxStackSize() ? i++ : i, stack, false);
		}
		markDirty();
	}
}