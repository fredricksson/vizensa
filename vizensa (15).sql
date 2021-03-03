-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 04, 2020 at 09:14 AM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `vizensa`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `category_id` int(11) NOT NULL,
  `catNome` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`category_id`, `catNome`) VALUES
(5, 't-shirts'),
(14, 'CHINELOS'),
(15, 'camisas'),
(16, 'Sapatilhas'),
(17, 'Jeans'),
(18, 'Sapatos'),
(19, 'colares'),
(20, 'aneis'),
(21, 'pulseiras');

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL,
  `cliNome` varchar(45) NOT NULL,
  `cliEmail` varchar(45) DEFAULT NULL,
  `cliContacto` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cliente`
--

INSERT INTO `cliente` (`idCliente`, `cliNome`, `cliEmail`, `cliContacto`) VALUES
(1, 'fred', '8421177430', 'dsd'),
(2, 'fred', '', ''),
(3, 'fred', '', ''),
(4, 'fred', '', ''),
(5, 'fred', '', ''),
(6, 'tomas', '', ''),
(7, 'hhhekr', '', ''),
(8, 'jbkjfdbgkjfd', '', ''),
(9, 'gabriel', '', ''),
(10, 'tomas', '', ''),
(11, 'tsm', '', '655'),
(12, 'MonTech', 'Montech@gmail.com', '842177430'),
(13, 'Montech', 'Montech@gmail.com', '842177430'),
(14, 'Montech', 'Montech@gmail.com', '842177430'),
(15, 'Montech', 'Montech@gmail.com', '84211430');

-- --------------------------------------------------------

--
-- Table structure for table `empresa`
--

CREATE TABLE `empresa` (
  `idEmpresa` int(11) NOT NULL,
  `empNome` varchar(45) DEFAULT NULL,
  `empTelfone` varchar(45) DEFAULT NULL,
  `empLogotipo` varchar(45) DEFAULT NULL,
  `empEmail` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `empresa`
--

INSERT INTO `empresa` (`idEmpresa`, `empNome`, `empTelfone`, `empLogotipo`, `empEmail`) VALUES
(1, 'vizenza', 'dsd', 'gmail.com', '');

-- --------------------------------------------------------

--
-- Table structure for table `funcionario`
--

CREATE TABLE `funcionario` (
  `funCodigo` int(11) NOT NULL,
  `funNome` varchar(45) NOT NULL,
  `funApelido` varchar(45) NOT NULL,
  `funTipoFuncionario` varchar(45) NOT NULL,
  `funNr_bi` varchar(45) DEFAULT NULL,
  `funGenero` varchar(45) NOT NULL,
  `funEstado_civil` varchar(45) NOT NULL,
  `funContacto` varchar(45) DEFAULT NULL,
  `funContacto2` int(11) DEFAULT NULL,
  `funEmail` varchar(45) DEFAULT NULL,
  `funData_nascimento` date NOT NULL,
  `funCargo` varchar(45) DEFAULT NULL,
  `funSalario` float NOT NULL,
  `funNr_conta` bigint(45) NOT NULL,
  `funAutenticavel` tinyint(1) DEFAULT '0',
  `visivel` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `funcionario`
--

INSERT INTO `funcionario` (`funCodigo`, `funNome`, `funApelido`, `funTipoFuncionario`, `funNr_bi`, `funGenero`, `funEstado_civil`, `funContacto`, `funContacto2`, `funEmail`, `funData_nascimento`, `funCargo`, `funSalario`, `funNr_conta`, `funAutenticavel`, `visivel`) VALUES
(1, 'fred', 'bande', 'gerente', '111222bi', 'c', 'solteiro', '12333', 1233, '@gmail', '1899-12-31', 'cargo', 23.4, 222, 0, 1),
(2, 'fred', 'bande', 'gerente', '111222bi', 'c', 'solteiro', '12333', 1233, '@gmail', '3270-07-05', 'cargo', 23.4, 222, 0, 1),
(3, 'fred', 'bande', 'gerente', '111222bi', 'c', 'solteiro', '12333', 1233, '@gmail', '3870-07-05', 'cargo', 23.4, 222, 0, 1),
(6, 'fred3', 'narciso', 'gerente', '111222bi', 'd', 'solteiro', '12333', 1233, '@gmail', '1999-07-12', 'cargo', 23.4, 222, 0, 1),
(7, 'gerdon', 'bande', 'Empregado', '334', 'Masculino', 'Solteiro/a', '56', 676, 'ghj', '2016-02-01', 'jghj', 767, 6767, 0, 1),
(8, 'gerdon', 'bande', 'Empregado', '334', 'Masculino', 'Solteiro/a', '56', 676, 'ghj', '2016-02-01', 'jghj', 767, 6767, 0, 1),
(9, 'FRedricksson Narciso', 'Bande', 'Gerente', '343434', 'Masculino', 'Solteiro/a', '34343', 343, 'sdfs', '2016-02-01', '44444', 4343, 3433, 0, 1),
(10, 'rfsd', 'sfdds', 'Empregado', 'sdf', 'Masculino', 'Casado/a', '44', 44, 'dfs', '2016-01-01', 'ds', 443, 33, 0, 1),
(11, 'ter', 'erter', 'Gerente', '55', 'Masculino', 'Solteiro/a', '55', 555, 'fdg', '2017-02-01', 'dfg', 55646, 6645, 0, 1),
(12, 'davi', 'fgfgd', 'Gerente', 'rtt', 'Masculino', 'Solteiro/a', '8778', 87789, 'hkjhjk', '2017-02-01', 'jhhg', 899, 8979, 0, 1),
(13, 'fd', 'safs', 'Empregado', '435', 'Masculino', 'Solteiro/a', '45564', 646, 'hfdghyfg', '2016-01-01', 'fgf', 8887, 456575, 0, 1),
(14, 'fg3', 'dfgfd', 'Empregado', '546', 'Masculino', 'Solteiro/a', '56', 456, 'gdf', '2017-01-01', 'gfh', 6575, 56765, 0, 1),
(15, 'maria', 'baga', 'Gerente', '22', 'Masculino', 'Casado/a', '22', 22, 'wsws', '2017-04-01', 'dd', 44, 444, 0, 1),
(16, 'jessica', 'texeira', 'Gerente', '3424334t', 'Masculino', 'Solteiro/a', '842177430', 842177430, 'jessica@gmail.com', '2017-09-01', 'bggfd', 4, 4, 0, 1),
(17, 'jessica', 'texeira', 'Gerente', '3424334t', 'Masculino', 'Solteiro/a', '842177430', 842177430, 'jessica@gmail.com', '2017-09-01', 'bggfd', 4, 4, 0, 1),
(18, 'jessica', 'texeira', 'Gerente', '3424334t', 'Masculino', 'Solteiro/a', '842177430', 842177430, 'jessica@gmail.com', '2017-09-01', 'bggfd', 4, 4, 0, 1),
(19, 'jessica', 'texeira', 'Gerente', '44343943d', 'Femenino', 'Solteiro/a', '842177430', 842177430, 'jessica@gmail.com', '2016-08-01', 'boss', 60000, 43434343, 0, 1),
(20, 'ya', 'ss', 'Empregado', '34', 'Masculino', 'Solteiro/a', '976667676', 676667679, '@gmail.com', '2017-08-01', 'dfg', 5654, 56445, 0, 1),
(21, 'g6', 'fg', 'Gerente', '33', 'Masculino', 'Casado/a', '333333333', 333333333, '@gmail.com', '2016-02-01', 'ewqd', 33434, 343234, 0, 1),
(22, 'g6', 'fg', 'Gerente', '33', 'Masculino', 'Casado/a', '333333333', 333333333, '@gmail.com', '2016-02-01', 'ewqd', 33434, 343234, 0, 1),
(23, 'g6', 'fg', 'Gerente', '33', 'Masculino', 'Casado/a', '333333333', 333333333, '@gmail.com', '2016-02-01', 'ewqd', 33434, 343234, 0, 1),
(24, 'lolo', 'ere', 'Gerente', '4543', 'Masculino', 'Solteiro/a', '343434343', 343434343, '@gmail.com', '2016-06-01', 'sddfs', 435, 345345, 0, 1),
(25, 'fgdh', 'fghfg', 'Empregado', 'fghgf', 'Femenino', 'Casado/a', '565656565', 656565655, '@gmail.com', '2017-03-01', 'retre', 455645, 456456, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `historico_e_s`
--

CREATE TABLE `historico_e_s` (
  `data` date NOT NULL,
  `idHistorico_E_S` int(11) NOT NULL,
  `hisHora_entrada` varchar(45) NOT NULL,
  `hisHora_saida` varchar(45) DEFAULT NULL,
  `Funcionario_funCodigo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `historico_e_s`
