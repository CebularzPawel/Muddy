package net.cebularz.newandmuddy.datagen.loot;

import net.cebularz.newandmuddy.block.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;


public class ModBlockLootTables extends BlockLootSubProvider {


    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

        this.dropSelf(ModBlocks.MUD_PILLAR.get());
        this.noDrop();
        this.dropSelf(ModBlocks.VERDANT_MUD_LAMP.get());
        this.dropSelf(ModBlocks.OCHRE_MUD_LAMP.get());
        this.dropSelf(ModBlocks.PEARLESCENT_MUD_LAMP.get());
        this.dropSelf(ModBlocks.CHISELED_MUD_BRICKS.get());;
        this.dropSelf(ModBlocks.POLISHED_PACKED_MUD.get());
        this.dropOther(ModBlocks.MUD_FARMLAND.get(), Blocks.MUD);
        this.dropSelf(ModBlocks.DRIED_MUD.get());
        this.dropSelf(ModBlocks.DRIED_MUD_BRICKS.get());
        this.dropSelf(ModBlocks.DRIED_MUD_PILLAR.get());
        this.dropSelf(ModBlocks.POLISHED_DRIED_MUD.get());
        this.dropSelf(ModBlocks.CHISELED_DRIED_MUD_BRICKS.get());
        this.dropSelf(ModBlocks.VERDANT_DRIED_MUD_LAMP.get());
        this.dropSelf(ModBlocks.OCHRE_DRIED_MUD_LAMP.get());
        this.dropSelf(ModBlocks.PEARLESCENT_DRIED_MUD_LAMP.get());
        this.add(ModBlocks.DRIED_MUD_BRICKS_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.DRIED_MUD_BRICKS_SLAB.get()));
        this.dropSelf(ModBlocks.DRIED_MUD_BRICKS_STAIRS.get());
        this.dropSelf(ModBlocks.DRIED_MUD_BRICKS_WALL.get());

        this.add(ModBlocks.POLISHED_PACKED_MUD_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.POLISHED_PACKED_MUD_SLAB.get()));
        this.dropSelf(ModBlocks.POLISHED_PACKED_MUD_STAIRS.get());
        this.dropSelf(ModBlocks.POLISHED_PACKED_MUD_WALL.get());

        this.add(ModBlocks.POLISHED_DRIED_MUD_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.POLISHED_DRIED_MUD_SLAB.get()));
        this.dropSelf(ModBlocks.POLISHED_DRIED_MUD_STAIRS.get());
        this.dropSelf(ModBlocks.POLISHED_DRIED_MUD_WALL.get());

        //this.dropSelf(ModBlocks.CINNAMON_FERN.get());
        //this.add(ModBlocks.POTTED_CINNAMON_FERN.get(), createPotFlowerItemTable(ModBlocks.CINNAMON_FERN.get()));

        this.add(ModBlocks.SINKING_MUD.get(),
                block -> noDrop());
        this.add(ModBlocks.MUD_SPLASH.get(),
                block -> noDrop());
    }


    protected LootTable.Builder createBismuthOreBlocks(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock, (LootPoolEntryContainer.Builder) this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

        @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
