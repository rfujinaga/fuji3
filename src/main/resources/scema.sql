CREATE TABLE login_user (
  id INT AUTO_INCREMENT,
  user_name VARCHAR(20) NOT NULL,
  mailaddress VARCHAR(120) NOT NULL,
  password VARCHAR(120) NOT NULL,
  roles VARCHAR(20),
  delete_flg INT(1) NOT NULL DEFAULT 1 ,
  PRIMARY KEY (id),
  UNIQUE KEY (user_name)
  );