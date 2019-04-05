package com.bewitchment.common.fortune;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Fortune;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

/**
 * Created by Joseph on 4/5/2019.
 */
public class FortuneMeetHorse extends Fortune {

	public FortuneMeetHorse() {
		super(Bewitchment.MOD_ID, "meet_horse", 3);
	}

	@Override
	public boolean canBeUsed(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean isInstant(EntityPlayer player)
	{
		return player.getRNG().nextDouble() < 0.0001;
	}

	@Override
	public boolean apply(EntityPlayer player) {
		for (int i = 0; i < 10; i++) {
			BlockPos pos = new BlockPos(player.posX + player.getRNG().nextGaussian() * 4, player.posY, player.posZ + player.getRNG().nextGaussian() * 4);
			EntityHorse horse = new EntityHorse(player.world);
			if (player.world.isAirBlock(pos) && player.world.isAirBlock(pos.up()) && player.world.getBlockState(pos.down()).canEntitySpawn(horse)) {
				horse.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
				horse.onInitialSpawn(player.world.getDifficultyForLocation(pos), null);
				horse.setTamedBy(player);
				player.world.spawnEntity(horse);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isNegative() {
		return false;
	}

}
