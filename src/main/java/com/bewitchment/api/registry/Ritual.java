package com.bewitchment.api.registry;

import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.block.tile.entity.TileEntityGlyph;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class Ritual extends IForgeRegistryEntry.Impl<Ritual>
{
	private final NonNullList<Ingredient> inputItems;
	private final ItemStack[] output;
	
	private final EntityEntry[] inputEntities;
	private final GlyphType[] circles = new GlyphType[3];
	
	private final int time, startingPower, runningPower;
	
	public Ritual(String modid, String name, NonNullList<Ingredient> inputItems, EntityEntry[] inputEntities, ItemStack[] output, int time, int startingPower, int runningPower, GlyphType small, GlyphType medium, GlyphType big)
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
	public EntityEntry[] getInputEntities()
	{
		return inputEntities;
	}
	
	/**
	 * @return the list of ItemStacks to be used as an input.
	 */
	public NonNullList<Ingredient> getInputItems()
	{
		return inputItems;
	}
	
	/**
	 * @param tile the glyph performing the ritual
	 * 
	 * @return the output of the ritual.
	 */
	public ItemStack[] getOutput(TileEntityGlyph tile)
	{
		return output;
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
	public boolean isValid(TileEntityGlyph tile, EntityPlayer caster)
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
	public boolean onLowPower(TileEntityGlyph tile, EntityPlayer caster)
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
	public void onUpdate(TileEntityGlyph tile, EntityPlayer caster)
	{
	}
	
	@SideOnly(Side.CLIENT)
	public void onRandomDisplayTick(TileEntityGlyph tile)
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
	public void onFinished(TileEntityGlyph tile, EntityPlayer caster)
	{
	}
	
	/**
	 * This method gets called when the ritual is activated by a player.
	 *
	 * @param tile the glyph performing the ritual
	 * @param caster the player that started the ritual
	 */
	public void onStarted(TileEntityGlyph tile, EntityPlayer caster)
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
	public void onStopped(TileEntityGlyph tile, EntityPlayer caster)
	{
	}
	
	public static NonNullList<Ingredient> ofi(Ingredient... ingredients)
	{
		return NonNullList.from(Ingredient.EMPTY, ingredients);
	}
	
	public static ItemStack[] ofs()
	{
//		return ofi(Ingredient.fromStacks(OreDictionary.getOres(oreDictionaryName).toArray(new ItemStack[1]))).toar;
		return new ItemStack[1];
	}
	
	public static EntityEntry[] ofe(EntityEntry... entries)
	{
		return entries;
	}
}