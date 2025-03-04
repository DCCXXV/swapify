/*
-- insert admin (username a, password aa)
INSERT INTO IWUser (id, enabled, roles, username, password)
VALUES (1, TRUE, 'ADMIN,USER', 'a',
    '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W');
INSERT INTO IWUser (id, enabled, roles, username, password)
VALUES (2, TRUE, 'USER', 'b',
    '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W');

-- start id numbering from a value that is larger than any assigned above
ALTER SEQUENCE "PUBLIC"."GEN" RESTART WITH 1024;
*/

-- Insertar admin (username a, password aa)
INSERT INTO IWUser (id, enabled, roles, username, password, description, rating, pfp)
VALUES (
    1, 
    TRUE, 
    'ADMIN,USER', 
    'a',
    '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W',
    'Usuario administrador', 
    5.0, 
    '/img/admin.png'
);

-- Insertar usuario normal (username b, password bb)
INSERT INTO IWUser (id, enabled, roles, username, password, description, rating, pfp)
VALUES (
    2, 
    TRUE, 
    'USER', 
    'guest',
    '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W',
    'Usuario estándar', 
    4.5, 
    '/img/user.png'
);
--Usuarios
INSERT INTO IWUser (id, enabled, roles, username, password, description, rating, pfp)
VALUES (
    3, 
    TRUE, 
    'USER', 
    'Juan_Alberto',
    '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W',
    '¡Hola! Soy Juan Alberto, me gustaría aprender alemán o expandir mis conocimientos de programación aprendiendo Java! Tengo amplios conocimientos en Python y soy fluido en Inglés y Francés.', 
    4.5, 
    '/JuanAlberto.jpg'
);
INSERT INTO IWUser (id, enabled, roles, username, password, description, rating, pfp)
VALUES (
    4,
    TRUE, 
    'USER', 
    'Ana', 
    '{bcrypt}$2a$10$C8N8D5nPbmgBa/KXJ.5iA.Kv3VG22Y6x/aDWAEwhO5u97swqzUPOW', 
    '¡Hola! Soy Ana, me encantaría aprender edición de fotografía. Soy profesora de yoga con 5 años de experiencia y también puedo enseñar técnicas de meditación. ¡Hablo francés fluidamente!', 
    4.7, 
    '/Ana.jpg'
);
INSERT INTO IWUser (id, enabled, roles, username, password, description, rating, pfp)
VALUES (
    5, 
    TRUE, 
    'USER', 
    'Luis', 
    '{bcrypt}$2a$10$QHj4gPhLJKNn0D3mFtXpD.Fz5.WKzRFWzB/X7PZpjS7XfON0tNw5S', 
    'Me llamo Luis y estoy buscando mejorar mi italiano, aunque también recientemente he tenido ganas de aprender edición de fotografía. Soy guitarrista con experiencia en música clásica y flamenca, y también puedo enseñar diseño gráfico básico con Adobe Illustrator o inglés ya que estoy certificado con un C1 por Cambridge.', 
    4.6, 
    '/Luis.jpg'
);
INSERT INTO IWUser (id, enabled, roles, username, password, description, rating, pfp)
VALUES (
    6, 
    TRUE, 
    'USER', 
    'Maria', 
    '{bcrypt}$2a$10$1W/1NBg1Wihs1dZwLh2Bjuj78HnA5xP6lL.pxZWRyOysIq7ZzFZZe', 
    'Soy María y me gustaría aprender a cocinar. Tengo experiencia dando clases de baile (salsa y bachata) y soy contadora profesional, puedo ayudar con temas de finanzas personales y Excel avanzado. Además, debido a mi experiencia laboral, tengo un alto nivel de inglés.', 
    4.9, 
    '/Maria.jpg'
);
INSERT INTO IWUser (id, enabled, roles, username, password, description, rating, pfp)
VALUES (
    7, 
    TRUE, 
    'USER', 
    'Carlos', 
    '{bcrypt}$2a$10$CwC5XpLmnW8w7IKVgSFDQuwllDYTV4Y.zKH5D2P.mqj7X5ykYqRai', 
    '¡Hola! Soy Carlos, un apasionado del deporte. Me encanta jugar al fútbol y también soy entrenador personal. Hablo inglés y estoy aprendiendo portugués.', 
    4.8, 
    '/CarlosPerez.jpg'
);
INSERT INTO IWUser (id, enabled, roles, username, password, description, rating, pfp)
VALUES (
     8, 
    TRUE, 
    'USER', 
    'Laura', 
    '{bcrypt}$2a$10$yZVrHQY3cTTUjxwBdP.SveWt5Jdj6KLJ6apPMOFu/ozMkvS0zqoeG', 
    'Hola, soy Laura. Soy diseñadora gráfica y me encanta crear contenido visual. También tengo experiencia en marketing digital y hablo francés.', 
    4.4, 
    '/LauraGomez.jpg'
);
INSERT INTO IWUser (id, enabled, roles, username, password, description, rating, pfp)
VALUES (
    9, 
    TRUE, 
    'USER', 
    'Pedro', 
    '{bcrypt}$2a$10$LQsE6tN0B5HrszvqJjIoLO2Cz5Nwn5By/Nhcf3cZfYo3apKowt/9O', 
    'Me llamo Pedro y soy desarrollador de software. Tengo experiencia en JavaScript y React. Me gustaría aprender más sobre inteligencia artificial y machine learning.', 
    4.7, 
    '/PedroMartinez.jpg'
);
INSERT INTO IWUser (id, enabled, roles, username, password, description, rating, pfp)
VALUES (
    10, 
    TRUE, 
    'USER', 
    'Sofia', 
    '{bcrypt}$2a$10$KX1R2pHzGmlK0wJGB3hZQuYWhzkhDB7M9w9RstxnTbGafGvgEJsF6', 
    'Soy Sofía, una amante de la naturaleza y la fotografía. Me encanta hacer senderismo y capturar paisajes con mi cámara. También hablo inglés y estoy aprendiendo alemán.', 
    4.3, 
    '/SofiaRamirez.jpg'
);
INSERT INTO IWUser (id, enabled, roles, username, password, description, rating, pfp)
VALUES (
     11, 
    TRUE, 
    'USER', 
    'Diego', 
    '{bcrypt}$2a$10$XeFbUpT4eJZp4tdRUfp3FOh4/CSu2dyJczDCYgI0FzR42hBFJkBMC', 
    'Hola, soy Diego. Soy músico y compositor. Toco varios instrumentos y me encanta enseñar música a otros. También hablo italiano y estoy aprendiendo ruso.', 
    4.6, 
    '/DiegoLopez.jpg'
);
INSERT INTO IWUser (id, enabled, roles, username, password, description, rating, pfp)
VALUES (
     12, 
    TRUE, 
    'USER', 
    'Elena', 
    '{bcrypt}$2a$10$Ry2qHBGH2i/dUM3Ks5NBgeRD0EmGuQgAyJXTQ1XFGqPH2W7kkhAom', 
    'Me llamo Elena y soy chef profesional. Me especializo en cocina mediterránea y me encanta experimentar con nuevos sabores. Hablo inglés y francés.', 
    4.8, 
    '/ElenaFernandez.jpg'
);
INSERT INTO IWUser (id, enabled, roles, username, password, description, rating, pfp)
VALUES (
    13, 
    TRUE, 
    'USER', 
    'Miguel', 
    '{bcrypt}$2a$10$7N9rE72pxm4b5uO2zPq78uACmC91FfKgH1fFQvWlLsRQaiC1Za5I.', 
    'Soy Miguel, un entusiasta de la tecnología. Trabajo como administrador de sistemas y me encanta todo lo relacionado con la ciberseguridad. Hablo inglés y estoy aprendiendo chino.', 
    4.5, 
    '/MiguelSanchez.jpg'
);
INSERT INTO IWUser (id, enabled, roles, username, password, description, rating, pfp)
VALUES (
    14, 
    TRUE, 
    'USER', 
    'Isabel', 
    '{bcrypt}$2a$10$fRsN8Yj5Lw2.GqsMnIFy2e/4Twz0JpJGXLc2vjoI2Z8ZcGm75yP2.', 
    'Hola, soy Isabel. Soy profesora de matemáticas y me encanta enseñar. También tengo experiencia en programación en Python y hablo inglés.', 
    4.9, 
    '/IsabelRodriguez.jpg'
);
INSERT INTO IWUser (id, enabled, roles, username, password, description, rating, pfp)
VALUES (
    15, 
    TRUE, 
    'USER', 
    'Lucia', 
    '{bcrypt}$2a$10$MZ1e0YvMJZrSxlXEZHKQj.Kcf9RV7WiRQmEHTkW.9YodFoq9/qeJ6', 
    '¡Hola! Soy Lucía, una apasionada de la música y la tecnología. Toco la guitarra y me encanta programar en mis tiempos libres. Hablo alemán y estoy aprendiendo japonés.', 
    4.2, 
    '/LuciaFernandez.jpg'
);









-- Iniciar secuencia de IDs
ALTER SEQUENCE GEN RESTART WITH 1024;

