plugins {
    id("java")
    id("me.champeau.jmh") version "0.7.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    jmh ("org.openjdk.jmh:jmh-core:1.36")
    jmh ("org.openjdk.jmh:jmh-generator-annprocess:1.36")
    implementation ("it.unimi.dsi:fastutil:8.5.9")
    jmhAnnotationProcessor ("org.openjdk.jmh:jmh-generator-annprocess:1.36")
}

tasks.test {
    useJUnitPlatform()
}