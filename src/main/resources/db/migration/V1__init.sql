create table product
(
    id               bigserial primary key,
    description      varchar(255),
    base_price       numeric(38, 2),
    current_price    numeric(38, 2),
    stock_quantity   bigint,
    status           varchar(255),
    creation_date    timestamp(6) with time zone,
    last_update_date timestamp(6) with time zone
);

insert into product(description, base_price, current_price, stock_quantity, status, creation_date)
values
    ('Laptop', 1000.00, 950.00, 50, 'ACTIVE', now()),
    ('Smartphone', 600.00, 550.00, 150, 'ACTIVE', now()),
    ('Headphones', 150.00, 120.00, 200, 'ACTIVE', now()),
    ('Keyboard', 80.00, 70.00, 300, 'ACTIVE', now()),
    ('Monitor', 300.00, 280.00, 100, 'ACTIVE', now()),
    ('Tablet', 400.00, 370.00, 75, 'ACTIVE', now()),
    ('Smartwatch', 250.00, 230.00, 120, 'ACTIVE', now()),
    ('External Hard Drive', 120.00, 110.00, 80, 'ACTIVE', now());

create table category
(
    id                 bigserial primary key,
    name               varchar(255),
    description        varchar(255),
    parent_category_id bigint references category (id)
);

insert into category(name, description, parent_category_id)
values
    ('Electronics', 'Devices and gadgets including computers, phones, and accessories', NULL),
    ('Laptops', 'Portable computers for work and entertainment', 1),
    ('Smartphones', 'Mobile phones with advanced features', 1),
    ('Accessories', 'Additional items for electronic devices', 1),
    ('Headphones', 'Audio devices for listening to music or taking calls', 3),
    ('Smartwatches', 'Wearable devices that pair with smartphones', 3),
    ('Home Appliances', 'Household devices for everyday tasks', NULL),
    ('Kitchen Appliances', 'Devices for food preparation and cooking', 7),
    ('Cleaning Appliances', 'Devices for maintaining cleanliness in the home', 7),
    ('Furniture', 'Indoor and outdoor furniture for home and office', NULL),
    ('Office Furniture', 'Furniture designed for office spaces', 10),
    ('Living Room Furniture', 'Furniture for living room spaces', 10);

create table product_category
(
    product_id  bigint references product (id),
    category_id bigint references category (id)
);

create table customer
(
    id                    bigserial primary key,
    name                  varchar(255),
    email                 varchar(255),
    password              varchar(255),
    address_information   varchar(255),
    phone_number          varchar(255),
    account_creation_date timestamp(6) with time zone,
    membership_tier       varchar(255)
);

create table t_order
(
    id                   bigserial primary key,
    customer_id          bigint references customer (id),
    order_date           timestamp(6) with time zone,
    status               varchar(255),
    billing_address      varchar(255),
    shipping_address     varchar(255),
    payment_method       varchar(255),
    subtotal             numeric(38, 2),
    shipping_cost        numeric(38, 2),
    tax_amount           numeric(38, 2),
    discount_amount      numeric(38, 2),
    total_price          numeric(38, 2),
    tracking_information varchar(255)
);


create table order_item(
    id bigserial primary key,
    product_id bigint references product(id),
    quantity bigint,
    price numeric(38,2),
    order_id bigint references t_order(id)
);

create table cart(
    id bigserial primary key,
    customer_id bigint references customer(id),
    creation_date timestamp(6) with time zone,
    last_updated_date timestamp(6) with time zone,
    total_price numeric(38,2),
    status varchar(255)
);

create table cart_item(
    id bigserial primary key,
    cart_id bigint references cart(id),
    product_id bigint references product(id),
    quantity numeric(38,2)
);

create table payment(
    id bigserial primary key,
    order_id bigint references t_order(id),
    amount numeric(38,2),
    method varchar(255),
    status varchar(255),
    transaction_date timestamp(6) with time zone,
    transaction_reference varchar(255)
);