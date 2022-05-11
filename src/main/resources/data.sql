INSERT INTO role (id, name) VALUES (1, 'USER');
INSERT INTO role (id, name) VALUES (2, 'ADMIN');

INSERT INTO users (login, password) VALUES ('admin', 'admin');

INSERT INTO book (id, description, name, price, category, author, quantity, image)
    VALUES (1, 'Фантастика', '451° по Фаренгейту', 1199, 'fantasy', 'Рэй Брэдбери', 3, '/img/fantasy1.jpg');
INSERT INTO book (id, description, name, price, category, author, quantity, image)
    VALUES (2, 'Фантастика', 'О дивный новый мир', 1299, 'fantasy', 'Олдос Хаксли', 5, '/img/fantasy2.jpg');
INSERT INTO book (id, description, name, price, category, author, quantity, image)
    VALUES (3, 'Фантастика', 'Конец вечности', 1599, 'fantasy', 'Айзек Азимов', 10, '/img/fantasy3.jpg');
INSERT INTO book (id, description, name, price, category, author, quantity, image)
    VALUES (4, 'Фантастика', 'Властелин колец', 1399, 'fantasy', 'Джон Рональд Руэл Толкин', 8, '/img/fantasy4.jpg');

INSERT INTO book (id, description, name, price, category, author, quantity, image)
    VALUES (5, 'Проза', 'Три товарища', 1499, 'prose', 'Эрих Мария Ремарк', 15, '/img/prose1.jpg');
INSERT INTO book (id, description, name, price, category, author, quantity, image)
    VALUES (6, 'Проза', 'Евгений Онегин', 899, 'prose', 'Александр Пушкин', 5, '/img/prose2.jpg');
INSERT INTO book (id, description, name, price, category, author, quantity, image)
    VALUES (7, 'Проза', 'Старик и море', 999, 'prose', 'Эрнест Хемингуэй', 14, '/img/prose3.jpg');
INSERT INTO book (id, description, name, price, category, author, quantity, image)
    VALUES (8, 'Проза', 'Идиот', 1299, 'prose', 'Фёдор Достоевский', 4, '/img/prose4.jpg');

INSERT INTO book (id, description, name, price, category, author, quantity, image)
    VALUES (9, 'Детектив', 'Остров проклятых', 599, 'detective', 'Деннис Лихэйн', 19, '/img/detective1.jpg');
INSERT INTO book (id, description, name, price, category, author, quantity, image)
    VALUES (10, 'Детектив', 'Токийский зодиак', 999, 'detective', 'Симада Содзи', 8, '/img/detective2.jpg');
INSERT INTO book (id, description, name, price, category, author, quantity, image)
    VALUES (11, 'Детектив', 'Убийство в «Восточном экспрессе»', 699, 'detective', 'Агата Кристи', 6, '/img/detective3.jpg');
INSERT INTO book (id, description, name, price, category, author, quantity, image)
    VALUES (12, 'Детектив', '1793. История одного убийства', 1099, 'detective', 'Никлас Натт-о-Даг', 3, '/img/detective4.jpg');

INSERT INTO book (id, description, name, price, category, author, quantity, image)
    VALUES (13, 'Научно-популярная литература', '21 урок для XXI века', 1199, 'non-fiction', 'Юваль Ной Харари', 8, '/img/non-fiction1.jpg');
INSERT INTO book (id, description, name, price, category, author, quantity, image)
    VALUES (14, 'Научно-популярная литература', 'Чёрный лебедь', 1799, 'non-fiction', 'Нассим Николас Талеб', 16, '/img/non-fiction2.jpg');
INSERT INTO book (id, description, name, price, category, author, quantity, image)
    VALUES (15, 'Научно-популярная литература', 'Осознанность. Как обрести гармонию в нашем безумном мире', 999,
    'non-fiction', 'Денни Пенман', 15, '/img/non-fiction3.jpg');
INSERT INTO book (id, description, name, price, category, author, quantity, image)
    VALUES (16, 'Научно-популярная литература', 'Думай медленно… решай быстро', 999,
    'non-fiction', 'Даниэль Канеман', 0, '/img/non-fiction4.jpg');