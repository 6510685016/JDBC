CREATE TABLE person (
                        ID INT NOT NULL PRIMARY KEY,
                        name VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_thai_520_w2 NOT NULL,
                        surname VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_thai_520_w2 NOT NULL,
                        birthdate DATE NOT NULL,
                        address VARCHAR(300) CHARACTER SET utf8 COLLATE utf8_thai_520_w2 NOT NULL
);
