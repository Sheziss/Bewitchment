package com.bewitchment;

import com.bewitchment.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//R DZH SVIV
//HFMXLMFIV11
//HKRMLHZFIFH111
//MLGOVTZOGVMWVI
//OVHLERPP
//WZGFIZ
//GSILFTS GSVHV ORMVH R WL WVXIVV
//GSZG YB NVIVOB KFGGRMT NB MZNV RM
//NB RMUOFVMXV TILDH, ZMW GSFH, R YVXLNV RNNLIGZO
//XBYVIMVGRX DVY


//GL HLNV, R ZN YFG Z HSZWV
//YFG SVIV, R VCVIG KLDVI LEVI GSVN
//R NZWV Z XZHGOV SVIV LM GSRH SROO
//ZMW SZEV KFG NB UOZT RM GSV TILFMW
//GSRH RH NB MVD DLIOW


//HSLFOW GSV GIVHKZHHVIH LU GSV LOW DLIOW
//ZIIREV SVIV, YVZIRMT SLHGRORGRVH
//GSVB DROO YV NVG DRGS DIZGS

@Mod(modid = Bewitchment.MODID, name = Bewitchment.NAME, version = Bewitchment.VERSION)
public class Bewitchment {
	public static final String MODID = "bewitchment", NAME = "Bewitchment", VERSION = "0.20-rewrite9";
	public static final Logger LOGGER = LogManager.getLogger(NAME);

	@Instance(Bewitchment.MODID)
	public static Bewitchment instance;

	@SidedProxy(serverSide = "com.bewitchment.common.CommonProxy", clientSide = "com.bewitchment.client.ClientProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
		LOGGER.info("Remember when I told you how my");
		LOGGER.info("Kin is different in some ways?");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
		LOGGER.info("It's a fact, she is exactly that!");
		LOGGER.info("A harbinger of death from the world of witchcraft,");
		LOGGER.info("And she's feeding them cakes and her ale to this innocent boy,");
		LOGGER.info("And her magic brings dismay!");

		LOGGER.info("I hear her in the wind, the bane of our town");
		LOGGER.info("Come with me, father, I'm to expose a heathen");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}