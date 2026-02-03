package com.betafoprhoton.bayonetcharge.client

import com.betafoprhoton.bayonetcharge.BayonetCharge.MODID
import com.betafoprhoton.bayonetcharge.server.PlayerStateManager.Companion.PlayerState.CHARGING
import com.mojang.blaze3d.platform.InputConstants
import dev.architectury.event.events.client.ClientTickEvent
import dev.architectury.networking.NetworkManager
import dev.architectury.registry.client.keymappings.KeyMappingRegistry
import io.netty.buffer.Unpooled
import net.minecraft.client.KeyMapping
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation

class KeyMappings {
    companion object {
        val BAYONET_CHARGE = KeyMapping(
            "key.bayonetcharge.bayonet_charge", // The translation key of the name shown in the Controls screen
            InputConstants.Type.KEYSYM, // This key mapping is for Keyboards by default
            InputConstants.KEY_C, // The default keycode
            "category.bayonetcharge.bayonet_charge" // The category translation key used to categorize in the Controls screen
        )

        fun register() {
            KeyMappingRegistry.register(BAYONET_CHARGE)

            ClientTickEvent.CLIENT_POST.register { minecraft: Minecraft? ->
                while (BAYONET_CHARGE.consumeClick()) {
                    if (minecraft == null || minecraft.player == null) break
                    val buf = FriendlyByteBuf(Unpooled.buffer())
                    buf.writeInt(1)
                    NetworkManager.sendToServer(ResourceLocation(MODID), buf)
                }
            }

            NetworkManager.registerReceiver(NetworkManager.Side.C2S, ResourceLocation(MODID)) {
                buf: FriendlyByteBuf?, context: NetworkManager.PacketContext? ->
                if (buf == null || context == null) return@registerReceiver
                val message = buf.readInt()
                when (message) {
                    1 -> {
                        if (!context.player.tags.contains(CHARGING.stringName))
                            context.player.addTag(CHARGING.stringName)
                    }
                }
            }
        }
    }
}