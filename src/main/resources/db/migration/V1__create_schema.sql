CREATE TABLE IF NOT EXISTS catalog (
  id bigint NOT NULL,
  title VARCHAR(256) NOT NULL,
  price DECIMAL  NOT NULL,
  CONSTRAINT catalog_pkey PRIMARY KEY (id)
);