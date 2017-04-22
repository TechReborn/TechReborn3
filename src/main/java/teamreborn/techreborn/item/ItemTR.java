package teamreborn.techreborn.item;

import net.minecraft.item.Item;
import teamreborn.techreborn.TRConstants;
import teamreborn.techreborn.TechReborn;
import teamreborn.techreborn.TechRebornCreativeTab;

/**
 * Created by Prospector
 */
public class ItemTR extends Item {

	public ItemTR(String name, boolean registerModel) {
		super();
		setRegistryName(TRConstants.MOD_ID, name);
		setUnlocalizedName(TRConstants.MOD_ID + ":" + name);
		setCreativeTab(TechRebornCreativeTab.TECHREBORN);
		if (registerModel) {
			TechReborn.itemModelsToRegister.add(this);
		}
	}

	public ItemTR(String name) {
		this(name, true);
	}
}
