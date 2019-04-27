package com.bewitchment.registry;

import java.util.Arrays;

import com.bewitchment.Bewitchment;
import com.bewitchment.Util;
import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.registry.DistilleryRecipe;
import com.bewitchment.api.registry.LoomRecipe;
import com.bewitchment.api.registry.OvenRecipe;
import com.bewitchment.api.registry.Ritual;
import com.bewitchment.common.block.BlockGlyph.GlyphType;
import com.bewitchment.common.entity.living.EntityBlindworm;
import com.bewitchment.common.entity.living.EntityLizard;
import com.bewitchment.common.entity.living.EntityNewt;
import com.bewitchment.common.entity.living.EntityOwl;
import com.bewitchment.common.entity.living.EntityRaven;
import com.bewitchment.common.entity.living.EntitySnake;
import com.bewitchment.common.entity.living.EntityToad;
import com.bewitchment.common.entity.spirits.demons.EntityAlphaHellhound;
import com.bewitchment.common.entity.spirits.demons.EntityDemon;
import com.bewitchment.common.entity.spirits.demons.EntityDemoness;
import com.bewitchment.common.entity.spirits.demons.EntityHellhound;
import com.bewitchment.common.entity.spirits.demons.EntitySerpent;
import com.bewitchment.common.entity.spirits.ghosts.EntityBlackDog;
import com.bewitchment.common.fortune.FortuneBadLuck;
import com.bewitchment.common.fortune.FortuneDeath;
import com.bewitchment.common.fortune.FortuneDropItem;
import com.bewitchment.common.fortune.FortuneGoodLuck;
import com.bewitchment.common.fortune.FortuneIllness;
import com.bewitchment.common.fortune.FortuneMeetBlaze;
import com.bewitchment.common.fortune.FortuneMeetCat;
import com.bewitchment.common.fortune.FortuneMeetDemon;
import com.bewitchment.common.fortune.FortuneMeetDireWolf;
import com.bewitchment.common.fortune.FortuneMeetDog;
import com.bewitchment.common.fortune.FortuneMeetDonkey;
import com.bewitchment.common.fortune.FortuneMeetHorse;
import com.bewitchment.common.fortune.FortuneMeetLlama;
import com.bewitchment.common.fortune.FortuneMeetMerchant;
import com.bewitchment.common.fortune.FortuneMeetParrot;
import com.bewitchment.common.fortune.FortuneMeetSerpent;
import com.bewitchment.common.fortune.FortuneMeetSilverfish;
import com.bewitchment.common.fortune.FortuneMeetWitch;
import com.bewitchment.common.fortune.FortuneMeetZombie;
import com.bewitchment.common.fortune.FortuneTreasure;
import com.bewitchment.common.fortune.FortuneVitality;
import com.bewitchment.common.ritual.RitualCallOfTheWild;
import com.bewitchment.common.ritual.RitualConjureAlphaHellhound;
import com.bewitchment.common.ritual.RitualConjureBlaze;
import com.bewitchment.common.ritual.RitualConjureDemon;
import com.bewitchment.common.ritual.RitualConjureGhast;
import com.bewitchment.common.ritual.RitualConjureHellhound;
import com.bewitchment.common.ritual.RitualConjureImp;
import com.bewitchment.common.ritual.RitualConjureMagmaCube;
import com.bewitchment.common.ritual.RitualConjureSerpent;
import com.bewitchment.common.ritual.RitualConjureVex;
import com.bewitchment.common.ritual.RitualConjureWitch;
import com.bewitchment.common.ritual.RitualConjureWither;
import com.bewitchment.common.ritual.RitualDrawing;
import com.bewitchment.common.ritual.RitualHighMoon;
import com.bewitchment.common.ritual.RitualHungryFlames;
import com.bewitchment.common.ritual.RitualPerception;
import com.bewitchment.common.ritual.RitualSandsOfTime;
import com.bewitchment.common.ritual.RitualSolarGlory;
import com.google.common.collect.Sets;

