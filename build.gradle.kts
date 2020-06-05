import org.gradle.api.tasks.wrapper.Wrapper

group = "com.github.nicholashauschild"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.3.0"
}

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib"))
    testCompile("junit", "junit", "4.12")
}

task<Wrapper>("wrapper") {
    gradleVersion = "6.5"
    distributionType = Wrapper.DistributionType.ALL
}
