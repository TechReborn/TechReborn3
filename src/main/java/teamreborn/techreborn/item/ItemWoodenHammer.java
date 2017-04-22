package teamreborn.techreborn.item;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamreborn.reborncore.api.registry.RebornRegistry;
import teamreborn.reborncore.api.registry.impl.ItemRegistry;
import teamreborn.techreborn.TRConstants;
import teamreborn.techreborn.blocks.BlockRubberLog;
import teamreborn.techreborn.blocks.BlockTreetap;

/**
 * Created by Prospector
 */
@RebornRegistry(modID = TRConstants.MOD_ID)
public class ItemWoodenHammer extends ItemTR {

	@ItemRegistry
	public static ItemWoodenHammer wooden_hammer;

	public ItemWoodenHammer() {
		super("wooden_hammer");
		setMaxStackSize(1);
		setMaxDamage(64);
	}

	private EnumHand getOppositeHand(EnumHand hand) {
		switch (hand) {
			case MAIN_HAND:
				return EnumHand.OFF_HAND;
			default:
				return EnumHand.MAIN_HAND;
		}
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = world.getBlockState(pos);
		if (!world.isRemote && state.getBlock() instanceof BlockRubberLog && player.getHeldItem(getOppositeHand(hand)).getItem().equals(Item.getItemFromBlock(BlockTreetap.treetap)) && state.getValue(BlockRubberLog.SAP_SIDE) != BlockRubberLog.EnumSapSide.NONE && state.getValue(BlockRubberLog.SAP_SIDE) == BlockRubberLog.getSideFromFacing(facing)) {
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.PASS;
	}
}