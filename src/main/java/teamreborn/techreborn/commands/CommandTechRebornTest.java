package teamreborn.techreborn.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import teamreborn.techreborn.TRConstants;
import teamreborn.techreborn.world.TechRebornWorld;

/**
 * Created by Mark on 23/04/2017.
 */
public class CommandTechRebornTest extends CommandBase {
	@Override
	public String getName() {
		return "trdev";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "trdev";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		long start = System.currentTimeMillis();
		BlockPos pos = spiralOutwardsLookingForBiome(sender.getEntityWorld(), Biome.getBiome(0), sender.getPosition().getX(), sender.getPosition().getZ());
		System.out.println(pos);
		System.out.println((System.currentTimeMillis() - start) + "ms");
		if(sender instanceof EntityPlayerMP){
			EntityPlayerMP playerMP = (EntityPlayerMP) sender;
			playerMP.connection.setPlayerLocation(pos.getX(), 150, pos.getZ(), 0, 0);
		}
	}


	public static BlockPos spiralOutwardsLookingForBiome(World world, Biome biomeToFind, double startX, double startZ) {
		int sampleSpacing = 16;
		int maxDist = 10000;
		return spiralOutwardsLookingForBiome(world, biomeToFind, startX, startZ, maxDist, sampleSpacing);
	}
	//Based off https://github.com/Glitchfiend/BiomesOPlenty/blob/4977b0100ca55f96de50337f46ed673512cf503a/src/main/java/biomesoplenty/common/util/biome/BiomeUtils.java
	public static BlockPos spiralOutwardsLookingForBiome(World world, Biome biomeToFind, double startX, double startZ, int maxDist, int sampleSpace) {
		double a = sampleSpace / Math.sqrt(Math.PI);
		double b = 2 * Math.sqrt(Math.PI);
		double x;
		double z;
		double dist = 0;
		int n;
		for (n = 0; dist < maxDist; ++n) {
			double rootN = Math.sqrt(n);
			dist = a * rootN;
			x = startX + (dist * Math.sin(b * rootN));
			z = startZ + (dist * Math.cos(b * rootN));
			if (world.getBiome(new BlockPos(x, 0, z)).getRegistryName().getResourceDomain().equals(TRConstants.MOD_ID)) {
				return new BlockPos((int) x, 0, (int) z);
			}
		}
		return null;
	}
}
