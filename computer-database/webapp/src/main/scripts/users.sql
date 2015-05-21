drop table if exists authorities;
drop table if exists users;

 create table users(
      username varchar(50) not null primary key,
      password varchar(50) not null,
      enabled boolean not null);

  create table authorities (
  	  id INT(11) NOT NULL AUTO_INCREMENT,
      username varchar(50) not null,
      authority varchar(50) not null,
      PRIMARY KEY (id),
      constraint fk_authorities_users foreign key(username) references users(username));
      create unique index ix_auth_username on authorities (username,authority);
  
INSERT INTO users(username,password,enabled) VALUES ('admin','admin', TRUE);
INSERT INTO users(username,password,enabled) VALUES ('user','user', TRUE);

INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');