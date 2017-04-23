package teamreborn.techreborn.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import teamreborn.reborncore.api.registry.RebornRegistry;
import teamreborn.reborncore.api.registry.impl.TileRegistry;
import teamreborn.techreborn.TRConstants;

/**
 * Created by Prospector
 */
@RebornRegistry(TRConstants.MOD_ID)
@TileRegistry(name = "TileTreetap")
public class TileTreetap extends TileEntity implements ITickable {
	@Override
	public void update() {

	}
}
