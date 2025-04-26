package net.cebularz.amethystmore.datagen.loot;

import net.cebularz.amethystmore.block.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;


public class ModBlockLootTables extends BlockLootSubProvider {


    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

        this.dropSelf(ModBlocks.AMETHYST_BRICKS.get());
        this.add(ModBlocks.AMETHYST_BRICKS_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.AMETHYST_BRICKS_SLAB.get()));
        this.dropSelf(ModBlocks.AMETHYST_BRICKS_STAIRS.get());
        this.dropSelf(ModBlocks.AMETHYST_BRICKS_WALL.get());


        this.dropSelf(ModBlocks.POLISHED_AMETHYST.get());
        this.add(ModBlocks.POLISHED_AMETHYST_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.POLISHED_AMETHYST_SLAB.get()));
        this.dropSelf(ModBlocks.POLISHED_AMETHYST_STAIRS.get());
        this.dropSelf(ModBlocks.POLISHED_AMETHYST_WALL.get());

        this.dropSelf(ModBlocks.CHISELED_AMETHYST_BRICKS.get());
        this.dropSelf(ModBlocks.AMETHYST_PILLAR.get());
        this.dropSelf(ModBlocks.AMETHYST_BALL.get());
        this.dropSelf(ModBlocks.AMETHYST_SPIKE.get());

        this.dropSelf(ModBlocks.AMETHYST_MOSAIC.get());
        this.dropSelf(ModBlocks.AMETHYST_SPIRAL.get());

        this.add(ModBlocks.AMETHYST_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.AMETHYST_SLAB.get()));
        this.dropSelf(ModBlocks.AMETHYST_STAIRS.get());
        this.dropSelf(ModBlocks.AMETHYST_WALL.get());
    }




        @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
