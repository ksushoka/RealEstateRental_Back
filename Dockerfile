# syntax=docker/dockerfile:1.4
FROM bellsoft/liberica-openjdk-debian:17.0.8 AS builder
WORKDIR /application
COPY . .

# <-- `id` here MUST begin with `myapp-gradle` to satisfy BuildKit
RUN --mount=type=cache,id=myapp-gradle-root-grd,target=/root/.gradle \
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