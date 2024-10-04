plugins {
	java
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

apply("./gradle/swagger.gradle.kts")

group = "com.ubo"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation ("org.springframework.boot:spring-boot-starter-jersey:3.3.4")
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa:3.3.4")
	compileOnly ("org.projectlombok:lombok:1.18.34")
	implementation("com.h2database:h2:2.3.232")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
