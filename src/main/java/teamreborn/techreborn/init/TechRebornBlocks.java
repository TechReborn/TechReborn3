package teamreborn.techreborn.init;

import net.minecraft.item.ItemStack;
import teamreborn.reborncore.modcl.BlockCL;
import teamreborn.techreborn.TechReborn;

/**
 * Created by Prospector
 */
public class TechRebornBlocks {

	public static void init() {

	}

	protected static void addToRegistry(BlockCL block) {
		addToRegistry(block.name.replaceFirst("^techreborn:", ""), block);
	}

	protected static void addToRegistry(String name, BlockCL block) {
		TechReborn.MOD_CL.getRegistry().blockRegistry.put(name, block);
	}

	protected static void addToRegistry(BlockCL block, String... oreDictNames) {
		addToRegistry(block.name.replaceFirst("^techreborn:", ""), block, oreDictNames);
	}

	protected static void addToRegistry(String name, BlockCL block, String... oreDictNames) {
		addToRegistry(name, block);
		for (String oreName : oreDictNames)
			TechReborn.MOD_CL.getRegistry().oreEntries.put(new ItemStack(block), oreName);
	}
}
