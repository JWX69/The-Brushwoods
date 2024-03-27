package dev.jwx.thebrushwoods.core;

import dev.jwx.thebrushwoods.TheBrushwoods;
import dev.jwx.thebrushwoods.common.item.BrushwoodsStaff;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BrushwoodsItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TheBrushwoods.MODID);

    public static final RegistryObject<BrushwoodsStaff> BRUSHWOODS_STAFF = ITEMS.register("brushwoods_staff", () -> new BrushwoodsStaff(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
