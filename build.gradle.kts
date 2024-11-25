import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  id("com.github.ben-manes.versions")
  id("io.spring.dependency-management")
  id("org.springframework.boot")
  kotlin("jvm")
  kotlin("plugin.jpa")
  kotlin("plugin.spring")
}

val buildid: String by project

val hypersistenceVersion: String by project
val kotlinLoggingVersion: String by project

group = "de.mbur"
version = "0.0.1-SNAPSHOT"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("io.hypersistence:hypersistence-utils-hibernate-63:${hypersistenceVersion}")
  implementation("io.github.oshai:kotlin-logging-jvm:$kotlinLoggingVersion")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")


  runtimeOnly("com.mysql:mysql-connector-j")

  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
  testImplementation("org.springframework.boot:spring-boot-starter-test")

  testRuntimeOnly("com.h2database:h2")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
  compilerOptions {
    freeCompilerArgs.addAll("-Xjsr305=strict")
    jvmTarget.set(JvmTarget.JVM_21)
  }
}

allOpen {
  annotation("jakarta.persistence.Entity")
  annotation("jakarta.persistence.MappedSuperclass")
  annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
  useJUnitPlatform()
}

fun isNonStable(version: String): Boolean {
  val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
  val regex = "^[0-9,.v-]+(-r)?$".toRegex()
  val isStable = stableKeyword || regex.matches(version)
  return isStable.not()
}

// https://github.com/ben-manes/gradle-versions-plugin
tasks.withType<DependencyUpdatesTask> {
  rejectVersionIf {
    isNonStable(candidate.version)
  }
}
