-- Insertar administrador (username 'a', password 'aa')

INSERT INTO IWUSER (id, email, enabled, roles, username, password, description, pic)
VALUES (
    1, 
    'a@a.a',
    TRUE, 
    'ADMIN,USER', 
    'a',
    '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W',
    'Usuario administrador',  
    'admin.png'
);

-- TEST

INSERT INTO IWUSER (id, enabled, roles, username, email, password, first_name, last_name, description, pic)
VALUES (
    2,
    TRUE,
    'USER',
    'guest',
    'b@a.a',
    '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W',
    'John',
    'Doe',
    'Usuario estándar',
    'default-pic.jpg'
);

-- Insertar habilidades
INSERT INTO Skill (id, name) VALUES
    (1, 'Inglés'),
    (2, 'Francés'),
    (3, 'Programación en Python'),
    (4, 'Desarrollo web'),
    (5, 'Programación en Java'),
    (6, 'Alemán'),
    (7, 'Edición de fotografía'),
    (8, 'Guitarra'),
    (9, 'Diseño gráfico'),
    (10, 'Italiano'),
    (11, 'Cocina'),
    (12, 'Salsa'),
    (13, 'Bachata'),
    (14, 'Excel'),
    (15, 'Fútbol'),
    (16, 'Entrenamiento personal'),
    (17, 'Nutrición'),
    (18, 'Marketing digital'),
    (19, 'Fotografía'),
    (20, 'Redes sociales'),
    (21, 'JavaScript'),
    (22, 'React'),
    (23, 'Inteligencia artificial'),
    (24, 'Machine learning'),
    (25, 'Senderismo'),
    (26, 'Ecología'),
    (27, 'Composición'),
    (28, 'Producción musical'),
    (29, 'Cocina mediterránea'),
    (30, 'Repostería'),
    (31, 'Cocina asiática'),
    (32, 'Administración de sistemas'),
    (33, 'Ciberseguridad'),
    (34, 'Chino'),
    (35, 'Ruso');


