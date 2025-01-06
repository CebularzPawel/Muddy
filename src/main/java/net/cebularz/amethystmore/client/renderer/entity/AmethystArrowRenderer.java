package net.cebularz.amethystmore.client.renderer.entity;

import net.cebularz.amethystmore.AmethystMore;
import net.cebularz.amethystmore.world.entity.projectile.AmethystArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AmethystArrowRenderer extends ArrowRenderer<AmethystArrow> {

    @OnlyIn(Dist.CLIENT)
    public AmethystArrowRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(AmethystArrow amethystArrow) {
        return new ResourceLocation(AmethystMore.MOD_ID,"textures/entity/projectiles/amethyst_arrow.png");
    }
}