import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityElderGuardian;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityStray;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.entity.passive.EntityMule;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySkeletonHorse;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.passive.EntityZombieHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
	public static void initFurnace() {
		GameRegistry.addSmelting(ModObjects.silver_ore, new ItemStack(ModObjects.silver_ingot), 0.35f);
		GameRegistry.addSmelting(Blocks.SAPLING, new ItemStack(ModObjects.wood_ash, 4), 0.15f);
		GameRegistry.addSmelting(ModObjects.amethyst_ore, new ItemStack(ModObjects.amethyst), 0.35f);
		GameRegistry.addSmelting(ModObjects.garnet_ore, new ItemStack(ModObjects.garnet), 0.35f);
		GameRegistry.addSmelting(ModObjects.moonstone_ore, new ItemStack(ModObjects.moonstone), 0.35f);
		GameRegistry.addSmelting(ModObjects.golden_thread, new ItemStack(Items.GOLD_NUGGET), 1);
		GameRegistry.addSmelting(ModObjects.unfired_jar, new ItemStack(ModObjects.empty_jar), 0.45f);
	}

	public static void postInitAthame() {
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityPlayer.class), Sets.newHashSet(new ItemStack(ModObjects.heart), new ItemStack(Items.SKULL, 1, 3)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityVillager.class), Sets.newHashSet(new ItemStack(ModObjects.heart)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityZombieVillager.class), Sets.newHashSet(new ItemStack(ModObjects.spectral_dust)));

		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityWither.class), Sets.newHashSet(new ItemStack(ModObjects.spectral_dust, 6)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityElderGuardian.class), Sets.newHashSet(new ItemStack(ModObjects.eye_of_old, 4)));

		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityZombie.class), Sets.newHashSet(new ItemStack(Items.SKULL, 1, 2), new ItemStack(ModObjects.spectral_dust)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityPigZombie.class), Sets.newHashSet(new ItemStack(ModObjects.spectral_dust, 3), new ItemStack(ModObjects.hoof, 2)));

		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityHusk.class), Sets.newHashSet(new ItemStack(ModObjects.spectral_dust)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntitySkeleton.class), Sets.newHashSet(new ItemStack(Items.SKULL), new ItemStack(ModObjects.spectral_dust)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityWitherSkeleton.class), Sets.newHashSet(new ItemStack(Items.SKULL, 1, 1), new ItemStack(ModObjects.spectral_dust, 2)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityStray.class), Sets.newHashSet(new ItemStack(ModObjects.spectral_dust)));

		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntitySpider.class), Sets.newHashSet(new ItemStack(ModObjects.envenomed_fang, 2)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityCaveSpider.class), Sets.newHashSet(new ItemStack(ModObjects.envenomed_fang, 2)));

		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityCreeper.class), Sets.newHashSet(new ItemStack(Items.SKULL, 1, 4)));

		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityBlaze.class), Sets.newHashSet(new ItemStack(ModObjects.ectoplasm)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityGhast.class), Sets.newHashSet(new ItemStack(ModObjects.ectoplasm, 2)));

		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntitySilverfish.class), Sets.newHashSet(new ItemStack(ModObjects.silver_scales, 2)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityEndermite.class), Sets.newHashSet(new ItemStack(ModObjects.dimensional_sand, 2)));

		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityEnderman.class), Sets.newHashSet(new ItemStack(ModObjects.ectoplasm)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityVex.class), Sets.newHashSet(new ItemStack(ModObjects.ectoplasm, 4)));

		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityGuardian.class), Sets.newHashSet(new ItemStack(ModObjects.eye_of_old)));

		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityPig.class), Sets.newHashSet(new ItemStack(ModObjects.hoof, 4)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityCow.class), Sets.newHashSet(new ItemStack(ModObjects.hoof, 4)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntitySheep.class), Sets.newHashSet(new ItemStack(ModObjects.hoof, 4)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityWolf.class), Sets.newHashSet(new ItemStack(ModObjects.tongue_of_dog), new ItemStack(ModObjects.carnivorous_tooth, 2)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityOcelot.class), Sets.newHashSet(new ItemStack(ModObjects.carnivorous_tooth, 2)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityParrot.class), Sets.newHashSet(new ItemStack(ModObjects.chromatic_quill, 2)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityHorse.class), Sets.newHashSet(new ItemStack(ModObjects.hoof, 4)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityDonkey.class), Sets.newHashSet(new ItemStack(ModObjects.hoof, 4)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityMule.class), Sets.newHashSet(new ItemStack(ModObjects.hoof, 4)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntitySkeletonHorse.class), Sets.newHashSet(new ItemStack(ModObjects.spectral_dust, 2)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityLlama.class), Sets.newHashSet(new ItemStack(ModObjects.hoof, 4)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityZombieHorse.class), Sets.newHashSet(new ItemStack(ModObjects.spectral_dust)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityPolarBear.class), Sets.newHashSet(new ItemStack(ModObjects.carnivorous_tooth, 4)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityBat.class), Sets.newHashSet(new ItemStack(ModObjects.wool_of_bat, 3)));

		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityBlindworm.class), Sets.newHashSet(new ItemStack(ModObjects.blindworms_sting, 2)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityLizard.class), Sets.newHashSet(new ItemStack(ModObjects.lizard_leg, 4)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityNewt.class), Sets.newHashSet(new ItemStack(ModObjects.eye_of_newt, 2)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityOwl.class), Sets.newHashSet(new ItemStack(ModObjects.owlets_wing, 2)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityRaven.class), Sets.newHashSet(new ItemStack(ModObjects.ravens_feather, 4)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityToad.class), Sets.newHashSet(new ItemStack(ModObjects.toe_of_frog, 4)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntitySnake.class), Sets.newHashSet(new ItemStack(ModObjects.adders_fork, 3)));

		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityBlackDog.class), Sets.newHashSet(new ItemStack(ModObjects.tongue_of_dog), new ItemStack(ModObjects.ectoplasm, 4), new ItemStack(ModObjects.spectral_dust)));

		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityHellhound.class), Sets.newHashSet(new ItemStack(ModObjects.tongue_of_dog), new ItemStack(ModObjects.hellhound_horn, 2), new ItemStack(Items.BLAZE_POWDER, 4)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityAlphaHellhound.class), Sets.newHashSet(new ItemStack(ModObjects.tongue_of_dog), new ItemStack(ModObjects.hellhound_horn, 4), new ItemStack(Items.BLAZE_POWDER, 8)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntitySerpent.class), Sets.newHashSet(new ItemStack(ModObjects.adders_fork, 3)));

		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityDemon.class), Sets.newHashSet(new ItemStack(ModObjects.demonic_heart)));
		BewitchmentAPI.ATHAME_LOOT.put(EntityRegistry.getEntry(EntityDemoness.class), Sets.newHashSet(new ItemStack(ModObjects.demonic_heart)));
	}

	public static void initDistillery() {
		BewitchmentAPI.REGISTRY_DISTILLERY.register(new DistilleryRecipe(Bewitchment.MODID, "cleansing_balm",
				Arrays.asList(Ingredient.fromStacks(new ItemStack(ModObjects.acacia_resin)), Ingredient.fromStacks(new ItemStack(ModObjects.white_sage)), Ingredient.fromStacks(new ItemStack(ModObjects.salt))),
				Arrays.asList(new ItemStack(ModObjects.cleansing_balm), new ItemStack(ModObjects.wood_ash)),
				0, 300));
		BewitchmentAPI.REGISTRY_DISTILLERY.register(new DistilleryRecipe(Bewitchment.MODID, "demonic_elixir",
				Arrays.asList(Ingredient.fromStacks(new ItemStack(Items.BLAZE_POWDER)), Ingredient.fromStacks(new ItemStack(ModObjects.cloudy_oil, 2)), Ingredient.fromStacks(new ItemStack(ModObjects.demonic_heart)), Ingredient.fromStacks(new ItemStack(ModObjects.graveyard_dust))),
				Arrays.asList(new ItemStack(ModObjects.demonic_elixir, 2), new ItemStack(ModObjects.diabolical_vein, 1)),
				0, 300));
		BewitchmentAPI.REGISTRY_DISTILLERY.register(new DistilleryRecipe(Bewitchment.MODID, "everchanging_dew",
				Arrays.asList(Ingredient.fromStacks(new ItemStack(Items.DYE, 1, Short.MAX_VALUE)), Ingredient.fromStacks(new ItemStack(ModObjects.essence_of_vitality)), Ingredient.fromStacks(new ItemStack(Items.PAPER))),
				Arrays.asList(new ItemStack(ModObjects.everchanging_dew), new ItemStack(Items.SLIME_BALL, 3)),
				0, 300));
		BewitchmentAPI.REGISTRY_DISTILLERY.register(new DistilleryRecipe(Bewitchment.MODID, "fiery_unguent",
				Arrays.asList(Ingredient.fromStacks(new ItemStack(Items.BLAZE_POWDER)), Ingredient.fromStacks(new ItemStack(ModObjects.cloudy_oil)), Ingredient.fromStacks(new ItemStack(Blocks.OBSIDIAN)), Ingredient.fromStacks(new ItemStack(ModObjects.wood_ash))),
				Arrays.asList(new ItemStack(ModObjects.fiery_unguent)),
				0, 900));
		BewitchmentAPI.REGISTRY_DISTILLERY.register(new DistilleryRecipe(Bewitchment.MODID, "heaven_extract",
				Arrays.asList(Ingredient.fromStacks(new ItemStack(ModObjects.birch_soul)), Ingredient.fromStacks(new ItemStack(Items.GLOWSTONE_DUST)), Ingredient.fromStacks(new ItemStack(ModObjects.garnet)), Ingredient.fromStacks(new ItemStack(Items.QUARTZ))),
				Arrays.asList(new ItemStack(ModObjects.heaven_extract)),
				0, 900));
		BewitchmentAPI.REGISTRY_DISTILLERY.register(new DistilleryRecipe(Bewitchment.MODID, "otherworldly_tears",
				Arrays.asList(Ingredient.fromStacks(new ItemStack(ModObjects.birch_soul)), Ingredient.fromStacks(new ItemStack(Items.ENDER_PEARL)), Ingredient.fromStacks(new ItemStack(Items.DYE, 1, EnumDyeColor.BLUE.getDyeDamage()))),
				Arrays.asList(new ItemStack(ModObjects.dimensional_sand, 2), new ItemStack(ModObjects.otherworldly_tears)),
				0, 600));
		BewitchmentAPI.REGISTRY_DISTILLERY.register(new DistilleryRecipe(Bewitchment.MODID, "philter_of_dishonesty",
				Arrays.asList(Ingredient.fromStacks(new ItemStack(Items.BLAZE_POWDER)), Ingredient.fromStacks(new ItemStack(ModObjects.graveyard_dust)), Ingredient.fromStacks(new ItemStack(ModObjects.liquid_witchcraft)), Ingredient.fromStacks(new ItemStack(ModObjects.oak_apple_gall))),
				Arrays.asList(new ItemStack(ModObjects.philter_of_dishonesty), new ItemStack(ModObjects.spectral_dust, 3)),
				0, 300));
		BewitchmentAPI.REGISTRY_DISTILLERY.register(new DistilleryRecipe(Bewitchment.MODID, "stone_ichor",
				Arrays.asList(Ingredient.fromStacks(new ItemStack(ModObjects.coquina)), Ingredient.fromStacks(new ItemStack(ModObjects.liquid_witchcraft)), Ingredient.fromStacks(new ItemStack(Blocks.OBSIDIAN)), Ingredient.fromStacks(new ItemStack(Blocks.STONE))),
				Arrays.asList(new ItemStack(ModObjects.stone_ichor), new ItemStack(ModObjects.salt, 4)),
				0, 900));
		BewitchmentAPI.REGISTRY_DISTILLERY.register(new DistilleryRecipe(Bewitchment.MODID, "swirl_of_the_depths",
				Arrays.asList(Ingredient.fromStacks(new ItemStack(ModObjects.coquina)), Ingredient.fromStacks(new ItemStack(ModObjects.kelp)), Ingredient.fromStacks(new ItemStack(Items.DYE, 1, EnumDyeColor.BLUE.getDyeDamage())), Ingredient.fromStacks(new ItemStack(ModObjects.otherworldly_tears))),
				Arrays.asList(new ItemStack(Items.SLIME_BALL, 2), new ItemStack(ModObjects.swirl_of_the_depths)),
				0, 900));
		BewitchmentAPI.REGISTRY_DISTILLERY.register(new DistilleryRecipe(Bewitchment.MODID, "undying_salve",
				Arrays.asList(Ingredient.fromStacks(new ItemStack(ModObjects.ectoplasm)), Ingredient.fromStacks(new ItemStack(ModObjects.ebb_of_death)), Ingredient.fromStacks(new ItemStack(ModObjects.essence_of_vitality))),
				Arrays.asList(new ItemStack(ModObjects.ectoplasm, 2), new ItemStack(ModObjects.undying_salve, 2)),
				0, 300));
	}

	public static void initLoom() {
		BewitchmentAPI.REGISTRY_LOOM.register(new LoomRecipe(Bewitchment.MODID, "spider_web",
				Arrays.asList(Ingredient.fromStacks(new ItemStack(Items.STRING)), Ingredient.fromStacks(new ItemStack(Items.STRING)), Ingredient.fromStacks(new ItemStack(Items.STRING))),
				new ItemStack(Blocks.WEB)));
		BewitchmentAPI.REGISTRY_LOOM.register(new LoomRecipe(Bewitchment.MODID, "regal_silk",
				Arrays.asList(Ingredient.fromStacks(new ItemStack(ModObjects.chromatic_quill)), Ingredient.fromStacks(new ItemStack(Blocks.WEB)), Ingredient.fromStacks(new ItemStack(Blocks.WEB)), Ingredient.fromStacks(new ItemStack(ModObjects.everchanging_dew))),
				new ItemStack(ModObjects.regal_silk, 12)));
		BewitchmentAPI.REGISTRY_LOOM.register(new LoomRecipe(Bewitchment.MODID, "golden_thread",
				Arrays.asList(Ingredient.fromStacks(new ItemStack(Items.WHEAT)), Ingredient.fromStacks(new ItemStack(Items.WHEAT)), Ingredient.fromStacks(new ItemStack(Blocks.HAY_BLOCK)), Ingredient.fromStacks(new ItemStack(ModObjects.everchanging_dew))),
				new ItemStack(ModObjects.golden_thread, 3)));
		BewitchmentAPI.REGISTRY_LOOM.register(new LoomRecipe(Bewitchment.MODID, "witches_stitching",
				Arrays.asList(Ingredient.fromStacks(new ItemStack(Items.STRING)), Ingredient.fromStacks(new ItemStack(Items.STRING)), Ingredient.fromStacks(new ItemStack(ModObjects.oak_spirit)), Ingredient.fromStacks(new ItemStack(ModObjects.oak_spirit))),
				new ItemStack(ModObjects.witches_stitching, 4)));
		BewitchmentAPI.REGISTRY_LOOM.register(new LoomRecipe(Bewitchment.MODID, "diabolical_vein",
				Arrays.asList(Ingredient.fromStacks(new ItemStack(ModObjects.soul_string)), Ingredient.fromStacks(new ItemStack(ModObjects.demonic_heart)), Ingredient.fromStacks(new ItemStack(ModObjects.philter_of_dishonesty)), Ingredient.fromStacks(new ItemStack(ModObjects.fiery_unguent))),
				new ItemStack(ModObjects.diabolical_vein, 8)));
		BewitchmentAPI.REGISTRY_LOOM.register(new LoomRecipe(Bewitchment.MODID, "pure_filament",
				Arrays.asList(Ingredient.fromStacks(new ItemStack(ModObjects.witches_stitching)), Ingredient.fromStacks(new ItemStack(ModObjects.witches_stitching)), Ingredient.fromStacks(new ItemStack(ModObjects.cleansing_balm)), Ingredient.fromStacks(new ItemStack(ModObjects.cleansing_balm))),
				new ItemStack(ModObjects.pure_filament, 4)));
		BewitchmentAPI.REGISTRY_LOOM.register(new LoomRecipe(Bewitchment.MODID, "soul_string",
				Arrays.asList(Ingredient.fromStacks(new ItemStack(ModObjects.witches_stitching)), Ingredient.fromStacks(new ItemStack(ModObjects.witches_stitching)), Ingredient.fromStacks(new ItemStack(ModObjects.undying_salve)), Ingredient.fromStacks(new ItemStack(ModObjects.ectoplasm))),
				new ItemStack(ModObjects.soul_string, 2)));
		BewitchmentAPI.REGISTRY_LOOM.register(new LoomRecipe(Bewitchment.MODID, "sanguine_fabric",
				Arrays.asList(Ingredient.fromStacks(new ItemStack(ModObjects.diabolical_vein)), Ingredient.fromStacks(new ItemStack(ModObjects.diabolical_vein)), Ingredient.fromStacks(new ItemStack(ModObjects.diabolical_vein)), Ingredient.fromStacks(new ItemStack(ModObjects.diabolical_vein))),
				new ItemStack(ModObjects.sanguine_fabric, 4)));
	}

	public static void initOven() {
		BewitchmentAPI.REGISTRY_OVEN.register(new OvenRecipe(Bewitchment.MODID, "wheat",
				new ItemStack(Items.WHEAT),
				new ItemStack(Items.BREAD),
				new ItemStack(ModObjects.cloudy_oil),
				0.85f));
		BewitchmentAPI.REGISTRY_OVEN.register(new OvenRecipe(Bewitchment.MODID, "cactus",
				new ItemStack(Blocks.CACTUS),
				new ItemStack(Items.DYE, 1, 2),
				new ItemStack(ModObjects.cloudy_oil),
				0.85f));
		BewitchmentAPI.REGISTRY_OVEN.register(new OvenRecipe(Bewitchment.MODID, "chorus_fruit",
				new ItemStack(Items.CHORUS_FRUIT),
				new ItemStack(Items.CHORUS_FRUIT_POPPED),
				new ItemStack(ModObjects.dimensional_sand, 2),
				0.85f));
		BewitchmentAPI.REGISTRY_OVEN.register(new OvenRecipe(Bewitchment.MODID, "mandrake_root",
				new ItemStack(ModObjects.mandrake_root),
				new ItemStack(ModObjects.wood_ash),
				new ItemStack(ModObjects.cloudy_oil),
				0.85f));
		BewitchmentAPI.REGISTRY_OVEN.register(new OvenRecipe(Bewitchment.MODID, "sapling_oak",
				new ItemStack(Blocks.SAPLING),
				new ItemStack(ModObjects.wood_ash, 4),
				new ItemStack(ModObjects.oak_spirit),
				0.85f));
		BewitchmentAPI.REGISTRY_OVEN.register(new OvenRecipe(Bewitchment.MODID, "sapling_spruce",
				new ItemStack(Blocks.SAPLING, 1, 1),
				new ItemStack(ModObjects.wood_ash, 4),
				new ItemStack(ModObjects.spruce_heart),
				0.85f));
		BewitchmentAPI.REGISTRY_OVEN.register(new OvenRecipe(Bewitchment.MODID, "sapling_birch",
				new ItemStack(Blocks.SAPLING, 1, 2),
				new ItemStack(ModObjects.wood_ash, 4),
				new ItemStack(ModObjects.birch_soul),
				0.85f));
		BewitchmentAPI.REGISTRY_OVEN.register(new OvenRecipe(Bewitchment.MODID, "sapling_jungle",
				new ItemStack(Blocks.SAPLING, 1, 3),
				new ItemStack(ModObjects.wood_ash, 4),
				new ItemStack(ModObjects.cloudy_oil),
				0.85f));
		BewitchmentAPI.REGISTRY_OVEN.register(new OvenRecipe(Bewitchment.MODID, "sapling_acacia",
				new ItemStack(Blocks.SAPLING, 1, 4),
				new ItemStack(ModObjects.wood_ash, 4),
				new ItemStack(ModObjects.acacia_resin),
				0.85f));
		BewitchmentAPI.REGISTRY_OVEN.register(new OvenRecipe(Bewitchment.MODID, "sapling_dark_oak",
				new ItemStack(Blocks.SAPLING, 1, 5),
				new ItemStack(ModObjects.wood_ash, 4),
				new ItemStack(ModObjects.oak_spirit),
				0.85f));
		BewitchmentAPI.REGISTRY_OVEN.register(new OvenRecipe(Bewitchment.MODID, "sapling_cypress",
				new ItemStack(ModObjects.sapling_cypress),
				new ItemStack(ModObjects.wood_ash),
				new ItemStack(ModObjects.ebb_of_death),
				0.85f));
		BewitchmentAPI.REGISTRY_OVEN.register(new OvenRecipe(Bewitchment.MODID, "sapling_elder",
				new ItemStack(ModObjects.sapling_elder),
				new ItemStack(ModObjects.wood_ash),
				new ItemStack(ModObjects.droplet_of_wisdom),
				0.85f));
		BewitchmentAPI.REGISTRY_OVEN.register(new OvenRecipe(Bewitchment.MODID, "sapling_juniper",
				new ItemStack(ModObjects.sapling_juniper),
				new ItemStack(ModObjects.wood_ash),
				new ItemStack(ModObjects.liquid_witchcraft),
				0.85f));
		BewitchmentAPI.REGISTRY_OVEN.register(new OvenRecipe(Bewitchment.MODID, "sapling_yew",
				new ItemStack(ModObjects.sapling_yew),
				new ItemStack(ModObjects.wood_ash),
				new ItemStack(ModObjects.essence_of_vitality),
				0.85f));
		BewitchmentAPI.REGISTRY_OVEN.register(new OvenRecipe(Bewitchment.MODID, "rotten_flesh",
				new ItemStack(Items.ROTTEN_FLESH),
				new ItemStack(Items.LEATHER),
				new ItemStack(ModObjects.ectoplasm, 3),
				0.85f));
		BewitchmentAPI.REGISTRY_OVEN.register(new OvenRecipe(Bewitchment.MODID, "bone",
				new ItemStack(Items.BONE),
				new ItemStack(Items.DYE, 1, 15),
				new ItemStack(ModObjects.ectoplasm),
				0.85f));
	}

	public static void postInitOven() {
		for (ItemStack stack : FurnaceRecipes.instance().getSmeltingList().keySet()) {
			ItemStack output = FurnaceRecipes.instance().getSmeltingResult(stack);
			if (!BewitchmentAPI.REGISTRY_OVEN.getValuesCollection().stream().anyMatch(r -> stack.getItem() == r.getInput().getItem() && (stack.getMetadata() == r.getInput().getMetadata() || r.getInput().getMetadata() == Short.MAX_VALUE))) {
				ResourceLocation loc = new ResourceLocation(Bewitchment.MODID, stack.getItem().getRegistryName().getPath().toString() + stack.getMetadata());
				int index = 0;
				while (true) {
					if (BewitchmentAPI.REGISTRY_OVEN.containsKey(loc))
						loc = new ResourceLocation(loc.getNamespace(), loc.getPath() + index++);
					else break;
				}
				BewitchmentAPI.REGISTRY_OVEN.register(new OvenRecipe(loc.getNamespace(), loc.getPath(),
						stack,
						output,
						stack.getItem() instanceof ItemFood ? new ItemStack(ModObjects.cloudy_oil) : ItemStack.EMPTY,
						0.85f));
			}
		}
	}

	public static void initRitual() {
		BewitchmentAPI.REGISTRY_RITUAL.register(new RitualHighMoon());
		BewitchmentAPI.REGISTRY_RITUAL.register(new RitualSolarGlory());
		BewitchmentAPI.REGISTRY_RITUAL.register(new RitualSandsOfTime());
		BewitchmentAPI.REGISTRY_RITUAL.register(new RitualPerception());
		BewitchmentAPI.REGISTRY_RITUAL.register(new RitualConjureWitch());
		BewitchmentAPI.REGISTRY_RITUAL.register(new RitualConjureMagmaCube());
		BewitchmentAPI.REGISTRY_RITUAL.register(new RitualConjureVex());
		BewitchmentAPI.REGISTRY_RITUAL.register(new RitualConjureBlaze());
		BewitchmentAPI.REGISTRY_RITUAL.register(new RitualConjureGhast());
		BewitchmentAPI.REGISTRY_RITUAL.register(new RitualConjureWither());
		BewitchmentAPI.REGISTRY_RITUAL.register(new RitualConjureDemon());
		BewitchmentAPI.REGISTRY_RITUAL.register(new RitualConjureImp());
		BewitchmentAPI.REGISTRY_RITUAL.register(new RitualConjureHellhound());
		BewitchmentAPI.REGISTRY_RITUAL.register(new RitualConjureAlphaHellhound());
		BewitchmentAPI.REGISTRY_RITUAL.register(new RitualConjureSerpent());
		BewitchmentAPI.REGISTRY_RITUAL.register(new RitualCallOfTheWild());
		BewitchmentAPI.REGISTRY_RITUAL.register(new RitualHungryFlames());
		BewitchmentAPI.REGISTRY_RITUAL.register(new RitualDrawing("draw_small",
				Arrays.asList(
						Ingredient.fromStacks(new ItemStack(ModObjects.wood_ash))),
				40, 100, 0, GlyphType.ANY, null, null, Ritual.small));
		BewitchmentAPI.REGISTRY_RITUAL.register(new RitualDrawing("draw_medium",
				Arrays.asList(
						Ingredient.fromStacks(new ItemStack(Items.CLAY_BALL)),
						Ingredient.fromStacks(new ItemStack(ModObjects.wood_ash))),
				40, 100, 0, GlyphType.ANY, null, null, Ritual.medium));
		BewitchmentAPI.REGISTRY_RITUAL.register(new RitualDrawing("draw_large",
				Arrays.asList(
						Ingredient.fromStacks(new ItemStack(Items.CLAY_BALL)),
						Ingredient.fromStacks(new ItemStack(Items.CLAY_BALL)),
						Ingredient.fromStacks(new ItemStack(ModObjects.wood_ash)),
						Ingredient.fromStacks(new ItemStack(ModObjects.wood_ash))),
				40, 100, 0, GlyphType.ANY, GlyphType.ANY, null, Ritual.large));
		BewitchmentAPI.REGISTRY_RITUAL.register(new Ritual(Bewitchment.MODID, "crystal_ball",
				Arrays.asList(
						Ingredient.fromStacks(Util.getOres("gemQuartz")),
						Ingredient.fromStacks(Util.getOres("blockGlass")),
						Ingredient.fromStacks(Util.getOres("blockGlass")),
						Ingredient.fromStacks(Util.getOres("blockGlass")),
						Ingredient.fromStacks(Util.getOres("blockGlass")),
						Ingredient.fromStacks(new ItemStack(ModObjects.liquid_witchcraft))),
				Arrays.asList(),
				Arrays.asList(new ItemStack(ModObjects.crystal_ball)),
				50, 750, 3, GlyphType.NORMAL, GlyphType.ENDER, null));
		BewitchmentAPI.REGISTRY_RITUAL.register(new Ritual(Bewitchment.MODID, "tarot_table",
				Arrays.asList(
						Ingredient.fromStacks(Util.getOres("string")),
						Ingredient.fromStacks(Util.getOres("dye")),
						Ingredient.fromStacks(Util.getOres("workbench")),
						Ingredient.fromStacks(new ItemStack(ModObjects.droplet_of_wisdom)),
						Ingredient.fromStacks(new ItemStack(ModObjects.droplet_of_wisdom)),
						Ingredient.fromStacks(new ItemStack(ModObjects.liquid_witchcraft))),
				Arrays.asList(),
				Arrays.asList(new ItemStack(ModObjects.tarot_table)),
				50, 350, 1, GlyphType.NORMAL, GlyphType.NORMAL, null));
		BewitchmentAPI.REGISTRY_RITUAL.register(new Ritual(Bewitchment.MODID, "tarots_deck",
				Arrays.asList(
						Ingredient.fromStacks(Util.getOres("dye")),
						Ingredient.fromStacks(Util.getOres("dye")),
						Ingredient.fromStacks(Util.getOres("paper")),
						Ingredient.fromStacks(new ItemStack(ModObjects.birch_soul)),
						Ingredient.fromStacks(Util.getOres("materialWax", "materialBeeswax", "wax", "tallow", "materialPressedWax", "itemBeeswax", "clumpWax", "beeswax", "itemWax"))),
				Arrays.asList(),
				Arrays.asList(new ItemStack(ModObjects.tarots_deck)),
				50, 350, 1, GlyphType.NORMAL, null, null));
		BewitchmentAPI.REGISTRY_RITUAL.register(new Ritual(Bewitchment.MODID, "broom_cypress",
				Arrays.asList(
						Ingredient.fromStacks(new ItemStack(ModObjects.broom)),
						Ingredient.fromStacks(new ItemStack(ModObjects.log_cypress)),
						Ingredient.fromStacks(new ItemStack(ModObjects.sapling_cypress)),
						Ingredient.fromStacks(new ItemStack(ModObjects.undying_salve)),
						Ingredient.fromStacks(new ItemStack(Items.ELYTRA, 1, Short.MAX_VALUE))),
				Arrays.asList(),
				Arrays.asList(new ItemStack(ModObjects.broom_cypress)),
				130, 1000, 4, GlyphType.NORMAL, GlyphType.NORMAL, GlyphType.ENDER));
		BewitchmentAPI.REGISTRY_RITUAL.register(new Ritual(Bewitchment.MODID, "broom_elder",
				Arrays.asList(
						Ingredient.fromStacks(new ItemStack(ModObjects.broom)),
						Ingredient.fromStacks(new ItemStack(ModObjects.log_elder)),
						Ingredient.fromStacks(new ItemStack(ModObjects.sapling_elder)),
						Ingredient.fromStacks(new ItemStack(ModObjects.undying_salve)),
						Ingredient.fromStacks(new ItemStack(Items.ELYTRA, 1, Short.MAX_VALUE))),
				Arrays.asList(),
				Arrays.asList(new ItemStack(ModObjects.broom_elder)),
				130, 1000, 4, GlyphType.NORMAL, GlyphType.NORMAL, GlyphType.ENDER));
		BewitchmentAPI.REGISTRY_RITUAL.register(new Ritual(Bewitchment.MODID, "broom_juniper",
				Arrays.asList(
						Ingredient.fromStacks(new ItemStack(ModObjects.broom)),
						Ingredient.fromStacks(new ItemStack(ModObjects.log_juniper)),
						Ingredient.fromStacks(new ItemStack(ModObjects.sapling_juniper)),
						Ingredient.fromStacks(new ItemStack(ModObjects.undying_salve)),
						Ingredient.fromStacks(new ItemStack(Items.ELYTRA, 1, Short.MAX_VALUE))),
				Arrays.asList(),
				Arrays.asList(new ItemStack(ModObjects.broom_juniper)),
				130, 1000, 4, GlyphType.NORMAL, GlyphType.NORMAL, GlyphType.ENDER));
		BewitchmentAPI.REGISTRY_RITUAL.register(new Ritual(Bewitchment.MODID, "broom_yew",
				Arrays.asList(
						Ingredient.fromStacks(new ItemStack(ModObjects.broom)),
						Ingredient.fromStacks(new ItemStack(ModObjects.log_yew)),
						Ingredient.fromStacks(new ItemStack(ModObjects.sapling_yew)),
						Ingredient.fromStacks(new ItemStack(ModObjects.undying_salve)),
						Ingredient.fromStacks(new ItemStack(Items.ELYTRA, 1, Short.MAX_VALUE))),
				Arrays.asList(),
				Arrays.asList(new ItemStack(ModObjects.broom_yew)),
				130, 1000, 4, GlyphType.NORMAL, GlyphType.NORMAL, GlyphType.ENDER));
		BewitchmentAPI.REGISTRY_RITUAL.register(new Ritual(Bewitchment.MODID, "sanctuary",
				Arrays.asList(
						Ingredient.fromStacks(new ItemStack(ModObjects.white_sage)),
						Ingredient.fromStacks(new ItemStack(ModObjects.salt)),
						Ingredient.fromStacks(Util.getOres("salt")),
						Ingredient.fromStacks(Util.getOres("dirt")),
						Ingredient.fromStacks(Util.getOres("dirt")),
						Ingredient.fromStacks(Util.getOres("dirt"))),
				Arrays.asList(),
				Arrays.asList(new ItemStack(ModObjects.purifying_earth, 16)),
				250, 500, 4, GlyphType.NORMAL, GlyphType.NORMAL, null));
	}

	public static void initFortune() {
		BewitchmentAPI.REGISTRY_FORTUNE.register(new FortuneBadLuck());
		BewitchmentAPI.REGISTRY_FORTUNE.register(new FortuneDeath());
		BewitchmentAPI.REGISTRY_FORTUNE.register(new FortuneDropItem());
		BewitchmentAPI.REGISTRY_FORTUNE.register(new FortuneGoodLuck());
		BewitchmentAPI.REGISTRY_FORTUNE.register(new FortuneIllness());
		BewitchmentAPI.REGISTRY_FORTUNE.register(new FortuneMeetCat());
		BewitchmentAPI.REGISTRY_FORTUNE.register(new FortuneMeetDog());
		BewitchmentAPI.REGISTRY_FORTUNE.register(new FortuneMeetHorse());
		BewitchmentAPI.REGISTRY_FORTUNE.register(new FortuneMeetLlama());
		BewitchmentAPI.REGISTRY_FORTUNE.register(new FortuneMeetParrot());
		BewitchmentAPI.REGISTRY_FORTUNE.register(new FortuneMeetDonkey());
		BewitchmentAPI.REGISTRY_FORTUNE.register(new FortuneVitality());
		BewitchmentAPI.REGISTRY_FORTUNE.register(new FortuneMeetBlaze());
		BewitchmentAPI.REGISTRY_FORTUNE.register(new FortuneMeetSerpent());
		BewitchmentAPI.REGISTRY_FORTUNE.register(new FortuneMeetDemon());
		BewitchmentAPI.REGISTRY_FORTUNE.register(new FortuneTreasure());
		BewitchmentAPI.REGISTRY_FORTUNE.register(new FortuneMeetWitch());
		BewitchmentAPI.REGISTRY_FORTUNE.register(new FortuneMeetSilverfish());
		BewitchmentAPI.REGISTRY_FORTUNE.register(new FortuneMeetZombie());
		BewitchmentAPI.REGISTRY_FORTUNE.register(new FortuneMeetMerchant());
		BewitchmentAPI.REGISTRY_FORTUNE.register(new FortuneMeetDireWolf());
	}
}