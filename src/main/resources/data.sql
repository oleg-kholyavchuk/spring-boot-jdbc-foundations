INSERT INTO faculties(id, name)
VALUES(1, 'Java Development');
INSERT INTO faculties(id, name)
VALUES(2, 'Java Automation');

INSERT INTO students(id, fio, age, faculties_id)
VALUES(1, 'Ivanov', 20, 1);
INSERT INTO students(id, fio, age, faculties_id)
VALUES(2, 'Petrov', 30, 1);
INSERT INTO students(id, fio, age, faculties_id)
VALUES(3, 'Ivan', 22, 1);
INSERT INTO students(id, fio, age, faculties_id)
VALUES(4, 'Kim', 25, 2);

INSERT INTO pets(id, homePet)
VALUES(1, 'Homemade');
INSERT INTO pets(id, homePet)
VALUES(2, 'Wild');

INSERT INTO animals(id, vie, wei, homePets_id) VALUES(1, 'Giraffe', 650, 2);
INSERT INTO animals(id, vie, wei, homePets_id) VALUES(2, 'Monkey', 55, 2);
INSERT INTO animals(id, vie, wei, homePets_id) VALUES(3, 'Trout', 12, 2);
INSERT INTO animals(id, vie, wei, homePets_id) VALUES(4, 'Human', 82, 1)
