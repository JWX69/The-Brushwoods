package dev.jwx.thebrushwoods.core;

import dev.jwx.thebrushwoods.TheBrushwoods;
import dev.jwx.thebrushwoods.common.block.ElmLeavesBlock;
import dev.jwx.thebrushwoods.common.block.InfectionVinesBlock;
import dev.jwx.thebrushwoods.common.block.InfectionVinesPlantBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class BrushwoodsBlocks {
    public static final ToIntFunction lumenellaLight = new ToIntFunction() {
        @Override
        public int applyAsInt(Object value) {
            return 10;
        }
    };
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TheBrushwoods.MODID);

    //BLOCKS REGISTRY

    public static final RegistryObject<Block> INFECTION = registerBlock("infection",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRASS)
                    .strength(1.2f,6f).sound(SoundType.HARD_CROP)));

    public static final RegistryObject<Block> INFECTION_ROOTS = registerBlock("infection_roots",
            () -> new RootsBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)
                    .instabreak().noCollission().offsetType(BlockBehaviour.OffsetType.XZ).sound(SoundType.HARD_CROP)));

    public static final RegistryObject<Block> INFECTION_VINES = registerBlock("infection_vines",
            () -> new InfectionVinesBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)
                    .instabreak().noCollission().sound(SoundType.HARD_CROP)));

    public static final RegistryObject<Block> INFECTION_VINES_PLANT = registerBlockNoItem("infection_vines_plant",
            () -> new InfectionVinesPlantBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)
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

    //gloomstone

    public static final RegistryObject<Block> GLOOMSTONE = registerBlock("gloomstone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> GLOOMSTONE_BRICKS = registerBlock("gloomstone_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> ROOTED_GLOOMSTONE_BRICKS = registerBlock("rooted_gloomstone_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    //silt

    public static final RegistryObject<Block> SILT = registerBlock("silt",
            () -> new SnowLayerBlock(BlockBehaviour.Properties.copy(Blocks.SOUL_SAND).sound(SoundType.SOUL_SAND)));

    public static final RegistryObject<Block> SILT_BLOCK = registerBlock("silt_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SOUL_SAND).sound(SoundType.SOUL_SAND)));

    //ashwood

    public static final RegistryObject<Block> ASHWOOD_PLANKS = registerBlock("ashwood_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> ASHWOOD_LOG = registerBlock("ashwood_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.WOOD)));

    //elm

    public static final RegistryObject<Block> ELM_PLANKS = registerBlock("elm_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> ELM_LOG = registerBlock("elm_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> ELM_LEAVES = registerBlock("elm_leaves",
            () -> new ElmLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.2F).noOcclusion().sound(SoundType.AZALEA_LEAVES)));

    //lumenella

    public static final RegistryObject<Block> lumenella = registerBlock("lumenella",
            () -> new RootsBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM)
                    .instabreak().noCollission().offsetType(BlockBehaviour.OffsetType.XZ).sound(SoundType.HARD_CROP).lightLevel(lumenellaLight)));
    public static final RegistryObject<Block> lumenella_lichen = registerBlock("lumenella_lichen",
            () -> new GlowLichenBlock(BlockBehaviour.Properties.copy(Blocks.GLOW_LICHEN)
                    .instabreak().noCollission().noOcclusion().sound(SoundType.GLOW_LICHEN).lightLevel(lumenellaLight)));
    //misc

    public static final RegistryObject<Block> SYLVAN_MOSS = registerBlock("sylvan_moss",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK).sound(SoundType.MOSS)));
    public static final RegistryObject<Block> ROOTED_SYLVAN_MOSS = registerBlock("rooted_sylvan_moss",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK).sound(SoundType.MOSS)));
    public static final RegistryObject<Block> VERDANT_PETALS = registerBlock("verdant_petals",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DANDELION).sound(SoundType.GRASS)));

    //serpentine
    public static final RegistryObject<Block> SERPENTINE = registerBlock("serpentine",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> SERPENTINE_BRICKS = registerBlock("serpentine_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> SERPENTINE_TILES = registerBlock("serpentine_tiles",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));

    //shale
    public static final RegistryObject<Block> SHALE = registerBlock("shale",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> SHALE_BRICKS = registerBlock("shale_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> SHALE_TILES = registerBlock("shale_tiles",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE)));
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
