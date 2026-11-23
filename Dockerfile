# Build stage
FROM gradle:9.8.2-jdk21 AS build

WORKDIR /app

COPY . .

RUN gradle --no-daemon clean installDist


# Runtime stage
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/build/install/auth/ /app/

EXPOSE 8080

ENTRYPOINT ["/app/bin/auth"]
