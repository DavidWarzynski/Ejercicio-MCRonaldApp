DROP ALL OBJECTS DELETE FILES;

CREATE TABLE Empleado (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255)
);

CREATE TABLE Producto (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255),
    stockDisponible INT
);

CREATE TABLE ProductoCarta (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    producto_id BIGINT,
    precio DOUBLE,
    FOREIGN KEY (producto_id) REFERENCES Producto(id)
);

CREATE TABLE Pedido (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    identificador VARCHAR(255),
    empleado_id BIGINT,
    precioTotal DOUBLE,
    FOREIGN KEY (empleado_id) REFERENCES Empleado(id)
);

CREATE TABLE ProductoPedido (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    productoCarta_id BIGINT,
    pedido_id BIGINT,
    productAmount INT,
    FOREIGN KEY (productoCarta_id) REFERENCES ProductoCarta(id),
    FOREIGN KEY (pedido_id) REFERENCES Pedido(id)
);

-- Crear empleados
INSERT INTO Empleado (nombre) VALUES ('Juan'), ('María'), ('Carlos');

-- Crear productos
INSERT INTO Producto (nombre, stockDisponible) VALUES
('Producto A', 10),
('Producto B', 20),
('Producto C', 15);

-- Crear productos de carta
INSERT INTO ProductoCarta (producto_id, precio) VALUES
(1, 10.5),
(2, 15.75),
(3, 20.0);

-- Crear pedidos
INSERT INTO Pedido (identificador, empleado_id, precioTotal) VALUES
('Pedido 001', 1, 50.25),
('Pedido 002', 2, 30.0),
('Pedido 003', 3, 45.75);

-- Asignar productos a los pedidos
INSERT INTO ProductoPedido (productoCarta_id, pedido_id, productAmount) VALUES
(1, 1, 2),
(2, 1, 1),
(3, 2, 3),
(1, 3, 1),
(2, 3, 2);
