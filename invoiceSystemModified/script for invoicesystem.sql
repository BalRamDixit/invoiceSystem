show databases;
drop database invoice;
create database invoice;
use invoice;
create table vendor
(
	uuid varchar(32),
	contactperson varchar(50),
	company varchar(100),
	contactno varchar(10),
	email varchar(256),
	address varchar(200),
	city varchar(50),
	url varchar(255),
	status varchar(1) default 'a'
);
alter table vendor 
add constraint vendorpk 
primary key(uuid);
create index vendorindex 
on vendor(contactperson);

create table client
(
	uuid varchar(32),
	contactperson varchar(50),
	company varchar(100),
	contactno varchar(10),
	email varchar(256),
	address varchar(200),
	city varchar(50),
	url varchar(255),
	status varchar(1) default 'a'
);
alter table client 
add constraint clientpk 
primary key(uuid);
create index clientindex 
on client(contactperson);

create table purchaseorder
(
	uuid varchar(32),
	vendorid varchar(32),
	clientid varchar(32),
	createdate date,
	unit int,
	rate int,
	duration int,
	startdate date,
	enddate date,
	particular varchar(1000),
	paymentterms varchar(1000),
	purchaseorderno varchar(32),
	purchasestatus varchar(2),
	paymentstatus varchar(2),
	stextra char(1) COMMENT '1-yes;0-no',
	status varchar(1) default 'a',
	currency varchar(10)
	
);
alter table purchaseorder 
add constraint purchaseorderpk 
primary key(uuid);
alter table purchaseorder 
add  constraint purchaseorderfk  
foreign key(vendorid) 
references vendor(uuid);
alter table purchaseorder 
add  constraint purchaseorderfk1  
foreign key(clientid) 
references client(uuid);
create index purchasedorerindex 
on purchaseorder(vendorid,clientid);

create table invoice
(
	uuid varchar(32),
    invoiceno varchar(10),
	purchaseorderno varchar(32),
	invoicedate date,
	total int
);
alter table invoice 
add constraint invoicepk 
primary key(uuid);
alter table invoice 
add  constraint invoicefk  
foreign key(purchaseorderno) 
references purchaseorder(uuid);

create table Expenses
(
	uuid varchar(32),
	expensetype varchar(2)  COMMENT '1-accommodation 2-travel 3-mailing',
	invoiceno varchar(32),
	total int,
	expensedate date,
	detail varchar(500)
);
alter table expenses 
add constraint expensespk 
primary key(uuid);
alter table expenses 
add  constraint expensesfk  
foreign key(invoiceno) 
references invoice(uuid);
