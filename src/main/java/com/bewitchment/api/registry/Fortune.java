package com.bewitchment.api.registry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class Fortune extends IForgeRegistryEntry.Impl<Fortune> {
	private final int chance;

	public Fortune(String modid, String name, int chance) {
		this.setRegistryName(new ResourceLocation(modid, name));
		this.chance = chance;
	}

	/**
	 * @return how many times the fortune should count for
	 */
	public int getChance() {
		return chance;
	}

	/**
	 * @param player the player that the fortune should effect
	 * @return true if the fortune should be removed, false otherwise
	 */
	public abstract boolean apply(EntityPlayer player);

	/**
	 * @param player the player that the fortune should effect
	 * @return true if the fortune can be used, false otherwise
	 */
	public abstract boolean canBeUsed(EntityPlayer player);

	/**
	 * @param player the player that the fortune should effect
	 * @return true if the fortune should take effect immediately, false otherwise
	 */
	public abstract boolean isInstant(EntityPlayer player);

	/**
	 * @return true if this fortune causes ill effects, false otherwise
	 */
	public boolean isNegative() {
		return false;
	}
}