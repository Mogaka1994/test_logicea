-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 26, 2023 at 12:26 AM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 8.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cards`
--

-- --------------------------------------------------------

--
-- Table structure for table `cards`
--

CREATE TABLE `cards` (
  `id` bigint(20) NOT NULL,
  `color` varchar(6) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `description` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `status` enum('DONE','IN_PROGRESS','TO_DO') DEFAULT NULL,
  `updated_at` datetime(6) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cards`
--

INSERT INTO `cards` (`id`, `color`, `created_at`, `description`, `name`, `status`, `updated_at`, `user_id`) VALUES
(1, '#ghsss', '2023-08-24 11:04:00.000000', 'dsfghj', 'polycarpmogaka16@gmail.com', 'IN_PROGRESS', '2023-08-24 11:04:00.000000', 1),
(4, '#FFFFF', '2023-08-26 00:02:37.000000', '12345678', 'card1', 'TO_DO', '2023-08-26 00:02:37.000000', 1),
(5, '#FFFFF', '2023-08-26 00:02:50.000000', '12345678', 'card2', 'TO_DO', '2023-08-26 00:02:50.000000', 1),
(6, '#FFFFF', '2023-08-26 00:02:56.000000', '12345678', 'card3', 'TO_DO', '2023-08-26 00:02:56.000000', 1),
(7, '#FFFFF', '2023-08-26 00:03:00.000000', '12345678', 'card4', 'TO_DO', '2023-08-26 00:03:00.000000', 1),
(8, '#FFFFF', '2023-08-26 00:03:06.000000', '12345678', 'card5', 'TO_DO', '2023-08-26 00:03:06.000000', 1),
(9, '#FFFFF', '2023-08-26 00:03:11.000000', '12345678', 'card6', 'TO_DO', '2023-08-26 00:03:11.000000', 1),
(10, '#FFFFF', '2023-08-26 00:03:16.000000', '12345678', 'card7', 'TO_DO', '2023-08-26 00:03:16.000000', 1),
(11, '#FFFFF', '2023-08-26 00:03:21.000000', '12345678', 'card8', 'TO_DO', '2023-08-26 00:03:21.000000', 1),
(12, '#FFFFF', '2023-08-26 00:03:24.000000', '12345678', 'card9', 'TO_DO', '2023-08-26 00:03:24.000000', 1),
(13, '#FFFFF', '2023-08-26 00:03:30.000000', '12345678', 'card10', 'TO_DO', '2023-08-26 00:03:30.000000', 1),
(14, '#FFFFF', '2023-08-26 00:06:11.000000', '12345678', 'card11', 'TO_DO', '2023-08-26 00:06:11.000000', 1),
(15, '#FFFFF', '2023-08-26 00:06:15.000000', '12345678', 'card12', 'TO_DO', '2023-08-26 00:06:15.000000', 1),
(16, '#FFFFF', '2023-08-26 00:06:19.000000', '12345678', 'card14', 'TO_DO', '2023-08-26 00:06:19.000000', 1),
(17, '#FFFFF', '2023-08-26 00:06:23.000000', '12345678', 'card15', 'TO_DO', '2023-08-26 00:06:23.000000', 1),
(18, '#FFFFF', '2023-08-26 01:22:35.000000', '12345678', 'polycarpmogaka16@gmail.com', 'TO_DO', '2023-08-26 01:22:35.000000', 1),
(19, '#FFFFF', '2023-08-26 01:24:57.000000', '12345678', 'polycarpmogaka16@gmail.com', 'TO_DO', '2023-08-26 01:24:57.000000', 1);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` enum('ROLE_ADMIN','ROLE_MEMBER') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ROLE_MEMBER'),
(2, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `status`) VALUES
(1, 'polycarpmogaka16@gmail.com', '$2a$10$EqKus7PlZTy73kL.ivYlmufTacd.yOHVVeGkMhO3XDbotAaEIzqn2', 1),
(2, 'polycarpmogaka21@gmail.com', '$2a$10$JtJ1hF1qThjXLI9gSz9LOux.lYnibIx4bmexsmc5n0YHaMLMlmdHW', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(1, 2),
(2, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cards`
--
ALTER TABLE `cards`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cards`
--
ALTER TABLE `cards`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
