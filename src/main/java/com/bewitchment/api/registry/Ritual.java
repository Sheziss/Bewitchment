package com.bewitchment.api.registry;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.block.BlockGlyph;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.block.tile.entity.TileEntityGlyph;
import com.bewitchment.registry.ModObjects;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class Ritual extends IForgeRegistryEntry.Impl<Ritual>
{
	public static final int[][] small = {
			{0,0,1,1,1,0,0},
			{0,1,0,0,0,1,0},
			{1,0,0,0,0,0,1},
			{1,0,0,0,0,0,1},
			{1,0,0,0,0,0,1},
			{0,1,0,0,0,1,0},
			{0,0,1,1,1,0,0}};
	public static final int[][] medium = {
			{0,0,0,1,1,1,1,1,0,0,0},
			{0,0,1,0,0,0,0,0,1,0,0},
			{0,1,0,0,0,0,0,0,0,1,0},
			{1,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,1},
			{0,1,0,0,0,0,0,0,0,1,0},
			{0,0,1,0,0,0,0,0,1,0,0},
			{0,0,0,1,1,1,1,1,0,0,0}};
	public static final int[][] large = {
			{0,0,0,0,1,1,1,1,1,1,1,0,0,0,0},
			{0,0,0,1,0,0,0,0,0,0,0,1,0,0,0},
			{0,0,1,0,0,0,0,0,0,0,0,0,1,0,0},
			{0,1,0,0,0,0,0,0,0,0,0,0,0,1,0},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{0,1,0,0,0,0,0,0,0,0,0,0,0,1,0},
			{0,0,1,0,0,0,0,0,0,0,0,0,1,0,0},
			{0,0,0,1,0,0,0,0,0,0,0,1,0,0,0},
			{0,0,0,0,1,1,1,1,1,1,1,0,0,0,0}};
	
	private final List<Ingredient> inputItems;
	private final List<ItemStack> output;
	
	private final List<EntityEntry> inputEntities;
	private final GlyphType[] circles = new GlyphType[3];
	
	private final int time, startingPower, runningPower;
	
	public Ritual(String modid, String name, List<Ingredient> inputItems, List<EntityEntry> inputEntities, List<ItemStack> output, int time, int startingPower, int runningPower, GlyphType small, GlyphType medium, GlyphType big)
	{
		this.setRegistryName(new ResourceLocation(modid, name));
		this.inputItems = inputItems;
		this.inputEntities = inputEntities;
		this.output = output;
		this.time = time;
		this.startingPower = startingPower;
		this.runningPower = runningPower;
		if (small == null) throw new IllegalArgumentException("Cannot have the smaller circle missing");
		if (medium == null && big != null) throw new IllegalArgumentException("Cannot have null middle circle when a big circle is present");
		if (small == GlyphType.GOLDEN || medium == GlyphType.GOLDEN || big == GlyphType.GOLDEN) throw new IllegalArgumentException("No golden circles allowed");
		circles[0] = small;
		circles[1] = medium;
		circles[2] = big;
	}
	
	public boolean canBePerformedRemotely()
	{
		return true;
	}
	
	/**
	 * @return an array of the circles used to perform the ritual.
	 */
	public GlyphType[] getCircles()
	{
		return circles;
	}
	
	/**
	 * @return the list of Entities to be used as an input.
	 */
	public List<EntityEntry> getInputEntities()
	{
		return inputEntities;
	}
	
	/**
	 * @return the list of ItemStacks to be used as an input.
	 */
	public List<Ingredient> getInputItems()
	{
		return inputItems;
	}
	
	/**
	 * @param tile the glyph performing the ritual
	 * 
	 * @return the output of the ritual.
	 */
	public List<ItemStack> getOutput(TileEntityGlyph tile)
	{
		List<ItemStack> out = new ArrayList<>();
		for (ItemStack stack : output) out.add(stack);
		if (tile != null)
		{
			for (int i = 0; i < tile.inventory.getSlots(); i++)
			{
				ItemStack stack = tile.inventory.extractItem(i, tile.inventory.getStackInSlot(i).getCount(), false);
				if (stack.getItem() == ModObjects.athame) stack.damageItem(50, tile.getWorld().getPlayerEntityByUUID(tile.getCaster()));
				else
				{
					for (Ingredient ing : getInputItems())
					{
						for (ItemStack stack0 : ing.getMatchingStacks())
						{
							if (Bewitchment.proxy.areStacksEqual(stack, stack0)) stack.shrink(stack0.getCount());
						}
					}
				}
				out.add(stack);
			}
		}
		return out;
	}
	
	/**
	 * @return the amount of power to be drained to start the ritual. If there is not enough power, the ritual is not started and the inputs are not consumed.
	 */
	public int getStartingPower()
	{
		return startingPower;
	}
	
	/**
	 * @return the amount of power drained per tick for the ritual to work.
	 */
	public int getRunningPower()
	{
		return runningPower;
	}
	
	/**
	 * @return the time in ticks of how long this ritual should last, or negative if infinite.
	 */
	public int getTime()
	{
		return time;
	}
	
	/**
	 * @param tile the glyph performing the ritual
	 * @param caster the player that started the ritual
	 * 
	 * @return true if the ritual is valid, otherwise false
	 */
	public boolean isValid(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time)
	{
		return true;
	}
	
	/**
	 * This method gets called every tick if the witches_altar doesn't have enough
	 * power to keep it running. This method is called in place of
	 * {@link #onUpdate(TileEntityGlyph, EntityPlayer)}
	 * 
	 * @param tile the glyph performing the ritual
	 * @param caster the player that started the ritual
	 */
	public boolean onLowPower(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time)
	{
		return false;
	}
	
	/**
	 * This method gets called every tick since the ritual was activated if it has
	 * enough power to run. If it doesn't,
	 * {@link #onLowPower(TileEntityGlyph, EntityPlayer)}
	 * gets called instead.
	 * 
	 * @param tile the glyph performing the ritual
	 * @param caster the player that started the ritual
	 */
	public void onUpdate(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time)
	{
	}
	
	/**
	 * This method gets called when the ritual time expires, before stopping
	 * automatically. This method is never called if
	 * {@link #onStopped(TileEntityGlyph, EntityPlayer)}
	 * is called.
	 * 
	 * @param tile the glyph performing the ritual
	 * @param caster the player that started the ritual
	 */
	public void onFinished(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time)
	{
		for (ItemStack stack : getOutput(tile)) InventoryHelper.spawnItemStack(world, tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ(), stack.copy());
	}
	
	/**
	 * This method gets called when the ritual is activated by a player.
	 *
	 * @param tile the glyph performing the ritual
	 * @param caster the player that started the ritual
	 */
	public void onStarted(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time)
	{
	}
	
	/**
	 * This method gets called when the ritual is stopped before completion by a
	 * player. This method is never called if
	 * {@link #onFinished(TileEntityGlyph, EntityPlayer)}
	 * is called.
	 * 
	 * @param tile the glyph performing the ritual
	 * @param caster the player that started the ritual
	 */
	public void onStopped(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time)
	{
		for (int i = 0; i < tile.inventory.getSlots(); i++) InventoryHelper.spawnItemStack(world, tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ(), tile.inventory.extractItem(i, tile.inventory.getStackInSlot(i).getCount(), false));
	}
	
	@SideOnly(Side.CLIENT)
	public void onRandomDisplayTick(TileEntityGlyph tile, World world, EntityPlayer caster, BlockPos pos, int dimension, int time)
	{
	}
	
	public final boolean matches(World world, BlockPos pos, List<ItemStack> ground, List<EntityLivingBase> living)
	{
		for (int x = 0; x < small.length; x++)
		{
			for (int z = 0; z < small.length; z++)
			{
				IBlockState state = world.getBlockState(pos.add(x - small.length / 2, 0, z - small.length / 2));
				if (small[x][z] == 1 && (state.getBlock() != ModObjects.glyph || (state.getValue(BlockGlyph.TYPE) != GlyphType.GOLDEN && (state.getValue(BlockGlyph.TYPE) != getCircles()[0] && getCircles()[0] != GlyphType.ANY)))) return false;
			}
		}
		if (getCircles()[1] != null)
		{
			for (int x = 0; x < medium.length; x++)
			{
				for (int z = 0; z < medium.length; z++)
				{
					IBlockState state = world.getBlockState(pos.add(x - medium.length / 2, 0, z - medium.length / 2));
					if (medium[x][z] == 1 && (state.getBlock() != ModObjects.glyph || (state.getValue(BlockGlyph.TYPE) != GlyphType.GOLDEN && (state.getValue(BlockGlyph.TYPE) != getCircles()[1] && getCircles()[1] != GlyphType.ANY)))) return false;
				}
			}
		}
		if (getCircles()[2] != null)
		{
			for (int x = 0; x < large.length; x++)
			{
				for (int z = 0; z < large.length; z++)
				{
					IBlockState state = world.getBlockState(pos.add(x - large.length / 2, 0, z - large.length / 2));
					if (large[x][z] == 1 && (state.getBlock() != ModObjects.glyph || (state.getValue(BlockGlyph.TYPE) != GlyphType.GOLDEN && (state.getValue(BlockGlyph.TYPE) != getCircles()[2] && getCircles()[2] != GlyphType.ANY)))) return false;
				}
			}
		}
		if (Bewitchment.proxy.areISListsEqual(getInputItems(), ground))
		{
			if (!getInputEntities().isEmpty())
			{
				boolean found = false;
				for (EntityLivingBase entity : living) if (getInputEntities().parallelStream().anyMatch(p -> p.getEntityClass().equals(entity.getClass()))) found = true;
				return found;
			}
			return true;
		}
		return false;
	}
}