package com.betafoprhoton.bayonetcharge.fabric


import com.betafoprhoton.bayonetcharge.fabriclike.BayonetChargeFabricLike
import net.fabricmc.api.ModInitializer


object BayonetChargeFabric: ModInitializer {
    override fun onInitialize() {
        BayonetChargeFabricLike.init()
    }
}
