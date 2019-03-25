CREATE TABLE IF NOT EXISTS T_ORDER (
  ID       BIGINT   NOT NULL AUTO_INCREMENT PRIMARY KEY,
  USER_ID  BIGINT   NOT NULL,
  ITEM_ID  BIGINT   NOT NULL,
  CREATED  DATETIME NOT NULL,
  QUANTITY INTEGER(11),
  STATUS   VARCHAR(20),
  FOREIGN KEY (USER_ID) REFERENCES T_USER (ID),
  FOREIGN KEY (ITEM_ID) REFERENCES T_ITEM (ID)
);