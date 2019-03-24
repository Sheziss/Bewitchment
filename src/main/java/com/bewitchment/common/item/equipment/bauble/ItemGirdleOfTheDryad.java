package com.bewitchment.common.item.equipment.bauble;

import com.bewitchment.client.model.equipment.ModelGirdleOfTheDryad;
import com.bewitchment.client.model.equipment.ModelGirdleOfTheDryadArmor;
import com.bewitchment.common.item.util.ModItemBauble;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.render.IRenderBauble;
import net.minecraft.block.BlockGrass;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGirdleOfTheDryad extends ModItemBauble implements IRenderBauble
{
	public ItemGirdleOfTheDryad()
	{
		super("girdle_of_the_dryad");
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onPlayerBaubleRender(ItemStack stack, EntityPlayer player, RenderType type, float partialTicks)
	{
		if (type == RenderType.BODY)
		{
			ModelBase model = new ModelGirdleOfTheDryad();
			ModelBase model_armor = new ModelGirdleOfTheDryadArmor();
			GlStateManager.pushMatrix();
			IRenderBauble.Helper.rotateIfSneaking(player);
			GlStateManager.rotate(180, 1, 0, 0);
			GlStateManager.translate(0, 0, 0.02);
			GlStateManager.scale(0.12, 0.12, 0.12);
			IRenderBauble.Helper.translateToChest();
			IRenderBauble.Helper.defaultTransforms();
			if (player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty()) model.render(player, player.limbSwing, player.limbSwingAmount, player.ticksExisted, player.prevRotationYaw, player.rotationPitch, 1);
			else model_armor.render(player, player.limbSwing, player.limbSwingAmount, player.ticksExisted, player.prevRotationYaw, player.rotationPitch, 1);
			GlStateManager.popMatrix();
		}
	}
	
	@Override
	public BaubleType getBaubleType(ItemStack stack)
	{
		return BaubleType.BELT;
	}
	
	@Override
	public boolean willAutoSync(ItemStack stack, EntityLivingBase living)
	{
		return true;
	}
	
	@Override
	public void onEquipped(ItemStack stack, EntityLivingBase living)
	{
		living.world.playSound(null, living.getPosition(), SoundEvents.BLOCK_WOOD_STEP, SoundCategory.PLAYERS, 0.75f, 1.9f);
	}
	
	@Override
	public void onUnequipped(ItemStack stack, EntityLivingBase living)
	{
		if (living instanceof EntityPlayer) growBark((EntityPlayer) living, -4);
	}
	
	@Override
	public void onWornTick(ItemStack stack, EntityLivingBase living)
	{
		if (!living.world.isRemote)
		{
			if (living instanceof EntityPlayer)
			{
				if (living.getRNG().nextDouble() < 0.0008 && living.world.getBlockState(living.getPosition().down()).getBlock() instanceof BlockGrass && growBark((EntityPlayer) living, 1))
				{
					living.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 80, 10, false, false));
					living.world.playSound(null, living.getPosition(), SoundEvents.BLOCK_CHORUS_FLOWER_GROW, SoundCategory.PLAYERS, 1, 1);
				}
			}
		}
	}
	
	@Override
	protected TextFormatting getTooltipColor()
	{
		return TextFormatting.AQUA;
	}
	
	@SubscribeEvent
	public void onHurt(LivingHurtEvent event)
	{
		if (!event.getEntityLiving().world.isRemote && event.getAmount() > 2 && event.getSource().getTrueSource() != null && event.getEntityLiving() instanceof EntityPlayer && growBark((EntityPlayer) event.getEntityLiving(), -1))
		{
			event.getEntityLiving().world.playSound(null, event.getEntityLiving().getPosition(), SoundEvents.BLOCK_WOOD_STEP, SoundCategory.PLAYERS, 0.75f, 1.9f);
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void equipmentChange(LivingEquipmentChangeEvent event)
	{
		if (!event.getEntityLiving().world.isRemote && event.getEntityLiving() instanceof EntityPlayer) growBark((EntityPlayer) event.getEntityLiving(), 0);
	}
	
	public static ItemStack getGirdle(EntityPlayer player)
	{
		for (int i = 0; i < BaublesApi.getBaublesHandler(player).getSlots(); i++) if (BaublesApi.getBaublesHandler(player).getStackInSlot(i).getItem() instanceof ItemGirdleOfTheDryad) return BaublesApi.getBaublesHandler(player).getStackInSlot(i);
		return ItemStack.EMPTY;
	}
	
	private boolean growBark(EntityPlayer player, int amount)
	{
		if (!getGirdle(player).isEmpty())
		{
			ItemStack stack = getGirdle(player);
			if (!stack.hasTagCompound())
			{
				stack.setTagCompound(new NBTTagCompound());
				stack.getTagCompound().setInteger("bark", 0);
			}
			if (amount > 0 ? stack.getTagCompound().getInteger("bark") < 4 : stack.getTagCompound().getInteger("bark") > 0)
			{
				stack.getTagCompound().setInteger("bark", Math.max(0, Math.min(Math.min(stack.getTagCompound().getInteger("bark"), Math.min(ForgeHooks.getTotalArmorValue(player) / 5, 4)) + amount, 4)));
				return true;
			}
		}
		return false;
	}
}