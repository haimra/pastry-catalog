create TABLE IF NOT EXISTS account(
  email_address VARCHAR(32) NOT NULL,
  password VARCHAR(32) NOT NULL,
  CONSTRAINT account_pkey PRIMARY KEY (email_address)
);
create TABLE IF NOT EXISTS address(
  id bigint GENERATED ALWAYS AS IDENTITY,
  address_line1 VARCHAR(256) NOT NULL,
  address_line2 VARCHAR(256),
  postcode VARCHAR(256) NOT NULL,
  CONSTRAINT address_pkey PRIMARY KEY (id)
);