-- Create the 'Order' table
CREATE TABLE Order_Table (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_number VARCHAR(255)
);

-- Create the 'OrderLineItems' table
CREATE TABLE Order_Line_Items (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sku_code VARCHAR(255),
    price DECIMAL(10, 2),
    quantity INT,
    order_id INT,
    FOREIGN KEY (order_id) REFERENCES Order_Table(id) ON DELETE CASCADE
);

-- Insert data into 'Order' table
INSERT INTO Order_Table (order_number) VALUES
    ('ORD123'),
    ('ORD456'),
    ('ORD789');

-- Insert data into 'OrderLineItems' table
INSERT INTO Order_Line_Items (sku_code, price, quantity, order_id) VALUES
    ('SKU001', 10.00, 2, 1),
    ('SKU002', 15.50, 3, 1),
    ('SKU003', 5.75, 1, 1),
    ('SKU004', 20.00, 1, 2),
    ('SKU005', 8.25, 4, 3),
    ('SKU006', 12.75, 2, 3);