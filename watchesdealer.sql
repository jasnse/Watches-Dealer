-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 23 Jan 2025 pada 09.59
-- Versi server: 10.4.19-MariaDB
-- Versi PHP: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `watchesdealer`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `brand`
--

CREATE TABLE `brand` (
  `BrandID` int(11) NOT NULL,
  `BrandName` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `brand`
--

INSERT INTO `brand` (`BrandID`, `BrandName`) VALUES
(1, 'Rolex'),
(2, 'Omega 1'),
(10, '123as');

-- --------------------------------------------------------

--
-- Struktur dari tabel `cart`
--

CREATE TABLE `cart` (
  `UserID` int(11) NOT NULL,
  `WatchID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `cart`
--

INSERT INTO `cart` (`UserID`, `WatchID`, `Quantity`) VALUES
(2, 1, 2),
(6, 2, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `detailtransaction`
--

CREATE TABLE `detailtransaction` (
  `TransactionID` int(11) NOT NULL,
  `WatchID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `detailtransaction`
--

INSERT INTO `detailtransaction` (`TransactionID`, `WatchID`, `Quantity`) VALUES
(18, 2, 1),
(18, 1, 2),
(19, 1, 4),
(19, 2, 2),
(19, 2, 1),
(20, 2, 2),
(20, 1, 3),
(21, 1, 1),
(21, 1, 2),
(21, 1, 3),
(21, 1, 4),
(21, 2, 2),
(21, 2, 1),
(22, 2, 2),
(22, 2, 1),
(22, 1, 2),
(22, 1, 1),
(23, 2, 2),
(23, 1, 2),
(34, 2, 2),
(35, 2, 1),
(35, 1, 2),
(35, 2, 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `headertransaction`
--

CREATE TABLE `headertransaction` (
  `TransactionID` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `TransactionDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `headertransaction`
--

INSERT INTO `headertransaction` (`TransactionID`, `UserID`, `TransactionDate`) VALUES
(18, 2, '2022-12-20'),
(19, 2, '2022-12-20'),
(20, 6, '2022-12-20'),
(21, 6, '2022-12-20'),
(22, 2, '2022-12-23'),
(23, 6, '2022-12-23'),
(34, 6, '2022-12-24'),
(35, 6, '2022-12-24');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `UserID` int(11) NOT NULL,
  `UserName` varchar(40) NOT NULL,
  `UserEmail` varchar(40) NOT NULL,
  `UserPassword` varchar(20) NOT NULL,
  `UserGender` varchar(6) NOT NULL,
  `UserRole` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`UserID`, `UserName`, `UserEmail`, `UserPassword`, `UserGender`, `UserRole`) VALUES
(1, 'Audina Chandra', 'a@b.com', 'a12345', 'Female', 'Admin'),
(2, 'Gav Reynald', 'gavrey@gmail.com', 'a12345', 'Male', 'Customer'),
(6, 'alif cepmek', '123', '123123', 'Female', 'Customer'),
(7, 'agusasadw', '321', '321321', 'Female', 'Admin'),
(12, 'testasd', '423@gmail.com', '423423', 'Male', 'Customer');

-- --------------------------------------------------------

--
-- Struktur dari tabel `watch`
--

CREATE TABLE `watch` (
  `WatchID` int(11) NOT NULL,
  `BrandID` int(11) NOT NULL,
  `WatchName` varchar(40) NOT NULL,
  `WatchPrice` int(11) NOT NULL,
  `WatchStock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `watch`
--

INSERT INTO `watch` (`WatchID`, `BrandID`, `WatchName`, `WatchPrice`, `WatchStock`) VALUES
(1, 1, 'Submariner 300 Watch', 10500, 4),
(2, 2, 'Seamaster Planet Ocean Watch', 6900, 2),
(22, 2, 'aacean Watch', 234512, 5);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`BrandID`);

--
-- Indeks untuk tabel `cart`
--
ALTER TABLE `cart`
  ADD KEY `UserID` (`UserID`),
  ADD KEY `WatchID` (`WatchID`);

--
-- Indeks untuk tabel `detailtransaction`
--
ALTER TABLE `detailtransaction`
  ADD KEY `TransactionID` (`TransactionID`),
  ADD KEY `WatchID` (`WatchID`);

--
-- Indeks untuk tabel `headertransaction`
--
ALTER TABLE `headertransaction`
  ADD PRIMARY KEY (`TransactionID`),
  ADD KEY `UserID` (`UserID`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserID`);

--
-- Indeks untuk tabel `watch`
--
ALTER TABLE `watch`
  ADD PRIMARY KEY (`WatchID`),
  ADD KEY `BrandID` (`BrandID`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `brand`
--
ALTER TABLE `brand`
  MODIFY `BrandID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT untuk tabel `headertransaction`
--
ALTER TABLE `headertransaction`
  MODIFY `TransactionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT untuk tabel `watch`
--
ALTER TABLE `watch`
  MODIFY `WatchID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE CASCADE,
  ADD CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`WatchID`) REFERENCES `watch` (`WatchID`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `detailtransaction`
--
ALTER TABLE `detailtransaction`
  ADD CONSTRAINT `detailtransaction_ibfk_1` FOREIGN KEY (`TransactionID`) REFERENCES `headertransaction` (`TransactionID`) ON DELETE CASCADE,
  ADD CONSTRAINT `detailtransaction_ibfk_2` FOREIGN KEY (`WatchID`) REFERENCES `watch` (`WatchID`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `headertransaction`
--
ALTER TABLE `headertransaction`
  ADD CONSTRAINT `headertransaction_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `watch`
--
ALTER TABLE `watch`
  ADD CONSTRAINT `watch_ibfk_1` FOREIGN KEY (`BrandID`) REFERENCES `brand` (`BrandID`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
