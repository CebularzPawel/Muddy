package net.cebularz.amethystmore.particle;

import net.cebularz.amethystmore.AmethystMore;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, AmethystMore.MOD_ID);

    public static final RegistryObject<SimpleParticleType> AMETHYST_SHINE_PARTICLE =
            PARTICLES_TYPES.register("amethyst_shine", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLES_TYPES.register(eventBus);
    }
}
