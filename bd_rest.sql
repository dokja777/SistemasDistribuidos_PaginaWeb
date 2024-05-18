-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-05-2024 a las 20:43:03
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_rest`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `t_cliente`
--

CREATE TABLE `t_cliente` (
  `Id_Cliente` varchar(6) NOT NULL,
  `Apellidos` varchar(50) NOT NULL,
  `Nombres` varchar(50) NOT NULL,
  `Direccion` varchar(100) DEFAULT NULL,
  `DNI` varchar(8) NOT NULL,
  `Telefono` varchar(10) DEFAULT NULL,
  `Movil` varchar(9) DEFAULT NULL,
  `estado` varchar(11) NOT NULL,
  `En linea` tinyint(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `t_cliente`
--

INSERT INTO `t_cliente` (`Id_Cliente`, `Apellidos`, `Nombres`, `Direccion`, `DNI`, `Telefono`, `Movil`, `estado`, `En linea`) VALUES
('C00001', 'GARCIA ANDONAIRE', 'JAVIER DAVID', 'AV.LOS ALAMOS 1234', '12345678', '3211234', '987654321', 'activo', 0),
('C00002', 'TREJO', 'BARRIENTOS', 'AV.LOS ALAMOS N°456', '45657524', '982330012', '45657524', 'inactivo', 0),
('C00003', 'ZAMUDIO', 'DE LA CRUZ', 'AV.BAYOVAR N°123', '10550273', '989046299', '43434343', '', 0),
('C00004', 'IZAGUIRRE', 'HUILLCA', 'CL ALCANFORES N° 911', '87654321', '3211298', '957647208', '', 0),
('C00005', 'ONTIVEROS', 'JAMES', 'LOS CABITOS', '98765432', '2587876', '999999999', '', 0),
('C00006', 'CRISTOPHER', 'QUISPE', 'AV LO FLORESTA N°400', '76376451', '12345677', '968218168', '', 0),
('C00007', 'PALMADERA', 'MANUEL', 'AV. Bolivar N°11', '12345678', '7521154', '964314264', '', 0),
('C00008', 'LORENZO', 'WILDER', 'Av. elias aguirre nÂ° 100', '12344320', '999888777', '87654321', '', 0),
('C00009', 'VARGAS', 'CHAVEZ', 'CALLE MANTER 420', '4250000', '73307686', '992878337', '', 0),
('C00010', 'SANCHEZ', 'FERNANDEZ PRADA', 'AV DEL EJERCITO N° 1746 ', '41810916', '34554398', '988455739', '', 0),
('C00012', 'GARCIA', 'SANCHEZ', 'AV.LOS ALAMOS 1222', '12211221', '2673421', '997665443', '', 0),
('C00013', 'SANCHEZ', 'MEDRANO', 'AV.LOS ALAMOS NÂ°123', '80982312', '4327656', '998777123', '', 0),
('C00014', 'URBINA', 'SALAS', 'AV.LA CALAMEÃ?A 321', '12121313', '2123438', '991232123', '', 0),
('C00015', 'TERRONES', 'LILIA', 'AV.LOS HEROES 121', '12344567', '78908767', '995456323', '', 0),
('C00016', 'QUESQUEN URIARTE', 'FRANCISCO', 'AV.LOS ALAMOS NÃ?Â°123', '80121315', '3245623', '999888765', '', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `t_detalle_pedido`
--

CREATE TABLE `t_detalle_pedido` (
  `Id_Pedido` varchar(6) NOT NULL,
  `Id_Prod` varchar(6) NOT NULL,
  `cantidad` double(8,2) DEFAULT NULL,
  `precio` double(8,2) DEFAULT NULL,
  `TotalDeta` double(8,2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `t_detalle_pedido`
--

INSERT INTO `t_detalle_pedido` (`Id_Pedido`, `Id_Prod`, `cantidad`, `precio`, `TotalDeta`) VALUES
('P0001', 'P00003', 2.00, 12.50, 25.00),
('P0001', 'P00002', 2.00, 12.50, 25.00),
('P0002', 'P00002', 5.00, 20.00, 100.00),
('P0002', 'P00003', 2.00, 10.00, 20.00),
('P0002', 'P00004', 10.00, 15.00, 150.00),
('P0003', 'P00004', 5.00, 20.00, 100.00),
('P0002', 'P00005', 3.00, 40.00, 120.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `t_pedido`
--

CREATE TABLE `t_pedido` (
  `Id_Pedido` varchar(6) NOT NULL,
  `Id_Cliente` varchar(6) NOT NULL,
  `Fecha` date NOT NULL,
  `SubTotal` double(8,2) DEFAULT NULL,
  `TotalVenta` double(8,2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `t_pedido`
--

INSERT INTO `t_pedido` (`Id_Pedido`, `Id_Cliente`, `Fecha`, `SubTotal`, `TotalVenta`) VALUES
('P0001', 'C00001', '2021-11-14', 234.24, 276.40),
('P0002', 'C00002', '2021-11-14', 234.24, 276.40),
('P0003', 'C00004', '2021-11-14', 234.24, 276.40),
('P0004', 'C00001', '2021-11-14', 234.24, 276.40);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `t_producto`
--

CREATE TABLE `t_producto` (
  `Id_Prod` varchar(6) NOT NULL,
  `Descripcion` varchar(50) NOT NULL,
  `costo` double(8,2) DEFAULT NULL,
  `precio` double(8,2) DEFAULT NULL,
  `cantidad` double(8,2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `t_producto`
--

INSERT INTO `t_producto` (`Id_Prod`, `Descripcion`, `costo`, `precio`, `cantidad`) VALUES
('P00002', 'AZUCAR', 134.00, 134.00, 5.50),
('P00003', 'ARROZ', 200.00, 200.00, 2.50),
('P00004', 'LECHE NESTLE', 200.00, 200.00, 4.50),
('P00005', 'ACEITE', 150.00, 150.00, 3.50);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `t_usuario`
--

CREATE TABLE `t_usuario` (
  `IdUsuario` varchar(50) NOT NULL,
  `Passwd` varchar(50) NOT NULL,
  `En linea` tinyint(1) NOT NULL,
  `Estado` varchar(10) NOT NULL DEFAULT 'activo'
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `t_usuario`
--

INSERT INTO `t_usuario` (`IdUsuario`, `Passwd`, `En linea`, `Estado`) VALUES
('AJGARCIA', '12345678', 0, 'activo'),
('UJPEREZ', 'HolaMundo', 0, 'activo'),
('U123', '123', 0, 'inactivo'),
('ADaniel', '123', 0, 'activo');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `t_cliente`
--
ALTER TABLE `t_cliente`
  ADD PRIMARY KEY (`Id_Cliente`);

--
-- Indices de la tabla `t_detalle_pedido`
--
ALTER TABLE `t_detalle_pedido`
  ADD PRIMARY KEY (`Id_Pedido`,`Id_Prod`),
  ADD KEY `Id_Prod` (`Id_Prod`);

--
-- Indices de la tabla `t_pedido`
--
ALTER TABLE `t_pedido`
  ADD PRIMARY KEY (`Id_Pedido`,`Id_Cliente`),
  ADD KEY `Id_Cliente` (`Id_Cliente`);

--
-- Indices de la tabla `t_producto`
--
ALTER TABLE `t_producto`
  ADD PRIMARY KEY (`Id_Prod`);

--
-- Indices de la tabla `t_usuario`
--
ALTER TABLE `t_usuario`
  ADD PRIMARY KEY (`IdUsuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
