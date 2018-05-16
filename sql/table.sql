CREATE TABLE IF NOT EXISTS documents(
    id integer not null,
    number integer not null,
    date timestamp not null,
    primary key(id,number)
)