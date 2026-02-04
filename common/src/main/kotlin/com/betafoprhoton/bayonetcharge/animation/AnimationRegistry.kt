package com.betafoprhoton.bayonetcharge.animation

import com.betafoprhoton.bayonetcharge.BayonetCharge.MODID
import dev.kosmx.playerAnim.api.layered.AnimationStack
import dev.kosmx.playerAnim.api.layered.IAnimation
import dev.kosmx.playerAnim.api.layered.ModifierLayer
import dev.kosmx.playerAnim.api.layered.modifier.SpeedModifier
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationFactory
import net.minecraft.client.player.AbstractClientPlayer
import net.minecraft.client.player.LocalPlayer
import net.minecraft.resources.ResourceLocation


object AnimationRegistry {
    fun register() {
        PlayerAnimationFactory.ANIMATION_DATA_FACTORY.registerFactory(
            ResourceLocation(MODID, "animation"),
            42
        ) { player: AbstractClientPlayer? ->
            if (player is LocalPlayer) {
                //animationStack.addAnimLayer(42, testAnimation); //Add and save the animation container for later use.
                val testAnimation = ModifierLayer<IAnimation?>()

                testAnimation.addModifierBefore(SpeedModifier(0.5f)) //This will be slow
                //testAnimation.addModifierBefore(MirrorModifier(true)) //Mirror the animation
                return@registerFactory testAnimation
            }
            null
        }

        PlayerAnimationAccess.REGISTER_ANIMATION_EVENT.register(PlayerAnimationAccess.AnimationRegister { player: AbstractClientPlayer?, animationStack: AnimationStack? ->
            val layer = ModifierLayer<IAnimation?>()
            animationStack!!.addAnimLayer(69, layer)
            PlayerAnimationAccess.getPlayerAssociatedData(player!!).set(ResourceLocation(MODID, "test"), layer)
        })
    }
}
