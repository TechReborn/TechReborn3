package teamreborn.techreborn;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import teamreborn.techreborn.commands.CommandTechRebornTest;
import teamreborn.techreborn.proxy.TechRebornServer;
import teamreborn.techreborn.world.TechRebornWorld;

import java.util.ArrayList;
import java.util.List;

@Mod(modid = TRConstants.MOD_ID, name = TRConstants.MOD_NAME, version = TRConstants.MOD_VERSION, dependencies = TRConstants.MOD_DEPENDENCIES, acceptedMinecraftVersions = TRConstants.MINECRAFT_VERSIONS)
public class TechReborn {

	@Mod.Instance(TRConstants.MOD_ID)
	public static TechReborn INSTANCE;
	@SidedProxy(clientSide = TRConstants.CLIENT_PROXY_CLASS, serverSide = TRConstants.SERVER_PROXY_CLASS)
	public static TechRebornServer PROXY;
	public static List<Item> itemModelsToRegister = new ArrayList<>();
	public static List<Block> blockModelsToRegister = new ArrayList<>();

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		PROXY.registerRenders();
		TechRebornWorld.init();
	}

	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event){
		event.registerServerCommand(new CommandTechRebornTest());
	}

}
