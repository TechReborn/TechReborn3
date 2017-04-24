package teamreborn.techreborn.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import teamreborn.reborncore.api.registry.RebornRegistry;
import teamreborn.reborncore.api.registry.impl.TileRegistry;
import teamreborn.techreborn.TRConstants;
import teamreborn.techreborn.blocks.BlockTTrapdoor;
import teamreborn.techreborn.transmission.EnumFrequency;
import teamreborn.techreborn.transmission.Frequency;
import teamreborn.techreborn.transmission.ITransmissionReciever;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prospector
 */
@RebornRegistry(TRConstants.MOD_ID)
@TileRegistry(name = "TileTransmissionTrapdoor")
public class TileTTrapdoor extends TileEntity implements ITransmissionReciever, ITickable {
	public Frequency openFrequency = new Frequency("open", EnumFrequency.ALPHA, false);

	public TileTTrapdoor() {

	}

	@Override
	public void update() {
		System.out.println(openFrequency.isPowered());
	}

	@Override
	public void setFrequency(EnumFrequency enumFrequency, Frequency frequency) {
		if (enumFrequency == EnumFrequency.ALPHA) {
			openFrequency = frequency;
		}
	}

	@Override
	public List<Frequency> getValidFrequencies() {
		List<Frequency> frequencies = new ArrayList<>();
		frequencies.add(openFrequency);
		return frequencies;
	}

	@Override
	public void onPowerUpdate() {
		world.setBlockState(pos, world.getBlockState(pos).withProperty(BlockTTrapdoor.OPEN, openFrequency.isPowered()));
	}
}
