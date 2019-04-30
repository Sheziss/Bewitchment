package com.bewitchment.common.entity.misc;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.registry.Spell;
import com.bewitchment.api.registry.Spell.SpellType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;

import java.util.UUID;

public class EntitySpell extends EntityThrowable {
	private static final DataParameter<String> SPELL = EntityDataManager.createKey(EntitySpell.class, DataSerializers.STRING), CASTER = EntityDataManager.createKey(EntitySpell.class, DataSerializers.STRING);

	public EntitySpell(World world) {
		super(world);
	}

	public EntitySpell(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if (ticksExisted > 40) setDead();
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (!world.isRemote) {
			Spell spell = BewitchmentAPI.REGISTRY_SPELL.getValue(new ResourceLocation(dataManager.get(SPELL)));
			EntityLivingBase caster = getCaster();
			if (spell != null) {
				if (result.typeOfHit != Type.ENTITY || result.entityHit != caster)
					spell.performEffect(world, result, caster);
				if (result.typeOfHit == Type.BLOCK && (spell.getType() == SpellType.BLOCK || spell.getType() == SpellType.ALL) && result.entityHit != caster)
					setDead();
				if (result.typeOfHit == Type.ENTITY && (spell.getType() == SpellType.ENTITY || spell.getType() == SpellType.ALL) && result.entityHit != caster)
					setDead();
			}
		}
	}

	@Override
	protected void entityInit() {
		setEntityInvulnerable(true);
		setNoGravity(true);
		setSize(0.1f, 0.1f);
		dataManager.register(SPELL, "");
		dataManager.register(CASTER, "");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		tag.setString("spell", dataManager.get(SPELL));
		tag.setString("caster", dataManager.get(CASTER));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		tag.setString("spell", dataManager.get(SPELL));
		tag.setString("caster", dataManager.get(CASTER));
	}

	public EntityLivingBase getCaster() {
		String uuid = dataManager.get(CASTER);
		if (uuid == null || uuid.isEmpty()) return null;
		EntityLivingBase player = world.getPlayerEntityByUUID(UUID.fromString(uuid));
		if (player != null) return player;
		for (Entity entity : world.getLoadedEntityList())
			if (entity instanceof EntityLivingBase && uuid.equals(entity.getUniqueID().toString()))
				return (EntityLivingBase) entity;
		return null;
	}
}