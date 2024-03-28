plugins {
    java
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    id("io.freefair.lombok") version "5.3.3.3"  // 新增此行

}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:junit-jupiter")
    implementation ("org.projectlombok:lombok:1.18.20") // 新增此行

    //add dependencies as needed
}

tasks.withType<Test> {
    useJUnitPlatform()
}
