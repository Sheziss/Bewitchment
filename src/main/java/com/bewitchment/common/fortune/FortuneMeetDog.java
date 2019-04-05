package com.bewitchment.common.fortune;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Fortune;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

/**
 * Created by Joseph on 4/5/2019.
 */
public class FortuneMeetDog extends Fortune {

	public FortuneMeetDog() {
		super(Bewitchment.MOD_ID, "meet_dog", 5);
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
			EntityWolf wolf = new EntityWolf(player.world);
			if (player.world.isAirBlock(pos) && player.world.isAirBlock(pos.up()) && player.world.getBlockState(pos.down()).canEntitySpawn(wolf)) {
				wolf.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
				wolf.onInitialSpawn(player.world.getDifficultyForLocation(pos), null);
				wolf.setTamedBy(player);
				player.world.spawnEntity(wolf);
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
