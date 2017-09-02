create table users (
    id serial primary key,
    username varchar(100) unique not null,
    password varchar(255) not null,
    active boolean
);

insert into users(username, password, active)
values('tamami','rahasia',true);

insert into users(username, password, active)
values('adi','rahasia',true);

create table permission (
    id serial primary key,
    id_user integer not null references users(id),
    user_role varchar(255) not null
);

insert into permission (id_user, user_role)
values(1, 'ROLE_SUPERVISOR');

insert into permission (id_user, user_role)
values(1, 'ROLE_OPERATOR');

insert into permission (id_user, user_role)
values(2, 'ROLE_OPERATOR');

