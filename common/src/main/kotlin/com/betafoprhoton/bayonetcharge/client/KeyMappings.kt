package com.betafoprhoton.bayonetcharge.client

import com.mojang.blaze3d.platform.InputConstants
import dev.architectury.registry.client.keymappings.KeyMappingRegistry
import net.minecraft.client.KeyMapping

class KeyMappings {
    companion object {
        val BAYONET_CHARGE = KeyMapping(
            "key.bayonetcharge.bayonet_charge", // The translation key of the name shown in the Controls screen
            InputConstants.Type.MOUSE, // This key mapping is for Keyboards by default
            InputConstants.MOUSE_BUTTON_MIDDLE, // The default keycode
            "category.bayonetcharge.bayonet_charge" // The category translation key used to categorize in the Controls screen
        )

        fun register() {
            KeyMappingRegistry.register(BAYONET_CHARGE)
        }
    }
}