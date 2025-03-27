package net.cebularz.amethystmore.effect;

import net.cebularz.amethystmore.AmethystMore;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, AmethystMore.MOD_ID);

    public static final RegistryObject<MobEffect> PURIFICATION = MOB_EFFECTS.register("purification",
            () -> new PurificationEffect(MobEffectCategory.BENEFICIAL,14194136));

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }

}
