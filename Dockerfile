# syntax=docker/dockerfile:1
#
# Multi-stage aligné avec la CI GitHub Actions :
#   - stage "build"  : compile + tests (verify) OU package seul si SKIP_TESTS=true (image finale en CI)
#   - stage runtime  : JRE + JAR

FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

ARG SKIP_TESTS=false

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw

COPY src ./src

# Cache Maven ; en CI l’image finale passe SKIP_TESTS=true (tests déjà exécutés sur l’étape build Docker).
RUN --mount=type=cache,target=/root/.m2 \
    set -eux; \
    if [ "${SKIP_TESTS}" = "true" ]; then \
      ./mvnw -B clean package -DskipTests; \
    else \
      ./mvnw -B clean verify; \
    fi; \
    FAT_JAR="$(find target -maxdepth 1 -name '*.jar' ! -name '*.jar.original' | head -n1)"; \
    test -n "${FAT_JAR}"; \
    cp "${FAT_JAR}" /app/application.jar

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

RUN useradd --create-home --shell /usr/sbin/nologin appuser
COPY --from=build /app/application.jar app.jar
RUN chown appuser:appuser app.jar
USER appuser

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
