-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1:3306
-- Üretim Zamanı: 19 Nis 2023, 11:38:10
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
-- Veritabanı: `turizm-acente-sistemi`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `hotels`
--

DROP TABLE IF EXISTS `hotels`;
CREATE TABLE IF NOT EXISTS `hotels` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `city` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `mail` varchar(255) NOT NULL,
  `phone` varchar(14) NOT NULL,
  `star` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `feature` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `lodging_types` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;

--
-- Tablo döküm verisi `hotels`
--

INSERT INTO `hotels` (`id`, `name`, `city`, `mail`, `phone`, `star`, `feature`, `lodging_types`, `address`) VALUES
(1, '7 Mirrors Hotel', 'Antalya', 'info@sevenmirrorshotel.com', '901233759', '5', 'Ücretsiz Otopark, Ücretsiz WiFi, Yüzme Havuzu, SPA, 7/24 Oda Servisi, Fitness Center', 'Ultra Herşey Dahil, Herşey Dahil, Tam Pansiyon', 'Gençlik, Fevzi Çakmak Cd. No. 242, 07100 Halilpaşa'),
(2, 'Hotel Melanie', 'İzmir', 'info@melaniehotels.com', '902487594', '4', 'Ücretsiz Otopark, Ücretsiz WiFi, Yüzme Havuzu', 'Herşey Dahil, Oda Kahvaltı, Tam Pansiyon, Yarım Pansiyon', 'Sığacık, 1280. Sk. 32 C, Seferihisar'),
(3, 'Pearl Lodge', 'Muğla', 'info@pearllodge.com', '902278391', '6', 'Ücretsiz Otopark, Ücretsiz WiFi, Yüzme Havuzu\r\nFitness Center, Hotel Concierge, SPA, 7/24 Oda Servisi', 'Ultra Herşey Dahil, Herşey Dahil, Oda Kahvaltı, Tam Pansiyon', 'Merkez Mahallesi, Soğuksu Cd. No:98, Datça'),
(4, 'The Jasmine Hotel', 'Ankara', 'info@jasminehotel.com', '00903124590321', '3', 'Ücretsiz Otopark, Yüzme Havuzu', 'Oda Kahvaltı, Tam Pansiyon', 'Çevre Evran, Latife Sk. no.54, Sincan'),
(6, 'Blaise Hotel', 'Hatay', 'info@blaisehotel.com', '00903267812094', '4', 'Ücretsiz Otopark, Ücretsiz WiFi', 'Yarım Pansiyon, Oda Kahvaltı', 'Başaktepe, Sıra Sk. No:245, Arsuz'),
(5, 'Apricot Inn', 'İstanbul', 'info@apricot-inn.com', '00902128790032', '3', 'Ücretsiz WiFi, Yüzme Havuzu', 'Oda Kahvaltı, Tam Pansiyon, Yarım Pansiyon', 'Esentepe, Şahin Cd. No 543, Şişli'),
(12, 'Hotel Marriott', 'İstanbul', 'info@hotel-marriott.com', '00902124536670', '4', 'Herşey Dahil, Oda Kahvaltı, Tam Pansiyon, Yarım Pansiyon', 'Herşey Dahil, Oda Kahvaltı, Tam Pansiyon, Yarım Pansiyon', 'Merkez, Çiçek Cd. No:442, 34381 Şişli');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `res_list`
--

