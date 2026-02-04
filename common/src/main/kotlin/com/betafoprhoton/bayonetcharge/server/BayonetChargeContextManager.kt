package com.betafoprhoton.bayonetcharge.server

import dev.architectury.event.events.common.PlayerEvent
import dev.architectury.event.events.common.TickEvent

class BayonetChargeContextManager {
    private val contexts: HashSet<BayonetChargeContext> = hashSetOf()

    fun tick() {
        contexts.forEach {
            it.tick()
        }
    }

    companion object {
        val INSTANCE = BayonetChargeContextManager()

        fun register() {
            PlayerEvent.PLAYER_JOIN.register {
                INSTANCE.contexts.add(BayonetChargeContext(it))
            }

            PlayerEvent.PLAYER_QUIT.register {
                INSTANCE.contexts.remove(INSTANCE.contexts.find { context -> context.player == it })
            }

            TickEvent.PLAYER_POST.register {
                INSTANCE.tick()
            }
        }
    }
}