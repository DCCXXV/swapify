-- Insertar administrador (username 'a', password 'aa')
INSERT INTO IWUSER (id, email, deleted, roles, username, password, description, pic, first_name)
VALUES (
    1, 
    'a@a.a',
    FALSE, 
    'ADMIN,USER', 
    'a',
    '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W',
    'Usuario administrador',  
    'admin.png',
    'El admin'
);

-- Usuario Juanito03 (fusionado con datos de Juan Alberto)
INSERT INTO IWUSER (id, deleted, roles, username, email, password, first_name, last_name, description, pic)
VALUES (
    2,
    FALSE,
    'USER',
    'juanito03',
    'b@a.a',
    '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W',
    'Juan',
    'Pérez',
    'Diseñador gráfico y fotógrafo. Habilidades: Inglés (avanzado), Python, Francés. Buscando aprender: Java, Alemán.',
    'Juan.jpg'
);

-- ========================================================
-- 1. INSERTAR REGISTROS EN LA TABLA Skill
-- ========================================================
INSERT INTO Skill (id, name) VALUES
    (1, 'Matemáticas'),
    (2, 'Programación en Python'),
    (3, 'Inglés'),
    (4, 'Guitarra'),
    (5, 'Alemán'),
    (6, 'Japonés'),
    (7, 'Francés'),
    (8, 'Yoga'),
    (9, 'Meditación'),
    (10, 'Edición de fotografía'),
    (11, 'Diseño gráfico'),
    (12, 'Italiano'),
    (13, 'Baile'),
    (14, 'Finanzas y Excel'),
    (15, 'Cocina'),
    (16, 'Fútbol'),
    (17, 'Entrenamiento personal'),
    (18, 'Portugués'),
    (19, 'Marketing digital'),
    (20, 'JavaScript'),
    (21, 'Desarrollo de software'),
    (22, 'Inteligencia artificial'),
    (23, 'Fotografía'),
    (24, 'Senderismo'),
    (25, 'Música y composición'),
    (26, 'Enseñanza musical'),
    (27, 'Ruso'),
    (28, 'Administración de sistemas'),
    (29, 'Ciberseguridad'),
    (30, 'Chino'),
    (31, 'Programación en Java'),
    (32, 'Machine learning'),
    (33, 'React Framework'),
    (34, 'Metodologías Ágiles');

