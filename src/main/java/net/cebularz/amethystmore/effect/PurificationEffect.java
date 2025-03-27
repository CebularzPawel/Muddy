package net.cebularz.amethystmore.effect;

import net.cebularz.amethystmore.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class PurificationEffect extends MobEffect {
    protected PurificationEffect(MobEffectCategory pCategory, int pColor) {

        super(pCategory, pColor);
    }
    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        int heal=0;
        for (MobEffectInstance effect : pLivingEntity.getActiveEffects()) {
            if (effect.getEffect().getCategory() == MobEffectCategory.HARMFUL){
                pLivingEntity.removeEffect(effect.getEffect());
                int amplifierHarm=effect.getAmplifier();

                heal+=(amplifierHarm+1)*4;
                pLivingEntity.heal(heal);
                Level level = pLivingEntity.level();
                spawnParticles(level,pLivingEntity);

                level.playSound((Player) null, pLivingEntity.blockPosition(), SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 4.0F, 1.0F);

                if (pAmplifier>0) {
                    int duration = pLivingEntity.getEffect(this).getDuration();
                    pLivingEntity.removeEffect(this);
                    pLivingEntity.addEffect(new MobEffectInstance(this,duration,pAmplifier-1));

                }else{
                    pLivingEntity.removeEffect(this);
                }
            }
        }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean isBeneficial() {
        return true;

    }
    private void spawnParticles(LevelAccessor world, LivingEntity entity) {
        for (int i = 0; i < 10; i++) {
            double d0 = (double)entity.getX() + world.getRandom().nextDouble();
            double d1 = (double)entity.getY() + world.getRandom().nextDouble();
            double d2 = (double)entity.getZ() + world.getRandom().nextDouble();

            world.addParticle(ModParticles.AMETHYST_SHINE_PARTICLE.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }
    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
