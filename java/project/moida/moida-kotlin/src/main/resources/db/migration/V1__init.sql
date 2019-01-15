CREATE TABLE member
(
  id bigint not null,
  name VARCHAR(255) NOT NULL,
  fbLink VARCHAR(255) NOT NULL,
  blogLink VARCHAR(255)	NOT NULL,
  primary key (id)
);
CREATE TABLE feed
(
  id bigint not null,
  memberId BIGINT NOT NULL,
  title VARCHAR(255) NOT NULL,
  link VARCHAR(255)	NOT NULL,
  version INT,
  primary key (id)
);

CREATE TABLE feedHistory
(
  id bigint not null,
  createdDate date,
  contents VARCHAR(255)	NOT NULL,
  feedId BIGINT NOT NULL,
  primary key (id)
);

CREATE TABLE form
(
  id bigint not null,
  memberId BIGINT NOT NULL,
  createdDate date,
  updatedDate date,
  primary key (id)
);

CREATE TABLE item
(
  id varchar(8) not null,
  formId BIGINT NOT NULL,
  content VARCHAR(255)	NOT NULL,
  confirm BOOLEAN DEFAULT FALSE,
  primary key (id)
);