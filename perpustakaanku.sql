-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 11 Jul 2021 pada 11.55
-- Versi server: 10.4.19-MariaDB
-- Versi PHP: 7.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `perpustakaanku`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `anggota`
--

CREATE TABLE `anggota` (
  `id` varchar(10) NOT NULL,
  `nim` varchar(15) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `jk` varchar(10) NOT NULL,
  `id_semester` int(11) NOT NULL,
  `kd_jurusan` varchar(10) NOT NULL,
  `kd_kelas` varchar(20) NOT NULL,
  `no_telephone` varchar(13) NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `anggota`
--

INSERT INTO `anggota` (`id`, `nim`, `nama`, `jk`, `id_semester`, `kd_jurusan`, `kd_kelas`, `no_telephone`, `status`) VALUES
('A0001', '171351218', 'Andy', 'PRIA', 1, 'IF', 'PC', '081234567891', 'AKTIF'),
('A0002', '171350000', 'Bayu', 'PRIA', 1, 'IF', 'MA', '081234567892', 'AKTIF'),
('A0003', '171350003', 'Cici', 'WANITA', 3, 'TM', 'MC', '081234567893', 'AKTIF'),
('A0004', '171350004', 'Dadang', 'PRIA', 8, 'IF', 'MA', '081234567894', 'AKTIF'),
('A0005', '171350005', 'Efendi', 'PRIA', 5, 'TM', 'MA', '081234567895', 'AKTIF');

-- --------------------------------------------------------

--
-- Struktur dari tabel `buku`
--

CREATE TABLE `buku` (
  `kode` varchar(20) NOT NULL,
  `judul` varchar(20) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `tahun` int(4) NOT NULL,
  `penerbit` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `buku`
--

INSERT INTO `buku` (`kode`, `judul`, `nama`, `tahun`, `penerbit`, `status`) VALUES
('B0001', 'Mentari', 'Ronaldo', 2020, 'Cahaya', 'ADA'),
('B0002', 'hujan gerimis', 'Suparman', 1998, 'Cahaya', 'ADA'),
('B0003', 'Anak Jalanan', 'Harry', 1990, 'Cahaya', 'ADA'),
('B0004', 'Anak Kosan Kelaparan', 'Doni', 2021, 'Harapan Hidup', 'ADA');

-- --------------------------------------------------------

--
-- Struktur dari tabel `jurusan`
--

CREATE TABLE `jurusan` (
  `kode_jurusan` varchar(10) NOT NULL,
  `nama_jurusan` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `jurusan`
--

INSERT INTO `jurusan` (`kode_jurusan`, `nama_jurusan`) VALUES
('IF', 'Teknik Informatika'),
('MI', 'Teknik Mesin'),
('TI', 'Teknik Industri'),
('TM', 'Teknik Manajemen'),
('TT', 'Teknik Tekstil');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kelas`
--

CREATE TABLE `kelas` (
  `kode_kelas` varchar(2) NOT NULL,
  `nama_kelas` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `kelas`
--

INSERT INTO `kelas` (`kode_kelas`, `nama_kelas`) VALUES
('MA', 'Malam A'),
('MB', 'Malam B'),
('MC', 'Malam C'),
('PA', 'Pagi A'),
('PB', 'Pagi B'),
('PC', 'Pagi C');

-- --------------------------------------------------------

--
-- Struktur dari tabel `peminjaman`
--

CREATE TABLE `peminjaman` (
  `id` varchar(20) NOT NULL,
  `nim_anggota` varchar(20) NOT NULL,
  `nama_anggota` varchar(20) NOT NULL,
  `judul_buku` varchar(20) NOT NULL,
  `tanggal_pinjam` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `peminjaman`
--

INSERT INTO `peminjaman` (`id`, `nim_anggota`, `nama_anggota`, `judul_buku`, `tanggal_pinjam`) VALUES
('C0001', 'A0001', 'A0001', 'B0001', '01 Juni 2020'),
('C0002', 'A0003', 'A0003', 'B0003', '01 Juni 2021');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengembalian`
--

CREATE TABLE `pengembalian` (
  `id` varchar(20) NOT NULL,
  `nim_anggota` varchar(20) NOT NULL,
  `nama_anggota` varchar(20) NOT NULL,
  `judul_buku` varchar(20) NOT NULL,
  `tanggal_pengembalian` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pengembalian`
--

INSERT INTO `pengembalian` (`id`, `nim_anggota`, `nama_anggota`, `judul_buku`, `tanggal_pengembalian`) VALUES
('D0001', 'A0001', 'A0001', 'B0001', '1 juni 2021'),
('D0002', 'A0003', 'A0003', 'B0003', '7 Juli 2021');

-- --------------------------------------------------------

--
-- Struktur dari tabel `semester`
--

CREATE TABLE `semester` (
  `id` int(11) NOT NULL,
  `nama_semester` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `semester`
--

INSERT INTO `semester` (`id`, `nama_semester`) VALUES
(1, 'SEMESTER I'),
(2, 'SEMESTER II'),
(3, 'SEMESTER III'),
(4, 'SEMESTER IV'),
(5, 'SEMESTER V'),
(6, 'SEMESTER VI'),
(7, 'SEMESTER VII'),
(8, 'SEMESTER VIII'),
(9, 'SEMESTER IX'),
(10, 'SEMESTER X'),
(11, 'SEMESTER XI'),
(12, 'SEMESTER XII'),
(13, 'SEMESTER XIII'),
(14, 'SEMESTER XIV');

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `level` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id`, `nama`, `username`, `password`, `level`) VALUES
(1, 'Kepala Pustakawan', 'kepala', '12345678', 'kepala'),
(2, 'Pustakawan', 'pustakawan', '12345678', 'pustakawan');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `anggota`
--
ALTER TABLE `anggota`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`kode`);

--
-- Indeks untuk tabel `jurusan`
--
ALTER TABLE `jurusan`
  ADD PRIMARY KEY (`kode_jurusan`);

--
-- Indeks untuk tabel `kelas`
--
ALTER TABLE `kelas`
  ADD PRIMARY KEY (`kode_kelas`);

--
-- Indeks untuk tabel `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `pengembalian`
--
ALTER TABLE `pengembalian`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `semester`
--
ALTER TABLE `semester`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `semester`
--
ALTER TABLE `semester`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
