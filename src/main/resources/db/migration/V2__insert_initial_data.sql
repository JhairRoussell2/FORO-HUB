INSERT INTO perfil (nombre) VALUES ('ADMIN'), ('USER');

INSERT INTO usuario (nombre, correo_electronico, contrasena, perfil_id)
VALUES
    ('Administrador', 'admin@forohub.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.', 1),
    ('Usuario Ejemplo', 'user@forohub.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.', 2);

INSERT INTO curso (nombre, categoria)
VALUES
    ('Spring Boot', 'Programación'),
    ('HTML y CSS', 'Front-end'),
    ('JavaScript', 'Front-end'),
    ('React', 'Front-end'),
    ('Node.js', 'Back-end');

INSERT INTO topico (titulo, mensaje, autor_id, curso_id, status)
VALUES
    ('Problema con Spring Security', 'No puedo configurar la autenticación JWT en mi proyecto', 2, 1, 'NO_RESPONDIDO'),
    ('Duda sobre CSS Grid', '¿Cómo crear un layout responsivo con CSS Grid?', 2, 2, 'SOLUCIONADO');