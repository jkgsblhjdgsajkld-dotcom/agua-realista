package com.exemplo.realwater;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;

public class RealWaterMod implements ModInitializer {
    public static final String MOD_ID = "realwater";

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    @Override
    public void onInitialize() {
        ModContent.init(); // força o registro de fluidos, bloco e item
    }
}
