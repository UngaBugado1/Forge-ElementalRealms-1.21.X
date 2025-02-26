package net.matos.elementalrealms.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class ChiseledTerraiteBlock extends HorizontalDirectionalBlock {

    public static final MapCodec<ChiseledTerraiteBlock> CODEC = simpleCodec(ChiseledTerraiteBlock::new);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public ChiseledTerraiteBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH)); // Set default state
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()); // Place based on player's direction
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING); // Add the FACING property to the state definition
    }
}