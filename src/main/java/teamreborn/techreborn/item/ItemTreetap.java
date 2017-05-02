package teamreborn.techreborn.item;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import teamreborn.techreborn.blocks.BlockRubberLog;

/**
 * Created by Prospector
 */
public class ItemTreetap extends ItemBlock {
	public ItemTreetap(Block block) {
		super(block);
	}

	@Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack) {
		if (player.getHeldItem(EnumHand.MAIN_HAND) == stack) {
			if (!(player.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemWoodenHammer)) {
				player.sendStatusMessage(new TextComponentString("Try holding a hammer in your other hand"), true);
				return false;
			}
		}
		if (player.getHeldItem(EnumHand.OFF_HAND) == stack) {
			if (!(player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemWoodenHammer)) {
				player.sendStatusMessage(new TextComponentString("Try holding a hammer in your other hand"), true);
				return false;
			}
		}
		IBlockState state = worldIn.getBlockState(pos);
		if (state.getBlock() instanceof BlockRubberLog && state.getValue(BlockRubberLog.SAP_SIDE) != BlockRubberLog.EnumSapSide.NONE && state.getValue(BlockRubberLog.SAP_SIDE) == BlockRubberLog.getSideFromFacing(side)) {
			return true;
		}
		player.sendStatusMessage(new TextComponentString("There isnt a sap spot here..."), true);
		return false;
	}
}
