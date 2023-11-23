create table priorities (
    id serial primary key,
    name text unique not null,
    position int
);

insert into priorities (name, position) values ('urgently', 1);
insert into priorities (name, position) values ('normal', 2);
