-- Inserting dummy data into the Product table
INSERT INTO Product (id, name, description, price, creation_time, last_update_time, category, version)
VALUES
    ('123', 'product1', 'Sample description for Product 1', 1000, 1668560400, 1684567200, 'OTHERS', '1.0'),
    ('124', 'product2', 'Sample description for Product 2', 1500, 1668561400, 1684577200, 'OTHERS', '1.0'),
    ('125', 'product3', 'Sample description for Product 3', 800, 1668562400, 1684587200, 'OTHERS', '1.0'),
    ('126', 'product4', 'Sample description for Product 4', 2000, 1668563400, 1684597200, 'OTHERS', '1.0'),
    ('127', 'product5', 'Sample description for Product 5', 1200, 1668564400, 1684607200, 'OTHERS', '1.0');
