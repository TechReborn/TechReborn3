package teamreborn.techreborn.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamreborn.reborncore.api.registry.RebornRegistry;
import teamreborn.reborncore.api.registry.impl.ItemRegistry;
import teamreborn.reborncore.reborninfoprovider.RebornInfoProviderHUD;
import teamreborn.reborncore.reborninfoprovider.elements.BlockDisplayElement;
import teamreborn.techreborn.TRConstants;

/**
 * Created by Prospector
 */
@RebornRegistry(TRConstants.MOD_ID)
public class ItemWrench extends ItemTR {

	@ItemRegistry
	public static ItemWrench wrench;

	public ItemWrench() {
		super("wrench");
		setMaxStackSize(1);
		setHarvestLevel("wrench", 1);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (world.isRemote) {
			RebornInfoProviderHUD.addElement(new BlockDisplayElement());
		}
		if (!world.isRemote && player.isSneaking()) {
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.PASS;
	}
}
