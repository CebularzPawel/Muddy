package net.cebularz.amethystmore;

import com.mojang.logging.LogUtils;
import net.cebularz.amethystmore.block.ModBlocks;

import net.cebularz.amethystmore.client.renderer.entity.AmethystArrowRenderer;
import net.cebularz.amethystmore.item.ModCreativeModTabs;
import net.cebularz.amethystmore.item.ModItems;
import net.cebularz.amethystmore.particle.ModParticles;
import net.cebularz.amethystmore.particle.custom.AmethystShineParticle;
import net.cebularz.amethystmore.world.entity.ModEntityType;
import net.cebularz.amethystmore.world.entity.projectile.AmethystArrow;
import net.cebularz.amethystmore.world.entity.projectile.ThrownAmethystEnderPearl;
import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(AmethystMore.MOD_ID)
public class AmethystMore
{
    public static final String MOD_ID = "amethystmore";
    private static final Logger LOGGER = LogUtils.getLogger();



    public AmethystMore()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(modEventBus);
        ModCreativeModTabs.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntityType.Register(modEventBus);
        ModParticles.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {



        //event.enqueueWork(()->{
            //((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.CINNAMON_FERN.getId(),ModBlocks.POTTED_CINNAMON_FERN);
        //});

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
            EntityRenderers.register(ModEntityType.AMETHYST_ARROW.get(), AmethystArrowRenderer::new);
            EntityRenderers.register(ModEntityType.THROWN_AMETHYST_ENDER_PEARL.get(), ThrownItemRenderer::new);

        }
        @SubscribeEvent
        public static void registerParticleFactories(RegisterParticleProvidersEvent event){
            event.registerSpriteSet(ModParticles.AMETHYST_SHINE_PARTICLE.get(),
                    AmethystShineParticle.Provider::new);
        }
    }
}
