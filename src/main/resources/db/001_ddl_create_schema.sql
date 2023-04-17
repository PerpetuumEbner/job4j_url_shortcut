CREATE TABLE IF NOT EXISTS urls
(
    id        SERIAL PRIMARY KEY,
    url       TEXT NOT NULL UNIQUE,
    short_url TEXT NOT NULL UNIQUE,
    counter   INT  NOT NULL
);

CREATE TABLE IF NOT EXISTS websites
(
    id       SERIAL PRIMARY KEY,
    website  TEXT NOT NULL UNIQUE,
    login    TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL
);