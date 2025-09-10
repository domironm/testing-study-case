-- V1__add_submission_and_author_table.sql

-- Create author table
CREATE TABLE IF NOT EXISTS author (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    mail VARCHAR(255) NOT NULL
);

-- Create submission table
CREATE TABLE IF NOT EXISTS submission (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    abstract_text VARCHAR(255),
    author_id INTEGER NOT NULL,
    CONSTRAINT fk_author FOREIGN KEY(author_id) REFERENCES author(id)
);
