DROP TABLE IF EXISTS guests CASCADE;
DROP TABLE IF EXISTS rates CASCADE;
DROP TABLE IF EXISTS rooms CASCADE;
DROP TABLE IF EXISTS booking CASCADE;

CREATE TABLE IF NOT EXISTS guests
(
    guest_id   INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phone VARCHAR(12) NOT NULL,
    email VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS rooms
(
    room_id   INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    rate INT REFERENCES rates (rate_id)
    );

CREATE TABLE IF NOT EXISTS rates
(
    rate_id        INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    max_persons INT NOT NULL,
    price  INT NOT NULL
    );

CREATE TABLE IF NOT EXISTS booking
(
    booking_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    guest_id              INT REFERENCES guests (guest_id),
    room_id        INT REFERENCES rooms (room_id),
    start_date DATE,
    end_date DATE,
    CONSTRAINT unique_link UNIQUE (guest_id, room_id)
    );

INSERT INTO rates (max_persons, price)
VALUES (1, 1000),  -- 1
       (2, 1500),  -- 2
       (3, 2000),  -- 3
       (4, 3000);  -- 4

INSERT INTO guests (first_name, last_name, phone, email)
VALUES ('Мария', 'Субботина', '+1(123)123 1111', 'maria@gmail.com')      -- 1
       ('Степан', 'Морозов', '+1(123)123 2222', 'moroz@gmail.com'),      -- 2
       ('Владислав', 'Снежнев', '+1(123)123 3333', 'vlad@gmail.com'),    -- 3
       ('Иван', 'Суворов', '+1(123)123 4444', 'ivan@gmail.com');         -- 4

INSERT INTO rooms (rate)
VALUES (1),        -- 1
       (1),        -- 2
       (1),        -- 3
       (2),        -- 4
       (3);        -- 5

INSERT INTO booking (guest_id, room_id, start_date, end_date)
VALUES (1, 1, '2024-02-01', '2024-02-07');