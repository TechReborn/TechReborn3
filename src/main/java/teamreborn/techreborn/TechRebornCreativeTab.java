package teamreborn.techreborn;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamreborn.techreborn.item.ItemWrench;

/**
 * Created by Mark on 22/04/2017.
 */
public class TechRebornCreativeTab {
	public static final CreativeTabs TECHREBORN = new CreativeTabs(TRConstants.MOD_ID) {
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem() {
			return new ItemStack(ItemWrench.wrench);
		}
	};
}