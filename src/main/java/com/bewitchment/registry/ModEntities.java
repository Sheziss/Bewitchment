package com.bewitchment.registry;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.entity.living.*;
import com.bewitchment.common.entity.misc.*;
import com.bewitchment.common.entity.spirits.demons.*;
import com.bewitchment.common.entity.spirits.ghosts.EntityBlackDog;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModEntities {
	public static final List<EntityEntry> REGISTRY = new ArrayList<>();

	private static int entity_id = 0;

	public static EntityEntry spell;

	public static EntityEntry cypress_broom;
	public static EntityEntry elder_broom;
	public static EntityEntry juniper_broom;
	public static EntityEntry yew_broom;

	public static EntityEntry blindworm;
	public static EntityEntry lizard;
	public static EntityEntry newt;
	public static EntityEntry owl;
	public static EntityEntry raven;
	public static EntityEntry snake;
	public static EntityEntry toad;

	public static EntityEntry black_dog;

	public static EntityEntry hellhound;
	public static EntityEntry alpha_hellhound;
	public static EntityEntry serpent;

	public static EntityEntry demon;
	public static EntityEntry demoness;
	public static EntityEntry imp;

	public static void postInit() {
		spell         = createEntityEntry(EntitySpell.class, "spell");
		cypress_broom = createEntityEntry(EntityCypressBroom.class, "cypress_broom");
		elder_broom   = createEntityEntry(EntityElderBroom.class, "elder_broom");
		juniper_broom = createEntityEntry(EntityJuniperBroom.class, "juniper_broom");
		yew_broom     = createEntityEntry(EntityYewBroom.class, "yew_broom");

		blindworm = createEntityEntry(EntityBlindworm.class, "blindworm", 0x826644, 0xd2b48c, EnumCreatureType.CREATURE, 20, 1, 4, Bewitchment.proxy.config.blindwormBiomes);
		lizard    = createEntityEntry(EntityLizard.class, "lizard", 0x568203, 0x0070bb, EnumCreatureType.CREATURE, 20, 1, 4, Bewitchment.proxy.config.lizardBiomes);
		newt      = createEntityEntry(EntityNewt.class, "newt", 0x000000, 0xffd300, EnumCreatureType.CREATURE, 20, 1, 4, Bewitchment.proxy.config.newtBiomes);
		owl       = createEntityEntry(EntityOwl.class, "owl", 0xaf813f, 0x6e5127, EnumCreatureType.CREATURE, 20, 1, 4, Bewitchment.proxy.config.owlBiomes);
		raven     = createEntityEntry(EntityRaven.class, "raven", 0x222222, 0x280638, EnumCreatureType.CREATURE, 20, 1, 4, Bewitchment.proxy.config.ravenBiomes);
		snake     = createEntityEntry(EntitySnake.class, "snake", 0xfF9779, 0x696969, EnumCreatureType.CREATURE, 20, 1, 4, Bewitchment.proxy.config.snakeBiomes);
		toad      = createEntityEntry(EntityToad.class, "toad", 0xa9ba9d, 0xc3b091, EnumCreatureType.CREATURE, 20, 1, 4, Bewitchment.proxy.config.toadBiomes);

		black_dog = createEntityEntry(EntityBlackDog.class, "black_dog", 0x000000, 0x000000, EnumCreatureType.MONSTER, 6, 1, 4, Bewitchment.proxy.config.blackDogBiomes);

		hellhound       = createEntityEntry(EntityHellhound.class, "hellhound", 0x555555, 0xed2939, EnumCreatureType.MONSTER, 6, 1, 4, Bewitchment.proxy.config.hellhoundBiomes);
		alpha_hellhound = createEntityEntry(EntityAlphaHellhound.class, "alpha_hellhound", 0x555555, 0xed2939, EnumCreatureType.MONSTER, 1, 1, 4, Bewitchment.proxy.config.alphaHellhoundBiomes);
		serpent         = createEntityEntry(EntitySerpent.class, "serpent", 0x555555, 0xff9966, EnumCreatureType.MONSTER, 6, 1, 4, Bewitchment.proxy.config.serpentBiomes);

		demon    = createEntityEntry(EntityDemon.class, "demon", 0x555555, 0xed2939);
		demoness = createEntityEntry(EntityDemoness.class, "demoness", 0x555555, 0xed2939);
		imp      = createEntityEntry(EntityImp.class, "imp", 0x555555, 0xed2939);
		for (EntityEntry entry : REGISTRY) ForgeRegistries.ENTITIES.register(entry);
	}

	private static final EntityEntry createEntityEntry(Class<? extends Entity> clazz, String name) {
		EntityEntry entry = EntityEntryBuilder.create().entity(clazz).id(new ResourceLocation(Bewitchment.MODID, name), entity_id++).name(Bewitchment.MODID + "." + name).tracker(64, 1, true).build();
		REGISTRY.add(entry);
		return entry;
	}

	private static final EntityEntry createEntityEntry(Class<? extends Entity> clazz, String name, int solidColor, int spotColor) {
		EntityEntry entry = EntityEntryBuilder.create().entity(clazz).id(new ResourceLocation(Bewitchment.MODID, name), entity_id++).name(Bewitchment.MODID + "." + name).tracker(64, 1, true).egg(solidColor, spotColor).build();
		REGISTRY.add(entry);
		return entry;
	}

	private static final EntityEntry createEntityEntry(Class<? extends Entity> clazz, String name, int solidColor, int spotColor, EnumCreatureType type, int weight, int min, int max, List<String> types) {
		Set<Biome> biomes = new HashSet<>();
		for (String typeName : types)
			for (Biome biome : BiomeDictionary.getBiomes(Type.getType(typeName))) biomes.add(biome);
		EntityEntry entry = EntityEntryBuilder.create().entity(clazz).id(new ResourceLocation(Bewitchment.MODID, name), entity_id++).name(Bewitchment.MODID + "." + name).tracker(64, 1, true).egg(solidColor, spotColor).spawn(type, weight, min, max, biomes).build();
		REGISTRY.add(entry);
		return entry;
	}
}