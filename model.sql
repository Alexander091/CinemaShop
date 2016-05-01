CREATE TABLE  movies
(
  id BIGINT PRIMARY KEY NOT NULL,
  title VARCHAR(500),
  year_of_production INTEGER,
  duration INT,
  description VARCHAR(4000)
);
CREATE UNIQUE INDEX "movies _movie_id_uindex" ON  movies (id);

CREATE TABLE  halls
(
  id BIGINT PRIMARY KEY NOT NULL  ,
  name VARCHAR(100)
);
CREATE UNIQUE INDEX "Halls_hall_id_uindex" ON  Halls (id);

CREATE TABLE  showings
(
  id BIGINT PRIMARY KEY NOT NULL  ,
  hall_id BIGINT,
  movie_id BIGINT,
  start timestamp with time zone,
  CONSTRAINT "showings_HALLS_HALL_ID_fk" FOREIGN KEY (hall_id) REFERENCES HALLS (ID),
  CONSTRAINT "showings_movies _MOVIE_ID_fk" FOREIGN KEY (movie_id) REFERENCES movies (ID)
);
CREATE UNIQUE INDEX "showings_showing_id_uindex" ON  showings (id);

CREATE TABLE  HALL_ROWS
(
  ID BIGINT PRIMARY KEY NOT NULL  ,
  HALL_ID BIGINT,
  NAME VARCHAR(100),
  CONSTRAINT ROWS_HALLS_HALL_ID_FK FOREIGN KEY (HALL_ID) REFERENCES HALLS (ID)
);
CREATE UNIQUE INDEX "halls_hall_id_uindex" ON  HALL_ROWS(ID);

CREATE TABLE  seats
(
  id BIGINT PRIMARY KEY NOT NULL,
  hall_row_id BIGINT,
  name VARCHAR(100),
  CONSTRAINT seats_HALL_ROWS_HALL_ROW_ID_fk FOREIGN KEY (hall_row_id) REFERENCES HALL_ROWS (ID)
);
CREATE UNIQUE INDEX "seats_seat_id_uindex" ON  seats (id);

CREATE TABLE  tickets
(
  id BIGINT PRIMARY KEY NOT NULL  ,
  showing_id BIGINT,
  seat_id BIGINT NOT NULL,
  name VARCHAR(100),
  CONSTRAINT tickets_SHOWINGS_SHOWING_ID_fk FOREIGN KEY (showing_id) REFERENCES SHOWINGS (ID),
  CONSTRAINT tickets_SEATS_SEAT_ID_fk FOREIGN KEY (seat_id) REFERENCES SEATS (ID)
);
CREATE UNIQUE INDEX "tickets_ticket_id_uindex" ON  tickets (id);

ALTER TABLE  TICKETS ADD hall_row_id BIGINT;
ALTER TABLE  TICKETS
  ADD CONSTRAINT TICKETS_HALL_ROWS_HALL_ROW_ID_fk
FOREIGN KEY (hall_row_id) REFERENCES HALL_ROWS (ID);

CREATE TABLE  roles
(
  id BIGINT PRIMARY KEY NOT NULL  ,
  name VARCHAR(500) NOT NULL
);
CREATE UNIQUE INDEX "roles_role_id_uindex" ON  roles (id);

CREATE TABLE  users
(
  id BIGINT PRIMARY KEY NOT NULL  ,
  login VARCHAR(500) NOT NULL,
  password VARCHAR(4000) NOT NULL
);
CREATE UNIQUE INDEX "users_user_id_uindex" ON  users (id);

CREATE TABLE  users_roles
(
  user_id BIGINT,
  role_id BIGINT,
  CONSTRAINT user_roles_USERS_USER_ID_fk FOREIGN KEY (user_id) REFERENCES USERS (ID),
  CONSTRAINT user_roles_ROLES_ROLE_ID_fk FOREIGN KEY (role_id) REFERENCES ROLES (ID)
);
