CREATE TABLE Clientes(
	 Cedula INT PRIMARY KEY NOT NULL,
    NombreCliente TEXT NOT NULL,
    Email TEXT NOT NULL,
    Telefono INT NOT NULL
);

CREATE TABLE Usuarios (
	 IdUsuario INT PRIMARY KEY NOT NULL,
    Usuario TEXT NOT NULL,
    Contrasena TEXT NOT NULL
);

CREATE TABLE Viajes (
	 Cedula INT REFERENCES Clientes(Cedula),
    Destino TEXT NOT NULL,
    Fecha DATE NOT NULL,
    CantidadPersonas INT NOT NULL
);

CREATE TABLE Recordatorios (
	IdRecordatorios INT PRIMARY KEY NOT NULL,
    FechaRecordatorios DATE NOT NULL,
    Hora TIME NOT NULL,
    Descripcion TEXT,
    IdUsuario INT REFERENCES Usuarios(IdUsuario)
);