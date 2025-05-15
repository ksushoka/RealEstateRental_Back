# syntax=docker/dockerfile:1.4
# ← enable BuildKit features!

FROM bellsoft/liberica-openjdk-debian:17.0.8 AS builder
WORKDIR /application
COPY . .

# add an id to your cache mount
RUN --mount=type=cache,id=gradle-cache,target=/root/.gradle \
    chmod +x gradlew && ./gradlew clean bootJar -x test

FROM bellsoft/liberica-openjdk-debian:17.0.8 AS layers
WORKDIR /application
COPY --from=builder /application/build/libs/*SNAPSHOT.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM bellsoft/liberica-openjdk-debian:17.0.8
RUN useradd -ms /bin/bash spring-user
USER spring-user
WORKDIR /application

COPY --from=layers /application/dependencies/    ./
COPY --from=layers /application/spring-boot-loader ./
COPY --from=layers /application/snapshot-dependencies ./
COPY --from=layers /application/application/      ./

ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]
