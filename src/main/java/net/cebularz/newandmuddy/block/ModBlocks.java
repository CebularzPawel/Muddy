package net.cebularz.newandmuddy.block;

import net.cebularz.newandmuddy.block.custom.*;
import net.cebularz.newandmuddy.NewAndMuddy;
import net.cebularz.newandmuddy.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS=
            DeferredRegister.create(ForgeRegistries.BLOCKS, NewAndMuddy.MOD_ID);

    public static final RegistryObject<Block> MUD_PILLAR = registerBlock("mud_pillar",
            ()-> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> MUD_FARMLAND = registerBlock("mud_farmland",
            ()-> new MudFarmLand(BlockBehaviour.Properties.copy(Blocks.FARMLAND).sound(SoundType.MUD).mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> SINKING_MUD = registerBlock("sinking_mud",
            ()-> new SinkingMudBlock(BlockBehaviour.Properties.copy(Blocks.MUD)));
    public static final RegistryObject<Block> DRIED_MUD = registerBlock("dried_mud",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.SANDSTONE).strength(0.5f)));
    public static final RegistryObject<Block> DRIED_MUD_BRICKS = registerBlock("dried_mud_bricks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS)));
    public static final RegistryObject<Block> DRIED_MUD_BRICKS_STAIRS = registerBlock("dried_mud_bricks_stairs",
            ()-> new StairBlock(()-> ModBlocks.DRIED_MUD_BRICKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS)));
    public static final RegistryObject<Block> DRIED_MUD_BRICKS_SLAB = registerBlock("dried_mud_bricks_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS)));
    public static final RegistryObject<Block> DRIED_MUD_BRICKS_WALL = registerBlock("dried_mud_bricks_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS)));
    public static final RegistryObject<Block> DRIED_MUD_PILLAR = registerBlock("dried_mud_pillar",
            ()-> new RotatedPillarBlock(BlockBehaviour.Properties.copy(ModBlocks.MUD_PILLAR.get())));
    public static final RegistryObject<Block> POLISHED_DRIED_MUD = registerBlock("polished_dried_mud",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MUD_SPLASH = registerBlock("mud_splash",
            ()-> new MudSplashBlock(BlockBehaviour.Properties.copy(Blocks.MUD).mapColor(MapColor.GLOW_LICHEN).replaceable().noCollission().strength(0.2F)));
    public static final RegistryObject<Block> POLISHED_PACKED_MUD = registerBlock("polished_packed_mud",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CHISELED_DRIED_MUD_BRICKS = registerBlock("chiseled_dried_mud_bricks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VERDANT_MUD_LAMP = registerBlock("verdant_mud_lamp",
            ()-> new MudLampBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).lightLevel(state -> state.getValue(MudLampBlock.LIT) ? 15 : 0).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> OCHRE_MUD_LAMP = registerBlock("ochre_mud_lamp",
            ()-> new MudLampBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).lightLevel(state -> state.getValue(MudLampBlock.LIT) ? 15 : 0).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PEARLESCENT_MUD_LAMP = registerBlock("pearlescent_mud_lamp",
            ()-> new MudLampBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).lightLevel(state -> state.getValue(MudLampBlock.LIT) ? 15 : 0).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> VERDANT_DRIED_MUD_LAMP = registerBlock("verdant_dried_mud_lamp",
            ()-> new MudLampBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).lightLevel(state -> state.getValue(MudLampBlock.LIT) ? 15 : 0).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> OCHRE_DRIED_MUD_LAMP = registerBlock("ochre_dried_mud_lamp",
            ()-> new MudLampBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).lightLevel(state -> state.getValue(MudLampBlock.LIT) ? 15 : 0).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PEARLESCENT_DRIED_MUD_LAMP = registerBlock("pearlescent_dried_mud_lamp",
            ()-> new MudLampBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).lightLevel(state -> state.getValue(MudLampBlock.LIT) ? 15 : 0).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CHISELED_MUD_BRICKS = registerBlock("chiseled_mud_bricks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).requiresCorrectToolForDrops()));



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
