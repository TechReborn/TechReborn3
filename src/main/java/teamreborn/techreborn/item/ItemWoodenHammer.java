package teamreborn.techreborn.item;

import com.google.common.collect.Multimap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

		if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", 9, 0));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", (double) -3.4F, 0));
		}

		return multimap;
	}

	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		stack.damageItem(2, attacker);
		return true;
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