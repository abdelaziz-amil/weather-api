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
extra["springCloudVersion"] = "2023.0.3"
dependencyManagement{
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation ("org.springframework.boot:spring-boot-starter-jersey:3.3.4")
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa:3.3.4")
	compileOnly ("org.projectlombok:lombok:1.18.34")
	annotationProcessor("org.projectlombok:lombok:1.18.34")
	implementation("com.h2database:h2:2.3.232")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
	implementation("io.github.openfeign:feign-jackson")
	implementation("io.github.openfeign:feign-okhttp")
	implementation("com.squareup.okhttp3:okhttp:4.12.0")
	testImplementation("io.github.openfeign:feign-mock")

	// circuit breaker dependencies
	implementation("io.github.resilience4j:resilience4j-spring-boot3:2.2.0")
	implementation("io.github.resilience4j:resilience4j-feign:2.2.0")

	// redis dependencies
	implementation("org.springframework.boot:spring-boot-starter-cache:3.3.5")
	implementation("org.springframework.boot:spring-boot-starter-data-redis:3.3.5")

	testImplementation("io.github.openfeign:feign-mock")
	testImplementation("io.rest-assured:rest-assured:5.5.0")
	testImplementation("io.rest-assured:json-path:5.5.0")

}

tasks.withType<Test> {
	useJUnitPlatform()
}