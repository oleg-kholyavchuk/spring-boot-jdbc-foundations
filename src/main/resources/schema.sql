
DROP TABLE IF EXISTS animals, pets;

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