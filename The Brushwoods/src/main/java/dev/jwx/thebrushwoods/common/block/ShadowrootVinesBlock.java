package dev.jwx.thebrushwoods.common.block;

import dev.jwx.thebrushwoods.core.BrushwoodsBlocks;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class ShadowrootVinesBlock extends GrowingPlantHeadBlock {
    public static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 15.0D, 12.0D);

    public ShadowrootVinesBlock(Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false, 0.1D);
    }

    protected int getBlocksToGrowWhenBonemealed(@NotNull RandomSource random) {
        return NetherVines.getBlocksToGrowWhenBonemealed(random);
    }
    @NotNull
    protected Block getBodyBlock() {return BrushwoodsBlocks.SHADOWROOT_VINES_PLANT.get();}

    protected boolean canGrowInto(@NotNull BlockState growState) {
        return NetherVines.isValidGrowthState(growState);
    }
}