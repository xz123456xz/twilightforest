package twilightforest.structures.start;

import com.mojang.serialization.Codec;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import twilightforest.TFFeature;
import twilightforest.TFStructures;
import twilightforest.structures.TFStructureComponent;
import twilightforest.structures.TFStructureComponentTemplate;
import twilightforest.world.TFGenerationSettings;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

import net.minecraft.world.level.levelgen.feature.StructureFeature.StructureStartFactory;

public class TFStructure<C extends FeatureConfiguration> extends StructureFeature<C> {

	private final TFFeature feature;
	private final boolean template;

	public TFStructure(Codec<C> codec, TFFeature feature) {
		this(codec, feature, false);
	}

	public TFStructure(Codec<C> codec, TFFeature feature, boolean template) {
		super(codec);
		this.feature = feature;
		this.template = template;
	}

	public TFFeature getFeature() {
		return feature;
	}

	@Override
	public List<MobSpawnSettings.SpawnerData> getDefaultSpawnList() {
		return feature.getCombinedMonsterSpawnableList();
	}

	@Override
	public List<MobSpawnSettings.SpawnerData> getDefaultCreatureSpawnList() {
		return feature.getCombinedCreatureSpawnableList();
	}

	@Override
	public boolean getDefaultRestrictsSpawnsToInside() {
		return true;
	}

	@Override
	public GenerationStep.Decoration step() {
		return feature.getDecorationStage();
	}

	@Override
	public StructureStartFactory<C> getStartFactory() {
		return template ? TemplateStart::new : Start::new;
	}

	private StructureStart<C> createStructureStart(ChunkPos pos, int refCount, long seed) {
		return this.getStartFactory().create(this, pos, refCount, seed);
	}

	@Override
	public StructureStart<?> generate(RegistryAccess dynamicRegistries, ChunkGenerator generator, BiomeSource provider, StructureManager templateManager, long seed, ChunkPos pos, Biome biome, int refCount, WorldgenRandom rand, StructureFeatureConfiguration settings, C config, LevelHeightAccessor accessor) {
		ChunkPos chunkpos = this.getPotentialFeatureChunk(settings, seed, rand, pos.x, pos.z);
		if (this.isFeatureChunk(generator, provider, seed, rand, pos, biome, chunkpos, config, accessor)) {
			StructureStart<C> structurestart = this.createStructureStart(pos, refCount, seed);
			structurestart.generatePieces(dynamicRegistries, generator, templateManager, pos, biome, config, accessor);
			if (structurestart.isValid()) {
				return structurestart;
			}
		}

		return StructureStart.INVALID_START;
	}

	@Override
	protected boolean isFeatureChunk(ChunkGenerator generator, BiomeSource provider, long seed, WorldgenRandom random, ChunkPos pos, Biome biome, ChunkPos structurePos, C config, LevelHeightAccessor accessor) {
		return TFFeature.isInFeatureChunk(pos.x << 4, pos.z << 4) && TFFeature.generateFeature(pos.x, pos.z, biome, seed) == feature;
	}

	private static int getSpawnListIndexAt(StructureStart<?> start, BlockPos pos) {
		int highestFoundIndex = -1;
		for (StructurePiece component : start.getPieces()) {
			if (component.getBoundingBox().isInside(pos)) {
				if (component instanceof TFStructureComponent) {
					TFStructureComponent tfComponent = (TFStructureComponent) component;
					if (tfComponent.spawnListIndex > highestFoundIndex)
						highestFoundIndex = tfComponent.spawnListIndex;
				} else
					return 0;
			}
		}
		return highestFoundIndex;
	}

	// FIXME: reimplement conquered status check
	@Nullable
	public static List<MobSpawnSettings.SpawnerData> gatherPotentialSpawns(StructureFeatureManager structureManager, MobCategory classification, BlockPos pos) {
		for (StructureFeature<?> structure : TFStructures.SEPARATION_SETTINGS.keySet()) {
			StructureStart<?> start = structureManager.getStructureAt(pos, true, structure);
			if (!start.isValid())
				continue;
			TFFeature feature = ((TFStructure<?>) structure).feature;
			if (classification != MobCategory.MONSTER)
				return feature.getSpawnableList(classification);
			final int index = getSpawnListIndexAt(start, pos);
			if (index < 0)
				return null;
			return feature.getSpawnableMonsterList(index);
		}
		return null;
	}

	// FIXME: reimplement conquered status
	private class Start extends StructureStart<C> {

		public Start(StructureFeature<C> p_i225876_1_, ChunkPos p_i225876_2_, int p_i225876_3_, long p_i225876_4_) {
			super(p_i225876_1_, p_i225876_2_, p_i225876_3_, p_i225876_4_);
		}

		@Override
		public void generatePieces(RegistryAccess p_230364_1_, ChunkGenerator p_230364_2_, StructureManager p_230364_3_, ChunkPos p_230364_4_, Biome p_230364_5_, C p_230364_6_, LevelHeightAccessor p_230364_7_) {
			boolean dontCenter = feature == TFFeature.LICH_TOWER || feature == TFFeature.TROLL_CAVE || feature == TFFeature.YETI_CAVE;
			int x = (p_230364_4_.x << 4) + (dontCenter ? 0 : 7);
			int z = (p_230364_4_.z << 4) + (dontCenter ? 0 : 7);
			int y = TFGenerationSettings.SEALEVEL + 1;
			StructurePiece start = feature.provideStructureStart(random, x, y, z);
			if(start == null)
				return;
			this.addPiece(start);
			start.addChildren(start, this, random);
			createBoundingBox();
		}
	}

	// FIXME: reimplement conquered status
	private class TemplateStart extends Start {

		public TemplateStart(StructureFeature<C> p_i225876_1_, ChunkPos p_i225876_2_, int p_i225876_3_, long p_i225876_4_) {
			super(p_i225876_1_, p_i225876_2_, p_i225876_3_, p_i225876_4_);
		}

		@Override
		public void generatePieces(RegistryAccess p_230364_1_, ChunkGenerator p_230364_2_, StructureManager p_230364_3_, ChunkPos p_230364_4_, Biome p_230364_5_, C p_230364_6_, LevelHeightAccessor p_230364_7_) {
			super.generatePieces(p_230364_1_, p_230364_2_, p_230364_3_, p_230364_4_, p_230364_5_, p_230364_6_, p_230364_7_);
			pieces.stream().filter(piece -> piece instanceof TFStructureComponentTemplate).map(TFStructureComponentTemplate.class::cast).forEach(piece -> piece.setup(p_230364_3_));
			createBoundingBox();
		}

		@Override
		public void placeInChunk(WorldGenLevel p_230366_1_, StructureFeatureManager p_230366_2_, ChunkGenerator p_230366_3_, Random p_230366_4_, BoundingBox p_230366_5_, ChunkPos p_230366_6_) {
			pieces.stream().filter(TFStructureComponentTemplate.class::isInstance).map(TFStructureComponentTemplate.class::cast).filter(component -> component.LAZY_TEMPLATE_LOADER != null).
					forEach(component -> component.LAZY_TEMPLATE_LOADER.run());
			super.placeInChunk(p_230366_1_, p_230366_2_, p_230366_3_, p_230366_4_, p_230366_5_, p_230366_6_);
		}

	}
}