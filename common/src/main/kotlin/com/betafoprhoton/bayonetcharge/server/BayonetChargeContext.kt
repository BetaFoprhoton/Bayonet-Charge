package com.betafoprhoton.bayonetcharge.server

import com.betafoprhoton.bayonetcharge.server.PlayerStateManager.Companion.PlayerState.*
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.targeting.TargetingConditions

class BayonetChargeContext(val player: ServerPlayer) {
    val playerStateManager = PlayerStateManager(
        6 * 20,
        2  * 20,
        6 * 20,
        { stopCharging() },
        {  },
        { removeTagMark() }
    )

    init {
        println("new player joined, bayonet charge context registered")
        playerStateManager.playerState = EXECYTING
        removeTagMark()
    }

    fun tick() {
        playerStateManager.tick()

        if (player.tags.contains(CHARGING.stringName) && !player.tags.contains(CHARGING.stringName + "-CONSUMED")) {
            startCharging()
            player.addTag(CHARGING.stringName + "-CONSUMED")
            //println("starting Bayonet Charge")
        }

        println("Report PlayerState: ${playerStateManager.playerState} ${playerStateManager.chargingTime}, ${playerStateManager.executingTime}, ${playerStateManager.cooldownTime}")
        //TODO: it seems that the charging and cooldown tickers are faster than effects

        if (playerStateManager.playerState == CHARGING) {
            val entities = player.level().getNearbyEntities(LivingEntity::class.java, TargetingConditions.forCombat(), player, player.boundingBox)
            val entityHit: LivingEntity? = entities.find { livingEntity -> player.hasLineOfSight(livingEntity) }
            entityHit?.let { entityCollided(it) }
        }
    }

    private fun removeTagMark() {
        player.removeTag(CHARGING.stringName)
        player.removeTag(CHARGING.stringName + "-CONSUMED")
    }

    fun startCharging() {
        playerStateManager.playerState = CHARGING
        player.addEffect(MobEffectInstance(MobEffects.MOVEMENT_SPEED, playerStateManager.chargingMax, 1), player)
    }

    fun entityCollided(entity: LivingEntity) {
        playerStateManager.playerState = EXECYTING
        println("Player ${player.name} collided $entity.name")
        //TODO: conneted with ClientAnimationManager
    }

    fun stopCharging() {
        player.addEffect(MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, playerStateManager.chargingMax), player)
    }
}