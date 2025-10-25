create table posts
(
    id         bigserial primary key,
    title      text      not null,
    body       text      not null,
    user_id    bigint    not null,
    status     text      not null,
    version    integer,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

insert into posts (title, body, user_id, status)
values ('First post', 'This is the first post', 1, 'DRAFT'),
       ('Second post', 'This is the second post', 2, 'PUBLISHED'),
       ('Third post', 'This is the third post', 1, 'DRAFT')
       ;