-- question table creation
create table Question (
	id int not null auto_increment,
    question varchar(255) not null,
    red varchar(255) not null,
    blue varchar(255) not null,
    category varchar(255),
    primary key (id)
);

-- add isnsfw column
alter table Question
add isnsfw varchar(1) not null
default 'n';

-- drop question table
DROP TABLE Question;



----------------- DB V 2 ----------------
create table Question (
	id int not null auto_increment,
    question varchar(500) not null,
    oneoption varchar(500) not null,
    twooption varchar(500) not null,
    oneimage varchar(500) default 'void',
    twoimage varchar(500)  default 'void',
    category varchar(500) default 'void',
    isnsfw varchar(1) default 'n',
    primary key (id)
);
