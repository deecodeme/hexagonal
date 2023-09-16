plugins {
    id("java")
}

group = "com.deecodeme.hexagonal"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:3.12.4")
    testImplementation("org.mockito:mockito-junit-jupiter:3.12.4")
    implementation("org.assertj:assertj-core:3.21.0")
}

apply(from = "${rootDir}/libraries.gradle")

tasks.test {
    useJUnitPlatform()
}