package com.betafoprhoton.bayonetcharge.server

import com.betafoprhoton.bayonetcharge.BayonetCharge.MODID
import com.betafoprhoton.bayonetcharge.server.PlayerStateManager.Companion.PlayerState.*

class PlayerStateManager(
    val chargingMax: Int,
    val executingMax: Int,
    val cooldownMax: Int,
    val chargingToCooldown: () -> Unit,
    val executingToCooldown: () -> Unit,
    val cooldownToNone: () -> Unit
) {
    var playerState: PlayerState
        set(value) {
            when (value) {
                CHARGING -> {
                    chargingTime = chargingMax
                    executingTime = 0
                    cooldownTime = 0
                }
                EXECYTING -> {
                    chargingTime = 0
                    executingTime = executingMax
                    cooldownTime = 0
                }
                COOLDOWN -> {
                    chargingTime = 0
                    executingTime = 0
                    cooldownTime = cooldownMax
                }
                NONE -> null
            }
        }
        get() {
            return if (chargingTime > 0 && executingTime == 0 && cooldownTime == 0) CHARGING
            else if (chargingTime == 0 && executingTime > 0 && cooldownTime == 0) EXECYTING
            else if (chargingTime == 0 && executingTime == 0 && cooldownTime > 0) COOLDOWN
            else NONE
        }
    var chargingTime: Int = 0
        set(value) { field = value.coerceIn(0, chargingMax) }
    var executingTime = 0
        set(value) { field = value.coerceIn(0, executingMax) }
    var cooldownTime = 0
        set(value) { field = value.coerceIn(0, cooldownMax) }

    fun tick() {
        chargingTime --
        executingTime --
        cooldownTime --

        if (chargingTime == 1 && executingTime == 0 && cooldownTime == 0) {
            chargingToCooldown.invoke()
            playerState = COOLDOWN
        }
        else if (chargingTime == 0 && executingTime == 1 && cooldownTime == 0) {
            executingToCooldown.invoke()
            playerState = COOLDOWN
            println("executingToCooldown")
        }
        else if (chargingTime == 0 && executingTime == 0 && cooldownTime == 1) {
            cooldownToNone.invoke()
            playerState = NONE
        }
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