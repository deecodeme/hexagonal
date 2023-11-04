plugins {
    id("java")
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
}

group = "com.deecodeme.hexagonal"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    project(":account:domain")
    project(":account:application")
    project(":account:adapter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:4.9.1"))
    implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}