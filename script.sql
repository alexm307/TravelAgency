create table destination
(
    id   varchar(255) not null
        primary key,
    name varchar(255) null
);

create table hibernate_sequence
(
    next_val bigint null
);

create table package
(
    id             varchar(255) not null
        primary key,
    availableSpots int          null,
    details        varchar(255) null,
    fromDate       datetime     null,
    name           varchar(255) null,
    price          double       null,
    spots          int          null,
    status         int          null,
    toDate         datetime     null,
    dest_id        varchar(255) null,
    constraint FKjdui7m7w0bbt4cqd23ku2y8i6
        foreign key (dest_id) references destination (id)
);

create table user
(
    id       varchar(255) not null
        primary key,
    email    varchar(255) null,
    password varchar(255) null,
    username varchar(255) null
);

create table user_package
(
    user_id    varchar(255) not null,
    package_id varchar(255) not null,
    constraint FK8n8qhfs5eceli4n13yf6u1agp
        foreign key (user_id) references user (id),
    constraint FKjcwapl2htr7k7l485y29cinog
        foreign key (package_id) references package (id)
);


