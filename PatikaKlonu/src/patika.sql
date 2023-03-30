-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1:3306
-- Üretim Zamanı: 30 Mar 2023, 07:20:54
-- Sunucu sürümü: 8.0.31
-- PHP Sürümü: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `patika`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `content`
--

DROP TABLE IF EXISTS `content`;
CREATE TABLE IF NOT EXISTS `content` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `info` varchar(255) NOT NULL,
  `link` varchar(255) NOT NULL,
  `course_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;

--
-- Tablo döküm verisi `content`
--

INSERT INTO `content` (`id`, `title`, `info`, `link`, `course_name`) VALUES
(1, 'Wrapper Sınıflar', 'Wrapper sınıflar; ilkel veri tiplerini (int,byte,sort vs.) nesne (object) olarak kullanmamızı sağlayan sınıflardır.', 'https://www.youtube.com/watch?v=5osg58spykg', 'Java 102'),
(9, 'String Veri Türü İşlemleri', 'JavaScript metinlerin tümü \"string\" veri tipi içinde tutulur ve \"string\" veri türü ile ilgili yapabileceğimiz birden fazla işlem vardır.', 'https://www.youtube.com/watch?v=mXbLdT-XfzU', 'Javascript'),
(10, 'WHERE ve Mantıksal Operatörler', 'WHERE anahtar kelimesi koşul ile birlikte çalışır.', 'https://www.youtube.com/watch?v=8dL7Ov0wg14', 'SQL 101');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `content_comments`
--

DROP TABLE IF EXISTS `content_comments`;
CREATE TABLE IF NOT EXISTS `content_comments` (
  `comment` varchar(255) NOT NULL,
  `rate` int NOT NULL,
  `content_id` int NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `course`
--

DROP TABLE IF EXISTS `course`;
CREATE TABLE IF NOT EXISTS `course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `patika_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `language` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb3;

--
-- Tablo döküm verisi `course`
--

INSERT INTO `course` (`id`, `user_id`, `patika_id`, `name`, `language`) VALUES
(1, 21, 1, 'Java 102', 'Java'),
(31, 21, 1, 'SQL 101', 'SQL'),
(30, 21, 2, 'Javascript', 'Javascript');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `patika`
--

DROP TABLE IF EXISTS `patika`;
CREATE TABLE IF NOT EXISTS `patika` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;

--
-- Tablo döküm verisi `patika`
--

INSERT INTO `patika` (`id`, `name`) VALUES
(1, 'Back-end Patikası'),
(2, 'Front-end Patikası');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `quiz`
--

DROP TABLE IF EXISTS `quiz`;
CREATE TABLE IF NOT EXISTS `quiz` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quiz_questions` varchar(255) NOT NULL,
  `content_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;

--
-- Tablo döküm verisi `quiz`
--

INSERT INTO `quiz` (`id`, `quiz_questions`, `content_id`) VALUES
(13, '1- Wrapper sınıflar hangi tür verileri kullanabilir?\r\n2-ArrayList\'lerde wrapper sınıfları kullanabilir miyiz?', 1),
(14, '1-SQL\'de eşit değil ifadesi hangi sembol ile gösterilir?\r\n2-BETWEEN ... AND operatörleri ne işe yarar?', 10),
(15, '1-String verileri değişkene atarken hangi sembol kullanılır?\r\n2-String veri tipinde bulunan bir ifadenin ne kadar uzun olduğunu bulmak için hangi özellik kullanılır? ', 9);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `student_course`
--

DROP TABLE IF EXISTS `student_course`;
CREATE TABLE IF NOT EXISTS `student_course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `course_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;

--
-- Tablo döküm verisi `student_course`
--

INSERT INTO `student_course` (`id`, `student_id`, `course_id`) VALUES
(13, 3, 1),
(14, 3, 31);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `uname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `type` enum('operator','educator','student') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb3;

--
-- Tablo döküm verisi `user`
--

INSERT INTO `user` (`id`, `name`, `uname`, `password`, `type`) VALUES
(3, 'ogrenci', 'ogrenci1', '1234', 'student'),
(20, 'admin', 'admin', '2112', 'operator'),
(21, 'egitmen', 'egitmen1', '4321', 'educator');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
