--
-- kunde
--
INSERT INTO kunde (art, id,email, nachname) VALUES ('F',1,'meier@web.de', 'Meier');
INSERT INTO kunde (art, id,email, nachname) VALUES ('P',2,'schnell@gmx.de', 'Schnell');
INSERT INTO kunde (art, id,email, nachname) VALUES ('F',3,'baum@web.de', 'Baum');
INSERT INTO kunde (art, id,email, nachname) VALUES ('P',4,'fritz@gmail.com', 'Fritz');
INSERT INTO kunde (art, id,email, nachname) VALUES ('F',5,'witzig@web.de', 'Witzig');

--
-- adresse
--


INSERT INTO adresse (id, ort, plz, kunde_fk) VALUES (200,'Karlsruhe','76133',1);
INSERT INTO adresse (id, ort, plz, kunde_fk) VALUES (200,'Emmendingen','79312',2);
INSERT INTO adresse (id, ort, plz, kunde_fk) VALUES (200,'Herbolzheim','79336',3);
INSERT INTO adresse (id, ort, plz, kunde_fk) VALUES (200,'Karlsruhe','76133',4);
INSERT INTO adresse (id, ort, plz, kunde_fk) VALUES (200,'Karlsruhe','76133',5);