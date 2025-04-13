package net.cebularz.amethystmore.datagen;


import net.cebularz.amethystmore.AmethystMore;
import net.cebularz.amethystmore.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AmethystMore.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {



        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.AMETHYST_BRICKS.get(),
                        ModBlocks.CHISELED_AMETHYST_BRICKS.get(),
                        ModBlocks.AMETHYST_BRICKS_SLAB.get(),
                        ModBlocks.AMETHYST_BRICKS_WALL.get(),
                        ModBlocks.AMETHYST_BRICKS_STAIRS.get(),
                        ModBlocks.POLISHED_AMETHYST.get(),
                        ModBlocks.POLISHED_AMETHYST_STAIRS.get(),
                        ModBlocks.POLISHED_AMETHYST_SLAB.get(),
                        ModBlocks.POLISHED_AMETHYST_WALL.get(),
                        ModBlocks.AMETHYST_PILLAR.get(),
                        ModBlocks.AMETHYST_BALL.get(),
                        ModBlocks.AMETHYST_SPIKE.get(),
                        ModBlocks.AMETHYST_MOSAIC.get(),
                        ModBlocks.AMETHYST_SPIRAL.get()
                );


        this.tag(BlockTags.WALLS)
                .add(ModBlocks.AMETHYST_BRICKS_WALL.get(),
                        ModBlocks.POLISHED_AMETHYST_WALL.get()
                );


    }

}
