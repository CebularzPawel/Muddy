package net.cebularz.amethystmore.block.custom;

import net.cebularz.amethystmore.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class AmethystBallBlock extends BaseEntityBlock {
    public static final int MAX = RotationSegment.getMaxSegmentIndex();

    private static final int ROTATIONS;
    public static final IntegerProperty ROTATION;
    public static final VoxelShape SHAPE = Block.box(5,0,5,11,6,11);
    public AmethystBallBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(ROTATION, 0));

    }
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return (BlockState)this.defaultBlockState().setValue(ROTATION, RotationSegment.convertToSegment(pContext.getRotation()));
    }

    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return (BlockState)pState.setValue(ROTATION, pRotation.rotate((Integer)pState.getValue(ROTATION), ROTATIONS));
    }
    static {
        ROTATIONS = MAX + 1;
        ROTATION = BlockStateProperties.ROTATION_16;
    }
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(ROTATION);
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return null;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack heldItem = pPlayer.getItemInHand(pHand);

        if(heldItem.isEmpty()){
            if((pPlayer.experienceLevel>=2&&(pPlayer.getMaxHealth()>pPlayer.getHealth()))||pPlayer.isCreative()){
                pPlayer.giveExperiencePoints(-15);
                pPlayer.heal(4);
                spawnParticles(pLevel,pPos);
                pLevel.playSound((Player) null, pPos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 4.0F, 1.0F);

                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }
    private void spawnParticles(LevelAccessor world, BlockPos pos) {
        for (int i = 0; i < 5; i++) {
            double d0 = (double)pos.getX() + world.getRandom().nextDouble();
            double d1 = (double)pos.getY() + world.getRandom().nextDouble();
            double d2 = (double)pos.getZ() + world.getRandom().nextDouble();

            world.addParticle(ModParticles.AMETHYST_SHINE_PARTICLE.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }
}
