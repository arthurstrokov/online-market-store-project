CREATE TABLE IF NOT EXISTS T_FEEDBACK (
  ID      BIGINT   NOT NULL AUTO_INCREMENT PRIMARY KEY,
  USER_ID BIGINT   NOT NULL,
  MESSAGE LONGTEXT NOT NULL,
  FOREIGN KEY (USER_ID) REFERENCES T_USER (ID)
);