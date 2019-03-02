package com.bewitchment.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryBuilder;

@Mod(modid = Bewitchment.MOD_ID, name = Bewitchment.MOD_NAME, version = Bewitchment.MOD_VERSION)
public class Bewitchment
{
	public static final String MOD_ID = "bewitchment", MOD_NAME = "Bewitchment", MOD_VERSION = "0.1";
	
	@Instance(Bewitchment.MOD_ID)
	public static Bewitchment instance;
	
	@SidedProxy(serverSide = "com.bewitchment.core.CommonProxy", clientSide = "com.bewitchment.core.ClientProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
	}
	
	/**
	 * The Bewitchment API, use this for creating addons.
	 */
	public static class API
	{
		public static final IForgeRegistry<DistilleryRecipe> REGISTRY_DISTILLERY = new RegistryBuilder<DistilleryRecipe>().setName(new ResourceLocation(Bewitchment.MOD_ID, "distillery")).setType(DistilleryRecipe.class).create();
		public static final IForgeRegistry<LoomRecipe> REGISTRY_LOOM = new RegistryBuilder<LoomRecipe>().setName(new ResourceLocation(Bewitchment.MOD_ID, "loom")).setType(LoomRecipe.class).create();
		public static final IForgeRegistry<OvenRecipe> REGISTRY_OVEN = new RegistryBuilder<OvenRecipe>().setName(new ResourceLocation(Bewitchment.MOD_ID, "oven")).setType(OvenRecipe.class).create();
		public static final IForgeRegistry<Ritual> REGISTRY_RITUAL = new RegistryBuilder<Ritual>().setName(new ResourceLocation(Bewitchment.MOD_ID, "ritual")).setType(Ritual.class).create();
		
		/**
		 * Registers a new DistilleryRecipe, for use in the Distillery.
		 * 
		 * @param recipe the recipe to register
		 * @return the recipe registered
		 */
		public static final DistilleryRecipe registerDistilleryRecipe(DistilleryRecipe recipe)
		{
			REGISTRY_DISTILLERY.register(recipe);
			return recipe;
		}
		
		/**
		 * Registers a new OvenRecipe, for use in the Oven.
		 * 
		 * @param recipe the recipe to register
		 * @return the recipe registered
		 */
		public static final OvenRecipe registerOvenRecipe(OvenRecipe recipe)
		{
			REGISTRY_OVEN.register(recipe);
			return recipe;
		}
		
		/**
		 * Registers a new Ritual, for use with rituals.
		 * 
		 * @param ritual the ritual to register
		 * @return the ritual registered
		 */
		public static final Ritual registerRitual(Ritual ritual)
		{
			REGISTRY_RITUAL.register(ritual);
			return ritual;
		}
		
		/**
		 * Registers a new LoomRecipe, for use with the Loom.
		 * 
		 * @param recipe the recipe to register
		 * @return the recipe registered
		 */
		public static final LoomRecipe registerLoomRecipe(LoomRecipe recipe)
		{
			REGISTRY_LOOM.register(recipe);
			return recipe;
		}
		
		public static class DistilleryRecipe extends IForgeRegistryEntry.Impl<DistilleryRecipe>
		{
			private ItemStack[] input , output;
			private int running_power, time;
			
			public DistilleryRecipe(String name, ItemStack[] input, ItemStack[] output, int running_power, int time)
			{
				this.setRegistryName(new ResourceLocation(Bewitchment.MOD_ID, name));
				this.input = input;
				this.output = output;
				this.running_power = running_power;
				this.time = time;
			}
			
			/**
			 * @return the list of ItemStacks to be used as an input.
			 */
			public ItemStack[] getInput()
			{
				return input;
			}
			
			/**
			 * @return the list of ItemStacks to be given as an output.
			 */
			public ItemStack[] getOutput()
			{
				return output;
			}
			
			/**
			 * @return how much power is required to keep the recipe going.
			 */
			public int getRunningPower()
			{
				return running_power;
			}
			
			/**
			 * @return how long the recipe should take to finish.
			 */
			public int getTime()
			{
				return time;
			}
			
			public boolean matches(List<ItemStack> inputStacks)
			{
				int nonEmpty = 0;
				for (ItemStack stack : inputStacks) if (stack.getCount() > 0) nonEmpty++;
				if (nonEmpty != getInput().length) return false;
				boolean[] found = new boolean[getInput().length];
				ArrayList<ItemStack> comp = new ArrayList<ItemStack>(inputStacks);
				for (int i = 0; i < getInput().length; i++)
				{
					for (int j = 0; j < comp.size(); j++)
					{
						ItemStack stack = comp.get(j);
						if (getInput()[i].getItem() == stack.getItem() && (getInput()[i].getMetadata() == stack.getMetadata() || getInput()[i].getMetadata() == 32767))
						{
							found[i] = true;
							comp.set(j, ItemStack.EMPTY);
							break;
						}
					}
				}
				for (boolean b : found) if (!b) return false;
				return true;
			}
		}
		
		public static class OvenRecipe extends IForgeRegistryEntry.Impl<OvenRecipe>
		{
			private ItemStack input, output, byproduct;
			private float byproduct_chance;
			
			public OvenRecipe(String name, ItemStack input, ItemStack output, ItemStack byproduct, float byproduct_chance)
			{
				this.setRegistryName(new ResourceLocation(Bewitchment.MOD_ID, name));
				this.input = input;
				this.output = output;
				this.byproduct = byproduct;
				this.byproduct_chance = byproduct_chance;
			}
			
			/**
			 * @return the ItemStack to be used as input.
			 */
			public ItemStack getInput()
			{
				return input;
			}
			
			/**
			 * @return the ItemStack to be given as output.
			 */
			public ItemStack getOutput()
			{
				return output;
			}
			
			/**
			 * @return the ItemStack to be given as a byproduct output.
			 */
			public ItemStack getByproduct()
			{
				return byproduct;
			}
			
			/**
			 * @return the chance for the byproduct to appear.
			 */
			public float getByproductChance()
			{
				return byproduct_chance;
			}
			
			public boolean matches(ItemStack inputStack)
			{
				return inputStack.getItem() == getInput().getItem() && (inputStack.getMetadata() == getInput().getMetadata() || getInput().getMetadata() == 32767);
			}
		}
		
		public static abstract class Ritual extends IForgeRegistryEntry.Impl<Ritual>
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
		
		public static class LoomRecipe extends IForgeRegistryEntry.Impl<LoomRecipe>
		{
			private ItemStack[] input;
			private ItemStack output;
			
			public LoomRecipe(String name, ItemStack[] input, ItemStack output)
			{
				this.setRegistryName(new ResourceLocation(Bewitchment.MOD_ID, name));
				this.input = input;
				this.output = output;
			}
			
			/**
			 * @return the list of ItemStacks to be used as input.
			 */
			public ItemStack[] getInput()
			{
				return input;
			}
			
			/**
			 * @return the output ItemStack.
			 */
			public ItemStack getOutput()
			{
				return output;
			}
		}
	}
}