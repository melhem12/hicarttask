-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 28, 2022 at 04:55 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.3.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_hicart_task`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `customer_address` varchar(255) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `customer_phone` varchar(255) DEFAULT NULL,
  `added_by` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `created_at`, `customer_address`, `customer_name`, `customer_phone`, `added_by`) VALUES
(1, '2022-02-28 16:15:21.000000', NULL, 'fathi', '12344567', 1),
(3, '2022-02-28 16:26:28.000000', 'updatedAddress', 'test update', '12345', 1);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  `order_date` datetime(6) DEFAULT NULL,
  `quantity` bigint(20) DEFAULT NULL,
  `added_by` bigint(20) NOT NULL,
  `customer_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `amount`, `order_date`, `quantity`, `added_by`, `customer_id`) VALUES
(1, 245.9, '2022-02-28 17:09:38.000000', 100, 1, 1),
(2, 3333.9, '2022-02-28 17:12:41.000000', 123, 1, 1),
(3, 66666.12, '2022-02-28 17:13:28.000000', 300, 1, 1),
(5, 3636.66, '2022-02-28 17:22:09.000000', 68578, 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `user_hicart_task`
--

CREATE TABLE `user_hicart_task` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_hicart_task`
--

INSERT INTO `user_hicart_task` (`id`, `address`, `created`, `email`, `gender`, `name`, `password`, `phone`) VALUES
(1, 'baalback', '2022-02-28 14:36:40.000000', 'melhemshoker@gmail.com', 'male', 'melhem shoker', '$2a$10$Pj/.vzPoxsH0fMUS3OFZLOBd2IT0agMqpEeZVFMpJMAKJad2KAp0K', 71722760);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK22ydxh3vb1x8enu11di4byder` (`added_by`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpqiogb51klxx63mbq1peek1if` (`added_by`),
  ADD KEY `FK624gtjin3po807j3vix093tlf` (`customer_id`);

--
-- Indexes for table `user_hicart_task`
--
ALTER TABLE `user_hicart_task`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user_hicart_task`
--
ALTER TABLE `user_hicart_task`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `FK22ydxh3vb1x8enu11di4byder` FOREIGN KEY (`added_by`) REFERENCES `user_hicart_task` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK624gtjin3po807j3vix093tlf` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `FKpqiogb51klxx63mbq1peek1if` FOREIGN KEY (`added_by`) REFERENCES `user_hicart_task` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
