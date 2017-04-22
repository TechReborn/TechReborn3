package teamreborn.techreborn.init;

import teamreborn.reborncore.modcl.BlockCL;
import teamreborn.reborncore.modcl.ItemCL;
import teamreborn.reborncore.modcl.ModCL;
import teamreborn.reborncore.modcl.RegistryCL;
import teamreborn.techreborn.TechReborn;

/**
 * Created by Prospector
 */
public class TechRebornRegistry extends RegistryCL {

	public void init(ModCL mod) {
		TechRebornItems.init();
		TechRebornBlocks.init();

		for (ItemCL item : TechReborn.MOD_CL.getRegistry().itemRegistry.values()) {
			register(item);
		}

		for (BlockCL block : TechReborn.MOD_CL.getRegistry().blockRegistry.values()) {
			register(block);
		}
	}

}
