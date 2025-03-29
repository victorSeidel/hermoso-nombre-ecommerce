CREATE DATABASE hermosoNombreDB;
USE hermosoNombreDB;

CREATE TABLE Iten
(
	id INT NOT NULL PRIMARY KEY,
    title VARCHAR(55) NOT NULL,
    quantity INT NOT NULL,
    unityPrice FLOAT NOT NULL
);

CREATE TABLE Payment 
(
	id INT NOT NULL PRIMARY KEY,
    iten_id INT NOT NULL,
    CONSTRAINT fk_item_payment FOREIGN KEY (iten_id) REFERENCES Iten(id)
);

CREATE TABLE Category 
(
	id INT NOT NULL PRIMARY KEY,
    category VARCHAR(55) NOT NULL,
    iten_id INT NOT NULL,
    CONSTRAINT fk_item_category FOREIGN KEY (iten_id) REFERENCES Iten(id)
);