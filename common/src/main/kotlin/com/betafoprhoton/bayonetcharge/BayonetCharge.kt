package com.betafoprhoton.bayonetcharge

import com.betafoprhoton.bayonetcharge.BayonetChargeExpectPlatform.getConfigDirectory
import com.betafoprhoton.bayonetcharge.animation.AnimationRegistry
import com.betafoprhoton.bayonetcharge.client.ClientAnimationManager
import com.betafoprhoton.bayonetcharge.client.KeyMappings
import com.betafoprhoton.bayonetcharge.server.BayonetChargeContextManager
import dev.architectury.event.events.client.ClientLifecycleEvent
import dev.architectury.registry.CreativeTabRegistry
import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

object BayonetCharge {
    const val MODID = "bayonetcharge"

    private val createModeTabs = DeferredRegister.create(MODID, Registries.CREATIVE_MODE_TAB)
    val exampleTab: RegistrySupplier<CreativeModeTab> = createModeTabs.register("example_tab") {
        CreativeTabRegistry.create(Component.translatable("category.bayonetcharge")) {
            ItemStack(exampleItem.get())
        }
    }

    private val items = DeferredRegister.create(MODID, Registries.ITEM)
    val exampleItem: RegistrySupplier<Item> = items.register(
        "example_item"
    ) {
        Item(
            Item.Properties().`arch$tab`(exampleTab) // DON'T CALL GET ON exampleTab HERE
        )
    }

    fun init() {
        createModeTabs.register()
        items.register()
        BayonetChargeContextManager.register()
        ClientLifecycleEvent.CLIENT_SETUP.register {
            KeyMappings.register()
            //AnimationRegistry.register()
        }
        ClientLifecycleEvent.CLIENT_LEVEL_LOAD.register {
            //ClientAnimationManager.register()
        }
        println("CONFIG DIR: ${getConfigDirectory().toAbsolutePath().normalize()}")
    }
}