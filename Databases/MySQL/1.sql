
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