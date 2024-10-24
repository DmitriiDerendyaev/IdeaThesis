INSERT INTO users (telegram_id, username, created_at) VALUES
                                                          (123456789, 'user1', CURRENT_TIMESTAMP),
                                                          (987654321, 'user2', CURRENT_TIMESTAMP);

INSERT INTO competencies (competency_name, description) VALUES
                                                            ('UI/UX Design', 'Проектирование интерфейсов и опыта пользователей'),
                                                            ('Python', 'Язык программирования высокого уровня'),
                                                            ('DevOps', 'Интеграция и автоматизация процессов разработки и эксплуатации');

INSERT INTO study_areas (area_name, description) VALUES
                                                     ('Web Development', 'Разработка веб-приложений'),
                                                     ('Data Science', 'Наука о данных и аналитика');

INSERT INTO user_competencies (user_id, competency_id, created_at) VALUES
                                                                       (1, 1, CURRENT_TIMESTAMP),
                                                                       (1, 2, CURRENT_TIMESTAMP),
                                                                       (2, 3, CURRENT_TIMESTAMP);

INSERT INTO user_study_areas (user_id, area_id, created_at) VALUES
                                                                (1, 1, CURRENT_TIMESTAMP),
                                                                (2, 2, CURRENT_TIMESTAMP);

INSERT INTO user_requests (user_id, competency_id, area_id, request_time, request_text) VALUES
                                                                                            (1, 1, 1, CURRENT_TIMESTAMP, 'Я ищу тему для диплома в области веб-разработки.'),
                                                                                            (2, 2, 2, CURRENT_TIMESTAMP, 'Нужна тема в области науки о данных.');

INSERT INTO generates_topics (request_id, topic_name, description, created_at) VALUES
                                                                                   (1, 'Разработка интерактивного веб-приложения', 'Создание приложения для управления проектами с использованием современных технологий.', CURRENT_TIMESTAMP),
                                                                                   (2, 'Анализ больших данных с использованием Python', 'Изучение методов анализа данных и визуализации информации.', CURRENT_TIMESTAMP);

INSERT INTO responses (request_id, topic_id, response_time) VALUES
                                                                (1, 1, CURRENT_TIMESTAMP),
                                                                (2, 2, CURRENT_TIMESTAMP);

INSERT INTO user_states (stage, competencies, area_of_study, updated_at) VALUES
                                                                                      ( 'ASK_COMPETENCIES', 'UI/UX, Python', 'Web Development', CURRENT_TIMESTAMP),
                                                                                      ('FINISH', 'DevOps', 'Data Science', CURRENT_TIMESTAMP);