-- Insert products
insert into product (id, name, description, available_quantity, price, category_id)
values (nextval('product_seq'), 'Smartphone', 'Latest model with 6GB RAM', 50, 699.99, 1),
       (nextval('product_seq'), 'Laptop', '15-inch display with i7 processor', 30, 1199.99, 1),
       (nextval('product_seq'), 'Science Fiction Novel', 'A popular sci-fi book', 100, 15.99, 2),
       (nextval('product_seq'), 'T-shirt', '100% cotton t-shirt', 200, 9.99, 3),
       (nextval('product_seq'), 'Jeans', 'Comfortable blue jeans', 150, 39.99, 3),
       (nextval('product_seq'), 'Air Conditioner', '1.5 Ton Split AC', 25, 499.99, 4),
       (nextval('product_seq'), 'Microwave Oven', '800W microwave with grill function', 40, 149.99, 4),
       (nextval('product_seq'), 'Action Figure', 'Superhero action figure', 300, 19.99, 5),
       (nextval('product_seq'), 'Board Game', 'Strategy board game for 2-4 players', 80, 29.99, 5),
       (nextval('product_seq'), 'Sofa Set', '3-seater sofa with cushions', 10, 899.99, 6),
       (nextval('product_seq'), 'Dining Table', '6-seater wooden dining table', 15, 499.99, 6),
       (nextval('product_seq'), 'Shampoo', 'Organic shampoo with natural ingredients', 120, 8.99, 7),
       (nextval('product_seq'), 'Electric Toothbrush', 'Rechargeable toothbrush with timer', 60, 39.99, 7),
       (nextval('product_seq'), 'Treadmill', 'Foldable treadmill with LCD display', 20, 299.99, 8),
       (nextval('product_seq'), 'Basketball', 'Official size and weight basketball', 100, 24.99, 8);
