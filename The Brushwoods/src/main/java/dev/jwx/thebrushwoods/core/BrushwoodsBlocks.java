package dev.jwx.thebrushwoods.core;

import dev.jwx.thebrushwoods.common.block.ElmLeavesBlock;
import dev.jwx.thebrushwoods.common.block.InfectionVinesBlock;
import dev.jwx.thebrushwoods.common.block.InfectionVinesPlantBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BrushwoodsBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TheBrushwoods.MODID);

    //BLOCKS REGISTRY

    public static final RegistryObject<Block> INFECTION = registerBlock("infection",
            () -> new Block(BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.COLOR_PURPLE)
                    .strength(1.2f,6f)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> INFECTION_ROOTS = registerBlock("infection_roots",
            () -> new RootsBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_PURPLE)
                    .instabreak().noCollission().offsetType(BlockBehaviour.OffsetType.XZ)),CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> INFECTION_VINES = registerBlock("infection_vines",
            () -> new InfectionVinesBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_PURPLE)
                    .instabreak().noCollission()),CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> INFECTION_VINES_PLANT = registerBlockNoItem("infection_vines_plant",
            () -> new InfectionVinesPlantBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_PURPLE)
                    .instabreak().noCollission()));

    //agaricus

    public static final RegistryObject<Block> AGARICUS = registerBlock("agaricus",
            () -> new RootsBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.SAND)
                    .instabreak().noCollission().offsetType(BlockBehaviour.OffsetType.XZ)),CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> SMALL_AGARICUS = registerBlock("small_agaricus",
            () -> new RootsBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.SAND)
                    .instabreak().noCollission()),CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> LARGE_AGARICUS = registerBlock("large_agaricus",
            () -> new DoublePlantBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.SAND)
                    .instabreak().noCollission()),CreativeModeTab.TAB_DECORATIONS);

    //gloomstone

    public static final RegistryObject<Block> GLOOMSTONE = registerBlock("gloomstone",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_BLUE)),CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> GLOOMSTONE_BRICKS = registerBlock("gloomstone_bricks",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_BLUE)),CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> ROOTED_GLOOMSTONE_BRICKS = registerBlock("rooted_gloomstone_bricks",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_BLUE)),CreativeModeTab.TAB_BUILDING_BLOCKS);

    //silt

    public static final RegistryObject<Block> SILT = registerBlock("silt",
            () -> new SnowLayerBlock(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.COLOR_GRAY)),CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> SILT_BLOCK = registerBlock("silt_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.COLOR_GRAY)),CreativeModeTab.TAB_BUILDING_BLOCKS);

    //ashwood

    public static final RegistryObject<Block> ASHWOOD_PLANKS = registerBlock("ashwood_planks",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)),CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> ASHWOOD_LOG = registerBlock("ashwood_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)),CreativeModeTab.TAB_BUILDING_BLOCKS);

    //elm

    public static final RegistryObject<Block> ELM_PLANKS = registerBlock("elm_planks",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)),CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> ELM_LOG = registerBlock("elm_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)),CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> ELM_LEAVES = registerBlock("elm_leaves",
            () -> new ElmLeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.COLOR_GREEN).strength(0.2F).noOcclusion()),CreativeModeTab.TAB_DECORATIONS);

    //lumenella

    public static final RegistryObject<Block> lumenella = registerBlock("lumenella",
            () -> new RootsBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_YELLOW)
                    .instabreak().noCollission().offsetType(BlockBehaviour.OffsetType.XZ)),CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> lumenella_lichen = registerBlock("lumenella_lichen",
            () -> new GlowLichenBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_YELLOW)
                    .instabreak().noCollission().noOcclusion()),CreativeModeTab.TAB_DECORATIONS);
    //misc

    public static final RegistryObject<Block> SYLVAN_MOSS = registerBlock("sylvan_moss",
            () -> new Block(BlockBehaviour.Properties.of(Material.MOSS, MaterialColor.COLOR_GREEN)),CreativeModeTab.TAB_BUILDING_BLOCKS);

    //end

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> registerBlockNoItem(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return BrushwoodsItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }



    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
