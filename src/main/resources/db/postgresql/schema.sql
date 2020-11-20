
CREATE TABLE IF NOT EXISTS orders
(
	id SERIAL PRIMARY KEY,
	order_date DATE,
	total_price INTEGER,
	is_served BOOLEAN,
	is_paid BOOLEAN,
	table_id INTEGER
);

CREATE TABLE IF NOT EXISTS menus
(
	id SERIAL PRIMARY KEY,
	menu_name VARCHAR(30),
	price INTEGER,
	image_file BYTEA
);


SELECT setval(pg_get_serial_sequence('orders', 'id'), coalesce(max(id),0) + 1, false) FROM orders;
SELECT setval(pg_get_serial_sequence('menus', 'id'), coalesce(max(id),0) + 1, false) FROM menus;
