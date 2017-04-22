package teamreborn.techreborn.init;

import net.minecraft.item.ItemStack;
import teamreborn.reborncore.modcl.ItemCL;
import teamreborn.techreborn.TechReborn;

/**
 * Created by Prospector
 */
public class TechRebornItems {

	public static void init() {

	}

	protected static void addToRegistry(ItemCL item) {
		addToRegistry(item.name.replaceFirst("^routiduct:", ""), item);
	}

	protected static void addToRegistry(String name, ItemCL item) {
		TechReborn.MOD_CL.getRegistry().itemRegistry.put(name, item);
	}

	protected static void addToRegistry(ItemCL item, String... oreDictNames) {
		addToRegistry(item.name.replaceFirst("^routiduct:", ""), item, oreDictNames);
	}

	protected static void addToRegistry(String name, ItemCL item, String... oreDictNames) {
		addToRegistry(name, item);
		for (String oreName : oreDictNames)
			TechReborn.MOD_CL.getRegistry().oreEntries.put(new ItemStack(item), oreName);
	}
}
