create table books
(
    id         bigserial primary key,
    isbn       text      not null unique,
    title      text      not null,
    author     text      not null,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    version    integer not null default 0
);

insert into books(isbn, title, author) VALUES
('978-0-14-028005-1', 'The Great Gatsby', 'F. Scott Fitzgerald'),
('978-0-14-028006-2', 'To Kill a Mockingbird', 'Harper Lee'),
('978-0-14-028007-3', 'Pride and Prejudice', 'Jane Austen'),
('978-0-14-028008-4', 'James Bond 007', 'Ian Fleming')
;