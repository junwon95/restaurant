CREATE TABLE IF NOT EXISTS tables
(
	id SERIAL PRIMARY KEY,
    is_empty BOOLEAN
);

CREATE TABLE IF NOT EXISTS menus
(
	id SERIAL PRIMARY KEY,
	menu_name VARCHAR(30),
	price INTEGER,
	stock INTEGER,
	stock_price INTEGER,
	image_file BYTEA
);

CREATE TABLE IF NOT EXISTS orders
(
	id SERIAL PRIMARY KEY,
	order_date TIMESTAMP,
	total_price INTEGER,
	table_id INTEGER,
	is_cooked BOOLEAN,
	is_paid BOOLEAN,
	FOREIGN KEY (table_id) REFERENCES tables(id)
);

CREATE TABLE IF NOT EXISTS contents
(
	id SERIAL PRIMARY KEY,
	order_id INTEGER,
	menu_name VARCHAR(30),
	menu_count INTEGER,
	FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS sales
(
	id SERIAL PRIMARY KEY,
	order_id INTEGER,
	sale_date DATE,
	price INTEGER
);

CREATE TABLE IF NOT EXISTS total_sales
(
	id SERIAL PRIMARY KEY,
    total INTEGER
);


CREATE TABLE IF NOT EXISTS users
(
	id SERIAL PRIMARY KEY,
    email VARCHAR(30),
    password VARCHAR(30),
    role VARCHAR(30)
);

SELECT setval(pg_get_serial_sequence('tables', 'id'), coalesce(max(id),0) + 1, false) FROM tables;
SELECT setval(pg_get_serial_sequence('menus', 'id'), coalesce(max(id),0) + 1, false) FROM menus;
SELECT setval(pg_get_serial_sequence('orders', 'id'), coalesce(max(id),0) + 1, false) FROM orders;
SELECT setval(pg_get_serial_sequence('contents', 'id'), coalesce(max(id),0) + 1, false) FROM contents;
SELECT setval(pg_get_serial_sequence('sales', 'id'), coalesce(max(id),0) + 1, false) FROM sales;
SELECT setval(pg_get_serial_sequence('users', 'id'), coalesce(max(id),0) + 1, false) FROM users;
