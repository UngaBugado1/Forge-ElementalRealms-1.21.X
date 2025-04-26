package net.matos.elementalrealms.block;

import net.matos.elementalrealms.ElementalRealms;
import net.matos.elementalrealms.block.custom.ChiseledTerraiteBlock;
import net.matos.elementalrealms.block.custom.EmberootCropBlock;
import net.matos.elementalrealms.block.custom.ModFlammableRotatedPillarBlock;
import net.matos.elementalrealms.item.ModItems;
import net.matos.elementalrealms.worldgen.tree.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ElementalRealms.MOD_ID);



    public static final RegistryObject<Block> TERRAITE = registerBlock("terraite",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.TUFF)));

              public static final RegistryObject<StairBlock> TERRAITE_STAIRS = registerBlock("terraite_stairs",
                  () -> new StairBlock(ModBlocks.TERRAITE.get().defaultBlockState(), BlockBehaviour.Properties.of()
                          .strength(4f).requiresCorrectToolForDrops().sound(SoundType.TUFF)));

              public static final RegistryObject<SlabBlock> TERRAITE_SLAB = registerBlock("terraite_slab",
                  () -> new SlabBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.TUFF)));

              public static final RegistryObject<WallBlock> TERRAITE_WALL = registerBlock("terraite_wall",
                  () -> new WallBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.TUFF)));


    public static final RegistryObject<Block> POLISHED_TERRAITE = registerBlock("polished_terraite",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.POLISHED_DEEPSLATE)));

              public static final RegistryObject<StairBlock> POLISHED_TERRAITE_STAIRS = registerBlock("polished_terraite_stairs",
                  () -> new StairBlock(ModBlocks.TERRAITE.get().defaultBlockState(), BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.TUFF)));

              public static final RegistryObject<SlabBlock> POLISHED_TERRAITE_SLAB = registerBlock("polished_terraite_slab",
                  () -> new SlabBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.TUFF)));

              public static final RegistryObject<WallBlock> POLISHED_TERRAITE_WALL = registerBlock("polished_terraite_wall",
                  () -> new WallBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.TUFF)));

              public static final RegistryObject<ButtonBlock> POLISHED_TERRAITE_BUTTON = registerBlock("polished_terraite_button",
                  () -> new ButtonBlock(BlockSetType.POLISHED_BLACKSTONE, 5, BlockBehaviour.Properties.of()
                          .strength(3f).requiresCorrectToolForDrops().noCollission()));


    public static final RegistryObject<Block> TERRAITE_BRICKS = registerBlock("terraite_bricks",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.TUFF)));

              public static final RegistryObject<StairBlock> TERRAITE_BRICK_STAIRS = registerBlock("terraite_brick_stairs",
                 () -> new StairBlock(ModBlocks.TERRAITE.get().defaultBlockState(), BlockBehaviour.Properties.of()
                         .strength(4f).requiresCorrectToolForDrops().sound(SoundType.TUFF)));

              public static final RegistryObject<SlabBlock> TERRAITE_BRICK_SLAB = registerBlock("terraite_brick_slab",
                 () -> new SlabBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.TUFF)));

              public static final RegistryObject<WallBlock> TERRAITE_BRICK_WALL = registerBlock("terraite_brick_wall",
                 () -> new WallBlock(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.TUFF)));

    public static final RegistryObject<Block> CHISELED_TERRAITE = registerBlock("chiseled_terraite",
            ()-> new ChiseledTerraiteBlock(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.TUFF)));





    public static final RegistryObject<Block> GEOCLUSTER = registerBlock("geocluster",
            () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));


    public static final RegistryObject<Block> EMBEROOT_CROP =  BLOCKS.register("emberoot_crop",
            () -> new EmberootCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));


    public static final RegistryObject<RotatedPillarBlock> ARCHAIC_LOG = registerBlock("archaic_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> ARCHAIC_WOOD = registerBlock("archaic_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_ARCHAIC_LOG = registerBlock("stripped_archaic_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_ARCHAIC_WOOD = registerBlock("stripped_archaic_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));


    public static final RegistryObject<Block> ARCHAIC_PLANKS = registerBlock("archaic_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });




    public static final RegistryObject<Block> ARCHAIC_LEAVES = registerBlock("archaic_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });


    public static final RegistryObject<Block> ARCHAIC_SAPLING = registerBlock("archaic_sapling",
            () -> new SaplingBlock(ModTreeGrowers.ARCHAIC,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));




private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block)
{
    RegistryObject<T> toReturn = BLOCKS.register(name, block);
    registerBlockItem(name, toReturn);
    return toReturn;
}

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block)
    {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
