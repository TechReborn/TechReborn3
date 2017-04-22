package teamreborn.techreborn;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import teamreborn.reborncore.modcl.ModCL;
import teamreborn.reborncore.modcl.RegistryCL;
import teamreborn.techreborn.init.TechRebornRegistry;
import teamreborn.techreborn.proxy.TechRebornServer;

@Mod(modid = TechReborn.MOD_ID, name = TechReborn.MOD_NAME, version = TechReborn.MOD_VERSION, dependencies = TechReborn.MOD_DEPENDENCIES, acceptedMinecraftVersions = TechReborn.MINECRAFT_VERSIONS)
public class TechReborn extends ModCL {
	protected static final String MOD_NAME = "Tech Reborn 3";
	protected static final String MOD_ID = "techreborn";
	protected static final String MOD_VERSION = "%version%";
	protected static final String MINECRAFT_VERSIONS = "[1.11.2]";
	protected static final String MOD_DEPENDENCIES = "required-after:reborncore;";
	protected static final String SERVER_PROXY_CLASS = "teamreborn.techreborn.proxy.TechRebornServer";
	protected static final String CLIENT_PROXY_CLASS = "teamreborn.techreborn.proxy.TechRebornClient";
	protected static final RegistryCL REGISTRY = new TechRebornRegistry();
	@Mod.Instance(MOD_ID)
	public static TechReborn MOD_CL;
	@SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = SERVER_PROXY_CLASS)
	public static TechRebornServer PROXY;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		getRegistry().init(MOD_CL);
		PROXY.registerRenders();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {

	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

	@Override
	public String getServerProxy() {
		return SERVER_PROXY_CLASS;
	}

	@Override
	public String getClientProxy() {
		return CLIENT_PROXY_CLASS;
	}

	@Override
	public String getModName() {
		return MOD_NAME;
	}

	@Override
	public String getModID() {
		return MOD_ID;
	}

	@Override
	public String getModVersion() {
		return MOD_VERSION;
	}

	@Override
	public String getModDependencies() {
		return MOD_DEPENDENCIES;
	}

	@Override
	public RegistryCL getRegistry() {
		return REGISTRY;
	}

	@Override
	public String getMinecraftVersion() {
		return MINECRAFT_VERSIONS;
	}
}
