DROP TABLE IF EXISTS "message" cascade ;
DROP TABLE IF EXISTS "post" cascade;
DROP TABLE IF EXISTS "topic" cascade;
DROP TABLE IF EXISTS "category" cascade;
DROP TABLE IF EXISTS "role" cascade;
DROP TABLE IF EXISTS "users" cascade;


CREATE TABLE role (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(50) UNIQUE NOT NULL
);
INSERT INTO role(name) VALUES
                           ('ROLE_USER' ),
                           ('ROLE_ADMIN');

-- Створення таблиці "user"
CREATE TABLE users (
                        id SERIAL PRIMARY KEY,
                        username VARCHAR(255) NOT NULL UNIQUE,
                        email VARCHAR(255) NOT NULL UNIQUE,
                        password TEXT NOT NULL,                -- Використовуємо TEXT замість VARCHAR
                        created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,  -- Використовуємо TIMESTAMPTZ
                        last_login TIMESTAMPTZ DEFAULT NULL,   -- Додаємо NULL як значення за замовчуванням
                        avatar VARCHAR(255),
                        role INT NOT NULL ,
                        FOREIGN KEY (role) REFERENCES role (id)
);


-- Створення індексів для підвищення продуктивності
CREATE INDEX idx_username ON users (username);
CREATE INDEX idx_email ON users (email);

-- Вставка даних в таблицю "user"
INSERT INTO users (username,email,password,last_login,avatar,role) VALUES
                                                                   ('Test','test@mail.com','{bcrypt}$2a$12$hzz38rS8hPaaVTbjrh4HvuTs6myDpWQJPO5Gwn7vKTK91aqI2ZDxu',CURRENT_TIMESTAMP,'uploads/default-avatar.png',1),
                                                                   ('Test2','test2@mail.com','{bcrypt}$2a$12$Rd76WTgj2MUJ7gVzfrsyCeEgiwa9rprDl35QCmnm/UDmDsN8lNag.',CURRENT_TIMESTAMP,'uploads/040102230424.jpg',1),
                                                                   ('Admin', 'admin@mail.com', '{bcrypt}$2a$12$Ix8T/tRxQ4chk7uBN/GgR.MEebpZ4ZGaI/f6kHqwwQW0RmR0zPU7W', CURRENT_TIMESTAMP, 'uploads/095439174129.jpg', 2);


-- Створення таблиці "category"
CREATE TABLE category (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL UNIQUE,
                          description TEXT,
                          created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
                          created_by INT,
                          updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
                          updated_by INT,
                          FOREIGN KEY (created_by) REFERENCES users (id),
                          FOREIGN KEY (updated_by) REFERENCES users (id)
);

-- Вставка даних в таблицю "category"
INSERT INTO category (name, description, created_by, updated_by) VALUES
                                                                     ('Test', 'This is test category', 1, 1),
                                                                     ('Test2', 'This is test category', 1, 1);

-- Створення таблиці "topic"
CREATE TABLE topic (
                       id SERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       category_id INT,
                       parent_topic_id INT,
                       created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
                       created_by INT NOT NULL ,
                       updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
                       updated_by INT,
                       FOREIGN KEY (created_by) REFERENCES users (id),
                       FOREIGN KEY (updated_by) REFERENCES users (id),
                       FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE,
                       FOREIGN KEY (parent_topic_id) REFERENCES topic (id) ON DELETE SET NULL
);

INSERT INTO topic(title, category_id, parent_topic_id, created_by) VALUES
                                                                       ('Test', '1', null, 1),
                                                                       ('Test2', '2', null, 2),
                                                                       ('TestInner', null, 1, 1);
-- Створення таблиці "post"
CREATE TABLE post (
                      id SERIAL PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      is_locked BOOLEAN DEFAULT FALSE,
                      is_pinned BOOLEAN DEFAULT FALSE,
                      created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
                      created_by INT NOT NULL,
                      updated_by INT,
                      topic INT NOT NULL,
                      FOREIGN KEY (created_by) REFERENCES users (id),
                      FOREIGN KEY (updated_by) REFERENCES users (id),
                      FOREIGN KEY (topic) REFERENCES topic (id) ON DELETE CASCADE
);

INSERT INTO post(title, is_locked, is_pinned,created_by,topic) VALUES
                                                                       ('Post', false, false, 1,1),
                                                                       ('TestPost', false, true, 2,1),
                                                                       ('TestInner', true, false, 1,2);

CREATE TABLE message (
                          id SERIAL PRIMARY KEY,
                          post_id INTEGER NOT NULL,
                          text TEXT NOT NULL,
                          created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
                          created_by INT NOT NULL,
                          updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
                          updated_by INT,
                          FOREIGN KEY (created_by) REFERENCES users (id),
                          FOREIGN KEY (updated_by) REFERENCES users (id),
                          FOREIGN KEY (post_id) REFERENCES post (id) ON DELETE CASCADE
);

INSERT INTO message (post_id, text, created_by, updated_by) VALUES
                                                                (1, 'This is the first message in Post.', 1, 1),
                                                                (1, 'Another message in Post.', 2, 2),
                                                                (2, 'Message in TestPost.', 2, 2),
                                                                (2, 'Second message in TestPost.', 1, 1),
                                                                (3, 'First message in TestInner.', 1, 1),
                                                                (3, 'Another message in TestInner.', 2, 2);




