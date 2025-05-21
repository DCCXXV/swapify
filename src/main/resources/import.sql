-- =========================
-- 1. USUARIOS
-- =========================
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

INSERT INTO IWUSER (id, deleted, roles, username, email, password, first_name, last_name, description, pic)
VALUES
    (2, FALSE, 'USER', 'juanito03', 'b@a.a', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Juan', 'Perez', 'Disenador grafico y fotografo. Habilidades: Ingles (avanzado), Python, Frances. Buscando aprender: Java, Aleman.', 'juan.jpg'),
    (14, FALSE, 'USER', 'isabel', 'isabel@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Isabel', 'Rodriguez', 'Profesora de matematicas y Python. Hablo ingles.', 'isabelrodriguez.jpg'),
    (15, FALSE, 'USER', 'lucia', 'lucia@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Lucia', 'Fernandez', 'Apasionada de la musica (guitarra) y tecnologia (Python). Hablo aleman y aprendo japones.', 'luciafernandez.jpg'),
    (17, FALSE, 'USER', 'ana', 'ana@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Ana', 'Gomez', 'Profesora de yoga y meditacion. Hablo frances. Quiero aprender edicion de fotografia.', 'ana.jpg'),
    (18, FALSE, 'USER', 'luis', 'luis@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Luis', 'Martinez', 'Guitarrista (clasica, flamenca) y disenador grafico basico. Ingles C1. Busco mejorar italiano y aprender edicion de foto.', 'luis.jpg'),
    (19, FALSE, 'USER', 'maria', 'maria@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Maria', 'Lopez', 'Profesora de baile (salsa, bachata) y contadora (finanzas, Excel). Ingles alto. Quiero aprender a cocinar.', 'maria.jpg'),
    (20, FALSE, 'USER', 'carlos', 'carlos@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Carlos', 'Perez', 'Apasionado del futbol y entrenador personal. Hablo ingles y aprendo portugues.', 'carlosperez.jpg'),
    (21, FALSE, 'USER', 'laura', 'laura@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Laura', 'Gomez', 'Disenadora grafica con experiencia en marketing digital. Hablo frances.', 'lauragomez.jpg'),
    (22, FALSE, 'USER', 'pedro', 'pedro@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Pedro', 'Martinez', 'Desarrollador de software (JavaScript, React, Agile). Quiero aprender IA y Machine Learning.', 'pedromartinez.jpg'),
    (23, FALSE, 'USER', 'sofia', 'sofia@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Sofia', 'Ramirez', 'Amante de la fotografia y senderismo. Hablo ingles y aprendo aleman.', 'sofiaramirez.jpg'),
    (24, FALSE, 'USER', 'diego', 'diego@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Diego', 'Lopez', 'Musico, compositor y profesor de musica. Hablo italiano y aprendo ruso.', 'diegolopez.jpg'),
    (25, FALSE, 'USER', 'elena', 'elena@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Elena', 'Fernandez', 'Chef profesional especializada en cocina mediterranea. Hablo ingles y frances.', 'elenafernandez.jpg'),
    (26, FALSE, 'USER', 'miguel', 'miguel@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Miguel', 'Sanchez', 'Administrador de sistemas y entusiasta de la ciberseguridad. Hablo ingles y aprendo chino.', 'miguelsanchez.jpg');

-- =========================
-- 2. HABILIDADES
-- =========================
INSERT INTO Skill (id, name) VALUES
    (1, 'Matematicas'),
    (2, 'Programacion en Python'),
    (3, 'Ingles'),
    (4, 'Guitarra'),
    (5, 'Aleman'),
    (6, 'Japones'),
    (7, 'Frances'),
    (8, 'Yoga'),
    (9, 'Meditacion'),
    (10, 'Edicion de fotografia'),
    (11, 'Diseno grafico'),
    (12, 'Italiano'),
    (13, 'Baile'),
    (14, 'Finanzas y Excel'),
    (15, 'Cocina'),
    (16, 'Futbol'),
    (17, 'Entrenamiento personal'),
    (18, 'Portugues'),
    (19, 'Marketing digital'),
    (20, 'JavaScript'),
    (21, 'Desarrollo de software'),
    (22, 'Inteligencia artificial'),
    (23, 'Fotografia'),
    (24, 'Senderismo'),
    (25, 'Musica y composicion'),
    (26, 'Ensenanza musical'),
    (27, 'Ruso'),
    (28, 'Administracion de sistemas'),
    (29, 'Ciberseguridad'),
    (30, 'Chino'),
    (31, 'Programacion en Java'),
    (32, 'Machine learning'),
    (33, 'React Framework'),
    (34, 'Metodologias Agiles');

-- =========================
-- 3. HABILIDADES ACTUALES
-- =========================
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    -- Juanito03
    (99, 2, 3, 'Ingles fluido por estudios y experiencia internacional.', 4.8, 120),
    (102, 2, 2, 'Competencias solidas en Python por proyectos profesionales y personales.', 4.7, 110),
    (103, 2, 7, 'Frances dominado por estudios academicos y estancias en paises francofonos.', 4.0, 85),
    (104, 2, 11, 'Diseno grafico aplicado a fotografia y proyectos personales.', 4.2, 90),
    (105, 2, 23, 'Fotografia profesional y artistica.', 4.3, 95),

    -- Isabel
    (1, 14, 1, 'Mas de 10 anos ensenando matematicas.', 4.8, 120),
    (2, 14, 2, 'Experiencia en proyectos con Python.', 4.5, 100),
    (3, 14, 3, 'Dominio de ingles por estudio y practica profesional.', 4.0, 80),

    -- Lucia
    (4, 15, 4, 'Guitarrista autodidacta con experiencia en escenarios.', 4.2, 90),
    (5, 15, 2, 'Programacion en Python como hobby.', 4.0, 85),
    (6, 15, 5, 'Aleman fluido por estudios y experiencias interculturales.', 4.0, 80),
    (7, 15, 6, 'Japones basico en proceso de aprendizaje.', 2.5, 30),

    -- Ana
    (10, 17, 8, '5 anos ensenando yoga con estilo propio.', 4.8, 125),
    (11, 17, 9, 'Experiencia ensenando tecnicas de meditacion.', 4.2, 90),
    (12, 17, 7, 'Frances fluido.', 4.5, 95),

    -- Luis
    (13, 18, 4, 'Experiencia en guitarra clasica y flamenca.', 4.5, 100),
    (14, 18, 11, 'Ensenanza de diseno grafico basico con Illustrator.', 4.2, 90),
    (15, 18, 3, 'Ingles avanzado certificado (C1 Cambridge).', 4.0, 80),

    -- Maria
    (16, 19, 13, 'Clases impartidas de salsa y bachata.', 4.5, 90),
    (17, 19, 14, 'Contadora profesional con experiencia en finanzas y Excel avanzado.', 4.2, 85),
    (18, 19, 3, 'Alto nivel de ingles por experiencia laboral.', 4.0, 80),

    -- Carlos
    (19, 20, 16, 'Anos de practica de futbol en equipos.', 4.3, 85),
    (20, 20, 17, 'Entrenador personal con experiencia.', 4.5, 90),
    (21, 20, 3, 'Solido nivel de ingles.', 4.0, 80),

    -- Laura
    (22, 21, 11, 'Mas de 10 anos de experiencia en diseno grafico.', 4.9, 130),
    (23, 21, 19, 'Desarrollo de estrategias de marketing digital.', 4.3, 95),
    (24, 21, 7, 'Frances fluido.', 4.5, 100),

    -- Pedro
    (25, 22, 20, 'Experiencia creando aplicaciones web interactivas con JavaScript.', 4.7, 110),
    (26, 22, 21, 'Desarrollador de software con experiencia en soluciones complejas.', 4.8, 120),
    (55, 22, 34, 'Experiencia aplicando metodologias agiles.', 4.7, 110),
    (56, 22, 33, 'Experiencia con React para aplicaciones web escalables.', 4.7, 110),

    -- Sofia
    (27, 23, 23, 'Pasion por la fotografia de paisajes y naturaleza.', 4.5, 95),
    (28, 23, 24, 'Practica regular de senderismo.', 4.7, 105),
    (29, 23, 3, 'Dominio de ingles fortalecido por viajes y estudios.', 4.0, 80),

    -- Diego
    (30, 24, 25, 'Musico y compositor con habilidad en creacion y ejecucion.', 4.8, 120),
    (31, 24, 26, 'Experiencia ensenando musica con metodos practicos.', 4.7, 110),
    (32, 24, 12, 'Italiano aprendido por estudios y experiencias culturales.', 4.0, 85),

    -- Elena
    (33, 25, 15, 'Chef profesional especializada en cocina mediterranea.', 4.9, 130),
    (34, 25, 3, 'Dominio de ingles para recetas internacionales.', 4.8, 120),
    (35, 25, 7, 'Frances fluido para influencias culinarias.', 4.7, 110),

    -- Miguel
    (36, 26, 28, 'Administrador de sistemas con gestion eficiente de infraestructuras.', 4.7, 115),
    (37, 26, 29, 'Apasionado por la ciberseguridad, implementando medidas de proteccion.', 4.8, 125),
    (38, 26, 3, 'Nivel avanzado de ingles para tendencias tecnologicas.', 4.5, 100),

    -- ADMIN
    (1001, 1, 1, 'Gestion de la plataforma y conocimientos generales.', 5.0, 200);

-- =========================
-- 4. HABILIDADES DESEADAS
-- =========================
INSERT INTO DESIRED_SKILL (id, user_id, skill_id, description) VALUES
    -- Juanito03
    (14, 2, 31, 'Aprender Java para expandir conocimientos y oportunidades laborales.'),
    (15, 2, 5, 'Aprender aleman para mercados internacionales y enriquecimiento profesional.'),

    -- Isabel
    (1, 14, 2, 'Mejorar programacion en Python para proyectos educativos.'),

    -- Lucia
    (2, 15, 6, 'Aprender japones para ampliar horizontes culturales y profesionales.'),

    -- Ana
    (3, 17, 10, 'Aprender edicion de fotografia para realzar la belleza de las imagenes.'),

    -- Luis
    (4, 18, 12, 'Mejorar italiano para enriquecer perfil cultural y profesional.'),
    (5, 18, 10, 'Aprender edicion de fotografia para potenciar impacto visual.'),

    -- Maria
    (6, 19, 15, 'Aprender a cocinar explorando nuevas recetas y tecnicas.'),

    -- Carlos
    (7, 20, 18, 'Aprender portugues para ampliar horizontes en el mundo deportivo.'),

    -- Laura
    (8, 21, 19, 'Profundizar en marketing digital para proyectos creativos.'),

    -- Pedro
    (9, 22, 22, 'Profundizar en inteligencia artificial para soluciones innovadoras.'),
    (10, 22, 32, 'Profundizar en machine learning para vanguardia tecnologica.'),

    -- Sofia
    (11, 23, 5, 'Aprender aleman para explorar nuevas culturas y expandir horizontes.'),

    -- Diego
    (12, 24, 27, 'Aprender ruso para ampliar horizontes artisticos y colaboraciones.'),

    -- Elena
    (13, 25, 7, 'Mejorar frances para influencias culinarias.'),

    -- Miguel
    (16, 26, 30, 'Aprender chino para participar en proyectos tecnologicos internacionales.'),

    -- ADMIN
    (1002, 1, 3, 'Mejorar ingles para comunicacion internacional.');

-- =========================
-- 5. SWAPS (ejemplo)
-- =========================
INSERT INTO SWAP (id, user_a_id, user_b_id, skill_a_id, skill_b_id, swap_status) VALUES 
    (1, 2, 14, 3, 1, 'ACTIVE'), -- Juanito (Ingles) <-> Isabel (Matematicas)
    (2, 2, 26, 3, 28, 'ACTIVE'), -- Juanito (Ingles) <-> Miguel (Administracion de sistemas)
    (3, 2, 14, 3, 1, 'FINISHED'), -- Juanito (Ingles) <-> Isabel (Matematicas) - Terminado
    (4, 2, 26, 3, 28, 'PENDING'), -- Juanito (Ingles) <-> Miguel (Administracion de sistemas) - Pendiente
    (5, 20, 2, 16, 3, 'PENDING'), -- Carlos (Futbol) <-> Juanito (Ingles) - Pendiente
    (6, 18, 2, 4, 7, 'FINISHED'); -- Luis (Guitarra) <-> Juanito (Frances) - Terminado

-- =========================
-- 6. REINICIAR SECUENCIA
-- =========================
ALTER SEQUENCE GEN RESTART WITH 1024;