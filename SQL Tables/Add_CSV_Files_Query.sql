-- Add the CSV Files to the Database by executing the following query.
COPY admins(username,hashedpassword,salt,fullname,createdby_admin) 
FROM 'C:\tmp\Admins.csv' DELIMITER ',' CSV HEADER;
COPY contentadmins(username,hashedpassword,salt,fullname,createdby_admin) 
FROM 'C:\tmp\Contentadmins.csv' DELIMITER ',' CSV HEADER;
COPY clients(username,hashedpassword,salt,fullname) 
FROM 'C:\tmp\Clients.csv' DELIMITER ',' CSV HEADER;
COPY movies(title,category,description) 
FROM 'C:\tmp\Movies.csv' DELIMITER ',' CSV HEADER;
COPY provoles(idmovie,start_date,end_date,ContentAdmin_id,cinemaid) 
FROM 'C:\tmp\Provoles.csv' DELIMITER ',' CSV HEADER;
COPY reservations(provoli_id,client_id) 
FROM 'C:\tmp\Reservations.csv' DELIMITER ',' CSV HEADER;
