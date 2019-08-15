DROP TABLE IF EXISTS app_users;

CREATE TABLE app_users (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL
);

INSERT INTO app_users (first_name, last_name) VALUES
  ('Juancho', 'Voltio'),
  ('El Mister', 'Black'),
  ('Wuachy', 'Meléndez');