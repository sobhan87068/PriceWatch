CREATE TABLE IF NOT EXISTS price_history
(
    id         bigserial primary key not null,
    product_id bigint                not null
        constraint history_product_id_fk
            references product,
    price      decimal(19, 4),
    created_at timestamp             not null,
    updated_at timestamp default null,
    deleted_at timestamp default null
);

