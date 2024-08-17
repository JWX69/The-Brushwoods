package dev.jwx.thebrushwoods.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class BrushwoodsStemBlock extends RotatedPillarBlock {
    public static final BooleanProperty TOP = BooleanProperty.create("top");

    public BrushwoodsStemBlock(Properties pProperties) {
        super(pProperties);
    }
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return (BlockState)this.defaultBlockState().setValue(AXIS, pContext.getClickedFace().getAxis()).setValue(TOP,!pContext.getLevel().getBlockState(pContext.getClickedPos().above()).is(this));
    }
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(TOP);
    }
    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        if (pDirection != Direction.UP)
            return pState;
        return pState.setValue(TOP, !pNeighborState.is(this));
    }
}
