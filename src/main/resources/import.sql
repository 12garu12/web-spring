/* Tabla Clientes*/
INSERT INTO clientes(id, first_name, last_name, email, create_at) VALUES (1, 'Andrés', 'Guzman', 'profesor@bolsadeideas.com', '2017-08-29');
INSERT INTO clientes(id, first_name, last_name, email, create_at) VALUES (2, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-29');
INSERT INTO clientes(id, first_name, last_name, email, create_at) VALUES (3, 'Camilo', 'Bermudes', 'caber@gmail.com', '2020-08-29');
INSERT INTO clientes(id, first_name, last_name, email, create_at) VALUES (4, 'Anna', 'Santillana', 'annasantillana@gmail.com', '2021-08-29');

/* Tabla productos */
INSERT INTO productos (id, nombre, precio, create_at) VALUES(1, 'Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (id, nombre, precio, create_at) VALUES(2, 'Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO productos (id, nombre, precio, create_at) VALUES(3, 'Apple iPod shuffle', 1499990, NOW());
INSERT INTO productos (id, nombre, precio, create_at) VALUES(4, 'Sony Notebook Z110', 37990, NOW());
INSERT INTO productos (id, nombre, precio, create_at) VALUES(5, 'Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO productos (id, nombre, precio, create_at) VALUES(6, 'Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO productos (id, nombre, precio, create_at) VALUES(7, 'Mica Comoda 5 Cajones', 299990, NOW());

/* Tabla facturas */
INSERT INTO facturas (id, descripcion, observacion, cliente_id, create_at) VALUES(1, 'Factura equipos de oficina', null, 1, NOW());
INSERT INTO facturas (id, descripcion, observacion, cliente_id, create_at) VALUES(2, 'Factura Bicicleta', 'Alguna nota importante!', 1, NOW());

/* Tabla facturas_items*/
INSERT INTO facturas_items (id, cantidad, factura_id, producto_id) VALUES(1, 1, 1, 1);
INSERT INTO facturas_items (id, cantidad, factura_id, producto_id) VALUES(2, 2, 1, 4);
INSERT INTO facturas_items (id, cantidad, factura_id, producto_id) VALUES(3, 1, 1, 5);
INSERT INTO facturas_items (id, cantidad, factura_id, producto_id) VALUES(4, 1, 1, 7);
INSERT INTO facturas_items (id, cantidad, factura_id, producto_id) VALUES(5, 3, 2, 6);
