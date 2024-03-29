package dev.jwx.thebrushwoods.core;

import dev.jwx.thebrushwoods.core.features.generators.FancyCanopyTreeGenerator;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BrushwoodEvents {
    
    @SubscribeEvent
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        if (event.getEntity().isCrouching() && event.getLevel() instanceof ServerLevelAccessor serverLevel) {
            System.out.println("h");
            FancyCanopyTreeGenerator.create(serverLevel, toBlockPos(event.getEntity().getPosition(0).add(10, 0, 10)));
        }
    }
    private static BlockPos toBlockPos(Vec3 vec3) {
        return new BlockPos((int) vec3.x, (int) vec3.y, (int) vec3.z);
    }
    
}
