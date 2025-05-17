CREATE TABLE pets (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(255) NOT NULL,
    idade INTEGER NOT NULL CHECK (idade <= 22),
    tipo VARCHAR(20) NOT NULL,
    raca VARCHAR(255) NOT NULL,
    peso DOUBLE PRECISION CHECK (peso >= 0.1 AND peso <= 60),
    criado_em TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),

    tutor_id UUID NOT NULL,
    endereco_id UUID,

    CONSTRAINT fk_tutor FOREIGN KEY (tutor_id) REFERENCES tutores(id) ON DELETE CASCADE,
    CONSTRAINT fk_endereco FOREIGN KEY (endereco_id) REFERENCES enderecos(id)
);