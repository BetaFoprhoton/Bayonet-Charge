package com.betafoprhoton.bayonetcharge.forge

import com.betafoprhoton.bayonetcharge.BayonetCharge
import dev.architectury.platform.forge.EventBuses
import net.minecraftforge.fml.common.Mod
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(BayonetCharge.MODID)
object BayonetChargeForge {
    init {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(BayonetCharge.MODID, MOD_BUS)
        BayonetCharge.init()
    }
}