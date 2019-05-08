package com.bewitchment.common.item.tool;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.common.item.util.ModItemSword;
import com.bewitchment.registry.ModObjects;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemAthame extends ModItemSword {
	public ItemAthame() {
		super("athame", ModObjects.TOOL_SILVER);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
		String tip = "tooltip." + getTranslationKey().substring(5);
		if (!I18n.format(tip).equals(tip)) tooltip.add(getTooltipColor() + I18n.format(tip));
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (!target.world.isRemote) {
			if (target instanceof EntityEnderman && attacker instanceof EntityPlayer) {
				target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 20);
				stack.damageItem(50, attacker);
			} else return super.hitEntity(stack, target, attacker);
		}
		return true;
	}

	@SubscribeEvent
	public void livingDrop(LivingDropsEvent event) {
		if (event.isRecentlyHit() && event.getSource().getTrueSource() instanceof EntityLivingBase && ((EntityLivingBase) event.getSource().getTrueSource()).getHeldItemMainhand().getItem() == ModObjects.athame && BewitchmentAPI.ATHAME_LOOT.containsKey(EntityRegistry.getEntry(event.getEntityLiving().getClass()))) {
			for (ItemStack stack : BewitchmentAPI.ATHAME_LOOT.get(EntityRegistry.getEntry(event.getEntityLiving().getClass())))
				if (event.getEntityLiving().getRNG().nextInt(5) <= 2 + (2 * event.getLootingLevel())) {
					if (event.getEntityLiving() instanceof EntityPlayer && stack.getItem() instanceof ItemSkull) {
						NBTTagCompound tag = new NBTTagCompound();
						tag.setString("SkullOwner", event.getEntity().getName());
						stack.setTagCompound(tag);
					}
					event.getDrops().add(new EntityItem(event.getEntityLiving().world, event.getEntityLiving().posX + 0.5, event.getEntityLiving().posY + 0.5, event.getEntityLiving().posZ + 0.5, stack));
				}
		}
	}
}