package net.matos.elementalrealms.block.custom;

import net.matos.elementalrealms.entity.ModEntities;
import net.minecraft.world.level.block.Block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEvent.Context;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import com.mojang.serialization.MapCodec;

public class TectoraxEggBlock extends Block {
    public static final MapCodec<TectoraxEggBlock> CODEC = simpleCodec(TectoraxEggBlock::new);
    public static final int MAX_HATCH_LEVEL = 2;
    public static final IntegerProperty HATCH = BlockStateProperties.HATCH;
    private static final int REGULAR_HATCH_TIME_TICKS = 24000;
    private static final int BOOSTED_HATCH_TIME_TICKS = 12000;
    private static final int RANDOM_HATCH_OFFSET_TICKS = 300;
    private static final VoxelShape SHAPE = Block.box(1.0, 0.0, 2.0, 15.0, 16.0, 14.0); // same as Sniffer Egg

    public TectoraxEggBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HATCH, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HATCH);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public int getHatchLevel(BlockState state) {
        return state.getValue(HATCH);
    }

    private boolean isReadyToHatch(BlockState state) {
        return getHatchLevel(state) == MAX_HATCH_LEVEL;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!isReadyToHatch(state)) {
            level.playSound(null, pos, SoundEvents.SNIFFER_EGG_CRACK, SoundSource.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
            level.setBlock(pos, state.setValue(HATCH, getHatchLevel(state) + 1), 2);
        } else {
            level.playSound(null, pos, SoundEvents.SNIFFER_EGG_HATCH, SoundSource.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
            level.destroyBlock(pos, false);

            var tectorax = ModEntities.TECTORAX.get().create(level);
            if (tectorax != null) {
                tectorax.setBaby(true);
                Vec3 center = pos.getCenter();
                tectorax.moveTo(center.x(), center.y(), center.z(), Mth.wrapDegrees(level.random.nextFloat() * 360.0F), 0.0F);
                level.addFreshEntity(tectorax);
            }
        }
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        boolean boosted = hatchBoost(level, pos);
        if (!level.isClientSide() && boosted) {
            level.levelEvent(3009, pos, 0); // hatch boost particles
        }

        int baseTicks = boosted ? BOOSTED_HATCH_TIME_TICKS : REGULAR_HATCH_TIME_TICKS;
        int delay = baseTicks / 3 + level.random.nextInt(RANDOM_HATCH_OFFSET_TICKS);
        level.gameEvent(GameEvent.BLOCK_PLACE, pos, Context.of(state));
        level.scheduleTick(pos, this, delay);
    }

    @Override
    public boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }

    public static boolean hatchBoost(BlockGetter level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(BlockTags.SNIFFER_EGG_HATCH_BOOST);
    }

    @Override
    public MapCodec<TectoraxEggBlock> codec() {
        return CODEC;
    }
}
