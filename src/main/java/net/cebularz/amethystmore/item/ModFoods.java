package net.cebularz.amethystmore.item;

import net.cebularz.amethystmore.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties AMETHYST_APPLE = new FoodProperties.Builder().nutrition(4).
            saturationMod(1.2f).effect(new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 0), 1.0F).effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 2400, 0), 1.0F).effect(new MobEffectInstance(ModEffects.PURIFICATION.get(), 2400, 3), 1.0F).alwaysEat().build();
    public static final FoodProperties AMETHYST_CARROT = new FoodProperties.Builder().nutrition(5).
            saturationMod(1.0f).effect(new MobEffectInstance(ModEffects.PURIFICATION.get(),300,0),0.5F).alwaysEat().build();
}
