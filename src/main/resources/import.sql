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
    '/img/admin.png'
);

-- Insertar usuario normal (username 'guest', password 'bb')
INSERT INTO IWUSER (id, email, enabled, roles, username, password, description, pic)
VALUES (
    2,
    'b@a.a',
    TRUE,
    'USER', 
    'guest',
    '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W',
    'Usuario estándar', 
    '/img/user.png'
);

-- Iniciar secuencia de IDs
ALTER SEQUENCE GEN RESTART WITH 1024;

