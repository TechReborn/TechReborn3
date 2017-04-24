package teamreborn.techreborn.world.biomes;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import teamreborn.techreborn.world.RubberTreeGenerator;

import java.util.Random;

/**
 * Created by Mark on 23/04/2017.
 */
public class RubberPlantationDecorator extends BiomeDecorator {

	RubberTreeGenerator treeGenerator = new RubberTreeGenerator();
	public int rubberTreesPerChunk;

	public RubberPlantationDecorator() {
		rubberTreesPerChunk = 10;
		treesPerChunk = 4;
		flowersPerChunk = 4;
		grassPerChunk = 24;
		waterlilyPerChunk = 10;
	}

	@Override
	protected void genDecorations(Biome biomeIn, World worldIn, Random random) {
		for (int chance = 0; chance < rubberTreesPerChunk; ++chance) {
			BlockPos blockpos = worldIn.getHeight(this.chunkPos);
			treeGenerator.generate(worldIn, random, blockpos);
		}
		super.genDecorations(biomeIn, worldIn, random);
	}
}
