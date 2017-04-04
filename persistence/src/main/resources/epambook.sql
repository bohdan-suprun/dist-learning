DROP TABLE IF EXISTS `User`;
CREATE TABLE `EVENTTABLE` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255),
  `username` varchar(255),
  `password` varchar(255),
  `dateOfBirth` date,
  `friend` varchar(255),
  `eventType` varchar(255),
  `noteText` varchar(255),
  `noteAuthor` varchar(255),
  `version` bigint(20),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8;