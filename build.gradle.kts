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
    // jitpack 저장소 추가
    maven("https://jitpack.io")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.compose.desktop:desktop:1.5.0")
    implementation("org.jetbrains.compose.material3:material3:1.5.0")
    implementation("org.jetbrains.compose.foundation:foundation:1.5.0")
    implementation("org.jetbrains.compose.runtime:runtime:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    
    // Retrofit 및 OkHttp 관련 의존성
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // Serialization Converter는 kotlinx.serialization 사용 시 필요
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    
    // JWT 토큰 디코딩 (선택사항)
    implementation("com.auth0:java-jwt:4.3.0")
    
    // 설정 저장을 위한 라이브러리 (토큰 저장용, DataStore 대체)
    implementation("com.russhwolf:multiplatform-settings:1.0.0")
    
    // ViewModel 라이브러리 변경
    // implementation("io.github.thechance101:compose-viewmodel:0.2.0") // 오류 발생 라이브러리
    implementation("org.jetbrains.compose.runtime:runtime-saveable:1.5.0") // 상태 저장용
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}