package net.cebularz.amethystmore.world.entity.projectile;

import net.cebularz.amethystmore.item.ModItems;
import net.cebularz.amethystmore.particle.ModParticles;
import net.cebularz.amethystmore.world.entity.ModEntityType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.EntityTeleportEvent;

import javax.annotation.Nullable;

public class ThrownAmethystEnderPearl extends ThrowableItemProjectile {


    public ThrownAmethystEnderPearl(EntityType<? extends ThrownAmethystEnderPearl> pEntityType, Level pLevel) {

        super(pEntityType, pLevel);
        this.setNoGravity(true);
    }

    public ThrownAmethystEnderPearl(Level pLevel,LivingEntity pShooter ) {

        super(ModEntityType.THROWN_AMETHYST_ENDER_PEARL.get(), pShooter, pLevel);
        this.setNoGravity(true);
    }
    @Override
    protected Item getDefaultItem() {
        return ModItems.AMETHYST_ENDER_PEARL.get();
    }
    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);

        for(int i = 0; i < 32; ++i) {
            this.level().addParticle(ModParticles.AMETHYST_SHINE_PARTICLE.get(), this.getX(), this.getY() + this.random.nextDouble() * 2.0, this.getZ(), this.random.nextGaussian(), 0.0, this.random.nextGaussian());
        }

        if (!this.level().isClientSide && !this.isRemoved()) {
            Entity entity = this.getOwner();
            if (entity instanceof ServerPlayer) {
                ServerPlayer serverplayer = (ServerPlayer)entity;
                if (serverplayer.connection.isAcceptingMessages() && serverplayer.level() == this.level() && !serverplayer.isSleeping()) {
                    if (entity.isPassenger()) {
                        serverplayer.dismountTo(this.getX(), this.getY(), this.getZ());
                    } else {
                        entity.teleportTo(this.getX(), this.getY(), this.getZ());
                    }

                    entity.teleportTo(this.getX(), this.getY(), this.getZ());
                    entity.resetFallDistance();
                    ((ServerPlayer) entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 1));
                }
            } else if (entity != null) {
                entity.teleportTo(this.getX(), this.getY(), this.getZ());
                entity.resetFallDistance();
            }

            this.discard();
        }

    }
    public int time = 10;
    @Override
    public void tick() {
        Vec3 vec3 = this.getDeltaMovement();
        double d5 = vec3.x;
        double d6 = vec3.y;
        double d1 = vec3.z;
        Entity entity = this.getOwner();
        for(int i = 0; i < 5; ++i) {
            this.level().addParticle(ModParticles.AMETHYST_SHINE_PARTICLE.get(), this.getX() + d5 * (double)i / 4.0, this.getY() + d6 * (double)i / 4.0, this.getZ() + d1 * (double)i / 4.0, -d5, -d6 + 0.2, -d1);
        }
        if(time<=0){
            this.setNoGravity(false);
        }
        else{
            time-=1;
        }
        if (entity instanceof Player && !entity.isAlive()) {
            this.discard();
        } else {
            super.tick();
        }
    }
    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        pResult.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 0.0F);
    }
    @Nullable
    public Entity changeDimension(ServerLevel p_37506_, ITeleporter teleporter) {
        Entity entity = this.getOwner();
        if (entity != null && entity.level().dimension() != p_37506_.dimension()) {
            this.setOwner((Entity)null);
        }

        return super.changeDimension(p_37506_, teleporter);
    }
}
