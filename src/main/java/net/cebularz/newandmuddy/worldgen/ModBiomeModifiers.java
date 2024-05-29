package net.cebularz.newandmuddy.worldgen;

import net.cebularz.newandmuddy.NewAndMuddy;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_DISK_MUD = registerKey("add_disk_mud");
    public static final ResourceKey<BiomeModifier> ADD_DISK_SINKING_MUD = registerKey("add_sinking_disk_mud");


   /// public static void bootstrap(BootstapContext<BiomeModifier> context) {
       /// var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        ///var biomes = context.lookup(Registries.BIOME);

        //context.register(ADD_DISK_MUD, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                //biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                //HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.DISK_MUD_SWAMP_PLACED_KEY)),
                //GenerationStep.Decoration.TOP_LAYER_MODIFICATION));

    //}

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(NewAndMuddy.MOD_ID, name));
    }
}
