INSERT INTO gyms (name, address, phone, email, opening_time, closing_time)
VALUES
    ('Gym Max', 'Main Street 123', '123-456-789', 'info@gymmax.com', '06:00:00', '22:00:00'),
    ('Gym Active', 'Active Road 45', '111-222-333', 'contact@active.com', '07:00:00', '23:00:00'),
    ('Gym Fit', 'Fit Avenue 12', '987-654-321', 'hello@gymfit.com', '06:00:00', '21:00:00'),
    ('Gym Power', 'Power Street 99', '555-666-777', 'power@gym.com', '08:00:00', '20:00:00'),
    ('Gym Elite', 'Elite Blvd 100', '888-999-000', 'elite@gym.com', '05:30:00', '22:30:00');

INSERT INTO employees (first_name, last_name, email, phone, role, gym_id)
VALUES
    ('Adam', 'Kowalski', 'adam.k@fitness.com', '500-100-200', 'TRAINER', 1),
    ('Ewa', 'Nowak', 'ewa.n@fitness.com', '500-300-400', 'MANAGER', 1),
    ('Robert', 'Lew', 'robert.l@fitness.com', '600-111-222', 'TRAINER', 2),
    ('Monika', 'Ziel', 'monika.z@fitness.com', '600-333-444', 'RECEPTIONIST', 3),
    ('Kamila', 'Marek', 'kamila.m@fitness.com', '600-555-666', 'RECEPTIONIST', 5);

INSERT INTO equipment (name, description, status, serial_number, gym_id)
VALUES
    ('Treadmill X1', 'High-quality treadmill', 'ACTIVE', 'SN-TRM-001', 1),
    ('Rowing Machine A', 'Basic rowing machine', 'MAINTENANCE', 'SN-RWM-002', 1),
    ('Bench Press Pro', 'Heavy-duty bench press', 'ACTIVE', 'SN-BCH-101', 2),
    ('Elliptical E3', 'Elliptical machine with TV screen', 'ACTIVE', 'SN-ELL-210', 2),
    ('Spinning Bike S1', 'Premium spinning bike', 'OUT_OF_ORDER', 'SN-SPN-350', 3);

INSERT INTO group_classes (classes_name, description, trainer_id, gym_id, start_time, end_time, capacity)
VALUES
    ('YOGA', 'Morning yoga session', 1, 1, '09:00:00', '10:00:00', 20),
    ('SPINNING', 'High-intensity spinning class', 1, 1, '18:00:00', '19:00:00', 15),
    ('CROSSFIT', 'CrossFit workout', 3, 2, '17:00:00', '18:00:00', 12),
    ('ZUMBA', 'Fun dance workout', 3, 2, '19:00:00', '20:00:00', 25),
    ('BOXING', 'Beginner boxing class', 1, 1, '10:00:00', '11:00:00', 10);

INSERT INTO members (first_name, last_name, email, phone, date_of_birth, registration_date)
VALUES
    ('Jan', 'Testowy', 'jan.t@example.com', '700-111-222', '1990-03-10 00:00:00', '2023-01-10'),
    ('Anna', 'Example', 'anna.e@example.com', '700-333-444', '1985-06-15 00:00:00', '2023-02-05'),
    ('Piotr', 'Konieczny', 'piotr.k@example.com', '700-555-666', '2000-01-20 00:00:00', '2023-03-01'),
    ('Kasia', 'Zawadzka', 'kasia.z@example.com', '700-777-888', '1995-08-25 00:00:00', '2023-03-10'),
    ('Marek', 'Kowal', 'marek.k@example.com', '700-999-000', '1980-12-01 00:00:00', '2023-04-01');

INSERT INTO memberships (type, start_date, end_date, member_id)
VALUES
    ('MONTHLY', '2023-03-01', '2023-03-31', 1),
    ('ANNUAL', '2023-01-01', '2023-12-31', 2),
    ('WEEKLY', '2023-02-15', '2023-03-15', 3),
    ('SELF_REPEATABLE', '2023-03-20', '2023-06-20', 4),
    ('MONTHLY', '2023-04-01', '2023-04-30', 5);

INSERT INTO class_enrollments (enrollment_date, member_id, class_id)
VALUES
    ('2023-03-10 08:15:00', 1, 1),
    ('2023-03-10 08:20:00', 2, 1),
    ('2023-03-10 09:00:00', 3, 3),
    ('2023-03-10 09:05:00', 1, 2),
    ('2023-03-10 10:00:00', 4, 4);
