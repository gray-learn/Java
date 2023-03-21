drop table Staff;
create table Staff (  
  id char(9) not null,
  lastName varchar(15),
  firstName varchar(15),
  mi char(1),
  address varchar(20),
  city varchar(20),
  state char(2),
  telephone char(10),
  email varchar(40),
  primary key (id)
);

INSERT INTO Staff (id, lastName, firstName, mi, address, city, state, telephone, email) VALUES  ('000000001', 'Smith', 'John', 'D', '123 Main St', 'Anytown', 'CA', '1234567890', 'johnsmith@email.com');
INSERT INTO Staff (id, lastName, firstName, mi, address, city, state, telephone, email) VALUES ('000000002', 'Johnson', 'Mary', 'E', '456 First Ave', 'Otherville', 'NY', '5551234567', 'maryjohnson@email.com');
INSERT INTO Staff (id, lastName, firstName, mi, address, city, state, telephone, email) VALUES ('000000003', 'Davis', 'Mark', 'P', '789 Second St', 'Smalltown', 'TX', '1235557890', 'markdavis@email.com');
INSERT INTO Staff (id, lastName, firstName, mi, address, city, state, telephone, email) VALUES ('000000004', 'Garcia', 'Linda', 'R', '234 Third Rd', 'Bigcity', 'IL', '7775551234', 'lindagarcia@email.com');
INSERT INTO Staff (id, lastName, firstName, mi, address, city, state, telephone, email) VALUES ('000000005', 'Williams', 'Tom', 'J', '567 Fourth Blvd', 'Metropolis', 'CA', '8881234567', 'tomwilliams@email.com');
INSERT INTO Staff (id, lastName, firstName, mi, address, city, state, telephone, email) VALUES ('000000006', 'Rodriguez', 'Maria', 'A', '890 Fifth Ave', 'Village', 'FL', '5557891234', 'mariarodriguez@email.com');
INSERT INTO Staff (id, lastName, firstName, mi, address, city, state, telephone, email) VALUES ('000000007', 'Smith', 'Karen', 'S', '123 Sixth St', 'Anytown', 'CA', '1234567890', 'karensmith@email.com');
INSERT INTO Staff (id, lastName, firstName, mi, address, city, state, telephone, email) VALUES ('000000008', 'Brown', 'James', 'W', '456 Seventh Ave', 'Otherville', 'NY', '5551234567', 'jamesbrown@email.com');
INSERT INTO Staff (id, lastName, firstName, mi, address, city, state, telephone, email) VALUES ('000000009', 'Gonzalez', 'Jose', 'L', '789 Eighth Rd', 'Smalltown', 'TX', '1235557890', 'josegonzalez@email.com');
INSERT INTO Staff (id, lastName, firstName, mi, address, city, state, telephone, email) VALUES ('000000010', 'Miller', 'Emily', 'K', '234 Ninth Blvd', 'Bigcity', 'IL', '7775551234', 'emilymiller@email.com');

select * from Staff order by ID