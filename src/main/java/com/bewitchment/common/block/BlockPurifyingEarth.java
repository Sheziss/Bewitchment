package com.bewitchment.common.block;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.capability.extendedplayer.ExtendedPlayer;
import com.bewitchment.common.block.util.ModBlock;
import com.bewitchment.registry.ModPotions;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPurifyingEarth extends ModBlock {
	public BlockPurifyingEarth() {
		super("purifying_earth", Material.GROUND, SoundType.GROUND, 0.5f, 2.5f, "shovel", 0);
	}

	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity) {
		if (entity instanceof EntityLivingBase) {
			EntityLivingBase living = (EntityLivingBase) entity;
			EnumCreatureAttribute att = living.getCreatureAttribute();
			if (att == EnumCreatureAttribute.UNDEAD || att == BewitchmentAPI.DEMON || att == BewitchmentAPI.SPIRIT || (living instanceof EntityPlayer && !living.getCapability(ExtendedPlayer.CAPABILITY, null).getTransformation().canCrossSalt)) {
				if (!living.isBurning()) {
					living.setFire(1500);
					living.addPotionEffect(new PotionEffect(ModPotions.demons_bane, 1000, 1, false, false));
					living.addPotionEffect(new PotionEffect(ModPotions.holy_water, 1000, 1, false, false));
				}
			}
		}
	}
}