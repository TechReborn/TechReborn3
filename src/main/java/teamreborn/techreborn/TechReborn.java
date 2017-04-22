package teamreborn.techreborn;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import teamreborn.techreborn.proxy.TechRebornServer;

@Mod(modid = TRConstants.MOD_ID, name = TRConstants.MOD_NAME, version = TRConstants.MOD_VERSION, dependencies = TRConstants.MOD_DEPENDENCIES, acceptedMinecraftVersions = TRConstants.MINECRAFT_VERSIONS)
public class TechReborn {

	@Mod.Instance(TRConstants.MOD_ID)
	public static TechReborn MOD_CL;
	@SidedProxy(clientSide = TRConstants.CLIENT_PROXY_CLASS, serverSide = TRConstants.SERVER_PROXY_CLASS)
	public static TechRebornServer PROXY;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		PROXY.registerRenders();
	}

}
