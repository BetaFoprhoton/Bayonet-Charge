package com.betafoprhoton.bayonetcharge.client

import com.betafoprhoton.bayonetcharge.BayonetCharge
import dev.architectury.event.events.client.ClientTickEvent
import dev.kosmx.playerAnim.api.layered.IAnimation
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer
import dev.kosmx.playerAnim.api.layered.ModifierLayer
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry
import net.minecraft.client.Minecraft
import net.minecraft.resources.ResourceLocation

class ClientAnimationManager() {
    fun playExecutingAnimation() {
        val clientPlayer = Minecraft.getInstance().player
        if (clientPlayer == null) return
        val animation =
            PlayerAnimationAccess.getPlayerAssociatedData(clientPlayer)
                .get(ResourceLocation(BayonetCharge.MODID, "animation")) as ModifierLayer<IAnimation?>?
        animation!!.setAnimation(KeyframeAnimationPlayer(PlayerAnimationRegistry.getAnimation(ResourceLocation("bayonetcharge", "back"))!!))
    }

    companion object {
        fun register() {
            ClientTickEvent.CLIENT_POST.register {
                ClientTickEvent.Client { minecraft: Minecraft? ->

                }
            }
        }
    }
}