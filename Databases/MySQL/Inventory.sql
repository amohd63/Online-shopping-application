-- Create the 'Inventory' table
CREATE TABLE Inventory (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sku_code VARCHAR(255),
    quantity INT
);

INSERT INTO Inventory (id, sku_code, quantity) VALUES
    (1, 'SKU001', 21),
    (2, 'SKU002', 31);