package com.bewitchment.api.registry;

import com.bewitchment.Bewitchment;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class Spell extends IForgeRegistryEntry.Impl<Spell>
{
	public enum SpellType
	{
		INSTANT, BLOCK, ENTITY, ALL;
	}
	
	private final SpellType type;
	private final String name;
	
	private final int color, cost;
	
	public Spell(String name, int cost, int color, SpellType type)
	{
		this.setRegistryName(new ResourceLocation(Bewitchment.MOD_ID, name));
		this.name = getRegistryName().toString();
		this.cost = cost;
		this.color = color;
		this.type = type;
	}
	
	public abstract boolean canBeUsed(World world, BlockPos pos, EntityLivingBase caster);
	
	/**
	 * @return the color of the spell
	 */
	public int getColor()
	{
		return color;
	}
	
	/**
	 * @return the cost of the spell
	 */
	public int getCost()
	{
		return cost;
	}
	
	/**
	 * @return the name of the spell
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * @return the type of the spell
	 */
	public SpellType getType()
	{
		return type;
	}
	
	public abstract void performEffect(World world, RayTraceResult result, EntityLivingBase caster);
}