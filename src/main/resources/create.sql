CREATE SEQUENCE visitors_seq;
CREATE SEQUENCE notes_seq;

CREATE TABLE visitors (
                          visitor_id BIGINT PRIMARY KEY DEFAULT nextval('visitors_seq'),
                          name VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          username VARCHAR(255) UNIQUE,
                          password VARCHAR(255) NOT NULL,
                          phone_number VARCHAR(255)
);

INSERT INTO visitors (name, email, username, password, phone_number) VALUES
                                                                         ('Nail Samigullin', 'nailchiksam@gmail.com', 'nailchiksam', 'qwerty123', '+88005553535'),
                                                                         ('John Cena', 'john.cena@gmail.com', 'johncena', 'johncena001', '+0987654321');

CREATE TABLE notes (
                       note_id BIGINT PRIMARY KEY DEFAULT nextval('notes_seq'),
                       visitor_id BIGINT REFERENCES visitors(visitor_id),
                       content TEXT,
                       creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);