package com.betafoprhoton.bayonetcharge.client

import com.betafoprhoton.bayonetcharge.BayonetCharge
import com.betafoprhoton.bayonetcharge.BayonetCharge.MODID
import com.betafoprhoton.bayonetcharge.animation.AnimationRegistry
import dev.architectury.networking.NetworkManager
import dev.kosmx.playerAnim.api.layered.AnimationStack
import dev.kosmx.playerAnim.api.layered.IAnimation
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer
import dev.kosmx.playerAnim.api.layered.ModifierLayer
import dev.kosmx.playerAnim.api.layered.modifier.SpeedModifier
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess.AnimationRegister
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationFactory
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry
import net.minecraft.client.player.AbstractClientPlayer
import net.minecraft.client.player.LocalPlayer
import net.minecraft.resources.ResourceLocation


object ClientAnimationManager {
    fun playExecutingAnimation(player: LocalPlayer) {

        val animation = PlayerAnimationAccess.getPlayerAssociatedData(player).get(ResourceLocation(MODID, "back")) as ModifierLayer<IAnimation?>?
        animation!!.setAnimation(KeyframeAnimationPlayer(PlayerAnimationRegistry.getAnimation(ResourceLocation("bayonetcharge", "back"))!!))
    }

    val ANIMATION_MESSAGE_ID = ResourceLocation(MODID, "animation")

    fun register() {
//        ClientTickEvent.CLIENT_POST.register {
//            it.player
//        }



        NetworkManager.registerReceiver(
            NetworkManager.Side.S2C,
            ANIMATION_MESSAGE_ID
        ) { _, context ->
            playExecutingAnimation(context.player as LocalPlayer)
        }
    }
}