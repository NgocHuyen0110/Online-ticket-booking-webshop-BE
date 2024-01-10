CREATE TABLE ticket
(
    id         int  NOT NULL AUTO_INCREMENT,
    name  varchar(100) NOT NULL,
    price double NOT NULL,
    description varchar(400) NULL,
    quantity_per_day int NOT NULL,
    museum_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (museum_id) REFERENCES museum (id)
)
