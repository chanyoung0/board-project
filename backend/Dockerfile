# 1단계: 빌드 (Gradle + JDK 17 이미지에서 jar 생성)
FROM gradle:8.10.2-jdk17 AS build
WORKDIR /workspace
COPY build.gradle settings.gradle ./
COPY src ./src
RUN gradle bootJar --no-daemon

# 2단계: 실행 (JRE만 있는 가벼운 이미지에 jar만 복사)
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/build/libs/*.jar app.jar
ENV DB_PATH=/data/board
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
