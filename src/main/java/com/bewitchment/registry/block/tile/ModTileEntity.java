package com.bewitchment.registry.block.tile;

import javax.annotation.Nonnull;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.IWorldNameable;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public abstract class ModTileEntity extends TileEntity implements IItemHandlerModifiable, ITickable, IWorldNameable
{
	public String custom_name = "";
	private ItemStackHandler inventory;
	
	protected ModTileEntity(int inventory_size)
	{
		inventory = new ItemStackHandler(inventory_size);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag)
	{
		tag.setTag("inventory", inventory.serializeNBT());
		tag.setString("custom_name", custom_name);
		markDirty();
		return super.writeToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		inventory.deserializeNBT(tag.getCompoundTag("inventory"));
		custom_name = tag.getString("custom_name");
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
	public String getName()
	{
		return hasCustomName() ? custom_name : I18n.format(world.getBlockState(pos).getBlock().getTranslationKey() + ".name");
	}
	
	@Override
	public boolean hasCustomName()
	{
		return custom_name != null && !custom_name.isEmpty();
	}
	
	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return inventory.getStackInSlot(slot);
	}
	
	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
	{
		return inventory.insertItem(slot, stack, simulate);
	}
	
	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate)
	{
		return inventory.extractItem(slot, amount, simulate);
	}
	
	@Override
	public void setStackInSlot(int slot, ItemStack stack)
	{
		inventory.setStackInSlot(slot, stack);
	}
	
	@Override
	public int getSlots()
	{
		return inventory.getSlots();
	}
	
	@Override
	public int getSlotLimit(int slot)
	{
		return inventory.getSlotLimit(slot);
	}
	
	@Override
	public abstract boolean isItemValid(int slot, @Nonnull ItemStack stack);
}