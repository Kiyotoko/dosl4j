plugins {
    kotlin("jvm") version "1.9.22"
    antlr
}

group = "org.dosl"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    implementation(kotlin("stdlib"))
    antlr("org.antlr:antlr4:4.5")
    testImplementation(kotlin("test"))
}
kotlin {
    jvmToolchain(11)
}

tasks.test {
    useJUnitPlatform()
}
tasks.compileKotlin {
    dependsOn(tasks.generateGrammarSource)
}
tasks.compileTestKotlin {
    dependsOn(tasks.generateTestGrammarSource)
}
tasks.generateGrammarSource {
    arguments.plusAssign(listOf("-visitor"))
}

