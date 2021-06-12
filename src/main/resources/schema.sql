CREATE TABLE IF NOT EXISTS catalog (
  id bigint not null,
  created_at timestamp not null,
  title varchar(50) not null,
  price numeric not null
);