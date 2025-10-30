-- ==============================
--   ENTREPRISES
-- ==============================
INSERT INTO companies (social_reason, address, access_information) VALUES ('TechCorp', '123 Rue de Paris, Lyon', 'Entrée par le portail principal'), ('InnovShoes', '45 Avenue du Cuir, Marseille', 'Bâtiment B, étage 2'), ('DataSolutions', '12 Boulevard Voltaire, Toulouse', 'Interphone 12B'), ('GreenSoft', '78 Rue Verte, Lille', 'Parking visiteur accessible'), ('CyberLink', '5 Rue du Numérique, Bordeaux', 'Badge requis à l’accueil');

-- ==============================
--   MAÎTRES D’APPRENTISSAGE
-- ==============================
INSERT INTO masters (last_name, first_name, position, email, phone_number, comments) VALUES ('Dupont', 'Jean', 'Chef de projet', 'jean.dupont@techcorp.com', '0601020304', 'Très bon contact avec les étudiants'),('Martin', 'Sophie', 'Responsable R et D', 'sophie.martin@innovshoes.com', '0611223344','Experte dans son domaine'),('Durand', 'Paul', 'Tech Lead', 'paul.durand@datasolutions.com', '0622334455','Excellent accompagnement technique'), ('Morel', 'Clara', 'Manager IT', 'clara.morel@greensoft.com', '0633445566','Toujours disponible pour ses apprentis'), ('Rossi', 'Marco', 'Architecte logiciel', 'marco.rossi@cyberlink.com', '0644556677','Très exigeant mais pédagogue');

-- ==============================
--   ORALS
-- ==============================
INSERT INTO oral_exams (date, grade, comments) VALUES ('2025-06-15', 15.5, 'Bonne présentation'), ('2025-06-18', 14.0, 'Contenu satisfaisant'),('2025-06-20', 16.0, 'Très bon niveau technique'),('2025-06-22', 17.5, 'Excellente maîtrise du sujet'),('2025-06-25', 13.5, 'Peut mieux structurer son discours');

-- ==============================
--   REPORTS
-- ==============================
INSERT INTO reports (theme, grade, comments) VALUES ('Développement d’une API', 16.0, 'Excellent travail technique'),('Analyse des signaux vocaux', 15.0, 'Bonne approche scientifique'),('Optimisation d’un moteur IA', 17.0, 'Innovant et bien documenté'),('Sécurisation d’applications web', 18.0, 'Présentation claire et pertinente'),('Déploiement cloud', 14.0, 'Projet bien mené mais à approfondir');

-- ==============================
--   EVALUATIONS
-- ==============================
INSERT INTO evaluations (oral_id, report_id) VALUES (1, 1), (2, 2),(3, 3),(4, 4),(5, 5);

-- ==============================
--   MISSIONS
-- ==============================
INSERT INTO missions (keywords, profession, comments) VALUES ('API, SpringBoot, REST', 'Développeur backend', 'Participation active au projet'), ('IA, Python, Audio', 'Data scientist', 'Très bon niveau en traitement du signal'),('Docker, Kubernetes, CI/CD', 'DevOps Engineer', 'Mise en place de pipelines automatisés'),('Cybersecurity, Pentesting', 'Ingénieur sécurité', 'Bonne rigueur dans les tests'),('Cloud, AWS, Terraform', 'Cloud Architect', 'Excellente compréhension des architectures distribuées');

-- ==============================
--   VISITES
-- ==============================
INSERT INTO visits (date, format, comments) VALUES ('2025-04-10', 'FACE_TO_FACE', 'Visite sur site avec le maître d’apprentissage'),('2025-04-12', 'VIDEOCONFERENCE', 'Échange à distance avec le tuteur EFREI'), ('2025-04-15', 'FACE_TO_FACE', 'Bon retour sur le déroulement de la mission'), ('2025-04-17', 'VIDEOCONFERENCE', 'Rappel des objectifs de fin d’année'),('2025-04-20', 'FACE_TO_FACE', 'Rencontre sur site avec présentation des résultats');

-- ==============================
--   APPRENTIS
-- ==============================
INSERT INTO apprentices (program, academic_year, major, last_name, first_name, email, phone_number, company_id, master_id, mission_id, visit_id, evaluation_id, comments, tutor_feedback) VALUES ('Cycle Ingénieur', '2024-2025', 'I2', 'Bernard', 'Lucas', 'lucas.bernard@efrei.fr', '0707070707', 1, 1, 1, 1, 1, 'Apprenti motivé et autonome', 'Bon suivi de mission'),('Cycle Ingénieur', '2024-2025', 'I2', 'Nguyen', 'Emma', 'emma.nguyen@efrei.fr', '0808080808',2, 2, 2, 2, 2, 'Travail sérieux et appliqué', 'Bonne progression cette année'), ('Cycle Ingénieur', '2024-2025', 'I2', 'Lemoine', 'Antoine', 'antoine.lemoine@efrei.fr', '0909090909',3, 3, 3, 3, 3, 'Très investi dans les tâches techniques', 'Bonne autonomie'), ('Cycle Ingénieur', '2024-2025', 'I2', 'Ribeiro', 'Inès', 'ines.ribeiro@efrei.fr', '0606060606', 4, 4, 4, 4, 4, 'Participation exemplaire en équipe', 'Communication fluide et proactive'),('Cycle Ingénieur', '2024-2025', 'I2', 'Diallo', 'Moussa', 'moussa.diallo@efrei.fr', '0656565656', 5, 5, 5, 5, 5, 'Bon potentiel technique', 'Peut améliorer la gestion du temps');