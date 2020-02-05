-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Feb 05, 2020 at 05:57 PM
-- Server version: 5.7.28
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `khaadyam_food`
--

-- --------------------------------------------------------

--
-- Table structure for table `addons`
--

DROP TABLE IF EXISTS `addons`;
CREATE TABLE IF NOT EXISTS `addons` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `shop_id` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `addon_products`
--

DROP TABLE IF EXISTS `addon_products`;
CREATE TABLE IF NOT EXISTS `addon_products` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `addon_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
CREATE TABLE IF NOT EXISTS `admins` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `avatar` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `admins_email_unique` (`email`),
  UNIQUE KEY `admins_phone_unique` (`phone`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`id`, `name`, `avatar`, `email`, `phone`, `password`, `remember_token`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'Admin', NULL, 'khaadhyam@gmail.com', '9359192553', '$2y$10$kX9ZAKgvf1BNEFg4ChuXxuriHE1QmICMXemE8UQG72wCjbHA46y.a', '0wyIBEZD2PaK2V4Ay0weFnyIiqUwvwa0NHhJpuX7qF0OKwbhIfWeUqDg9NHL', NULL, '2019-11-07 10:24:45', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `admin_password_resets`
--

DROP TABLE IF EXISTS `admin_password_resets`;
CREATE TABLE IF NOT EXISTS `admin_password_resets` (
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  KEY `admin_password_resets_email_index` (`email`),
  KEY `admin_password_resets_token_index` (`token`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `admin_password_resets`
--

INSERT INTO `admin_password_resets` (`email`, `token`, `created_at`) VALUES
('khaadhyam@gmail.com', '$2y$10$pPdv0feRAWgQ.qLQgs5wCugVP0rHZufwrnvnmMSro.N13NaWd./9W', '2019-11-06 22:41:00');

-- --------------------------------------------------------

--
-- Table structure for table `bank_details`
--

DROP TABLE IF EXISTS `bank_details`;
CREATE TABLE IF NOT EXISTS `bank_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` int(10) NOT NULL,
  `holder_name` varchar(100) NOT NULL,
  `bank_name` varchar(150) NOT NULL,
  `branch_code` varchar(150) NOT NULL,
  `account_number` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bank_details`
--

INSERT INTO `bank_details` (`id`, `shop_id`, `holder_name`, `bank_name`, `branch_code`, `account_number`) VALUES
(1, 0, 'Shaun', 'Shaun Bank', 'SHAU', '123ABC12345678GH'),
(2, 11, 'Shaun', 'Shaun', '1Shau', 'qwertyuiopasdfgh');

-- --------------------------------------------------------

--
-- Table structure for table `cards`
--

DROP TABLE IF EXISTS `cards`;
CREATE TABLE IF NOT EXISTS `cards` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `card_type` enum('stripe','braintree','bambora') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'stripe',
  `last_four` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `card_id` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `exp_year` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `exp_month` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `brand` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `cvc` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_default` int(11) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cards`
--

INSERT INTO `cards` (`id`, `user_id`, `card_type`, `last_four`, `card_id`, `exp_year`, `exp_month`, `brand`, `cvc`, `is_default`, `created_at`, `updated_at`) VALUES
(6, 3, 'bambora', '4632372003669364', '862871', '28', '4', NULL, '123', 1, '2019-06-27 21:43:54', '2019-06-27 21:43:54'),
(2, 33, 'bambora', '1111111111111141', '589947', '20', '10', NULL, '434', 1, '2019-05-04 21:18:28', '2019-05-04 21:18:28'),
(3, 33, 'bambora', '4242424242424242', '837985', '20', '2', NULL, '333', 1, '2019-05-06 21:47:55', '2019-05-06 21:47:55'),
(4, 37, 'bambora', '6080320365288143', '182113', '26', '5', NULL, '493', 1, '2019-05-09 00:45:22', '2019-05-09 00:45:22'),
(5, 37, 'bambora', '4577044300659144', '712562', '21', '8', NULL, '396', 1, '2019-05-09 00:47:12', '2019-05-09 00:47:12'),
(7, 2, 'bambora', '4320904890000407', '430863', '20', '10', NULL, '434', 1, '2019-06-27 21:48:52', '2019-06-27 21:48:52'),
(8, 97, 'bambora', '5419190206753165', '599191', '23', '4', NULL, '582', 1, '2019-07-18 01:39:52', '2019-07-18 01:39:52'),
(9, 95, 'bambora', '5588996558526685', '747578', '26', '1', NULL, '521', 1, '2019-08-18 01:14:21', '2019-08-18 01:14:21');

-- --------------------------------------------------------

--
-- Table structure for table `cart_addons`
--

DROP TABLE IF EXISTS `cart_addons`;
CREATE TABLE IF NOT EXISTS `cart_addons` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_cart_id` int(11) NOT NULL,
  `addon_product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `shop_id` int(11) NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `position` int(11) DEFAULT NULL,
  `status` enum('enabled','disabled') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'enabled',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `parent_id`, `shop_id`, `name`, `description`, `position`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 0, 168, 'Starter', 'Best', 1, 'enabled', '2019-11-12 15:08:09', NULL, NULL),
