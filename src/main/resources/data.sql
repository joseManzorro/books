CREATE TABLE IF NOT EXISTS author (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50)  NOT NULL
);

CREATE TABLE IF NOT EXISTS book (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    description VARCHAR(250),
    author_id INTEGER NOT NULL,
    FOREIGN KEY (author_id) REFERENCES authors(id)
);

INSERT INTO author (id, first_name, last_name) VALUES
  (1, 'Joanne', 'Rowling'),
  (2, 'David', 'Walliams'),
  (3, 'Douglas', 'Stuart'),
  (4, 'Dick', 'Strawbridge'),
  (5, 'Lee', 'Child');

INSERT INTO book (id, title, description, author_id) VALUES
  (1, 'The Ickabog', 'A fabulously imagined fairytale about scary monsters, wicked courtiers and resourceful children, J.K. Rowling’s standalone fable is adorned with illustrations submitted by young readers.', 1),
  (2, 'Code Name Bananas', 'David Walliams and Tony Ross strike gold again with a wildly entertaining wartime romp featuring Nazi plots, escaped gorillas and a plucky young boy and his zookeeper uncle.', 2),
  (3, 'Shuggie Bain', 'An uncompromising yet tender and warmly witty exploration of love, pride and poverty, Shuggie Bain charts the endeavours of its eponymous protagonist – an ambitious and fastidious boy from a dire mining town with a thirst for a better life.', 3),
  (4, 'A Year at the Chateau', 'Mapping Dick and Angel Strawbridge’s extraordinary project of bringing an abandoned French castle back to life, A Year at the Chateau tells the complete story of the undertaking, first explored in the Channel 4 show Escape to the Chateau.', 4),
  (5, 'The Sentinel', 'Heralding a new dawn for Jack Reacher, The Sentinel is co-written by Lee Child and his younger brother Andrew - but never fear, our favourite moral avenger is still in muscular form, stepping in to right the wrongs done to a band of country musicians.', 5),
  (6, 'Harry Potter and the Deathly Hallows', 'Harry Potter and the Deathly Hallows is a fantasy novel written by British author J. K. Rowling and the seventh and final novel of the Harry Potter series.', 1);