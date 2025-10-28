-- ==============================
--   CREATION DE LA BASE
-- ==============================
DROP TABLE IF EXISTS apprentices;
DROP TABLE IF EXISTS visits;
DROP TABLE IF EXISTS missions;
DROP TABLE IF EXISTS evaluations;
DROP TABLE IF EXISTS reports;
DROP TABLE IF EXISTS oral_exams;
DROP TABLE IF EXISTS masters;
DROP TABLE IF EXISTS companies;

CREATE DATABASE IF NOT EXISTS apprentices_db;
USE apprentices_db;

-- ==============================
--   TABLE : companies
-- ==============================
CREATE TABLE companies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    social_reason VARCHAR(255),
    address VARCHAR(255),
    access_information TEXT
);

-- ==============================
--   TABLE : masters
-- ==============================
CREATE TABLE masters (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    last_name VARCHAR(255),
    first_name VARCHAR(255),
    position VARCHAR(255),
    email VARCHAR(255),
    phone_number VARCHAR(255),
    comments TEXT
);

-- ==============================
--   TABLE : oral_exams
-- ==============================
CREATE TABLE oral_exams (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date DATE,
    grade DOUBLE,
    comments TEXT
);

-- ==============================
--   TABLE : reports
-- ==============================
CREATE TABLE reports (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    theme VARCHAR(255),
    grade DOUBLE,
    comments TEXT
);

-- ==============================
--   TABLE : evaluations
-- ==============================
CREATE TABLE evaluations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    oral_id BIGINT,
    report_id BIGINT,
    CONSTRAINT fk_evaluation_oral FOREIGN KEY (oral_id) REFERENCES oral_exams(id),
    CONSTRAINT fk_evaluation_report FOREIGN KEY (report_id) REFERENCES reports(id)
);

-- ==============================
--   TABLE : missions
-- ==============================
CREATE TABLE missions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    keywords VARCHAR(255),
    profession VARCHAR(255),
    comments TEXT
);

-- ==============================
--   TABLE : visits
-- ==============================
CREATE TABLE visits (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date DATE,
    format VARCHAR(50),
    comments TEXT
);

-- ==============================
--   TABLE : apprentices
-- ==============================
CREATE TABLE apprentices (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    program VARCHAR(255),
    academic_year VARCHAR(255),
    major VARCHAR(50),
    last_name VARCHAR(255),
    first_name VARCHAR(255),
    email VARCHAR(255),
    phone_number VARCHAR(255),
    company_id BIGINT,
    masters BIGINT,
    mission_id BIGINT,
    visit_id BIGINT,
    evaluation_id BIGINT,
    comments TEXT,
    tutor_feedback TEXT,
    CONSTRAINT fk_apprentice_company FOREIGN KEY (company_id) REFERENCES companies(id),
    CONSTRAINT fk_apprentice_master FOREIGN KEY (masters) REFERENCES masters(id),
    CONSTRAINT fk_apprentice_mission FOREIGN KEY (mission_id) REFERENCES missions(id),
    CONSTRAINT fk_apprentice_visit FOREIGN KEY (visit_id) REFERENCES visits(id),
    CONSTRAINT fk_apprentice_evaluation FOREIGN KEY (evaluation_id) REFERENCES evaluations(id)
);


USE apprentices_db;

-- ==============================
--   ENTREPRISES
-- ==============================
INSERT INTO companies (social_reason, address, access_information)
VALUES
('TechCorp', '123 Rue de Paris, Lyon', 'Entrée par le portail principal'),
('InnovShoes', '45 Avenue du Cuir, Marseille', 'Bâtiment B, étage 2');

-- ==============================
--   MAÎTRES D’APPRENTISSAGE
-- ==============================
INSERT INTO masters (last_name, first_name, position, email, phone_number, comments)
VALUES
('Dupont', 'Jean', 'Chef de projet', 'jean.dupont@techcorp.com', '0601020304', 'Très bon contact avec les étudiants'),
('Martin', 'Sophie', 'Responsable R&D', 'sophie.martin@innovshoes.com', '0611223344', 'Experte dans son domaine');

-- ==============================
--   ORALS
-- ==============================
INSERT INTO oral_exams (date, grade, comments)
VALUES
('2025-06-15', 15.5, 'Bonne présentation'),
('2025-06-18', 14.0, 'Contenu satisfaisant');

-- ==============================
--   REPORTS
-- ==============================
INSERT INTO reports (theme, grade, comments)
VALUES
('Développement d’une API', 16.0, 'Excellent travail technique'),
('Analyse des signaux vocaux', 15.0, 'Bonne approche scientifique');

-- ==============================
--   EVALUATIONS
-- ==============================
INSERT INTO evaluations (oral_id, report_id)
VALUES
(1, 1),
(2, 2);

-- ==============================
--   MISSIONS
-- ==============================
INSERT INTO missions (keywords, profession, comments)
VALUES
('API, SpringBoot, REST', 'Développeur backend', 'Participation active au projet'),
('IA, Python, Audio', 'Data scientist', 'Très bon niveau en traitement du signal');

-- ==============================
--   VISITES
-- ==============================
INSERT INTO visits (date, format, comments)
VALUES
('2025-04-10', 'FACE_TO_FACE', 'Visite sur site avec le maître d’apprentissage'),
('2025-04-12', 'VIDEOCONFERENCE', 'Échange à distance avec le tuteur EFREI');

-- ==============================
--   APPRENTIS
-- ==============================
INSERT INTO apprentices (
    program, academic_year, major, last_name, first_name, email, phone_number,
    company_id, masters, mission_id, visit_id, evaluation_id, comments, tutor_feedback
)
VALUES
('Cycle Ingénieur', '2024-2025', 'I2', 'Bernard', 'Lucas', 'lucas.bernard@efrei.fr', '0707070707',
 1, 1, 1, 1, 1, 'Apprenti motivé et autonome', 'Bon suivi de mission'),

('Cycle Ingénieur', '2024-2025', 'I2', 'Nguyen', 'Emma', 'emma.nguyen@efrei.fr', '0808080808',
 2, 2, 2, 2, 2, 'Travail sérieux et appliqué', 'Bonne progression cette année');
