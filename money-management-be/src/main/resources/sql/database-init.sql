-- USERS --

DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    id            bigint                                              NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    create_date   timestamp(0) without time zone NOT NULL,
    date_of_birth date                                                NOT NULL,
    email         character varying(50) COLLATE pg_catalog."default"  NOT NULL,
    full_name     character varying(100) COLLATE pg_catalog."default" NOT NULL,
    gender        character varying(10) COLLATE pg_catalog."default"  NOT NULL,
    password      character varying(255) COLLATE pg_catalog."default" NOT NULL,
    username      character varying(50) COLLATE pg_catalog."default"  NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
    CONSTRAINT uk_r43af9ap4edm43mmtq01oddj6 UNIQUE (username)
);

-- ADDRESSES --

DROP TABLE IF EXISTS public.addresses;

CREATE TABLE IF NOT EXISTS public.addresses
(
    id      bigint NOT NULL DEFAULT nextval('addresses_id_seq'::regclass),
    city    character varying(50) COLLATE pg_catalog."default",
    country character varying(50) COLLATE pg_catalog."default",
    street  character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT addresses_pkey PRIMARY KEY (id)
);

-- STORES --

DROP TABLE IF EXISTS public.addresses;

CREATE TABLE IF NOT EXISTS public.stores
(
    id         bigint                                              NOT NULL DEFAULT nextval('stores_id_seq'::regclass),
    name       character varying(100) COLLATE pg_catalog."default" NOT NULL,
    address_id bigint,
    user_id    bigint                                              NOT NULL,
    CONSTRAINT stores_pkey PRIMARY KEY (id),
    CONSTRAINT fkiw281hibigo41ijsvot42osjj FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkn6g5fy0yv9wg5lm00wsjmnfj1 FOREIGN KEY (address_id)
        REFERENCES public.addresses (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- PRODUCTS_CATEGORIES --

DROP TABLE IF EXISTS public.products_categories;

CREATE TABLE IF NOT EXISTS public.products_categories
(
    id      bigint                                             NOT NULL DEFAULT nextval('products_categories_id_seq'::regclass),
    name    character varying(50) COLLATE pg_catalog."default" NOT NULL,
    user_id bigint                                             NOT NULL,
    CONSTRAINT products_categories_pkey PRIMARY KEY (id),
    CONSTRAINT fk520dg3br2ui414h6gjrmpk65p FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- PRODUCTS_TYPES --

DROP TABLE IF EXISTS public.products_types;

CREATE TABLE IF NOT EXISTS public.products_types
(
    id                  bigint                                             NOT NULL DEFAULT nextval('products_types_id_seq'::regclass),
    brand               character varying(50) COLLATE pg_catalog."default",
    name                character varying(50) COLLATE pg_catalog."default" NOT NULL,
    product_category_id bigint                                             NOT NULL,
    user_id             bigint                                             NOT NULL,
    CONSTRAINT products_types_pkey PRIMARY KEY (id),
    CONSTRAINT fkpqj7nrlpxej2d8bepw15ts00b FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkrbfha7gs0qtqo1qbb16m0f1jb FOREIGN KEY (product_category_id)
        REFERENCES public.products_categories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- PRODUCTS --

DROP TABLE IF EXISTS public.products;

CREATE TABLE IF NOT EXISTS public.products
(
    id                   bigint         NOT NULL DEFAULT nextval('products_id_seq'::regclass),
    date                 date           NOT NULL,
    percent_discount     numeric(19, 2),
    price_after_discount numeric(19, 2) NOT NULL,
    price_discount       numeric(19, 2),
    quantity             numeric(19, 2) NOT NULL,
    standard_price       numeric(19, 2) NOT NULL,
    total_price          numeric(19, 2) NOT NULL,
    product_type_id      bigint         NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (id),
    CONSTRAINT fkgjjutj0gotbew3lyh6kmukid2 FOREIGN KEY (product_type_id)
        REFERENCES public.products_types (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- EXPENSES_CATEGORIES --

DROP TABLE IF EXISTS public.expenses_categories;

CREATE TABLE IF NOT EXISTS public.expenses_categories
(
    id      bigint                                             NOT NULL DEFAULT nextval('expenses_categories_id_seq'::regclass),
    name    character varying(50) COLLATE pg_catalog."default" NOT NULL,
    user_id bigint                                             NOT NULL,
    CONSTRAINT expenses_categories_pkey PRIMARY KEY (id),
    CONSTRAINT fk1dbbl25kk5bfhdspvckoekf5h FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- EXPENSES_SUB_CATEGORIES --

DROP TABLE IF EXISTS public.expenses_sub_categories;

CREATE TABLE IF NOT EXISTS public.expenses_sub_categories
(
    id                  bigint                                             NOT NULL DEFAULT nextval('expenses_sub_categories_id_seq'::regclass),
    name                character varying(50) COLLATE pg_catalog."default" NOT NULL,
    expense_category_id bigint                                             NOT NULL,
    CONSTRAINT expenses_sub_categories_pkey PRIMARY KEY (id),
    CONSTRAINT fkhcqr485w978j9t5v442fscyys FOREIGN KEY (expense_category_id)
        REFERENCES public.expenses_categories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- EXPENSES --

DROP TABLE IF EXISTS public.expenses;

CREATE TABLE IF NOT EXISTS public.expenses
(
    id              bigint         NOT NULL DEFAULT nextval('expenses_id_seq'::regclass),
    date            date           NOT NULL,
    description     character varying(500) COLLATE pg_catalog."default",
    discount        numeric(19, 2),
    price           numeric(19, 2) NOT NULL,
    category_id     bigint         NOT NULL,
    store_id        bigint         NOT NULL,
    sub_category_id bigint         NOT NULL,
    user_id         bigint         NOT NULL,
    CONSTRAINT expenses_pkey PRIMARY KEY (id),
    CONSTRAINT fk579iih66hitxicsut51oa9iwu FOREIGN KEY (store_id)
        REFERENCES public.stores (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkbkyqp8jeaw4swbfhlbsy3n9g9 FOREIGN KEY (sub_category_id)
        REFERENCES public.expenses_sub_categories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkfn3fkwaxkexiriqae1459n27n FOREIGN KEY (category_id)
        REFERENCES public.expenses_categories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkhpk0n2cbnfiuu5nrgl0ika3hq FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- EXPENSES_PRODUCTS --

DROP TABLE IF EXISTS public.expenses_products;

CREATE TABLE IF NOT EXISTS public.expenses_products
(
    expense_entity_id bigint NOT NULL,
    products_id       bigint NOT NULL,
    CONSTRAINT uk_o26bg43omcct40q07dfhkfnti UNIQUE (products_id),
    CONSTRAINT fkhr5u2h9ya9swgljs8fopdq173 FOREIGN KEY (expense_entity_id)
        REFERENCES public.expenses (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fklcf0xnq42q9r95qbgym4acido FOREIGN KEY (products_id)
        REFERENCES public.products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);