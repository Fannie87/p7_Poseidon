create table bidlist (bid_list_id integer not null, account varchar(255), ask float(53), ask_quantity float(53), benchmark varchar(255), bid float(53), bid_list_date datetime(6), bid_quantity float(53), book varchar(255), commentary varchar(255), creation_date datetime(6), creation_name varchar(255), deal_name varchar(255), deal_type varchar(255), revision_date datetime(6), revision_name varchar(255), security varchar(255), side varchar(255), source_list_id varchar(255), status varchar(255), trader varchar(255), type varchar(255), primary key (bid_list_id)) engine=InnoDB
create table curvepoint (id integer not null, as_of_date datetime(6), creation_date datetime(6), curve_id integer, term float(53), value float(53), primary key (id)) engine=InnoDB
create table rating (id integer not null, fitch_rating varchar(255), moodys_rating varchar(255), order_number integer, sandprating varchar(255), primary key (id)) engine=InnoDB
create table rulename (id integer not null, description varchar(255), json varchar(255), name varchar(255), sql_part varchar(255), sql_str varchar(255), template varchar(255), primary key (id)) engine=InnoDB
create table trade (trade_id integer not null, account varchar(255), benchmark varchar(255), book varchar(255), buy_price float(53), buy_quantity float(53), creation_date datetime(6), creation_name varchar(255), deal_name varchar(255), deal_type varchar(255), revision_date datetime(6), revision_name varchar(255), security varchar(255), sell_price float(53), sell_quantity float(53), side varchar(255), source_list_id varchar(255), status varchar(255), trade_date datetime(6), trader varchar(255), type varchar(255), primary key (trade_id)) engine=InnoDB
create table users (id integer not null, fullname varchar(255), password varchar(255), role varchar(255), username varchar(255), primary key (id)) engine=InnoDB
create table users_seq (next_val bigint) engine=InnoDB