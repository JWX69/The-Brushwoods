package dev.jwx.thebrushwoods.core.features.generators;

import dev.jwx.thebrushwoods.core.BrushwoodsBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class FancyCanopyTreeGenerator {

//    class GeneratorData {
//
//    }
    
    private static final List<Direction> horizontalDirections = List.of(Direction.values())
        .stream().filter(direction -> direction.getAxis() != Direction.Axis.Y).toList();
    
    public static void create(ServerLevelAccessor level, BlockPos origin) {
    
        for (int x = 0; x <= 1; x++) for (int z = 0; z <= 1; z++) for (int y = 0; y <= 12; y++) {
            level.setBlock(origin.offset(x, y, z), BrushwoodsBlocks.ELM_LOG.get().defaultBlockState(), 19);
        }
        
        int trunkEmitCount = (int) (3 + Math.random());
        List<Direction> selectedDirections = new ArrayList<>();
        List<Direction> unselectedDirections = new ArrayList<>(horizontalDirections);
        for (int i = 0; i < trunkEmitCount; i++) {
            selectedDirections.add(
                unselectedDirections.remove(randomIntInRange(0, unselectedDirections.size()))
            );
        }
        
        for (Direction direction : selectedDirections) {
            Vec3 bias = Vec3.atLowerCornerOf(direction.getNormal().multiply(2));
    
            //Create a bias towards directions that don't have any trunks on them
            List<Direction> neighboringUnselectedDirections = unselectedDirections.stream()
                .filter(unselectedDirection ->
                    unselectedDirection == direction.getClockWise()
                    || unselectedDirection == direction.getCounterClockWise()
                ).toList();
            if (neighboringUnselectedDirections.size() != 0)
                bias = bias.add(Vec3.atCenterOf(neighboringUnselectedDirections.get(0).getNormal().multiply(2)));
            
            emmitTrunk(level, getTrunkOrigin(direction, origin), bias, direction.getOpposite());
        }
        
    }
    
    public static void emmitTrunk(ServerLevelAccessor level, BlockPos origin, Vec3 bias, Direction direction) {
        List<Direction> permittedDirections = new ArrayList<>(horizontalDirections);
        permittedDirections.remove(direction);
        
        FancyCanopyTreeGenerator.createSurfaceRoot(
            level,
            origin,
            bias,
            (float) (6 + randomInRange(-2, 2)),
            permittedDirections
        );
    
    }
    
    public static BlockPos getTrunkOrigin(Direction direction, BlockPos origin) {
        Vec3i normal = direction.getNormal();
        return new BlockPos(
            origin.getX() + (normal.getX() == 0 ? 0 : (normal.getX() == 1 ? 2 : -1)),
            origin.getY(),
            origin.getZ() + (normal.getZ() == 0 ? 0 : (normal.getZ() == 1 ? 2 : -1))
        );
    }
    
    /**
     * Create the roots that go at the base of the tree
     * @param bias Vec3 which is actually a Vec2 in disguise but the class doesn't exist and this is easier
     * */
    public static void createSurfaceRoot(ServerLevelAccessor worldGenLevel, BlockPos origin, Vec3 bias, float strength, List<Direction> permittedDirections) {
        Vec3 targetPos = bias.add(randomInRange(-1, 1), 0, randomInRange(-1, 1));
        
        Direction direction = pickDirection(permittedDirections, targetPos);
        
        placeSurfaceRoot(worldGenLevel, origin, strength);
        
        origin = origin.relative(direction);
        strength = (float) (strength-(1 + Math.random()*2f));
        
        if (strength < 1)
            return;
        
        //Make the bias lose its strength over time
        bias = targetPos.scale(0.5);//Value fresh from my ass, add a config
        
        int subRootCount = getSubRootCount(strength);
        for (int i = 0; i < subRootCount; i++) {
            createSurfaceRoot(
                worldGenLevel,
                origin,
                bias.add(randomInRange(-2, 2), 0, randomInRange(-2, 2)),
                (strength + (strength / subRootCount)) / 2f,
                new ArrayList<>(permittedDirections)
            );
        }
    }
    private static void placeSurfaceRoot(ServerLevelAccessor level, BlockPos origin, float strength) {
        for (int i = getSurfaceRootDepth(strength); i < strength + ((getSurfaceRootDepth(strength) + 2) / 2f); i++) {
            level.setBlock(origin.offset(0, i, 0), BrushwoodsBlocks.ELM_LOG.get().defaultBlockState(), 18);
        }
    }
    private static int getSurfaceRootDepth(float strength) {
        return (int) ((strength) - 3);
    }
    
    private static Direction pickDirection(List<Direction> permittedDirections, Vec3 bias) {
        List<Direction> validDirections = new ArrayList<>(permittedDirections);
        
        if (permittedDirections.size() == 0) {
            
            new IllegalArgumentException("Brushwood tree generator error, Received no valid directions for permittedDirections!").printStackTrace();
            return Direction.NORTH;
            
        } else if (permittedDirections.size() == 1) {
            
            return permittedDirections.get(0);
            
        } else if (permittedDirections.size() > 2) {
            validDirections.removeIf(axis ->
                axis.getAxisDirection().getStep() !=
                    ((axis.getAxis() == Direction.Axis.X ? bias.x : bias.z) >= 0 ? 1 : -1)
            );
        }
        
        double width = Math.abs(bias.x) + Math.abs(bias.z);
        boolean wasXAxisChosen = randomInRange(0, width) < Math.abs(bias.x);
        
        return validDirections.stream().filter(direction ->
            (wasXAxisChosen && direction.getAxis() == Direction.Axis.X)
            || (!wasXAxisChosen && direction.getAxis() != Direction.Axis.X)
        ).findFirst().orElse(Direction.NORTH);
    }
    
    private static int getSubRootCount(float strength) {
        return 1 + (strength > 3 ? (int) (Math.random()*2f) : 0);
    }
    
    private static double randomInRange(double min, double max) {
        return (Math.random() * (max-min)) + min;
    }
    
    private static int randomIntInRange(int min, int max) {
        return (int) ((Math.random() * (max-min)) + min);
    }
    
}
