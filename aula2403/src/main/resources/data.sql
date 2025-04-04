CREATE TABLE veiculo (
                         id SERIAL PRIMARY KEY,
                         marca VARCHAR(255) NOT NULL,
                         modelo VARCHAR(255),
                         preco DOUBLE PRECISION,
                         anoFabricacao INTEGER
);
