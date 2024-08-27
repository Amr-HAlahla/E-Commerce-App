-- Insert demo data into the category table
insert into category (id, name, description)
values (nextval('category_seq'), 'Electronics', 'Devices and gadgets'),
       (nextval('category_seq'), 'Books', 'Fiction and non-fiction books'),
       (nextval('category_seq'), 'Clothing', 'Men, women, and kids clothing'),
       (nextval('category_seq'), 'Home Appliances', 'Household electronic appliances'),
       (nextval('category_seq'), 'Toys', 'Toys for children of all ages'),
       (nextval('category_seq'), 'Furniture', 'Home and office furniture'),
       (nextval('category_seq'), 'Beauty & Health', 'Beauty, personal care, and health products'),
       (nextval('category_seq'), 'Sports', 'Sports equipment and gear');
