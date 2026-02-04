architectury {
    common(rootProject.property("enabled_platforms").toString().split(","))
}

loom {
    accessWidenerPath.set(file("src/main/resources/bayonetcharge.accesswidener"))
}

repositories {
    maven {
        name = "KosmX's maven"
        url = uri("https://maven.kosmx.dev/")
    }
}

dependencies {
    // We depend on fabric loader here to use the fabric @Environment annotations and get the mixin dependencies
    // Do NOT use other classes from fabric loader
    modImplementation("net.fabricmc:fabric-loader:${rootProject.property("fabric_loader_version")}")
    modImplementation("dev.kosmx.player-anim:player-animation-lib:${rootProject.property("player_anim_version")}")
        ?.let { include(it) }
    modImplementation("io.github.kosmx:bendy-lib:${project.property("bendylib_version")}")
        ?.let { include(it) }
    // Remove the next line if you don't want to depend on the API
    modApi("dev.architectury:architectury:${rootProject.property("architectury_version")}")
}