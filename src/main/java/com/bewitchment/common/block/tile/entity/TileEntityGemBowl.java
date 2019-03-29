package com.bewitchment.common.block.tile.entity;

import java.util.HashMap;
import java.util.Map;

import com.bewitchment.common.block.tile.entity.util.ModTileEntity;

import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityGemBowl extends ModTileEntity
{
	public static final Map<String, Integer> gainMap = new HashMap<>();
	
	public final ItemStackHandler inventory = new ItemStackHandler(18)
	{
		@Override
		public int getSlotLimit(int slot)
		{
			return 1;
		}
	};
	
	static
	{
		gainMap.put("fossil", 1);
		gainMap.put("gemDiamond", 4);
		gainMap.put("gemEmerald", 4);
		gainMap.put("gemPsi", 4);
		gainMap.put("gemPrismarine", 2);
		gainMap.put("gemLifeCrystal", 2);
		gainMap.put("gemQuartz", 1);
		gainMap.put("gemLapis", 1);
		gainMap.put("dustRedstone", 1);
		gainMap.put("gemBloodstone", 2);
		gainMap.put("gemNuummite", 2);
		gainMap.put("gemGarnet", 2);
		gainMap.put("gemTourmaline", 2);
		gainMap.put("gemJasper", 2);
		gainMap.put("gemTigersEye", 2);
		gainMap.put("gemMalachite", 2);
		gainMap.put("gemAmethyst", 2);
		gainMap.put("gemAlexandrite", 2);
		gainMap.put("gemAquamarine", 1);
		gainMap.put("gemRuby", 1);
		gainMap.put("gemSapphire", 1);
		gainMap.put("gemPeridot", 1);
		gainMap.put("gemAmber", 1);
		gainMap.put("gemApatite", 1);
		gainMap.put("gemTopaz", 1);
		gainMap.put("gemJet", 1);
		gainMap.put("gemTanzanite", 1);
		gainMap.put("gemPearl", 1);
		gainMap.put("gemOpal", 1);
		gainMap.put("gemZanite", 1);
		gainMap.put("gemCrimsonMiddleGem", 1);
		gainMap.put("gemAquaMiddleGem", 1);
		gainMap.put("gemGreenMiddleGem", 1);
		gainMap.put("gemZircon", 1);
		gainMap.put("gemAzurite", 1);
		gainMap.put("gemEudialyte", 1);
		gainMap.put("gemRime", 1);
		gainMap.put("gemAgate", 1);
		gainMap.put("gemJade", 1);
		gainMap.put("gemOnyx", 1);
		gainMap.put("gemEnderBiotite", 1);
		gainMap.put("gemBurnium", 1);
		gainMap.put("gemEndimium", 1);
		gainMap.put("gemHephaestite", 1);
		gainMap.put("gemScarlite", 1);
		gainMap.put("gemAether", 1);
		gainMap.put("gemSerpentine", 1);
		gainMap.put("gemPetoskeyStone", 1);
		gainMap.put("gemValonite", 1);
		gainMap.put("gemRhodochrosite", 1);
		gainMap.put("gemBoronNitride", 1);
		gainMap.put("gemFluorite", 1);
		gainMap.put("gemVilliaumite", 1);
		gainMap.put("gemCarobbiite", 1);
		gainMap.put("gemMoon", 1);
		gainMap.put("gemRedstone", 1);
		gainMap.put("gemVinteum", 1);
		gainMap.put("gemQuartzite", 1);
		gainMap.put("gemGlass", 1);
		gainMap.put("gemQuartzBlock", 1);
		gainMap.put("gemAlmandine", 1);
		gainMap.put("gemBlueTopaz", 1);
		gainMap.put("gemCinnabar", 1);
		gainMap.put("gemGreenSapphire", 1);
		gainMap.put("gemRutile", 1);
		gainMap.put("gemLazurite", 1);
		gainMap.put("gemSodalite", 1);
		gainMap.put("gemCertusQuartz", 1);
		gainMap.put("gemOlivine", 1);
		gainMap.put("gemLignite", 1);
		gainMap.put("gemGarnetRed", 1);
		gainMap.put("gemGarnetYellow", 1);
		gainMap.put("gemMonazite", 1);
		gainMap.put("gemDominicanAmber", 1);
		gainMap.put("gemScarabBlue", 1);
		gainMap.put("gemScarab", 1);
		gainMap.put("gemMoldavite", 2);
		gainMap.put("gemVioletSapphire", 1);
		gainMap.put("gemCatsEye", 1);
		gainMap.put("gemAmmolite", 1);
		gainMap.put("gemSpinel", 1);
		gainMap.put("gemMoonstone", 1);
		gainMap.put("gemSunstone", 1);
		gainMap.put("gemPyrope", 1);
		gainMap.put("gemRoseQuartz", 1);
		gainMap.put("gemKunzite", 1);
		gainMap.put("gemKyanite", 1);
		gainMap.put("gemChrysoprase", 1);
		gainMap.put("gemBlackDiamond", 1);
		gainMap.put("gemTektite", 1);
		gainMap.put("gemMorganite", 1);
		gainMap.put("gemLepidolite", 1);
		gainMap.put("gemCoral", 1);
		gainMap.put("gemCarnelian", 1);
		gainMap.put("gemGoldenBeryl", 1);
		gainMap.put("gemHeliodor", 1);
		gainMap.put("gemIndicolite", 1);
		gainMap.put("gemIolite", 1);
		gainMap.put("gemTurquoise", 1);
		gainMap.put("materialCoraliumPearl", 1);
		gainMap.put("gemShadow", 1);
	}
	
	@Override
	public ItemStackHandler[] getInventories()
	{
		return new ItemStackHandler[] {inventory};
	}
	
	public int getGemValue()
	{
		for (int id : OreDictionary.getOreIDs(inventory.getStackInSlot(0))) if (gainMap.containsKey(OreDictionary.getOreName(id))) return gainMap.get(OreDictionary.getOreName(id));
		return 0;
	}
}