CREATE TABLE orders
(
    id   int  NOT NULL AUTO_INCREMENT,
    order_date date NOT NULL,
    customer_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customer(id)

);
CREATE TABLE order_item
(
    id    int  NOT NULL AUTO_INCREMENT,
    ticket_id int NOT NULL,
    amount int NOT NULL,
    price double NOT NULL,
    order_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (ticket_id) REFERENCES ticket(id),
    FOREIGN KEY (order_id) REFERENCES orders(id)
);