--

INSERT INTO `historico_e_s` (`data`, `idHistorico_E_S`, `hisHora_entrada`, `hisHora_saida`, `Funcionario_funCodigo`) VALUES
('2026-09-01', 1, '11:23', '11:28', 6),
('2026-09-01', 2, '11:23', '11:28', 6),
('2026-09-01', 3, '11:23', '11:28', 6),
('2026-09-01', 4, '11:23', '11:28', 6),
('2026-09-07', 5, '16:73', '17:28', 6),
('2020-09-03', 6, '13:73', '16:28', 6),
('2020-09-03', 7, '13:73', '16:28', 6),
('1999-09-03', 8, '13:73', '16:28', 3);

-- --------------------------------------------------------

--
-- Table structure for table `morada`
--

CREATE TABLE `morada` (
  `morAvenida` varchar(45) DEFAULT NULL,
  `morQuarteirao` varchar(45) DEFAULT NULL,
  `morRua` varchar(45) DEFAULT NULL,
  `idMorada` int(11) NOT NULL,
  `Funcionario_funCodigo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `morada`
--

INSERT INTO `morada` (`morAvenida`, `morQuarteirao`, `morRua`, `idMorada`, `Funcionario_funCodigo`) VALUES
('mahota', '12', 'weer', 2, 1),
('sdfsd', 'sdf', 'sdfs', 5, 9),
('dsf', 'sdfs', 'sdfsd', 6, 9),
('gfhfg', 'fghgh', 'fghfg', 7, 9),
('jhk', 'hjkhj', 'hjk', 8, 9),
('fgh', 'fghfg', 'fghh', 9, 9),
('fgh', 'fgh', 'fh', 10, 9),
('dddd', 'ddd', 'dd', 11, 9),
('1', '12', '123', 12, 24),
('', '', '', 13, 25);

-- --------------------------------------------------------

--
-- Table structure for table `produto`
--

CREATE TABLE `produto` (
  `codigo` int(11) NOT NULL,
  `prodNome` varchar(45) NOT NULL,
  `prodCor` varchar(45) NOT NULL,
  `prodPreco` float NOT NULL,
  `visibilidade` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `produto`
--

INSERT INTO `produto` (`codigo`, `prodNome`, `prodCor`, `prodPreco`, `visibilidade`) VALUES
(1, 'calca', '0x333333ff', 10, 1),
(2, 'camisa', '0x666666ff', 12, 0),
(12, 'HAVAINAS', '0xb31a1aff', 20, 0),
(13, 'Wave Zone', '0x334db3ff', 25, 1),
(14, 'Gucci', '0xcc3333ff', 140, 1),
(15, 'Air Force', '0xffffffff', 260, 1),
(404, 'camisas', '0x003300ff', 14, 1),
(405, 'gucci', '0x990000ff', 14, 1),
(500, 'All Star', '0xb31a1aff', 12, 0),
(546, 'ghfh', '0x666600ff', 45, 0),
(608, 'All Star', '0xb3b31aff', 50, 1),
(657, 'hgf-', '0xb3801aff', 5, 0),
(700, 'Speark', '0xb3b31aff', 13, 0),
(777, 'Speark', '0xb34d1aff', 40, 0),
(888, 'Speark', '0x1a1a1aff', 50, 0),
(999, 'polo', '0x333333ff', 10, 0),
(40040, 'yes', '0xff9966ff', 3, 0),
(657667, 'frt', '0xb3b31aff', 65, 1),
(756756, 'rtyr', '0xb3b31aff', 6, 0),
(5675867, 'tyuy', '0x003300ff', 76, 0);

-- --------------------------------------------------------

--
-- Table structure for table `produtoitem`
--

CREATE TABLE `produtoitem` (
  `id` int(10) NOT NULL,
  `FK_codigoVenda` int(10) NOT NULL,
  `FK_codigoProduto` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `produto_has_category`
--

CREATE TABLE `produto_has_category` (
  `Produto_codigo` int(11) NOT NULL,
  `category_category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `produto_has_category`
--

INSERT INTO `produto_has_category` (`Produto_codigo`, `category_category_id`) VALUES
(1, 8),
(1, 9),
(2, 5),
(3, 5),
(10, 2),
(12, 2),
(12, 14),
(13, 14),
(14, 2),
(14, 5),
(15, 2),
(15, 16),
(23, 2),
(45, 5),
(55, 9),
(56, 2),
(56, 5),
(56, 9),
(90, 8),
(404, 5),
(405, 5),
(500, 16),
(546, 9),
(546, 10),
(546, 14),
(567, 8),
(608, 16),
(646, 9),
(657, 5),
(700, 16),
(777, 16),
(888, 16),
(999, 16),
(4343, 7),
(4564, 8),
(5545, 9),
(40040, 14),
(56456, 7),
(756756, 5),
(5454565, 10),
(5545456, 9),
(5675867, 5);

-- --------------------------------------------------------

--
-- Table structure for table `tamanhos`
--

CREATE TABLE `tamanhos` (
  `id` int(11) NOT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `tamanho` int(11) DEFAULT NULL,
  `produto_codigo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tamanhos`
--

INSERT INTO `tamanhos` (`id`, `quantidade`, `tamanho`, `produto_codigo`) VALUES
(1, 10, 12, 1),
(2, 10, 13, 1),
(3, 12, 30, 2),
(4, 12, 31, 2),
(5, 20, 50, 12),
(6, 20, 43, 12),
(7, 20, 23, 12),
(8, 20, 10, 12),
(9, 25, 23, 13),
(10, 25, 34, 13),
(11, 140, 12, 14),
(12, 140, 13, 14),
(13, 140, 14, 14),
(14, 140, 15, 14),
(15, 140, 16, 14),
(16, 260, 32, 15),
(17, 260, 33, 15),
(18, 14, 30, 404),
(19, 14, 31, 404),
(20, 14, 32, 404),
(21, 14, 30, 405),
(22, 14, 31, 405),
(23, 14, 31, 405),
(24, 12, 42, 500),
(25, 12, 43, 500),
(26, 12, 39, 500),
(27, 13, 12, 700),
(28, 13, 17, 700),
(29, 3, 12, 40040),
(30, 3, 13, 40040),
(31, 45, 45, 546),
(32, 45, 5, 546),
(33, 6, 6, 756756),
(34, 76, 7, 5675867),
(35, 76, 7, 5675867),
(36, 5, 5, 657),
(37, 5, 6, 657),
(38, 50, 42, 608),
(39, 50, 43, 608),
(40, 50, 39, 608),
(41, 40, 39, 777),
(42, 40, 40, 777),
(43, 40, 41, 777),
(44, 40, 42, 777),
(45, 10, 39, 999),
(46, 10, 40, 999),
(47, 10, 41, 999),
(48, 10, 42, 999),
(49, 10, 43, 999),
(50, 50, 40, 888),
(51, 50, 41, 888),
(52, 50, 42, 888),
(53, 50, 43, 888);

-- --------------------------------------------------------

--
-- Table structure for table `utilizador`
--

CREATE TABLE `utilizador` (
  `idUtilizador` int(11) NOT NULL,
  `utiSenha` varchar(45) DEFAULT NULL,
  `utiUtilizador` varchar(45) DEFAULT NULL,
  `Funcionario_funCodigo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `venda`
--

CREATE TABLE `venda` (
  `idVenda` int(11) NOT NULL,
  `venData_venda` date NOT NULL,
  `venHora_venda` varchar(45) NOT NULL,
  `venQuantidade` int(11) NOT NULL,
  `venValor_pago` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `venda`
--

INSERT INTO `venda` (`idVenda`, `venData_venda`, `venHora_venda`, `venQuantidade`, `venValor_pago`) VALUES
(1, '2000-01-12', '13:12', 12, 13),
(2, '2000-01-12', '13:12', 12, 13),
(3, '2000-01-12', '13:12', 12, 13),
(4, '1999-07-12', '15:12', 12, 19);

-- --------------------------------------------------------

--
-- Table structure for table `venda_compra`
--

CREATE TABLE `venda_compra` (
  `Funcionario_codigo` int(11) NOT NULL,
  `produto_codigo` int(11) NOT NULL,
  `cliente_codigo` int(11) NOT NULL,
  `venda_codigo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `venda_compra`
--

INSERT INTO `venda_compra` (`Funcionario_codigo`, `produto_codigo`, `cliente_codigo`, `venda_codigo`) VALUES
(1, 3, 1, 1),
(1, 3, 1, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idCliente`);

--
-- Indexes for table `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`idEmpresa`);

--
-- Indexes for table `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`funCodigo`);

--
-- Indexes for table `historico_e_s`
--
ALTER TABLE `historico_e_s`
  ADD PRIMARY KEY (`idHistorico_E_S`),
  ADD KEY `Funcionario_funCodigo` (`Funcionario_funCodigo`);

--
-- Indexes for table `morada`
--
ALTER TABLE `morada`
  ADD PRIMARY KEY (`idMorada`,`Funcionario_funCodigo`),
  ADD KEY `Funcionario_funCodigo` (`Funcionario_funCodigo`);

--
-- Indexes for table `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`codigo`);

--
-- Indexes for table `produtoitem`
--
ALTER TABLE `produtoitem`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_codigoVenda` (`FK_codigoVenda`),
  ADD KEY `FK_codigoProduto` (`FK_codigoProduto`);

--
-- Indexes for table `produto_has_category`
--
ALTER TABLE `produto_has_category`
  ADD PRIMARY KEY (`Produto_codigo`,`category_category_id`),
  ADD KEY `category_category_id` (`category_category_id`);

--
-- Indexes for table `tamanhos`
--
ALTER TABLE `tamanhos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `produto_codigo` (`produto_codigo`);

--
-- Indexes for table `utilizador`
--
ALTER TABLE `utilizador`
  ADD PRIMARY KEY (`idUtilizador`),
  ADD KEY `Funcionario_funCodigo` (`Funcionario_funCodigo`);

--
-- Indexes for table `venda`
--
ALTER TABLE `venda`
  ADD PRIMARY KEY (`idVenda`);

--
-- Indexes for table `venda_compra`
--
ALTER TABLE `venda_compra`
  ADD PRIMARY KEY (`Funcionario_codigo`,`produto_codigo`,`cliente_codigo`,`venda_codigo`),
  ADD KEY `produto_codigo` (`produto_codigo`),
  ADD KEY `cliente_codigo` (`cliente_codigo`),
  ADD KEY `venda_codigo` (`venda_codigo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `empresa`
--
ALTER TABLE `empresa`
  MODIFY `idEmpresa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `funCodigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `historico_e_s`
--
ALTER TABLE `historico_e_s`
  MODIFY `idHistorico_E_S` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `morada`
--
ALTER TABLE `morada`
  MODIFY `idMorada` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `produtoitem`
--
ALTER TABLE `produtoitem`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tamanhos`
--
ALTER TABLE `tamanhos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT for table `utilizador`
--
ALTER TABLE `utilizador`
  MODIFY `idUtilizador` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `venda`
--
ALTER TABLE `venda`
  MODIFY `idVenda` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `historico_e_s`
--
ALTER TABLE `historico_e_s`
  ADD CONSTRAINT `historico_e_s_ibfk_1` FOREIGN KEY (`Funcionario_funCodigo`) REFERENCES `funcionario` (`funCodigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `morada`
--
ALTER TABLE `morada`
  ADD CONSTRAINT `morada_ibfk_1` FOREIGN KEY (`Funcionario_funCodigo`) REFERENCES `funcionario` (`funCodigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `produtoitem`
--
ALTER TABLE `produtoitem`
  ADD CONSTRAINT `produtoitem_ibfk_1` FOREIGN KEY (`FK_codigoVenda`) REFERENCES `venda` (`idVenda`),
  ADD CONSTRAINT `produtoitem_ibfk_2` FOREIGN KEY (`FK_codigoProduto`) REFERENCES `produto` (`codigo`);

--
-- Constraints for table `utilizador`
--
ALTER TABLE `utilizador`
  ADD CONSTRAINT `utilizador_ibfk_1` FOREIGN KEY (`Funcionario_funCodigo`) REFERENCES `funcionario` (`funCodigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
