package com.bewitchment.common.item.tool;

import java.util.List;

import com.bewitchment.api.capability.extendedplayer.ExtendedPlayer;
import com.bewitchment.api.capability.extendedplayer.ExtendedPlayer.TransformationType;
import com.bewitchment.common.item.util.ModItemArmor;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSilverArmor extends ModItemArmor
{
	public ItemSilverArmor(String name, ArmorMaterial mat, EntityEquipmentSlot slot)
	{
		super(name, mat, slot);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced)
	{
		tooltip.add(TextFormatting.GRAY + I18n.format("tooltip." + "armor_description_" + getArmorMaterial().name()));
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
	{
		if (player.getCapability(ExtendedPlayer.CAPABILITY, null).getTransformation() == TransformationType.WEREWOLF) player.attackEntityFrom(DamageSource.MAGIC, 1);
	}
	
	@SubscribeEvent
	public void onHurt(LivingHurtEvent event)
	{
		if (getSilverArmor(event.getEntityLiving()) > 0)
		{
			if (event.getSource().getTrueSource() instanceof EntityLivingBase)
			{
				if (((EntityLivingBase) event.getSource().getTrueSource()).getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) event.setAmount(event.getAmount() * 0.9f);
				if (event.getSource().getTrueSource() instanceof EntityPlayer && event.getSource().getTrueSource().getCapability(ExtendedPlayer.CAPABILITY, null).getTransformation() == TransformationType.WEREWOLF)
				{
					event.setAmount(event.getAmount() * 0.9f);
					event.getSource().getTrueSource().attackEntityFrom(DamageSource.causeThornsDamage(event.getEntityLiving()), MathHelper.clamp(event.getAmount() / 2, 1, 4));
				}
			}
		}
	}
	
	private byte getSilverArmor(EntityLivingBase entity)
	{
		byte fin = 0;
		for (ItemStack stack : entity.getArmorInventoryList()) if (stack.getItem() instanceof ItemSilverArmor) fin++;
		return fin;
	}
}