-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 28, 2022 at 07:57 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `listaleitura`
--

-- --------------------------------------------------------

--
-- Table structure for table `livros`
--

CREATE TABLE `livros` (
  `id` bigint(20) NOT NULL,
  `capa` varchar(255) NOT NULL,
  `completo` bit(1) NOT NULL,
  `genero_principal` varchar(255) NOT NULL,
  `genero_secundario` varchar(255) NOT NULL,
  `paginas_lidas` int(11) NOT NULL,
  `paginas_totais` int(11) NOT NULL,
  `rating` float NOT NULL,
  `sinopse` longtext NOT NULL,
  `sub_titulo` varchar(255) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `usuario_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `livros`
--

INSERT INTO `livros` (`id`, `capa`, `completo`, `genero_principal`, `genero_secundario`, `paginas_lidas`, `paginas_totais`, `rating`, `sinopse`, `sub_titulo`, `titulo`, `usuario_id`) VALUES
(2, 'https://i.pinimg.com/564x/c1/6d/67/c16d6708317e711c3c960e79626397a6.jpg', b'1', 'Aventura', '', 336, 336, 5, 'There is a world behind the canvas. Past the flat façade and the crackling paint is a realm where art lives, breathes, creates, and destroys.\n\nClaudia Miravista loves art but only sees what is on the surface--until the Dutch boy Pim appears in the painting in her room. Pim has been trapped in the world behind the canvas for centuries by a power-hungry witch, and he now believes that Claudia is his only hope for escape. Fueled by the help of an ancient artist and some microwaveable magic, Claudia enters the wondrous and terrifying world behind the canvas, intent on destroying the witch\'s most cherished possession and setting her new friend free. But in that world nothing is quite as it appears on the surface. Not even friendship.\n\nRevel in the mystery and adventure of an alternate world where famous paintings come to life in Behind the Canvas by Alexander Vance!', '', 'Behind the Canvas', 1),
(4, 'https://i.pinimg.com/564x/4a/91/0b/4a910b1418d5f228bfb361ee7be1db7f.jpg', b'0', 'Fantasia', '', 20, 368, 0, 'The Black Book granted them that power. Harnessing that power feels good, especially when everything in their lives makes them feel powerless.\n\nDuring a spell gone wrong, Liss\'s boyfriend is snatched away by an evil entity and presumed dead. Dan and Liss\'s friendship dies that night, too. How can they practice magic after the darkness that they conjured?\n\nMonths later, Liss discovers that her boyfriend is alive, trapped underground in the grips of an ancient force. She must save him, and she needs Dan and the power of The Black Book to do so. Dan is quickly sucked back into Liss\'s orbit and pushes away her best friend, Alexa. But Alexa has some big secrets she\'s hiding and her own unique magical disaster to deal with.\n\nWhen another teenager disappears, the girls know it\'s no coincidence. What greedy magic have they awakened? And what does it want with these teens it has stolen?\n\nSet in the atmospheric wilds of California\'s northern coast, Sasha Laurens\'s thrilling debut novel is about the complications of friendship, how to take back power, and how to embrace the darkness that lives within us all.', '', 'A Wicked Magic', 1);

-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

CREATE TABLE `usuarios` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` (`id`, `email`, `senha`, `nome`) VALUES
(1, 'pedro@gmail.com', '123', 'PedroH'),
(2, 'lucas', '123', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `livros`
--
ALTER TABLE `livros`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `livros`
--
ALTER TABLE `livros`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
