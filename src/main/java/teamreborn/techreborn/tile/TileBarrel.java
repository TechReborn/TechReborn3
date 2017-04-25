package teamreborn.techreborn.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import teamreborn.reborncore.api.registry.RebornRegistry;
import teamreborn.reborncore.api.registry.impl.TileRegistry;
import teamreborn.reborncore.util.Tank;
import teamreborn.techreborn.TRConstants;
import teamreborn.techreborn.fluids.TechRebornFluids;

/**
 * Created by Prospector
 */
@RebornRegistry(TRConstants.MOD_ID)
@TileRegistry(name = "TileBarrel")
public class TileBarrel extends TileEntity {

	public Tank tank = new Tank("sap_stored", 2000, this, TechRebornFluids.SAP.fluid);

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound = super.writeToNBT(compound);
		tank.writeToNBT(compound);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		tank.readFromNBT(compound);
	}

}
