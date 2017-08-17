-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.1.21-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for bookstore
DROP DATABASE IF EXISTS `bookstore`;
CREATE DATABASE IF NOT EXISTS `bookstore` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bookstore`;

-- Dumping structure for table bookstore.authors
DROP TABLE IF EXISTS `authors`;
CREATE TABLE IF NOT EXISTS `authors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `age` varchar(50) DEFAULT NULL,
  `nationality` varchar(50) DEFAULT NULL,
  `description` longtext,
  `CNP` bigint(13) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore.authors: ~7 rows (approximately)
DELETE FROM `authors`;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` (`id`, `name`, `age`, `nationality`, `description`, `CNP`) VALUES
	(1, 'Bruce Eckel', '60', 'United States of America', 'Bruce Eckel (born July 8, 1957) is a computer programng.', 1234567891234),
	(32, NULL, '0', 'jkl', NULL, 1234567899876),
	(33, 'nume', '23', 'nationalitate', NULL, 12345675427896),
	(34, 'num', '23', 'nat', 'desc', 1231231231231),
	(35, 'nume', '45', 'nat', 'descr', 19426235682356),
	(36, 'nume', '45', 'nat', 'descr', 0),
	(37, 'Y. Daniel Liang', '40', 'Shanghai', 'Y. DANIEL LIANG holds B.S. and M.S. degrees in computer science from Fudan University in Shanghai and a Ph.D. degree in computer science from the University of Oklahoma. He has published four books, as well as numerous papers in international journals. He has taught Java courses internationally, as well as consulted in the areas of algorithm design, client/server computing, and database management. Dr. Liang is currently an associate professor in the Department of Computer Science at Purdue University at Fort Wayne, where he twice received the Excellence in Research Award from the School of Engineering, Technology, and Computer Science. ', 1234500003456);
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;

-- Dumping structure for table bookstore.books
DROP TABLE IF EXISTS `books`;
CREATE TABLE IF NOT EXISTS `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `author_id` int(11) NOT NULL,
  `isbn` bigint(20) NOT NULL,
  `price` double NOT NULL,
  `description` longtext NOT NULL,
  `image` blob NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `isbn` (`isbn`),
  KEY `FK_books_authors` (`author_id`),
  CONSTRAINT `FK_books_authors` FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore.books: ~9 rows (approximately)
DELETE FROM `books`;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` (`id`, `name`, `author_id`, `isbn`, `price`, `description`, `image`) VALUES
	(1, 'Thinking in JAVA', 1, 97890, 30, 'Thinking in Java  is a book about the Java programming language, written by Bruce Eckel and first published in 1998', _binary 0x433A5C55736572735C6D6164616C696E612E6C7563615C437261696F76612D496E7465726E736869702D323031375C626F6F6B73746F72655C7765625C696D616765735C7468696E6B696E67696E6A6176612E6A7067),
	(2, 'Thinking in C++', 1, 139177094, 12, 'In the first edition of Thinking in C++, Bruce Eckel synthesized years of C++ teaching and programming experience into a beautifully structured course in making the most of the language. It became an instant classic, winning the 1995 Software Development Jolt Cola Award for best book of the year. Now, Eckel has thoroughly rewritten Thinking in C++ to reflect the final ANSI/ISO C++ standard. Every page has been revisited and rethought, with many new examples and exercises -- all designed to help you understand C++ "down to the bare metal," so you can solve virtually any problem.', _binary 0x433A5C55736572735C6D6164616C696E612E6C7563615C437261696F76612D496E7465726E736869702D323031375C626F6F6B73746F72655C7765625C696D616765735C7468696E6B696E67696E632B2B2E6A7067),
	(21, 'BK', 32, 0, 0, 'fkbnfjkldbn', _binary 0x433A5C55736572735C6D6164616C696E612E6C7563615C437261696F76612D496E7465726E736869702D323031375C626F6F6B73746F72655C7765625C696D616765735C696E6465782E6A7067),
	(22, 'carteee', 33, 131256, 34, 'sdjghajg', _binary 0x433A5C55736572735C6D6164616C696E612E6C7563615C437261696F76612D496E7465726E736869702D323031375C626F6F6B73746F72655C7765625C696D616765735C696E6465782E6A7067),
	(23, 'carte2', 34, 123124, 45, 'dfsjgh', _binary 0x433A5C55736572735C6D6164616C696E612E6C7563615C437261696F76612D496E7465726E736869702D323031375C626F6F6B73746F72655C7765625C696D616765735C696E6465782E6A7067),
	(24, 'nume', 35, 12647, 37, 'hafahj', _binary 0x433A5C55736572735C6D6164616C696E612E6C7563615C437261696F76612D496E7465726E736869702D323031375C626F6F6B73746F72655C7765625C696D616765735C696D616765732E6A7067),
	(30, 'Computer Graphics Using Java 2D and 3D', 37, 97813351, 174, 'This Java handbook makes a practical tutorial on Java 2D and Java 3D for computer professionals. It contains in-depth coverage of basic computer graphics concepts and techniques, and introduces advanced graphic features to an audience mostly trained in the Java language. KEY TOPICS: Chapter topics include mathematical background for computer graphics, .geometric transformation, views, lighting and texturing, behavior and interaction, and animation.', _binary 0x433A5C55736572735C6D6164616C696E612E6C7563615C437261696F76612D496E7465726E736869702D323031375C626F6F6B73746F72655C7765625C696D616765735C67726170686963732E6A7067),
	(32, 'Revel for Liang Java', 37, 978034167008, 86.67, 'REVELâ?¢ for Liang Java delivers a digital immersive learning experience that seamlessly integrates author content and pedagogy with dynamic and interactive coding activities and assignable/gradable homework. Animated code listings, LiveExamples, and videos bring course concepts to life for your students. ', _binary 0x433A5C55736572735C6D6164616C696E612E6C7563615C437261696F76612D496E7465726E736869702D323031375C626F6F6B73746F72655C7765625C696D616765735C726576656C2E6A7067),
	(33, 'cqrte', 37, 12345, 30, 'nuuu', _binary 0x433A5C55736572735C6D6164616C696E612E6C7563615C437261696F76612D496E7465726E736869702D323031375C626F6F6B73746F72655C7765625C696D616765735C726576656C2E6A7067);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;

-- Dumping structure for table bookstore.order
DROP TABLE IF EXISTS `order`;
CREATE TABLE IF NOT EXISTS `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `total_price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore.order: ~14 rows (approximately)
DELETE FROM `order`;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` (`id`, `total_price`) VALUES
	(1, 35),
	(2, 30),
	(3, 30),
	(4, 30),
	(5, 90),
	(6, 37),
	(7, 45),
	(8, 67),
	(9, 45),
	(10, 79),
	(11, 30),
	(12, 30),
	(13, 30),
	(14, 390);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;

