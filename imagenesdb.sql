-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-05-2019 a las 01:31:57
-- Versión del servidor: 10.1.38-MariaDB
-- Versión de PHP: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `imagenesdb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `imagen`
--

CREATE TABLE `imagen` (
  `idimagen` int(10) NOT NULL,
  `nombre` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  `url` varchar(150) COLLATE latin1_spanish_ci NOT NULL,
  `fechadescarga` date DEFAULT NULL,
  `idcliente` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `imagen`
--

INSERT INTO `imagen` (`idimagen`, `nombre`, `url`, `fechadescarga`, `idcliente`) VALUES
(5, 'TLOZ', 'https://i.pinimg.com/originals/76/a9/67/76a967156646cf649d37a9a5f9a36acf.jpg', NULL, NULL),
(6, 'TheWitcher3', 'http://assets.vg247.com/current//2015/05/the_witcher_3_wild_hunt_guide_walkthrough.jpg', NULL, NULL),
(7, 'Skyrim', 'https://i.blogs.es/14a384/1366_2000/1366_2000.jpg', NULL, NULL),
(8, 'LeagueOfLegends', 'https://i2.sdpnoticias.com/sdpnoticias/2018/07/27/1920_league-of-legends_620x350.jpg', NULL, NULL),
(9, 'WorldOfWarcraft', 'https://dlcompare-images.s3.eu-west-3.amazonaws.com/game_tetiere/upload/gameimage/file/37199.jpeg', NULL, NULL),
(10, 'Diablo', 'https://blznav.akamaized.net/img/games/cards/card-diablo-immortal-27ce8fcd012c5f03.jpg', NULL, NULL),
(11, 'Overwatch', 'https://blznav.akamaized.net/img/games/cards/card-overwatch-7eff92e1257149aa.jpg', NULL, NULL),
(12, 'Stellaris', 'https://hb.imgix.net/7797384a66a57b3baea9fb335092a4fbd49301bd.jpg?auto=compress,format&fit=crop&h=353&w=616&s=fb0ea697fa8e5c30180d5f76665de12c', NULL, NULL),
(13, 'Warhammer40K', 'https://hb.imgix.net/6eb2e0a29a89e9a288f1bd73b969124332ac8726.jpg?auto=compress,format&fit=crop&h=353&w=616&s=bb4cd6f47af159dabc16ad8a42b8a90e', NULL, NULL),
(14, 'DeadByDaylight', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSQHfkZ7sio-Kv9jqStuB2p0mV1mxm2bg6o1TI_ZHzki0rn8bnEDQ', NULL, NULL),
(15, 'Starcraft2', 'https://www.jinx.com/productimage/3855/23/1/900.jpg', NULL, NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `imagen`
--
ALTER TABLE `imagen`
  ADD PRIMARY KEY (`idimagen`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `imagen`
--
ALTER TABLE `imagen`
  MODIFY `idimagen` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
