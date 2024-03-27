package dev.jwx.thebrushwoods.common.item;

import dev.jwx.thebrushwoods.world.dimension.ModDimensions;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.Objects;

public class BrushwoodsStaff extends Item {
    public BrushwoodsStaff(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (pLevel.isClientSide)
            return super.use(pLevel, pPlayer, pUsedHand);
        if (pLevel.dimension() == Level.OVERWORLD) {
            pPlayer.teleportTo(Objects.requireNonNull(pPlayer.getServer().getLevel(ModDimensions.BW_KEY)),pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(), EnumSet.noneOf(RelativeMovement.class),pPlayer.getYHeadRot(),pPlayer.getXRot());
        } else {
            pPlayer.teleportTo(Objects.requireNonNull(pPlayer.getServer().getLevel(Level.OVERWORLD)),pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(), EnumSet.noneOf(RelativeMovement.class),pPlayer.getYHeadRot(),pPlayer.getXRot());
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
