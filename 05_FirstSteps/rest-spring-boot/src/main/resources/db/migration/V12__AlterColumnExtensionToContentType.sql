ALTER TABLE files RENAME COLUMN extension TO contentType;

ALTER TABLE files MODIFY COLUMN contentType VARCHAR(100);