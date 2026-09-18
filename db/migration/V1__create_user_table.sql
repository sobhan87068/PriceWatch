CREATE TABLE IF NOT EXISTS users(
    id bigserial primary key not null,
    email varchar(255) unique,
    password varchar(255),
    created_at timestamp not null,
    updated_at timestamp default null,
    deleted_at timestamp default null
);