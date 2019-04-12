package com.bewitchment.common.fortune;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Fortune;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;

public class FortuneMeetZombie extends Fortune {
	public FortuneMeetZombie() {
		super(Bewitchment.MOD_ID, "meet_zombie", 9);
	}

	@Override
	public boolean canBeUsed(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean isInstant(EntityPlayer player) {
		return player.getRNG().nextDouble() < 0.0001;
	}

	@Override
	public boolean isNegative() {
		return true;
	}

	@Override
	public boolean apply(EntityPlayer player) {
		for (int i = 0; i < 10; i++) {
			BlockPos pos = new BlockPos(player.posX + player.getRNG().nextGaussian() * 4, player.posY, player.posZ + player.getRNG().nextGaussian() * 4);
			EntityZombie zombie = new EntityZombie(player.world);
			if (player.world.isAirBlock(pos) && player.world.isAirBlock(pos.up()) && player.world.getBlockState(pos.down()).canEntitySpawn(zombie)) {
				zombie.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
				zombie.onInitialSpawn(player.world.getDifficultyForLocation(pos), null);
				player.world.spawnEntity(zombie);
				if (player.getRNG().nextInt(10) < player.world.getDifficulty().ordinal())
					zombie.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 900, 1));
				if (player.getRNG().nextInt(10) < player.world.getDifficulty().ordinal())
					zombie.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 900, 1));
				if (player.getRNG().nextInt(10) < player.world.getDifficulty().ordinal())
					zombie.addPotionEffect(new PotionEffect(MobEffects.SPEED, 900, 1));
				if (player.getRNG().nextInt(10) < player.world.getDifficulty().ordinal())
					zombie.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 900, 1));
				return true;
			}
		}
		return false;
	}
}