package com.bewitchment.registry.block.tile;

import java.util.ArrayList;
import java.util.List;

import com.bewitchment.core.Bewitchment;
import com.bewitchment.core.Bewitchment.API.DistilleryRecipe;
import com.bewitchment.core.CommonProxy.ModGui;
import com.bewitchment.registry.block.ModBlock;
import com.bewitchment.registry.capability.MagicPower;

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
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class BlockDistillery extends ModBlock implements ITileEntityProvider
{
	private static final AxisAlignedBB BBOX_X = new AxisAlignedBB(0.125, 0, 0, 0.875, 0.6875, 1), BBOX_Z = new AxisAlignedBB(0, 0, 0.125, 1, 0.6875, 0.875);
	
	public BlockDistillery(String name)
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
		player.openGui(Bewitchment.instance, ModGui.DISTILLERY.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		if (!world.isRemote && world.getGameRules().getBoolean("doTileDrops") && hasTileEntity(state) && world.getTileEntity(pos) instanceof IItemHandler) for (int i = 0; i < ((IItemHandler)world.getTileEntity(pos)).getSlots(); i++) InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), ((IItemHandler)world.getTileEntity(pos)).getStackInSlot(i));
		super.breakBlock(world, pos, state);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return state.getValue(BlockHorizontal.FACING).getAxis() == Axis.Z ? BBOX_X : BBOX_Z;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return state.getValue(BlockHorizontal.FACING).getAxis() == Axis.Z ? BBOX_X : BBOX_Z;
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
		public static final int BURN_TIME = 1200;
		
		private MagicPower magic_power = MagicPower.Provider.CAPABILITY.getDefaultInstance();
		
		private String current_recipe = "";
		
		private int burn_time, progress, recipe_time;
		
		public Tile()
		{
			super(13);
		}
		
		@Override
		public NBTTagCompound writeToNBT(NBTTagCompound tag)
		{
			tag.setString("current_recipe", current_recipe);
			tag.setInteger("burn_time", burn_time);
			tag.setInteger("progress", progress);
			tag.setInteger("recipe_time", recipe_time);
			tag.setInteger("power", magic_power.getAmount());
			return super.writeToNBT(tag);
		}
		
		@Override
		public void readFromNBT(NBTTagCompound tag)
		{
			super.readFromNBT(tag);
			current_recipe = tag.getString("current_recipe");
			burn_time = tag.getInteger("burn_time");
			progress = tag.getInteger("progress");
			recipe_time = tag.getInteger("recipe_time");
			magic_power.setAmount(tag.getInteger("power"));
		}
		
		@Override
		public SPacketUpdateTileEntity getUpdatePacket()
		{
			return new SPacketUpdateTileEntity(getPos(), 1, writeToNBT(new NBTTagCompound()));
		}
		
		@Override
		public void onDataPacket(NetworkManager manager, SPacketUpdateTileEntity packet)
		{
			readFromNBT(packet.getNbtCompound());
		}
		
		@Override
		public boolean hasCapability(Capability<?> capability, EnumFacing face)
		{
			return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == MagicPower.Provider.CAPABILITY || super.hasCapability(capability, face);
		}
		
		@Override
		public <T> T getCapability(Capability<T> capability, EnumFacing face)
		{
			return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(this) : capability == MagicPower.Provider.CAPABILITY ? MagicPower.Provider.CAPABILITY.cast(magic_power) : super.getCapability(capability, face);
		}
		
		@Override
		public boolean isItemValid(int slot, ItemStack stack)
		{
			return slot == 0 ? stack.getItem() == Items.BLAZE_POWDER : slot < 7;
		}
		
		@Override
		public void update()
		{
			if (burn_time > 0) burn_time--;
			if (!current_recipe.isEmpty())
			{
				if (burn_time == 0 && !getStackInSlot(0).isEmpty())
				{
					burn_time = BURN_TIME;
					extractItem(0, 1, false);
				}
				else if (burn_time > 0)
				{
					if (progress < recipe_time)
					{
						if (true) // mp.drainAltarFirst(this.world.getClosestPlayer(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5, 5, false), this.getPos(), this.world.provider.getDimension(), 2)) {
						{
							progress++;
						}
					}
					else progress = 0;
				}
			}
			checkRecipe();
		}
		
		private boolean canOutputFit(DistilleryRecipe recipe)
		{
			ItemStackHandler sim = new ItemStackHandler(6);
			for (int i = 0; i < sim.getSlots(); i++) sim.setStackInSlot(i, getStackInSlot(i + 7).copy());
			for (ItemStack stack : recipe.getOutput())
			{
				ItemStack sim0 = stack.copy();
				for (int i = 0; (i < sim.getSlots() && !sim0.isEmpty()); i++) sim0 = sim.insertItem(i, sim0, false);
				if (!sim0.isEmpty()) return false;
			}
			return true;
		}
		
		private void checkRecipe()
		{
			List<ItemStack> inputStacks = new ArrayList<ItemStack>();
			for (int i = 1; i < 7; i++) inputStacks.add(getStackInSlot(i));
			DistilleryRecipe recipe = Bewitchment.API.REGISTRY_DISTILLERY.getValuesCollection().parallelStream().filter(dr -> dr.matches(inputStacks)).findFirst().orElse(null);
			if (recipe == null)
			{
				current_recipe = "";
				progress = 0;
				recipe_time = -1;
			}
			else if (!current_recipe.equals(recipe.getRegistryName().toString()) && canOutputFit(recipe))
			{
				current_recipe = recipe.getRegistryName().toString();
				recipe_time = recipe.getTime();
			}
			if (recipe != null && progress >= recipe_time)
			{
				int i;
				for (i = 1; i < 7; i++) extractItem(i, 1, false);
				for (ItemStack stack : recipe.getOutput()) insertItem(getStackInSlot(i).getItem() != stack.getItem() || getStackInSlot(i).getMetadata() != stack.getMetadata() || getStackInSlot(i).getCount() >= getStackInSlot(i).getMaxStackSize() ? i++ : i, stack, false);
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
			addSlotToContainer(new ModSlot(tile, index++, 80, 58));
			for (int i = 0; i < 3; i++)
			{
				addSlotToContainer(new ModSlot(tile, index++, 18, (18 * (i + 1)) - 1));
				addSlotToContainer(new ModSlot(tile, index++, 36, (18 * (i + 1)) - 1));
			}
			for (int i = 0; i < 3; i++)
			{
				addSlotToContainer(new ModSlot(tile, index++, 124, (18 * (i + 1)) - 1));
				addSlotToContainer(new ModSlot(tile, index++, 142, (18 * (i + 1)) - 1));
			}
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
		private static final ResourceLocation TEX = new ResourceLocation(Bewitchment.MOD_ID, "textures/gui/distillery.png");
		
		private final Container container;
		
		public Gui(Container container)
		{
			super(container);
			this.container = container;
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
			drawTexturedModalRect(x + 76, y + 16, 176, 0, (container.tile.progress * 24 / Math.max(1, container.tile.recipe_time)) + 1, 17);
			int burnProgress = 14 - (int) Math.ceil((14 * (container.tile.burn_time / (double) Tile.BURN_TIME)));
			drawTexturedModalRect(x + 81, y + 36 + burnProgress, 242, burnProgress, 14, 14 - burnProgress);
		}
	}
}