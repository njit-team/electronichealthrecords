create table sample_data
(
	id int auto_increment
		primary key,
	first_name varchar(100) null,
	last_name varchar(100) null,
	company_name varchar(100) null,
	street varchar(100) null,
	city varchar(100) null,
	state varchar(100) null,
	zip_code varchar(100) null,
	phone_number varchar(100) null,
	phone_number_2 varchar(100) null,
	email varchar(50) null,
	web varchar(100) null,
	country varchar(100) null,
	constraint sample_data_email_uindex
		unique (email)
);
