package net.cebularz.amethystmore.world.entity.projectile;

import net.cebularz.amethystmore.item.ModItems;
import net.cebularz.amethystmore.particle.ModParticles;
import net.cebularz.amethystmore.world.entity.ModEntityType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class AmethystArrow extends AbstractArrow {
    private final Item referenceitem;
    public AmethystArrow(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setNoGravity(true);
        this.referenceitem = ModItems.AMETHYST_ARROW.get();
    }

    public AmethystArrow(LivingEntity pShooter, Level pLevel, Item referenceitem) {

        super(ModEntityType.AMETHYST_ARROW.get(), pShooter, pLevel);
        this.setNoGravity(true);
        this.referenceitem = referenceitem;
    }

    @Override
    public ItemStack getPickupItem() {
        return new ItemStack(this.referenceitem);
    }
    public int time = 10;
    @Override
    public void tick() {
        Vec3 vec3 = this.getDeltaMovement();
        double d5 = vec3.x;
        double d6 = vec3.y;
        double d1 = vec3.z;



        if(time<=0){
            this.setNoGravity(false);
        }
        else{
            time-=1;


            for(int i = 0; i < 10; ++i) {
                this.level().addParticle(ModParticles.AMETHYST_SHINE_PARTICLE.get(), this.getX() + d5 * (double)i / 4.0, this.getY() + d6 * (double)i / 4.0, this.getZ() + d1 * (double)i / 4.0, -d5, -d6 + 0.2, -d1);
            }
        }

        super.tick();
    }



}
