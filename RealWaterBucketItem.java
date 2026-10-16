package com.exemplo.realwater;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;

/** Balde "mágico": despeja a água realista e continua cheio (água infinita para colocar, mas sem fontes infinitas). */
public class RealWaterBucketItem extends BucketItem {
    public RealWaterBucketItem(Fluid fluid, Properties properties) {
        super(fluid, properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        InteractionResult result = super.use(level, player, hand);
        // Se despejou a água, devolve SUCCESS simples => o item na mão NÃO é trocado por balde vazio.
        return result.consumesAction() ? InteractionResult.SUCCESS : result;
    }
}
