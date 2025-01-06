package net.cebularz.amethystmore.item.custom;

import net.cebularz.amethystmore.item.ModItems;
import net.cebularz.amethystmore.world.entity.projectile.AmethystArrow;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class AmethystArrowItem extends ArrowItem {
    public AmethystArrowItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        pLevel.playSound((Player) null, pShooter.blockPosition(), SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 4.0F, 1.0F);
        AmethystArrow arrow = new AmethystArrow(pShooter,pLevel, ModItems.AMETHYST_ARROW.get());
        arrow.damageSources();
        return arrow;
    }

    public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
        int enchant = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, bow);
        return enchant <= 0 ? false : this.getClass() == AmethystArrowItem.class;
    }
}
