package net.cebularz.newandmuddy.datagen;


import net.cebularz.newandmuddy.NewAndMuddy;
import net.cebularz.newandmuddy.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, NewAndMuddy.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(BlockTags.MAINTAINS_FARMLAND)
                .add(ModBlocks.MUD_FARMLAND.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.MUD_PILLAR.get(),
                        ModBlocks.POLISHED_PACKED_MUD.get(),
                        ModBlocks.VERDANT_MUD_LAMP.get(),
                        ModBlocks.OCHRE_MUD_LAMP.get(),
                        ModBlocks.PEARLESCENT_MUD_LAMP.get(),
                        ModBlocks.CHISELED_MUD_BRICKS.get(),
                        ModBlocks.DRIED_MUD.get(),
                        ModBlocks.DRIED_MUD_BRICKS.get(),
                        ModBlocks.DRIED_MUD_PILLAR.get(),
                        ModBlocks.POLISHED_DRIED_MUD.get(),
                        ModBlocks.CHISELED_DRIED_MUD_BRICKS.get()
                );




        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.MUD_SPLASH.get());

        this.tag(BlockTags.WALLS)
                .add(ModBlocks.DRIED_MUD_BRICKS_WALL.get());
    }

}
