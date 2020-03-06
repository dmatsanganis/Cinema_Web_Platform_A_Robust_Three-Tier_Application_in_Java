CREATE TABLE IF NOT EXISTS Provoles --Views Database's Table Query.
(
    id SERIAL UNIQUE NOT NULL,
    idmovie INTEGER REFERENCES Movies(id) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    ContentAdmin_id INTEGER REFERENCES ContentAdmins(id) NOT NULL,
    cinemaid INTEGER REFERENCES Cinemas(id) NOT NULL,
    PRIMARY KEY (id)
);
