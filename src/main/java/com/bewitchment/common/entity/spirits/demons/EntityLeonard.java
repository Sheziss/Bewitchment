package com.bewitchment.common.entity.spirits.demons;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.common.entity.living.EntityMultiSkin;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.IMob;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by Joseph on 3/9/2019.
 */
public class EntityLeonard extends EntityMultiSkin implements IAnimatedEntity, IMob {

	public static final Animation CAST_SPELL = Animation.create(20, 10);
	private int animationTick;
	private Animation currentAnimation;

	public EntityLeonard(World worldIn) {
		super(worldIn);
		setSize(1F, 3.5F);
	}

	@Override
	public int getSkinTypes() {
		return 1;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		AnimationHandler.INSTANCE.updateAnimations(this);
	}

	public EnumCreatureAttribute getCreatureAttribute() {
		return BewitchmentAPI.getAPI().DEMON;
	}

	@Nullable
	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}

	@Override
	public int getAnimationTick() {
		return animationTick;
	}

	@Override
	public void setAnimationTick(int tick) {
		animationTick = tick;
	}

	@Override
	public Animation getAnimation() {
		return currentAnimation;
	}

	@Override
	public void setAnimation(Animation animation) {
		currentAnimation = animation;
	}

	@Override
	public Animation[] getAnimations() {
		return new Animation[]{IAnimatedEntity.NO_ANIMATION, EntityLeonard.CAST_SPELL};
	}
}