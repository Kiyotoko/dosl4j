plugins {
    kotlin("jvm") version "1.9.22"
    antlr
    `maven-publish`
}

group = "org.dosl"
version = "0.1.0"

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
publishing {
    publications {
        create<MavenPublication>("github") {
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "github"
            url = uri("https://maven.pkg.github.com/Kiyotoko/dosl4j")
        }
    }
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