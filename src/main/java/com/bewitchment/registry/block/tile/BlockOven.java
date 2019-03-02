package com.bewitchment.registry.block.tile;

import java.util.Random;

import com.bewitchment.core.Bewitchment;
import com.bewitchment.core.Bewitchment.API.OvenRecipe;
import com.bewitchment.core.CommonProxy.ModGui;
import com.bewitchment.registry.ModItems;
import com.bewitchment.registry.block.ModBlock;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IWorldNameable;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BlockOven extends ModBlock implements ITileEntityProvider
{
	public BlockOven(String name)
	{
		super(name, Material.IRON, SoundType.METAL, 5, 5, "pickaxe", 0);
		this.setDefaultState(blockState.getBaseState().withProperty(BlockHorizontal.FACING, EnumFacing.SOUTH));
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new Tile();
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing face, float hitX, float hitY, float hitZ)
	{
		if (player.isSneaking()) return false;
		if (!player.getHeldItem(hand).isEmpty() && player.getHeldItem(hand).getItem() == Items.NAME_TAG && world.getTileEntity(pos) instanceof IWorldNameable)
		{
			if (!player.isCreative()) player.getHeldItem(hand).shrink(1);
			((Tile)world.getTileEntity(pos)).custom_name = player.getHeldItem(hand).getDisplayName();
			world.getTileEntity(pos).markDirty();
		}
		else player.openGui(Bewitchment.instance, ModGui.OVEN.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		if (!world.isRemote && world.getGameRules().getBoolean("doTileDrops") && hasTileEntity(state) && world.getTileEntity(pos) instanceof IItemHandler) for (int i = 0; i < ((IItemHandler)world.getTileEntity(pos)).getSlots(); i++) InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), ((IItemHandler)world.getTileEntity(pos)).getStackInSlot(i));
		super.breakBlock(world, pos, state);
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face)
	{
		return BlockFaceShape.UNDEFINED;
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing face, float hitX, float hitY, float hitZ, int meta, EntityLivingBase entity, EnumHand hand)
	{
		return getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.fromAngle(entity.rotationYaw));
	}
	
	@Override
	public boolean isFullBlock(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(BlockHorizontal.FACING, EnumFacing.HORIZONTALS[meta & 3]);
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(BlockHorizontal.FACING).getHorizontalIndex();
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, BlockHorizontal.FACING);
	}
	
	public static class Tile extends ModTileEntity
	{
		private final Random rand = new Random();
		
		public String current_recipe = "";
		
		private int burn_time, fuel_burn_time, progress;
		
		public Tile()
		{
			super(5);
		}
		
		@Override
		public NBTTagCompound writeToNBT(NBTTagCompound tag)
		{
			tag.setString("current_recipe", current_recipe);
			tag.setInteger("burn_time", burn_time);
			tag.setInteger("fuel_burn_time", fuel_burn_time);
			tag.setInteger("progress", progress);
			return super.writeToNBT(tag);
		}
		
		@Override
		public void readFromNBT(NBTTagCompound tag)
		{
			super.readFromNBT(tag);
			current_recipe = tag.getString("current_recipe");
			burn_time = tag.getInteger("burn_time");
			fuel_burn_time = tag.getInteger("fuel_burn_time");
			progress = tag.getInteger("progress");
		}
		
		@Override
		public boolean hasCapability(Capability<?> capability, EnumFacing face)
		{
			return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, face);
		}
		
		@Override
		public <T> T getCapability(Capability<T> capability, EnumFacing face)
		{
			return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(this) : super.getCapability(capability, face);
		}
		
		@Override
		public boolean isItemValid(int index, ItemStack stack)
		{
			return index == 0 ? TileEntityFurnace.isItemFuel(stack) : index == 1 ? stack.getItem() == ModItems.empty_jar : index == 2;
		}
		
		@Override
		public void update()
		{
			OvenRecipe recipe = Bewitchment.API.REGISTRY_OVEN.getValuesCollection().parallelStream().filter(dr -> dr.matches(getStackInSlot(2))).findFirst().orElse(null);
			if (recipe == null)
			{
				current_recipe = "";
				progress = 0;
			}
			else if (!current_recipe.equals(recipe.getRegistryName().toString()) && (getStackInSlot(3).isEmpty() || (getStackInSlot(3).isItemEqual(recipe.getOutput()) && getStackInSlot(3).getCount() < getSlotLimit(3))) && (getStackInSlot(4).isEmpty() || (getStackInSlot(4).isItemEqual(recipe.getOutput()) && getStackInSlot(4).getCount() < getSlotLimit(4)))) current_recipe = recipe.getRegistryName().toString();
			if (burn_time > 0) burn_time--;
			if (!current_recipe.isEmpty())
			{
				if (burn_time == 0 && !getStackInSlot(0).isEmpty())
				{
					burn_time = TileEntityFurnace.getItemBurnTime(getStackInSlot(0));
					fuel_burn_time = burn_time;
					extractItem(0, 1, false);
				}
				else if (burn_time > 0)
				{
					progress++;
					if (progress >= 100)
					{
						progress = 0;
						extractItem(2, 1, false);
						ItemStack output = getStackInSlot(3);
						if (output.isItemEqual(recipe.getByproduct())) output.grow(1);
						else output = recipe.getOutput();
						insertItem(3, output, false);
						if (rand.nextFloat() <= recipe.getByproductChance())
						{
							extractItem(1, 1, false);
							ItemStack byproduct = getStackInSlot(4);
							if (byproduct.isItemEqual(recipe.getByproduct())) byproduct.grow(1);
							else byproduct = recipe.getByproduct();
							insertItem(4, byproduct, false);
						}
					}
				}
			}
			markDirty();
		}
	}
	
	public static class Container extends net.minecraft.inventory.Container
	{
		public final Tile tile;
		
		public Container(InventoryPlayer inventory, Tile tile)
		{
			this.tile = tile;
			int index = 0;
			addSlotToContainer(new ModSlot(tile, index++, 44, 55));
			addSlotToContainer(new ModSlot(tile, index++, 80, 55));
			addSlotToContainer(new ModSlot(tile, index++, 44, 19));
			addSlotToContainer(new ModSlot(tile, index++, 116, 19));
			addSlotToContainer(new ModSlot(tile, index++, 116, 55));
			for (int i = 0; i < 3; i++) for (int j = 0; j < 9; j++) addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			for (int i = 0; i < 9; i++) addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
		}
		
		@Override
		public ItemStack transferStackInSlot(EntityPlayer player, int index)
	    {
			ItemStack stack = ItemStack.EMPTY;
			Slot slot = inventorySlots.get(index);
			if (slot != null && slot.getHasStack())
			{
				ItemStack stack0 = slot.getStack();
				stack = stack0.copy();
				int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size() - 2;
				if (index < containerSlots && !mergeItemStack(stack0, containerSlots, inventorySlots.size(), true) || !mergeItemStack(stack0, 0, containerSlots, false)) return ItemStack.EMPTY;
				if (stack0.getCount() == 0) slot.putStack(ItemStack.EMPTY);
				else slot.onSlotChanged();
				if (stack0.getCount() == stack.getCount()) return ItemStack.EMPTY;
				slot.onTake(player, stack0);
			}
			return stack;
	    }
		
		@Override
		public boolean canInteractWith(EntityPlayer player)
		{
			return !player.isSpectator();
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static class Gui extends GuiContainer
	{
		private static final ResourceLocation TEX = new ResourceLocation(Bewitchment.MOD_ID, "textures/gui/oven.png");
		
		private final InventoryPlayer inventory;
		private final Container container;
		
		public Gui(Container container, InventoryPlayer inventory)
		{
			super(container);
			this.container = container;
			this.inventory = inventory;
		}
		
		@Override
		public void drawScreen(int mouseX, int mouseY, float partialTicks)
		{
			drawDefaultBackground();
			super.drawScreen(mouseX, mouseY, partialTicks);
			renderHoveredToolTip(mouseX, mouseY);
		}
		
		@Override
		protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
		{
			GlStateManager.color(1, 1, 1);
			mc.getTextureManager().bindTexture(TEX);
			int x = (width - xSize) / 2;
			int y = (height - ySize) / 2;
			drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
			this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
			if (container.tile.burn_time > 0)
			{
				int time = ((container.tile.burn_time) * 13) / container.tile.fuel_burn_time;
				this.drawTexturedModalRect(x + 44, (y + 50) - time, 176, 12 - time, 14, time + 1);
			}
			this.drawTexturedModalRect(x + 76, y + 19, 176, 14, ((container.tile.progress * 24) / 100) + 1, 16);
		}
		
		@Override
		protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
		{
			String name = container.tile.getName();
			fontRenderer.drawString(name, (xSize / 2) - (fontRenderer.getStringWidth(name) / 2), 6, 0x404040);
			fontRenderer.drawString(inventory.getDisplayName().getUnformattedText(), 8, ySize - 94, 0x404040);
		}
	}
}