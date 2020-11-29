DROP TABLE IF EXISTS booked;

CREATE TABLE booked (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  title VARCHAR(250) NOT NULL,
  author VARCHAR(250) NOT NULL,
  pages INTEGER NOT NULL
);

INSERT INTO booked (title, author, pages) VALUES
  ('The Lord of the Rings: The Fellowship of the Ring', 'J.R.R. Tolkien', 423),
  ('Clean Code', 'Robert C. Martin', 431),
  ('Good Night Moon', 'Margaret Wise Brown', 32)