DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS competencies;
DROP TABLE IF EXISTS user_competencies;
DROP TABLE IF EXISTS study_areas;
DROP TABLE IF EXISTS user_study_areas;
DROP TABLE IF EXISTS user_requests;
DROP TABLE IF EXISTS generates_topics;
DROP TABLE IF EXISTS responses;
DROP TABLE IF EXISTS user_requests;

CREATE TABLE IF NOT EXISTS users
(
    user_id     BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    telegram_id BIGINT       NOT NULL,
    username    VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP    NOT NULL
);

CREATE TABLE IF NOT EXISTS competencies
(
    competency_id   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    competency_name VARCHAR(255) NOT NULL,
    description     TEXT
);

CREATE TABLE IF NOT EXISTS user_competencies
(
    users_competency_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id             BIGINT    NOT NULL,
    competency_id       BIGINT    NOT NULL,
    created_at          TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (competency_id) REFERENCES competencies (competency_id)
);

CREATE TABLE IF NOT EXISTS study_areas
(
    area_id     BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    area_name   VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS user_study_areas
(
    user_study_area_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id            BIGINT    NOT NULL,
    area_id            BIGINT    NOT NULL,
    created_at         TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (area_id) REFERENCES study_areas (area_id)
);

CREATE TABLE IF NOT EXISTS user_requests
(
    request_id    BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id       BIGINT    NOT NULL,
    competency_id BIGINT    NOT NULL,
    area_id       BIGINT    NOT NULL,
    request_time  TIMESTAMP NOT NULL,
    request_text  TEXT      NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (competency_id) REFERENCES competencies (competency_id),
    FOREIGN KEY (area_id) REFERENCES study_areas (area_id)
);

CREATE TABLE IF NOT EXISTS generates_topics
(
    topic_id    BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    request_id  BIGINT       NOT NULL,
    topic_name  VARCHAR(255) NOT NULL,
    description TEXT         NOT NULL,
    created_at  TIMESTAMP    NOT NULL,
    FOREIGN KEY (request_id) REFERENCES user_requests (request_id)
);

CREATE TABLE IF NOT EXISTS responses
(
    response_id   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    request_id    BIGINT NOT NULL,
    topic_id      BIGINT NOT NULL,
    response_time TIMESTAMP,
    FOREIGN KEY (request_id) REFERENCES user_requests (request_id),
    FOREIGN KEY (topic_id) REFERENCES generates_topics (topic_id)
);

CREATE TABLE IF NOT EXISTS user_states
(
    user_id       BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    stage         VARCHAR(50) NOT NULL,
    competencies  TEXT,
    area_of_study TEXT,
    updated_at    TIMESTAMP   NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);
