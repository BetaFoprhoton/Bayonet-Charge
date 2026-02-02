package com.betafoprhoton.bayonetcharge.quilt

import com.betafoprhoton.bayonetcharge.fabriclike.BayonetChargeFabricLike
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer

object BayonetChargeQuilt: ModInitializer {
    override fun onInitialize(mod: ModContainer?) {
        BayonetChargeFabricLike.init()
    }
}