CREATE TABLE IF NOT EXISTS Reservations
(
  id SERIAL UNIQUE NOT NULL,
  provoli_id INTEGER REFERENCES Provoles(id) NOT NULL,
  client_id INTEGER REFERENCES Clients(id) NOT NULL,
  PRIMARY KEY(id)
);
