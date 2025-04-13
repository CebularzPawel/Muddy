package net.cebularz.amethystmore.block;

import net.cebularz.amethystmore.AmethystMore;
import net.cebularz.amethystmore.block.custom.AmethystBallBlock;
import net.cebularz.amethystmore.block.custom.AmethystMosaicBlock;
import net.cebularz.amethystmore.block.custom.AmethystSpikeBlock;
import net.cebularz.amethystmore.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS=
            DeferredRegister.create(ForgeRegistries.BLOCKS, AmethystMore.MOD_ID);


    public static final RegistryObject<Block> AMETHYST_BRICKS = registerBlock("amethyst_bricks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)));

    public static final RegistryObject<Block> AMETHYST_BRICKS_STAIRS = registerBlock("amethyst_bricks_stairs",
            ()-> new StairBlock(()-> ModBlocks.AMETHYST_BRICKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)));
    public static final RegistryObject<Block> AMETHYST_BRICKS_SLAB = registerBlock("amethyst_bricks_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)));
    public static final RegistryObject<Block> AMETHYST_BRICKS_WALL = registerBlock("amethyst_bricks_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)));


    public static final RegistryObject<Block>  POLISHED_AMETHYST = registerBlock("polished_amethyst",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)));
    public static final RegistryObject<Block>  AMETHYST_SPIRAL = registerBlock("amethyst_spiral",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)));
    public static final RegistryObject<Block> POLISHED_AMETHYST_STAIRS = registerBlock("polished_amethyst_stairs",
            ()-> new StairBlock(()-> ModBlocks.POLISHED_AMETHYST.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)));
    public static final RegistryObject<Block> POLISHED_AMETHYST_SLAB = registerBlock("polished_amethyst_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)));
    public static final RegistryObject<Block> POLISHED_AMETHYST_WALL = registerBlock("polished_amethyst_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)));



    public static final RegistryObject<Block> CHISELED_AMETHYST_BRICKS = registerBlock("chiseled_amethyst_bricks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)));

    public static final RegistryObject<Block> AMETHYST_PILLAR = registerBlock("amethyst_pillar",
            ()-> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AMETHYST_MOSAIC = registerBlock("amethyst_mosaic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AMETHYST_BALL = registerBlock("amethyst_ball",
            ()-> new AmethystBallBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).noOcclusion().pushReaction(PushReaction.DESTROY).lightLevel((p_50870_) -> {
                return 10;
            })));


    public static final RegistryObject<Block> AMETHYST_SPIKE = registerBlock("amethyst_spike",
            ()-> new AmethystSpikeBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).forceSolidOn().noOcclusion().randomTicks().strength(1.0F, 3.0F).dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY).lightLevel((p_50870_) -> {
                return 5;
            })));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
       RegistryObject<T> toReturn = BLOCKS.register(name,block);
       registerBlockitem(name,toReturn);
       return toReturn;
   }
    private static <T extends Block> RegistryObject<Item> registerBlockitem(String name,RegistryObject<T>block){
        return ModItems.ITEMS.register(name,()->new BlockItem(block.get(),new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
