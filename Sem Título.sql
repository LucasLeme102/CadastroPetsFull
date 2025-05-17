select * from tutores
select * from pets
select * from enderecos

ALTER TABLE tutores
ADD CONSTRAINT unique_nome UNIQUE (nome);