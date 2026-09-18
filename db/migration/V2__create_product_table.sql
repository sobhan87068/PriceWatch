CREATE TABLE IF NOT EXISTS product
(
    id         bigserial primary key not null,
    name       varchar(255),
    url        varchar(500) unique,
    currency   varchar(3),
    created_at timestamp             not null,
    updated_at timestamp default null,
    deleted_at timestamp default null
);