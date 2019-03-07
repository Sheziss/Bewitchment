package com.bewitchment.api.recipe;

import java.util.List;

import com.bewitchment.Bewitchment;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class Ritual extends IForgeRegistryEntry.Impl<Ritual>
{
	private ItemStack[] input_items, output;
	private Class<? extends Entity>[] input_entities;
	
	private GlyphType[] circles = new GlyphType[3];
	private int time, running_power, starting_power;
	
	public Ritual(String name, ItemStack[] input_items, Class<? extends Entity>[] input_entities, ItemStack[] output, int time, int running_power, int starting_power, GlyphType small, GlyphType medium, GlyphType big)
	{
		this.setRegistryName(new ResourceLocation(Bewitchment.MOD_ID, name));
		this.input_items = input_items;
		this.input_entities = input_entities;
		this.output = output;
		this.time = time;
		this.running_power = running_power;
		this.starting_power = starting_power;
		if (small == null) throw new IllegalArgumentException("Cannot have the smaller circle missing");
		if (medium == null && big != null) throw new IllegalArgumentException("Cannot have null middle circle when a big circle is present");
		if (small == GlyphType.GOLDEN || medium == GlyphType.GOLDEN || big == GlyphType.GOLDEN) throw new IllegalArgumentException("No golden circles allowed");
		circles[0] = small;
		circles[1] = medium;
		circles[2] = big;
	}
	
	/**
	 * @return the list of ItemStacks to be used as an input.
	 */
	public ItemStack[] getInputItems()
	{
		return input_items;
	}
	
	/**
	 * @return the list of Entities to be used as an input.
	 */
	public Class<? extends Entity>[] getInputEntities()
	{
		return input_entities;
	}
	
	/**
	 * @return the output of the ritual.
	 */
	public ItemStack[] getOutput()
	{
		return output;
	}
	
	/**
	 * @return an array of the circles used to perform the ritual.
	 */
	public GlyphType[] getCircles()
	{
		return circles;
	}
	
	/**
	 * This method gets called every tick if the altar doesn't have enough power to keep it running.
	 * This method is called in place of {@link #onUpdate(World, TileEntity, EntityPlayer, BlockPos, BlockPos, NBTTagCompound, int)}
	 * 
	 * @param world the world the ritual is being performed in
	 * @param tile the TileEntityGlyph performing the ritual
	 * @param player the player that activated the ritual, or null
	 * @param recipe the list of items used to perform the ritual
	 * @param realPosition the position of the tile performing the ritual
	 * @param effectivePosition the position where the ritual should take place
	 * @param covenSize the size of the coven performing this ritual, including the player
	 */
	public abstract boolean onLowPower(World world, TileEntity tile, EntityPlayer player, BlockPos realPosition, BlockPos effectivePosition, NBTTagCompound data, int covenSize);
	
	/**
	 * This method gets called when the ritual is activated by a player.
	 * 
	 * @param world the world the ritual is being performed in
	 * @param tile the TileEntityGlyph performing the ritual
	 * @param player the player that activated the ritual, or null
	 * @param recipe the list of items used to perform the ritual
	 * @param realPosition the position of the tile performing the ritual
	 * @param effectivePosition the position where the ritual should take place
	 * @param covenSize the size of the coven performing this ritual, including the player
	 */
	public abstract boolean onStarted(World world, TileEntity tile, EntityPlayer player, BlockPos realPosition, BlockPos effectivePosition, NBTTagCompound data, int covenSize);
	
	/**
	 * This method gets called when the ritual is stopped before completion by a player.
	 * This method is never called if {@link #onFinish(World, TileEntity, EntityPlayer, BlockPos, BlockPos, NBTTagCompound, int)} is called.
	 * 
	 * @param world the world the ritual is being performed in
	 * @param tile the TileEntityGlyph performing the ritual
	 * @param player the player that activated the ritual, or null
	 * @param recipe the list of items used to perform the ritual
	 * @param realPosition the position of the tile performing the ritual
	 * @param effectivePosition the position where the ritual should take place
	 * @param covenSize the size of the coven performing this ritual, including the player
	 */
	public abstract boolean onStopped(World world, TileEntity tile, EntityPlayer player, BlockPos realPosition, BlockPos effectivePosition, NBTTagCompound data, int covenSize);
	
	/**
	 * This method gets called when the ritual time expires, before stopping automatically.
	 * This method is never called if {@link #onStopped(World, TileEntity, EntityPlayer, BlockPos, BlockPos, NBTTagCompound, int)} is called.
	 * 
	 * @param world the world the ritual is being performed in
	 * @param tile the TileEntityGlyph performing the ritual
	 * @param player the player that activated the ritual, or null
	 * @param recipe the list of items used to perform the ritual
	 * @param realPosition the position of the tile performing the ritual
	 * @param effectivePosition the position where the ritual should take place
	 * @param covenSize the size of the coven performing this ritual, including the player
	 */
	public abstract boolean onFinished(World world, TileEntity tile, EntityPlayer player, BlockPos realPosition, BlockPos effectivePosition, NBTTagCompound data, int covenSize);
	
	/**
	 * This method gets called every tick since the ritual was activated if it has enough power to run.
	 * If it doesn't, {@link #onLowPower(World, TileEntity, EntityPlayer, BlockPos, BlockPos, NBTTagCompound, int)} gets called instead.
	 * 
	 * @param world the world the ritual is being performed in
	 * @param tile the TileEntityGlyph performing the ritual
	 * @param player the player that activated the ritual, or null
	 * @param recipe the list of items used to perform the ritual
	 * @param realPosition the position of the tile performing the ritual
	 * @param effectivePosition the position where the ritual should take place
	 * @param covenSize the size of the coven performing this ritual, including the player
	 */
	public abstract boolean onUpdate(World world, TileEntity tile, EntityPlayer player, BlockPos realPosition, BlockPos effectivePosition, NBTTagCompound data, int covenSize);
	
	/**
	 * 
	 * @param world the world the ritual is being performed in
	 * @param player the player that activated the ritual, or null
	 * @param recipe the list of items used to perform the ritual
	 * @param realPosition the position of the tile performing the ritual
	 * @param effectivePosition the position where the ritual should take place
	 * @param covenSize the size of the coven performing this ritual, including the player
	 * @return true if the ritual is valid, otherwise false
	 */
	public boolean isValid(World world, EntityPlayer player, List<ItemStack> recipe, BlockPos realPosition, BlockPos effectivePosition, int covenSize)
	{
		return true;
	}
	
	public boolean canBePerformedRemotely()
	{
		return true;
	}
	
	/**
	 * @return the amount of power drained per tick for the ritual to work.
	 */
	public int getRunningPower()
	{
		return running_power;
	}

	/**
	 * The amount of power to be drained to start the ritual.
	 * If there is not enough power, the ritual is not started and the inputs are not consumed.
	 * @return the starting power
	 */
	public int getStartingPower()
	{
		return starting_power;
	}
	
	/**
	 * @return the time in ticks of how long this ritual should last, or negative if infinite.
	 */
	public int getTime() 
	{
		return time;
	}
	
	public enum GlyphType
	{
		GOLDEN, NORMAL, NETHER, END, ANY;
	}
}