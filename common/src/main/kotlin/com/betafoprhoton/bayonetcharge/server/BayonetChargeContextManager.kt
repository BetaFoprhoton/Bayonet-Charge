package com.betafoprhoton.bayonetcharge.server

class BayonetChargeContextManager {
    val contexts: HashSet<BayonetChargeContext> = hashSetOf()
    companion object {
        val INSTANCE = BayonetChargeContextManager()
    }

    init {

    }
}