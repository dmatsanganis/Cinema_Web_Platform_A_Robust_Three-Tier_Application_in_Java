CREATE TABLE IF NOT EXISTS Admins
(
  id SERIAL UNIQUE NOT NULL,
  username VARCHAR(255) UNIQUE NOT NULL,
  hashedpassword VARCHAR(255) NOT NULL ,
  salt VARCHAR(255) NOT NULL ,
  fullname VARCHAR(255) NOT NULL,
  createdby_admin INTEGER NOT NULL,
  PRIMARY KEY (id)
);
