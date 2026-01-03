package com.betafoprhoton.bayonetcharge.forge

import com.betafoprhoton.bayonetcharge.BayonetCharge
import dev.architectury.platform.forge.EventBuses
import net.minecraftforge.fml.common.Mod
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(BayonetCharge.MOD_ID)
object BayonetChargeForge {
    init {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(BayonetCharge.MOD_ID, MOD_BUS)
        BayonetCharge.init()
    }
}