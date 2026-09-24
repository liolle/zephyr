-- Use an identity provider in prod
CREATE TABLE users (
    niss VARCHAR(11) PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL
);

CREATE TABLE patients (
    niss VARCHAR(11) PRIMARY KEY,
    guardian_niss VARCHAR(11),

    CONSTRAINT fk_patient_user FOREIGN KEY (niss) REFERENCES users(niss),
    CONSTRAINT fk_patient_guardian FOREIGN KEY (guardian_niss) REFERENCES users(niss)
);

CREATE TABLE vaccine_stocks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    vaccine_name VARCHAR(100) NOT NULL,
    lot_name VARCHAR(50) NOT NULL,
    quantity_available INT NOT NULL,
    expiration_date DATE NOT NULL,

    CONSTRAINT uq_vaccine_lot UNIQUE (vaccine_name, lot_name)
);

CREATE TABLE vaccination_records (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_niss VARCHAR(11) NOT NULL,
    administrator_niss VARCHAR(11) NOT NULL,
    vaccine_stock_id BIGINT NOT NULL,
    administered_at DATETIME NOT NULL,

    CONSTRAINT fk_record_patient FOREIGN KEY (patient_niss) REFERENCES patients(niss),
    CONSTRAINT fk_record_administrator FOREIGN KEY (administrator_niss) REFERENCES users(niss),
    CONSTRAINT fk_record_stock FOREIGN KEY (vaccine_stock_id) REFERENCES vaccine_stocks(id)
);
