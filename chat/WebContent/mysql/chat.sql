CREATE DATABASE db_chat; #创建 db_chat 数据库

use db_chat; #进入 db_chat 数据库

CREATE TABLE `tb_user` (
 
			 `username` varchar(10) NOT NULL,
			 `password` varchar(10) NOT NULL,
  
			 `petName` varchar(10) DEFAULT NULL,
 
			 `sex` tinyint(1) unsigned zerofill DEFAULT NULL,
 
			 `profile` varchar(50) DEFAULT NULL,
 
			 `Email` varchar(30) DEFAULT NULL,
 
			 `power` int(11) unsigned zerofill DEFAULT NULL,
 
			 PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_message` (
  
			 `id` int(11) NOT NULL AUTO_INCREMENT,
 
			 `username` varchar(10) NOT NULL,
 
			 `DATE` datetime NOT NULL,
 
			 `content` tinytext NOT NULL,
 
			 PRIMARY KEY (`id`),
 
			 KEY `username` (`username`),
 
			 CONSTRAINT `tb_message_ibfk_1` FOREIGN KEY (`username`) REFERENCES `tb_user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8