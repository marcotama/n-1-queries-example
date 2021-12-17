BEGIN TRANSACTION;

INSERT INTO Country(id, name)
VALUES (1, 'Serbia');

INSERT INTO Country(id, name)
VALUES (2, 'France');

INSERT INTO Country(id, name)
VALUES (3, 'USA');

INSERT INTO Country(id, name)
VALUES (4, 'Australia');

INSERT INTO Country(id, name)
VALUES (5, 'UK');

INSERT INTO Country(id, name)
VALUES (6, 'Spain');



INSERT INTO City(id, name, country_id)
VALUES (1, 'Belgrade', 1);

INSERT INTO City(id, name, country_id)
VALUES (2, 'Novi Sad', 1);

INSERT INTO City(id, name, country_id)
VALUES (3, 'Niš', 1);

INSERT INTO City(id, name, country_id)
VALUES (4, 'Paris', 2);

INSERT INTO City(id, name, country_id)
VALUES (5, 'Lyon', 2);

INSERT INTO City(id, name, country_id)
VALUES (6, 'Nice', 2);

INSERT INTO City(id, name, country_id)
VALUES (7, 'New York', 3);

INSERT INTO City(id, name, country_id)
VALUES (8, 'Los Angeles', 3);

INSERT INTO City(id, name, country_id)
VALUES (9, 'Chicago', 3);

INSERT INTO City(id, name, country_id)
VALUES (10, 'Melbourne', 4);

INSERT INTO City(id, name, country_id)
VALUES (11, 'Sydney', 4);

INSERT INTO City(id, name, country_id)
VALUES (12, 'Canberra', 4);

INSERT INTO City(id, name, country_id)
VALUES (13, 'London', 5);

INSERT INTO City(id, name, country_id)
VALUES (14, 'Birmingham', 5);

INSERT INTO City(id, name, country_id)
VALUES (15, 'Glasgow', 5);

INSERT INTO City(id, name, country_id)
VALUES (16, 'Madrid', 6);

INSERT INTO City(id, name, country_id)
VALUES (17, 'Barcelona', 6);


INSERT INTO Player (id, name, country_id, birth_date)
VALUES (1, 'Djokovic', 1, '1987-05-22');

INSERT INTO Player (ID, name, country_id, birth_date)
VALUES (2, 'Monfils', 2, '1986-09-01');

INSERT INTO Player (ID, name, country_id, birth_date)
VALUES (3, 'Isner', 3, '1985-04-26');

INSERT INTO Player (ID, name, country_id, birth_date)
VALUES (4, 'Nadal', 6, '1986-06-03');


INSERT INTO Competition(id, name, host_country_id)
VALUES (1, 'Australian Open', 4);

INSERT INTO Competition(id, name, host_country_id)
VALUES (2, 'US Open', 3);

INSERT INTO Competition(id, name, host_country_id)
VALUES (3, 'Wimbledon', 5);

INSERT INTO Competition(id, name, host_country_id)
VALUES (4, 'Roland-Garros', 2);


INSERT INTO Competition_Instance(id, competition_id, year, winner_id)
VALUES (1, 1, 2021, 1);

INSERT INTO Competition_Instance(id, competition_id, year, winner_id)
VALUES (2, 1, 2020, 1);

INSERT INTO Competition_Instance(id, competition_id, year, winner_id)
VALUES (3, 2, 2021, 1);

INSERT INTO Competition_Instance(id, competition_id, year, winner_id)
VALUES (4, 2, 2020, 4);

INSERT INTO Competition_Instance(id, competition_id, year, winner_id)
VALUES (5, 3, 2021, 1);


INSERT INTO Brand(id, name)
VALUES (1, 'Babolat');

INSERT INTO Brand(id, name)
VALUES (2, 'Head');

INSERT INTO Brand(id, name)
VALUES (3, 'Wilson');

INSERT INTO Brand(id, name)
VALUES (4, 'Prince');


INSERT INTO Sponsor(player_id, brand_id)
VALUES (1, 1);

INSERT INTO Sponsor(player_id, brand_id)
VALUES (1, 2);

INSERT INTO Sponsor(player_id, brand_id)
VALUES (2, 4);

INSERT INTO Sponsor(player_id, brand_id)
VALUES (2, 1);

INSERT INTO Sponsor(player_id, brand_id)
VALUES (3, 3);

COMMIT;