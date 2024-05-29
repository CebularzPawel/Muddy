package net.cebularz.newandmuddy.worldgen;

import net.cebularz.newandmuddy.NewAndMuddy;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> DISK_MUD_SWAMP_PLACED_KEY = registerKey("mud_disk_place");
    public static final ResourceKey<PlacedFeature> DISK_SINKING_MUD_PLACED_KEY = registerKey("sinking_mud_disk_place");

    public static void bootstrap(BootstapContext<PlacedFeature> context){
        HolderGetter<ConfiguredFeature<?,?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(context, DISK_MUD_SWAMP_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DISK_MUD_SWAMP_KEY), new PlacementModifier[]{CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(new Block[]{Blocks.GRASS_BLOCK})), BiomeFilter.biome()});

        PlacementUtils.register(context, DISK_SINKING_MUD_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DISK_SINKING_MUD_KEY), new PlacementModifier[]{CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(new Block[]{Blocks.MUD})), BiomeFilter.biome()});


    }


    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(NewAndMuddy.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
