//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.cebularz.amethystmore.block.custom;

import com.google.common.annotations.VisibleForTesting;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import javax.annotation.Nullable;

import net.cebularz.amethystmore.block.ModBlocks;
import net.cebularz.amethystmore.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEvent.Context;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AmethystSpikeBlock extends Block implements Fallable, SimpleWaterloggedBlock {
    public static final DirectionProperty TIP_DIRECTION;
    public static final EnumProperty<DripstoneThickness> THICKNESS;
    public static final BooleanProperty WATERLOGGED;
    private static final int MAX_SEARCH_LENGTH_WHEN_CHECKING_DRIP_TYPE = 11;
    private static final int DELAY_BEFORE_FALLING = 2;
    private static final float DRIP_PROBABILITY_PER_ANIMATE_TICK = 0.02F;
    private static final float DRIP_PROBABILITY_PER_ANIMATE_TICK_IF_UNDER_LIQUID_SOURCE = 0.12F;
    private static final int MAX_SEARCH_LENGTH_BETWEEN_STALACTITE_TIP_AND_CAULDRON = 11;
    private static final float WATER_TRANSFER_PROBABILITY_PER_RANDOM_TICK = 0.17578125F;
    private static final float LAVA_TRANSFER_PROBABILITY_PER_RANDOM_TICK = 0.05859375F;
    private static final double MIN_TRIDENT_VELOCITY_TO_BREAK_DRIPSTONE = 0.6;
    private static final float STALACTITE_DAMAGE_PER_FALL_DISTANCE_AND_SIZE = 1.0F;
    private static final int STALACTITE_MAX_DAMAGE = 40;
    private static final int MAX_STALACTITE_HEIGHT_FOR_DAMAGE_CALCULATION = 6;
    private static final float STALAGMITE_FALL_DISTANCE_OFFSET = 2.0F;
    private static final int STALAGMITE_FALL_DAMAGE_MODIFIER = 2;
    private static final float AVERAGE_DAYS_PER_GROWTH = 5.0F;
    private static final float GROWTH_PROBABILITY_PER_RANDOM_TICK = 0.011377778F;
    private static final int MAX_GROWTH_LENGTH = 7;
    private static final int MAX_STALAGMITE_SEARCH_RANGE_WHEN_GROWING = 10;
    private static final float STALACTITE_DRIP_START_PIXEL = 0.6875F;
    private static final VoxelShape TIP_MERGE_SHAPE;
    private static final VoxelShape TIP_SHAPE_UP;
    private static final VoxelShape TIP_SHAPE_DOWN;
    private static final VoxelShape FRUSTUM_SHAPE;
    private static final VoxelShape MIDDLE_SHAPE;
    private static final VoxelShape BASE_SHAPE;
    private static final float MAX_HORIZONTAL_OFFSET = 0.125F;
    private static final VoxelShape REQUIRED_SPACE_TO_DRIP_THROUGH_NON_SOLID_BLOCK;

    public AmethystSpikeBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(TIP_DIRECTION, Direction.UP)).setValue(THICKNESS, DripstoneThickness.TIP)).setValue(WATERLOGGED, false));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(new Property[]{TIP_DIRECTION, THICKNESS, WATERLOGGED});
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return isValidPointedDripstonePlacement(pLevel, pPos, (Direction)pState.getValue(TIP_DIRECTION));
    }

    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        if ((Boolean)pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

        if (pDirection != Direction.UP && pDirection != Direction.DOWN) {
            return pState;
        } else {
            Direction $$6 = (Direction)pState.getValue(TIP_DIRECTION);
            if ($$6 == Direction.DOWN && pLevel.getBlockTicks().hasScheduledTick(pCurrentPos, this)) {
                return pState;
            } else if (pDirection == $$6.getOpposite() && !this.canSurvive(pState, pLevel, pCurrentPos)) {
                if ($$6 == Direction.DOWN) {
                    pLevel.scheduleTick(pCurrentPos, this, 2);
                } else {
                    pLevel.scheduleTick(pCurrentPos, this, 1);
                }

                return pState;
            } else {
                boolean $$7 = pState.getValue(THICKNESS) == DripstoneThickness.TIP_MERGE;
                DripstoneThickness $$8 = calculateDripstoneThickness(pLevel, pCurrentPos, $$6, $$7);
                return (BlockState)pState.setValue(THICKNESS, $$8);
            }
        }
    }

    public void onProjectileHit(Level pLevel, BlockState pState, BlockHitResult pHit, Projectile pProjectile) {
        BlockPos $$4 = pHit.getBlockPos();
        if (!pLevel.isClientSide && pProjectile.mayInteract(pLevel, $$4) && pProjectile instanceof ThrownTrident && pProjectile.getDeltaMovement().length() > 0.6) {
            pLevel.destroyBlock($$4, true);
        }

    }

    public void fallOn(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float pFallDistance) {
        if (pState.getValue(TIP_DIRECTION) == Direction.UP && pState.getValue(THICKNESS) == DripstoneThickness.TIP) {
            pEntity.causeFallDamage(pFallDistance + 2.0F, 2.0F, pLevel.damageSources().stalagmite());
        } else {
            super.fallOn(pLevel, pState, pPos, pEntity, pFallDistance);
        }

    }

    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (canDrip(pState)){
            float $$4 = pRandom.nextFloat();
            if (!($$4 > 0.12F)) {
                spawnParticles(pLevel, pPos);
            }
        }

    }
    private void spawnParticles(LevelAccessor world, BlockPos pos) {
        for (int i = 0; i < 1; i++) {
            double d0 = (double)pos.getX() + world.getRandom().nextDouble();
            double d1 = (double)pos.getY() + world.getRandom().nextDouble();
            double d2 = (double)pos.getZ() + world.getRandom().nextDouble();

            world.addParticle(ModParticles.AMETHYST_SHINE_PARTICLE.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (isStalagmite(pState) && !this.canSurvive(pState, pLevel, pPos)) {
            pLevel.destroyBlock(pPos, true);
        } else {
            spawnFallingStalactite(pState, pLevel, pPos);
        }

    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextFloat() < 0.011377778F && isStalactiteStartPos(pState, pLevel, pPos)) {
            growStalactiteOrStalagmiteIfPossible(pState, pLevel, pPos, pRandom);
        }

    }

    

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        LevelAccessor $$1 = pContext.getLevel();
        BlockPos $$2 = pContext.getClickedPos();
        Direction $$3 = pContext.getNearestLookingVerticalDirection().getOpposite();
        Direction $$4 = calculateTipDirection($$1, $$2, $$3);
        if ($$4 == null) {
            return null;
        } else {
            boolean $$5 = !pContext.isSecondaryUseActive();
            DripstoneThickness $$6 = calculateDripstoneThickness($$1, $$2, $$4, $$5);
            return $$6 == null ? null : (BlockState)((BlockState)((BlockState)this.defaultBlockState().setValue(TIP_DIRECTION, $$4)).setValue(THICKNESS, $$6)).setValue(WATERLOGGED, $$1.getFluidState($$2).getType() == Fluids.WATER);
        }
    }

    public FluidState getFluidState(BlockState pState) {
        return (Boolean)pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return Shapes.empty();
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        DripstoneThickness $$4 = (DripstoneThickness)pState.getValue(THICKNESS);
        VoxelShape $$10;
        if ($$4 == DripstoneThickness.TIP_MERGE) {
            $$10 = TIP_MERGE_SHAPE;
        } else if ($$4 == DripstoneThickness.TIP) {
            if (pState.getValue(TIP_DIRECTION) == Direction.DOWN) {
                $$10 = TIP_SHAPE_DOWN;
            } else {
                $$10 = TIP_SHAPE_UP;
            }
        } else if ($$4 == DripstoneThickness.FRUSTUM) {
            $$10 = FRUSTUM_SHAPE;
        } else if ($$4 == DripstoneThickness.MIDDLE) {
            $$10 = MIDDLE_SHAPE;
        } else {
            $$10 = BASE_SHAPE;
        }

        Vec3 $$11 = pState.getOffset(pLevel, pPos);
        return $$10.move($$11.x, 0.0, $$11.z);
    }

    public boolean isCollisionShapeFullBlock(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return false;
    }

    public float getMaxHorizontalOffset() {
        return 0.125F;
    }

    public void onBrokenAfterFall(Level pLevel, BlockPos pPos, FallingBlockEntity pFallingBlock) {
        if (!pFallingBlock.isSilent()) {
            pLevel.levelEvent(1045, pPos, 0);
        }

    }

    public DamageSource getFallDamageSource(Entity pEntity) {
        return pEntity.damageSources().fallingStalactite(pEntity);
    }

    private static void spawnFallingStalactite(BlockState pState, ServerLevel pLevel, BlockPos pPos) {
        BlockPos.MutableBlockPos $$3 = pPos.mutable();

        for(BlockState $$4 = pState; isStalactite($$4); $$4 = pLevel.getBlockState($$3)) {
            FallingBlockEntity $$5 = FallingBlockEntity.fall(pLevel, $$3, $$4);
            if (isTip($$4, true)) {
                int $$6 = Math.max(1 + pPos.getY() - $$3.getY(), 6);
                float $$7 = 1.0F * (float)$$6;
                $$5.setHurtsEntities($$7, 40);
                break;
            }

            $$3.move(Direction.DOWN);
        }

    }

    @VisibleForTesting
    public static void growStalactiteOrStalagmiteIfPossible(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        BlockState $$4 = pLevel.getBlockState(pPos.above(1));
        BlockState $$5 = pLevel.getBlockState(pPos.above(2));
        if (canGrow($$4, $$5)) {
            BlockPos $$6 = findTip(pState, pLevel, pPos, 7, false);
            if ($$6 != null) {
                BlockState $$7 = pLevel.getBlockState($$6);
                if (canDrip($$7) && canTipGrow($$7, pLevel, $$6)) {
                    if (pRandom.nextBoolean()) {
                        grow(pLevel, $$6, Direction.DOWN);
                    } else {
                        growStalagmiteBelow(pLevel, $$6);
                    }

                }
            }
        }
    }

    private static void growStalagmiteBelow(ServerLevel pLevel, BlockPos pPos) {
        BlockPos.MutableBlockPos $$2 = pPos.mutable();

        for(int $$3 = 0; $$3 < 10; ++$$3) {
            $$2.move(Direction.DOWN);
            BlockState $$4 = pLevel.getBlockState($$2);
            if (!$$4.getFluidState().isEmpty()) {
                return;
            }

            if (isUnmergedTipWithDirection($$4, Direction.UP) && canTipGrow($$4, pLevel, $$2)) {
                grow(pLevel, $$2, Direction.UP);
                return;
            }

            if (isValidPointedDripstonePlacement(pLevel, $$2, Direction.UP) && !pLevel.isWaterAt($$2.below())) {
                grow(pLevel, $$2.below(), Direction.UP);
                return;
            }

            if (!canDripThrough(pLevel, $$2, $$4)) {
                return;
            }
        }

    }

    private static void grow(ServerLevel pServer, BlockPos pPos, Direction pDirection) {
        BlockPos $$3 = pPos.relative(pDirection);
        BlockState $$4 = pServer.getBlockState($$3);
        if (isUnmergedTipWithDirection($$4, pDirection.getOpposite())) {
            createMergedTips($$4, pServer, $$3);
        } else if ($$4.isAir() || $$4.is(Blocks.WATER)) {
            createDripstone(pServer, $$3, pDirection, DripstoneThickness.TIP);
        }

    }

    private static void createDripstone(LevelAccessor pLevel, BlockPos pPos, Direction pDirection, DripstoneThickness pThickness) {
        BlockState $$4 = (BlockState)((BlockState)((BlockState) ModBlocks.AMETHYST_SPIKE.get().defaultBlockState().setValue(TIP_DIRECTION, pDirection)).setValue(THICKNESS, pThickness)).setValue(WATERLOGGED, pLevel.getFluidState(pPos).getType() == Fluids.WATER);
        pLevel.setBlock(pPos, $$4, 3);
    }

    private static void createMergedTips(BlockState pState, LevelAccessor pLevel, BlockPos pPos) {
        BlockPos $$5;
        BlockPos $$6;
        if (pState.getValue(TIP_DIRECTION) == Direction.UP) {
            $$6 = pPos;
            $$5 = pPos.above();
        } else {
            $$5 = pPos;
            $$6 = pPos.below();
        }

        createDripstone(pLevel, $$5, Direction.DOWN, DripstoneThickness.TIP_MERGE);
        createDripstone(pLevel, $$6, Direction.UP, DripstoneThickness.TIP_MERGE);
    }





    @Nullable
    private static BlockPos findTip(BlockState pState, LevelAccessor pLevel, BlockPos pPos, int pMaxIterations, boolean pIsTipMerge) {
        if (isTip(pState, pIsTipMerge)) {
            return pPos;
        } else {
            Direction $$5 = (Direction)pState.getValue(TIP_DIRECTION);
            BiPredicate<BlockPos, BlockState> $$6 = (p_202023_, p_202024_) -> {
                return p_202024_.is(ModBlocks.AMETHYST_SPIKE.get()) && p_202024_.getValue(TIP_DIRECTION) == $$5;
            };
            return (BlockPos)findBlockVertical(pLevel, pPos, $$5.getAxisDirection(), $$6, (p_154168_) -> {
                return isTip(p_154168_, pIsTipMerge);
            }, pMaxIterations).orElse((BlockPos) null);
        }
    }

    @Nullable
    private static Direction calculateTipDirection(LevelReader pLevel, BlockPos pPos, Direction pDir) {
        Direction $$5;
        if (isValidPointedDripstonePlacement(pLevel, pPos, pDir)) {
            $$5 = pDir;
        } else {
            if (!isValidPointedDripstonePlacement(pLevel, pPos, pDir.getOpposite())) {
                return null;
            }

            $$5 = pDir.getOpposite();
        }

        return $$5;
    }

    private static DripstoneThickness calculateDripstoneThickness(LevelReader pLevel, BlockPos pPos, Direction pDir, boolean pIsTipMerge) {
        Direction $$4 = pDir.getOpposite();
        BlockState $$5 = pLevel.getBlockState(pPos.relative(pDir));
        if (isPointedDripstoneWithDirection($$5, $$4)) {
            return !pIsTipMerge && $$5.getValue(THICKNESS) != DripstoneThickness.TIP_MERGE ? DripstoneThickness.TIP : DripstoneThickness.TIP_MERGE;
        } else if (!isPointedDripstoneWithDirection($$5, pDir)) {
            return DripstoneThickness.TIP;
        } else {
            DripstoneThickness $$6 = (DripstoneThickness)$$5.getValue(THICKNESS);
            if ($$6 != DripstoneThickness.TIP && $$6 != DripstoneThickness.TIP_MERGE) {
                BlockState $$7 = pLevel.getBlockState(pPos.relative($$4));
                return !isPointedDripstoneWithDirection($$7, pDir) ? DripstoneThickness.BASE : DripstoneThickness.MIDDLE;
            } else {
                return DripstoneThickness.FRUSTUM;
            }
        }
    }

    public static boolean canDrip(BlockState p_154239_) {
        return isStalactite(p_154239_) && p_154239_.getValue(THICKNESS) == DripstoneThickness.TIP && !(Boolean)p_154239_.getValue(WATERLOGGED);
    }

    private static boolean canTipGrow(BlockState pState, ServerLevel pLevel, BlockPos pPos) {
        Direction $$3 = (Direction)pState.getValue(TIP_DIRECTION);
        BlockPos $$4 = pPos.relative($$3);
        BlockState $$5 = pLevel.getBlockState($$4);
        if (!$$5.getFluidState().isEmpty()) {
            return false;
        } else {
            return $$5.isAir() ? true : isUnmergedTipWithDirection($$5, $$3.getOpposite());
        }
    }

    private static Optional<BlockPos> findRootBlock(Level pLevel, BlockPos pPos, BlockState pState, int pMaxIterations) {
        Direction $$4 = (Direction)pState.getValue(TIP_DIRECTION);
        BiPredicate<BlockPos, BlockState> $$5 = (p_202015_, p_202016_) -> {
            return p_202016_.is(ModBlocks.AMETHYST_SPIKE.get()) && p_202016_.getValue(TIP_DIRECTION) == $$4;
        };
        return findBlockVertical(pLevel, pPos, $$4.getOpposite().getAxisDirection(), $$5, (p_154245_) -> {
            return !p_154245_.is(ModBlocks.AMETHYST_SPIKE.get());
        }, pMaxIterations);
    }

    private static boolean isValidPointedDripstonePlacement(LevelReader pLevel, BlockPos pPos, Direction pDir) {
        BlockPos $$3 = pPos.relative(pDir.getOpposite());
        BlockState $$4 = pLevel.getBlockState($$3);
        return $$4.isFaceSturdy(pLevel, $$3, pDir) || isPointedDripstoneWithDirection($$4, pDir);
    }

    private static boolean isTip(BlockState pState, boolean pIsTipMerge) {
        if (!pState.is(ModBlocks.AMETHYST_SPIKE.get())) {
            return false;
        } else {
            DripstoneThickness $$2 = (DripstoneThickness)pState.getValue(THICKNESS);
            return $$2 == DripstoneThickness.TIP || pIsTipMerge && $$2 == DripstoneThickness.TIP_MERGE;
        }
    }

    private static boolean isUnmergedTipWithDirection(BlockState pState, Direction pDir) {
        return isTip(pState, false) && pState.getValue(TIP_DIRECTION) == pDir;
    }

    private static boolean isStalactite(BlockState pState) {
        return isPointedDripstoneWithDirection(pState, Direction.DOWN);
    }

    private static boolean isStalagmite(BlockState pState) {
        return isPointedDripstoneWithDirection(pState, Direction.UP);
    }

    private static boolean isStalactiteStartPos(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return isStalactite(pState) && !pLevel.getBlockState(pPos.above()).is(ModBlocks.AMETHYST_SPIKE.get());
    }

    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }

    private static boolean isPointedDripstoneWithDirection(BlockState pState, Direction pDir) {
        return pState.is(ModBlocks.AMETHYST_SPIKE.get()) && pState.getValue(TIP_DIRECTION) == pDir;
    }



    @Nullable
    public static BlockPos findStalactiteTipAboveCauldron(Level pLevel, BlockPos pPos) {
        BiPredicate<BlockPos, BlockState> $$2 = (p_202030_, p_202031_) -> {
            return canDripThrough(pLevel, p_202030_, p_202031_);
        };
        return (BlockPos)findBlockVertical(pLevel, pPos, Direction.UP.getAxisDirection(), $$2, AmethystSpikeBlock::canDrip, 11).orElse((BlockPos) null);
    }

    public static Fluid getCauldronFillFluidType(ServerLevel pLevel, BlockPos pPos) {
        return (Fluid)getFluidAboveStalactite(pLevel, pPos, pLevel.getBlockState(pPos)).map((p_221858_) -> {
            return p_221858_.fluid;
        }).filter(AmethystSpikeBlock::canFillCauldron).orElse(Fluids.EMPTY);
    }

    private static Optional<FluidInfo> getFluidAboveStalactite(Level pLevel, BlockPos pPos, BlockState pState) {
        return !isStalactite(pState) ? Optional.empty() : findRootBlock(pLevel, pPos, pState, 11).map((p_221876_) -> {
            BlockPos $$2 = p_221876_.above();
            BlockState $$3 = pLevel.getBlockState($$2);
            Object $$5;
            if ($$3.is(Blocks.MUD) && !pLevel.dimensionType().ultraWarm()) {
                $$5 = Fluids.WATER;
            } else {
                $$5 = pLevel.getFluidState($$2).getType();
            }

            return new FluidInfo($$2, (Fluid)$$5, $$3);
        });
    }

    private static boolean canFillCauldron(Fluid p_154159_) {
        return p_154159_ == Fluids.LAVA || p_154159_ == Fluids.WATER;
    }

    private static boolean canGrow(BlockState amethystblockstate, BlockState pState) {
        return (amethystblockstate.is(Blocks.AMETHYST_BLOCK) && pState.is(Blocks.CALCITE));
    }

    private static Fluid getDripFluid(Level pLevel, Fluid pFluid) {
        if (pFluid.isSame(Fluids.EMPTY)) {
            return pLevel.dimensionType().ultraWarm() ? Fluids.LAVA : Fluids.WATER;
        } else {
            return pFluid;
        }
    }

    private static Optional<BlockPos> findBlockVertical(LevelAccessor pLevel, BlockPos pPos, Direction.AxisDirection pAxis, BiPredicate<BlockPos, BlockState> pPositionalStatePredicate, Predicate<BlockState> pStatePredicate, int pMaxIterations) {
        Direction $$6 = Direction.get(pAxis, Axis.Y);
        BlockPos.MutableBlockPos $$7 = pPos.mutable();

        for(int $$8 = 1; $$8 < pMaxIterations; ++$$8) {
            $$7.move($$6);
            BlockState $$9 = pLevel.getBlockState($$7);
            if (pStatePredicate.test($$9)) {
                return Optional.of($$7.immutable());
            }

            if (pLevel.isOutsideBuildHeight($$7.getY()) || !pPositionalStatePredicate.test($$7, $$9)) {
                return Optional.empty();
            }
        }

        return Optional.empty();
    }

    private static boolean canDripThrough(BlockGetter pLevel, BlockPos pPos, BlockState pState) {
        if (pState.isAir()) {
            return true;
        } else if (pState.isSolidRender(pLevel, pPos)) {
            return false;
        } else if (!pState.getFluidState().isEmpty()) {
            return false;
        } else {
            VoxelShape $$3 = pState.getCollisionShape(pLevel, pPos);
            return !Shapes.joinIsNotEmpty(REQUIRED_SPACE_TO_DRIP_THROUGH_NON_SOLID_BLOCK, $$3, BooleanOp.AND);
        }
    }

    static {
        TIP_DIRECTION = BlockStateProperties.VERTICAL_DIRECTION;
        THICKNESS = BlockStateProperties.DRIPSTONE_THICKNESS;
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        TIP_MERGE_SHAPE = Block.box(5.0, 0.0, 5.0, 11.0, 16.0, 11.0);
        TIP_SHAPE_UP = Block.box(5.0, 0.0, 5.0, 11.0, 11.0, 11.0);
        TIP_SHAPE_DOWN = Block.box(5.0, 5.0, 5.0, 11.0, 16.0, 11.0);
        FRUSTUM_SHAPE = Block.box(4.0, 0.0, 4.0, 12.0, 16.0, 12.0);
        MIDDLE_SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 16.0, 13.0);
        BASE_SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0);
        REQUIRED_SPACE_TO_DRIP_THROUGH_NON_SOLID_BLOCK = Block.box(6.0, 0.0, 6.0, 10.0, 16.0, 10.0);
    }

    static record FluidInfo(BlockPos pos, Fluid fluid, BlockState sourceState) {
        FluidInfo(BlockPos pos, Fluid fluid, BlockState sourceState) {
            this.pos = pos;
            this.fluid = fluid;
            this.sourceState = sourceState;
        }

        public BlockPos pos() {
            return this.pos;
        }

        public Fluid fluid() {
            return this.fluid;
        }

        public BlockState sourceState() {
            return this.sourceState;
        }
    }
}
