DROP TABLE IF EXISTS students, faculties;
DROP TABLE IF EXISTS animals, pets;

CREATE TABLE students(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
fio VARCHAR(256),
age INT,
faculties_id BIGINT
);

CREATE TABLE faculties(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(256)
);

ALTER TABLE students add foreign key (faculties_id) references faculties(id);

CREATE TABLE animals(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
vie VARCHAR(256),
wei INT,
homePets_id BIGINT
);

CREATE TABLE pets(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
homePet VARCHAR(256)
);

ALTER TABLE animals add foreign key (homePets_id) references pets(id);