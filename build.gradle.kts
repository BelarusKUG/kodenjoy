import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin

plugins {
    kotlin("jvm").version("1.3.21").apply(false)
}

subprojects {
    apply<KotlinPlatformJvmPlugin>()
    apply<ApplicationPlugin>()

    repositories {
        jcenter()
        maven("https://github.com/codenjoyme/codenjoy-repo/raw/master/snapshots")
    }

    dependencies {
        val implementation by configurations
        val testImplementation by configurations
        val testRuntimeOnly by configurations

        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("com.codenjoy:engine:1.0.28")
        implementation("org.apache.logging.log4j:log4j-api")

        implementation(platform("org.apache.logging.log4j:log4j-bom:2.11.2"))
        testImplementation(platform("org.junit:junit-bom:5.4.0"))
        testRuntimeOnly(platform("org.junit:junit-bom:5.4.0"))

        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testImplementation("org.junit.jupiter:junit-jupiter-api")
        testImplementation("org.junit.jupiter:junit-jupiter-params")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    }
}

tasks {
    wrapper {
        gradleVersion = "5.3"
        distributionType = Wrapper.DistributionType.ALL
    }
}
