# 1. Używamy lekkiego obrazu z Javą 21
FROM eclipse-temurin:21-jdk-alpine

# 2. Tworzymy folder wewnątrz kontenera, gdzie będą nasze pliki
WORKDIR /app

# 3. Kopiujemy pliki naszego projektu do kontenera
COPY . .

# 4. Nadajemy uprawnienia do uruchomienia Gradle'a
RUN chmod +x gradlew

# 5. Komenda, która wykona się po starcie kontenera
CMD ["./gradlew", "test"]