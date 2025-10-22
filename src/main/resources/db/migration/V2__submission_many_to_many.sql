-- Migration to switch Submission.author (ManyToOne) to Submission.authors (ManyToMany)
-- Drop old FK and column, create join table.

-- 1. Drop foreign key constraint if exists (name used in V1 was fk_author)
ALTER TABLE submission DROP CONSTRAINT IF EXISTS fk_author;

-- 2. Drop old column
ALTER TABLE submission DROP COLUMN IF EXISTS author_id;

-- 3. Create join table for many-to-many relation
CREATE TABLE IF NOT EXISTS submission_author (
    submission_id INTEGER NOT NULL REFERENCES submission(id) ON DELETE CASCADE,
    author_id INTEGER NOT NULL REFERENCES author(id) ON DELETE CASCADE,
    PRIMARY KEY (submission_id, author_id)
);

-- (Optional) If you had existing data and wanted to migrate it, you would insert rows
-- into submission_author before dropping the column. Not needed here if data can be discarded.

