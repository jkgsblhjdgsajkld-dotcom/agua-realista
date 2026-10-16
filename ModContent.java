package com.exemplo.realwater;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public final class ModContent {
    private ModContent() {}

    public static final FlowingFluid STILL = Registry.register(
            BuiltInRegistries.FLUID, RealWaterMod.id("realistic_water"),
            new RealisticWaterFluid.Still());

    public static final FlowingFluid FLOWING = Registry.register(
            BuiltInRegistries.FLUID, RealWaterMod.id("flowing_realistic_water"),
            new RealisticWaterFluid.Flowing());

    public static final Block WATER_BLOCK = Registry.register(
            BuiltInRegistries.BLOCK, RealWaterMod.id("realistic_water"),
            new LiquidBlock(STILL, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, RealWaterMod.id("realistic_water")))
                    .mapColor(MapColor.WATER)
                    .replaceable()
                    .noCollision()
                    .strength(100.0F)
                    .pushReaction(PushReaction.DESTROY)
                    .noLootTable()
                    .liquid()));

    public static final Item BUCKET = Registry.register(
            BuiltInRegistries.ITEM, RealWaterMod.id("realistic_water_bucket"),
            new RealWaterBucketItem(STILL, new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, RealWaterMod.id("realistic_water_bucket")))
                    .craftRemainder(Items.BUCKET)
                    .stacksTo(1)));

    public static void init() {}
}
