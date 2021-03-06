
use javadb;
# DROP TABLE IF EXISTS User, userinfo, hciB, multimediaB, iotB, systemappB, aiB;

create table User (
id int(11) NOT NULL AUTO_INCREMENT,
name varchar(100) NOT NULL,
student_number int(20) NOT NULL,
password varchar(100) NOT NULL,
primary key(id) ) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

create table userinfo (
id int(11) NOT NULL AUTO_INCREMENT,
student_number int(20) NOT NULL,
lecture_num int(10) NOT NULL,
primary key(id) ) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

create table hciB(
lecture_num int(10) NOT NULL,
primary key(lecture_num) ) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

insert into hciB (lecture_num) value (1),(2),(3);

create table multimediaB(
lecture_num int(10) NOT NULL,
primary key(lecture_num) ) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

insert into multimediaB (lecture_num) value (16),(1),(17);

create table iotB(
lecture_num int(10) NOT NULL,
primary key(lecture_num) ) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

insert into iotB (lecture_num) value (25),(18),(26);

create table systemappB(
lecture_num int(10) NOT NULL,
primary key(lecture_num) ) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

insert into systemappB (lecture_num) value (37),(38),(39);

create table aiB(
lecture_num int(10) NOT NULL,
primary key(lecture_num) ) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

insert into aiB (lecture_num) value (48),(26),(40);

create table virtualrealityB(
lecture_num int(10) NOT NULL,
primary key(lecture_num) ) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

insert into virtualrealityB (lecture_num) value (13),(1),(2);

create table infoprotectB(
lecture_num int(10) NOT NULL,
primary key(lecture_num) ) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

insert into infoprotectB (lecture_num) value (58),(59),(25);

create table datascienceB(
lecture_num int(10) NOT NULL,
primary key(lecture_num) ) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

insert into datascienceB (lecture_num) value (40),(26),(50);

create table sweduB(
lecture_num int(10) NOT NULL,
primary key(lecture_num) ) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

insert into sweduB (lecture_num) value (76),(3),(42);

create table hciS(
lecture_num int(10) NOT NULL,
primary key(lecture_num) ) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

insert into hciS (lecture_num) value (4),(5),(6),(7),(8),(9),(10),(11),(12),(13),(14),(15);

create table multimediaS(
lecture_num int(10) NOT NULL,
primary key(lecture_num) ) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

insert into multimediaS (lecture_num) value (18),(19),(20),(21),(22),(4),(23),(24),(12),(13);

create table iotS(
lecture_num int(10) NOT NULL,
primary key(lecture_num) ) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

insert into iotS (lecture_num) value (27),(19),(28),(29),(30),(31),(32),(33),(34),(35),(36);

create table systemappS(
lecture_num int(10) NOT NULL,
primary key(lecture_num) ) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

insert into systemappS (lecture_num) value (40),(41),(42),(43),(36),(44),(45),(28),(46),(13),(47);

create table aiS(
lecture_num int(10) NOT NULL,
primary key(lecture_num) ) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

insert into aiS (lecture_num) value (4),(21),(42),(43),(36),(44),(45),(28),(46),(13),(47);

create table virtualrealityS(
lecture_num int(10) NOT NULL,
primary key(lecture_num) ) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

insert into virtualrealityS (lecture_num) value (53),(12),(16),(54),(55),(56),(57),(5),(4),(13),(15);

create table infoprotectS(
lecture_num int(10) NOT NULL,
primary key(lecture_num) ) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

insert into infoprotectS (lecture_num) value (60),(61),(62),(63),(64),(65),(30),(67),(68),(69),(66);

create table datascienceS(
lecture_num int(10) NOT NULL,
primary key(lecture_num) ) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

insert into datascienceS (lecture_num) value (51),(70),(48),(71),(72),(73),(74),(75),(2),(17);

create table sweduS(
lecture_num int(10) NOT NULL,
primary key(lecture_num) ) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

insert into sweduS (lecture_num) value (77),(8),(24),(78),(79),(80),(81),(40),(82),(47),(83);