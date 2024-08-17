package dev.jwx.thebrushwoods.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;

public class WillowRootsBlock extends Block {
    public static final DirectionProperty FACING;
    public static final BooleanProperty UP;
    public static final BooleanProperty BOTTOM = BooleanProperty.create("bottom");
    public static final BooleanProperty TOP = BooleanProperty.create("top");

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, UP, TOP, BOTTOM);
    }

    public WillowRootsBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(UP, false)
                .setValue(TOP, true)
                .setValue(BOTTOM, true));
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos pos = pContext.getClickedPos();
        LevelAccessor level = pContext.getLevel();
        BlockState above = level.getBlockState(pos.above());
        BlockState below = level.getBlockState(pos.below());

     return this.defaultBlockState()
                .setValue(FACING, pContext.getHorizontalDirection().getOpposite())
            .setValue(UP, pContext.getNearestLookingVerticalDirection() == Direction.UP)
            .setValue(TOP, !above.is(this))
            .setValue(BOTTOM, !below.is(this));
}

    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        if (pDirection == Direction.UP) {
            return pState.setValue(TOP, !pNeighborState.is(this));
        } else if (pDirection == Direction.DOWN) {
            return pState.setValue(BOTTOM, !pNeighborState.is(this));
        }
        return pState;
    }

    static {
        FACING = BlockStateProperties.HORIZONTAL_FACING;
        UP = BlockStateProperties.UP;
    }
}
