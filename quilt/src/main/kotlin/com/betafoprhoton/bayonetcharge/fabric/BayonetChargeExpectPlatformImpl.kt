package com.betafoprhoton.bayonetcharge.fabric

import org.quiltmc.loader.api.QuiltLoader
import java.nio.file.Path

object BayonetChargeExpectPlatformImpl {
    @JvmStatic // Jvm Static is required so that java can access it
    fun getConfigDirectory(): Path {
        return QuiltLoader.getConfigDir()
    }
}