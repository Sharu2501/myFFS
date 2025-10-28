-- ==============================
--   ENTREPRISES
-- ==============================
INSERT INTO companies (social_reason, address, access_information) VALUES
('TechCorp', '123 Rue de Paris, Lyon', 'Entrée par le portail principal');

INSERT INTO companies (social_reason, address, access_information) VALUES
('InnovShoes', '45 Avenue du Cuir, Marseille', 'Bâtiment B, étage 2');

-- ==============================
--   MAÎTRES D’APPRENTISSAGE
-- ==============================
INSERT INTO masters (last_name, first_name, position, email, phone_number, comments) VALUES
('Dupont', 'Jean', 'Chef de projet', 'jean.dupont@techcorp.com', '0601020304', 'Très bon contact avec les étudiants');

INSERT INTO masters (last_name, first_name, position, email, phone_number, comments) VALUES
('Martin', 'Sophie', 'Responsable R&D', 'sophie.martin@innovshoes.com', '0611223344', 'Experte dans son domaine');

-- ==============================
--   ORALS
-- ==============================
INSERT INTO oral_exams (date, grade, comments) VALUES
('2025-06-15', 15.5, 'Bonne présentation');

INSERT INTO oral_exams (date, grade, comments) VALUES
('2025-06-18', 14.0, 'Contenu satisfaisant');

-- ==============================
--   REPORTS
-- ==============================
INSERT INTO reports (theme, grade, comments) VALUES
('Développement d’une API', 16.0, 'Excellent travail technique');

INSERT INTO reports (theme, grade, comments) VALUES
('Analyse des signaux vocaux', 15.0, 'Bonne approche scientifique');

-- ==============================
--   EVALUATIONS
-- ==============================
INSERT INTO evaluations (oral_id, report_id) VALUES
((SELECT id FROM oral_exams ORDER BY id LIMIT 1), (SELECT id FROM reports ORDER BY id LIMIT 1));

INSERT INTO evaluations (oral_id, report_id) VALUES
((SELECT id FROM oral_exams ORDER BY id DESC LIMIT 1), (SELECT id FROM reports ORDER BY id DESC LIMIT 1));

-- ==============================
--   MISSIONS
-- ==============================
INSERT INTO missions (keywords, profession, comments) VALUES
('API, SpringBoot, REST', 'Développeur backend', 'Participation active au projet');

INSERT INTO missions (keywords, profession, comments) VALUES
('IA, Python, Audio', 'Data scientist', 'Très bon niveau en traitement du signal');

-- ==============================
--   VISITES
-- ==============================
INSERT INTO visits (date, format, comments) VALUES
('2025-04-10', 'FACE_TO_FACE', 'Visite sur site avec le maître d’apprentissage');

INSERT INTO visits (date, format, comments) VALUES
('2025-04-12', 'VIDEOCONFERENCE', 'Échange à distance avec le tuteur EFREI');

-- ==============================
--   APPRENTIS
-- ==============================
INSERT INTO apprentices (
    program, academic_year, major, last_name, first_name, email, phone_number,
    company_id, masters, mission_id, visit_id, evaluation_id, comments, tutor_feedback
) VALUES (
    'Cycle Ingénieur', '2024-2025', 'I2', 'Bernard', 'Lucas', 'lucas.bernard@efrei.fr', '0707070707',
    (SELECT id FROM companies WHERE social_reason='TechCorp'),
    (SELECT id FROM masters WHERE last_name='Dupont'),
    (SELECT id FROM missions ORDER BY id LIMIT 1),
    (SELECT id FROM visits ORDER BY id LIMIT 1),
    (SELECT id FROM evaluations ORDER BY id LIMIT 1),
    'Apprenti motivé et autonome', 'Bon suivi de mission'
);

INSERT INTO apprentices (
    program, academic_year, major, last_name, first_name, email, phone_number,
    company_id, masters, mission_id, visit_id, evaluation_id, comments, tutor_feedback
) VALUES (
    'Cycle Ingénieur', '2024-2025', 'I2', 'Nguyen', 'Emma', 'emma.nguyen@efrei.fr', '0808080808',
    (SELECT id FROM companies WHERE social_reason='InnovShoes'),
    (SELECT id FROM masters WHERE last_name='Martin'),
    (SELECT id FROM missions ORDER BY id DESC LIMIT 1),
    (SELECT id FROM visits ORDER BY id DESC LIMIT 1),
    (SELECT id FROM evaluations ORDER BY id DESC LIMIT 1),
    'Travail sérieux et appliqué', 'Bonne progression cette année'
);
