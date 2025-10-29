create table users
(
    id      bigserial primary key,
    email   text    not null unique,
    name    text    not null,
    version integer not null default 0
);

create table addresses
(
    users     bigint,
    users_key bigint,
    street    text not null unique,
    city      text not null,
    country   text not null,
    version   integer
);
