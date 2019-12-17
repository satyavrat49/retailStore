set search_path to Public;

CREATE TABLE Public.role(
   role_id bigserial PRIMARY KEY,
   role_name VARCHAR (255) UNIQUE NOT NULL
);
insert into Public.role(role_id,role_name) values(1,'Employee');
insert into Public.role(role_id,role_name) values(2,'Store affilated');
insert into Public.role(role_id,role_name) values(3,'Others');

CREATE TABLE Public.Users(
   user_id bigserial PRIMARY KEY,
   username VARCHAR (50) UNIQUE NOT NULL,
   email VARCHAR (355) UNIQUE NOT NULL,
   role_id integer  REFERENCES Public.role(role_id) ON UPDATE CASCADE,
   created_on DATE NOT NULL DEFAULT CURRENT_DATE ,
   last_login TIMESTAMP
);

CREATE SEQUENCE users_seq_user_id start 1 increment 1;

insert into Public.Users(username,email,role_id,created_on,last_login) values ('Ram','Ram@Gmail.com',1,now(),now());
insert into Public.Users(username,email,role_id,created_on,last_login) values ('shyam','shyam@Gmail.com',2,now(),now());
insert into Public.Users(username,email,role_id,created_on,last_login) values ('Dan','Dan@Gmail.com',3,now(),now());
insert into Public.Users(username,email,role_id,created_on,last_login) values ('john','john@Gmail.com',3,now(),now());





CREATE TABLE Public.product_categories(
   category_id bigserial PRIMARY KEY,
   category_name VARCHAR (255) UNIQUE NOT NULL
);

insert into Public.product_categories(category_id,category_name) values(1,'groceries');
	insert into Public.product_categories(category_id,category_name) values(2,'electronics');


CREATE TABLE Public.product(
   product_id bigserial PRIMARY KEY,
   product_name VARCHAR (255) UNIQUE NOT NULL,
   product_cost Numeric,
   category_id  integer  REFERENCES Public.product_categories(category_id) ON UPDATE CASCADE
);


insert into Public.product(product_id,product_name,product_cost,category_id) values(1,'beans','10',1);
insert into Public.product(product_id,product_name,product_cost,category_id) values(2,'rice','10',1);
insert into Public.product(product_id,product_name,product_cost,category_id) values(3,'Phone','100',2);
insert into Public.product(product_id,product_name,product_cost,category_id) values(4,'TV','1000',2);
alter table product alter column product_cost TYPE VARCHAR;