-- ========================================================
-- 2. INSERTAR REGISTROS EN LA TABLA IWUser (otros usuarios)
-- ========================================================
INSERT INTO IWUser (id, deleted, roles, username, email, password, first_Name, last_Name, description, pic) VALUES
    (14, FALSE, 'USER', 'Isabel', 'isabel@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Isabel', 'Rodriguez', 'Profesora de matemáticas y Python. Hablo inglés.', 'IsabelRodriguez.jpg'),
    (15, FALSE, 'USER', 'Lucia', 'lucia@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Lucía', 'Fernandez', 'Apasionada de la música (guitarra) y tecnología (Python). Hablo alemán y aprendo japonés.', 'LuciaFernandez.jpg'),
    (17, FALSE, 'USER', 'Ana', 'ana@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Ana', 'Gómez', 'Profesora de yoga y meditación. Hablo francés. Quiero aprender edición de fotografía.', 'Ana.jpg'),
    (18, FALSE, 'USER', 'Luis', 'luis@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Luis', 'Martínez', 'Guitarrista (clásica, flamenca) y diseñador gráfico básico. Inglés C1. Busco mejorar italiano y aprender edición de foto.', 'Luis.jpg'),
    (19, FALSE, 'USER', 'María', 'maria@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'María', 'López', 'Profesora de baile (salsa, bachata) y contadora (finanzas, Excel). Inglés alto. Quiero aprender a cocinar.', 'María.jpg'),
    (20, FALSE, 'USER', 'Carlos', 'carlos@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Carlos', 'Pérez', 'Apasionado del fútbol y entrenador personal. Hablo inglés y aprendo portugués.', 'CarlosPerez.jpg'),
    (21, FALSE, 'USER', 'Laura', 'laura@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Laura', 'Gómez', 'Diseñadora gráfica con experiencia en marketing digital. Hablo francés.', 'LauraGomez.jpg'),
    (22, FALSE, 'USER', 'Pedro', 'pedro@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Pedro', 'Martínez', 'Desarrollador de software (JavaScript, React, Agile). Quiero aprender IA y Machine Learning.', 'PedroMartinez.jpg'),
    (23, FALSE, 'USER', 'Sofía', 'sofia@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Sofía', 'Ramírez', 'Amante de la fotografía y senderismo. Hablo inglés y aprendo alemán.', 'SofiaRamirez.jpg'),
    (24, FALSE, 'USER', 'Diego', 'diego@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Diego', 'López', 'Músico, compositor y profesor de música. Hablo italiano y aprendo ruso.', 'DiegoLopez.jpg'),
    (25, FALSE, 'USER', 'Elena', 'elena@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Elena', 'Fernández', 'Chef profesional especializada en cocina mediterránea. Hablo inglés y francés.', 'ElenaFernandez.jpg'),
    (26, FALSE, 'USER', 'Miguel', 'miguel@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Miguel', 'Sánchez', 'Administrador de sistemas y entusiasta de la ciberseguridad. Hablo inglés y aprendo chino.', 'MiguelSanchez.jpg');

-- ========================================================
-- 3. INSERTAR REGISTROS EN LA TABLA CURRENT_SKILL (habilidades actuales)
-- ========================================================
-- Usuario 2: Juanito03 (incluye habilidades fusionadas)
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (99, 2, 3, 'Inglés fluido por estudios y experiencia internacional.', 4.8, 120),
    (102, 2, 2, 'Competencias sólidas en Python por proyectos profesionales y personales.', 4.7, 110),
    (103, 2, 7, 'Francés dominado por estudios académicos y estancias en países francófonos.', 4.0, 85);

-- Usuario 14: Isabel
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (1, 14, 1, 'Más de 10 años enseñando matemáticas.', 4.8, 120),
    (2, 14, 2, 'Experiencia en proyectos con Python.', 4.5, 100),
    (3, 14, 3, 'Dominio de inglés por estudio y práctica profesional.', 4.0, 80);

-- Usuario 15: Lucía
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (4, 15, 4, 'Guitarrista autodidacta con experiencia en escenarios.', 4.2, 90),
    (5, 15, 2, 'Programación en Python como hobby.', 4.0, 85),
    (6, 15, 5, 'Alemán fluido por estudios y experiencias interculturales.', 4.0, 80);

-- Usuario 17: Ana
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (10, 17, 8, '5 años enseñando yoga con estilo propio.', 4.8, 125),
    (11, 17, 9, 'Experiencia enseñando técnicas de meditación.', 4.2, 90),
    (12, 17, 7, 'Francés fluido.', 4.5, 95);

-- Usuario 18: Luis
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (13, 18, 4, 'Experiencia en guitarra clásica y flamenca.', 4.5, 100),
    (14, 18, 11, 'Enseñanza de diseño gráfico básico con Illustrator.', 4.2, 90),
    (15, 18, 3, 'Inglés avanzado certificado (C1 Cambridge).', 4.0, 80);

-- Usuario 19: María
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (16, 19, 13, 'Clases impartidas de salsa y bachata.', 4.5, 90),
    (17, 19, 14, 'Contadora profesional con experiencia en finanzas y Excel avanzado.', 4.2, 85),
    (18, 19, 3, 'Alto nivel de inglés por experiencia laboral.', 4.0, 80);

-- Usuario 20: Carlos
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (19, 20, 16, 'Años de práctica de fútbol en equipos.', 4.3, 85),
    (20, 20, 17, 'Entrenador personal con experiencia.', 4.5, 90),
    (21, 20, 3, 'Sólido nivel de inglés.', 4.0, 80);

-- Usuario 21: Laura
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (22, 21, 11, 'Más de 10 años de experiencia en diseño gráfico.', 4.9, 130),
    (23, 21, 19, 'Desarrollo de estrategias de marketing digital.', 4.3, 95),
    (24, 21, 7, 'Francés fluido.', 4.5, 100);

-- Usuario 22: Pedro
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (25, 22, 20, 'Experiencia creando aplicaciones web interactivas con JavaScript.', 4.7, 110),
    (56, 22, 33, 'Experiencia con React para aplicaciones web escalables.', 4.7, 110),
    (55, 22, 34, 'Experiencia aplicando metodologías ágiles.', 4.7, 110),
    (26, 22, 21, 'Desarrollador de software con experiencia en soluciones complejas.', 4.8, 120);

-- Usuario 23: Sofía
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (27, 23, 23, 'Pasión por la fotografía de paisajes y naturaleza.', 4.5, 95),
    (28, 23, 24, 'Práctica regular de senderismo.', 4.7, 105),
    (29, 23, 3, 'Dominio de inglés fortalecido por viajes y estudios.', 4.0, 80);

-- Usuario 24: Diego
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (30, 24, 25, 'Músico y compositor con habilidad en creación y ejecución.', 4.8, 120),
    (31, 24, 26, 'Experiencia enseñando música con métodos prácticos.', 4.7, 110),
    (32, 24, 12, 'Italiano aprendido por estudios y experiencias culturales.', 4.0, 85);

-- Usuario 25: Elena
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (33, 25, 15, 'Chef profesional especializada en cocina mediterránea.', 4.9, 130),
    (34, 25, 3, 'Dominio de inglés para recetas internacionales.', 4.8, 120),
    (35, 25, 7, 'Francés fluido para influencias culinarias.', 4.7, 110);

-- Usuario 26: Miguel
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (36, 26, 28, 'Administrador de sistemas con gestión eficiente de infraestructuras.', 4.7, 115),
    (37, 26, 29, 'Apasionado por la ciberseguridad, implementando medidas de protección.', 4.8, 125),
    (38, 26, 3, 'Nivel avanzado de inglés para tendencias tecnológicas.', 4.5, 100);

-- ========================================================
-- 4. INSERTAR REGISTROS EN LA TABLA DESIRED_SKILL (habilidades deseadas)
-- ========================================================
-- Usuario 2: Juanito03 (incluye deseos fusionados)
INSERT INTO DESIRED_SKILL (id, user_id, skill_id, description) VALUES
    (14, 2, 31, 'Aprender Java para expandir conocimientos y oportunidades laborales.'),
    (15, 2, 5, 'Aprender alemán para mercados internacionales y enriquecimiento profesional.');

-- Usuario 15: Lucía
INSERT INTO DESIRED_SKILL (id, user_id, skill_id, description) VALUES
    (1, 15, 6, 'Aprender japonés para ampliar horizontes culturales y profesionales.');

-- Usuario 17: Ana
INSERT INTO DESIRED_SKILL (id, user_id, skill_id, description) VALUES
    (4, 17, 10, 'Aprender edición de fotografía para realzar la belleza de las imágenes.');

-- Usuario 18: Luis
INSERT INTO DESIRED_SKILL (id, user_id, skill_id, description) VALUES
    (5, 18, 12, 'Mejorar italiano para enriquecer perfil cultural y profesional.'),
    (6, 18, 10, 'Aprender edición de fotografía para potenciar impacto visual.');

-- Usuario 19: María
INSERT INTO DESIRED_SKILL (id, user_id, skill_id, description) VALUES
    (7, 19, 15, 'Aprender a cocinar explorando nuevas recetas y técnicas.');

-- Usuario 20: Carlos
INSERT INTO DESIRED_SKILL (id, user_id, skill_id, description) VALUES
    (8, 20, 18, 'Aprender portugués para ampliar horizontes en el mundo deportivo.');

-- Usuario 22: Pedro
INSERT INTO DESIRED_SKILL (id, user_id, skill_id, description) VALUES
    (9, 22, 22, 'Profundizar en inteligencia artificial para soluciones innovadoras.'),
    (10, 22, 32, 'Profundizar en machine learning para vanguardia tecnológica.');

-- Usuario 23: Sofía
INSERT INTO DESIRED_SKILL (id, user_id, skill_id, description) VALUES
    (11, 23, 5, 'Aprender alemán para explorar nuevas culturas y expandir horizontes.');

-- Usuario 24: Diego
INSERT INTO DESIRED_SKILL (id, user_id, skill_id, description) VALUES
    (12, 24, 27, 'Aprender ruso para ampliar horizontes artísticos y colaboraciones.');

-- Usuario 26: Miguel
INSERT INTO DESIRED_SKILL (id, user_id, skill_id, description) VALUES
    (13, 26, 30, 'Aprender chino para participar en proyectos tecnológicos internacionales.');

-- ========================================================
-- 5. INSERTAR REGISTROS EN LA TABLA SWAP
-- ========================================================
INSERT INTO SWAP (id, user_a_id, user_b_id, skill_a_id, skill_b_id, swap_status) VALUES 
    (1, 2, 14, 3, 1, 'ACTIVE'); -- Juanito (Inglés) <-> Isabel (Mates)

INSERT INTO SWAP (id, user_a_id, user_b_id, skill_a_id, skill_b_id, swap_status) VALUES 
    (2, 2, 26, 3, 23, 'ACTIVE'); -- Juanito (Inglés) <-> Miguel (Fotografía?) - Skill B parece incorrecta para Miguel

INSERT INTO SWAP (id, user_a_id, user_b_id, skill_a_id, skill_b_id, swap_status) VALUES 
    (3, 2, 14, 3, 1, 'FINISHED'); -- Juanito (Inglés) <-> Isabel (Mates) - Terminado

INSERT INTO SWAP (id, user_a_id, user_b_id, skill_a_id, skill_b_id, swap_status) VALUES 
    (4, 2, 26, 3, 23, 'PENDING'); -- Juanito (Inglés) <-> Miguel (Fotografía?) - Pendiente, Skill B parece incorrecta

INSERT INTO SWAP (id, user_a_id, user_b_id, skill_a_id, skill_b_id, swap_status) VALUES 
    (5, 20, 2, 16, 3, 'PENDING'); -- Carlos (Fútbol) <-> Juanito (Inglés) - Pendiente

INSERT INTO SWAP (id, user_a_id, user_b_id, skill_a_id, skill_b_id, swap_status) VALUES 
    (6, 18, 2, 4, 7, 'FINISHED'); -- Luis (Guitarra) <-> Juanito (Francés) - Terminado (para review)

-- ========================================================
-- Reiniciar secuencia de IDs
-- ========================================================
ALTER SEQUENCE GEN RESTART WITH 1024;
