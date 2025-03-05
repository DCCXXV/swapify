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
    (1, 'Matemáticas'),
    (2, 'Programación en Python'),
    (3, 'Inglés'),
    (4, 'Música'),
    (5, 'Guitarra'),
    (6, 'Alemán'),
    (7, 'Japonés');

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
-- Insertar habilidades actuales de los usuarios
INSERT INTO currentSkill (id, description, rating, points, user_id, skill_id) VALUES
    (1, 'Profesora de matemáticas con experiencia.', 9.9, 100, 14, 1),
    (2, 'Experta en Python, programación avanzada.', 8.7, 95, 14, 2),
    (3, 'Nivel avanzado de inglés, C1.', 7.5, 90, 14, 3),
    (4, 'Guitarrista aficionada, autodidacta.', 6.2, 80, 15, 5),
    (5, 'Programadora en Python nivel intermedio.', 6.0, 85, 15, 2),
    (6, 'Nivel alto de alemán, B2.', 7.3, 88, 15, 6);

-- Insertar habilidades deseadas de los usuarios
INSERT INTO desiredSkill (id, description, user_id, skill_id) VALUES
    (1, 'Mejorar mi nivel de alemán.', 14, 6),
    (2, 'Aprender a tocar la guitarra.', 14, 5),
    (3, 'Aprender japonés desde cero.', 15, 7),
    (4, 'Mejorar mi nivel de matemáticas aplicadas.', 15, 1);
*/

-- Iniciar secuencia de IDs
ALTER SEQUENCE GEN RESTART WITH 1024;

