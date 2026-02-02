package com.betafoprhoton.bayonetcharge.mixin;

import com.betafoprhoton.bayonetcharge.server.BayonetChargeContext;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class MixinPlayer {
    @Unique
    private BayonetChargeContext bayonet_charge$bayonetChargeContext = null;

    @Inject(at = @At("TAIL"), method = "tick")
    public void tick(CallbackInfo ci) {
        if (bayonet_charge$bayonetChargeContext != null) {
            bayonet_charge$bayonetChargeContext.tick();
        }
    }
}
