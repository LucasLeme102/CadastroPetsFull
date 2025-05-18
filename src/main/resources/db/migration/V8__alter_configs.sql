ALTER TABLE tutores ADD COLUMN endereco_id UUID;
ALTER TABLE tutores ADD CONSTRAINT fk_endereco FOREIGN KEY (endereco_id) REFERENCES enderecos(id);