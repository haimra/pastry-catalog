ALTER TABLE catalog ADD COLUMN sku VARCHAR(32);
UPDATE catalog SET sku = 'suk' || id;
ALTER TABLE catalog DROP CONSTRAINT catalog_pkey;
ALTER TABLE catalog DROP COLUMN id;
ALTER TABLE catalog ADD PRIMARY KEY (sku);