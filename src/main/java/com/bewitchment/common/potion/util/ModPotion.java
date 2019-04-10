package com.bewitchment.common.potion.util;

import com.bewitchment.Bewitchment;
import com.bewitchment.registry.ModPotions;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModPotion extends Potion {
	public static final ResourceLocation EXTRA_EFFECTS_ALT = new ResourceLocation(Bewitchment.MOD_ID, "textures/gui/potions.png");

	private final boolean instant;

	public ModPotion(String name, boolean is_bad, int color, boolean instant) {
		super(is_bad, color);
		setRegistryName(Bewitchment.MOD_ID, name);
		setPotionName(getRegistryName().toString().replace(":", "."));
		this.instant = instant;
		ModPotions.REGISTRY.add(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isBeneficial() {
		return !isBadEffect();
	}

	@Override
	public boolean isInstant() {
		return instant;
	}

	@Override
	public boolean isReady(int duration, int amplifier) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getStatusIconIndex() {
		Minecraft.getMinecraft().renderEngine.bindTexture(EXTRA_EFFECTS_ALT);
		return super.getStatusIconIndex();
	}
}