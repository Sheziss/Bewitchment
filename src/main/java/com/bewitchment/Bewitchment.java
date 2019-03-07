package com.bewitchment;

import com.bewitchment.common.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Bewitchment.MOD_ID, name = Bewitchment.MOD_NAME, version = Bewitchment.MOD_VERSION)
public class Bewitchment
{
	public static final String MOD_ID = "bewitchment", MOD_NAME = "Bewitchment", MOD_VERSION = "0.1";
	
	@Instance(Bewitchment.MOD_ID)
	public static Bewitchment instance;
	
	@SidedProxy(serverSide = "com.bewitchment.common.CommonProxy", clientSide = "com.bewitchment.client.ClientProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
	}
}