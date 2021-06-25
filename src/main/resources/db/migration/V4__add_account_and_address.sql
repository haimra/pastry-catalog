create TABLE IF NOT EXISTS account(
  id bigint GENERATED ALWAYS AS IDENTITY,
  email_address VARCHAR(32) NOT NULL,
  password VARCHAR(32) NOT NULL,
  CONSTRAINT account_pkey PRIMARY KEY (id)
);
create TABLE IF NOT EXISTS address(
  id bigint GENERATED ALWAYS AS IDENTITY,
  email_address VARCHAR(32) NOT NULL,
  address_line1 VARCHAR(256) NOT NULL,
  address_line2 VARCHAR(256),
  postcode VARCHAR(256) NOT NULL,
  CONSTRAINT address_pkey PRIMARY KEY (id)
);
CREATE INDEX idx_address_email ON address(email_address);