-- Dumping structure for table bookstore.order_item
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE IF NOT EXISTS `order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_order_item_order` (`order_id`),
  KEY `FK_order_item_books` (`book_id`),
  CONSTRAINT `FK_order_item_books` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  CONSTRAINT `FK_order_item_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore.order_item: ~14 rows (approximately)
DELETE FROM `order_item`;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` (`id`, `order_id`, `book_id`, `price`) VALUES
	(5, 2, 1, 30),
	(6, 5, 1, 30),
	(7, 5, 2, 60),
	(8, 6, 24, 37),
	(9, 7, 23, 45),
	(10, 8, 1, 30),
	(11, 8, 24, 37),
	(12, 7, 23, 45),
	(13, 10, 23, 45),
	(14, 10, 22, 34),
	(15, 2, 1, 30),
	(16, 2, 1, 30),
	(17, 2, 1, 30),
	(18, 14, 1, 30);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;

-- Dumping structure for table bookstore.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `real_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `FK_users_user_type` (`type`),
  CONSTRAINT `FK_users_user_type` FOREIGN KEY (`type`) REFERENCES `user_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore.users: ~12 rows (approximately)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `username`, `password`, `real_name`, `email`, `type`) VALUES
	(1, 'demo', 'demo', 'demodemo', 'demo', 2),
	(3, 'a', 'a', 'a', 'a', 2),
	(4, 'q', 'q', 'q', 'q', 2),
	(5, 'w', 'w', 'w', 'w', 1),
	(12, 'u', 'u', 'u', 'u', 1),
	(13, 'i', 'i', 'i', 'i', 2),
	(14, 'p', 'p', 'p', 'p', 2),
	(16, 'b', 'a', 'm', 'o', 2),
	(24, 'admin', 'admin', 'Madalina Luca', 'madalina.luca12@yahoo.com', 1),
	(25, 'user', 'user', 'Maria Popa', 'maria.popa@yahoo.com', 2),
	(27, 'user2', 'user2', 'User2', 'user@yahoo.com', 2),
	(28, 'admin1', 'admin', 'real', 'email', 2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Dumping structure for table bookstore.user_type
DROP TABLE IF EXISTS `user_type`;
CREATE TABLE IF NOT EXISTS `user_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore.user_type: ~2 rows (approximately)
DELETE FROM `user_type`;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
INSERT INTO `user_type` (`id`, `name`) VALUES
	(1, 'admin'),
	(2, 'user');
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
