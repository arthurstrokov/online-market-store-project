CREATE TABLE IF NOT EXISTS T_ITEM_DISCOUNT (
  ITEM_ID     BIGINT NOT NULL,
  DISCOUNT_ID BIGINT NOT NULL,
  PRIMARY KEY (ITEM_ID, DISCOUNT_ID),
  FOREIGN KEY (ITEM_ID) REFERENCES T_ITEM (ID),
  FOREIGN KEY (DISCOUNT_ID) REFERENCES T_DISCOUNT (ID)
);