-- Insertar usuarios
INSERT INTO IWUser (id, enabled, roles, username, email, password, first_Name, last_Name, description, pic) VALUES
    (14, TRUE, 'USER', 'Isabel', 'isabel@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Isabel', 'Rodriguez', 'Hola, soy Isabel. Soy profesora de matemáticas y me encanta enseñar. También tengo experiencia en programación en Python y hablo inglés.', 'IsabelRodriguez.jpg'),
    (15, TRUE, 'USER', 'Lucia', 'lucia@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Lucía', 'Fernandez', '¡Hola! Soy Lucía, una apasionada de la música y la tecnología. Toco la guitarra y me encanta programar en mis tiempos libres. Hablo alemán y estoy aprendiendo japonés.', 'LuciaFernandez.jpg'),
    (16, TRUE, 'USER', 'Juan Alberto', 'juan.alberto@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Juan', 'Alberto', '¡Hola! Soy Juan Alberto, me gustaría aprender alemán o expandir mis conocimientos de programación aprendiendo Java! Tengo amplios conocimientos en Python y soy fluido en Inglés y Francés.', 'JuanAlberto.jpg'),
    (17, TRUE, 'USER', 'Ana', 'ana@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Ana', 'Gómez', '¡Hola! Soy Ana, me encantaría aprender edición de fotografía. Soy profesora de yoga con 5 años de experiencia y también puedo enseñar técnicas de meditación. ¡Hablo francés fluidamente!', 'Ana.jpg'),
    (18, TRUE, 'USER', 'Luis', 'luis@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Luis', 'Martínez', 'Me llamo Luis y estoy buscando mejorar mi italiano, aunque también recientemente he tenido ganas de aprender edición de fotografía. Soy guitarrista con experiencia en música clásica y flamenca, y también puedo enseñar diseño gráfico básico con Adobe Illustrator o inglés ya que estoy certificado con un C1 por Cambridge.', 'Luis.jpg'),
    (19, TRUE, 'USER', 'María', 'maria@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'María', 'López', 'Soy María y me gustaría aprender a cocinar. Tengo experiencia dando clases de baile (salsa y bachata) y soy contadora profesional, puedo ayudar con temas de finanzas personales y Excel avanzado. Además, debido a mi experiencia laboral, tengo un alto nivel de inglés.', 'Maria.jpg'),
    (20, TRUE, 'USER', 'Carlos', 'carlos@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Carlos', 'Pérez', '¡Hola! Soy Carlos, un apasionado del deporte. Me encanta jugar al fútbol y también soy entrenador personal. Hablo inglés y estoy aprendiendo portugués.', 'CarlosPerez.jpg'),
    (21, TRUE, 'USER', 'Laura', 'laura@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Laura', 'Gómez', 'Hola, soy Laura. Soy diseñadora gráfica y me encanta crear contenido visual. También tengo experiencia en marketing digital y hablo francés.', 'LauraGomez.jpg'),
    (22, TRUE, 'USER', 'Pedro', 'pedro@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Pedro', 'Martínez', 'Me llamo Pedro y soy desarrollador de software. Tengo experiencia en JavaScript y React. Me gustaría aprender más sobre inteligencia artificial y machine learning.', 'PedroMartinez.jpg'),
    (23, TRUE, 'USER', 'Sofía', 'sofia@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Sofía', 'Ramírez', 'Soy Sofía, una amante de la naturaleza y la fotografía. Me encanta hacer senderismo y capturar paisajes con mi cámara. También hablo inglés y estoy aprendiendo alemán.', 'SofiaRamirez.jpg'),
    (24, TRUE, 'USER', 'Diego', 'diego@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Diego', 'López', 'Hola, soy Diego. Soy músico y compositor. Toco varios instrumentos y me encanta enseñar música a otros. También hablo italiano y estoy aprendiendo ruso.', 'DiegoLopez.jpg'),
    (25, TRUE, 'USER', 'Elena', 'elena@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Elena', 'Fernández', 'Me llamo Elena y soy chef profesional. Me especializo en cocina mediterránea y me encanta experimentar con nuevos sabores. Hablo inglés y francés.', 'ElenaFernandez.jpg'),
    (26, TRUE, 'USER', 'Miguel', 'miguel@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Miguel', 'Sánchez', 'Soy Miguel, un entusiasta de la tecnología. Trabajo como administrador de sistemas y me encanta todo lo relacionado con la ciberseguridad. Hablo inglés y estoy aprendiendo chino.', 'MiguelSanchez.jpg');

/*
-- Asignar habilidades actuales (CurrentSkill)
INSERT INTO CURRENT_SKILL (id, user_id, skill_id, description, rating, points) VALUES
    (1, 14, 3, 'Programación en Python', 4.5, 100),
    (2, 14, 1, 'Inglés', 4.0, 80),
    (3, 15, 8, 'Guitarra', 4.2, 90),
    (4, 15, 21, 'Programación', 3.8, 75),
    (5, 15, 6, 'Alemán', 3.5, 60),
    (6, 16, 1, 'Inglés', 4.8, 120),
    (7, 16, 2, 'Francés', 4.0, 85),
    (8, 16, 3, 'Programación en Python', 4.7, 110),
    (9, 17, 2, 'Francés', 4.5, 95),
    (10, 17, 7, 'Yoga', 4.8, 125),
    (11, 17, 8, 'Meditación', 4.2, 90);

-- Asignar habilidades deseadas (DesiredSkill)
INSERT INTO DESIRED_SKILL (id, user_id, skill_id, description) VALUES
    (1, 14, 23, 'Inteligencia artificial'),
    (2, 14, 11, 'Educación'),
    (3, 15, 28, 'Producción musical'),
    (4, 15, 35, 'Japonés'),
    (5, 16, 5, 'Programación en Java'),
    (6, 16, 6, 'Alemán'),
    (7, 17, 7, 'Edición de fotografía'),
    (8, 18, 10, 'Italiano'),
    (9, 18, 7, 'Edición de fotografía'),
    (10, 19, 11, 'Cocina'),
    (11, 20, 34, 'Chino'),
    (12, 21, 23, 'Inteligencia artificial'),
    (13, 22, 26, 'Ecología'),
    (14, 23, 35, 'Ruso');
*/

-- Iniciar secuencia de IDs
ALTER SEQUENCE GEN RESTART WITH 1024;

