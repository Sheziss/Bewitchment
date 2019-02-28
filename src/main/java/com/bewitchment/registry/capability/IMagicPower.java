package com.bewitchment.registry.capability;

import com.bewitchment.core.Bewitchment;
import com.bewitchment.registry.block.tile.BlockDistillery;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public interface IMagicPower
{
	public int getAmount();
	
	public void setAmount(int amount);
	
	public int getMaxAmount();
	
	public void setMaxAmount(int max_amount);
	
	public int getBonusAmount();
	
	public void setBonusAmount(int bonus_amount);
	
	public static class Cap implements IMagicPower
	{
		int amount, max_amount, bonus_amount;
		
		@Override
		public int getAmount()
		{
			return amount;
		}
		
		@Override
		public void setAmount(int amount)
		{
			this.amount = amount;
		}
		
		@Override
		public int getMaxAmount()
		{
			return max_amount;
		}
		
		@Override
		public void setMaxAmount(int max_amount)
		{
			this.max_amount = max_amount;
		}
		
		@Override
		public int getBonusAmount()
		{
			return max_amount;
		}
		
		@Override
		public void setBonusAmount(int bonus_amount)
		{
			this.bonus_amount = bonus_amount;
		}
	}
	public static class Storage implements IStorage<IMagicPower>
	{
		@Override
		public NBTBase writeNBT(Capability<IMagicPower> capability, IMagicPower instance, EnumFacing side)
		{
			NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger("amount", instance.getAmount());
			tag.setInteger("max_amount", instance.getMaxAmount());
			tag.setInteger("bonux_amount", instance.getBonusAmount());
			return tag;
		}
		
		@Override
		public void readNBT(Capability<IMagicPower> capability, IMagicPower instance, EnumFacing side, NBTBase nbt)
		{
			instance.setAmount(((NBTTagCompound)nbt).getInteger("amount"));
			instance.setMaxAmount(((NBTTagCompound)nbt).getInteger("max_amount"));
			instance.setBonusAmount(((NBTTagCompound)nbt).getInteger("bonux_amount"));
		}
	}
	
	public static class Provider implements ICapabilitySerializable<NBTTagCompound>
	{
		@CapabilityInject(IMagicPower.class)
		public static final Capability<IMagicPower> CAPABILITY = null;
		
		private IMagicPower instance = CAPABILITY.getDefaultInstance();
		
		@Override
		public boolean hasCapability(Capability<?> capability, EnumFacing facing)
		{
			return capability == CAPABILITY;
		}
		
		@Override
		public <T> T getCapability(Capability<T> capability, EnumFacing facing)
		{
			return capability == CAPABILITY ? CAPABILITY.<T>cast(instance) : null;
		}
		
		@Override
		public NBTTagCompound serializeNBT()
		{
			return (NBTTagCompound) CAPABILITY.getStorage().writeNBT(CAPABILITY, instance, null);
		}
		
		@Override
		public void deserializeNBT(NBTTagCompound nbt)
		{
			CAPABILITY.getStorage().readNBT(CAPABILITY, instance, null, nbt);
		}
	}
	
	public static class Handler
	{
		public static final ResourceLocation CAP = new ResourceLocation(Bewitchment.MOD_ID, "magic_power");
		
		@SubscribeEvent
		public void attachCapabilityE(AttachCapabilitiesEvent<Entity> event)
		{
			if (event.getObject() instanceof EntityPlayer) event.addCapability(CAP, new Provider());
		}
		
		@SubscribeEvent
		public void attachCapabilityTE(AttachCapabilitiesEvent<TileEntity> event)
		{
			if (event.getObject() instanceof BlockDistillery.Tile) event.addCapability(CAP, new Provider());
		}
		
		@SubscribeEvent
		public void livingTick(LivingEvent.LivingUpdateEvent event)
		{
			if (event.getEntityLiving() instanceof EntityPlayer)
			{
				IMagicPower cap = event.getEntityLiving().getCapability(Provider.CAPABILITY, null);
				if (cap.getMaxAmount() <= 0)
				{
					cap.setAmount(800);
					cap.setMaxAmount(800);
					cap.setBonusAmount(0);
				}
			}
		}
		
		@SubscribeEvent
		public void clonePlayer(PlayerEvent.Clone event)
		{
			IMagicPower oldC = event.getOriginal().getCapability(Provider.CAPABILITY, null), newC = event.getEntityPlayer().getCapability(Provider.CAPABILITY, null);
			newC.setAmount(oldC.getAmount());
			newC.setMaxAmount(oldC.getMaxAmount());
			newC.setBonusAmount(oldC.getBonusAmount());
		}
	}
}