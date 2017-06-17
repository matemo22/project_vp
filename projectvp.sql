-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 17, 2017 at 11:45 AM
-- Server version: 10.1.10-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projectvp`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id` int(11) NOT NULL,
  `name` varchar(25) NOT NULL,
  `product` varchar(10) NOT NULL,
  `jenis` varchar(25) NOT NULL,
  `supplier` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `gudang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id`, `name`, `product`, `jenis`, `supplier`, `harga`, `quantity`, `gudang`) VALUES
(1, '55" QLED 4K Curved Smart TV Q8C', 'TV', 'LED', 4, 28998000, 13, 1),
(2, 'SAMSUNG Kulkas 2 Pintu [RT25FARBDSA]', 'Kulkas', 'Dua Pintu', 4, 4947000, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `merek`
--

CREATE TABLE `merek` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `merek`
--

INSERT INTO `merek` (`id`, `name`) VALUES
(1, 'LG'),
(2, 'Sharp'),
(3, 'Samsung'),
(4, 'Panasonic'),
(5, 'Motorola'),
(6, 'Apple'),
(7, 'Epson'),
(8, 'HP'),
(9, 'Nintendo'),
(10, 'Hitachi'),
(11, 'Sony'),
(12, 'Toshiba'),
(13, 'BSH'),
(14, 'Dell'),
(15, 'Intel'),
(16, 'Fujitsu'),
(17, 'Founder'),
(18, 'Canon'),
(19, 'Nokia'),
(20, 'NEC'),
(21, 'Huawei'),
(22, 'Lenovo'),
(23, 'Ericsson'),
(24, 'Philips'),
(25, 'CEC'),
(26, 'Western Digital'),
(27, 'MSI'),
(28, 'FujiFilm'),
(29, 'Haier'),
(30, 'Ricoh'),
(31, 'Xerox'),
(32, 'Seagate'),
(33, 'Gree'),
(34, 'Whirpool'),
(35, 'Nikon'),
(36, 'Hisense'),
(37, 'Changhong'),
(38, 'Asus'),
(39, 'ZTE'),
(40, 'TCL'),
(41, 'Wistron'),
(42, 'Acer');

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `id` int(11) NOT NULL,
  `supplier` int(11) NOT NULL,
  `produkName` varchar(255) NOT NULL,
  `produkType` varchar(255) NOT NULL,
  `namaBarang` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL,
  `tglPesan` date NOT NULL,
  `user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`id`, `supplier`, `produkName`, `produkType`, `namaBarang`, `quantity`, `tglPesan`, `user`) VALUES
(1, 3, 'TV', 'LED', 'tvg', 12, '2017-06-17', 1);

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `id` int(11) NOT NULL,
  `id_merek` int(11) NOT NULL,
  `location` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`id`, `id_merek`, `location`) VALUES
(1, 1, 'Surabaya'),
(2, 2, 'Surabaya'),
(3, 1, 'Jakarta'),
(4, 3, 'Surabaya'),
(5, 4, 'Surabaya'),
(6, 5, 'Surabaya'),
(7, 6, 'Surabaya'),
(8, 7, 'Surabaya'),
(9, 8, 'Surabaya'),
(10, 9, 'Surabaya'),
(11, 10, 'Surabaya'),
(12, 11, 'Surabaya'),
(13, 12, 'Surabaya'),
(14, 13, 'Surabaya'),
(15, 14, 'Surabaya'),
(16, 15, 'Surabaya'),
(17, 16, 'Surabaya'),
(18, 17, 'Surabaya'),
(19, 18, 'Surabaya'),
(20, 19, 'Surabaya'),
(21, 20, 'Surabaya'),
(22, 21, 'Surabaya'),
(23, 22, 'Surabaya'),
(24, 23, 'Surabaya'),
(25, 24, 'Surabaya'),
(26, 25, 'Surabaya'),
(27, 26, 'Surabaya'),
(28, 27, 'Surabaya'),
(29, 28, 'Surabaya'),
(30, 29, 'Surabaya'),
(31, 30, 'Surabaya'),
(32, 31, 'Surabaya'),
(33, 32, 'Surabaya'),
(34, 33, 'Surabaya'),
(35, 34, 'Surabaya'),
(36, 35, 'Surabaya'),
(37, 36, 'Surabaya'),
(38, 37, 'Surabaya'),
(39, 38, 'Surabaya'),
(40, 39, 'Surabaya'),
(41, 40, 'Surabaya'),
(42, 41, 'Surabaya'),
(43, 42, 'Surabaya');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `authority` int(11) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `authority`, `status`) VALUES
(1, 'matemo22', 'asdf', 1, 1),
(2, 'atan', '1234', 1, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `merek`
--
ALTER TABLE `merek`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `merek`
--
ALTER TABLE `merek`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;
--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
