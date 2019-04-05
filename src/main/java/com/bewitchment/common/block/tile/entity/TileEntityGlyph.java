package com.bewitchment.common.block.tile.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.capability.magicpower.MagicPower;
import com.bewitchment.api.registry.Ritual;
import com.bewitchment.common.block.tile.entity.util.TileEntityAltarStorage;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityGlyph extends TileEntityAltarStorage implements ITickable
{
	private Ritual ritual;
	private BlockPos effective_pos = getPos();
	private UUID caster;
	private int cooldown = -1;
	
	public final ItemStackHandler inventory = new ItemStackHandler(Byte.MAX_VALUE)
	{
		@Override
	    public int getSlotLimit(int slot)
	    {
	        return Integer.MAX_VALUE;
	    }
	};
	
	@Override
	public void update()
	{
		if (ritual != null)
		{
			if (!world.isRemote)
			{
				if (caster != null)
				{
					EntityPlayer player = world.getPlayerEntityByUUID(caster);
					if (MagicPower.attemptDrain(world, player, getAltarPosition(), ritual.getRunningPower() * (getEffectivePosition() == getPos() ? 1 : MathHelper.ceil(getEffectivePosition().distanceSq(getPos()) / 400))))
					{
						ritual.onUpdate(this, player);
						cooldown--;
					}
					else if (ritual.onLowPower(this, player)) stopRitual(player, false);
					if (cooldown <= 0 && ritual.getTime() >= 0) stopRitual(player, true);
				}
			}
			else ritual.onRandomDisplayTick(this);
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag)
	{
		tag.setString("ritual", ritual == null ? "" : ritual.getRegistryName().toString());
		tag.setLong("effectivePos", getEffectivePosition().toLong());
		tag.setString("caster", caster == null ? "" : caster.toString());
		tag.setInteger("cooldown", cooldown);
		return super.writeToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		ritual = tag.getString("ritual") == "" ? null : BewitchmentAPI.REGISTRY_RITUAL.getValue(new ResourceLocation(tag.getString("ritual")));
		setEffectivePosition(BlockPos.fromLong(tag.getLong("effectivePos")));
		caster = tag.getString("caster") == "" ? null : UUID.fromString(tag.getString("caster"));
		cooldown = tag.getInteger("cooldown");
	}
	
	@Override
	public ItemStackHandler[] getInventories()
	{
		return new ItemStackHandler[] {inventory};
	}
	
	public void startRitual(EntityPlayer player)
	{
		if (!world.isRemote && cooldown < 0)
		{
			List<ItemStack> items_on_ground = new ArrayList<>();
			List<EntityItem> entityItemsOnGround = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(getPos()).grow(3, 0, 3));
			for (EntityItem item : entityItemsOnGround) 
			{
				for (int i = 0; i < item.getItem().getCount(); i++)
				{
					ItemStack copy = item.getItem().copy();
					copy.setCount(1);
					items_on_ground.add(copy);
				}
			}
			List<EntityLivingBase> living_on_ground = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(getPos()).grow(3, 0, 3));
			ritual = BewitchmentAPI.REGISTRY_RITUAL.getValuesCollection().parallelStream().filter(p -> p.matches(world, getPos(), items_on_ground, living_on_ground)).findFirst().orElse(null);
			if (ritual != null)
			{
				if (ritual.isValid(this, player))
				{
					if (MagicPower.attemptDrain(world, player, getAltarPosition(), ritual.getStartingPower() * (getEffectivePosition() == getPos() ? 1 : MathHelper.ceil(getEffectivePosition().distanceSq(getPos()) / 400))))
					{
						world.notifyBlockUpdate(getPos(), world.getBlockState(getPos()), world.getBlockState(getPos()), 2);
						caster = player.getPersistentID();
						cooldown = ritual.getTime();
						ritual.onStarted(this, player);
						world.playSound(null, getPos(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.7f, 0.7f);
						player.sendStatusMessage(new TextComponentTranslation(ritual.getRegistryName().toString().replace(":", ".")), true);
						for (EntityItem item : entityItemsOnGround) item.setDead();
						if (!ritual.getInputEntities().isEmpty())
						{
							for (EntityLivingBase entity : living_on_ground)
							{
								if (ritual.getInputEntities().parallelStream().anyMatch(p -> p.getEntityClass().equals(entity.getClass())))
								{
									entity.attackEntityFrom(DamageSource.MAGIC, Integer.MAX_VALUE);
									break;
								}
							}
						}
					}
					else player.sendStatusMessage(new TextComponentTranslation("magic.no_power"), true);
				}
				else player.sendStatusMessage(new TextComponentTranslation("ritual.precondition"), true);
			}
			else player.sendStatusMessage(new TextComponentTranslation("ritual.invalid_input"), true);
		}
	}
	
	public void stopRitual(EntityPlayer player, boolean finished)
	{
		world.notifyBlockUpdate(getPos(), world.getBlockState(getPos()), world.getBlockState(getPos()), 2);
		if (ritual != null)
		{
			if (finished)
			{
				ritual.onFinished(this, player);
				for (ItemStack stack : ritual.getOutput(this)) InventoryHelper.spawnItemStack(world, getPos().getX(), getPos().getY(), getPos().getZ(), stack.copy());
			}
			else
			{
				ritual.onStopped(this, player);
				for (int i = 0; i < inventory.getSlots(); i++) InventoryHelper.spawnItemStack(world, getPos().getX(), getPos().getY(), getPos().getZ(), inventory.extractItem(i, inventory.getStackInSlot(i).getCount(), false));
			}
		}
		clear(inventory);
		setEffectivePosition(getPos());
		ritual = null;
		caster = null;
		cooldown = -1;
	}
	
	public Ritual getRitual()
	{
		return ritual;
	}
	
	public UUID getCaster()
	{
		return caster;
	}
	
	public BlockPos getEffectivePosition()
	{
		return effective_pos;
	}
	
	public void setEffectivePosition(BlockPos pos)
	{
		effective_pos = pos;
	}
}