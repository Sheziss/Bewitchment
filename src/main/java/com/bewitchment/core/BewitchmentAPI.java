package com.bewitchment.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BewitchmentAPI
{
	public static final List<IDistilleryRecipe> REGISTRY_DISTILLERY = new ArrayList<IDistilleryRecipe>();
	public static final List<IOvenRecipe> REGISTRY_OVEN = new ArrayList<IOvenRecipe>();
	public static final List<IRitual> REGISTRY_RITUAL = new ArrayList<IRitual>();
	public static final List<ILoomRecipe> REGISTRY_LOOM = new ArrayList<ILoomRecipe>();
	
	/**
	 * Registers a new IDistilleryRecipe, for use in the distillery.
	 * 
	 * @param recipe the recipe to register
	 * @return the recipe registered
	 */
	public static final IDistilleryRecipe registerDistilleryRecipe(IDistilleryRecipe recipe)
	{
		REGISTRY_DISTILLERY.add(recipe);
		return recipe;
	}
	
	/**
	 * Registers a new IOvenRecipe, for use in the Oven.
	 * 
	 * @param recipe the recipe to register
	 * @return the recipe registered
	 */
	public static final IOvenRecipe registerOvenRecipe(IOvenRecipe recipe)
	{
		REGISTRY_OVEN.add(recipe);
		return recipe;
	}
	
	/**
	 * Registers a new IRitual, for use with rituals.
	 * 
	 * @param recipe the recipe to register
	 * @return the recipe registered
	 */
	public static final IRitual registerRitual(IRitual recipe)
	{
		REGISTRY_RITUAL.add(recipe);
		return recipe;
	}
	
	/**
	 * Registers a new ILoomRecipe, for use with the Loom.
	 * 
	 * @param recipe the recipe to register
	 * @return the recipe registered
	 */
	public static final ILoomRecipe registerLoomRecipe(ILoomRecipe recipe)
	{
		REGISTRY_LOOM.add(recipe);
		return recipe;
	}
	
	public interface IDistilleryRecipe
	{
		/**
		 * @return the list of ItemStacks to be used as an input.
		 */
		public List<ItemStack> getInput();
		
		/**
		 * @return the list of ItemStacks to be given as an output.
		 */
		public List<ItemStack> getOutput();
		
		/**
		 * @return how much power is required to keep the recipe going.
		 */
		public int getRunningPower();
		
		/**
		 * @return how long the recipe should take to finish.
		 */
		public int getTime();
		
		public static class DistilleryRecipe implements IDistilleryRecipe
		{
			private List<ItemStack> input = new ArrayList<ItemStack>(), output = new ArrayList<ItemStack>();
			private int running_power, time;
			
			public DistilleryRecipe(ItemStack[] input, ItemStack[] output, int running_power, int time)
			{
				for (ItemStack stack : input) this.input.add(stack);
				for (ItemStack stack : output) this.output.add(stack);
				this.running_power = running_power;
				this.time = time;
			}
			
			@Override
			public List<ItemStack> getInput()
			{
				return input;
			}
			
			@Override
			public List<ItemStack> getOutput()
			{
				return output;
			}
			
			@Override
			public int getRunningPower()
			{
				return running_power;
			}
			
			@Override
			public int getTime()
			{
				return time;
			}
		}
	}
	
	public interface IOvenRecipe
	{
		/**
		 * @return the ItemStack to be used as input.
		 */
		public ItemStack getInput();
		
		/**
		 * @return the ItemStack to be given as output.
		 */
		public ItemStack getOutput();
		
		/**
		 * @return the ItemStack to be given as byproduct output.
		 */
		public ItemStack getByproduct();
		
		/**
		 * @return the chance for byproduct to appear.
		 */
		public float getByproductChance();
		
		public static class OvenRecipe implements IOvenRecipe
		{
			private ItemStack input, output, byproduct;
			private float byproduct_chance;
			
			public OvenRecipe(ItemStack input, ItemStack output, ItemStack byproduct, float byproduct_chance)
			{
				this.input = input;
				this.output = output;
				this.byproduct = byproduct;
				this.byproduct_chance = byproduct_chance;
			}
			
			@Override
			public ItemStack getInput()
			{
				return input;
			}
			
			@Override
			public ItemStack getOutput()
			{
				return output;
			}
			
			@Override
			public ItemStack getByproduct()
			{
				return byproduct;
			}
			
			@Override
			public float getByproductChance()
			{
				return byproduct_chance;
			}
		}
	}
	
	public interface IRitual
	{
		public default boolean canBePerformedRemotely()
		{
			return true;
		}
		
		/**
		 * 
		 * @param world the world the ritual is being performed in
		 * @param player the player that activated the ritual, or null
		 * @param recipe the list of items used to perform the ritual
		 * @param realPosition the position of the rile performing the ritual
		 * @param effectivePosition the position where the ritual should take place
		 * @param covenSize the size of the coven performing this ritual, including the player
		 * @return true if the ritual is valid, otherwise false
		 */
		public default boolean isValid(World world, EntityPlayer player, List<ItemStack> recipe, BlockPos realPosition, BlockPos effectivePosition, int covenSize)
		{
			return true;
		}
		
		/**
		 * @return the list of ItemStacks to be used as an input.
		 */
		public List<ItemStack> getInputItems();
		
		/**
		 * @return the list of Entities to be used as an input.
		 */
		public List<Class<? extends Entity>> getInputEntities();
		
		/**
		 * @return the output of the ritual.
		 */
		public List<ItemStack> getOutput();
		
		/**
		 * This method gets called every tick if the altar doesn't have enough power to keep it running.
		 * This method is called in place of {@link #onUpdate(World, TileEntity, EntityPlayer, BlockPos, BlockPos, NBTTagCompound, int)}
		 * 
		 * @param world the world the ritual is being performed in
		 * @param tile the TileEntityGlyph performing the ritual
		 * @param player the player that activated the ritual, or null
		 * @param recipe the list of items used to perform the ritual
		 * @param realPosition the position of the rile performing the ritual
		 * @param effectivePosition the position where the ritual should take place
		 * @param covenSize the size of the coven performing this ritual, including the player
		 */
		public boolean onLowPower(World world, TileEntity tile, EntityPlayer player, BlockPos realPosition, BlockPos effectivePosition, NBTTagCompound data, int covenSize);
		
		/**
		 * This method gets called when the ritual is activated by a player.
		 * 
		 * @param world the world the ritual is being performed in
		 * @param tile the TileEntityGlyph performing the ritual
		 * @param player the player that activated the ritual, or null
		 * @param recipe the list of items used to perform the ritual
		 * @param realPosition the position of the rile performing the ritual
		 * @param effectivePosition the position where the ritual should take place
		 * @param covenSize the size of the coven performing this ritual, including the player
		 */
		public boolean onStarted(World world, TileEntity tile, EntityPlayer player, BlockPos realPosition, BlockPos effectivePosition, NBTTagCompound data, int covenSize);
		
		/**
		 * This method gets called when the ritual is stopped before completion by a player.
		 * This method is never called if {@link #onFinish(World, TileEntity, EntityPlayer, BlockPos, BlockPos, NBTTagCompound, int)} is called.
		 * 
		 * @param world the world the ritual is being performed in
		 * @param tile the TileEntityGlyph performing the ritual
		 * @param player the player that activated the ritual, or null
		 * @param recipe the list of items used to perform the ritual
		 * @param realPosition the position of the rile performing the ritual
		 * @param effectivePosition the position where the ritual should take place
		 * @param covenSize the size of the coven performing this ritual, including the player
		 */
		public boolean onStopped(World world, TileEntity tile, EntityPlayer player, BlockPos realPosition, BlockPos effectivePosition, NBTTagCompound data, int covenSize);
		
		/**
		 * This method gets called when the ritual time expires, before stopping automatically.
		 * This method is never called if {@link #onStopped(World, TileEntity, EntityPlayer, BlockPos, BlockPos, NBTTagCompound, int)} is called.
		 * 
		 * @param world the world the ritual is being performed in
		 * @param tile the TileEntityGlyph performing the ritual
		 * @param player the player that activated the ritual, or null
		 * @param recipe the list of items used to perform the ritual
		 * @param realPosition the position of the rile performing the ritual
		 * @param effectivePosition the position where the ritual should take place
		 * @param covenSize the size of the coven performing this ritual, including the player
		 */
		public boolean onFinished(World world, TileEntity tile, EntityPlayer player, BlockPos realPosition, BlockPos effectivePosition, NBTTagCompound data, int covenSize);
		
		/**
		 * This method gets called every tick since the ritual was activated if it has enough power to run.
		 * If it doesn't, {@link #onLowPower(World, TileEntity, EntityPlayer, BlockPos, BlockPos, NBTTagCompound, int)} gets called instead.
		 * 
		 * @param world the world the ritual is being performed in
		 * @param tile the TileEntityGlyph performing the ritual
		 * @param player the player that activated the ritual, or null
		 * @param recipe the list of items used to perform the ritual
		 * @param realPosition the position of the rile performing the ritual
		 * @param effectivePosition the position where the ritual should take place
		 * @param covenSize the size of the coven performing this ritual, including the player
		 */
		public boolean onUpdate(World world, TileEntity tile, EntityPlayer player, BlockPos realPosition, BlockPos effectivePosition, NBTTagCompound data, int covenSize);
		
		/**
		 * The circles used to perform the ritual.
		 * The bit encoding is an 8 bit number BBMMSSNN where the first 6 bits are, 2 by 2, the metadata of the circle
		 * type from bigger to smaller, and the last 2 the number of total circles.
		 * 
		 * @return the integer bit representation of what circles this ritual requires.
		 */
		public int getCircles();
		
		/**
		 * @return the amount of power drained per tick for the ritual to work.
		 */
		public int getRunningPower();
		
		/**
		 * The amount of power to be drained to start the ritual.
		 * If there is not enough power, the ritual is not started and the inputs are not consumed.
		 * @return the starting power
		 */
		public int getStartingPower();
		
		/**
		 * @return the time in ticks of how long this ritual should last, or negative if infinite.
		 */
		public int getTime();
		
		public static class Ritual implements IRitual
		{
			private List<ItemStack> input_items = new ArrayList<ItemStack>(), output = new ArrayList<ItemStack>();
			private List<Class<? extends Entity>> input_entities = new ArrayList<Class<? extends Entity>>();
			
			private int circles, time, running_power, starting_power;
			
			public Ritual(ItemStack[] input_items, Class<? extends Entity>[] input_entities, ItemStack[] output, int time, int running_power, int starting_power, GlyphType small, GlyphType medium, GlyphType big)
			{
				for (ItemStack stack : input_items) this.input_items.add(stack);
				for (Class<? extends Entity> clazz : input_entities) this.input_entities.add(clazz);
				for (ItemStack stack : output) this.output.add(stack);
				this.time = time;
				this.running_power = running_power;
				this.starting_power = starting_power;
				if (small == null) throw new IllegalArgumentException("Cannot have the smaller circle missing");
				if (medium == null && big != null) throw new IllegalArgumentException("Cannot have null middle circle when a big circle is present");
				if (small == GlyphType.GOLDEN || medium == GlyphType.GOLDEN || big == GlyphType.GOLDEN) throw new IllegalArgumentException("No golden circles allowed");
				if (medium != null) circles++;
				if (big != null) circles++;
				circles |= small.ordinal() << 2 | (medium == null ? 0 : medium.ordinal() << 4) | (big == null ? 0 : big.ordinal() << 6);
			}
			
			@Override
			public List<ItemStack> getInputItems()
			{
				return input_items;
			}
			
			@Override
			public List<Class<? extends Entity>> getInputEntities()
			{
				return input_entities;
			}
			
			@Override
			public List<ItemStack> getOutput()
			{
				return output;
			}
			
			@Override
			public boolean onLowPower(World world, TileEntity tile, EntityPlayer player, BlockPos realPosition, BlockPos effectivePosition, NBTTagCompound data, int covenSize)
			{
				return false;
			}
			
			@Override
			public boolean onStarted(World world, TileEntity tile, EntityPlayer player, BlockPos realPosition, BlockPos effectivePosition, NBTTagCompound data, int covenSize)
			{
				return false;
			}
			
			@Override
			public boolean onStopped(World world, TileEntity tile, EntityPlayer player, BlockPos realPosition, BlockPos effectivePosition, NBTTagCompound data, int covenSize)
			{
				return false;
			}
			
			@Override
			public boolean onFinished(World world, TileEntity tile, EntityPlayer player, BlockPos realPosition, BlockPos effectivePosition, NBTTagCompound data, int covenSize)
			{
				return false;
			}
			
			@Override
			public boolean onUpdate(World world, TileEntity tile, EntityPlayer player, BlockPos realPosition, BlockPos effectivePosition, NBTTagCompound data, int covenSize)
			{
				return false;
			}
			
			@Override
			public int getCircles()
			{
				return circles;
			}
			
			@Override
			public int getRunningPower()
			{
				return running_power;
			}

			@Override
			public int getStartingPower()
			{
				return starting_power;
			}
			
			@Override
			public int getTime() 
			{
				return time;
			}
			
			public enum GlyphType
			{
				GOLDEN, NORMAL, NETHER, END, ANY;
			}
		}
	}
	
	public interface ILoomRecipe
	{
		/**
		 * @return the list of ItemStacks to be used as input.
		 */
		public List<ItemStack> getInput();
		
		/**
		 * @return the output ItemStack.
		 */
		public ItemStack getOutput();
		
		public static class LoomRecipe implements ILoomRecipe
		{
			private List<ItemStack> input = new ArrayList<ItemStack>();
			private ItemStack output;
			
			public LoomRecipe(ItemStack[] input, ItemStack output)
			{
				for (ItemStack stack : input) this.input.add(stack);
				this.output = output;
			}
			
			@Override
			public List<ItemStack> getInput()
			{
				return input;
			}
			
			@Override
			public ItemStack getOutput()
			{
				return output;
			}
		}
	}
}