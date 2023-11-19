-- Table to store user information
CREATE TABLE  IF NOT EXISTS  USERS (
                                     userid INT PRIMARY KEY AUTO_INCREMENT,
                                     username VARCHAR(20) UNIQUE,
                                     salt VARCHAR(255),
                                     password TEXT ,
                                     firstname VARCHAR(20),
                                     lastname VARCHAR(20)
);

-- Table to store notes associated with users
CREATE TABLE  IF NOT EXISTS  NOTES (
                                     noteid INT PRIMARY KEY AUTO_INCREMENT,
                                     notetitle VARCHAR(255),
                                     notedescription VARCHAR(1000),
                                     userid INT,
                                     FOREIGN KEY (userid) REFERENCES USERS(userid)
);

-- Table to store files associated with users
CREATE TABLE  IF NOT EXISTS  FILES (
                                     fileId INT PRIMARY KEY AUTO_INCREMENT,
                                     filename VARCHAR(255),
                                     contenttype VARCHAR(255),
                                     filesize VARCHAR(255),
                                     userid INT,
                                     filedata BLOB,
                                     FOREIGN KEY (userid) REFERENCES USERS(userid)
);

-- Table to store credentials associated with users
-- CREATE TABLE IF NOT EXISTS CREDENTIALS
CREATE TABLE  IF NOT EXISTS  CREDENTIALS (
                                           credentialid INT PRIMARY KEY AUTO_INCREMENT,
                                           url VARCHAR(100),
                                           username VARCHAR(30),
                                           keyname VARCHAR(255), -- Assuming 'key' is a reserved keyword, changed to 'keyname'
                                           password TEXT,
                                           userid INT,
                                           FOREIGN KEY (userid) REFERENCES USERS(userid)
);

-- Table to store user authorities
CREATE TABLE  IF NOT EXISTS  AUTHORITIES (
                             USERNAME VARCHAR(128) NOT NULL,
                             AUTHORITY VARCHAR(128) NOT NULL,
                             PRIMARY KEY (USERNAME, AUTHORITY),
                             CONSTRAINT AUTHORITIES_UNIQUE UNIQUE (USERNAME, AUTHORITY),
                             CONSTRAINT AUTHORITIES_FK1 FOREIGN KEY (USERNAME) REFERENCES USERS (USERNAME)
);
