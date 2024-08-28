package net.cebularz.newandmuddy.mixin;


import net.cebularz.newandmuddy.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.MudBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(MudBlock.class)
public class MudDry {
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (pLevel.dimensionType().ultraWarm()) {
            pLevel.setBlock(pPos, ModBlocks.DRIED_MUD.get().defaultBlockState(), 3);
            pLevel.levelEvent(2009, pPos, 0);
            pLevel.playSound((Player)null, pPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, (1.0F + pLevel.getRandom().nextFloat() * 0.2F) * 0.7F);
        }

    }
}
