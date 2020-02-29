-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 29-02-2020 a las 21:59:36
-- Versión del servidor: 5.7.24
-- Versión de PHP: 7.2.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `lumlist`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `passwd` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `admin`
--

INSERT INTO `admin` (`id`, `username`, `passwd`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `courses`
--

CREATE TABLE `courses` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `courses`
--

INSERT INTO `courses` (`id`, `name`) VALUES
(1544, 'DAW'),
(1545, 'DAM'),
(1546, 'ASIR');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `students`
--

CREATE TABLE `students` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `surname` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `passwd` varchar(100) NOT NULL,
  `birth_date` date NOT NULL,
  `available` tinyint(1) NOT NULL,
  `phone` int(12) NOT NULL,
  `email` varchar(100) NOT NULL,
  `observations` varchar(1000) DEFAULT NULL,
  `linkedin` varchar(150) DEFAULT NULL,
  `github` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `students`
--

INSERT INTO `students` (`id`, `name`, `surname`, `username`, `passwd`, `birth_date`, `available`, `phone`, `email`, `observations`, `linkedin`, `github`) VALUES
(15, 'Pedro', 'Lopez Martinez', 'pedro', 'c6cc8094c2dc07b700ffcc36d64e2138', '2020-02-29', 1, 0, 'email@email.com', NULL, NULL, NULL),
(16, 'Maria', 'Marin Garcia', 'maria', '263bce650e68ab4e23f28263760b9fa5', '2020-02-29', 1, 0, 'email@email.com', NULL, NULL, NULL),
(17, 'Julio', 'Sanchez Lopez', 'julio', 'c027636003b468821081e281758e35ff', '2020-02-29', 0, 0, 'email@email.com', NULL, NULL, NULL),
(18, 'Elena', 'Carrion Gutierrez ', 'elena', 'fadf17141f3f9c3389d10d09db99f757', '1999-06-16', 0, 0, 'email@email.com', NULL, NULL, 'http://www.google.es'),
(19, 'Juan Salvador', 'Vazquez Flores', 'juansa', '05012fd2370599d7e46441afc3bfe0e2', '2020-02-29', 0, 0, 'email@email.com', NULL, NULL, NULL),
(20, 'Marina', 'Diaz Torres', 'marina', 'ce5225d01c39d2567bc229501d9e610d', '2020-02-29', 1, 0, 'email@email.com', NULL, NULL, NULL),
(21, 'Arturo', 'Ortiz Morales', 'arturo', '65deafcf3c1ad1751415736c4cc11f76', '2020-02-29', 1, 0, 'email@email.com', NULL, NULL, NULL),
(22, 'Marcos', 'Vera Ramos', 'marcos', 'c5e3539121c4944f2bbe097b425ee774', '2020-02-29', 0, 0, 'email@email.com', NULL, NULL, NULL),
(23, 'Angel', 'Salas Calvo', 'angelsalas', '1e8a4f5451abbcac73cbc0a8bf5090c8', '1999-05-27', 1, 611459667, 'angelsalascalvo@gmail.com', NULL, 'https://www.google.es', 'https://github.com/angelsalascalvo'),
(24, 'Alba', 'Navarro Ramos', 'alba', '74efb8aac68e37c289dfcf260e19ab25', '2020-02-29', 1, 0, 'email@email.com', NULL, NULL, NULL),
(25, 'Lucia', 'Martinez Arias', 'lucia', '3ba430337eb30f5fd7569451b5dfdf32', '2020-02-29', 1, 0, 'email@email.com', NULL, NULL, NULL),
(28, 'Pedro', 'Molina Perez', 'pedromolina', '782b0e9cee70a33d617257a1e2c19675', '2020-02-29', 1, 0, 'email@email.com', NULL, NULL, NULL),
(29, 'Almudena', 'Fernandez Rojas', 'almudena', '89b838fb224de539b8d4cc073e881e97', '2020-02-29', 1, 0, 'email@email.com', NULL, NULL, NULL),
(30, 'Rosa', 'Perez Fernandez', 'rosa', '84109ae4b4178430b8174b1dfef9162b', '2020-02-29', 1, 0, 'email@email.com', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `student_course`
--

CREATE TABLE `student_course` (
  `id_course` int(11) NOT NULL,
  `id_student` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `student_course`
--

INSERT INTO `student_course` (`id_course`, `id_student`) VALUES
(1544, 15),
(1545, 15),
(1545, 16),
(1544, 17),
(1544, 18),
(1545, 19),
(1546, 20),
(1546, 21),
(1546, 22),
(1544, 23),
(1545, 23),
(1545, 24),
(1545, 25),
(1546, 25),
(1545, 28),
(1545, 29);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indices de la tabla `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indices de la tabla `student_course`
--
ALTER TABLE `student_course`
  ADD PRIMARY KEY (`id_course`,`id_student`),
  ADD KEY `id_student` (`id_student`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `courses`
--
ALTER TABLE `courses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1547;

--
-- AUTO_INCREMENT de la tabla `students`
--
ALTER TABLE `students`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `student_course`
--
ALTER TABLE `student_course`
  ADD CONSTRAINT `student_course_ibfk_1` FOREIGN KEY (`id_course`) REFERENCES `courses` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `student_course_ibfk_2` FOREIGN KEY (`id_student`) REFERENCES `students` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
