import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")

    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)

    // https://mvnrepository.com/artifact/com.mysql/mysql-connector-j
    implementation("com.mysql:mysql-connector-j:8.1.0")

    //implementation("androidx.compose.material:material-*:1.1.0-alpha06")


    // https://mvnrepository.com/artifact/cafe.adriel.voyager/voyager-navigator
    runtimeOnly("cafe.adriel.voyager:voyager-navigator:1.0.0-rc05")

    // https://mvnrepository.com/artifact/cafe.adriel.voyager/voyager-navigator-desktop
    implementation("cafe.adriel.voyager:voyager-navigator-desktop:1.0.0-rc05")

    // TabNavigator
    implementation("cafe.adriel.voyager:voyager-tab-navigator:1.0.0-rc05")

    // https://mvnrepository.com/artifact/cafe.adriel.voyager/voyager-tab-navigator-desktop
    implementation("cafe.adriel.voyager:voyager-tab-navigator-desktop:1.0.0-rc05")

    // https://mvnrepository.com/artifact/cafe.adriel.voyager/voyager-transitions-desktop
    implementation("cafe.adriel.voyager:voyager-transitions-desktop:1.0.0-rc05")


}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "movieStore"
            packageVersion = "1.0.0"
        }
    }
}
