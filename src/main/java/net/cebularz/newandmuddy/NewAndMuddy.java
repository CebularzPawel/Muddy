package net.cebularz.newandmuddy;

import com.mojang.logging.LogUtils;
import net.cebularz.newandmuddy.block.ModBlocks;
import net.cebularz.newandmuddy.effect.ModEffects;
import net.cebularz.newandmuddy.entity.ModEntities;
import net.cebularz.newandmuddy.entity.custom.MudBallProjectile;
import net.cebularz.newandmuddy.item.ModCreativeModTabs;
import net.cebularz.newandmuddy.item.ModItems;
import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

@Mod(NewAndMuddy.MOD_ID)
public class NewAndMuddy
{
    public static final String MOD_ID = "newandmuddy";
    private static final Logger LOGGER = LogUtils.getLogger();



    public NewAndMuddy()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(modEventBus);
        ModCreativeModTabs.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register((modEventBus));
        ModEffects.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HELLO FROM COMMON SETUP");

        DispenserBlock.registerBehavior(ModItems.MUD_BALL.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level p_123476_, Position p_123477_, ItemStack p_123478_) {
                return (Projectile) Util.make(new MudBallProjectile(p_123476_, p_123477_.x(), p_123477_.y(), p_123477_.z()), (p_123474_) -> {
                    p_123474_.setItem(p_123478_);
                });
            }
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.MUD_BALL_PROJECTILE_ENTITY.get(), ThrownItemRenderer::new);

        }
    }
}
