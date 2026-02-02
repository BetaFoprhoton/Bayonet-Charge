package com.betafoprhoton.bayonetcharge.server

import com.betafoprhoton.bayonetcharge.BayonetCharge.MODID
import com.betafoprhoton.bayonetcharge.server.PlayerStateManager.Companion.PlayerState.*
import kotlin.compareTo

class PlayerStateManager(val chargingMax: Int, val executingMax: Int, val cooldownMax: Int) {
    var playerState: PlayerState
        set(value) {
            when (value) {
                CHARGING -> chargingTime = chargingMax
                EXECYTING -> executingTime = executingMax
                COOLDOWN -> cooldownTime = cooldownMax
                NONE -> null
            }
        }
        get() {
            return if (chargingTime > 0 && executingTime == 0 && cooldownTime == 0) CHARGING
            else if (chargingTime == 0 && executingTime > 0 && cooldownTime == 0) EXECYTING
            else if (chargingTime == 0 && executingTime == 0 && cooldownTime > 0) COOLDOWN
            else PlayerState.NONE
        }
    var chargingTime: Int = 0
        set(value) { field = value.coerceIn(0, chargingMax) }
    var executingTime = 0
        set(value) { field = value.coerceIn(0, executingTime) }
    var cooldownTime = 0
        set(value) { field = value.coerceIn(0, cooldownMax) }

    fun tick() {
        chargingTime --
        executingTime --
        cooldownTime --
    }

    companion object {
        enum class PlayerState(val stringName: String) {
            CHARGING("$MODID\$CHARGING"),
            EXECYTING("$MODID\$EXECYTING"),
            COOLDOWN("$MODID\$COOLDOWN"),
            NONE("$MODID\$NONE")
        }
    }
}