package com.betafoprhoton.bayonetcharge.client

import net.minecraft.client.KeyMapping
import net.minecraft.client.player.AbstractClientPlayer
import net.minecraft.world.entity.player.Player

class BayonetChargeContext(val player: Player) {
    var chargingTime: Int = -1
        set(value) { if (value > 6 * 20) 6 * 20 else if (value < 0) 0 }
    var cooldownTime = 0
        set(value) { if (value > 6 * 20) 6 * 20 else if (value < 0) 0 }
    val staute: String
        get() { return if (chargingTime >= 0) "charging" else "cooldown" }

    fun chargingTick() {
        chargingTime --

    }

}