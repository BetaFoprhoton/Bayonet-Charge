package com.betafoprhoton.bayonetcharge.server

import com.betafoprhoton.bayonetcharge.BayonetCharge
import com.betafoprhoton.bayonetcharge.server.PlayerStateManager.Companion.PlayerState.*
import dev.kosmx.playerAnim.api.layered.IAnimation
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer
import dev.kosmx.playerAnim.api.layered.ModifierLayer
import dev.kosmx.playerAnim.api.layered.modifier.AbstractFadeModifier
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry
import net.minecraft.client.player.AbstractClientPlayer
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.targeting.TargetingConditions
import net.minecraft.world.entity.player.Player

class BayonetChargeContext(val player: Player) {
    val playerStateManager = PlayerStateManager(6 * 20, 2  * 20, 6 * 20)

    fun tick() {
        playerStateManager.tick()

        if (player.tags.contains(CHARGING.stringName) && !player.tags.contains(CHARGING.stringName + "-CONSUMED")) {
            playerStateManager.playerState = CHARGING
            player.addTag(CHARGING.stringName + "-CONSUMED")
        }

        if (playerStateManager.playerState == NONE) {
            player.removeTag(CHARGING.stringName)
            player.removeTag(CHARGING.stringName + "-CONSUMED")
        }

        if (!player.isSprinting || playerStateManager.playerState == COOLDOWN) {
            stopCharging()
        }

        if (playerStateManager.playerState == CHARGING) {
            val entities = player.level().getNearbyEntities(LivingEntity::class.java, TargetingConditions.forCombat(), player, player.boundingBox)
            val entityHit: LivingEntity? = entities.find { livingEntity -> player.hasLineOfSight(livingEntity) }
            entityHit?.let { entityCollided(it) }
        }
    }

    fun startCharging() {
        playerStateManager.playerState = CHARGING
        player.addEffect(MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 30, 2), player)
    }

    fun entityCollided(entity: LivingEntity) {
        playerStateManager.playerState = EXECYTING
        //TODO: conneted with ClientAnimationManager
    }

    fun stopCharging() {
        playerStateManager.playerState = COOLDOWN
        player.addEffect(MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20 * 30), player)
    }

}