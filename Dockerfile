# 1. Baza: Obraz z Javą i Gradlem
FROM gradle:8.5-jdk17 AS build
WORKDIR /app

# 2. CACHE: Kopiujemy tylko pliki konfiguracyjne
COPY build.gradle settings.gradle ./

# 3. POBIERANIE: Ściągamy biblioteki (tak lepiej niż kopiować cały folder projektu, bo Docker to zapamięta i przy ponownym uruchomieniu po jakiejś zmianie w kodzie Dockerfile będzie działał szybciej bo nie będzie musiał kopiować znowu wszystkiego)
RUN gradle dependencies --no-daemon

# 4. KOD: Dopiero teraz kopiujemy kod źródłowy (bo zmienia się najczęściej, więc tylko on wtedy będzie kopiowany, a nie wszystko , bo dependencies będą już w pamięci dopóki czegoś w nich nie zmienimy)
COPY src ./src

# 5. START: Uruchamiamy testy
CMD ["gradle", "test", "--no-daemon"]