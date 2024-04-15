package dev.jwx.thebrushwoods.common.block;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
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

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(new Property[]{FACING});
        pBuilder.add(new Property[]{UP});
    }
    public WillowRootsBlock(Properties pProperties) {
        super(pProperties);
    }
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite()).setValue(UP, pContext.getNearestLookingVerticalDirection()==Direction.UP);
    }
    static {
        FACING = BlockStateProperties.HORIZONTAL_FACING;
        UP = BlockStateProperties.UP;
    }
}
