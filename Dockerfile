# syntax=docker/dockerfile:1

# Stage 1: tests + package entirely inside the image (reproducible, no host target/)
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw

COPY src ./src

# Cache Maven repository between builds (requires BuildKit: export DOCKER_BUILDKIT=1)
RUN --mount=type=cache,target=/root/.m2 \
    ./mvnw -B clean verify \
    && FAT_JAR="$(find target -maxdepth 1 -name '*.jar' ! -name '*.jar.original' | head -n1)" \
    && test -n "$FAT_JAR" \
    && cp "$FAT_JAR" /app/application.jar

# Stage 2: minimal JRE runtime
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

RUN useradd --create-home --shell /usr/sbin/nologin appuser
COPY --from=build /app/application.jar app.jar
RUN chown appuser:appuser app.jar
USER appuser

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
