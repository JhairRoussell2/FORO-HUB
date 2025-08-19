CREATE TABLE perfil (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(100) NOT NULL
);

CREATE TABLE usuario (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL,
                         correo_electronico VARCHAR(150) UNIQUE NOT NULL,
                         contrasena VARCHAR(255) NOT NULL,
                         perfil_id BIGINT,
                         activo BOOLEAN DEFAULT TRUE,
                         FOREIGN KEY (perfil_id) REFERENCES perfil(id)
);

CREATE TABLE curso (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       nombre VARCHAR(100) NOT NULL,
                       categoria VARCHAR(100) NOT NULL
);

CREATE TABLE topico (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        titulo VARCHAR(200) NOT NULL,
                        mensaje TEXT NOT NULL,
                        fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        status VARCHAR(50) DEFAULT 'NO_RESPONDIDO',
                        autor_id BIGINT,
                        curso_id BIGINT,
                        activo BOOLEAN DEFAULT TRUE,
                        FOREIGN KEY (autor_id) REFERENCES usuario(id),
                        FOREIGN KEY (curso_id) REFERENCES curso(id)
);

CREATE TABLE respuesta (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           mensaje TEXT NOT NULL,
                           topico_id BIGINT,
                           fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           autor_id BIGINT,
                           solucion BOOLEAN DEFAULT FALSE,
                           activo BOOLEAN DEFAULT TRUE,
                           FOREIGN KEY (topico_id) REFERENCES topico(id),
                           FOREIGN KEY (autor_id) REFERENCES usuario(id)
);