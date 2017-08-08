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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore.authors: ~6 rows (approximately)
DELETE FROM `authors`;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` (`id`, `name`, `age`, `nationality`, `description`, `CNP`) VALUES
	(1, 'Bruce Eckel', '60', 'United States of America', 'Bruce Eckel (born July 8, 1957) is a computer programng.', 1234567891234),
	(32, NULL, '0', 'jkl', NULL, 1234567899876),
	(33, 'nume', '23', 'nationalitate', NULL, 12345675427896),
	(34, 'num', '23', 'nat', 'desc', 1231231231231),
	(35, 'nume', '45', 'nat', 'descr', 19426235682356),
	(36, 'nume', '45', 'nat', 'descr', 0);
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;

-- Dumping structure for table bookstore.books
DROP TABLE IF EXISTS `books`;
CREATE TABLE IF NOT EXISTS `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `author_id` int(11) NOT NULL,
  `isbn` int(30) NOT NULL,
  `price` double NOT NULL,
  `description` longtext NOT NULL,
  `image` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `isbn` (`isbn`),
  KEY `FK_books_authors` (`author_id`),
  CONSTRAINT `FK_books_authors` FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore.books: ~6 rows (approximately)
DELETE FROM `books`;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` (`id`, `name`, `author_id`, `isbn`, `price`, `description`, `image`) VALUES
	(1, 'Thinking in JAVA', 1, 97890, 30, 'Thinking in Java  is a book about the Java programming language, written by Bruce Eckel and first published in 1998', ''),
	(2, 'Thinking in C++', 1, 139177094, 12, 'In the first edition of Thinking in C++, Bruce Eckel synthesized years of C++ teaching and programming experience into a beautifully structured course in making the most of the language. It became an instant classic, winning the 1995 Software Development Jolt Cola Award for best book of the year. Now, Eckel has thoroughly rewritten Thinking in C++ to reflect the final ANSI/ISO C++ standard. Every page has been revisited and rethought, with many new examples and exercises -- all designed to help you understand C++ "down to the bare metal," so you can solve virtually any problem.', ''),
	(21, 'BK', 32, 0, 0, 'fkbnfjkldbn', ''),
	(22, 'carteee', 33, 131256, 34, 'sdjghajg', ''),
	(23, 'carte2', 34, 123124, 45, 'dfsjgh', ''),
	(24, 'nume', 35, 12647, 37, 'hafahj', '../images/images.jpg');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;

-- Dumping structure for table bookstore.order
DROP TABLE IF EXISTS `order`;
CREATE TABLE IF NOT EXISTS `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `total_price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore.order: ~0 rows (approximately)
DELETE FROM `order`;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore.order_item: ~0 rows (approximately)
DELETE FROM `order_item`;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore.users: ~10 rows (approximately)
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
	(25, 'user', 'user', 'Maria Popa', 'maria.popa@yahoo.com', 2);
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
