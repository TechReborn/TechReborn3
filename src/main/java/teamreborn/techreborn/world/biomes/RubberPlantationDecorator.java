package teamreborn.techreborn.world.biomes;

import net.minecraft.world.biome.BiomeDecorator;

/**
 * Created by Mark on 23/04/2017.
 */
public class RubberPlantationDecorator extends BiomeDecorator {

	public RubberPlantationDecorator() {
		treesPerChunk = 12;
		extraTreeChance = 0.2F;
		flowersPerChunk = 0;
		grassPerChunk = 20;
	}
}
