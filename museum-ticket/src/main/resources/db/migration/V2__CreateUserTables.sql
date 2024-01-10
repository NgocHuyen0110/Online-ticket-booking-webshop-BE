CREATE TABLE customer (
                          id         int  NOT NULL AUTO_INCREMENT,
                          full_name  varchar(200) NOT NULL,
                          PRIMARY KEY (id)
);

INSERT INTO customer (full_name)
VALUES ('Huyen Thai');

CREATE TABLE admin (
                       id         int  NOT NULL AUTO_INCREMENT,
                       full_name  varchar(200) NOT NULL,
                       date_of_birth date,
                       address varchar(200),
                       PRIMARY KEY (id)
);

CREATE TABLE user (
                      id         int          NOT NULL AUTO_INCREMENT,
                      email      varchar(50)  NOT NULL,
                      password   varchar(200) NOT NULL,
                      customer_id int NULL,
                      admin_id   int NULL,
                      PRIMARY KEY (id),
                      UNIQUE (email),
                      FOREIGN KEY (customer_id) REFERENCES customer (id),
                      FOREIGN KEY (admin_id) REFERENCES admin (id)
);

INSERT INTO user (email, password, customer_id, admin_id)
VALUES ('huyen@gmail.com', '$10$fautN.N0ry1gVhK2EPMCveWdim4wGbOxQXL/RZ.Nzkh3ka7L4rYA6', 1, NULL);

CREATE TABLE user_role (
                           id        int         NOT NULL AUTO_INCREMENT,
                           user_id   int         NOT NULL,
                           role_name varchar(50) NOT NULL,
                           PRIMARY KEY (id),
                           UNIQUE (user_id),
                           FOREIGN KEY (user_id) REFERENCES user (id)
);

INSERT INTO user_role (user_id, role_name)
VALUES (1, 'CUSTOMER');
