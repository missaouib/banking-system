
create table user
(
    id int auto_increment
        primary key,
    password varchar(255) null,
    username varchar(255) null
);

create table account_holder
(
    city varchar(255) null,
    country varchar(255) null,
    postal_code varchar(255) null,
    street varchar(255) null,
    date_of_birth date null,
    mailing_city varchar(255) null,
    mailing_country varchar(255) null,
    mailing_postal_code varchar(255) null,
    mailing_street varchar(255) null,
    id int not null
        primary key,
    constraint FK5tyfveeddp5dqy88r0yl3x5vw
        foreign key (id) references user (id)
);

create table account
(
    id int auto_increment
        primary key,
    amount decimal(19,2) null,
    currency varchar(255) null,
    creation_date date null,
    penalty_fee decimal(19,2) null,
    status varchar(255) null,
    primary_owner_id int null,
    secondary_owner_id int null,
    constraint FKa4ukcqpfubnnuxjp9sqwfvj1c
        foreign key (secondary_owner_id) references account_holder (id),
    constraint FKeb4hr0wmc9ab52ulodr1dcoq2
        foreign key (primary_owner_id) references account_holder (id)
);

create table admin
(
    id int not null
        primary key,
    constraint FK1ja8rua032fgnk9jmq7du3b3a
        foreign key (id) references user (id)
);

create table checking
(
    minimum_balance decimal(19,2) null,
    monthly_maintenance_fee decimal(19,2) null,
    secret_key varchar(255) null,
    id int not null
        primary key,
    constraint FK4pww71v1myyn2iai6qm84t29o
        foreign key (id) references account (id)
);

create table credit_card
(
    credit_limit decimal(19,2) null,
    interest_rate decimal(11,4) null,
    paid_interest_rate date null,
    id int not null
        primary key,
    constraint FK5nn2ykrc28pst4v0axopldeqv
        foreign key (id) references account (id)
);

create table role
(
    id int auto_increment
        primary key,
    name varchar(255) null,
    user_id int null,
    constraint FK61g3ambult7v7nh59xirgd9nf
        foreign key (user_id) references user (id)
);

create table savings
(
    interest_rate decimal(11,4) null,
    minimum_balance decimal(19,2) null,
    paid_interest_rate date null,
    secret_key varchar(255) null,
    id int not null
        primary key,
    constraint FKs02t0s57xrunyqosm96vrttin
        foreign key (id) references account (id)
);

create table student_checking
(
    secret_key varchar(255) null,
    id int not null
        primary key,
    constraint FKcw2ja1qwe1e6xhuj4e1o9jvy6
        foreign key (id) references account (id)
);

create table third_party
(
    hashed_key varchar(255) null,
    id int not null
        primary key,
    constraint FKtcjpve90sq4j3esfvti8jbba2
        foreign key (id) references user (id)
);

create table transaction
(
    id int auto_increment
        primary key,
    amount decimal(19,2) null,
    currency varchar(255) null,
    creation_date datetime(6) null,
    destination_account_id int null,
    origin_account_id int null,
    constraint FKafme1ysrc4lieofon8skwdosy
        foreign key (origin_account_id) references account (id),
    constraint FKan2cq79w1pqavplm9nbu5aef0
        foreign key (destination_account_id) references account (id)
);

