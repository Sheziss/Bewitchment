package com.bewitchment.common.block.tile.entity;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.capability.extendedplayer.ExtendedPlayer;
import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.api.registry.Fortune;
import com.bewitchment.common.block.tile.entity.util.TileEntityAltarStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.List;
import java.util.stream.Collectors;

public class TileEntityCrystalBall extends TileEntityAltarStorage {
	public boolean activate(EntityPlayer player) {
		if (!player.isSneaking()) {
			if (!world.isRemote) {
				Fortune fortune = player.getCapability(ExtendedPlayer.CAPABILITY, null).getFortune();
				if (fortune != null)
					player.sendStatusMessage(new TextComponentTranslation("fortune.fortune_exists" + new TextComponentTranslation(fortune.getRegistryName().toString())), true);
				else if (getAltarPosition() != null && MagicPower.attemptDrain(world.getTileEntity(getAltarPosition()), player, 3000)) {
					List<Fortune> valid = BewitchmentAPI.REGISTRY_FORTUNE.getValuesCollection().stream().filter(f -> f.canBeUsed(player)).collect(Collectors.toList());
					if (!valid.isEmpty()) {
						Fortune set = null;
						while (set == null) {
							for (Fortune f : valid) {
								if (world.rand.nextInt(100) <= f.getChance()) {
									set = f;
									break;
								}
							}
						}
						player.sendStatusMessage(new TextComponentTranslation(set.getRegistryName().toString()), true);
						player.getCapability(ExtendedPlayer.CAPABILITY, null).setFortune(set);
					} else player.sendStatusMessage(new TextComponentTranslation("fortune.no_fortune"), true);
				} else player.sendStatusMessage(new TextComponentTranslation("magic.no_power"), true);
			}
			return true;
		}
		return false;
	}
}