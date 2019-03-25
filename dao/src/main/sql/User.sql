CREATE TABLE IF NOT EXISTS T_USER (
  ID          BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  EMAIL       VARCHAR(30) NOT NULL,
  SURNAME     VARCHAR(20) NOT NULL,
  NAME        VARCHAR(20) NOT NULL,
  PASSWORD    VARCHAR(20) NOT NULL,
  ROLE_ID     BIGINT(10)  NOT NULL,
  DISCOUNT_ID BIGINT      NOT NULL,
  FOREIGN KEY (DISCOUNT_ID) REFERENCES T_DISCOUNT (ID),
  FOREIGN KEY (ROLE_ID) REFERENCES T_ROLE (ID)
);