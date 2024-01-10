CREATE TABLE museum
(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NUll,
    location varchar(200) NOT NULL,
    phone varchar(11) NOT NULL,
    description varchar(300),
    primary key(id),
    UNIQUE(name)

);
INSERT INTO museum(
    name,location,phone,description

)VALUES ('Van Gogh','Museumplein 6, 1071 DJ Amsterdam','0205705200','Every day open:  9:00-18:00'),
        ('Rijksmuseum ','Cuypers Library · Rijksmuseum Research Library','0206747000','Every day open:  9:00-18:00');
