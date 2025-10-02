-- --------------------------------------------------------
-- Host:                         10.0.0.10
-- Server version:               11.6.2-MariaDB-ubu2004 - mariadb.org binary distribution
-- Server OS:                    debian-linux-gnu
-- HeidiSQL Version:             12.10.0.7000
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for aa07_pr_voting
CREATE DATABASE IF NOT EXISTS `aa07_pr_voting` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `aa07_pr_voting`;

-- Dumping structure for table aa07_pr_voting.member_cache
CREATE TABLE IF NOT EXISTS `member_cache` (
  `fuid` int(11) NOT NULL,
  `username` text NOT NULL,
  `last_seen` datetime DEFAULT NULL,
  PRIMARY KEY (`fuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table aa07_pr_voting.notes
CREATE TABLE IF NOT EXISTS `notes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `noting_member_fuid` int(11) NOT NULL,
  `pr_number` int(11) NOT NULL,
  `note_text` text NOT NULL,
  `created` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `noting_member_fuid_FK1` (`noting_member_fuid`),
  KEY `pr_number_FK1` (`pr_number`),
  CONSTRAINT `noting_member_fuid_FK1` FOREIGN KEY (`noting_member_fuid`) REFERENCES `member_cache` (`fuid`),
  CONSTRAINT `pr_number_FK1` FOREIGN KEY (`pr_number`) REFERENCES `prs` (`pr_number`)
) ENGINE=InnoDB AUTO_INCREMENT=3045 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table aa07_pr_voting.prs
CREATE TABLE IF NOT EXISTS `prs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pr_number` int(11) NOT NULL,
  `pr_name` text NOT NULL,
  `pr_type` text NOT NULL,
  `date_opened` datetime NOT NULL,
  `pr_status` enum('OPEN','CLOSED','MERGED') NOT NULL DEFAULT 'OPEN',
  `pr_types` text NOT NULL DEFAULT '[]',
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `pr_number` (`pr_number`),
  KEY `pr_status` (`pr_status`)
) ENGINE=InnoDB AUTO_INCREMENT=11487 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table aa07_pr_voting.tm_requests
CREATE TABLE IF NOT EXISTS `tm_requests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `requesting_member_fuid` int(11) NOT NULL,
  `pr_number` int(11) NOT NULL,
  `created` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `pr_number_FK2` (`pr_number`),
  KEY `requesting_member_fuid_FK1` (`requesting_member_fuid`),
  CONSTRAINT `pr_number_FK2` FOREIGN KEY (`pr_number`) REFERENCES `prs` (`pr_number`),
  CONSTRAINT `requesting_member_fuid_FK1` FOREIGN KEY (`requesting_member_fuid`) REFERENCES `member_cache` (`fuid`)
) ENGINE=InnoDB AUTO_INCREMENT=865 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table aa07_pr_voting.votes
CREATE TABLE IF NOT EXISTS `votes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `voting_member_fuid` int(11) NOT NULL,
  `pr_number` int(11) NOT NULL,
  `voting_group` enum('HEAD','MAINT') NOT NULL,
  `vote_type` enum('APPROVE','OBJECT') NOT NULL,
  `created` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`id`) USING BTREE,
  KEY `pr_number_FK3` (`pr_number`) USING BTREE,
  KEY `voting_member_fuid_FK1` (`voting_member_fuid`) USING BTREE,
  CONSTRAINT `votes_ibfk_1` FOREIGN KEY (`pr_number`) REFERENCES `prs` (`pr_number`),
  CONSTRAINT `votes_ibfk_2` FOREIGN KEY (`voting_member_fuid`) REFERENCES `member_cache` (`fuid`)
) ENGINE=InnoDB AUTO_INCREMENT=516 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- Data exporting was unselected.

-- Dumping structure for table aa07_pr_voting.votes_new
CREATE TABLE IF NOT EXISTS `votes_new` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `voting_member_fuid` int(11) NOT NULL,
  `pr_number` int(11) NOT NULL,
  `voting_group` enum('LEGACY','DESIGN','BALANCE','VETO','SPRITE','MAP') NOT NULL,
  `vote_type` enum('APPROVE','OBJECT') NOT NULL,
  `created` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `enforce_unique_votes` (`voting_member_fuid`,`pr_number`,`voting_group`,`vote_type`),
  KEY `pr_number_FK3` (`pr_number`),
  CONSTRAINT `pr_number_FK3` FOREIGN KEY (`pr_number`) REFERENCES `prs` (`pr_number`),
  CONSTRAINT `voting_member_fuid_FK1` FOREIGN KEY (`voting_member_fuid`) REFERENCES `member_cache` (`fuid`)
) ENGINE=InnoDB AUTO_INCREMENT=12481 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
