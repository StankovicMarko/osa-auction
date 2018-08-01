-- create database sf53_2015;
use sf53_2015;

insert into users (id, username, password, firstname, lastname, email, enabled, lastpasswordresetdate) values (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, '2017-03-05');
insert into users (id, username, password, firstname, lastname, email, enabled, lastpasswordresetdate) values (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', 1, '2017-03-05');
insert into users (id, username, password, firstname, lastname, email, enabled, lastpasswordresetdate) values (3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', 0, '2017-03-05');

insert into authority (id, name) values  (1, 'ROLE_USER');
insert into authority (id, name) values  (2, 'ROLE_ADMIN');

-- insert into user_authority (user_id, authority_id) values (1, 1);
insert into user_authority (user_id, authority_id) values (1, 2);
insert into user_authority (user_id, authority_id) values (2, 1);
insert into user_authority (user_id, authority_id) values (3, 1);


insert into items (item_id, item_desc, item_name, item_pic, item_sold, owner) 
	values (1, 'aaaaaa', 'aaaa5', '/images/item.png', null, 'admin');

insert into items (item_id, item_desc, item_name, item_pic, item_sold, owner) 
	values (2, 'bbbbb', 'bbbbb', '/images/aitem.png', null, 'user');

insert into auctions (auction_id, end_date, start_date, start_price, item_id, user_id) 
	values (1, '2017-03-05', '2017-03-05', 50, 1, 1);

insert into auctions (auction_id, end_date, start_date, start_price, item_id, user_id) 
	values (2, '2017-03-05', '2017-03-05', 510, 2, 2);


