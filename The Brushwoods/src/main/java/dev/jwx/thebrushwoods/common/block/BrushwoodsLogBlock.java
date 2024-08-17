package dev.jwx.thebrushwoods.common.block;

import dev.jwx.thebrushwoods.core.BrushwoodsBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.Nullable;

public class BrushwoodsLogBlock extends RotatedPillarBlock {
    public BrushwoodsLogBlock(Properties pProperties) {
        super(pProperties);
    }

        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

        @Override
        public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if(context.getItemInHand().getItem() instanceof AxeItem) {
            if(state.is(BrushwoodsBlocks.ELM_LOG.get())) {
                return BrushwoodsBlocks.STRIPPED_ELM_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(BrushwoodsBlocks.ELM_WOOD.get())) {
                return BrushwoodsBlocks.STRIPPED_ELM_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(BrushwoodsBlocks.WILLOW_LOG.get())) {
                return BrushwoodsBlocks.STRIPPED_WILLOW_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(BrushwoodsBlocks.WILLOW_WOOD.get())) {
                return BrushwoodsBlocks.STRIPPED_WILLOW_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(BrushwoodsBlocks.ASHWOOD_LOG.get())) {
                return BrushwoodsBlocks.STRIPPED_ASHWOOD_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(BrushwoodsBlocks.ASHWOOD.get())) {
                return BrushwoodsBlocks.STRIPPED_ASHWOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}


