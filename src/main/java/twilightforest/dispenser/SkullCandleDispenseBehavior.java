package twilightforest.dispenser;

import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import twilightforest.block.*;
import twilightforest.block.entity.SkullCandleBlockEntity;

public class SkullCandleDispenseBehavior extends OptionalDispenseItemBehavior {

	private static Item candle;

	public SkullCandleDispenseBehavior(Item candleItem) {
		 candle = candleItem;
	}

	@Override
	protected ItemStack execute(BlockSource source, ItemStack stack) {
		ServerLevel level = source.getLevel();
		if (!level.isClientSide()) {
			BlockPos blockpos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
			this.setSuccess(tryAddCandle(level, blockpos) || tryCreateSkullCandle(level, blockpos));
			if (this.isSuccess()) {
				stack.shrink(1);
			}
		}

		return stack;
	}

	private static boolean tryAddCandle(ServerLevel level, BlockPos pos) {
		if(level.getBlockEntity(pos) instanceof SkullCandleBlockEntity sc) {
			if (candle == AbstractSkullCandleBlock.candleColorToCandle(AbstractSkullCandleBlock.CandleColors.colorFromInt(sc.candleColor).getSerializedName()).asItem()) {
				if (sc.candleAmount < 4) {
					sc.candleAmount++;
					level.playSound(null, pos, SoundEvents.CANDLE_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
					level.getLightEngine().checkBlock(pos);
					level.setBlockEntity(new SkullCandleBlockEntity(pos, level.getBlockState(pos), sc.candleColor, sc.candleAmount));
					return true;
				}
			}
		}
		return false;
	}

	private static boolean tryCreateSkullCandle(ServerLevel level, BlockPos pos) {
		BlockState blockstate = level.getBlockState(pos);
		if (blockstate.getBlock() instanceof AbstractSkullBlock skull) {
			SkullBlock.Types type = (SkullBlock.Types) skull.getType();
			boolean wall = blockstate.getBlock() instanceof WallSkullBlock;
			switch (type) {

				case SKELETON -> {
					if (wall) makeWallSkull(level, pos, TFBlocks.SKELETON_WALL_SKULL_CANDLE.get());
					else makeFloorSkull(level, pos, TFBlocks.SKELETON_SKULL_CANDLE.get());
				}
				case WITHER_SKELETON -> {
					if (wall) makeWallSkull(level, pos, TFBlocks.WITHER_SKELE_WALL_SKULL_CANDLE.get());
					else makeFloorSkull(level, pos, TFBlocks.WITHER_SKELE_SKULL_CANDLE.get());
				}
				case PLAYER -> {
					if (wall) makeWallSkull(level, pos, TFBlocks.PLAYER_WALL_SKULL_CANDLE.get());
					else makeFloorSkull(level, pos, TFBlocks.PLAYER_SKULL_CANDLE.get());
				}
				case ZOMBIE -> {
					if (wall) makeWallSkull(level, pos, TFBlocks.ZOMBIE_WALL_SKULL_CANDLE.get());
					else makeFloorSkull(level, pos, TFBlocks.ZOMBIE_SKULL_CANDLE.get());
				}
				case CREEPER -> {
					if (wall) makeWallSkull(level, pos, TFBlocks.CREEPER_WALL_SKULL_CANDLE.get());
					else makeFloorSkull(level, pos, TFBlocks.CREEPER_SKULL_CANDLE.get());
				}
				default -> { return false; }
			}
			return true;
		}

		return false;
	}

	private static void makeFloorSkull(Level level, BlockPos pos, Block newBlock) {
		GameProfile profile = null;
		if(level.getBlockEntity(pos) instanceof SkullBlockEntity skull) profile = skull.getOwnerProfile();
		level.setBlockAndUpdate(pos, newBlock.defaultBlockState()
				.setValue(AbstractSkullCandleBlock.LIGHTING, AbstractLightableBlock.Lighting.NONE)
				.setValue(SkullCandleBlock.ROTATION, level.getBlockState(pos).getValue(SkullBlock.ROTATION)));
		level.setBlockEntity(new SkullCandleBlockEntity(pos,
				newBlock.defaultBlockState()
						.setValue(AbstractSkullCandleBlock.LIGHTING, AbstractLightableBlock.Lighting.NONE)
						.setValue(SkullCandleBlock.ROTATION, level.getBlockState(pos).getValue(SkullBlock.ROTATION)),
				AbstractSkullCandleBlock.candleToCandleColor(candle).getValue(), 1));
		if(level.getBlockEntity(pos) instanceof SkullCandleBlockEntity sc) sc.setOwner(profile);
	}

	private static void makeWallSkull(Level level, BlockPos pos, Block newBlock) {
		GameProfile profile = null;
		if(level.getBlockEntity(pos) instanceof SkullBlockEntity skull) profile = skull.getOwnerProfile();
		level.setBlockAndUpdate(pos, newBlock.defaultBlockState()
				.setValue(AbstractSkullCandleBlock.LIGHTING, AbstractLightableBlock.Lighting.NONE)
				.setValue(WallSkullCandleBlock.FACING, level.getBlockState(pos).getValue(WallSkullBlock.FACING)));
		level.setBlockEntity(new SkullCandleBlockEntity(pos,
				newBlock.defaultBlockState()
						.setValue(AbstractSkullCandleBlock.LIGHTING, AbstractLightableBlock.Lighting.NONE)
						.setValue(WallSkullCandleBlock.FACING, level.getBlockState(pos).getValue(WallSkullBlock.FACING)),
				AbstractSkullCandleBlock.candleToCandleColor(candle).getValue(), 1));
		if(level.getBlockEntity(pos) instanceof SkullCandleBlockEntity sc) sc.setOwner(profile);
	}
}
