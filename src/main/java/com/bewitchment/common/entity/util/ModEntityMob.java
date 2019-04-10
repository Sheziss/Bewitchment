package com.bewitchment.common.entity.util;

import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public abstract class ModEntityMob extends EntityMob implements IAnimatedEntity {
	public static final DataParameter<Integer> SKIN = EntityDataManager.createKey(ModEntityMob.class, DataSerializers.VARINT);

	private final ResourceLocation lootTableLocation;

	protected Animation currentAnimation;
	protected int animationTick;

	public ModEntityMob(World world, ResourceLocation lootTableLocation) {
		super(world);
		this.lootTableLocation = lootTableLocation;
	}

	@Override
	public abstract Animation[] getAnimations();

	@Override
	public Animation getAnimation() {
		return currentAnimation;
	}

	@Override
	public int getAnimationTick() {
		return animationTick;
	}

	@Override
	public void setAnimation(Animation animation) {
		currentAnimation = animation;
	}

	@Override
	public void setAnimationTick(int tick) {
		animationTick = tick;
	}

	@Override
	protected abstract boolean isValidLightLevel();

	@Override
	protected ResourceLocation getLootTable() {
		return lootTableLocation;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		AnimationHandler.INSTANCE.updateAnimations(this);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		if (getSkinTypes() > 1) dataManager.register(SKIN, 0);
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData data) {
		dataManager.set(SKIN, rand.nextInt(getSkinTypes()));
		return super.onInitialSpawn(difficulty, data);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		if (getSkinTypes() > 1) {
			tag.setInteger("skin", dataManager.get(SKIN));
			dataManager.setDirty(SKIN);
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		if (getSkinTypes() > 1) dataManager.set(SKIN, tag.getInteger("skin"));
	}

	protected int getSkinTypes() {
		return 1;
	}
}