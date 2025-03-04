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
    (15, TRUE, 'USER', 'Lucia', 'lucia@example.com', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'Lucía', 'Fernandez', '¡Hola! Soy Lucía, una apasionada de la música y la tecnología. Toco la guitarra y me encanta programar en mis tiempos libres. Hablo alemán y estoy aprendiendo japonés.', 'LuciaFernandez.jpg');

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


-- Iniciar secuencia de IDs
ALTER SEQUENCE GEN RESTART WITH 1024;

