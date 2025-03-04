plugins {
    kotlin("jvm") version "1.9.0"
    id("org.jetbrains.compose") version "1.5.0"
    kotlin("plugin.serialization") version "1.9.0" 
}

group = "org.example"
version = "1.0-SNAPSHOT"
repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
    // maven central에 없는 패키지들을 위해 추가
    maven("https://androidx.dev/storage/compose-compiler/repository")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.compose.desktop:desktop:1.5.0")
    implementation("org.jetbrains.compose.material3:material3:1.5.0")
    implementation("org.jetbrains.compose.foundation:foundation:1.5.0")
    implementation("org.jetbrains.compose.runtime:runtime:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}