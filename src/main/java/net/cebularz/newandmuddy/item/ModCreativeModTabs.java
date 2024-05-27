package net.cebularz.newandmuddy.item;


import net.cebularz.newandmuddy.NewAndMuddy;
import net.cebularz.newandmuddy.block.ModBlocks;
import net.cebularz.newandmuddy.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NewAndMuddy.MOD_ID);
    public static final RegistryObject<CreativeModeTab> CUSTOM_TAB = CREATIVE_MODE_TABS.register("custom_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.CHISELED_MUD_BRICKS.get()))
                    .title(Component.translatable("creativetab.custom_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.MUD_BALL.get());
                        pOutput.accept(ModItems.PACKED_MUD_BALL.get());
                        pOutput.accept(ModItems.SINKING_MUD_BUCKET.get());
                        pOutput.accept(ModBlocks.POLISHED_PACKED_MUD.get());
                        pOutput.accept(ModBlocks.MUD_PILLAR.get());
                        pOutput.accept(ModBlocks.CHISELED_MUD_BRICKS.get());
                        pOutput.accept(ModBlocks.OCHRE_MUD_LAMP.get());
                        pOutput.accept(ModBlocks.PEARLESCENT_MUD_LAMP.get());
                        pOutput.accept(ModBlocks.VERDANT_MUD_LAMP.get());
                        pOutput.accept(ModBlocks.DRIED_MUD.get());
                        pOutput.accept(ModBlocks.DRIED_MUD_BRICKS.get());
                        pOutput.accept(ModBlocks.DRIED_MUD_BRICKS_STAIRS.get());
                        pOutput.accept(ModBlocks.DRIED_MUD_BRICKS_SLAB.get());
                        pOutput.accept(ModBlocks.DRIED_MUD_BRICKS_WALL.get());
                        pOutput.accept(ModBlocks.POLISHED_DRIED_MUD.get());
                        pOutput.accept(ModBlocks.DRIED_MUD_PILLAR.get());
                        pOutput.accept(ModBlocks.CHISELED_DRIED_MUD_BRICKS.get());
                        pOutput.accept(ModBlocks.OCHRE_DRIED_MUD_LAMP.get());
                        pOutput.accept(ModBlocks.PEARLESCENT_DRIED_MUD_LAMP.get());
                        pOutput.accept(ModBlocks.VERDANT_DRIED_MUD_LAMP.get());

                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
