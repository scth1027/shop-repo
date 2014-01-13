--
-- kunde ID 1-
--
INSERT INTO kunde (art, id,email, nachname) VALUES ('F',1,'meier@web.de', 'Meier');
INSERT INTO kunde (art, id,email, nachname) VALUES ('P',2,'schnell@gmx.de', 'Schnell');
INSERT INTO kunde (art, id,email, nachname) VALUES ('F',3,'baum@web.de', 'Baum');
INSERT INTO kunde (art, id,email, nachname) VALUES ('P',4,'fritz@gmail.com', 'Fritz');
INSERT INTO kunde (art, id,email, nachname) VALUES ('F',5,'witzig@web.de', 'Witzig');

--
-- adresse ID 200-
--


INSERT INTO adresse (id, ort, plz, strasse, kunde_fk) VALUES (200,'Karlsruhe','76133','Hansstra�e 3',1);
INSERT INTO adresse (id, ort, plz, strasse, kunde_fk) VALUES (201,'Emmendingen','79312','Marktplatz 9',2);
INSERT INTO adresse (id, ort, plz, strasse, kunde_fk) VALUES (202,'Herbolzheim','79336','Am Sportfeld 89',3);
INSERT INTO adresse (id, ort, plz, strasse, kunde_fk) VALUES (203,'Karlsruhe','76133','Rosenweg 7',4);
INSERT INTO adresse (id, ort, plz, strasse, kunde_fk) VALUES (204,'Freiburg','78812','Talstra�e 4',5);

--
-- bestellung ID 1000-
--