(2, 0, 168, 'indian', 'Best', 1, 'enabled', '2019-11-12 15:08:16', NULL, NULL),
(3, 0, 168, 'meal box', 'Best', 1, 'enabled', '2019-11-12 15:08:25', NULL, NULL),
(4, 0, 167, 'dinner', 'Best', 1, 'enabled', '2019-11-12 15:33:40', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `category_images`
--

DROP TABLE IF EXISTS `category_images`;
CREATE TABLE IF NOT EXISTS `category_images` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL,
  `url` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `position` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `category_images`
--

INSERT INTO `category_images` (`id`, `category_id`, `url`, `position`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 1, 'https://khaadyam.com/storage/categories/y64oSYrmDEYGOEukaXWXuOVl0mQEKb2jEX6Lef1s.png', 0, '2019-11-12 15:08:09', NULL, NULL),
(2, 2, 'https://khaadyam.com/storage/categories/y64oSYrmDEYGOEukaXWXuOVl0mQEKb2jEX6Lef1s.png', 0, '2019-11-12 15:08:16', NULL, NULL),
(3, 3, 'https://khaadyam.com/storage/categories/y64oSYrmDEYGOEukaXWXuOVl0mQEKb2jEX6Lef1s.png', 0, '2019-11-12 15:08:25', NULL, NULL),
(4, 4, 'https://khaadyam.com/storage/categories/y64oSYrmDEYGOEukaXWXuOVl0mQEKb2jEX6Lef1s.png', 0, '2019-11-12 15:33:40', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `category_product`
--

DROP TABLE IF EXISTS `category_product`;
CREATE TABLE IF NOT EXISTS `category_product` (
  `category_id` int(10) UNSIGNED NOT NULL,
  `product_id` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`product_id`,`category_id`),
  KEY `category_product_category_id_foreign` (`category_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `category_product`
--

INSERT INTO `category_product` (`category_id`, `product_id`) VALUES
(1, 1),
(4, 2),
(4, 3);

-- --------------------------------------------------------

--
-- Table structure for table `chatuserdnf`
--

DROP TABLE IF EXISTS `chatuserdnf`;
CREATE TABLE IF NOT EXISTS `chatuserdnf` (
  `cud_id` int(11) NOT NULL AUTO_INCREMENT,
  `cud_order_id` varchar(20) NOT NULL,
  `msg_by` varchar(10) NOT NULL,
  `cud_msg` text NOT NULL,
  `cud_date` datetime NOT NULL,
  PRIMARY KEY (`cud_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `cuisines`
--

DROP TABLE IF EXISTS `cuisines`;
CREATE TABLE IF NOT EXISTS `cuisines` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cuisines`
--

INSERT INTO `cuisines` (`id`, `name`, `created_at`, `updated_at`, `deleted_at`) VALUES
(13, 'INDIAN', '2019-10-19 23:56:22', '2019-10-19 23:56:22', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `cuisine_shop`
--

DROP TABLE IF EXISTS `cuisine_shop`;
CREATE TABLE IF NOT EXISTS `cuisine_shop` (
  `cuisine_id` int(10) UNSIGNED NOT NULL,
  `shop_id` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`shop_id`,`cuisine_id`),
  KEY `cuisine_shop_cuisine_id_foreign` (`cuisine_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cuisine_shop`
--

INSERT INTO `cuisine_shop` (`cuisine_id`, `shop_id`) VALUES
(13, 1),
(13, 2),
(13, 3),
(13, 4),
(13, 167),
(13, 168);

-- --------------------------------------------------------

--
-- Table structure for table `custom_pushes`
--

DROP TABLE IF EXISTS `custom_pushes`;
CREATE TABLE IF NOT EXISTS `custom_pushes` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `send_to` enum('ALL','USERS','PROVIDERS') COLLATE utf8mb4_unicode_ci NOT NULL,
  `condition` enum('ACTIVE','LOCATION','RIDES','AMOUNT') COLLATE utf8mb4_unicode_ci NOT NULL,
  `condition_data` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `message` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sent_to` int(11) NOT NULL DEFAULT '0',
  `schedule_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `custom_pushes`
--

INSERT INTO `custom_pushes` (`id`, `send_to`, `condition`, `condition_data`, `message`, `sent_to`, `schedule_at`, `created_at`, `updated_at`) VALUES
(1, 'ALL', 'ACTIVE', NULL, 'hello nitinujdcdcsdjcsckjscskjcscjkscjksjcscjkscscscsc', 0, NULL, '2019-07-18 17:58:58', '2019-07-18 17:58:58'),
(2, 'USERS', 'ACTIVE', 'WEEK', 'hhjjhjkhnjj', 0, NULL, '2019-07-18 18:00:45', '2019-07-18 18:00:45'),
(3, 'ALL', 'ACTIVE', NULL, 'jkjnk', 0, NULL, '2019-08-20 15:05:04', '2019-08-20 15:05:04');

-- --------------------------------------------------------

--
-- Table structure for table `documents`
--

DROP TABLE IF EXISTS `documents`;
CREATE TABLE IF NOT EXISTS `documents` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` enum('DRIVER','VEHICLE') COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `enquiry_transporters`
--

DROP TABLE IF EXISTS `enquiry_transporters`;
CREATE TABLE IF NOT EXISTS `enquiry_transporters` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `failed_jobs`
--

DROP TABLE IF EXISTS `failed_jobs`;
CREATE TABLE IF NOT EXISTS `failed_jobs` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `connection` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `queue` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `payload` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `exception` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `failed_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `favorites`
--

DROP TABLE IF EXISTS `favorites`;
CREATE TABLE IF NOT EXISTS `favorites` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `favourites`
--

DROP TABLE IF EXISTS `favourites`;
CREATE TABLE IF NOT EXISTS `favourites` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `meal`
--

DROP TABLE IF EXISTS `meal`;
CREATE TABLE IF NOT EXISTS `meal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` varchar(5) NOT NULL,
  `name` varchar(100) NOT NULL,
  `image` varchar(500) NOT NULL,
  `created_at` date NOT NULL,
  `updated_at` datetime NOT NULL,
  `type_id` varchar(5) NOT NULL,
  `day_id` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=70 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meal`
--

INSERT INTO `meal` (`id`, `shop_id`, `name`, `image`, `created_at`, `updated_at`, `type_id`, `day_id`) VALUES
(69, '168', 'VEG MASALA', 'http://khaadhyamfoods.achintyaenterprises.com/kf/uploads/shop/1573666245617.png', '2019-11-14', '0000-00-00 00:00:00', '1', '4'),
(67, '167', 'samosa', '', '2019-11-12', '0000-00-00 00:00:00', '1', '2'),
(68, '167', 'dosa', '', '2019-11-12', '0000-00-00 00:00:00', '2', '2'),
(66, '168', 'rf', 'http://khaadhyamfoods.achintyaenterprises.com/kf/uploads/shop/1573661092016.png', '2019-11-13', '0000-00-00 00:00:00', '1', '3'),
(65, '167', 'Dosa', '', '2019-11-09', '0000-00-00 00:00:00', '1', '6'),
(64, '168', 'veg delite', 'https://khaadyam.com/kf/uploads/shop/1573319502710.png', '2019-11-11', '0000-00-00 00:00:00', '1', '1'),
(63, '168', 'veg thali', 'https://khaadyam.com/kf/uploads/shop/1573319402836.png', '2019-11-10', '0000-00-00 00:00:00', '2', '7'),
(62, '3', 'veg thali1', 'https://khaadyam.com/kf/uploads/shop/1572024031175.png', '2019-10-25', '0000-00-00 00:00:00', '2', '5'),
(61, '3', 'veg thali', 'https://khaadyam.com/kf/uploads/shop/1572023874599.png', '2019-10-26', '0000-00-00 00:00:00', '1', '6'),
(60, '2', 'veg', '', '2019-10-24', '0000-00-00 00:00:00', '2', '4'),
(59, '1', 'veg thali', '', '2019-10-22', '0000-00-00 00:00:00', '1', '2'),
(58, '1', 'veg thali', '', '2019-10-20', '0000-00-00 00:00:00', '1', '7');

-- --------------------------------------------------------

--
-- Table structure for table `meal_day`
--

DROP TABLE IF EXISTS `meal_day`;
CREATE TABLE IF NOT EXISTS `meal_day` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `day` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meal_day`
--

INSERT INTO `meal_day` (`id`, `day`) VALUES
(1, 'Monday'),
(2, 'Tuesday'),
(3, 'Wednesday'),
(4, 'Thursday'),
(5, 'Friday'),
(6, 'Saturday'),
(7, 'Sunday');

-- --------------------------------------------------------

--
-- Table structure for table `meal_delivery_day`
--

DROP TABLE IF EXISTS `meal_delivery_day`;
CREATE TABLE IF NOT EXISTS `meal_delivery_day` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meal_id` varchar(5) NOT NULL,
  `day_id` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=70 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meal_delivery_day`
--

INSERT INTO `meal_delivery_day` (`id`, `meal_id`, `day_id`) VALUES
(69, '69', '4'),
(68, '68', '2'),
(67, '67', '2'),
(66, '66', '3'),
(65, '65', '6'),
(64, '64', '1'),
(63, '63', '7'),
(62, '62', '5'),
(61, '61', '6'),
(60, '60', '4'),
(59, '59', '2'),
(58, '58', '7');

-- --------------------------------------------------------

--
-- Table structure for table `meal_delivery_time`
--

DROP TABLE IF EXISTS `meal_delivery_time`;
CREATE TABLE IF NOT EXISTS `meal_delivery_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` varchar(5) NOT NULL,
  `time_slot_id` varchar(5) NOT NULL,
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=147 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meal_delivery_time`
--

INSERT INTO `meal_delivery_time` (`id`, `shop_id`, `time_slot_id`, `type`) VALUES
(131, '3', '9', '2'),
(130, '3', '8', '2'),
(129, '3', '7', '2'),
(128, '3', '6', '2'),
(127, '3', '4', '1'),
(126, '3', '3', '1'),
(125, '3', '2', '1'),
(124, '3', '1', '1'),
(123, '1', '4', '1'),
(122, '1', '3', '1'),
(121, '1', '2', '1'),
(120, '1', '1', '1'),
(132, '168', '1', '1'),
(133, '168', '2', '1'),
(134, '168', '3', '1'),
(135, '168', '4', '1'),
(136, '168', '6', '2'),
(137, '168', '7', '2'),
(138, '168', '8', '2'),
(139, '168', '9', '2'),
(140, '167', '6', '2'),
(141, '167', '7', '2'),
(142, '167', '8', '2'),
(143, '167', '9', '2'),
(144, '167', '1', '1'),
(145, '167', '2', '1'),
(146, '167', '3', '1');

-- --------------------------------------------------------

--
-- Table structure for table `meal_delivery_type`
--

DROP TABLE IF EXISTS `meal_delivery_type`;
CREATE TABLE IF NOT EXISTS `meal_delivery_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meal_id` varchar(5) NOT NULL,
  `type_id` varchar(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=70 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meal_delivery_type`
--

INSERT INTO `meal_delivery_type` (`id`, `meal_id`, `type_id`) VALUES
(69, '69', '1'),
(68, '68', '2'),
(67, '67', '1'),
(66, '66', '1'),
(65, '65', '1'),
(64, '64', '1'),
(63, '63', '2'),
(62, '62', '2'),
(61, '61', '1'),
(60, '60', '2'),
(59, '59', '1'),
(58, '58', '1');

-- --------------------------------------------------------

--
-- Table structure for table `meal_menu`
--

DROP TABLE IF EXISTS `meal_menu`;
CREATE TABLE IF NOT EXISTS `meal_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meal_id` varchar(5) NOT NULL,
  `item_key` varchar(100) NOT NULL,
  `item_value` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meal_menu`
--

INSERT INTO `meal_menu` (`id`, `meal_id`, `item_key`, `item_value`) VALUES
(1, '58', 'rice', '1'),
(2, '59', 'rice', '1'),
(3, '60', 'chapati', '5'),
(4, '61', 'chapati', '4'),
(5, '61', 'bengan sabji', '1'),
(6, '61', 'rice', '1'),
(7, '61', 'dal', '1'),
(8, '62', 'rice', '1'),
(9, '62', 'dal', '1'),
(10, '62', 'chapati', '4'),
(11, '62', 'bhaji', '1'),
(12, '63', 'rice', '1'),
(13, '63', 'dal', '1'),
(14, '64', 'chapati', '4'),
(15, '64', 'sabji', '1'),
(16, '65', 'samosa', '5'),
(17, '67', 'samosa', '20'),
(18, '68', 'dosa', '40'),
(19, '66', 'rice', '1'),
(20, '69', 'chapati', '4'),
(21, '66', 'bhaji', '1');

-- --------------------------------------------------------

--
-- Table structure for table `meal_order`
--

DROP TABLE IF EXISTS `meal_order`;
CREATE TABLE IF NOT EXISTS `meal_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(10) NOT NULL,
  `meal_id` varchar(10) NOT NULL,
  `shop_id` varchar(10) NOT NULL,
  `total_days` varchar(10) NOT NULL,
  `start_date` varchar(15) NOT NULL,
  `end_date` varchar(15) NOT NULL,
  `time_slot` varchar(10) NOT NULL,
  `created_at` datetime NOT NULL,
  `total_payable` varchar(15) NOT NULL,
  `address` text NOT NULL,
  `status` varchar(5) NOT NULL,
  `payment_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meal_order`
--

INSERT INTO `meal_order` (`id`, `user_id`, `meal_id`, `shop_id`, `total_days`, `start_date`, `end_date`, `time_slot`, `created_at`, `total_payable`, `address`, `status`, `payment_id`) VALUES
(45, '1', '', '168', '28', '2019-11-13', '2019-12-10', '6', '2019-11-13 08:17:04', '39200', '14, Rahatani Rd, Jagtap Dairy, Pimple Saudagar, Pune, Maharashtra 411017, India , 1 , s', '1', 'pay_Dff6RM10cQ02kM'),
(46, '1', '', '168', '3', '2019-11-14', '2019-11-16', '1', '2019-11-14 10:29:26', '150', '14, Rahatani Rd, Jagtap Dairy, Pimple Saudagar, Pune, Maharashtra 411017, India , 1 , s', '1', 'pay_Dg5tNnPTtst8Tv'),
(44, '1', '', '1', '3', '2019-10-20', '2019-10-22', '1', '2019-10-20 11:38:24', '150', '14, Rahatani Rd, Jagtap Dairy, Pimple Saudagar, Pune, Maharashtra 411017, India , b101 , vijay sales', '1', 'pay_DWPymx31SZVO4H');

-- --------------------------------------------------------

--
-- Table structure for table `meal_price`
--

DROP TABLE IF EXISTS `meal_price`;
CREATE TABLE IF NOT EXISTS `meal_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meal_id` varchar(10) NOT NULL,
  `price` varchar(10) NOT NULL,
  `for_subscription_id` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=89 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `meal_shop_price`
--

DROP TABLE IF EXISTS `meal_shop_price`;
CREATE TABLE IF NOT EXISTS `meal_shop_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` varchar(10) NOT NULL,
  `price` varchar(10) NOT NULL,
  `subscription_id` varchar(5) NOT NULL,
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=69 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meal_shop_price`
--

INSERT INTO `meal_shop_price` (`id`, `shop_id`, `price`, `subscription_id`, `type`) VALUES
(56, '3', '1000', '4', '1'),
(55, '3', '550', '3', '1'),
(54, '3', '260', '2', '1'),
(53, '3', '40', '1', '1'),
(52, '1', '2800', '4', '1'),
(51, '1', '1500', '3', '1'),
(50, '1', '250', '2', '1'),
(49, '1', '50', '1', '1'),
(57, '3', '45', '1', '2'),
(58, '3', '400', '2', '2'),
(59, '3', '700', '3', '2'),
(60, '3', '1300', '4', '2'),
(61, '168', '50', '1', '1'),
(62, '168', '350', '2', '1'),
(63, '168', '750', '3', '1'),
(64, '168', '1500', '4', '1'),
(65, '168', '60', '1', '2'),
(66, '168', '180', '2', '2'),
(67, '168', '320', '3', '2'),
(68, '168', '1400', '4', '2');

-- --------------------------------------------------------

--
-- Table structure for table `meal_subscription_pack`
--

DROP TABLE IF EXISTS `meal_subscription_pack`;
CREATE TABLE IF NOT EXISTS `meal_subscription_pack` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `days` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meal_subscription_pack`
--

INSERT INTO `meal_subscription_pack` (`id`, `days`) VALUES
(1, '3 Days'),
(2, '7 Days'),
(3, '15 Day'),
(4, '28 Days');

-- --------------------------------------------------------

--
-- Table structure for table `meal_time_slot`
--

DROP TABLE IF EXISTS `meal_time_slot`;
CREATE TABLE IF NOT EXISTS `meal_time_slot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time_slot` varchar(20) NOT NULL,
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meal_time_slot`
--

INSERT INTO `meal_time_slot` (`id`, `time_slot`, `type`) VALUES
(2, '1:00 PM - 1:45 PM', '1'),
(3, '1:45 PM - 3:00 PM', '1'),
(1, '12:00 PM - 1:00 PM', '1'),
(4, '3:00 PM - 4:00 PM', '1'),
(6, '7:00 PM - 7:45 PM', '2'),
(7, '7:45 PM - 9:00 PM', '2'),
(8, '9:00 PM - 9:45 PM', '2'),
(9, '9:45 PM - 11:00 PM', '2');

-- --------------------------------------------------------

--
-- Table structure for table `meal_type`
--

DROP TABLE IF EXISTS `meal_type`;
CREATE TABLE IF NOT EXISTS `meal_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meal_type`
--

INSERT INTO `meal_type` (`id`, `type`) VALUES
(1, 'Lunch'),
(2, 'Dinner');

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--

DROP TABLE IF EXISTS `migrations`;
CREATE TABLE IF NOT EXISTS `migrations` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `migration` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2014_10_12_000000_create_users_table', 1),
(2, '2014_10_12_100000_create_password_resets_table', 1),
(3, '2015_08_25_172600_create_settings_table', 1),
(4, '2016_06_01_000001_create_oauth_auth_codes_table', 1),
(5, '2016_06_01_000002_create_oauth_access_tokens_table', 1),
(6, '2016_06_01_000003_create_oauth_refresh_tokens_table', 1),
(7, '2016_06_01_000004_create_oauth_clients_table', 1),
(8, '2016_06_01_000005_create_oauth_personal_access_clients_table', 1),
(9, '2017_01_11_181312_create_cards_table', 1),
(10, '2017_01_11_182717_create_request_filters_table', 1),
(11, '2017_06_08_123940_create_admins_table', 1),
(12, '2017_06_08_123941_create_admin_password_resets_table', 1),
(13, '2017_06_09_044823_create_shops_table', 1),
(14, '2017_06_09_044824_create_shop_password_resets_table', 1),
(15, '2017_06_09_045212_create_transporters_table', 1),
(16, '2017_06_09_045213_create_transporter_password_resets_table', 1),
(17, '2017_06_09_102917_create_products_table', 1),
(18, '2017_06_09_105225_create_categories_table', 1),
(19, '2017_06_12_064740_create_user_addresses_table', 1),
(20, '2017_06_12_101042_create_orders_table', 1),
(21, '2017_06_12_101114_create_order_invoices_table', 1),
(22, '2017_06_12_101147_create_order_ratings_table', 1),
(23, '2017_06_12_101212_create_order_disputes_table', 1),
(24, '2017_06_12_101253_create_promocodes_table', 1),
(25, '2017_06_12_101446_create_surge_pricings_table', 1),
(26, '2017_06_12_113225_create_notifications_table', 1),
(27, '2017_06_13_124334_create_zones_table', 1),
(28, '2017_06_13_125525_create_transporter_documents_table', 1),
(29, '2017_06_13_130530_create_category_images_table', 1),
(30, '2017_06_15_115211_create_product_images_table', 1),
(31, '2017_06_15_115323_create_product_prices_table', 1),
(32, '2017_06_16_112721_create_user_carts_table', 1),
(33, '2017_06_23_124807_create_oauth_access_token_guards', 1),
(34, '2017_08_03_194354_create_custom_pushes_table', 1),
(35, '2017_09_19_053529_create_cuisines_table', 1),
(36, '2017_09_19_053543_create_documents_table', 1),
(37, '2017_09_19_053556_create_order_dispute_comments_table', 1),
(38, '2017_09_19_053608_create_promocode_usages_table', 1),
(39, '2017_09_19_053619_create_transporter_vehicles_table', 1),
(40, '2017_09_19_053635_create_transporter_shifts_table', 1),
(41, '2017_09_19_053648_create_transporter_shift_timings_table', 1),
(42, '2017_09_19_053659_create_wallet_passbooks_table', 1),
(43, '2017_09_19_110911_create_shop_timings_table', 1),
(44, '2017_09_25_125459_create_transporter_locations_table', 1),
(45, '2017_10_04_130254_create_order_timings_table', 1),
(46, '2017_10_07_103112_create_favorites_table', 1),
(47, '2017_10_12_122111_create_shop_banners_table', 1),
(48, '2017_10_13_062225_create_notice_boards_table', 1),
(49, '2017_10_20_070027_create_order_dispute_helps_table', 1),
(50, '2017_10_21_063643_create_permission_tables', 1),
(51, '2017_11_06_111106_create_addons_table', 1),
(52, '2017_11_08_121819_create_cart_addons_table', 1),
(53, '2017_11_08_133602_create_enquiry_transporters_table', 1),
(54, '2017_11_09_062754_create_addon_products_table', 1),
(55, '2017_11_27_142447_create_restuarants_table', 1),
(56, '2017_11_28_063349_create_newsletters_table', 1),
(57, '2017_12_05_043339_add_delivery_date_to_orders', 1),
(58, '2017_12_06_091412_add_schedule_status_to_orders', 1),
(59, '2018_02_01_105814_create_favourites_table', 1),
(60, '2019_08_19_000000_create_failed_jobs_table', 2);

-- --------------------------------------------------------

--
-- Table structure for table `model_has_permissions`
--

DROP TABLE IF EXISTS `model_has_permissions`;
CREATE TABLE IF NOT EXISTS `model_has_permissions` (
  `permission_id` int(10) UNSIGNED NOT NULL,
  `model_id` int(10) UNSIGNED NOT NULL,
  `model_type` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`permission_id`,`model_id`,`model_type`),
  KEY `model_has_permissions_model_id_model_type_index` (`model_id`,`model_type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `model_has_roles`
--

DROP TABLE IF EXISTS `model_has_roles`;
CREATE TABLE IF NOT EXISTS `model_has_roles` (
  `role_id` int(10) UNSIGNED NOT NULL,
  `model_id` int(10) UNSIGNED NOT NULL,
  `model_type` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`role_id`,`model_id`,`model_type`),
  KEY `model_has_roles_model_id_model_type_index` (`model_id`,`model_type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `model_has_roles`
--

INSERT INTO `model_has_roles` (`role_id`, `model_id`, `model_type`) VALUES
(1, 1, 'App\\Admin'),
(2, 2, 'App\\Admin');

-- --------------------------------------------------------

--
-- Table structure for table `newsletters`
--

DROP TABLE IF EXISTS `newsletters`;
CREATE TABLE IF NOT EXISTS `newsletters` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `notice_boards`
--

DROP TABLE IF EXISTS `notice_boards`;
CREATE TABLE IF NOT EXISTS `notice_boards` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `transporter_id` int(11) NOT NULL,
  `title` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `notice` text COLLATE utf8mb4_unicode_ci,
  `note` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `notice_boards`
--

INSERT INTO `notice_boards` (`id`, `transporter_id`, `title`, `notice`, `note`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 31, 'hello', 'xsxsxsxsaxaxasxsxsxsx', 'xsxscsscscscscsc', '2019-07-18 18:02:18', '2019-07-18 18:02:18', NULL),
(2, 36, 'hellon demo16', 'ddm,dvdmvdemlvdvm,dvd', 'dvdvdvdvdvdv', '2019-07-18 18:27:35', '2019-07-18 18:27:35', NULL),
(3, 36, 'hello mr nitin', 'bro just go for the ordewr', 'cdcdscscscscscscscscscscscscscs', '2019-07-18 18:28:13', '2019-07-18 18:28:13', NULL),
(4, 36, 'not woking properly', 'hbhjhbh', 'bjhbhjbh', '2019-07-19 00:45:35', '2019-07-19 00:45:35', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
CREATE TABLE IF NOT EXISTS `notifications` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `transporter_id` int(11) NOT NULL DEFAULT '0',
  `message` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `notifications`
--

INSERT INTO `notifications` (`id`, `user_id`, `transporter_id`, `message`, `created_at`, `updated_at`) VALUES
(1, 1, 0, 'Your Order Created Successfully', '2019-11-12 09:42:36', '2019-11-12 09:42:36'),
(2, 1, 0, 'Your order Delivery Otp is 893349', '2019-11-12 09:42:37', '2019-11-12 09:42:37');

-- --------------------------------------------------------

--
-- Table structure for table `oauth_access_tokens`
--

DROP TABLE IF EXISTS `oauth_access_tokens`;
CREATE TABLE IF NOT EXISTS `oauth_access_tokens` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `client_id` int(11) NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `scopes` text COLLATE utf8mb4_unicode_ci,
  `revoked` tinyint(1) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `expires_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `oauth_access_tokens_user_id_index` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `oauth_access_tokens`
--

INSERT INTO `oauth_access_tokens` (`id`, `user_id`, `client_id`, `name`, `scopes`, `revoked`, `created_at`, `updated_at`, `expires_at`) VALUES
('68fc4c8b5ca95e1b2d59c62ffeabf739b62d2b3321461a6d9227f89c6b65f94b60e1fd546086411f', 8, 4, NULL, '[]', 0, '2020-02-02 12:18:39', '2020-02-02 12:18:39', '2021-02-02 17:48:39'),
('b62c3d23be1d3f7f834a1397c18c251c2117b590e1dac4980fff3fc38c11e0c279cf70e1988c2338', 8, 4, NULL, '[]', 0, '2020-02-02 12:27:32', '2020-02-02 12:27:32', '2021-02-02 17:57:32'),
('02da38b8ec15ecd5d3a2057e1bb6a42b4fb7b8ed7a8046dcd509006dc0b34fbb97b96ab40593a72e', 8, 4, NULL, '[]', 0, '2020-02-02 12:27:39', '2020-02-02 12:27:39', '2021-02-02 17:57:39'),
('7ae699ee849978c294f2b1f3457be00bf90bd2232b0ddd997d07c503a1adb0a6555dddbe29e76d0b', 8, 4, NULL, '[]', 0, '2020-02-02 12:27:41', '2020-02-02 12:27:41', '2021-02-02 17:57:41'),
('234771404dad98e43811f06a87c91f37e4167ee20c9c198a6b67260e3b0cb43109ea213a46d50ddf', 8, 4, NULL, '[]', 0, '2020-02-02 12:31:55', '2020-02-02 12:31:55', '2021-02-02 18:01:55'),
('8c719f851e8a424cce9f1bda890ec9dbcffe2a4ce118f8d0c18f70367442f1b83e266e0b545a06de', 8, 4, NULL, '[]', 0, '2020-02-02 12:32:43', '2020-02-02 12:32:43', '2021-02-02 18:02:43'),
('02a10707ca3cbcb1f44ee2aebe3c043062d73c9ff0b2f74d282730cfa75055f41c97ab79ab668e9d', 8, 4, NULL, '[]', 0, '2020-02-02 23:38:51', '2020-02-02 23:38:51', '2021-02-03 05:08:51'),
('32c14ea2e8a7465d82069f1cd06778de44db1fa679cfcd90da9e985be1f8ee60a70caa6fe02dbe10', 8, 4, NULL, '[]', 0, '2020-02-02 23:39:51', '2020-02-02 23:39:51', '2021-02-03 05:09:51'),
('1e1cb0486de011a6ffde9813145ea18eac0e3afc8f5ee863806c209d6669ce0f7ff898364f990888', 8, 4, NULL, '[]', 0, '2020-02-03 00:57:57', '2020-02-03 00:57:57', '2021-02-03 06:27:57'),
('32d3f6a28631d4c398ded1747f9787883f8d00a2a1e2bca0d0e678342857d3f9f04d873bad3d4847', 10, 4, NULL, '[]', 0, '2020-02-03 00:59:38', '2020-02-03 00:59:38', '2021-02-03 06:29:38'),
('b70a726e55bb384530d2f68dfc268fa6ea09bdb0dfeb439f138966ae7253d2fc1e86f05de89a3c8d', 10, 4, NULL, '[]', 0, '2020-02-03 01:00:13', '2020-02-03 01:00:13', '2021-02-03 06:30:13'),
('0dc0091fd7e8e678944c14c55df439c08c4bdebf050ef625a25761dcb3b2f5a56cb87eaed0815342', 10, 4, NULL, '[]', 0, '2020-02-03 01:00:51', '2020-02-03 01:00:51', '2021-02-03 06:30:51'),
('e185715e9bfc0b08a314c6908ad43ea37a85a08c53fdf90a5ff95caa4e15c695f0c26e32e039faa3', 8, 4, NULL, '[]', 0, '2020-02-03 01:01:19', '2020-02-03 01:01:19', '2021-02-03 06:31:19'),
('d2b92017d3d92922eb313dd02448be2645f11b560ba4daaf8f468bd7160c642db17fb2bb392581dc', 8, 4, NULL, '[]', 0, '2020-02-03 01:29:21', '2020-02-03 01:29:21', '2021-02-03 06:59:21'),
('ed5a66df3584a5db6a91e8164a4b6bffcfc54aff6d48b8c3ac13a8d88f1b31a287c2477f10ae4f56', 10, 4, NULL, '[]', 0, '2020-02-03 01:30:14', '2020-02-03 01:30:14', '2021-02-03 07:00:14'),
('75af6826f45bb69435a3b1ae53b705ccf7b0b0040eb0453093023b726bd4130f2c712cc2ebb1515d', 8, 4, NULL, '[]', 0, '2020-02-03 01:36:39', '2020-02-03 01:36:39', '2021-02-03 07:06:39'),
('6ffe94377ef8ca538207b314ec40d267f961f648b12d93dc405834e409f230b7c5b0d08c6674f76d', 8, 4, NULL, '[]', 0, '2020-02-03 01:54:02', '2020-02-03 01:54:02', '2021-02-03 07:24:02'),
('94347a516b52d3844ba6f652f69ba13d27c5ea0f58b5363a431791b1c3b150af80855f3142053baa', 8, 4, NULL, '[]', 0, '2020-02-03 02:10:20', '2020-02-03 02:10:20', '2021-02-03 07:40:20'),
('b191abc4a655e876e06337ae2425f7b3ae829e8e96cc12ecb82b416df38f29b0e1fca748b70fe3c2', 8, 4, NULL, '[]', 0, '2020-02-03 02:11:27', '2020-02-03 02:11:27', '2021-02-03 07:41:27'),
('0f7640bfe3dbb7daa3a36003fbfebaac6d910d76e37fe58c698bab1266db9217bdeffeecc9d4dfbd', 10, 4, NULL, '[]', 0, '2020-02-03 02:18:03', '2020-02-03 02:18:03', '2021-02-03 07:48:03'),
('5cf494fea4ee7a54b62782eb35c861f76911c608e173d1643b7ad13bf3d910bdcc5883aa87142dfc', 10, 4, NULL, '[]', 0, '2020-02-03 02:21:37', '2020-02-03 02:21:37', '2021-02-03 07:51:37'),
('4e2ab990205925a9a8214e5ec9ce29743745e261d60244bdba859bccd47eee44b5fd3590cf4079d9', 8, 4, NULL, '[]', 0, '2020-02-03 11:21:30', '2020-02-03 11:21:30', '2021-02-03 16:51:30'),
('45602910790cda83f63e354b7d0ebf329a98bdda25ed697614ff54ed8d3578f4e330e8fc3b53ad3d', 8, 4, NULL, '[]', 0, '2020-02-03 11:21:52', '2020-02-03 11:21:52', '2021-02-03 16:51:52'),
('cdf91ee978bbd523481d7f924abca620540e8b998a64645a0c275b26e96d53ccd72120478469e43d', 8, 4, NULL, '[]', 0, '2020-02-03 11:22:06', '2020-02-03 11:22:06', '2021-02-03 16:52:06'),
('384fb31308c8cde96e3c14078ce69d00b55622c114cce9c356102a660e01fa88ec3200e678114aae', 8, 4, NULL, '[]', 0, '2020-02-03 11:22:19', '2020-02-03 11:22:19', '2021-02-03 16:52:19'),
('acd045d6795a4c7f8b0ff77979873429e8f9ceba899bbb5ef9d5b853b510fda21ef41e3199b58778', 8, 4, NULL, '[]', 0, '2020-02-03 11:23:10', '2020-02-03 11:23:10', '2021-02-03 16:53:10');

-- --------------------------------------------------------

--
-- Table structure for table `oauth_access_token_providers`
--

DROP TABLE IF EXISTS `oauth_access_token_providers`;
CREATE TABLE IF NOT EXISTS `oauth_access_token_providers` (
  `oauth_access_token_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `provider` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`oauth_access_token_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `oauth_auth_codes`
--

DROP TABLE IF EXISTS `oauth_auth_codes`;
CREATE TABLE IF NOT EXISTS `oauth_auth_codes` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `scopes` text COLLATE utf8mb4_unicode_ci,
  `revoked` tinyint(1) NOT NULL,
  `expires_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `oauth_clients`
--

DROP TABLE IF EXISTS `oauth_clients`;
CREATE TABLE IF NOT EXISTS `oauth_clients` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `secret` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `redirect` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `personal_access_client` tinyint(1) NOT NULL,
  `password_client` tinyint(1) NOT NULL,
  `revoked` tinyint(1) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `oauth_clients_user_id_index` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `oauth_clients`
--

INSERT INTO `oauth_clients` (`id`, `user_id`, `name`, `secret`, `redirect`, `personal_access_client`, `password_client`, `revoked`, `created_at`, `updated_at`) VALUES
(1, NULL, 'Laravel Personal Access Client', 'pJ1yPlzbIYZhcrdgRTYpM0XWsPnBcFnD8j1QwukO', 'http://localhost', 1, 0, 0, '2020-02-02 08:11:09', '2020-02-02 08:11:09'),
(2, NULL, 'Laravel Password Grant Client', 'okWpCezhC0k06e2OTVZOLPFJyXuD3W8LziJi4JZD', 'http://localhost', 0, 1, 0, '2020-02-02 08:11:09', '2020-02-02 08:11:09'),
(3, NULL, 'Laravel Personal Access Client', 'IoAaB5Tb4da7oXBWwY6igCZpHIdVkXDvxjm7dWPA', 'http://localhost', 1, 0, 0, '2020-02-02 11:09:38', '2020-02-02 11:09:38'),
(4, NULL, 'Laravel Password Grant Client', 'pLAkjJmdzA8kWBkS8Exih3cSjSMdPognCyz89JSh', 'http://localhost', 0, 1, 0, '2020-02-02 11:09:38', '2020-02-02 11:09:38');

-- --------------------------------------------------------

--
-- Table structure for table `oauth_personal_access_clients`
--

DROP TABLE IF EXISTS `oauth_personal_access_clients`;
CREATE TABLE IF NOT EXISTS `oauth_personal_access_clients` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `client_id` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `oauth_personal_access_clients_client_id_index` (`client_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `oauth_personal_access_clients`
--

INSERT INTO `oauth_personal_access_clients` (`id`, `client_id`, `created_at`, `updated_at`) VALUES
(1, 1, '2020-02-02 08:11:09', '2020-02-02 08:11:09'),
(2, 3, '2020-02-02 11:09:38', '2020-02-02 11:09:38');

-- --------------------------------------------------------

--
-- Table structure for table `oauth_refresh_tokens`
--

DROP TABLE IF EXISTS `oauth_refresh_tokens`;
CREATE TABLE IF NOT EXISTS `oauth_refresh_tokens` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `access_token_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `revoked` tinyint(1) NOT NULL,
  `expires_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `oauth_refresh_tokens_access_token_id_index` (`access_token_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `oauth_refresh_tokens`
--

INSERT INTO `oauth_refresh_tokens` (`id`, `access_token_id`, `revoked`, `expires_at`) VALUES
('ecb0ecf9f7f6bf071a60a647f5d80c8c80d8c1b7c9546c7cc06bcf0e96f840bf2e7423a54705cb83', '68fc4c8b5ca95e1b2d59c62ffeabf739b62d2b3321461a6d9227f89c6b65f94b60e1fd546086411f', 0, '2021-02-02 17:48:39'),
('c1d8080fbd10b9206d4e710472bb490978fdfbd97381b9a274f064cb386d45ee744bb95fc0b1a530', 'b62c3d23be1d3f7f834a1397c18c251c2117b590e1dac4980fff3fc38c11e0c279cf70e1988c2338', 0, '2021-02-02 17:57:32'),
('8850f5bf5c2d4c1f31542955296071884410a6584992f6cc616a30e04850783409c98933a4bd6b3b', '02da38b8ec15ecd5d3a2057e1bb6a42b4fb7b8ed7a8046dcd509006dc0b34fbb97b96ab40593a72e', 0, '2021-02-02 17:57:39'),
('45814b748ac4e4240f2d53c0e4337033cf6b5f64c9adba61c18cbe73aa5e86ede445e81f687831a6', '7ae699ee849978c294f2b1f3457be00bf90bd2232b0ddd997d07c503a1adb0a6555dddbe29e76d0b', 0, '2021-02-02 17:57:41'),
('8afe4287b8d89de0308943d99c95c020476a1712d25f907130e82c7058b3abe09e4b296e935825e5', '234771404dad98e43811f06a87c91f37e4167ee20c9c198a6b67260e3b0cb43109ea213a46d50ddf', 0, '2021-02-02 18:01:55'),
('9ce22d600e3e519eb2751f621f908ec9d2e7c1f30d17045c547ef7f24c66724439aacc7ecd7c22d3', '8c719f851e8a424cce9f1bda890ec9dbcffe2a4ce118f8d0c18f70367442f1b83e266e0b545a06de', 0, '2021-02-02 18:02:43'),
('076d8d1959700e252d82e7339b46a29640e16c6131329b44b6d368f6e52a3f7ab4f8762867d88237', '02a10707ca3cbcb1f44ee2aebe3c043062d73c9ff0b2f74d282730cfa75055f41c97ab79ab668e9d', 0, '2021-02-03 05:08:51'),
('29c43d1b4501d4266784017bbfc9c68009253b5c8f396fe7aeb2f08f918dcdba2bd66ea96873afc8', '32c14ea2e8a7465d82069f1cd06778de44db1fa679cfcd90da9e985be1f8ee60a70caa6fe02dbe10', 0, '2021-02-03 05:09:51'),
('ea0f192c871637e4e2e75ffe0e791baddc0c1dab358e9b36f35b83796feb936dcd0ebbd48ac3bf32', '1e1cb0486de011a6ffde9813145ea18eac0e3afc8f5ee863806c209d6669ce0f7ff898364f990888', 0, '2021-02-03 06:27:57'),
('21b7e298e00d5a9e2afcdce692bdc4fb4f5865cfe2d50f02269e25c8271fd72c7b9faae3adf07520', '32d3f6a28631d4c398ded1747f9787883f8d00a2a1e2bca0d0e678342857d3f9f04d873bad3d4847', 0, '2021-02-03 06:29:38'),
('645220b3dc5153072b9e839c42324933bce162d2262a667b49f4a1c351eb43c3db61746a6c860fda', 'b70a726e55bb384530d2f68dfc268fa6ea09bdb0dfeb439f138966ae7253d2fc1e86f05de89a3c8d', 0, '2021-02-03 06:30:13'),
('121c6d8f587d5262af9750c802dc88ca8fdc4303af9ef360e31dce83665d24f636a59a6b80f5967e', '0dc0091fd7e8e678944c14c55df439c08c4bdebf050ef625a25761dcb3b2f5a56cb87eaed0815342', 0, '2021-02-03 06:30:51'),
('e6a4075734a243fe1799b938b0b7dbfb834b350d4944d4fb670fe25323c7171328b8c702a2b2f98a', 'e185715e9bfc0b08a314c6908ad43ea37a85a08c53fdf90a5ff95caa4e15c695f0c26e32e039faa3', 0, '2021-02-03 06:31:19'),
('7c7ac7d380f97559cd8570b751d45c74b3d265faab780a6d96a4d972581518c5e29491b4ff83b13d', 'd2b92017d3d92922eb313dd02448be2645f11b560ba4daaf8f468bd7160c642db17fb2bb392581dc', 0, '2021-02-03 06:59:21'),
('890661bc216704d93571a3bf361b21ed11f9d849ebc44d459a63909bb5d6fe2af115643e3b0f8e3e', 'ed5a66df3584a5db6a91e8164a4b6bffcfc54aff6d48b8c3ac13a8d88f1b31a287c2477f10ae4f56', 0, '2021-02-03 07:00:14'),
('ee5f440eebd1304920df9c7b9bfd277bc43513846dc88efae3b9acf35a260c7ae9d4a58b1b0b41fc', '75af6826f45bb69435a3b1ae53b705ccf7b0b0040eb0453093023b726bd4130f2c712cc2ebb1515d', 0, '2021-02-03 07:06:39'),
('b09b7b26442b6654294ddbbadcc005cb6a30c56e609cb1ff85a97c691fd97503206a95246eccc21e', '6ffe94377ef8ca538207b314ec40d267f961f648b12d93dc405834e409f230b7c5b0d08c6674f76d', 0, '2021-02-03 07:24:02'),
('577dfdd246fa05f7838728037bcd8790953b73e49241627504a6c80021d5df1df463e32492d51c3e', '94347a516b52d3844ba6f652f69ba13d27c5ea0f58b5363a431791b1c3b150af80855f3142053baa', 0, '2021-02-03 07:40:20'),
('87a05eabdc9bead0e53223bd073edb8ae3d7263798aab6d7c4f7661883a1d8bf834191184858a91f', 'b191abc4a655e876e06337ae2425f7b3ae829e8e96cc12ecb82b416df38f29b0e1fca748b70fe3c2', 0, '2021-02-03 07:41:27'),
('0aa98c4e1fae135f34c17451e7c17ae12c6176a88ef287c7d1c589e79181649c1ef87ca5ca0f3cf9', '0f7640bfe3dbb7daa3a36003fbfebaac6d910d76e37fe58c698bab1266db9217bdeffeecc9d4dfbd', 0, '2021-02-03 07:48:03'),
('24b5fe3f8e9397e6d9023523a46bf9b36817c9dc92d113a255da3de64861fef9a99864ccf9b6842b', '5cf494fea4ee7a54b62782eb35c861f76911c608e173d1643b7ad13bf3d910bdcc5883aa87142dfc', 0, '2021-02-03 07:51:37'),
('e7e552bd547407a253c9ffb4552d05f0f0a7a29c8d55df1fb0df1d80d05d448c07be4c9de4b857a7', '4e2ab990205925a9a8214e5ec9ce29743745e261d60244bdba859bccd47eee44b5fd3590cf4079d9', 0, '2021-02-03 16:51:31'),
('a19c8c3c8df95eb4c9cc93bd90745ef443c8faada4f637310bd95ba0e06f9baee437f17ee18f5e1c', '45602910790cda83f63e354b7d0ebf329a98bdda25ed697614ff54ed8d3578f4e330e8fc3b53ad3d', 0, '2021-02-03 16:51:52'),
('a89f2b10774fabb76cb2a367b491673527ef38d399ee66771ba84d3a3c5a71d235ddf79c736a005c', 'cdf91ee978bbd523481d7f924abca620540e8b998a64645a0c275b26e96d53ccd72120478469e43d', 0, '2021-02-03 16:52:06'),
('dac38de78827805f2dc33eb1f4cee8a7dd1cd42c65fcb5c70a9ee6cff88a5951658b446626f45746', '384fb31308c8cde96e3c14078ce69d00b55622c114cce9c356102a660e01fa88ec3200e678114aae', 0, '2021-02-03 16:52:19'),
('5a3494d6b11e055367ff893710255e59db23db9340b404683fbf9af468c06e4583e63fab169c392f', 'acd045d6795a4c7f8b0ff77979873429e8f9ceba899bbb5ef9d5b853b510fda21ef41e3199b58778', 0, '2021-02-03 16:53:10');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `invoice_id` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  `shift_id` int(11) DEFAULT NULL,
  `user_address_id` int(11) NOT NULL,
  `shop_id` int(11) DEFAULT NULL,
  `transporter_id` int(11) DEFAULT NULL,
  `transporter_vehicle_id` int(11) DEFAULT NULL,
  `reason` text COLLATE utf8mb4_unicode_ci,
  `note` text COLLATE utf8mb4_unicode_ci,
  `route_key` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `dispute` enum('CREATED','RESOLVE','NODISPUTE') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'NODISPUTE',
  `delivery_date` datetime NOT NULL,
  `order_otp` int(11) NOT NULL,
  `order_ready_time` int(11) NOT NULL DEFAULT '0',
  `order_ready_status` int(11) NOT NULL DEFAULT '0',
  `status` enum('ORDERED','RECEIVED','CANCELLED','ASSIGNED','PROCESSING','SEARCHING','REACHED','PICKEDUP','ARRIVED','COMPLETED') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'ORDERED',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `schedule_status` int(11) NOT NULL DEFAULT '0',
  `tip` int(11) DEFAULT NULL,
  `total_distance` int(11) DEFAULT NULL,
  `pickup` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `invoice_id`, `user_id`, `shift_id`, `user_address_id`, `shop_id`, `transporter_id`, `transporter_vehicle_id`, `reason`, `note`, `route_key`, `dispute`, `delivery_date`, `order_otp`, `order_ready_time`, `order_ready_status`, `status`, `created_at`, `updated_at`, `deleted_at`, `schedule_status`, `tip`, `total_distance`, `pickup`) VALUES
(22, 'ef7fd693-b652-4c10-be94-07e777c42f1a', 1, NULL, 1, 168, NULL, NULL, NULL, NULL, 'mhopBsekaMPGJBLHBPANKLOBOEKMAK?E@Ci@_Aq@w@w@s@aC}B{DeEwCaDe@e@w@bCi@e@u@u@', 'NODISPUTE', '2019-11-12 04:42:00', 893349, 0, 0, 'RECEIVED', '2019-11-12 09:42:35', '2019-11-12 09:42:36', NULL, 0, 0, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `order_disputes`
--

DROP TABLE IF EXISTS `order_disputes`;
CREATE TABLE IF NOT EXISTS `order_disputes` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `order_disputehelp_id` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) DEFAULT NULL,
  `transporter_id` int(11) DEFAULT NULL,
  `shop_id` int(11) DEFAULT NULL,
  `type` enum('CANCELLED','COMPLAINED','REFUND','REASSIGN') COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_by` enum('user','shop','transporter') COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_to` enum('user','shop','transporter','dispatcher') COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` enum('CREATED','RESOLVED') COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_dispute_comments`
--

DROP TABLE IF EXISTS `order_dispute_comments`;
CREATE TABLE IF NOT EXISTS `order_dispute_comments` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) NOT NULL,
  `order_dispute_id` int(11) NOT NULL,
  `description` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_dispute_helps`
--

DROP TABLE IF EXISTS `order_dispute_helps`;
CREATE TABLE IF NOT EXISTS `order_dispute_helps` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` enum('CANCELLED','COMPLAINED','REFUND','REASSIGN') COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_invoices`
--

DROP TABLE IF EXISTS `order_invoices`;
CREATE TABLE IF NOT EXISTS `order_invoices` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `paid` int(11) NOT NULL DEFAULT '0',
  `gross` double(10,2) NOT NULL DEFAULT '0.00',
  `discount` double(10,2) NOT NULL DEFAULT '0.00',
  `delivery_charge` double(10,2) NOT NULL DEFAULT '0.00',
  `wallet_amount` double(10,2) NOT NULL DEFAULT '0.00',
  `payable` int(11) NOT NULL DEFAULT '0',
  `tax` double(10,2) NOT NULL DEFAULT '0.00',
  `net` int(11) NOT NULL DEFAULT '0',
  `total_pay` double NOT NULL DEFAULT '0',
  `tender_pay` double NOT NULL DEFAULT '0',
  `ripple_price` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `payment_mode` enum('cash','stripe','razorpay','paypal','braintree','wallet','ripple','eather','bitcoin') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'cash',
  `payment_id` text COLLATE utf8mb4_unicode_ci,
  `status` enum('pending','processing','failed','success') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'pending',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `order_invoices`
--

INSERT INTO `order_invoices` (`id`, `order_id`, `quantity`, `paid`, `gross`, `discount`, `delivery_charge`, `wallet_amount`, `payable`, `tax`, `net`, `total_pay`, `tender_pay`, `ripple_price`, `payment_mode`, `payment_id`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
(22, 22, 1, 1, 100.00, 10.00, 20.00, 0.00, 110, 0.00, 110, 110, 0, '0', 'razorpay', 'pay_DfIXLw5taAJfB5', 'success', '2019-11-12 09:42:36', '2019-11-12 09:42:36', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `order_ratings`
--

DROP TABLE IF EXISTS `order_ratings`;
CREATE TABLE IF NOT EXISTS `order_ratings` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_rating` int(11) DEFAULT NULL,
  `user_comment` text COLLATE utf8mb4_unicode_ci,
  `transporter_id` int(11) DEFAULT NULL,
  `transporter_rating` int(11) DEFAULT NULL,
  `transporter_comment` text COLLATE utf8mb4_unicode_ci,
  `shop_id` int(11) DEFAULT NULL,
  `shop_rating` int(11) DEFAULT NULL,
  `shop_comment` text COLLATE utf8mb4_unicode_ci,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_timings`
--

DROP TABLE IF EXISTS `order_timings`;
CREATE TABLE IF NOT EXISTS `order_timings` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `status` enum('ORDERED','RECEIVED','CANCELLED','ASSIGNED','PROCESSING','REACHED','PICKEDUP','ARRIVED','COMPLETED','SEARCHING') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `order_timings`
--

INSERT INTO `order_timings` (`id`, `order_id`, `status`, `created_at`, `updated_at`) VALUES
(25, 22, 'ORDERED', '2019-11-12 09:42:36', '2019-11-12 09:42:36');

-- --------------------------------------------------------

--
-- Table structure for table `password_resets`
--

DROP TABLE IF EXISTS `password_resets`;
CREATE TABLE IF NOT EXISTS `password_resets` (
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  KEY `password_resets_email_index` (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `password_resets`
--

INSERT INTO `password_resets` (`email`, `token`, `created_at`) VALUES
('vishalrbt@gmail.com', '$2y$10$1GDwZxp522yzklEeMkoEuOFA6TXYH0tXzDhAdlfFkzBMXeWULYy1W', '2019-04-08 20:15:18'),
('kuldeepbhati0056@gmail.com', '$2y$10$kUaEJBFSuQqspzO17qO/dOPRVtjP8RPCiGJIzXNdug1Wyi5WDp1le', '2019-05-07 14:46:30'),
('laliteshchauhan94@gmail.com', '$2y$10$1zzenK/cQHy0bkV.9nwuo.i24rhK2PBFVqNiIa3PnpMUKFOsagzqq', '2019-05-08 21:00:04'),
('suraj69sb@gmail.com', '$2y$10$FySHpaYIxUt5s7p73H1sSu6KjvNgenzEDSR9TvJTcoHSRgNJmamnq', '2019-12-11 15:47:27');

-- --------------------------------------------------------

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
CREATE TABLE IF NOT EXISTS `permissions` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `guard_name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT '0',
  `shop_id` int(11) NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `position` int(11) DEFAULT NULL,
  `food_type` enum('veg','non-veg') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'veg',
  `avalability` tinyint(1) NOT NULL DEFAULT '0',
  `max_quantity` int(11) NOT NULL DEFAULT '10',
  `featured` int(11) NOT NULL DEFAULT '0',
  `addon_status` int(11) NOT NULL DEFAULT '0',
  `status` enum('enabled','disabled') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'enabled',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `nonveg` tinyint(1) DEFAULT '0',
  `half_price` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `parent_id`, `shop_id`, `name`, `description`, `position`, `food_type`, `avalability`, `max_quantity`, `featured`, `addon_status`, `status`, `created_at`, `updated_at`, `deleted_at`, `nonveg`, `half_price`) VALUES
(1, 0, 168, 'hara bhara kabab', 'Tasty meal', 1, 'veg', 0, 10, 0, 0, 'enabled', '2019-11-12 15:08:59', NULL, NULL, 0, NULL),
(2, 0, 167, 'samosa', '', 1, 'veg', 0, 10, 0, 0, 'enabled', '2019-11-12 15:34:42', NULL, NULL, 0, NULL),
(3, 0, 167, 'samosa', '-', 1, 'veg', 0, 10, 0, 0, 'enabled', '2019-11-12 15:35:09', NULL, NULL, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `product_images`
--

DROP TABLE IF EXISTS `product_images`;
CREATE TABLE IF NOT EXISTS `product_images` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `url` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `position` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `product_images`
--

INSERT INTO `product_images` (`id`, `product_id`, `url`, `position`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 1, 'https://khaadyam.com/kf/uploads/product/1573533556326.png', 0, '2019-11-12 15:09:23', NULL, NULL),
(2, 1, 'https://khaadyam.com/kf/uploads/product/1573533561231.png', 2, '2019-11-12 15:09:32', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `product_prices`
--

DROP TABLE IF EXISTS `product_prices`;
CREATE TABLE IF NOT EXISTS `product_prices` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `price` int(10) UNSIGNED NOT NULL,
  `currency` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `discount` int(10) UNSIGNED NOT NULL DEFAULT '0',
  `discount_type` enum('percentage','amount') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'percentage',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `product_prices`
--

INSERT INTO `product_prices` (`id`, `product_id`, `price`, `currency`, `discount`, `discount_type`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 1, 100, '₹', 0, 'percentage', '2019-11-12 15:08:59', NULL, NULL),
(2, 2, 20, '₹', 0, 'percentage', '2019-11-12 15:34:42', NULL, NULL),
(3, 3, 20, '₹', 0, 'percentage', '2019-11-12 15:35:09', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `promocodes`
--

DROP TABLE IF EXISTS `promocodes`;
CREATE TABLE IF NOT EXISTS `promocodes` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `promo_code` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `promocode_type` enum('amount','percent') COLLATE utf8mb4_unicode_ci NOT NULL,
  `discount` double(10,2) NOT NULL DEFAULT '0.00',
  `expiration` datetime NOT NULL,
  `status` enum('ADDED','EXPIRED') COLLATE utf8mb4_unicode_ci NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `promocodes`
--

INSERT INTO `promocodes` (`id`, `promo_code`, `promocode_type`, `discount`, `expiration`, `status`, `deleted_at`, `created_at`, `updated_at`) VALUES
(16, 'FREEMEAL', 'percent', 50.00, '2020-01-17 00:00:00', 'ADDED', NULL, '2019-11-14 10:12:46', '2019-11-14 10:12:46');

-- --------------------------------------------------------

--
-- Table structure for table `promocode_usages`
--

DROP TABLE IF EXISTS `promocode_usages`;
CREATE TABLE IF NOT EXISTS `promocode_usages` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `promocode_id` int(11) NOT NULL,
  `status` enum('ADDED','USED','EXPIRED') COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `promocode_usages`
--

INSERT INTO `promocode_usages` (`id`, `user_id`, `promocode_id`, `status`, `created_at`, `updated_at`) VALUES
(58, 8, 16, 'USED', '2019-11-15 08:10:39', '2019-11-15 08:10:39');

-- --------------------------------------------------------

--
-- Table structure for table `request_filters`
--

DROP TABLE IF EXISTS `request_filters`;
CREATE TABLE IF NOT EXISTS `request_filters` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `request_id` int(11) NOT NULL,
  `provider_id` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `restuarants`
--

DROP TABLE IF EXISTS `restuarants`;
CREATE TABLE IF NOT EXISTS `restuarants` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `hours_opening` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `hours_closing` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `restuarants_email_unique` (`email`),
  UNIQUE KEY `restuarants_phone_unique` (`phone`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `guard_name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`, `guard_name`, `created_at`, `updated_at`) VALUES
(1, 'Admin', 'admin', NULL, NULL),
(2, 'Dispute Manager', 'admin', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `role_has_permissions`
--

DROP TABLE IF EXISTS `role_has_permissions`;
CREATE TABLE IF NOT EXISTS `role_has_permissions` (
  `permission_id` int(10) UNSIGNED NOT NULL,
  `role_id` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`permission_id`,`role_id`),
  KEY `role_has_permissions_role_id_foreign` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `settings`
--

DROP TABLE IF EXISTS `settings`;
CREATE TABLE IF NOT EXISTS `settings` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `key` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `value` text COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `settings_key_index` (`key`)
) ENGINE=MyISAM AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `settings`
--

INSERT INTO `settings` (`id`, `key`, `value`) VALUES
(1, 'site_title', 'KHAADYAM'),
(2, 'site_logo', 'https://khaadyam.com/assets/user/img/logo.png'),
(3, 'site_favicon', 'https://khaadyam.com/assets/user/img/logo.png'),
(4, 'site_copyright', '© 2019 KHAADYAM - Home Food'),
(5, 'delivery_charge', '20'),
(6, 'resturant_response_time', '180'),
(7, 'currency', '₹'),
(8, 'currency_code', 'INR'),
(9, 'search_distance', '5'),
(10, 'tax', '0'),
(11, 'payment_mode', 'stripe'),
(12, 'manual_assign', '1'),
(13, 'transporter_response_time', '180'),
(14, 'GOOGLE_MAP_KEY', 'AIzaSyCY4CwTBUXVCz2-ZLfNGhpiz5cpWGe8wr4'),
(15, 'TWILIO_SID', 'no'),
(16, 'TWILIO_TOKEN', 'no'),
(17, 'TWILIO_FROM', 'no'),
(18, 'PUBNUB_PUB_KEY', 'no'),
(19, 'PUBNUB_SUB_KEY', 'no'),
(20, 'stripe_charge', '0'),
(21, 'stripe_publishable_key', 'pk_test_I8WYetvYjPq8g9c7lydm4iS6'),
(22, 'stripe_secret_key', 'sk_test_8Zh8nglPSAMvmuDoh4p3UX40'),
(23, 'FB_CLIENT_ID', '290984818086469'),
(24, 'FB_CLIENT_SECRET', '1f52cb4378e623bb819cd8469e408482'),
(25, 'FB_REDIRECT', 'http://localhost'),
(26, 'GOOGLE_CLIENT_ID', '319525834468-isvm9u70sdef8pq7dbbs92m90fvk3eqt.apps.googleusercontent.com'),
(27, 'GOOGLE_CLIENT_SECRET', 'FL0YR5dw9RuV6OdI8IkkI9oS'),
(28, 'GOOGLE_REDIRECT', 'http://localhost'),
(29, 'GOOGLE_API_KEY', 'AIzaSyCY4CwTBUXVCz2-ZLfNGhpiz5cpWGe8wr4'),
(30, 'ANDROID_ENV', 'development'),
(31, 'ANDROID_PUSH_KEY', 'AIzaSyBzvWOtvpuNXBKp6vxBBRMizNJj_1wIQVg'),
(32, 'IOS_USER_ENV', 'development'),
(33, 'IOS_PROVIDER_ENV', 'development'),
(34, 'DEMO_MODE', '0'),
(35, 'SUB_CATEGORY', '0'),
(36, 'SCHEDULE_ORDER', '0'),
(37, 'client_id', 'no'),
(38, 'client_secret', 'YnjutNczhk44fyeBk3tNGJlK1WCc8svIaLYCgs4O'),
(39, 'PRODUCT_ADDONS', '1'),
(40, 'BRAINTREE_ENV', 'sandbox'),
(41, 'BRAINTREE_MERCHANT_ID', 'twbd779hfc859jxq'),
(42, 'BRAINTREE_PUBLIC_KEY', '7bn6hystx7vs2g8r'),
(43, 'BRAINTREE_PRIVATE_KEY', '721e48cc74fdf2dfaacc6c3410de2f27'),
(44, 'RIPPLE_KEY', 'rEsaDShsYPmMZopoG3nNjutWJCk1Zn9cbX'),
(45, 'RIPPLE_BARCODE', 'http://localhost/images/ripple.png'),
(46, 'ETHER_ADMIN_KEY', '0x16abb22fd68c25286d72e77226ddaad87323cbb1'),
(47, 'ETHER_KEY', 'R92FW87ER1QZIDYX1UHTVBY625T8HCPKNR'),
(48, 'ETHER_BARCODE', 'http://localhost/images/ether.jpeg'),
(49, 'CLIENT_AUTHORIZATION', 'sandbox_v5r97hvk_twbd779hfc859jxq'),
(50, 'SOCIAL_FACEBOOK_LINK', 'http://facebook.com'),
(51, 'SOCIAL_TWITTER_LINK', 'http://twitter.com'),
(52, 'SOCIAL_G-PLUS_LINK', 'http://google.com'),
(53, 'SOCIAL_INSTAGRAM_LINK', 'http://instagram.com'),
(54, 'SOCIAL_PINTEREST_LINK', 'http://pinterest.com'),
(55, 'SOCIAL_VIMEO_LINK', 'http://vimeo.com'),
(56, 'SOCIAL_YOUTUBE_LINK', 'http://youtube.com'),
(57, 'COMMISION_OVER_FOOD', '5'),
(58, 'COMMISION_OVER_DELIVERY_FEE', '10'),
(59, 'APP_STORE_LINK', 'https://itunes.apple.com/us/app/foodie-express-food-delivery/id1296870125?mt=8'),
(60, 'GOOGLE_PLAY_LINK', 'https://play.google.com/store/apps/details?id=com.foodie.app&hl=en'),
(61, 'contact', '<p>8887998502</p>'),
(62, 'privacy', '<p><br />\r\nThis Privacy Policy (&ldquo;<strong>Policy</strong>&rdquo;) describes the policies and procedures on the collection, use, disclosure and protection of your information when you use our website located at, or the khaadyam mobile application (collectively, &ldquo;<strong>khaadyam Platform</strong>&rdquo;) made available by (&ldquo;<strong>khaadyam</strong>&rdquo;, &ldquo;<strong>Company</strong>&rdquo;, &ldquo;<strong>we</strong>&rdquo;, &ldquo;<strong>us</strong>&rdquo; and &ldquo;<strong>our</strong>&rdquo;), a food delivery company</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>The terms &ldquo;you&rdquo; and &ldquo;your&rdquo; refer to the user of the khaadyam Platform. The term &ldquo;<strong>Services</strong>&rdquo; refers to any services offered by khaadyam whether on the khaadyam Platform or otherwise.</p>\r\n\r\n<p>Please read this Policy before using the khaadyam Platform or submitting any personal information to khaadyam. This Policy is a part of and incorporated within, and is to be read along with, the Terms of Use.</p>\r\n\r\n<p><strong>YOUR CONSENT</strong></p>\r\n\r\n<p>By using the khaadyam Platform and the Services, you agree and consent to the collection, transfer, use, storage, disclosure and sharing of your information as described and collected by us in accordance with this Policy. If you do not agree with the Policy, please do not use or access the khaadyam Platform.</p>\r\n\r\n<p><strong>POLICY CHANGES</strong></p>\r\n\r\n<p>We may occasionally update this Policy and such changes will be posted on this page. If we make any significant changes to this Policy we will endeavor to provide you with reasonable notice of such changes, such as via prominent notice on the khaadyam Platform or to your email address on record and where required by applicable law, we will obtain your consent. To the extent permitted under the applicable law, your continued use of our Services after we publish or send a notice about our changes to this Policy shall constitute your consent to the updated Policy.</p>\r\n\r\n<p><strong>LINKS TO OTHER WEBSITES</strong></p>\r\n\r\n<p>The khaadyam Platform may contain links to other websites. Any personal information about you collected whilst visiting such websites is not governed by this Policy. khaadyam shall not be responsible for and has no control over the practices and content of any website accessed using the links contained on the khaadyam Platform. This Policy shall not apply to any information you may disclose to any of our service providers/service personnel which we do not require you to disclose to us or any of our service providers under this Policy.</p>\r\n\r\n<p><strong>INFORMATION WE COLLECT FROM YOU</strong></p>\r\n\r\n<p>We will collect and process the following information about you:</p>\r\n\r\n<ul>\r\n	<li><strong>Information you give us - </strong>This includes information submitted when you:</li>\r\n</ul>\r\n\r\n<ol>\r\n	<li>Create or update your khaadyam account, which may include your name, email, phone number, login name and password, address, payment or banking information, date of birth and profile picture. If you sign in to the khaadyam Platform through third-party sign-in services such as Facebook, Google Plus or Gmail or any other social networking or similar site (collectively, &ldquo;SNS&rdquo;), an option of which may be provided to you by khaadyam at its sole discretion, you will be allowing us to pass through and receive from the SNS your log-in information and other user data; or</li>\r\n	<li>Provide content to us, which may include reviews, ordering details and history, favorite vendors, special merchant requests, contact information of people you refer to us and other information you provide on the khaadyam Platform (&ldquo;<strong>Your Content</strong>&rdquo;).</li>\r\n	<li>Use our Services, we may collect and store information about you to process your requests and automatically complete forms for future transactions, including (but not limited to) your phone number, address, email, billing information and credit or payment card information.</li>\r\n	<li>Correspond with khaadyam for customer support;</li>\r\n	<li>Participate in the interactive services offered by the khaadyam Platform such as discussion boards, competitions, promotions or surveys, other social media functions or make payments etc., or</li>\r\n	<li>Enable features that require khaadyam access to your address book or calendar;</li>\r\n	<li>Report problems for troubleshooting.</li>\r\n	<li>If you sign up to use our Services as a merchant or a delivery partner, we may collect location details, copies of government identification documents and other details (KYC), call and SMS details.</li>\r\n</ol>\r\n\r\n<ul>\r\n	<li><strong>Information we collect about you - </strong>With regard to each of your visits to the khaadyam Platform, we will automatically collect and analyze the following demographic and other information:</li>\r\n</ul>\r\n\r\n<ol>\r\n	<li>When you communicate with us (via email, phone, through the khaadyam Platform or otherwise), we may maintain a record of your communication;</li>\r\n	<li><strong>Location information</strong>: Depending on the Services that you use, and your app settings or device permissions, we may collect your real time information, or approximate location information as determined through data such as GPS, IP address;</li>\r\n	<li><strong>Usage and Preference Information</strong>: We collect information as to how you interact with our Services, preferences expressed and settings chosen. khaadyam Platform includes the khaadyam advertising services (&ldquo;Ad Services&rdquo;), which may collect user activity and browsing history within the khaadyam Platform and across third-party sites and online services, including those sites and services that include our ad pixels (&ldquo;Pixels&rdquo;), widgets, plug-ins, buttons, or related services or through the use of cookies. Our Ad Services collect browsing information including without limitation your Internet protocol (IP) address and location, your login information, browser type and version, date and time stamp, user agent, khaadyam cookie ID (if applicable), time zone setting, browser plug-in types and versions, operating system and platform, and other information about user activities on the khaadyam Platform, as well as on third party sites and services that have embedded our Pixels, widgets, plug-ins, buttons, or related services;</li>\r\n	<li><strong>Transaction Information</strong>: We collect transaction details related to your use of our Services, and information about your activity on the Services, including the full Uniform Resource Locators (URL), the type of Services you requested or provided, comments, domain names, search results selected, number of clicks, information and pages viewed and searched for, the order of those pages, length of your visit to our Services, the date and time you used the Services, amount charged, details regarding application of promotional code, methods used to browse away from the page and any phone number used to call our customer service number and other related transaction details;</li>\r\n	<li><strong>Device Information</strong>: We may collect information about the devices you use to access our Services, including the hardware models, operating systems and versions, software, file names and versions, preferred languages, unique device identifiers, advertising identifiers, serial numbers, device motion information and mobile network information. Analytics companies may use mobile device IDs to track your usage of the khaadyam Platform;</li>\r\n	<li><strong>Stored information and files</strong>: khaadyam mobile application (khaadyam app) may also access metadata and other information associated with other files stored on your mobile device. This may include, for example, photographs, audio and video clips, personal contacts and address book information. If you permit the khaadyam app to access the address book on your device, we may collect names and contact information from your address book to facilitate social interactions through our services and for other purposes described in this Policy or at the time of consent or collection. If you permit the khaadyam app to access the calendar on your device, we collect calendar information such as event title and description, your response (Yes, No, Maybe), date and time, location and number of attendees.</li>\r\n	<li>If you are a partner chef, merchant or a delivery partner, we will, additionally, record your calls with us made from the device used to provide Services, related call details, SMS details location and address details.</li>\r\n</ol>\r\n\r\n<ul>\r\n	<li><strong>Information we receive from other sources -</strong></li>\r\n</ul>\r\n\r\n<ol>\r\n	<li>We may receive information about you from third parties, such as other users, partners (including ad partners, analytics providers, search information providers), or our affiliated companies or if you use any of the other websites/apps we operate or the other Services we provide. Users of our Ad Services and other third-parties may share information with us such as the cookie ID, device ID, or demographic or interest data, and information about content viewed or actions taken on a third-party website, online services or apps. For example, users of our Ad Services may also be able to share customer list information (e.g., email or phone number) with us to create customized audience segments for their ad campaigns.</li>\r\n	<li>When you sign in to khaadyam Platform with your SNS account, or otherwise connect to your SNS account with the Services, you consent to our collection, storage, and use, in accordance with this Policy, of the information that you make available to us through the social media interface. This could include, without limitation, any information that you have made public through your social media account, information that the social media service shares with us, or information that is disclosed during the sign-in process. Please see your social media provider&rsquo;s privacy policy and help center for more information about how they share information when you choose to connect your account.</li>\r\n	<li>If you are partner restaurant, merchant or a delivery partner, we may, additionally, receive feedback and rating from other users.</li>\r\n</ol>\r\n\r\n<p><strong>COOKIES</strong></p>\r\n\r\n<p>Our khaadyam Platform and third parties with whom we partner, may use cookies, pixel tags, web beacons, mobile device IDs, &ldquo;flash cookies&rdquo; and similar files or technologies to collect and store information with respect to your use of the Services and third-party websites.</p>\r\n\r\n<p>Cookies are small files that are stored on your browser or device by websites, apps, online media and advertisements. We use cookies and similar technologies for purposes such as:</p>\r\n\r\n<ul>\r\n	<li>Authenticating users;</li>\r\n	<li>Remembering user preferences and settings;</li>\r\n	<li>Determining the popularity of content;</li>\r\n	<li>Delivering and measuring the effectiveness of advertising campaigns;</li>\r\n	<li>Analysing site traffic and trends, and generally understanding the online behaviors and interests of people who interact with our services.</li>\r\n</ul>\r\n\r\n<p>A pixel tag (also called a web beacon or clear GIF) is a tiny graphic with a unique identifier, embedded invisibly on a webpage (or an online ad or email), and is used to count or track things like activity on a webpage or ad impressions or clicks, as well as to access cookies stored on users&rsquo; computers. We use pixel tags to measure the popularity of our various pages, features and services. We also may include web beacons in e-mail messages or newsletters to determine whether the message has been opened and for other analytics.</p>\r\n\r\n<p>To modify your cookie settings, please visit your browser&rsquo;s settings. By using our Services with your browser settings to accept cookies, you are consenting to our use of cookies in the manner described in this section.</p>\r\n\r\n<p>We may also allow third parties to provide audience measurement and analytics services for us, to serve advertisements on our behalf across the Internet, and to track and report on the performance of those advertisements. These entities may use cookies, web beacons, SDKs and other technologies to identify your device when you visit the khaadyam Platform and use our Services, as well as when you visit other online sites and services.</p>\r\n\r\n<p>Please see our <a href=\"https://swiggy.com/cookie-policy\" target=\"_blank\">Cookie Policy</a> for more information regarding the use of cookies and other technologies described in this section, including regarding your choices relating to such technologies.</p>\r\n\r\n<p><strong>USES OF YOUR INFORMATION</strong></p>\r\n\r\n<ul>\r\n	<li>We use the information we collect for following purposes, including:</li>\r\n</ul>\r\n\r\n<ol>\r\n	<li>To provide, personalise, maintain and improve our products and services, such as to enable deliveries and other services, enable features to personalise your khaadyam account;</li>\r\n	<li>To carry out our obligations arising from any contracts entered into between you and us and to provide you with the relevant information and services;</li>\r\n	<li>To administer and enhance the security of our khaadyam Platform and for internal operations, including troubleshooting, data analysis, testing, research, statistical and survey purposes;</li>\r\n	<li>To provide you with information about services we consider similar to those that you are already using, or have enquired about, or may interest you. If you are a registered user, we will contact you by electronic means (e-mail or SMS or telephone) with information about these services;</li>\r\n	<li>To understand our users (what they do on our Services, what features they like, how they use them, etc.), improve the content and features of our Services (such as by personalizing content to your interests), process and complete your transactions, make special offers, provide customer support, process and respond to your queries;</li>\r\n	<li>To generate and review reports and data about, and to conduct research on, our user base and Service usage patterns;</li>\r\n	<li>To allow you to participate in interactive features of our Services, if any; or</li>\r\n	<li>To measure or understand the effectiveness of advertising we serve to you and others, and to deliver relevant advertising to you.</li>\r\n	<li>If you are a partner restaurant or merchant or delivery partner, to track the progress of delivery or status of the order placed by our customers.</li>\r\n</ol>\r\n\r\n<p>We may combine the information that we receive from third parties with the information you give to us and information we collect about you for the purposes set out above. Further, we may anonymize and/or de-identify information collected from you through the Services or via other means, including via the use of third-party web analytic tools. As a result, our use and disclosure of aggregated and/or de-identified information is not restricted by this Policy, and it may be used and disclosed to others without limitation.</p>\r\n\r\n<p>We analyse the log files of our khaadyam Platform that may contain Internet Protocol (IP) addresses, browser type and language, Internet service provider (ISP), referring, app crashes, page viewed and exit websites and applications, operating system, date/time stamp, and clickstream data. This helps us to administer the website, to learn about user behavior on the site, to improve our product and services, and to gather demographic information about our user base as a whole.</p>\r\n\r\n<p><strong>DISCLOSURE AND DISTRIBUTION OF YOUR INFORMATION</strong></p>\r\n\r\n<p>We may share your information that we collect for following purposes:</p>\r\n\r\n<ul style=\"list-style-type:square\">\r\n	<li><strong>With Service Providers: </strong>We may share your information with our vendors, consultants, marketing partners, research firms and other service providers or business partners, such as Payment processing companies, to support our business. For example, your information may be shared with outside vendors to send you emails and messages or push notifications to your devices in relation to our Services, to help us analyze and improve the use of our Services, to process and collect payments. We also may use vendors for other projects, such as conducting surveys or organizing sweepstakes for us.</li>\r\n	<li><strong>With Partner chef/Merchant:</strong> While you place a request to order food through the khaadyam Platform, your information is provided to us and to the restaurants/merchants with whom you may choose to order. In order to facilitate your online food order processing, we provide your information to that restaurant/merchant in a similar manner as if you had made a food order directly with the restaurant. If you provide a mobile phone number, khaadyam may send you text messages regarding the order&rsquo;s delivery status.</li>\r\n	<li><strong>With Other Users: </strong>If you are a delivery partner, we may share your name, phone number and/or profile picture (if applicable), tracking details with other users to provide them the Services.</li>\r\n	<li><strong>For Crime Prevention or Investigation: </strong>We may share this information with governmental agencies or other companies assisting us, when we are:\r\n	<ul style=\"list-style-type:square\">\r\n		<li>Obligated under the applicable laws or in good faith to respond to court orders and processes; or</li>\r\n		<li>Detecting and preventing against actual or potential occurrence of identity theft, fraud, abuse of Services and other illegal acts;</li>\r\n		<li>Responding to claims that an advertisement, posting or other content violates the intellectual property rights of a third party;</li>\r\n		<li>Under a duty to disclose or share your personal data in order to enforce our Terms of Use and other agreements, policies or to protect the rights, property, or safety of the Company, our customers, or others, or in the event of a claim or dispute relating to your use of our Services. This includes exchanging information with other companies and organizations for the purposes of fraud detection and credit risk reduction.</li>\r\n	</ul>\r\n	</li>\r\n	<li><strong>For Internal Use: </strong>We may share your information with any present or future member of our &ldquo;Group&rdquo; (as defined below)or affiliates for our internal business purposes The term &ldquo;Group&rdquo; means, with respect to any person, any entity that is controlled by such person, or any entity that controls such person, or any entity that is under common control with such person, whether directly or indirectly, or, in the case of a natural person, any Relative (as such term is defined in the Companies Act, 1956 and Companies Act, 2013 to the extent applicable) of such person.</li>\r\n	<li>To fulfill the purpose for which you provide it.</li>\r\n	<li>We may share your information other than as described in this Policy if we notify you and you consent to the sharing.</li>\r\n</ul>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><strong>DATA SECURITY PRECAUTIONS</strong></p>\r\n\r\n<p>We have in place appropriate technical and security measures to secure the information collected by us.</p>\r\n\r\n<p>We use vault and tokenization services from third party service providers to protect the sensitive personal information provided by you. The third-party service providers with respect to our vault and tokenization services and our payment gateway and payment processing are compliant with the payment card industry standard (generally referred to as PCI compliant service providers). You are advised not to send your full credit/debit card details through unencrypted electronic platforms. Where we have given you (or where you have chosen) a username and password which enables you to access certain parts of the khaadyam Platform, you are responsible for keeping these details confidential. We ask you not to share your password with anyone.</p>\r\n\r\n<p>Please we aware that the transmission of information via the internet is not completely secure. Although we will do our best to protect your personal data, we cannot guarantee the security of your data transmitted through the khaadyam Platform. Once we have received your information, we will use strict physical, electronic, and procedural safeguards to try to prevent unauthorized access.</p>\r\n\r\n<p><strong>OPT-OUT</strong></p>\r\n\r\n<p>When you sign up for an account, you are opting in to receive emails from khaadyam. You can log in to manage your email preferences [<em>here</em>] or you can follow the &ldquo;unsubscribe&rdquo; instructions in commercial email messages, but note that you cannot opt out of receiving certain administrative notices, service notices, or legal notices from khaadyam.</p>\r\n\r\n<p>If you wish to withdraw your consent for the use and disclosure of your personal information in the manner provided in this Policy, please write to us at [&bull;]. Please note that we may take time to process such requests, and your request shall take effect no later than 5 (Five) business days from the receipt of such request, after which we will not use your personal data for any processing unless required by us to comply with our legal obligations.</p>');

-- --------------------------------------------------------

--
-- Table structure for table `shops`
--

DROP TABLE IF EXISTS `shops`;
CREATE TABLE IF NOT EXISTS `shops` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `avatar` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `default_banner` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `offer_min_amount` double(20,2) NOT NULL DEFAULT '0.00',
  `offer_percent` int(11) DEFAULT '0',
  `estimated_delivery_time` int(11) DEFAULT NULL,
  `address` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `maps_address` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `latitude` double(15,8) NOT NULL,
  `longitude` double(15,8) NOT NULL,
  `pure_veg` tinyint(1) NOT NULL DEFAULT '0',
  `rating` int(11) DEFAULT '0',
  `rating_status` int(11) NOT NULL DEFAULT '0',
  `status` enum('onboarding','banned','active') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'onboarding',
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `phone2` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `shops_email_unique` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=169 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `shops`
--

INSERT INTO `shops` (`id`, `name`, `email`, `password`, `phone`, `avatar`, `default_banner`, `description`, `offer_min_amount`, `offer_percent`, `estimated_delivery_time`, `address`, `maps_address`, `latitude`, `longitude`, `pure_veg`, `rating`, `rating_status`, `status`, `remember_token`, `created_at`, `updated_at`, `deleted_at`, `phone2`) VALUES
(167, 'Sukh Sagar', 'suraj69sb@gmail.com-5df08837a148c', '$2y$10$i4S0kRdcCZ4i7vfLnLHRReDlsp73Dxw8SLrwjfybIfhDNh3PK0itq', '+918788070324', 'https://khaadyam.com/kf/uploads/shop/1573535740887.png', 'https://khaadyam.com/kf/uploads/shop/1573535816427.png', 'Test', 20.00, 10, 60, 'MH MSH 6, Patvipura, Amravati, Maharashtra 444601, India', 'MH MSH 6, Patvipura, Amravati, Maharashtra 444601, India', 20.92605991, 77.75443241, 0, 5, 1, 'onboarding', NULL, '2019-11-09 21:55:03', '2019-12-11 06:09:59', '2019-12-11 06:09:59', '8788070324'),
(168, 'KHAADHYAM', 'prasadjewade16@gmail.com', '$2y$10$OLBZF425tb0lVEYWTxhG8eE2xPNOWcDj0rYXrBPDpkGNI1NFH2nf6', '9359192553', 'http://khaadhyamfoods.achintyaenterprises.com/kf/uploads/shop/1573751277659.png', NULL, 'Healthy food on the way', 99.00, 10, 45, 'Kokane Chowk, Pimpri-Chinchwad, Maharashtra, India', 'Kokane Chowk, Pimpri-Chinchwad, Maharashtra, India', 18.59734600, 73.79050000, 1, 5, 0, 'active', NULL, '2019-11-09 21:58:29', '2020-02-03 00:55:47', NULL, '7720803048');

-- --------------------------------------------------------

--
-- Table structure for table `shop_banners`
--

DROP TABLE IF EXISTS `shop_banners`;
CREATE TABLE IF NOT EXISTS `shop_banners` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL DEFAULT '0',
  `url` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `position` int(11) NOT NULL DEFAULT '0',
  `status` enum('active','inactive') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'inactive',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `shop_password_resets`
--

DROP TABLE IF EXISTS `shop_password_resets`;
CREATE TABLE IF NOT EXISTS `shop_password_resets` (
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  KEY `shop_password_resets_email_index` (`email`),
  KEY `shop_password_resets_token_index` (`token`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `shop_password_resets`
--

INSERT INTO `shop_password_resets` (`email`, `token`, `created_at`) VALUES
('kuldeepbhati0056@gmail.com', '$2y$10$6h1Wpg6JKsrK/WGGu/bEGu.HnLwhw2itGEmQipqwVyT1/NDB7Zr7W', '2019-04-17 20:00:46');

-- --------------------------------------------------------

--
-- Table structure for table `shop_timings`
--

DROP TABLE IF EXISTS `shop_timings`;
CREATE TABLE IF NOT EXISTS `shop_timings` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) NOT NULL,
  `start_time` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `end_time` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `day` enum('ALL','SUN','MON','TUE','WED','THU','FRI','SAT') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'ALL',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `shop_timings`
--

INSERT INTO `shop_timings` (`id`, `shop_id`, `start_time`, `end_time`, `day`, `created_at`, `updated_at`) VALUES
(4, 1, '23:00', '11:00', 'ALL', NULL, NULL),
(5, 2, '12:00', '08:00', 'ALL', NULL, NULL),
(10, 3, '11:00', '23:38', 'ALL', NULL, NULL),
(14, 4, '09:00', '22:00', 'ALL', NULL, NULL),
(20, 167, '09:00', '12:00', 'ALL', NULL, NULL),
(19, 168, '09:00', '23:00', 'ALL', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `slider`
--

DROP TABLE IF EXISTS `slider`;
CREATE TABLE IF NOT EXISTS `slider` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `slider`
--

INSERT INTO `slider` (`id`, `image`) VALUES
(19, 'http://khaadhyamfoods.achintyaenterprises.com/kf/uploads/slider/Mouth_Licking_Home_Food.png'),
(11, 'http://khaadhyamfoods.achintyaenterprises.com/kf/uploads/slider/by_KHAADHYAM.png');

-- --------------------------------------------------------

--
-- Table structure for table `surge_pricings`
--

DROP TABLE IF EXISTS `surge_pricings`;
CREATE TABLE IF NOT EXISTS `surge_pricings` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transporters`
--

DROP TABLE IF EXISTS `transporters`;
CREATE TABLE IF NOT EXISTS `transporters` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `avatar` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` text COLLATE utf8mb4_unicode_ci,
  `latitude` double(15,8) DEFAULT '0.00000000',
  `longitude` double(15,8) DEFAULT '0.00000000',
  `otp` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0',
  `rating` int(11) NOT NULL DEFAULT '5',
  `device_token` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `device_id` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '0',
  `device_type` enum('android','ios') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` enum('assessing','banned','online','offline','riding','unsettled') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'assessing',
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `transporters_email_unique` (`email`),
  UNIQUE KEY `transporters_phone_unique` (`phone`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `transporters`
--

INSERT INTO `transporters` (`id`, `name`, `email`, `phone`, `password`, `avatar`, `address`, `latitude`, `longitude`, `otp`, `rating`, `device_token`, `device_id`, `is_active`, `device_type`, `status`, `remember_token`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'prasad jewade', 'prasadjewade16@gmail.com', '7720803048', '$2y$10$stZSFT26DRrsZikw/Ex6nejvjYe/iiGOd0t9WlyUarRrldZdAMWDq', 'IMG20191027174429.jpg', 'Divya Heights, Arvind Colony, Pimple Saudagar, Pune, Maharashtra, India', 18.60125430, 73.79420800, '0', 5, NULL, NULL, 1, NULL, 'offline', NULL, '2019-11-02 13:06:16', '2019-11-02 13:13:30', NULL),
(2, 'yash', 'yash@frantic.in', '918887998502', '$2y$10$v.TMtpTHidkTFjqFZxJajeyQkndW2xtrE80WWSBPfixgft7GrJaw6', 'photo.jpg', 'Jd&Jd UNISEX SALON AND ACCADEMY, Arya Samaj Road, Dwarka Puri, B Block, Ellenabad, Sirsa, Haryana, India', 29.53383610, 75.03182300, '0', 5, NULL, NULL, 0, NULL, 'assessing', NULL, '2019-11-02 17:02:53', '2019-11-02 17:02:53', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `transporter_documents`
--

DROP TABLE IF EXISTS `transporter_documents`;
CREATE TABLE IF NOT EXISTS `transporter_documents` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `transporter_id` int(11) NOT NULL,
  `document_id` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `url` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `unique_id` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` enum('ASSESSING','ACTIVE') COLLATE utf8mb4_unicode_ci NOT NULL,
  `expires_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transporter_locations`
--

DROP TABLE IF EXISTS `transporter_locations`;
CREATE TABLE IF NOT EXISTS `transporter_locations` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `transporter_id` int(11) NOT NULL,
  `address` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `latitude` double(15,8) NOT NULL,
  `longitude` double(15,8) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transporter_password_resets`
--

DROP TABLE IF EXISTS `transporter_password_resets`;
CREATE TABLE IF NOT EXISTS `transporter_password_resets` (
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  KEY `transporter_password_resets_email_index` (`email`),
  KEY `transporter_password_resets_token_index` (`token`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transporter_shifts`
--

DROP TABLE IF EXISTS `transporter_shifts`;
CREATE TABLE IF NOT EXISTS `transporter_shifts` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `transporter_id` int(11) NOT NULL,
  `transporter_vehicle_id` int(11) NOT NULL,
  `total_order` int(11) NOT NULL DEFAULT '0',
  `start_time` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `end_time` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transporter_shift_timings`
--

DROP TABLE IF EXISTS `transporter_shift_timings`;
CREATE TABLE IF NOT EXISTS `transporter_shift_timings` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `transporter_shift_id` int(11) NOT NULL,
  `start_time` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `end_time` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `order_count` int(11) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transporter_vehicles`
--

DROP TABLE IF EXISTS `transporter_vehicles`;
CREATE TABLE IF NOT EXISTS `transporter_vehicles` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `transporter_id` int(11) NOT NULL,
  `vehicle_no` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `avatar` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `device_token` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `device_id` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `device_type` enum('android','ios') COLLATE utf8mb4_unicode_ci NOT NULL,
  `login_by` enum('manual','facebook','google') COLLATE utf8mb4_unicode_ci NOT NULL,
  `social_unique_id` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `stripe_cust_id` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `wallet_balance` double(8,2) NOT NULL DEFAULT '0.00',
  `otp` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0',
  `braintree_id` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_email_unique` (`email`),
  UNIQUE KEY `users_phone_unique` (`phone`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `phone`, `avatar`, `password`, `device_token`, `device_id`, `device_type`, `login_by`, `social_unique_id`, `stripe_cust_id`, `wallet_balance`, `otp`, `braintree_id`, `remember_token`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'Prasad Jewade', 'prasadjewade16@gmail.com', '+919359192553', NULL, '$2y$10$EypSHfbBsUZ1OL3GjNh1SOm0R65lYjhA7XbKrSr6VuILXBozWQamm', 'f1eGqvAhUPg:APA91bGHJ1Mcw6qPUaTFzRB9ZY7iQxBpgBu2UyLPuESPpkaFdXzvZX6TdYwuZazf8ICDN1Y3hOmgqAe8R23utHcWRvGYNjSiclUoYsxHkG8xQwH6CPNjbiSdAylqgfhy-Kb4Rpm1bVAi', 'b01c12a44027bd38', 'android', 'manual', '', NULL, 0.00, '0', NULL, NULL, '2019-10-19 23:45:05', '2019-11-09 22:05:37', NULL),
(2, 'Nitin', 'theevilnitin@gmail.com', '+917042254089', NULL, '$2y$10$nOEzzvT5eEc9fazbyLCz1OCXFw1wqWKfpxmxqFDwIfVX/rgqTVXvS', 'e6un8uPBuzM:APA91bG5O9SDvW4PnKmQoHWhzIagQPU1-_XqzskhvXH965LVA-Dysqk6_Ch3vpz0k-yge0dchR5H4_nRKNviEPKU1Qt8oMyGd6tILSUHxYzM8gJ7226H-4GLjirEcI68cb-yP4Hr7I_c', '7b69ed6ed8ba28c6', 'android', 'manual', '', NULL, 0.00, '0', NULL, NULL, '2019-10-20 20:07:24', '2019-11-02 19:41:33', NULL),
(3, 'abc', 'playstationcnx@gmail.com', '+919108508869', NULL, '$2y$10$yKiPIfkoYAU4.6MrntXlEuitAm.nZ7.oxE7BBrB/mRKMWTPGTGBUK', 'duqql8vHXu4:APA91bELTNAKgMLVqOU9JBct1X1JwXuLdULGUQ0iqy2PZlI0EqmDThSDeh6EKkDhMaQa2Zv6-VHaSQ9BXtYUSFjPOqNnvu2PJpmoBLa46FbEiVKI-sAEY5NCRsak31jhF0peNaCuqHuc', '6f135f0dba0be798', 'android', 'manual', '', NULL, 0.00, '0', NULL, NULL, '2019-10-21 07:22:52', '2019-10-21 07:22:55', NULL),
(4, 'Google', 'playstorecnx4@gmail.com', '+916366356700', NULL, '$2y$10$loPojvKcsWqzp/SIQS89IO0gQWxmMkYuU3B90cZJMoHmOm3HMQFA6', 'eM5Cj1ZJY2g:APA91bF8Q_zusCyVgQPioLEujCFdH3ITX9Mu7c9CtoLOZ0elcayXPIOO15UduvzwXu-yJhWANa4RVel0AdGkXUENL2LftDIqDmet1uTh3rnuBAXLCBD1uW2yIqYFMnC00xmO_gFiUs9J', 'f06a4d2a16109b3d', 'android', 'manual', '', NULL, 0.00, '0', NULL, NULL, '2019-10-23 17:09:40', '2019-10-23 17:09:44', NULL),
(5, 'Nikhilesh Mandal', 'nikhileshmandal12@gmail.com', '+917020038436', NULL, '$2y$10$kVCq9EUgY8ItK8aCfsu3jessdXKceK33h2c3CPFt3Y7ZMm5UDttaq', 'eY1l3sorFCg:APA91bGFX1feZuSm99VGYi8lMzKORz0q4C9AZACJ5Ne436ytjGx_5GaGqpNlhgJ9UOBRtOZBJj_90WxasxMmlQoGhKPfSR8UaouHFmApzZz0soA5hfsZ_6TxQBExygXVDVjPix8xWUut', '3ca917cabf379aea', 'android', 'manual', '', NULL, 0.00, '0', NULL, NULL, '2019-11-04 10:48:44', '2019-11-04 10:48:47', NULL),
(6, 'Naresh', 'nareshnikam999@gmail.com', '+919511263267', NULL, '$2y$10$nQE1tcO.CrXi5bLLtNFQKeZ22lGdciTk8fWgyTdPlgS6lREzVy8gK', 'fkd_oVTOUFI:APA91bExUQ7vpY4ZsLuQqlt0W3USewedHupn2KQEow7OUnbgYo0H75QFw_LjS_335gd1MaDjslAg_U84MA6iaj7hUuP_U1zVOaJj0lGC3LNcliI0sxkxpI2ZDKpNvkYbwywgdj-6YVyI', '61eedee7817de1cb', 'android', 'manual', '', NULL, 0.00, '0', NULL, NULL, '2019-11-06 08:16:24', '2019-11-06 08:16:26', NULL),
(7, 'Test User', 'prabhakarkalbhile.7@gmail.com', '+917798299384', NULL, '$2y$10$zc9IcR1PJZRR4QdgtzJN4OyNV1zSny3wWZNXRcnsMytwDC/2bavWi', 'fLjC-7UXc4E:APA91bFC-_dHvHEwL6ekb7ymawWezs49yZ5qs10XUdSWWIxsaaLA9X_FGrXz-zQ0Yy9jTh7NR9yC063J4aG19y6CxlipKZEmTUjyMQLAEMmo8vAxHxZWr1mv-meVe2Q6vb7SGbcT5hGP', '5edc1973c90f568c', 'android', 'manual', '', NULL, 0.00, '0', NULL, NULL, '2019-11-06 17:10:44', '2019-11-06 17:10:49', NULL),
(8, 'Suraj', 'suraj69sb@gmail.com', '+918149305561', 'https://khaadyam.com/storage/user/profile/xG3ShTmn6t0hqrEy6aZfp7oK6uUO83JT9CaMJcrx.png', '$2y$10$XsNxrA6JmcI47unJovtL1ObrAWxo8.rvbRBlYnzXMqllMSmem9Ptm', 'fdAy47jNzpE:APA91bGUxASHqErtkDDA4hdxbgvBQU8g-a_X-68HwBsoliBoPm88Mxrqi9W2sRAxEMB8v9jMY6aC5LcnU2lBpJZvmrKdjLTfNcH9KpeW7OMVeQDyix_n1uvgJRBmNn6MxsCtPZbdxnlo', '10025b92ec2f780b', 'android', 'manual', '', NULL, 0.00, '0', NULL, NULL, '2019-11-07 20:56:48', '2019-11-15 09:52:40', NULL),
(9, 'Playstore Cnx', 'playstorecnx13@gmail.com', '+919606970074', NULL, '$2y$10$6JCPBJNo1.SJddIUG2qkVuotmUt.OlNXjB1DkeQ1DMrUFSLJi7/F2', 'd6ZkIlwrv5I:APA91bHiNEUG9j6m0ag5rIfrzf2K_atNiycZByjQ3lLbxP54trnUcsiL9lKEzTfW03GC8bGWSadliWjGfWDGUPjcp-41Pei--921LLt6wY8WYRqtb5BRzd5cVGJm6RVdU1SX4lu8wQYM', '6e34fcd07a6bba47', 'android', 'manual', '', NULL, 0.00, '0', NULL, NULL, '2019-11-07 23:04:47', '2019-11-07 23:04:48', NULL),
(11, 'satish', 'india4321.sd@gmail.com', '9552655234', NULL, '$2y$10$OOOj.Wpf84MIp8lD4opNNO/MJkYcgIttNQLB99nQznUip5kKOfp3e', NULL, NULL, 'android', 'manual', NULL, NULL, 0.00, '0', NULL, 'oFTtjWWSOVkmqi8iCI6hCBp6EZUZYTeBIF0AuQXE25xq3CMZNmyafFKxMNH8', '2019-11-15 20:31:41', '2019-11-15 20:31:41', NULL),
(12, 'Shivam', 'shivam.more24@gmail.com', '919764098573', NULL, '$2y$10$UqZC9GXRIMjAbCGxOpcHN.FiikhND7KZi5kLQNlrPwNI4cJ49zXFy', NULL, NULL, 'android', 'manual', NULL, NULL, 0.00, '0', NULL, NULL, '2019-11-28 21:22:42', '2019-11-28 21:22:42', NULL),
(13, 'shubham thakare', 'shubhamrthakare@gmail.com', 'India8668431507', NULL, '$2y$10$tHVq2NseYgBHQl4e65TNOelB57kOZr4ipWm1SJKIaeaJAnZVVwBey', NULL, NULL, 'android', 'manual', NULL, NULL, 0.00, '0', NULL, NULL, '2019-12-08 14:52:49', '2019-12-08 14:52:49', NULL),
(14, 'Prakash bhoi', 'bhoi.prakash@gmail.com', '9820793948', NULL, '$2y$10$x0S5A15S.y8qsRfULwpbpuZoazi9SEIeja9CayPca76CYt8YQ7MPS', NULL, NULL, 'android', 'manual', NULL, NULL, 0.00, '0', NULL, NULL, '2019-12-10 15:01:43', '2019-12-10 15:01:43', NULL),
(15, 'Shreya Kadapa', 'shreyakadapa49@gmail.com', '+919975527518', NULL, '$2y$10$OkG.6tgot6dTu0J8RpHDmO6GhUsB9r/yFlqwtuV36Hm.nA2m5CXpy', NULL, NULL, 'android', 'manual', NULL, NULL, 0.00, '0', NULL, NULL, '2020-01-06 16:16:05', '2020-01-06 16:16:05', NULL),
(16, 'Mahesh Gohite', 'mgohite@gmail.com', '9975882747', NULL, '$2y$10$Iwdg4vR8J6VhudFtavqpKuWONCrduseK4rpVzZecotiCJuY8rozQe', NULL, NULL, 'android', 'manual', NULL, NULL, 0.00, '0', NULL, NULL, '2020-01-11 13:57:11', '2020-01-11 13:57:11', NULL),
(17, 'Gaurav Valera', 'gaurav.valera1@gmail.com', '+918554017994', NULL, '$2y$10$nj8otbjrfclBRfBi0ujWNuQUsMC57CaYqTG.07bghupVlMAMFFgHS', NULL, NULL, 'android', 'manual', NULL, NULL, 0.00, '0', NULL, NULL, '2020-01-12 04:46:27', '2020-01-12 04:46:27', NULL),
(18, 'Rohit', 'rohitpatil186@gmail.com', '7798801111', NULL, '$2y$10$6kkZnKxMEcACTbXU2CXygeJMGC1pIsccOJmShU9UqT5sQQ434KvxO', NULL, NULL, 'android', 'manual', NULL, NULL, 0.00, '0', NULL, NULL, '2020-01-12 08:13:12', '2020-01-12 08:13:12', NULL),
(19, 'Om Prakash', 'omprakash1996@gmail.com', '+918824026236', NULL, '$2y$10$F/lo35I7JyjZw/a0mks9HelLEoMCvEzQFPju2rTaiWcyViDYbRCLq', NULL, NULL, 'android', 'manual', NULL, NULL, 0.00, '0', NULL, NULL, '2020-01-13 12:07:45', '2020-01-13 12:07:45', NULL),
(20, 'sss', 'vsinfodeveloper@gmail.com', '+919325277966', NULL, '$2y$10$mYyIHRmUewf.aJaDC.q.S.TAV/y6PfMY54s0FN7TVL0kBm6Z8fQj6', NULL, NULL, 'android', 'manual', '', NULL, 0.00, '0', NULL, NULL, '2020-01-31 01:00:42', '2020-01-31 01:00:42', NULL),
(21, 'sss', 'nsinfodeveloper@gmail.com', '+919325277968', NULL, '$2y$10$CZiQomvE0cyC9TJfx5dt7uzA.FKhfx.GgpMB.8MhyJcoiBNA9CJBS', NULL, NULL, 'android', 'manual', '', NULL, 0.00, '0', NULL, NULL, '2020-01-31 01:07:55', '2020-01-31 01:07:55', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_addresses`
--

DROP TABLE IF EXISTS `user_addresses`;
CREATE TABLE IF NOT EXISTS `user_addresses` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `building` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `street` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `city` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `state` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `country` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `pincode` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `landmark` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `map_address` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `latitude` double(15,8) NOT NULL,
  `longitude` double(15,8) NOT NULL,
  `type` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `user_addresses`
--

INSERT INTO `user_addresses` (`id`, `user_id`, `building`, `street`, `city`, `state`, `country`, `pincode`, `landmark`, `map_address`, `latitude`, `longitude`, `type`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 1, 'b101', NULL, 'Pune', 'Maharashtra', 'India', '411017', 'vijay sales', '14, Rahatani Rd, Jagtap Dairy, Pimple Saudagar, Pune, Maharashtra 411017, India', 18.60121340, 73.79393250, 'home', '2019-10-20 22:08:02', '2019-10-20 22:08:02', NULL),
(2, 1, 'heh', NULL, 'Pune', 'Maharashtra', 'India', '411030', 'bxb', 'Pune-Okayama Friendship Garden, Sinhgad Rd, Pune Okayama Friendship Garden, Dattawadi, Pune, Maharashtra 411030, India', 18.49145310, 73.83678890, 'work', '2019-10-23 14:22:58', '2019-10-23 14:22:58', NULL),
(3, 7, '03', NULL, 'Nashik', 'Maharashtra', 'India', '422011', 'Dwarka', 'Kathe Galli Bus Stop, Nashik - Pune Rd, General Vaidya Nagar, Nashik, Maharashtra 422011, India', 19.99044950, 73.79924300, 'other', '2019-11-06 17:12:23', '2019-11-06 17:12:23', NULL),
(4, 1, '101', NULL, 'Pune', 'Maharashtra', 'India', '411045', 'near mandir', '4, Madhuban Society Rd, Balewadi, Pune, Maharashtra 411045, India', 18.57841730, 73.76970250, 'room', '2019-11-10 19:24:26', '2019-11-10 19:24:26', NULL),
(5, 1, 'c101', NULL, 'Watunde', 'Maharashtra', 'India', '412107', 'near', 'Unnamed Road, Watunde, Maharashtra 412107, India', 18.44590400, 73.59332860, 'other', '2019-11-10 19:27:12', '2019-11-10 19:27:12', NULL),
(6, 8, '38', NULL, 'Amravati', 'Maharashtra', 'India', '444605', '53320', 'Yashoda Nagar Rd, Shanti Nagar, Juni Kotwali, Amravati, Maharashtra 444605, India', 20.91625280, 77.77221400, 'home', '2019-11-12 21:52:14', '2019-11-12 22:51:02', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_carts`
--

DROP TABLE IF EXISTS `user_carts`;
CREATE TABLE IF NOT EXISTS `user_carts` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `promocode_id` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `note` text COLLATE utf8mb4_unicode_ci,
  `savedforlater` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `user_carts`
--

INSERT INTO `user_carts` (`id`, `user_id`, `product_id`, `promocode_id`, `order_id`, `quantity`, `note`, `savedforlater`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 1, 1, NULL, 22, 1, NULL, 0, '2019-11-12 09:42:16', '2019-11-12 09:42:37', '2019-11-12 09:42:37'),
(2, 10, 2, NULL, NULL, 1, NULL, 0, '2019-11-12 10:07:14', '2019-11-12 10:07:14', NULL),
(3, 10, 3, NULL, NULL, 1, NULL, 0, '2019-11-12 10:07:20', '2019-11-12 10:07:20', NULL),
(4, 1, 2, NULL, NULL, 1, NULL, 0, '2019-11-12 10:11:07', '2019-11-14 22:06:21', '2019-11-14 22:06:21'),
(5, 1, 3, NULL, NULL, 1, NULL, 0, '2019-11-12 10:11:10', '2019-11-14 22:06:21', '2019-11-14 22:06:21'),
(6, 1, 1, NULL, NULL, 3, NULL, 0, '2019-11-14 22:06:21', '2019-11-15 10:04:40', NULL),
(7, 8, 2, NULL, NULL, 1, NULL, 0, '2019-11-15 08:09:04', '2019-11-15 09:54:41', '2019-11-15 09:54:41'),
(8, 8, 1, NULL, NULL, 1, NULL, 0, '2019-11-15 09:55:34', '2019-11-15 09:55:51', '2019-11-15 09:55:51'),
(9, 8, 1, NULL, NULL, 1, NULL, 0, '2019-11-15 09:55:56', '2019-11-15 09:56:17', '2019-11-15 09:56:17'),
(10, 8, 2, NULL, NULL, 3, NULL, 0, '2019-11-15 09:56:17', '2019-11-15 09:56:32', NULL),
(11, 8, 3, NULL, NULL, 1, NULL, 0, '2019-11-15 09:56:21', '2019-11-15 10:01:09', '2019-11-15 10:01:09'),
(12, 11, 2, NULL, NULL, 1, NULL, 0, '2019-11-15 20:33:06', '2019-11-15 20:35:16', '2019-11-15 20:35:16'),
(13, 11, 1, NULL, NULL, 1, NULL, 0, '2019-11-15 20:35:30', '2019-11-15 20:35:30', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `wallet_passbooks`
--

DROP TABLE IF EXISTS `wallet_passbooks`;
CREATE TABLE IF NOT EXISTS `wallet_passbooks` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `amount` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `message` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` enum('CREDITED','DEBITED') COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `wallet_passbooks`
--

INSERT INTO `wallet_passbooks` (`id`, `user_id`, `amount`, `message`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 8, '50.00', 'wallet Credited  Using Promocode FREEMEAL', 'CREDITED', '2019-11-15 08:10:39', '2019-11-15 08:10:39', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `zones`
--

DROP TABLE IF EXISTS `zones`;
CREATE TABLE IF NOT EXISTS `zones` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `north_east_lat` double(15,8) NOT NULL,
  `north_east_lng` double(15,8) NOT NULL,
  `south_west_lat` double(15,8) NOT NULL,
  `south_west_lng` double(15,8) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
