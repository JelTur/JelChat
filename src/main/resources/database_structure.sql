create table users(
    id int not null auto_increment,
    username varchar(50) unique not null,
    password varchar(50) not null,
    name varchar(50) not null,
    primary key(id)
);

CREATE TABLE chat (
  id int(11) NOT NULL,
  sender_user_id int(11) NOT NULL,
  receiver_user_id int(11) NOT NULL,
  message text COLLATE utf8_unicode_ci NOT NULL
);

ALTER TABLE chat
  ADD CONSTRAINT chat_ibfk_1 FOREIGN KEY (receiver_user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT chat_ibfk_2 FOREIGN KEY (sender_user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE;