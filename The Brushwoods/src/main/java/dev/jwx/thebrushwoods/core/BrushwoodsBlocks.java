package dev.jwx.thebrushwoods.core;

import dev.jwx.thebrushwoods.TheBrushwoods;
import dev.jwx.thebrushwoods.common.block.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class BrushwoodsBlocks {
    public static final ToIntFunction<BlockState> lumenellaLight() {
        return (state) -> {
            return 10;
        };
    };
    public static final ToIntFunction lumenellaLight = value -> 10;
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TheBrushwoods.MODID);

    //BLOCKS REGISTRY

    public static final RegistryObject<Block> SHADOWROOT = registerBlock("shadowroot",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)
                    .strength(1.2f,6f).sound(SoundType.HARD_CROP)));
    public static final RegistryObject<Block> SHADOWROOT_SPROUTS = registerBlock("shadowroot_sprouts",
            () -> new RootsBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)
                    .instabreak().noCollission().offsetType(BlockBehaviour.OffsetType.XZ).sound(SoundType.HARD_CROP)));
    public static final RegistryObject<Block> SHADE_POLLUP = registerBlock("shade_pollup",
            () -> new RootsBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)
                    .instabreak().noCollission().offsetType(BlockBehaviour.OffsetType.XZ).sound(SoundType.HARD_CROP)));
    public static final RegistryObject<Block> SHADOWROOT_VINES = registerBlock("shadowroot_vines",
            () -> new ShadowrootVinesBlock(BlockBehaviour.Properties.copy(Blocks.TWISTING_VINES)
                    .instabreak().noCollission().sound(SoundType.HARD_CROP)));
    public static final RegistryObject<Block> SHADOWROOT_VINES_PLANT = registerBlockNoItem("shadowroot_vines_plant",
            () -> new ShadowrootVinesPlantBlock(BlockBehaviour.Properties.copy(Blocks.TWISTING_VINES)
                    .instabreak().noCollission().sound(SoundType.HARD_CROP)));

    //agaricus

    public static final RegistryObject<Block> AGARICUS = registerBlock("agaricus",
            () -> new RootsBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM)
                    .instabreak().noCollission().offsetType(BlockBehaviour.OffsetType.XZ).sound(SoundType.HARD_CROP)));
    public static final RegistryObject<Block> SMALL_AGARICUS = registerBlock("small_agaricus",
            () -> new RootsBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM)
                    .instabreak().noCollission().sound(SoundType.HARD_CROP)));
    public static final RegistryObject<Block> LARGE_AGARICUS = registerBlock("large_agaricus",
            () -> new DoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM)
                    .instabreak().noCollission().sound(SoundType.HARD_CROP)));

    //Rustcap
    public static final RegistryObject<Block> RUSTCAP = registerBlock("rustcap",
            () -> new RootsBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM)
                    .instabreak().noCollission().offsetType(BlockBehaviour.OffsetType.XZ).sound(SoundType.HARD_CROP)));
    public static final RegistryObject<Block> RUSTCAP_STEM = registerBlock("rustcap_stem",
            () -> new BrushwoodsStemBlock(BlockBehaviour.Properties.copy(Blocks.MUSHROOM_STEM).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> RUSTCAP_CROWN = registerBlock("rustcap_crown",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM_BLOCK).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> RUSTCAP_BLOCK = registerBlock("rustcap_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM_BLOCK).sound(SoundType.WOOD)));

    //ashwood

    public static final RegistryObject<Block> ASHWOOD_PLANKS = registerBlock("ashwood_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ASHWOOD_LOG = registerBlock("ashwood_log",
            () -> new BrushwoodsLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRIPPED_ASHWOOD_LOG = registerBlock("stripped_ashwood_log",
            () -> new BrushwoodsLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ASHWOOD = registerBlock("ashwood",
            () -> new BrushwoodsLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRIPPED_ASHWOOD = registerBlock("stripped_ashwood",
            () -> new BrushwoodsLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.WOOD)));

    // elm
    public static final RegistryObject<Block> ELM_PLANKS = registerBlock("elm_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ELM_LOG = registerBlock("elm_log",
            () -> new BrushwoodsLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRIPPED_ELM_LOG = registerBlock("stripped_elm_log",
            () -> new BrushwoodsLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ELM_WOOD = registerBlock("elm_wood",
            () -> new BrushwoodsLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRIPPED_ELM_WOOD = registerBlock("stripped_elm_wood",
            () -> new BrushwoodsLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ELM_LEAVES = registerBlock("elm_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.2F).noOcclusion().sound(SoundType.AZALEA_LEAVES)));

    //willow
    public static final RegistryObject<Block> WILLOW_PLANKS = registerBlock("willow_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WILLOW_LOG = registerBlock("willow_log",
            () -> new BrushwoodsLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRIPPED_WILLOW_LOG = registerBlock("stripped_willow_log",
            () -> new BrushwoodsLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WILLOW_WOOD = registerBlock("willow_wood",
            () -> new BrushwoodsLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRIPPED_WILLOW_WOOD = registerBlock("stripped_willow_wood",
            () -> new BrushwoodsLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WILLOW_ROOTS = registerBlock("willow_roots",
            () -> new WillowRootsBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(0.3F).noOcclusion().noCollission().sound(SoundType.WOOD)));
    public static final RegistryObject<Block> WILLOW_LEAVES = registerBlock("willow_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.2F).noOcclusion().sound(SoundType.AZALEA_LEAVES)));

    //lumenella
    public static final RegistryObject<Block> LUMENELLA = registerBlock("lumenella",
            () -> new RootsBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM)
                    .instabreak().noCollission().offsetType(BlockBehaviour.OffsetType.XZ).sound(SoundType.HARD_CROP).lightLevel(lumenellaLight())));
    public static final RegistryObject<Block> LUMENELLA_LICHEN = registerBlock("lumenella_lichen",
            () -> new GlowLichenBlock(BlockBehaviour.Properties.copy(Blocks.GLOW_LICHEN)
                    .instabreak().noCollission().noOcclusion().sound(SoundType.GLOW_LICHEN).lightLevel(lumenellaLight())));
    //misc

    public static final RegistryObject<Block> SYLVAN_MOSS = registerBlock("sylvan_moss",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK).sound(SoundType.MOSS)));
    public static final RegistryObject<Block> ROOTED_SYLVAN_MOSS = registerBlock("rooted_sylvan_moss",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK).sound(SoundType.MOSS)));
    public static final RegistryObject<Block> VERDANT_PETALS = registerBlock("verdant_petals",
            () -> new RootsBlock(BlockBehaviour.Properties.copy(Blocks.DANDELION).sound(SoundType.GRASS)));

    //serpentine
    public static final RegistryObject<Block> SERPENTINE = registerBlock("serpentine",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> SERPENTINE_BRICKS = registerBlock("serpentine_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> SERPENTINE_TILES = registerBlock("serpentine_tiles",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> POLISHED_SERPENTINE = registerBlock("polished_serpentine",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> SERPENTINE_TRIM = registerBlock("serpentine_trim",
            () -> new BrushwoodsTrimBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));

    //shale
    public static final RegistryObject<Block> SHALE = registerBlock("shale",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> SHALE_BRICKS = registerBlock("shale_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> SHALE_TILES = registerBlock("shale_tiles",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> POLISHED_SHALE = registerBlock("polished_shale",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> SHALE_TRIM = registerBlock("shale_trim",
        () -> new BrushwoodsTrimBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));

    //silt

    public static final RegistryObject<Block> SILT = registerBlock("silt",
            () -> new SnowLayerBlock(BlockBehaviour.Properties.copy(Blocks.SAND).sound(SoundType.SOUL_SAND)));
    public static final RegistryObject<Block> SILT_BLOCK = registerBlock("silt_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SAND).sound(SoundType.SOUL_SAND)));
    public static final RegistryObject<Block> SILTSTONE = registerBlock("siltstone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> COBBLED_SILTSTONE = registerBlock("cobbled_siltstone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> SILTSTONE_BRICKS = registerBlock("siltstone_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> SILTSTONE_TILES = registerBlock("siltstone_tiles",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> POLISHED_SILTSTONE = registerBlock("polished_siltstone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> SILTSTONE_TRIM = registerBlock("siltstone_trim",
            () -> new BrushwoodsTrimBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));

    //end



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> registerBlockNoItem(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return BrushwoodsItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
