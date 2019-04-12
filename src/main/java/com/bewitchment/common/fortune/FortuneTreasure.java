package com.bewitchment.common.fortune;

import com.bewitchment.Bewitchment;
import com.bewitchment.api.capability.extendedplayer.ExtendedPlayer;
import com.bewitchment.api.registry.Fortune;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Created by Joseph on 4/12/2019.
 */

//Todo: Get this working right
public class FortuneTreasure extends Fortune {

	public FortuneTreasure() {
		super(Bewitchment.MOD_ID, "treasure", 3);
	}

	@Override
	public boolean canBeUsed(@Nonnull EntityPlayer player) {
		return true;
	}

	@Override
	public boolean isInstant(@Nonnull EntityPlayer player) {
		return false;
	}

	@Override
	public boolean apply(@Nonnull EntityPlayer player) {
		player.getCapability(ExtendedPlayer.CAPABILITY, null).setActive();
		return false;
	}

	@Override
	public boolean isNegative() {
		return false;
	}

	@SubscribeEvent
	public void onDig(BlockEvent.BreakEvent evt) {
		ExtendedPlayer cap = evt.getPlayer().getCapability(ExtendedPlayer.CAPABILITY, null);
		if (cap.getFortune() == this) {
			if (cap.isActive()) {
				Block block = evt.getState().getBlock();
				if (block == Blocks.DIRT || block == Blocks.GRASS || block == Blocks.SAND || block == Blocks.MYCELIUM || block == Blocks.GRAVEL || block == Blocks.SOUL_SAND) {
					LootTable lt = evt.getWorld().getLootTableManager().getLootTableFromLocation(ModLootTables.MATERIALS);
					LootContext lc = (new LootContext.Builder((WorldServer) evt.getWorld()).withLuck(evt.getPlayer().getLuck()).withPlayer(evt.getPlayer())).build();
					List<ItemStack> spawn = lt.generateLootForPools(evt.getPlayer().getRNG(), lc);
					spawn.forEach(s -> spawn(s, evt.getWorld(), evt.getPos()));
					cap.setRemovable();
				}
			}
		}
	}

	private void spawn(ItemStack s, World world, BlockPos pos) {
		EntityItem i = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, s);
		i.setNoPickupDelay();
		world.spawnEntity(i);
	}
}