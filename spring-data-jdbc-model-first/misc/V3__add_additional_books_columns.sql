alter table books add column publisher varchar(100);
alter table books add column deleted boolean default false;
