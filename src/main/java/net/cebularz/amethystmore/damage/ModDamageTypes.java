package net.cebularz.amethystmore.damage;

import net.cebularz.amethystmore.AmethystMore;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class ModDamageTypes {

    public static final ResourceKey<DamageType> AMETHYST_SPIKE_DAMAGE = ResourceKey.create(
            Registries.DAMAGE_TYPE, new ResourceLocation(AmethystMore.MOD_ID, "amethyst_spike")
    );

    public static Holder<DamageType> amethystSpikeDamageType(Level level) {
        return level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(AMETHYST_SPIKE_DAMAGE);
    }

    public static DamageSource amethystSpikeDamage(Level level) {
        return new DamageSource(amethystSpikeDamageType(level), null, null, null);
    }
}