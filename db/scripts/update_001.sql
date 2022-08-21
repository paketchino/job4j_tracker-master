create table if not exists items (
    id serial primary key,
    name text not null,
    description text not null,
    created timestamp
);