/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  vitor
 * Created: 13/01/2016
 */
create table ACCOUNT (ACCOUNT_ID bigint not null, AGENCY varchar(255), BANK varchar(255), NUMBER varchar(255), primary key (ACCOUNT_ID));

create table CUSTOMER (CUSTOMER_ID bigint not null, CELLPHONE varchar(255), DOCUMENT varchar(255) not null, LAST_NAME varchar(255) not null, NAME varchar(255) not null, PHONE varchar(255), ACCOUNT_ID bigint, primary key (CUSTOMER_ID));

create table PAYMENT_REGISTER (PAYMENT_REGISTER_ID bigint not null, PAYMENT_DATE date not null, PAYMENT_STATUS varchar(255) not null, PAYMENT_VALUE decimal(19,2) not null, PROPERTY_CUSTOMER_ID bigint not null, primary key (PAYMENT_REGISTER_ID));

create table PERMISSION (PERMISSION_ID bigint not null, PERMISSION_NAME varchar(255), TO_VIEW_ID varchar(255), primary key (PERMISSION_ID));

create table PROFILE (PROFILE_ID bigint not null, NAME varchar(255) not null, primary key (PROFILE_ID));

create table PROFILE_PERMISSION (PERMISSION_ID bigint not null, PROFILE_ID bigint not null);

create table PROPERTY (PROPERTY_ID bigint not null, ADDRESS varchar(255) not null, AREA integer not null, BATHROOM integer, BEDROOM integer, COMPLEMENT varchar(255), DINNER_ROOM integer, INTERNAL_OBS varchar(255), KITCHEN integer, LAUNDRY integer, NEIGHBORHOOD varchar(255) not null, NUMBER varchar(255) not null, OBS varchar(255), PARKING_SPACES integer, ROOM integer, STATUS varchar(255) not null, SUITE integer, PROPERTY_TYPE varchar(255) not null, PROPERTY_VALUE decimal(19,2) not null, PROPRIETOR_ID bigint not null, primary key (PROPERTY_ID));

create table PROPERTY_CUSTOMER (PROPERTY_CUSTOMER_ID bigint not null, CONTRACT_PERIOD integer, DEPOSIT_VALUE decimal(19,2) not null, PAYMENT_DAY integer not null, STARTED_DATE date not null, CUSTOMER_ID bigint, PROPERTY_ID bigint, primary key (PROPERTY_CUSTOMER_ID));

create table PROPRIETOR (PROPRIETOR_ID bigint not null, CELLPHONE varchar(255), DOCUMENT varchar(255) not null, NAME varchar(255) not null, PHONE varchar(255), ACCOUNT_ID bigint, primary key (PROPRIETOR_ID));

create table USERS (USER_ID bigint not null, EMAIL varchar(255) not null, LOGIN varchar(255) not null, PASSWORD varchar(255) not null, ACTIVE varchar(255), PROFILE_ID bigint, primary key (USER_ID));

alter table CUSTOMER add constraint FK_j8v3r41eql2il40jp38v3pxc4 foreign key (ACCOUNT_ID) references ACCOUNT (ACCOUNT_ID);

alter table PAYMENT_REGISTER add constraint FK_5gw5w8c6vl1osw4u06l2uvt2s foreign key (PROPERTY_CUSTOMER_ID) references PROPERTY_CUSTOMER (PROPERTY_CUSTOMER_ID);

alter table PROFILE_PERMISSION add constraint FK_h8iks5h0f9xtcv7hi62mdfgps foreign key (PROFILE_ID) references PERMISSION (PERMISSION_ID);

alter table PROFILE_PERMISSION add constraint FK_dbin3joywc9o4y0t0c9y9rs33 foreign key (PERMISSION_ID) references PROFILE (PROFILE_ID);

alter table PROPERTY add constraint FK_1ahi9jfocmalylsgnvyd88ccg foreign key (PROPRIETOR_ID) references PROPRIETOR (PROPRIETOR_ID);

alter table PROPERTY_CUSTOMER add constraint FK_sy2v05yxqnb68ugs7yjhfi3h7 foreign key (CUSTOMER_ID) references CUSTOMER (CUSTOMER_ID);

alter table PROPERTY_CUSTOMER add constraint FK_h8kuylr0hdcixkg1ux8qnlmfb foreign key (PROPERTY_ID) references PROPERTY (PROPERTY_ID);

alter table PROPRIETOR add constraint FK_j6j8iijegg69qfn1dkp8j30gl foreign key (ACCOUNT_ID) references ACCOUNT (ACCOUNT_ID);

alter table USERS add constraint FK_6nf4ydnudmruh952nsmy1jjsb foreign key (PROFILE_ID) references PROFILE (PROFILE_ID);

create table hibernate_sequence ( next_val bigint );

insert into hibernate_sequence values ( 1 );

