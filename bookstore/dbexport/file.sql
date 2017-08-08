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


/* EXPORT 8 AUGUST 10:44*/

-- Dumping database structure for bookstore
DROP DATABASE IF EXISTS `bookstore`;
CREATE DATABASE IF NOT EXISTS `bookstore` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bookstore`;

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

-- Dumping data for table bookstore.order: ~0 rows (approximately)
DELETE FROM `order`;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;

-- Dumping data for table bookstore.order_item: ~0 rows (approximately)
DELETE FROM `order_item`;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;

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
