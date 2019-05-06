package com.bewitchment.common.block.tile.entity;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.api.registry.Ritual;
import com.bewitchment.common.block.tile.entity.util.TileEntityAltarStorage;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TileEntityGlyph extends TileEntityAltarStorage implements ITickable {
	public final ItemStackHandler inventory = new ItemStackHandler(Byte.MAX_VALUE) {
		@Override
		public int getSlotLimit(int slot) {
			return Integer.MAX_VALUE;
		}
	};
	private Ritual ritual;
	private BlockPos effectivePos = getPos();
	private UUID caster;
	private int cooldown = -1, effectiveDim = 0;

	@Override
	public void update() {
		if (ritual != null && caster != null) {
			EntityPlayer player = world.getPlayerEntityByUUID(caster);
			if (cooldown > ritual.getTime()) {
				cooldown--;
				if (cooldown % 20 == 0) {
					List<EntityItem> entities = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(getPos()).grow(3, 0, 3));
					if (!entities.isEmpty()) {
						if (!world.isRemote) {
							inventory.insertItem(getFirstEmptySlot(inventory), entities.get(0).getItem().splitStack(entities.get(0).getItem().getCount()), false);
							world.playSound(null, getPos(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.7f, 0.7f);
						} else for (int i = 0; i < 20; i++)
							world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, entities.get(0).posX + world.rand.nextGaussian() / 3, entities.get(0).posY + world.rand.nextGaussian() / 3, entities.get(0).posZ + world.rand.nextGaussian() / 3, 0, 0, 0);
					}
				}
				if (!world.isRemote && cooldown == ritual.getTime()) {
					List<EntityLivingBase> living_on_ground = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(getPos()).grow(3, 0, 3));
					ritual.onStarted(this, getWorld(), player, getEffectivePos(), getEffectiveDim(), cooldown);
					world.playSound(null, getPos(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.7f, 0.7f);
					if (!ritual.getInputEntities().isEmpty()) {
						for (EntityLivingBase entity : living_on_ground) {
							if (ritual.getInputEntities().stream().anyMatch(p -> p.getEntityClass().equals(entity.getClass()))) {
								entity.attackEntityFrom(DamageSource.MAGIC, Integer.MAX_VALUE);
								break;
							}
						}
					}
				}
			} else {
				if (world.isRemote)
					ritual.onRandomDisplayTick(this, getWorld(), player, getEffectivePos(), getEffectiveDim(), cooldown);
				if (MagicPower.attemptDrain(this, player, ritual.getRunningPower() * (getEffectivePos() == getPos() ? 1 : MathHelper.ceil(getEffectivePos().distanceSq(getPos()) / 400)))) {
					if (!world.isRemote)
						ritual.onUpdate(this, getWorld(), player, getEffectivePos(), getEffectiveDim(), cooldown);
					cooldown--;
				}
				if (!world.isRemote) {
					if (ritual.onLowPower(this, getWorld(), player, getEffectivePos(), getEffectiveDim(), cooldown))
						stopRitual(player, false);
					if (cooldown <= 0 && ritual.getTime() >= 0) stopRitual(player, true);
				}
			}
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setString("ritual", ritual == null ? "" : ritual.getRegistryName().toString());
		tag.setLong("effectivePos", getEffectivePos().toLong());
		tag.setInteger("effectiveDim", getEffectiveDim());
		tag.setString("caster", caster == null ? "" : caster.toString());
		tag.setInteger("cooldown", cooldown);
		return super.writeToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		ritual = tag.getString("ritual").isEmpty() ? null : BewitchmentAPI.REGISTRY_RITUAL.getValue(new ResourceLocation(tag.getString("ritual")));
		setEffectivePos(BlockPos.fromLong(tag.getLong("effectivePos")));
		setEffectiveDim(tag.getInteger("effectiveDim"));
		caster = tag.getString("caster").isEmpty() ? null : UUID.fromString(tag.getString("caster"));
		cooldown = tag.getInteger("cooldown");
	}

	@Override
	public ItemStackHandler[] getInventories() {
		return new ItemStackHandler[]{inventory};
	}

	public void startRitual(EntityPlayer player) {
		if (!world.isRemote && cooldown < 0) {
			List<ItemStack> items_on_ground = new ArrayList<>();
			List<EntityItem> entityItemsOnGround = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(getPos()).grow(3, 0, 3));
			for (EntityItem item : entityItemsOnGround) {
				for (int i = 0; i < item.getItem().getCount(); i++) {
					ItemStack copy = item.getItem().copy();
					copy.setCount(1);
					items_on_ground.add(copy);
				}
			}
			List<EntityLivingBase> living_on_ground = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(getPos()).grow(3, 0, 3));
			ritual = BewitchmentAPI.REGISTRY_RITUAL.getValuesCollection().stream().filter(p -> p.matches(world, getPos(), items_on_ground, living_on_ground)).findFirst().orElse(null);
			if (ritual != null) {
				setEffectiveDim(world.provider.getDimension());
				if (ritual.isValid(this, getWorld(), player, getEffectivePos(), getEffectiveDim(), cooldown)) {
					if (getAltarPosition() != null && MagicPower.attemptDrain(world.getTileEntity(getAltarPosition()), player, ritual.getStartingPower() * (getEffectivePos() == getPos() ? 1 : MathHelper.ceil(getEffectivePos().distanceSq(getPos()) / 400)))) {
						world.notifyBlockUpdate(getPos(), world.getBlockState(getPos()), world.getBlockState(getPos()), 2);
						caster = player.getPersistentID();
						cooldown = ritual.getTime() + (entityItemsOnGround.size() * 20) + (ritual.getInputEntities().isEmpty() ? 0 : 20) + 20;
						for (EntityItem entity : entityItemsOnGround) entity.setInfinitePickupDelay();
						player.sendStatusMessage(new TextComponentTranslation(ritual.getRegistryName().toString().replace(":", ".")), true);
						return;
					} else player.sendStatusMessage(new TextComponentTranslation("magic.no_power"), true);
				} else player.sendStatusMessage(new TextComponentTranslation("ritual.precondition"), true);
			} else player.sendStatusMessage(new TextComponentTranslation("ritual.invalid_input"), true);
			world.playSound(null, getPos(), SoundEvents.BLOCK_NOTE_SNARE, SoundCategory.BLOCKS, 1, 1);
		}
	}

	public void stopRitual(EntityPlayer player, boolean finished) {
		world.notifyBlockUpdate(getPos(), world.getBlockState(getPos()), world.getBlockState(getPos()), 2);
		if (ritual != null) {
			if (finished) {
				ritual.onFinished(this, getWorld(), player, getEffectivePos(), getEffectiveDim(), cooldown);
				world.playSound(null, getPos(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.BLOCKS, 0.7f, 0.7f);
			} else {
				ritual.onStopped(this, getWorld(), player, getEffectivePos(), getEffectiveDim(), cooldown);
				world.playSound(null, getPos(), SoundEvents.ENTITY_CHICKEN_EGG, SoundCategory.BLOCKS, 0.7f, 0.7f);
			}
		}
		for (EntityItem entity : world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(getPos()).grow(3, 0, 3)))
			entity.setDefaultPickupDelay();
		clear(inventory);
		setEffectivePos(getPos());
		setEffectiveDim(getWorld().provider.getDimension());
		ritual = null;
		caster = null;
		cooldown = -1;
	}

	public Ritual getRitual() {
		return ritual;
	}

	public UUID getCaster() {
		return caster;
	}

	public BlockPos getEffectivePos() {
		return effectivePos;
	}

	public void setEffectivePos(BlockPos pos) {
		effectivePos = pos;
	}

	public int getEffectiveDim() {
		return effectiveDim;
	}

	public void setEffectiveDim(int dim) {
		effectiveDim = dim;
	}
}