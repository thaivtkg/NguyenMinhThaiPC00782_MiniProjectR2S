
use `nmtlaptop`;
INSERT INTO accounts VALUES ('admin','admin','Nguyễn Minh Thái','Thainmpc00782@gmail.com');



INSERT INTO role VALUES ('ADMIN','Administrator');
INSERT INTO role VALUES ('GUEST','Guests');
INSERT INTO role VALUES ('USER','USERS');

INSERT INTO address VALUES (1,'admin','Cần Thơ, Ninh Kiều');
INSERT INTO address VALUES (2,'admin','Cần Thơ,Cái Răng');
INSERT INTO address VALUES (3,'admin','Tp Hồ Chí Minh, Quận 1');
INSERT INTO address VALUES (4,'admin','Vĩnh long');

--
-- Dumping data for table `authority`
--

INSERT INTO authority VALUES (4,'admin','ADMIN');
INSERT INTO authority VALUES (5,'admin','USER');
INSERT INTO authority VALUES (6,'admin','GUEST');



INSERT INTO brand VALUES ('ACE ','Acer');
INSERT INTO brand VALUES ('ASU ','ASUS');
INSERT INTO brand VALUES ('DEL ','DELL');
INSERT INTO brand VALUES ('HP  ','HP');
INSERT INTO brand VALUES ('LEN ','Lenovo');
INSERT INTO brand VALUES ('MSI ','MSI');





INSERT INTO products VALUES (1,'Laptop Dell Vostro 14',17000000,'2021-06-22',_binary '',92,'DEL ','dellVostro.jpg');
INSERT INTO products VALUES (2,'Laptop ASUS TUF Gaming',25490000,'2021-06-20',_binary '',44,'ASU ','tuf.jpg');
INSERT INTO products VALUES (3,'Laptop Lenovo Legion 5',21890000,'2021-06-20',_binary '',96,'LEN ','lenovoLegion.jpg');
INSERT INTO products VALUES (4,'Laptop ACER Nitro 5',25490000,'2021-06-20',_binary '',49,'ACE ','nitro2020.jpg');
INSERT INTO products VALUES (5,'Laptop MSI Prestige 14 EVO',27490000,'2021-06-20',_binary '',2,'MSI ','msiprestige.jpg');
INSERT INTO products VALUES (6,'Laptop ASUS ROG Strix G',24990000,'2021-06-20',_binary '\0',0,'ASU ','ezgif-3-7ffbebd7470e.jpg');
INSERT INTO products VALUES (7,'dad',1000000,'2021-09-08',_binary '',100,'ACE ','3d408854.jpg');





-- Dump completed
