package com.bewitchment.api;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.*;
import com.bewitchment.common.block.tile.entity.TileEntityWitchesAltar;
import com.bewitchment.common.item.tool.ItemAthame;
import com.bewitchment.registry.ModObjects;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.Collection;

/**
 * The Bewitchment API, use this for creating addons.
 */
public class BewitchmentAPI {
	public static final IForgeRegistry<DistilleryRecipe> REGISTRY_DISTILLERY = new RegistryBuilder<DistilleryRecipe>().setName(ModObjects.distillery.getRegistryName()).setType(DistilleryRecipe.class).create();
	public static final IForgeRegistry<LoomRecipe> REGISTRY_LOOM = new RegistryBuilder<LoomRecipe>().setName(ModObjects.loom.getRegistryName()).setType(LoomRecipe.class).create();
	public static final IForgeRegistry<OvenRecipe> REGISTRY_OVEN = new RegistryBuilder<OvenRecipe>().setName(ModObjects.oven.getRegistryName()).setType(OvenRecipe.class).create();
	public static final IForgeRegistry<Ritual> REGISTRY_RITUAL = new RegistryBuilder<Ritual>().setName(ModObjects.glyph.getRegistryName()).setType(Ritual.class).create();
	public static final IForgeRegistry<Spell> REGISTRY_SPELL = new RegistryBuilder<Spell>().setName(new ResourceLocation(Bewitchment.MOD_ID, "spell")).setType(Spell.class).create();

	public static final IForgeRegistry<Fortune> REGISTRY_FORTUNE = new RegistryBuilder<Fortune>().setName(new ResourceLocation(Bewitchment.MOD_ID, "fortune")).setType(Fortune.class).create();

	/**
	 * The Demon creature attribute.
	 */
	public static EnumCreatureAttribute DEMON = EnumHelper.addCreatureAttribute("DEMON");

	/**
	 * The Spirit creature attribute.
	 */
	public static EnumCreatureAttribute SPIRIT = EnumHelper.addCreatureAttribute("SPIRIT");

	/**
	 * Gets the scan value associated with a block
	 *
	 * @param block the block to be checked
	 * @return the scan value of the block
	 */
	public static final int getAltarScanValue(Block block) {
		return TileEntityWitchesAltar.SCAN_VALUES.getOrDefault(block, 0);
	}

	/**
	 * Gets the sword multiplier value associated with a block
	 *
	 * @param item the item to be checked
	 * @return the multiplier value of the item
	 */
	public static final double getAltarSwordMultiplierValue(Item item) {
		return TileEntityWitchesAltar.SWORD_MULTIPLIER_VALUES.getOrDefault(item, 0d);
	}

	/**
	 * Gets the sword radius value associated with a block
	 *
	 * @param item the item to be checked
	 * @return the radius value of the item
	 */
	public static final int getAltarSwordRadiusValue(Item item) {
		return TileEntityWitchesAltar.SWORD_RADIUS_VALUES.getOrDefault(item, 0);
	}

	/**
	 * Registers a new scan value for the Witches' Altar.
	 *
	 * @param block the block to be registered
	 * @param power the value associated with the block
	 */
	public static final void registerAltarScanValue(Block block, int power) {
		TileEntityWitchesAltar.SCAN_VALUES.put(block, power);
	}

	/**
	 * Registers a new sword multiplier value for the Witches' Altar.
	 *
	 * @param item              the item to be registered
	 * @param varietyMultiplier the multiplier associated with the item
	 */
	public static final void registerAltarSwordMultiplier(Item item, double varietyMultiplier) {
		TileEntityWitchesAltar.SWORD_MULTIPLIER_VALUES.put(item, varietyMultiplier);
	}

	/**
	 * Registers a new sword radius value for the Witches' Altar.
	 *
	 * @param item   the item to be registered
	 * @param radius the radius associated with the item
	 */
	public static final void registerAltarSwordRadius(Item item, int radius) {
		TileEntityWitchesAltar.SWORD_RADIUS_VALUES.put(item, radius);
	}

	/**
	 * Registers a new special drop for the Athame
	 *
	 * @param entry the EntityEntry associated with the drop
	 * @param drops the list of ItemStacks dropped when the Entity is killed with the Athame
	 */
	public static final void registerAthameLoot(EntityEntry entry, Collection<ItemStack> drops) {
		ItemAthame.LOOT_TABLE.put(entry, drops);
	}

	/**
	 * Registers a new DistilleryRecipe, for use in the Distillery.
	 *
	 * @param recipe the recipe to register
	 * @return the recipe registered
	 */
	public static final DistilleryRecipe registerDistilleryRecipe(DistilleryRecipe recipe) {
		REGISTRY_DISTILLERY.register(recipe);
		return recipe;
	}

	/**
	 * Registers a new LoomRecipe, for use with the Loom.
	 *
	 * @param recipe the recipe to register
	 * @return the recipe registered
	 */
	public static final LoomRecipe registerLoomRecipe(LoomRecipe recipe) {
		REGISTRY_LOOM.register(recipe);
		return recipe;
	}

	/**
	 * Registers a new OvenRecipe, for use in the Oven.
	 *
	 * @param recipe the recipe to register
	 * @return the recipe registered
	 */
	public static final OvenRecipe registerOvenRecipe(OvenRecipe recipe) {
		REGISTRY_OVEN.register(recipe);
		return recipe;
	}

	/**
	 * Registers a new Ritual, for use with rituals.
	 *
	 * @param ritual the ritual to register
	 * @return the ritual registered
	 */
	public static final Ritual registerRitual(Ritual ritual) {
		REGISTRY_RITUAL.register(ritual);
		return ritual;
	}

	/**
	 * Registers a new Spell.
	 *
	 * @param recipe the spell to register
	 * @return the spell registered
	 */
	public static final Spell registerSpell(Spell spell) {
		REGISTRY_SPELL.register(spell);
		return spell;
	}

	/**
	 * Registers a new Fortune.
	 *
	 * @param recipe the fortune to register
	 * @return the fortune registered
	 */
	public static final Fortune registerFortune(Fortune fortune) {
		REGISTRY_FORTUNE.register(fortune);
		return fortune;
	}
}