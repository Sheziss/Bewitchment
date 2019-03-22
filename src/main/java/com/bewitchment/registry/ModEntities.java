package com.bewitchment.registry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bewitchment.Bewitchment;
import com.bewitchment.common.entity.hostile.EntityAlphaHellhound;
import com.bewitchment.common.entity.hostile.EntityHellhound;
import com.bewitchment.common.entity.hostile.EntitySerpent;
import com.bewitchment.common.entity.living.EntityBlindworm;
import com.bewitchment.common.entity.living.EntityLizard;
import com.bewitchment.common.entity.living.EntityNewt;
import com.bewitchment.common.entity.living.EntityOwl;
import com.bewitchment.common.entity.living.EntityRaven;
import com.bewitchment.common.entity.living.EntitySnake;
import com.bewitchment.common.entity.living.EntityToad;
import com.bewitchment.common.entity.misc.EntityBeeSwarm;
import com.bewitchment.common.entity.misc.EntityBroom;
import com.bewitchment.common.entity.misc.EntitySpell;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@EventBusSubscriber(modid = Bewitchment.MOD_ID)
public class ModEntities
{
	public static final List<EntityEntry> REGISTRY = new ArrayList<>();
	
	private static int entity_id = 0;
	
	public static final EntityEntry bee_swarm = createEntityEntry(EntityBeeSwarm.class, "bee_swarm");
	public static final EntityEntry spell = createEntityEntry(EntitySpell.class, "spell");

	public static final EntityEntry entity_broom = createEntityEntry(EntityBroom.class, "broom");
	
	public static final EntityEntry entity_blindworm = createEntityEntry(EntityBlindworm.class, "blindworm", 0x826644, 0xd2b48c, EnumCreatureType.CREATURE, 20, 1, 4, Type.FOREST);
	public static final EntityEntry entity_lizard = createEntityEntry(EntityLizard.class, "lizard", 0x568203, 0x0070bb, EnumCreatureType.CREATURE, 20, 1, 4, Type.FOREST);
	public static final EntityEntry entity_newt = createEntityEntry(EntityNewt.class, "newt", 0x000000, 0xffd300, EnumCreatureType.CREATURE, 20, 1, 4, Type.SWAMP);
	public static final EntityEntry entity_owl = createEntityEntry(EntityOwl.class, "owl", 0xaf813f, 0x6e5127, EnumCreatureType.CREATURE, 20, 1, 4, Type.FOREST, Type.DENSE);
	public static final EntityEntry entity_raven = createEntityEntry(EntityRaven.class, "raven", 0x222222, 0x280638, EnumCreatureType.CREATURE, 20, 1, 4, Type.PLAINS, Type.WASTELAND);
	public static final EntityEntry entity_snake = createEntityEntry(EntitySnake.class, "snake", 0xfF9779, 0x696969, EnumCreatureType.CREATURE, 20, 1, 4, Type.PLAINS, Type.HILLS);
	public static final EntityEntry entity_toad = createEntityEntry(EntityToad.class, "toad", 0xa9ba9d, 0xc3b091, EnumCreatureType.CREATURE, 20, 1, 4, Type.SWAMP);
	
	public static final EntityEntry entity_hellhound = createEntityEntry(EntityHellhound.class, "hellhound", 0x555555, 0xed2939, EnumCreatureType.MONSTER, 6, 1, 4, Type.NETHER);
	public static final EntityEntry entity_alpha_hellhound = createEntityEntry(EntityAlphaHellhound.class, "alpha_hellhound", 0x555555, 0xed2939, EnumCreatureType.MONSTER, 1, 1, 4, Type.NETHER);
	public static final EntityEntry entity_serpent = createEntityEntry(EntitySerpent.class, "serpent", 0x555555, 0xff9966, EnumCreatureType.MONSTER, 6, 1, 4, Type.NETHER);

	@SubscribeEvent
	public static void registerEntities(Register<EntityEntry> event)
	{
		for (EntityEntry entry : REGISTRY) event.getRegistry().register(entry);
	}
	
	private static final EntityEntry createEntityEntry(Class<? extends Entity> clazz, String name)
	{
		EntityEntry entry = EntityEntryBuilder.create().entity(clazz).id(new ResourceLocation(Bewitchment.MOD_ID, name), entity_id++).name(name).tracker(64, 1, true).build();
		REGISTRY.add(entry);
		return entry;
	}
	
	private static final EntityEntry createEntityEntry(Class<? extends Entity> clazz, String name, int solidColor, int spotColor, EnumCreatureType type, int weight, int min, int max, Type... biomeTypes)
	{
		Set<Biome> biomes = new HashSet<>();
		for (Biome biome : ForgeRegistries.BIOMES) for (Type biomeType : biomeTypes) if (BiomeDictionary.hasType(biome, biomeType)) biomes.add(biome);
		EntityEntry entry = EntityEntryBuilder.create().entity(clazz).id(new ResourceLocation(Bewitchment.MOD_ID, name), entity_id++).name(name).tracker(64, 1, true).egg(solidColor, spotColor).spawn(type, weight, min, max, biomes).build();
		REGISTRY.add(entry);
		return entry;
	}
}