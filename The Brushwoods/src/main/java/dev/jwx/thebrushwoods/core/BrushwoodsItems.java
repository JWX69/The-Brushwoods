package dev.jwx.thebrushwoods.core;

import dev.jwx.thebrushwoods.TheBrushwoods;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BrushwoodsItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TheBrushwoods.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
