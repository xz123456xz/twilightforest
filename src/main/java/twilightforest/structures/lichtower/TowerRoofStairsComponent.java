package twilightforest.structures.lichtower;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import twilightforest.TFFeature;

import java.util.Random;

public class TowerRoofStairsComponent extends TowerRoofComponent {

	public TowerRoofStairsComponent(ServerLevel level, CompoundTag nbt) {
		super(LichTowerPieces.TFLTRSt, nbt);
	}

	public TowerRoofStairsComponent(TFFeature feature, int i, TowerWingComponent wing) {
		super(LichTowerPieces.TFLTRSt, feature, i);

		// always facing = 0.  This roof cannot rotate, due to stair facing issues.
		this.setOrientation(Direction.SOUTH);

		this.size = wing.size; // assuming only square towers and roofs right now.
		this.height = size / 2;

		// just hang out at the very top of the tower
		makeCapBB(wing);
	}

	/**
	 * Makes a pyramid-shaped roof out of stairs
	 */
	@Override
	public boolean postProcess(WorldGenLevel world, StructureFeatureManager manager, ChunkGenerator generator, Random rand, BoundingBox sbb, ChunkPos chunkPosIn, BlockPos blockPos) {
		BlockState birchSlab = Blocks.BIRCH_SLAB.defaultBlockState();
		BlockState birchPlanks = Blocks.BIRCH_PLANKS.defaultBlockState();

		BlockState birchStairsNorth = Blocks.BIRCH_STAIRS.defaultBlockState().setValue(StairBlock.FACING, Direction.NORTH);
		BlockState birchStairsSouth = Blocks.BIRCH_STAIRS.defaultBlockState().setValue(StairBlock.FACING, Direction.SOUTH);
		BlockState birchStairsEast = Blocks.BIRCH_STAIRS.defaultBlockState().setValue(StairBlock.FACING, Direction.EAST);
		BlockState birchStairsWest = Blocks.BIRCH_STAIRS.defaultBlockState().setValue(StairBlock.FACING, Direction.WEST);

		for (int y = 0; y <= height; y++) {
			int min = y;
			int max = size - y - 1;
			for (int x = min; x <= max; x++) {
				for (int z = min; z <= max; z++) {
					if (x == min) {
						if (z == min || z == max) {
							placeBlock(world, birchSlab, x, y, z, sbb);
						} else {
							placeBlock(world, birchStairsWest, x, y, z, sbb);
						}
					} else if (x == max) {
						if (z == min || z == max) {
							placeBlock(world, birchSlab, x, y, z, sbb);
						} else {
							placeBlock(world, birchStairsEast, x, y, z, sbb);
						}
					} else if (z == max) {
						placeBlock(world, birchStairsSouth, x, y, z, sbb);
					} else if (z == min) {
						placeBlock(world, birchStairsNorth, x, y, z, sbb);
					} else {
						placeBlock(world, birchPlanks, x, y, z, sbb);
					}
				}
			}
		}
		return true;
	}
}
