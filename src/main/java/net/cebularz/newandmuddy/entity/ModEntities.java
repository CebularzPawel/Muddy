package net.cebularz.newandmuddy.entity;

import net.cebularz.newandmuddy.entity.custom.MudBallProjectile;
import net.cebularz.newandmuddy.NewAndMuddy;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, NewAndMuddy.MOD_ID);



    public static final RegistryObject<EntityType<MudBallProjectile>> MUD_BALL_PROJECTILE_ENTITY =
            ENTITY_TYPES.register("mud_ball_projectile_entity", () -> EntityType.Builder.<MudBallProjectile>of(MudBallProjectile::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("mud_ball_projectile_entity"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}