package com.betafoprhoton.bayonetcharge.animation

import com.betafoprhoton.bayonetcharge.BayonetCharge.MODID
import dev.kosmx.playerAnim.api.layered.IAnimation
import dev.kosmx.playerAnim.api.layered.ModifierLayer
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationFactory
import net.minecraft.client.player.AbstractClientPlayer
import net.minecraft.resources.ResourceLocation


object AnimationRegistry {
    fun register() {
        PlayerAnimationFactory.ANIMATION_DATA_FACTORY.registerFactory(
            ResourceLocation(MODID, "back"),
            42,
            AnimationRegistry::registerPlayerAnimation
        )
    }

    private fun registerPlayerAnimation(player: AbstractClientPlayer?): IAnimation {
        //This will be invoked for every new player
        return ModifierLayer<IAnimation?>()
    }
}
