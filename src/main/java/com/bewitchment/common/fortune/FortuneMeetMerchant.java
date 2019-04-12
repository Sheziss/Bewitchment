package com.bewitchment.common.fortune;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.registry.Fortune;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import java.util.Random;

public class FortuneMeetMerchant extends Fortune {
	public FortuneMeetMerchant() {
		super(Bewitchment.MOD_ID, "meet_merchant", 6);
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
		return false;
	}

	@Override
	public boolean apply(EntityPlayer player) {
		for (int i = 0; i < 10; i++) {
			BlockPos pos = new BlockPos(player.posX + player.getRNG().nextGaussian() * 4, player.posY, player.posZ + player.getRNG().nextGaussian() * 4);
			EntityVillager villager = new EntityVillager(player.world);
			if (player.world.isAirBlock(pos) && player.world.isAirBlock(pos.up()) && player.world.getBlockState(pos.down()).canEntitySpawn(villager)) {
				villager.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
				villager.onInitialSpawn(player.world.getDifficultyForLocation(pos), null);
				player.world.spawnEntity(villager);
				VillagerRegistry.setRandomProfession(villager, new Random());
				if (villager.getProfessionForge().getRegistryName().getPath().equals("nitwit")) {
					villager.setProfession(VillagerRegistry.FARMER);
				}
				return true;
			}
		}
		return false;
	}
}