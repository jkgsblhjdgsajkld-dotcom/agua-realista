package com.exemplo.realwater.client;

import com.exemplo.realwater.ModContent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

/**
 * Compatível com Sodium: o Sodium respeita o FluidRenderHandler e o BlockRenderLayerMap do Fabric API.
 */
public class RealWaterClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Texturas vanilla da água + tom azul-cristalino mais "realista"
        FluidRenderHandlerRegistry.INSTANCE.register(
                ModContent.STILL, ModContent.FLOWING,
                SimpleFluidRenderHandler.coloredWater(0x3A8FD6));

        // Renderiza como translúcido
        BlockRenderLayerMap.putFluids(ChunkSectionLayer.TRANSLUCENT, ModContent.STILL, ModContent.FLOWING);
    }
}
