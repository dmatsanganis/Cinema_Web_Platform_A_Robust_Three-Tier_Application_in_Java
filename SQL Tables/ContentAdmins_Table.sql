CREATE TABLE IF NOT EXISTS Contentadmins
(
  id SERIAL UNIQUE NOT NULL,
  username VARCHAR(255) UNIQUE NOT NULL,
  hashedpassword VARCHAR(255) NOT NULL ,
  salt VARCHAR(255) NOT NULL ,
  fullname VARCHAR(255) NOT NULL,
  createdby_admin INTEGER REFERENCES  Admins(id) NOT NULL,
  PRIMARY KEY (id)
);