DROP TABLE IF EXISTS `res_list`;
CREATE TABLE IF NOT EXISTS `res_list` (
  `id` int NOT NULL AUTO_INCREMENT,
  `hotel_id` int NOT NULL,
  `room_id` int NOT NULL,
  `check_in` date NOT NULL,
  `check_out` date NOT NULL,
  `guest_name` varchar(255) NOT NULL,
  `guest_identity` varchar(255) NOT NULL,
  `res_note` varchar(255) NOT NULL,
  `contact_name` varchar(255) NOT NULL,
  `contact_phone` varchar(255) NOT NULL,
  `contact_mail` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `room`
--

DROP TABLE IF EXISTS `room`;
CREATE TABLE IF NOT EXISTS `room` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` enum('Single','Double','Suit') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `bed_number` int NOT NULL,
  `stock` int NOT NULL,
  `tv` tinyint(1) NOT NULL,
  `mini_bar` tinyint(1) NOT NULL,
  `game_console` tinyint(1) NOT NULL,
  `hotel_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;

--
-- Tablo döküm verisi `room`
--

INSERT INTO `room` (`id`, `type`, `bed_number`, `stock`, `tv`, `mini_bar`, `game_console`, `hotel_id`) VALUES
(1, 'Suit', 2, 30, 1, 1, 1, 1),
(2, 'Double', 2, 50, 1, 1, 2, 2),
(3, 'Single', 2, 20, 1, 2, 2, 2),
(4, 'Double', 1, 30, 1, 1, 1, 1),
(5, 'Suit', 1, 40, 1, 1, 1, 3),
(6, 'Double', 2, 90, 1, 1, 1, 3),
(7, 'Single', 2, 50, 2, 1, 2, 4),
(8, 'Single', 2, 10, 1, 2, 1, 4),
(9, 'Single', 2, 69, 1, 2, 2, 5),
(10, 'Double', 1, 45, 1, 1, 2, 5),
(11, 'Double', 2, 10, 2, 2, 1, 6),
(12, 'Single', 1, 40, 1, 2, 2, 6),
(18, 'Single', 2, 9, 0, 0, 0, 12),
(19, 'Double', 1, 50, 1, 1, 1, 12);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `room_price`
--

DROP TABLE IF EXISTS `room_price`;
CREATE TABLE IF NOT EXISTS `room_price` (
  `id` int NOT NULL AUTO_INCREMENT,
  `lodging_type` varchar(255) NOT NULL,
  `hotel_id` int NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb3;

--
-- Tablo döküm verisi `room_price`
--

INSERT INTO `room_price` (`id`, `lodging_type`, `hotel_id`, `price`) VALUES
(1, 'Ultra Her Şey Dahil', 1, 7000),
(2, 'Her Şey Dahil', 1, 5000),
(3, 'Oda Kahvaltı', 1, 0),
(4, 'Tam Pansiyon', 1, 2000),
(5, 'Yarım Pansiyon', 1, 0),
(6, 'Sadece Yatak', 1, 0),
(7, 'Alkol Hariç Full Credit', 1, 0),
(8, 'Ultra Her Şey Dahil', 3, 5000),
(9, 'Her Şey Dahil', 3, 3000),
(10, 'Oda Kahvaltı', 3, 2000),
(11, 'Tam Pansiyon', 3, 500),
(12, 'Yarım Pansiyon', 3, 0),
(13, 'Sadece Yatak', 3, 0),
(14, 'Alkol Hariç Full Credit', 3, 0),
(15, 'Ultra Her Şey Dahil', 2, 0),
(16, 'Her Şey Dahil', 2, 4000),
(17, 'Oda Kahvaltı', 2, 2000),
(18, 'Tam Pansiyon', 2, 1700),
(19, 'Yarım Pansiyon', 2, 1000),
(20, 'Sadece Yatak', 2, 0),
(21, 'Alkol Hariç Full Credit', 2, 0),
(22, 'Ultra Her Şey Dahil', 4, 0),
(23, 'Her Şey Dahil', 4, 0),
(24, 'Oda Kahvaltı', 4, 1000),
(25, 'Tam Pansiyon', 4, 800),
(26, 'Yarım Pansiyon', 4, 0),
(27, 'Sadece Yatak', 4, 0),
(28, 'Alkol Hariç Full Credit', 4, 0),
(70, 'Alkol Hariç Full Credit', 12, 0),
(69, 'Sadece Yatak', 12, 0),
(68, 'Yarım Pansiyon', 12, 300),
(67, 'Tam Pansiyon', 12, 600),
(66, 'Oda Kahvaltı', 12, 1000),
(65, 'Her Şey Dahil', 12, 2000),
(64, 'Ultra Her Şey Dahil', 12, 0),
(43, 'Ultra Her Şey Dahil', 6, 0),
(44, 'Her Şey Dahil', 6, 0),
(45, 'Oda Kahvaltı', 6, 900),
(46, 'Tam Pansiyon', 6, 0),
(47, 'Yarım Pansiyon', 6, 700),
(48, 'Sadece Yatak', 6, 0),
(49, 'Alkol Hariç Full Credit', 6, 0),
(57, 'Ultra Her Şey Dahil', 5, 0),
(58, 'Her Şey Dahil', 5, 0),
(59, 'Oda Kahvaltı', 5, 900),
(60, 'Tam Pansiyon', 5, 700),
(61, 'Yarım Pansiyon', 5, 300),
(62, 'Sadece Yatak', 5, 0),
(63, 'Alkol Hariç Full Credit', 5, 0);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

--
-- Tablo döküm verisi `user`
--

INSERT INTO `user` (`id`, `name`, `email`, `password`) VALUES
(1, 'admin', 'admin', 'admin1234');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
