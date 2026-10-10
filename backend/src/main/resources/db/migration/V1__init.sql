CREATE TABLE adherent (
    id                BIGSERIAL PRIMARY KEY,
    nom               VARCHAR(100) NOT NULL,
    email             VARCHAR(255) NOT NULL UNIQUE,
    mot_de_passe_hash VARCHAR(255) NOT NULL,
    role              VARCHAR(20)  NOT NULL CHECK (role IN ('ADMIN', 'ADHERENT')),
    cree_le           TIMESTAMPTZ  NOT NULL DEFAULT now()
);

CREATE TABLE materiel (
    id              BIGSERIAL PRIMARY KEY,
    nom             VARCHAR(150)   NOT NULL,
    categorie       VARCHAR(100)   NOT NULL,
    description     TEXT,
    unite_compteur  VARCHAR(10)    NOT NULL CHECK (unite_compteur IN ('HEURE', 'HECTARE')),
    compteur_actuel NUMERIC(12, 2) NOT NULL DEFAULT 0 CHECK (compteur_actuel >= 0),
    tarif_unitaire  NUMERIC(10, 2) CHECK (tarif_unitaire IS NULL OR tarif_unitaire >= 0),
    photo_key       VARCHAR(255),
    actif           BOOLEAN        NOT NULL DEFAULT TRUE
);
