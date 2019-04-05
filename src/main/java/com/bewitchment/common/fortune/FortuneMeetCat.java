package com.bewitchment.common.fortune;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Fortune;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

/**
 * Created by Joseph on 4/5/2019.
 */
public class FortuneMeetCat extends Fortune {

	public FortuneMeetCat() {
		super(Bewitchment.MOD_ID, "meet_cat", 5);
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
			EntityOcelot cat = new EntityOcelot(player.world);
			if (player.world.isAirBlock(pos) && player.world.isAirBlock(pos.up()) && player.world.getBlockState(pos.down()).canEntitySpawn(cat)) {
				cat.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
				cat.onInitialSpawn(player.world.getDifficultyForLocation(pos), null);
				cat.setTamedBy(player);
				player.world.spawnEntity(cat);
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
