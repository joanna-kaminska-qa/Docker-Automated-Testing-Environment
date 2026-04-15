CREATE TABLE IF NOT EXISTS habits (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    target_per_week INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Dodaj jeden rekord na start, żeby sprawdzić czy działa
INSERT INTO habits (name, target_per_week) VALUES ('Testowy nawyk', 3);