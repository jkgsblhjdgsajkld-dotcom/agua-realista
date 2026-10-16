package com.exemplo.realwater;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

/**
 * Água "realista":
 *  - NÃO cria fontes infinitas (canConvertToSource = false): poças se espalham e afinam.
 *  - Escorre mais rápido que a água vanilla (tickDelay 4 em vez de 5).
 *  - Entra na tag minecraft:water => nadar, afogar, apagar fogo, barcos, peixes etc. funcionam.
 */
public abstract class RealisticWaterFluid extends FlowingFluid {

    @Override public Fluid getSource()  { return ModContent.STILL; }
    @Override public Fluid getFlowing() { return ModContent.FLOWING; }
    @Override public Item getBucket()   { return ModContent.BUCKET; }

    @Override public boolean isSame(Fluid fluid) {
        return fluid == ModContent.STILL || fluid == ModContent.FLOWING;
    }

    @Override protected boolean canConvertToSource(ServerLevel level) { return false; }

    @Override protected void beforeDestroyingBlock(LevelAccessor level, BlockPos pos, BlockState state) {
        BlockEntity be = state.hasBlockEntity() ? level.getBlockEntity(pos) : null;
        Block.dropResources(state, level, pos, be);
    }

    @Override protected int getSlopeFindDistance(LevelReader level) { return 4; }
    @Override protected int getDropOff(LevelReader level)           { return 1; }
    @Override public int getTickDelay(LevelReader level)            { return 4; }
    @Override protected float getExplosionResistance()              { return 100.0F; }

    @Override protected boolean canBeReplacedWith(FluidState state, BlockGetter level, BlockPos pos,
                                                  Fluid fluid, Direction direction) {
        return direction == Direction.DOWN && !fluid.is(FluidTags.WATER);
    }

    @Override protected BlockState createLegacyBlock(FluidState state) {
        return ModContent.WATER_BLOCK.defaultBlockState()
                .setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
    }

    public static class Flowing extends RealisticWaterFluid {
        @Override protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }
        @Override public int getAmount(FluidState state) { return state.getValue(LEVEL); }
        @Override public boolean isSource(FluidState state) { return false; }
    }

    public static class Still extends RealisticWaterFluid {
        @Override public int getAmount(FluidState state) { return 8; }
        @Override public boolean isSource(FluidState state) { return true; }
    }
}
