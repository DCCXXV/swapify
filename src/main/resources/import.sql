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

-- Iniciar secuencia de IDs
ALTER SEQUENCE GEN RESTART WITH 1024;

