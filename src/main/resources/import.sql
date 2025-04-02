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

-- TEST

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
    'Soy Juan Pérez, un diseñador gráfico y fotógrafo con más de diez años de experiencia. Mi pasión es transformar ideas en realidades visuales a través del diseño y la fotografía, creando identidades visuales y capturando momentos únicos. Siempre estoy buscando aprender y crecer, especialmente en desarrollo web y marketing digital, y me encantaría mejorar mi inglés para comunicarme con clientes internacionales. En mi tiempo libre, disfruto del arte, la tecnología y la naturaleza. Si te interesa colaborar o intercambiar habilidades, ¡no dudes en contactarme! ',
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
-- 2. INSERTAR REGISTROS EN LA TABLA IWUser (usuarios)
-- ========================================================
INSERT INTO IWUser (id, deleted, roles, username, email, password, first_Name, last_Name, description, pic) VALUES
    (14, FALSE, 'USER', 'Isabel', 'isabel@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Isabel', 'Rodriguez', 'Hola, soy Isabel. Soy profesora de matemáticas y me encanta enseñar. También tengo experiencia en programación en Python y hablo inglés.', 'IsabelRodriguez.jpg'),
    (15, FALSE, 'USER', 'Lucia', 'lucia@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Lucía', 'Fernandez', '¡Hola! Soy Lucía, una apasionada de la música y la tecnología. Toco la guitarra y me encanta programar en mis tiempos libres. Hablo alemán y estoy aprendiendo japonés.', 'LuciaFernandez.jpg'),
    (16, FALSE, 'USER', 'Juan Alberto', 'juan.alberto@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Juan', 'Alberto', '¡Hola! Soy Juan Alberto, me gustaría aprender alemán o expandir mis conocimientos de programación aprendiendo Java! Tengo amplios conocimientos en Python y soy fluido en Inglés y Francés.', 'JuanAlberto.jpg'),
    (17, FALSE, 'USER', 'Ana', 'ana@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Ana', 'Gómez', '¡Hola! Soy Ana, me encantaría aprender edición de fotografía. Soy profesora de yoga con 5 años de experiencia y también puedo enseñar técnicas de meditación. ¡Hablo francés fluidamente!', 'Ana.jpg'),
    (18, FALSE, 'USER', 'Luis', 'luis@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Luis', 'Martínez', 'Me llamo Luis y estoy buscando mejorar mi italiano, aunque también recientemente he tenido ganas de aprender edición de fotografía. Soy guitarrista con experiencia en música clásica y flamenca, y también puedo enseñar diseño gráfico básico con Adobe Illustrator o inglés ya que estoy certificado con un C1 por Cambridge.', 'Luis.jpg'),
    (19, FALSE, 'USER', 'María', 'maria@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'María', 'López', 'Soy María y me gustaría aprender a cocinar. Tengo experiencia dando clases de baile (salsa y bachata) y soy contadora profesional, puedo ayudar con temas de finanzas personales y Excel avanzado. Además, debido a mi experiencia laboral, tengo un alto nivel de inglés.', 'María.jpg'),
    (20, FALSE, 'USER', 'Carlos', 'carlos@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Carlos', 'Pérez', '¡Hola! Soy Carlos, un apasionado del deporte. Me encanta jugar al fútbol y también soy entrenador personal. Hablo inglés y estoy aprendiendo portugués.', 'CarlosPerez.jpg'),
    (21, FALSE, 'USER', 'Laura', 'laura@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Laura', 'Gómez', 'Hola, soy Laura. Soy diseñadora gráfica y me encanta crear contenido visual. También tengo experiencia en marketing digital y hablo francés.', 'LauraGomez.jpg'),
    (22, FALSE, 'USER', 'Pedro', 'pedro@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Pedro', 'Martínez', 'Me llamo Pedro y soy desarrollador de software. Tengo experiencia en JavaScript y React. Me gustaría aprender más sobre inteligencia artificial y machine learning.', 'PedroMartinez.jpg'),
    (23, FALSE, 'USER', 'Sofía', 'sofia@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Sofía', 'Ramírez', 'Soy Sofía, una amante de la naturaleza y la fotografía. Me encanta hacer senderismo y capturar paisajes con mi cámara. También hablo inglés y estoy aprendiendo alemán.', 'SofiaRamirez.jpg'),
    (24, FALSE, 'USER', 'Diego', 'diego@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Diego', 'López', 'Hola, soy Diego. Soy músico y compositor. Toco varios instrumentos y me encanta enseñar música a otros. También hablo italiano y estoy aprendiendo ruso.', 'DiegoLopez.jpg'),
    (25, FALSE, 'USER', 'Elena', 'elena@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Elena', 'Fernández', 'Me llamo Elena y soy chef profesional. Me especializo en cocina mediterránea y me encanta experimentar con nuevos sabores. Hablo inglés y francés.', 'ElenaFernandez.jpg'),
    (26, FALSE, 'USER', 'Miguel', 'miguel@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Miguel', 'Sánchez', 'Soy Miguel, un entusiasta de la tecnología. Trabajo como administrador de sistemas y me encanta todo lo relacionado con la ciberseguridad. Hablo inglés y estoy aprendiendo chino.', 'MiguelSanchez.jpg');

-- ========================================================
-- 3. INSERTAR REGISTROS EN LA TABLA CURRENT_SKILL (habilidades actuales)
-- ========================================================
-- Para cada registro se incluye una descripción detallada, además de rating y points.

-- Usuario 14: Isabel
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (1, 14, 1, 'Con más de 10 años enseñando matemáticas, he desarrollado un enfoque claro para explicar conceptos complejos de forma sencilla, inspirando a mis estudiantes.', 4.8, 120),
    (2, 14, 2, 'He trabajado en varios proyectos utilizando Python, lo que me ha permitido desarrollar soluciones innovadoras y eficientes a problemas cotidianos.', 4.5, 100),
    (3, 14, 3, 'Mi dominio del inglés se ha forjado a través de años de estudio y práctica profesional, facilitando la comunicación en entornos internacionales.', 4.0, 80);

-- Usuario 15: Lucía
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (4, 15, 4, 'Como guitarrista autodidacta, he perfeccionado mi técnica a través de presentaciones en pequeños escenarios, conectando con la música y las emociones.', 4.2, 90),
    (5, 15, 2, 'Disfruto programar en Python en mis tiempos libres, explorando nuevas ideas y desarrollando soluciones creativas para retos tecnológicos.', 4.0, 85),
    (6, 15, 5, 'Hablo alemán con fluidez, gracias a estudios formales y experiencias interculturales, lo que me permite comunicarme eficazmente en este idioma.', 4.0, 80);

-- Usuario 16: Juan Alberto
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (7, 16, 2, 'He desarrollado sólidas competencias en Python a través de proyectos tanto profesionales como personales, resolviendo problemas complejos de manera eficiente.', 4.7, 110),
    (8, 16, 3, 'Mi fluidez en inglés se ha fortalecido con años de estudio y experiencia en entornos internacionales, permitiéndome colaborar en proyectos globales.', 4.8, 120),
    (9, 16, 7, 'El francés lo domino gracias a estudios académicos y estancias en países francófonos, lo que enriquece mi capacidad de comunicación cultural.', 4.0, 85);

-- Usuario 2: Juan Pérez
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (7, 16, 2, 'He desarrollado sólidas competencias en Python a través de proyectos tanto profesionales como personales, resolviendo problemas complejos de manera eficiente.', 4.7, 110),
    (8, 16, 3, 'Mi fluidez en inglés se ha fortalecido con años de estudio y experiencia en entornos internacionales, permitiéndome colaborar en proyectos globales.', 4.8, 120),
    (9, 16, 7, 'El francés lo domino gracias a estudios académicos y estancias en países francófonos, lo que enriquece mi capacidad de comunicación cultural.', 4.0, 85);


-- Usuario 17: Ana
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (10, 17, 8, 'Durante 5 años he enseñado yoga, desarrollando un estilo propio que equilibra mente y cuerpo y se adapta a las necesidades individuales de mis alumnos.', 4.8, 125),
    (11, 17, 9, 'He perfeccionado técnicas de meditación, ayudando a mis alumnos a encontrar la calma y la concentración mediante prácticas efectivas y personalizadas.', 4.2, 90),
    (12, 17, 7, 'Domino el francés fluidamente, lo que me permite acceder a una rica herencia cultural y comunicarme en entornos internacionales.', 4.5, 95);

-- Usuario 18: Luis
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (13, 18, 4, 'Mi experiencia en guitarra, tanto en música clásica como flamenca, me ha permitido desarrollar un estilo único y transmitir emociones a través de la música.', 4.5, 100),
    (14, 18, 11, 'He enseñado diseño gráfico básico utilizando Adobe Illustrator, combinando creatividad y técnica para transformar ideas en realidades visuales impactantes.', 4.2, 90),
    (15, 18, 3, 'Mi nivel avanzado en inglés, certificado por Cambridge, me permite comunicarme eficazmente y acceder a recursos internacionales.', 4.0, 80);

-- Usuario 19: María
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (16, 19, 13, 'He impartido clases de salsa y bachata, transmitiendo mi pasión por el baile y ayudando a mis alumnos a conectar con el ritmo y la cultura latina.', 4.5, 90),
    (17, 19, 14, 'Como contadora profesional, mi experiencia en finanzas y Excel avanzado me permite ofrecer soluciones prácticas para la gestión económica personal y empresarial.', 4.2, 85),
    (18, 19, 3, 'Mi alto nivel de inglés, reforzado por mi experiencia laboral, me permite comunicarme de manera efectiva en entornos internacionales.', 4.0, 80);

-- Usuario 20: Carlos
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (19, 20, 16, 'Mi pasión por el fútbol se refleja en años de práctica en equipos y torneos, desarrollando habilidades tácticas y un fuerte espíritu de equipo.', 4.3, 85),
    (20, 20, 17, 'Como entrenador personal, he ayudado a numerosos deportistas a alcanzar sus metas, combinando técnica, motivación y un enfoque integral del bienestar físico.', 4.5, 90),
    (21, 20, 3, 'Poseo un sólido nivel de inglés que me permite acceder a contenidos internacionales y comunicarme en el ámbito deportivo global.', 4.0, 80);

-- Usuario 21: Laura
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (22, 21, 11, 'Con más de diez años de experiencia en diseño gráfico, he trabajado en proyectos variados que me han permitido dominar la creación de identidades visuales, transformando ideas en realidades estéticas y funcionales.', 4.9, 130),
    (23, 21, 19, 'He desarrollado estrategias de marketing digital combinando creatividad y análisis de datos, logrando conectar marcas con audiencias específicas y alcanzar objetivos de negocio.', 4.3, 95),
    (24, 21, 7, 'Domino el francés fluidamente, lo que me abre puertas a colaboraciones internacionales y me permite comprender matices culturales en el mundo del diseño.', 4.5, 100);

-- Usuario 22: Pedro
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (25, 22, 20, 'Mi experiencia en JavaScript me ha permitido crear aplicaciones web interactivas y escalables, utilizando tecnologías modernas y metodologías ágiles.', 4.7, 110),
    (56, 22, 33, 'Mi experiencia React me ha permitido crear aplicaciones web interactivas y escalables, utilizando tecnologías modernas y metodologías ágiles.', 4.7, 110),
    (55, 22, 34, 'Mi experiencia React me ha permitido crear aplicaciones web interactivas y escalables, utilizando tecnologías modernas y metodologías ágiles.', 4.7, 110),
    (26, 22, 21, 'Como desarrollador de software, he enfrentado y resuelto desafíos complejos, integrando diversas tecnologías para construir soluciones robustas y eficientes.', 4.8, 120);

-- Usuario 23: Sofía
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (27, 23, 23, 'Mi pasión por la fotografía se refleja en la captura de paisajes y momentos naturales, combinando técnica y sensibilidad para contar historias visuales.', 4.5, 95),
    (28, 23, 24, 'Practico senderismo de forma regular, lo que me permite conectar profundamente con la naturaleza y disfrutar de su belleza en cada ruta.', 4.7, 105),
    (29, 23, 3, 'Mi dominio del inglés se ha fortalecido a través de experiencias de viaje y estudios, facilitando la comunicación en entornos internacionales.', 4.0, 80);

-- Usuario 24: Diego
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (30, 24, 25, 'Como músico y compositor, he perfeccionado mi habilidad para crear y ejecutar piezas musicales, integrando técnicas clásicas y contemporáneas que inspiran a mi audiencia.', 4.8, 120),
    (31, 24, 26, 'Enseñar música es mi pasión; disfruto compartir mi experiencia y ayudar a otros a descubrir su talento a través de métodos prácticos y creativos.', 4.7, 110),
    (32, 24, 12, 'He aprendido italiano mediante estudios y experiencias culturales, lo que me permite disfrutar de la música y la literatura en su idioma original.', 4.0, 85);

-- Usuario 25: Elena
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (33, 25, 15, 'Como chef profesional, me especializo en cocina mediterránea, combinando tradición e innovación para crear platos que sorprenden tanto en sabor como en presentación.', 4.9, 130),
    (34, 25, 3, 'Mi dominio del inglés me permite acceder a una amplia gama de recetas internacionales y colaborar con chefs de diversas culturas.', 4.8, 120),
    (35, 25, 7, 'El francés lo manejo fluidamente, lo que enriquece mi perspectiva culinaria al integrar influencias y técnicas de distintas tradiciones gastronómicas.', 4.7, 110);

-- Usuario 26: Miguel
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (36, 26, 28, 'Como administrador de sistemas, gestiono infraestructuras tecnológicas de forma eficiente, garantizando la continuidad y seguridad de los servicios.', 4.7, 115),
    (37, 26, 29, 'Me apasiona la ciberseguridad; he implementado medidas de protección y me mantengo actualizado sobre las últimas amenazas para salvaguardar la información.', 4.8, 125),
    (38, 26, 3, 'Mi avanzado nivel de inglés me permite estar al tanto de las tendencias y mejores prácticas en tecnología a nivel global.', 4.5, 100);

-- ========================================================
-- 4. INSERTAR REGISTROS EN LA TABLA DESIRED_SKILL (habilidades deseadas)
-- ========================================================
-- Aquí se explica el porqué el usuario desea aprender o perfeccionar la habilidad.

-- Usuario 15: Lucía
INSERT INTO DESIRED_SKILL (id, user_id, skill_id, description) VALUES
    (1, 15, 6, 'Estoy aprendiendo japonés para ampliar mis horizontes culturales y profesionales, con el objetivo de comunicarme con fluidez y sumergirme en la cultura nipona.');

-- Usuario 16: Juan Alberto
INSERT INTO DESIRED_SKILL (id, user_id, skill_id, description) VALUES
    (2, 16, 31, 'Quiero expandir mis conocimientos aprendiendo Java, un lenguaje robusto que me abrirá nuevas oportunidades laborales y proyectos de software.'),
    (3, 16, 5, 'Deseo aprender alemán para integrarme mejor en mercados internacionales y enriquecer mi perfil profesional.');

-- Usuario 17: Ana
INSERT INTO DESIRED_SKILL (id, user_id, skill_id, description) VALUES
    (4, 17, 10, 'Quiero aprender edición de fotografía para realzar la belleza de las imágenes, dominando técnicas de retoque y composición que permitan transformar fotografías ordinarias en obras de arte.');

-- Usuario 18: Luis
INSERT INTO DESIRED_SKILL (id, user_id, skill_id, description) VALUES
    (5, 18, 12, 'Deseo mejorar mi italiano para enriquecer mi perfil cultural y profesional, facilitando la comunicación en contextos internacionales.'),
    (6, 18, 10, 'Me gustaría aprender edición de fotografía para transformar mis imágenes y potenciar su impacto visual, explorando nuevas técnicas digitales.');

-- Usuario 19: María
INSERT INTO DESIRED_SKILL (id, user_id, skill_id, description) VALUES
    (7, 19, 15, 'Quiero aprender a cocinar, explorando nuevas recetas y técnicas culinarias para fusionar sabores y crear experiencias gastronómicas únicas.');

-- Usuario 20: Carlos
INSERT INTO DESIRED_SKILL (id, user_id, skill_id, description) VALUES
    (8, 20, 18, 'Estoy aprendiendo portugués para ampliar mis horizontes en el mundo deportivo y comunicarme con entrenadores y atletas de otros países.');

-- Usuario 22: Pedro
INSERT INTO DESIRED_SKILL (id, user_id, skill_id, description) VALUES
    (9, 22, 22, 'Deseo profundizar en inteligencia artificial para desarrollar soluciones innovadoras y mantenerme a la vanguardia tecnológica en el desarrollo de software.'),
    (10, 22, 32, 'Deseo profundizar en machine learning para desarrollar soluciones innovadoras y mantenerme a la vanguardia tecnológica en el desarrollo de software.');
-- Usuario 23: Sofía
INSERT INTO DESIRED_SKILL (id, user_id, skill_id, description) VALUES
    (11, 23, 5, 'Estoy aprendiendo alemán para poder explorar nuevas culturas y expandir mis horizontes tanto personales como profesionales.');

-- Usuario 24: Diego
INSERT INTO DESIRED_SKILL (id, user_id, skill_id, description) VALUES
    (12, 24, 27, 'Estoy aprendiendo ruso para ampliar mis horizontes artísticos y colaborar en proyectos musicales internacionales con nuevas influencias culturales.');

-- Usuario 26: Miguel
INSERT INTO DESIRED_SKILL (id, user_id, skill_id, description) VALUES
    (13, 26, 30, 'Quiero aprender chino para participar en proyectos internacionales en el sector tecnológico, aprovechando las oportunidades de un mercado en expansión.');


INSERT INTO SWAP (id, user_a_id, user_b_id, skill_a_id, skill_b_id, status) VALUES 
    (1, 2, 14, 3, 1,'ACTIVE');

INSERT INTO SWAP (id, user_a_id, user_b_id, skill_a_id, skill_b_id, status) VALUES 
    (2, 2, 26, 3, 23,'ACTIVE');

-- Iniciar secuencia de IDs
ALTER SEQUENCE GEN RESTART WITH 1024;