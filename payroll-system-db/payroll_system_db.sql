-- --------------------------------------------------------
-- Host:                         192.168.1.5
-- Server version:               5.1.59-community - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2013-02-01 15:59:11
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for payroll_system_db
DROP DATABASE IF EXISTS `payroll_system_db`;
CREATE DATABASE IF NOT EXISTS `payroll_system_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `payroll_system_db`;


-- Dumping structure for table payroll_system_db.ach_detail
DROP TABLE IF EXISTS `ach_detail`;
CREATE TABLE IF NOT EXISTS `ach_detail` (
  `id` bigint(20) NOT NULL,
  `ach_tx_no` varchar(45) NOT NULL,
  `amount` decimal(30,0) NOT NULL,
  `payment_status` int(11) DEFAULT NULL,
  `status_description` varchar(255) DEFAULT NULL,
  `tx_date` date NOT NULL,
  `payment_mode_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK6B9F1A2A9FB6FBBD` (`payment_mode_id`),
  CONSTRAINT `FK6B9F1A2A9FB6FBBD` FOREIGN KEY (`payment_mode_id`) REFERENCES `payment_mode` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.ach_detail: ~0 rows (approximately)
/*!40000 ALTER TABLE `ach_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `ach_detail` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.address
DROP TABLE IF EXISTS `address`;
CREATE TABLE IF NOT EXISTS `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(20) NOT NULL,
  `country` varchar(20) DEFAULT NULL,
  `county_name` varchar(45) NOT NULL,
  `landmark` varchar(50) DEFAULT NULL,
  `pin_zip` varchar(10) NOT NULL,
  `street_address` varchar(100) DEFAULT NULL,
  `address_type_id` bigint(20) NOT NULL,
  `us_city_id` bigint(20) NOT NULL,
  `us_county_id` bigint(20) NOT NULL,
  `us_state_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKBB979BF41FD55A93` (`us_state_id`),
  KEY `FKBB979BF4D270C441` (`us_city_id`),
  KEY `FKBB979BF45619DF39` (`address_type_id`),
  KEY `FKBB979BF48C3CC621` (`us_county_id`),
  CONSTRAINT `FKBB979BF48C3CC621` FOREIGN KEY (`us_county_id`) REFERENCES `us_county` (`id`),
  CONSTRAINT `FKBB979BF41FD55A93` FOREIGN KEY (`us_state_id`) REFERENCES `us_state` (`id`),
  CONSTRAINT `FKBB979BF45619DF39` FOREIGN KEY (`address_type_id`) REFERENCES `address_type` (`id`),
  CONSTRAINT `FKBB979BF4D270C441` FOREIGN KEY (`us_city_id`) REFERENCES `us_city` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.address: ~0 rows (approximately)
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.address_type
DROP TABLE IF EXISTS `address_type`;
CREATE TABLE IF NOT EXISTS `address_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` longtext,
  `type` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.address_type: ~6 rows (approximately)
/*!40000 ALTER TABLE `address_type` DISABLE KEYS */;
INSERT INTO `address_type` (`id`, `description`, `type`) VALUES
	(1, NULL, 'Shipping'),
	(2, NULL, 'Business'),
	(3, NULL, 'Legal'),
	(4, NULL, 'Normal'),
	(5, NULL, 'Work'),
	(6, NULL, 'Personal');
/*!40000 ALTER TABLE `address_type` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.allowance_rate
DROP TABLE IF EXISTS `allowance_rate`;
CREATE TABLE IF NOT EXISTS `allowance_rate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` decimal(30,0) DEFAULT NULL,
  `date_of_entry` datetime DEFAULT NULL,
  `release_date` datetime DEFAULT NULL,
  `wef` datetime DEFAULT NULL,
  `allowance_type_id` bigint(20) NOT NULL,
  `us_state_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK8810BCA71FD55A93` (`us_state_id`),
  KEY `FK8810BCA780E1DC01` (`allowance_type_id`),
  CONSTRAINT `FK8810BCA780E1DC01` FOREIGN KEY (`allowance_type_id`) REFERENCES `allowance_type` (`id`),
  CONSTRAINT `FK8810BCA71FD55A93` FOREIGN KEY (`us_state_id`) REFERENCES `us_state` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.allowance_rate: ~0 rows (approximately)
/*!40000 ALTER TABLE `allowance_rate` DISABLE KEYS */;
/*!40000 ALTER TABLE `allowance_rate` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.allowance_type
DROP TABLE IF EXISTS `allowance_type`;
CREATE TABLE IF NOT EXISTS `allowance_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.allowance_type: ~2 rows (approximately)
/*!40000 ALTER TABLE `allowance_type` DISABLE KEYS */;
INSERT INTO `allowance_type` (`id`, `description`, `type`) VALUES
	(1, NULL, 'Personal'),
	(2, NULL, 'Dependent');
/*!40000 ALTER TABLE `allowance_type` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.check_detail
DROP TABLE IF EXISTS `check_detail`;
CREATE TABLE IF NOT EXISTS `check_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `check_amount` decimal(30,0) NOT NULL,
  `check_clearing_date` varchar(45) DEFAULT NULL,
  `check_date` date NOT NULL,
  `check_number` varchar(45) NOT NULL,
  `check_status` int(11) DEFAULT NULL,
  `check_void_date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.check_detail: ~0 rows (approximately)
/*!40000 ALTER TABLE `check_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `check_detail` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.company
DROP TABLE IF EXISTS `company`;
CREATE TABLE IF NOT EXISTS `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `business_fax` varchar(20) DEFAULT NULL,
  `business_name` varchar(45) DEFAULT NULL,
  `business_phone` varchar(20) DEFAULT NULL,
  `client_id` varchar(10) DEFAULT NULL,
  `company_name_on_check` varchar(35) DEFAULT NULL,
  `fein` varchar(25) DEFAULT NULL,
  `legal_name` varchar(45) NOT NULL,
  `status` int(11) NOT NULL,
  `web_address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.company: ~0 rows (approximately)
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
/*!40000 ALTER TABLE `company` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.company_address
DROP TABLE IF EXISTS `company_address`;
CREATE TABLE IF NOT EXISTS `company_address` (
  `company_id` bigint(20) NOT NULL,
  `addresses_id` bigint(20) NOT NULL,
  PRIMARY KEY (`company_id`,`addresses_id`),
  UNIQUE KEY `addresses_id` (`addresses_id`),
  KEY `FK593CB232123D1A90` (`company_id`),
  KEY `FK593CB232D7C8ACC2` (`addresses_id`),
  CONSTRAINT `FK593CB232D7C8ACC2` FOREIGN KEY (`addresses_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FK593CB232123D1A90` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.company_address: ~0 rows (approximately)
/*!40000 ALTER TABLE `company_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_address` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.company_bank_info
DROP TABLE IF EXISTS `company_bank_info`;
CREATE TABLE IF NOT EXISTS `company_bank_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_number` varchar(15) NOT NULL,
  `account_type` int(11) NOT NULL,
  `bank_name` varchar(45) NOT NULL,
  `routing_number` varchar(15) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `company_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `account_number` (`account_number`),
  UNIQUE KEY `routing_number` (`routing_number`),
  KEY `FK5E2D1B8F123D1A90` (`company_id`),
  CONSTRAINT `FK5E2D1B8F123D1A90` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.company_bank_info: ~0 rows (approximately)
/*!40000 ALTER TABLE `company_bank_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_bank_info` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.company_deduction
DROP TABLE IF EXISTS `company_deduction`;
CREATE TABLE IF NOT EXISTS `company_deduction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `display_name` varchar(45) DEFAULT NULL,
  `company_id` bigint(20) NOT NULL,
  `deduction_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK268A1F23123D1A90` (`company_id`),
  KEY `FK268A1F23A1AA0C10` (`deduction_id`),
  CONSTRAINT `FK268A1F23A1AA0C10` FOREIGN KEY (`deduction_id`) REFERENCES `deduction` (`id`),
  CONSTRAINT `FK268A1F23123D1A90` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.company_deduction: ~0 rows (approximately)
/*!40000 ALTER TABLE `company_deduction` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_deduction` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.company_department
DROP TABLE IF EXISTS `company_department`;
CREATE TABLE IF NOT EXISTS `company_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dept_id` varchar(20) NOT NULL,
  `dept_name` varchar(45) NOT NULL,
  `description` longtext,
  `status` int(11) DEFAULT NULL,
  `company_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK80E59C54123D1A90` (`company_id`),
  CONSTRAINT `FK80E59C54123D1A90` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.company_department: ~0 rows (approximately)
/*!40000 ALTER TABLE `company_department` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_department` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.company_earning
DROP TABLE IF EXISTS `company_earning`;
CREATE TABLE IF NOT EXISTS `company_earning` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `display_name` varchar(45) DEFAULT NULL,
  `company_id` bigint(20) NOT NULL,
  `earning_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK287A9B48123D1A90` (`company_id`),
  KEY `FK287A9B48DC783370` (`earning_id`),
  CONSTRAINT `FK287A9B48DC783370` FOREIGN KEY (`earning_id`) REFERENCES `earning` (`id`),
  CONSTRAINT `FK287A9B48123D1A90` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.company_earning: ~0 rows (approximately)
/*!40000 ALTER TABLE `company_earning` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_earning` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.company_employee_section_map
DROP TABLE IF EXISTS `company_employee_section_map`;
CREATE TABLE IF NOT EXISTS `company_employee_section_map` (
  `employee_section_id` bigint(20) NOT NULL,
  `company_id` bigint(20) NOT NULL,
  PRIMARY KEY (`company_id`,`employee_section_id`),
  KEY `FKAFC28773123D1A90` (`company_id`),
  KEY `FKAFC28773730C5333` (`employee_section_id`),
  CONSTRAINT `FKAFC28773730C5333` FOREIGN KEY (`employee_section_id`) REFERENCES `employee_section` (`id`),
  CONSTRAINT `FKAFC28773123D1A90` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.company_employee_section_map: ~0 rows (approximately)
/*!40000 ALTER TABLE `company_employee_section_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_employee_section_map` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.company_feature
DROP TABLE IF EXISTS `company_feature`;
CREATE TABLE IF NOT EXISTS `company_feature` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activated_on` datetime DEFAULT NULL,
  `deactivated_on` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `company_id` bigint(20) NOT NULL,
  `plan_feature_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK63479134123D1A90` (`company_id`),
  KEY `FK63479134575B45D` (`plan_feature_id`),
  CONSTRAINT `FK63479134575B45D` FOREIGN KEY (`plan_feature_id`) REFERENCES `plan_feature` (`id`),
  CONSTRAINT `FK63479134123D1A90` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.company_feature: ~0 rows (approximately)
/*!40000 ALTER TABLE `company_feature` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_feature` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.company_otp
DROP TABLE IF EXISTS `company_otp`;
CREATE TABLE IF NOT EXISTS `company_otp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `generated_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `otp_code` varchar(10) NOT NULL,
  `company_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKE1B79469D007852B` (`company_user_id`),
  CONSTRAINT `FKE1B79469D007852B` FOREIGN KEY (`company_user_id`) REFERENCES `company_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.company_otp: ~0 rows (approximately)
/*!40000 ALTER TABLE `company_otp` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_otp` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.company_paid_benefit
DROP TABLE IF EXISTS `company_paid_benefit`;
CREATE TABLE IF NOT EXISTS `company_paid_benefit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accrual_mode` int(11) DEFAULT NULL,
  `accrual_rate` float DEFAULT NULL,
  `accrue_hours_on_leave` bit(1) DEFAULT NULL,
  `no_of_hours` float DEFAULT NULL,
  `rollover_hours` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `company_id` bigint(20) NOT NULL,
  `company_earning_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK7A6952A6123D1A90` (`company_id`),
  KEY `FK7A6952A61872489` (`company_earning_id`),
  CONSTRAINT `FK7A6952A61872489` FOREIGN KEY (`company_earning_id`) REFERENCES `company_earning` (`id`),
  CONSTRAINT `FK7A6952A6123D1A90` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.company_paid_benefit: ~0 rows (approximately)
/*!40000 ALTER TABLE `company_paid_benefit` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_paid_benefit` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.company_payroll_deduction
DROP TABLE IF EXISTS `company_payroll_deduction`;
CREATE TABLE IF NOT EXISTS `company_payroll_deduction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `value` decimal(30,0) NOT NULL,
  `company_deduction_id` bigint(20) NOT NULL,
  `employee_payroll_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK9983AE892E6CAB33` (`employee_payroll_id`),
  KEY `FK9983AE89BEC11AE9` (`company_deduction_id`),
  CONSTRAINT `FK9983AE89BEC11AE9` FOREIGN KEY (`company_deduction_id`) REFERENCES `company_deduction` (`id`),
  CONSTRAINT `FK9983AE892E6CAB33` FOREIGN KEY (`employee_payroll_id`) REFERENCES `employee_payroll` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.company_payroll_deduction: ~0 rows (approximately)
/*!40000 ALTER TABLE `company_payroll_deduction` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_payroll_deduction` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.company_payroll_tax
DROP TABLE IF EXISTS `company_payroll_tax`;
CREATE TABLE IF NOT EXISTS `company_payroll_tax` (
  `id` bigint(20) NOT NULL,
  `tax_calculated` decimal(30,0) DEFAULT NULL,
  `company_tax_id` bigint(20) NOT NULL,
  `employee_payroll_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK355238CF1CD1F469` (`company_tax_id`),
  KEY `FK355238CF2E6CAB33` (`employee_payroll_id`),
  CONSTRAINT `FK355238CF2E6CAB33` FOREIGN KEY (`employee_payroll_id`) REFERENCES `employee_payroll` (`id`),
  CONSTRAINT `FK355238CF1CD1F469` FOREIGN KEY (`company_tax_id`) REFERENCES `company_tax` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.company_payroll_tax: ~0 rows (approximately)
/*!40000 ALTER TABLE `company_payroll_tax` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_payroll_tax` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.company_payroll_worker_comp
DROP TABLE IF EXISTS `company_payroll_worker_comp`;
CREATE TABLE IF NOT EXISTS `company_payroll_worker_comp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `value` decimal(30,0) NOT NULL,
  `company_worker_compensation_id` bigint(20) NOT NULL,
  `employee_payroll_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKEBA8624C2E6CAB33` (`employee_payroll_id`),
  KEY `FKEBA8624C89DFC12C` (`company_worker_compensation_id`),
  CONSTRAINT `FKEBA8624C89DFC12C` FOREIGN KEY (`company_worker_compensation_id`) REFERENCES `company_worker_compensation` (`id`),
  CONSTRAINT `FKEBA8624C2E6CAB33` FOREIGN KEY (`employee_payroll_id`) REFERENCES `employee_payroll` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.company_payroll_worker_comp: ~0 rows (approximately)
/*!40000 ALTER TABLE `company_payroll_worker_comp` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_payroll_worker_comp` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.company_person
DROP TABLE IF EXISTS `company_person`;
CREATE TABLE IF NOT EXISTS `company_person` (
  `company_id` bigint(20) NOT NULL,
  `contacts_id` bigint(20) NOT NULL,
  PRIMARY KEY (`company_id`,`contacts_id`),
  UNIQUE KEY `contacts_id` (`contacts_id`),
  KEY `FKF343C557123D1A90` (`company_id`),
  KEY `FKF343C557D0077B26` (`contacts_id`),
  CONSTRAINT `FKF343C557D0077B26` FOREIGN KEY (`contacts_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FKF343C557123D1A90` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.company_person: ~0 rows (approximately)
/*!40000 ALTER TABLE `company_person` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_person` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.company_role
DROP TABLE IF EXISTS `company_role`;
CREATE TABLE IF NOT EXISTS `company_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_description` longtext,
  `role_name` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.company_role: ~0 rows (approximately)
/*!40000 ALTER TABLE `company_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_role` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.company_role_permission
DROP TABLE IF EXISTS `company_role_permission`;
CREATE TABLE IF NOT EXISTS `company_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission_type` int(11) NOT NULL,
  `company_id` bigint(20) NOT NULL,
  `company_role_id` bigint(20) NOT NULL,
  `payroll_section_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKB002CD76927F4B59` (`payroll_section_id`),
  KEY `FKB002CD76123D1A90` (`company_id`),
  KEY `FKB002CD762ADCC14B` (`company_role_id`),
  CONSTRAINT `FKB002CD762ADCC14B` FOREIGN KEY (`company_role_id`) REFERENCES `company_role` (`id`),
  CONSTRAINT `FKB002CD76123D1A90` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FKB002CD76927F4B59` FOREIGN KEY (`payroll_section_id`) REFERENCES `payroll_section` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.company_role_permission: ~0 rows (approximately)
/*!40000 ALTER TABLE `company_role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_role_permission` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.company_security_question
DROP TABLE IF EXISTS `company_security_question`;
CREATE TABLE IF NOT EXISTS `company_security_question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `answer` varchar(100) NOT NULL,
  `company_user_id` bigint(20) NOT NULL,
  `security_question_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK8BABFEC3D007852B` (`company_user_id`),
  KEY `FK8BABFEC3A4E9AFA5` (`security_question_id`),
  CONSTRAINT `FK8BABFEC3A4E9AFA5` FOREIGN KEY (`security_question_id`) REFERENCES `security_question` (`id`),
  CONSTRAINT `FK8BABFEC3D007852B` FOREIGN KEY (`company_user_id`) REFERENCES `company_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.company_security_question: ~0 rows (approximately)
/*!40000 ALTER TABLE `company_security_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_security_question` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.company_tax
DROP TABLE IF EXISTS `company_tax`;
CREATE TABLE IF NOT EXISTS `company_tax` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ein` varchar(45) NOT NULL,
  `exempted` bit(1) NOT NULL,
  `company_id` bigint(20) NOT NULL,
  `tax_deposit_cycle_id` bigint(20) NOT NULL,
  `tax_type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKE1B7A4E9123D1A90` (`company_id`),
  KEY `FKE1B7A4E991F7B727` (`tax_type_id`),
  KEY `FKE1B7A4E9723491D2` (`tax_deposit_cycle_id`),
  CONSTRAINT `FKE1B7A4E9723491D2` FOREIGN KEY (`tax_deposit_cycle_id`) REFERENCES `tax_deposit_cycle` (`id`),
  CONSTRAINT `FKE1B7A4E9123D1A90` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FKE1B7A4E991F7B727` FOREIGN KEY (`tax_type_id`) REFERENCES `tax_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.company_tax: ~0 rows (approximately)
/*!40000 ALTER TABLE `company_tax` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_tax` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.company_transaction
DROP TABLE IF EXISTS `company_transaction`;
CREATE TABLE IF NOT EXISTS `company_transaction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `transaction_type` varchar(45) DEFAULT NULL,
  `ach_detail_id` bigint(20) NOT NULL,
  `check_detail_id` bigint(20) NOT NULL,
  `company_id` bigint(20) NOT NULL,
  `fund_category_id` bigint(20) NOT NULL,
  `payment_mode_id` bigint(20) NOT NULL,
  `payroll_id` bigint(20) NOT NULL,
  `payroll_company_id` bigint(20) NOT NULL,
  `wire_transfer_detail_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKFC349A1C3C03D02F` (`fund_category_id`),
  KEY `FKFC349A1C123D1A90` (`company_id`),
  KEY `FKFC349A1C94F4459` (`payroll_company_id`),
  KEY `FKFC349A1C56CD7990` (`payroll_id`),
  KEY `FKFC349A1C9FB6FBBD` (`payment_mode_id`),
  KEY `FKFC349A1CCF9F603D` (`ach_detail_id`),
  KEY `FKFC349A1C67F42EBA` (`wire_transfer_detail_id`),
  KEY `FKFC349A1C6F6ECFC1` (`check_detail_id`),
  CONSTRAINT `FKFC349A1C6F6ECFC1` FOREIGN KEY (`check_detail_id`) REFERENCES `check_detail` (`id`),
  CONSTRAINT `FKFC349A1C123D1A90` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FKFC349A1C3C03D02F` FOREIGN KEY (`fund_category_id`) REFERENCES `fund_category` (`id`),
  CONSTRAINT `FKFC349A1C56CD7990` FOREIGN KEY (`payroll_id`) REFERENCES `payroll` (`id`),
  CONSTRAINT `FKFC349A1C67F42EBA` FOREIGN KEY (`wire_transfer_detail_id`) REFERENCES `wire_transfer_detail` (`id`),
  CONSTRAINT `FKFC349A1C94F4459` FOREIGN KEY (`payroll_company_id`) REFERENCES `payroll_company` (`id`),
  CONSTRAINT `FKFC349A1C9FB6FBBD` FOREIGN KEY (`payment_mode_id`) REFERENCES `payment_mode` (`id`),
  CONSTRAINT `FKFC349A1CCF9F603D` FOREIGN KEY (`ach_detail_id`) REFERENCES `ach_detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.company_transaction: ~0 rows (approximately)
/*!40000 ALTER TABLE `company_transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_transaction` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.company_user
DROP TABLE IF EXISTS `company_user`;
CREATE TABLE IF NOT EXISTS `company_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `firm_name` varchar(45) DEFAULT NULL,
  `forget_process_initiated` bit(1) DEFAULT NULL,
  `mobile_access` bit(1) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `security_pin` varchar(10) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_name` varchar(45) NOT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `company_id` bigint(20) NOT NULL,
  `company_role_id` bigint(20) NOT NULL,
  `person_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK553DAE4D123D1A90` (`company_id`),
  KEY `FK553DAE4D6448E4` (`person_id`),
  KEY `FK553DAE4D17A2D330` (`address_id`),
  KEY `FK553DAE4D2ADCC14B` (`company_role_id`),
  CONSTRAINT `FK553DAE4D2ADCC14B` FOREIGN KEY (`company_role_id`) REFERENCES `company_role` (`id`),
  CONSTRAINT `FK553DAE4D123D1A90` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FK553DAE4D17A2D330` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FK553DAE4D6448E4` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.company_user: ~0 rows (approximately)
/*!40000 ALTER TABLE `company_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_user` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.company_user_security_question
DROP TABLE IF EXISTS `company_user_security_question`;
CREATE TABLE IF NOT EXISTS `company_user_security_question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `answer` varchar(100) NOT NULL,
  `company_user_id` bigint(20) NOT NULL,
  `security_question_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK3E579C93D007852B` (`company_user_id`),
  KEY `FK3E579C93A4E9AFA5` (`security_question_id`),
  CONSTRAINT `FK3E579C93A4E9AFA5` FOREIGN KEY (`security_question_id`) REFERENCES `security_question` (`id`),
  CONSTRAINT `FK3E579C93D007852B` FOREIGN KEY (`company_user_id`) REFERENCES `company_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.company_user_security_question: ~0 rows (approximately)
/*!40000 ALTER TABLE `company_user_security_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_user_security_question` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.company_worker_compensation
DROP TABLE IF EXISTS `company_worker_compensation`;
CREATE TABLE IF NOT EXISTS `company_worker_compensation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `description` longtext,
  `company_id` bigint(20) NOT NULL,
  `company_deduction_id` bigint(20) NOT NULL,
  `us_state_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKD315C0B91FD55A93` (`us_state_id`),
  KEY `FKD315C0B9123D1A90` (`company_id`),
  KEY `FKD315C0B9BEC11AE9` (`company_deduction_id`),
  CONSTRAINT `FKD315C0B9BEC11AE9` FOREIGN KEY (`company_deduction_id`) REFERENCES `company_deduction` (`id`),
  CONSTRAINT `FKD315C0B9123D1A90` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FKD315C0B91FD55A93` FOREIGN KEY (`us_state_id`) REFERENCES `us_state` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.company_worker_compensation: ~0 rows (approximately)
/*!40000 ALTER TABLE `company_worker_compensation` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_worker_compensation` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.contact_type
DROP TABLE IF EXISTS `contact_type`;
CREATE TABLE IF NOT EXISTS `contact_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` longtext,
  `type` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.contact_type: ~4 rows (approximately)
/*!40000 ALTER TABLE `contact_type` DISABLE KEYS */;
INSERT INTO `contact_type` (`id`, `description`, `type`) VALUES
	(1, NULL, 'Authorized'),
	(2, NULL, 'Primary'),
	(3, NULL, 'Billing'),
	(4, NULL, 'Normal');
/*!40000 ALTER TABLE `contact_type` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.county_tax_rate
DROP TABLE IF EXISTS `county_tax_rate`;
CREATE TABLE IF NOT EXISTS `county_tax_rate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ceiling` decimal(30,0) DEFAULT NULL,
  `date_of_entry` datetime DEFAULT NULL,
  `rate` decimal(7,3) DEFAULT NULL,
  `release_date` datetime DEFAULT NULL,
  `resident_status` int(11) NOT NULL,
  `wef` datetime DEFAULT NULL,
  `tax_type_id` bigint(20) NOT NULL,
  `us_county_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKE2728C4991F7B727` (`tax_type_id`),
  KEY `FKE2728C498C3CC621` (`us_county_id`),
  CONSTRAINT `FKE2728C498C3CC621` FOREIGN KEY (`us_county_id`) REFERENCES `us_county` (`id`),
  CONSTRAINT `FKE2728C4991F7B727` FOREIGN KEY (`tax_type_id`) REFERENCES `tax_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.county_tax_rate: ~0 rows (approximately)
/*!40000 ALTER TABLE `county_tax_rate` DISABLE KEYS */;
/*!40000 ALTER TABLE `county_tax_rate` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.deduction
DROP TABLE IF EXISTS `deduction`;
CREATE TABLE IF NOT EXISTS `deduction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deduction_name` varchar(45) NOT NULL,
  `description` longtext,
  `deduction_category_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `deduction_category_id` (`deduction_category_id`,`deduction_name`),
  KEY `FK5DD1A06544F0A21B` (`deduction_category_id`),
  CONSTRAINT `FK5DD1A06544F0A21B` FOREIGN KEY (`deduction_category_id`) REFERENCES `deduction_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.deduction: ~7 rows (approximately)
/*!40000 ALTER TABLE `deduction` DISABLE KEYS */;
INSERT INTO `deduction` (`id`, `deduction_name`, `description`, `deduction_category_id`) VALUES
	(1, '401K', NULL, 1),
	(2, 'SEP IRA', NULL, 1),
	(3, 'SH 401K', NULL, 1),
	(4, 'Dental', NULL, 2),
	(5, 'Medical', NULL, 2),
	(6, 'Miscellaneous', NULL, 2),
	(7, 'Vision', NULL, 2);
/*!40000 ALTER TABLE `deduction` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.deduction_cap
DROP TABLE IF EXISTS `deduction_cap`;
CREATE TABLE IF NOT EXISTS `deduction_cap` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cap_amount` decimal(30,0) DEFAULT NULL,
  `date_of_entry` datetime DEFAULT NULL,
  `release_date` datetime DEFAULT NULL,
  `wef` datetime DEFAULT NULL,
  `company_deduction_id` bigint(20) NOT NULL,
  `us_state_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKE1BD94F81FD55A93` (`us_state_id`),
  KEY `FKE1BD94F8BEC11AE9` (`company_deduction_id`),
  CONSTRAINT `FKE1BD94F8BEC11AE9` FOREIGN KEY (`company_deduction_id`) REFERENCES `company_deduction` (`id`),
  CONSTRAINT `FKE1BD94F81FD55A93` FOREIGN KEY (`us_state_id`) REFERENCES `us_state` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.deduction_cap: ~0 rows (approximately)
/*!40000 ALTER TABLE `deduction_cap` DISABLE KEYS */;
/*!40000 ALTER TABLE `deduction_cap` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.deduction_category
DROP TABLE IF EXISTS `deduction_category`;
CREATE TABLE IF NOT EXISTS `deduction_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category` varchar(45) NOT NULL,
  `description` longtext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `category` (`category`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.deduction_category: ~2 rows (approximately)
/*!40000 ALTER TABLE `deduction_category` DISABLE KEYS */;
INSERT INTO `deduction_category` (`id`, `category`, `description`) VALUES
	(1, 'Retirement Plans', NULL),
	(2, 'Insurance Premiums', NULL);
/*!40000 ALTER TABLE `deduction_category` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.earning
DROP TABLE IF EXISTS `earning`;
CREATE TABLE IF NOT EXISTS `earning` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` longtext,
  `earning_name` varchar(45) NOT NULL,
  `earning_category_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `earning_category_id` (`earning_category_id`,`earning_name`),
  KEY `FK8AD5850A352D4C65` (`earning_category_id`),
  CONSTRAINT `FK8AD5850A352D4C65` FOREIGN KEY (`earning_category_id`) REFERENCES `earning_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.earning: ~11 rows (approximately)
/*!40000 ALTER TABLE `earning` DISABLE KEYS */;
INSERT INTO `earning` (`id`, `description`, `earning_name`, `earning_category_id`) VALUES
	(1, NULL, 'Bonus', 1),
	(2, NULL, 'Commission', 1),
	(3, NULL, 'Expense', 1),
	(4, NULL, 'Overtime Earnings', 1),
	(5, NULL, 'Personal', 3),
	(6, NULL, 'Sick', 3),
	(7, NULL, 'Vacation Time', 3),
	(8, NULL, 'Mileage', 2),
	(10, NULL, 'Test_Scorp', 2),
	(11, NULL, 'Clergy', 2),
	(12, NULL, 'Miscellaneous', 2);
/*!40000 ALTER TABLE `earning` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.earning_category
DROP TABLE IF EXISTS `earning_category`;
CREATE TABLE IF NOT EXISTS `earning_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category` varchar(45) NOT NULL,
  `description` longtext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `category` (`category`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.earning_category: ~3 rows (approximately)
/*!40000 ALTER TABLE `earning_category` DISABLE KEYS */;
INSERT INTO `earning_category` (`id`, `category`, `description`) VALUES
	(1, 'Default Earnings', NULL),
	(2, 'Additional Earnings', NULL),
	(3, 'Paid Time Off', NULL);
/*!40000 ALTER TABLE `earning_category` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.employee
DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contractor_company_name` varchar(45) DEFAULT NULL,
  `contractor_company_tin` varchar(45) DEFAULT NULL,
  `employee_access` int(11) DEFAULT NULL,
  `employee_id` varchar(45) NOT NULL,
  `employee_type` int(11) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `security_answer` varchar(100) DEFAULT NULL,
  `security_question` longtext,
  `setup_status` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `work_email` varchar(45) DEFAULT NULL,
  `work_ext` varchar(45) DEFAULT NULL,
  `work_fax` varchar(45) DEFAULT NULL,
  `wrk_phone` varchar(45) DEFAULT NULL,
  `company_id` bigint(20) NOT NULL,
  `company_worker_compensation_id` bigint(20) NOT NULL,
  `person_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK4722E6AE123D1A90` (`company_id`),
  KEY `FK4722E6AE6448E4` (`person_id`),
  KEY `FK4722E6AE89DFC12C` (`company_worker_compensation_id`),
  CONSTRAINT `FK4722E6AE89DFC12C` FOREIGN KEY (`company_worker_compensation_id`) REFERENCES `company_worker_compensation` (`id`),
  CONSTRAINT `FK4722E6AE123D1A90` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FK4722E6AE6448E4` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.employee: ~0 rows (approximately)
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.employee_address
DROP TABLE IF EXISTS `employee_address`;
CREATE TABLE IF NOT EXISTS `employee_address` (
  `employee_id` bigint(20) NOT NULL,
  `addresses_id` bigint(20) NOT NULL,
  PRIMARY KEY (`employee_id`,`addresses_id`),
  UNIQUE KEY `addresses_id` (`addresses_id`),
  KEY `FK8F879B63D7C8ACC2` (`addresses_id`),
  KEY `FK8F879B63F50D33C4` (`employee_id`),
  CONSTRAINT `FK8F879B63F50D33C4` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK8F879B63D7C8ACC2` FOREIGN KEY (`addresses_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.employee_address: ~0 rows (approximately)
/*!40000 ALTER TABLE `employee_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_address` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.employee_allowance_record
DROP TABLE IF EXISTS `employee_allowance_record`;
CREATE TABLE IF NOT EXISTS `employee_allowance_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `as_on_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `no_of_allowance` bigint(20) NOT NULL,
  `allowance_type_id` bigint(20) NOT NULL,
  `employee_w4_detail_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK37BF3C980E1DC01` (`allowance_type_id`),
  KEY `FK37BF3C9F04F477E` (`employee_w4_detail_id`),
  CONSTRAINT `FK37BF3C9F04F477E` FOREIGN KEY (`employee_w4_detail_id`) REFERENCES `employee_w4_detail` (`id`),
  CONSTRAINT `FK37BF3C980E1DC01` FOREIGN KEY (`allowance_type_id`) REFERENCES `allowance_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.employee_allowance_record: ~0 rows (approximately)
/*!40000 ALTER TABLE `employee_allowance_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_allowance_record` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.employee_bank_account
DROP TABLE IF EXISTS `employee_bank_account`;
CREATE TABLE IF NOT EXISTS `employee_bank_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_number` varchar(45) NOT NULL,
  `account_type` int(11) NOT NULL,
  `bank_name` varchar(45) NOT NULL,
  `deposit_value` decimal(30,0) DEFAULT NULL,
  `deposit_value_type` int(11) NOT NULL,
  `routing_number` varchar(45) NOT NULL,
  `employee_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKBDD6891BF50D33C4` (`employee_id`),
  CONSTRAINT `FKBDD6891BF50D33C4` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.employee_bank_account: ~0 rows (approximately)
/*!40000 ALTER TABLE `employee_bank_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_bank_account` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.employee_deduction
DROP TABLE IF EXISTS `employee_deduction`;
CREATE TABLE IF NOT EXISTS `employee_deduction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount_paid` decimal(10,0) DEFAULT NULL,
  `deduction_type` int(11) NOT NULL,
  `deduction_value_type` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `target_value` decimal(10,0) DEFAULT NULL,
  `value` decimal(10,0) DEFAULT NULL,
  `company_deduction_id` bigint(20) NOT NULL,
  `employee_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKF5BF8014F50D33C4` (`employee_id`),
  KEY `FKF5BF8014BEC11AE9` (`company_deduction_id`),
  CONSTRAINT `FKF5BF8014BEC11AE9` FOREIGN KEY (`company_deduction_id`) REFERENCES `company_deduction` (`id`),
  CONSTRAINT `FKF5BF8014F50D33C4` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.employee_deduction: ~0 rows (approximately)
/*!40000 ALTER TABLE `employee_deduction` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_deduction` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.employee_department_allocation
DROP TABLE IF EXISTS `employee_department_allocation`;
CREATE TABLE IF NOT EXISTS `employee_department_allocation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `allocation_perentage` decimal(5,0) DEFAULT NULL,
  `company_department_id` bigint(20) NOT NULL,
  `employee_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK576A4D1C471E030B` (`company_department_id`),
  KEY `FK576A4D1CF50D33C4` (`employee_id`),
  CONSTRAINT `FK576A4D1CF50D33C4` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK576A4D1C471E030B` FOREIGN KEY (`company_department_id`) REFERENCES `company_department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.employee_department_allocation: ~0 rows (approximately)
/*!40000 ALTER TABLE `employee_department_allocation` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_department_allocation` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.employee_earning
DROP TABLE IF EXISTS `employee_earning`;
CREATE TABLE IF NOT EXISTS `employee_earning` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `earning_value_type` int(11) NOT NULL,
  `value` decimal(30,0) DEFAULT NULL,
  `company_earning_id` bigint(20) NOT NULL,
  `employee_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK5EC584791872489` (`company_earning_id`),
  KEY `FK5EC58479F50D33C4` (`employee_id`),
  CONSTRAINT `FK5EC58479F50D33C4` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK5EC584791872489` FOREIGN KEY (`company_earning_id`) REFERENCES `company_earning` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.employee_earning: ~0 rows (approximately)
/*!40000 ALTER TABLE `employee_earning` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_earning` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.employee_payroll
DROP TABLE IF EXISTS `employee_payroll`;
CREATE TABLE IF NOT EXISTS `employee_payroll` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `direct_deposit_status` bit(1) DEFAULT NULL,
  `gross_pay` decimal(30,0) NOT NULL,
  `hourly_rate` decimal(30,0) DEFAULT NULL,
  `net_check_amount` decimal(30,0) DEFAULT NULL,
  `net_direct_deposit` decimal(30,0) DEFAULT NULL,
  `other_hours` decimal(10,0) DEFAULT NULL,
  `regular_hours` decimal(10,0) DEFAULT NULL,
  `row_type` int(11) DEFAULT NULL,
  `salary` decimal(30,0) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `total_deduction` decimal(30,0) DEFAULT NULL,
  `total_tax` decimal(30,0) DEFAULT NULL,
  `employee_id` bigint(20) NOT NULL,
  `payroll_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKA50E9C9456CD7990` (`payroll_id`),
  KEY `FKA50E9C94F50D33C4` (`employee_id`),
  CONSTRAINT `FKA50E9C94F50D33C4` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FKA50E9C9456CD7990` FOREIGN KEY (`payroll_id`) REFERENCES `payroll` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.employee_payroll: ~0 rows (approximately)
/*!40000 ALTER TABLE `employee_payroll` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_payroll` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.employee_payroll_deduction
DROP TABLE IF EXISTS `employee_payroll_deduction`;
CREATE TABLE IF NOT EXISTS `employee_payroll_deduction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `calculated_deduction` decimal(30,0) DEFAULT NULL,
  `calculated_y2d` decimal(30,0) DEFAULT NULL,
  `deduction_override_type` int(11) DEFAULT NULL,
  `deduction_override_value` decimal(30,0) DEFAULT NULL,
  `is_deduction_override` bit(1) DEFAULT NULL,
  `value` decimal(30,0) DEFAULT NULL,
  `employee_deduction_id` bigint(20) NOT NULL,
  `employee_payroll_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKA1C58E7A2E6CAB33` (`employee_payroll_id`),
  KEY `FKA1C58E7AE4360F3` (`employee_deduction_id`),
  CONSTRAINT `FKA1C58E7AE4360F3` FOREIGN KEY (`employee_deduction_id`) REFERENCES `employee_deduction` (`id`),
  CONSTRAINT `FKA1C58E7A2E6CAB33` FOREIGN KEY (`employee_payroll_id`) REFERENCES `employee_payroll` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.employee_payroll_deduction: ~0 rows (approximately)
/*!40000 ALTER TABLE `employee_payroll_deduction` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_payroll_deduction` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.employee_payroll_earning
DROP TABLE IF EXISTS `employee_payroll_earning`;
CREATE TABLE IF NOT EXISTS `employee_payroll_earning` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `calculated_earning` decimal(30,0) NOT NULL,
  `calculated_y2d` decimal(30,0) NOT NULL,
  `earning_value_type` int(11) NOT NULL,
  `value` decimal(30,0) NOT NULL,
  `employee_earning_id` bigint(20) NOT NULL,
  `employee_payroll_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK39A7F45FB4176513` (`employee_earning_id`),
  KEY `FK39A7F45F2E6CAB33` (`employee_payroll_id`),
  CONSTRAINT `FK39A7F45F2E6CAB33` FOREIGN KEY (`employee_payroll_id`) REFERENCES `employee_payroll` (`id`),
  CONSTRAINT `FK39A7F45FB4176513` FOREIGN KEY (`employee_earning_id`) REFERENCES `employee_earning` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.employee_payroll_earning: ~0 rows (approximately)
/*!40000 ALTER TABLE `employee_payroll_earning` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_payroll_earning` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.employee_payroll_tax
DROP TABLE IF EXISTS `employee_payroll_tax`;
CREATE TABLE IF NOT EXISTS `employee_payroll_tax` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `calculated_tax` decimal(30,0) DEFAULT NULL,
  `calculated_y2d` decimal(30,0) DEFAULT NULL,
  `is_tax_override` bit(1) DEFAULT NULL,
  `tax_override_type` int(11) NOT NULL,
  `tax_override_value` decimal(30,0) DEFAULT NULL,
  `employee_payroll_id` bigint(20) NOT NULL,
  `employee_tax_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKCB321802E6CAB33` (`employee_payroll_id`),
  KEY `FKCB32180B4DC09F3` (`employee_tax_id`),
  CONSTRAINT `FKCB32180B4DC09F3` FOREIGN KEY (`employee_tax_id`) REFERENCES `employee_tax` (`id`),
  CONSTRAINT `FKCB321802E6CAB33` FOREIGN KEY (`employee_payroll_id`) REFERENCES `employee_payroll` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.employee_payroll_tax: ~0 rows (approximately)
/*!40000 ALTER TABLE `employee_payroll_tax` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_payroll_tax` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.employee_pay_setup
DROP TABLE IF EXISTS `employee_pay_setup`;
CREATE TABLE IF NOT EXISTS `employee_pay_setup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hourly_rate` decimal(10,0) DEFAULT NULL,
  `other_rate` decimal(10,0) DEFAULT NULL,
  `pay_rate` decimal(10,0) DEFAULT NULL,
  `pay_type` int(11) NOT NULL,
  `regular_hours` decimal(5,0) DEFAULT NULL,
  `salary` decimal(30,0) DEFAULT NULL,
  `employee_id` bigint(20) NOT NULL,
  `payroll_frequency_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK7BA30FD5394EB039` (`payroll_frequency_id`),
  KEY `FK7BA30FD5F50D33C4` (`employee_id`),
  CONSTRAINT `FK7BA30FD5F50D33C4` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK7BA30FD5394EB039` FOREIGN KEY (`payroll_frequency_id`) REFERENCES `payroll_frequency` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.employee_pay_setup: ~0 rows (approximately)
/*!40000 ALTER TABLE `employee_pay_setup` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_pay_setup` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.employee_section
DROP TABLE IF EXISTS `employee_section`;
CREATE TABLE IF NOT EXISTS `employee_section` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` longtext,
  `section_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.employee_section: ~4 rows (approximately)
/*!40000 ALTER TABLE `employee_section` DISABLE KEYS */;
INSERT INTO `employee_section` (`id`, `description`, `section_name`) VALUES
	(1, NULL, 'Personal Info'),
	(2, NULL, 'Employment Info'),
	(3, NULL, 'Security Profile'),
	(4, NULL, 'View Tax Info');
/*!40000 ALTER TABLE `employee_section` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.employee_tax
DROP TABLE IF EXISTS `employee_tax`;
CREATE TABLE IF NOT EXISTS `employee_tax` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `as_on_date` datetime DEFAULT NULL,
  `exempted` bit(1) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `employee_id` bigint(20) NOT NULL,
  `tax_type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK9900CE9A91F7B727` (`tax_type_id`),
  KEY `FK9900CE9AF50D33C4` (`employee_id`),
  CONSTRAINT `FK9900CE9AF50D33C4` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK9900CE9A91F7B727` FOREIGN KEY (`tax_type_id`) REFERENCES `tax_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.employee_tax: ~0 rows (approximately)
/*!40000 ALTER TABLE `employee_tax` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_tax` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.employee_transaction
DROP TABLE IF EXISTS `employee_transaction`;
CREATE TABLE IF NOT EXISTS `employee_transaction` (
  `id` bigint(20) NOT NULL,
  `transaction_type` varchar(45) NOT NULL,
  `ach_detail_id` bigint(20) NOT NULL,
  `check_detail_id` bigint(20) NOT NULL,
  `company_id` bigint(20) NOT NULL,
  `employee_payroll_id` bigint(20) NOT NULL,
  `fund_category_id` bigint(20) NOT NULL,
  `payment_mode_id` bigint(20) NOT NULL,
  `payroll_company_id` bigint(20) NOT NULL,
  `wire_transfer_detail_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKD39582CD3C03D02F` (`fund_category_id`),
  KEY `FKD39582CD123D1A90` (`company_id`),
  KEY `FKD39582CD94F4459` (`payroll_company_id`),
  KEY `FKD39582CD2E6CAB33` (`employee_payroll_id`),
  KEY `FKD39582CD9FB6FBBD` (`payment_mode_id`),
  KEY `FKD39582CDCF9F603D` (`ach_detail_id`),
  KEY `FKD39582CD67F42EBA` (`wire_transfer_detail_id`),
  KEY `FKD39582CD6F6ECFC1` (`check_detail_id`),
  CONSTRAINT `FKD39582CD6F6ECFC1` FOREIGN KEY (`check_detail_id`) REFERENCES `check_detail` (`id`),
  CONSTRAINT `FKD39582CD123D1A90` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FKD39582CD2E6CAB33` FOREIGN KEY (`employee_payroll_id`) REFERENCES `employee_payroll` (`id`),
  CONSTRAINT `FKD39582CD3C03D02F` FOREIGN KEY (`fund_category_id`) REFERENCES `fund_category` (`id`),
  CONSTRAINT `FKD39582CD67F42EBA` FOREIGN KEY (`wire_transfer_detail_id`) REFERENCES `wire_transfer_detail` (`id`),
  CONSTRAINT `FKD39582CD94F4459` FOREIGN KEY (`payroll_company_id`) REFERENCES `payroll_company` (`id`),
  CONSTRAINT `FKD39582CD9FB6FBBD` FOREIGN KEY (`payment_mode_id`) REFERENCES `payment_mode` (`id`),
  CONSTRAINT `FKD39582CDCF9F603D` FOREIGN KEY (`ach_detail_id`) REFERENCES `ach_detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.employee_transaction: ~0 rows (approximately)
/*!40000 ALTER TABLE `employee_transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_transaction` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.employee_w4_detail
DROP TABLE IF EXISTS `employee_w4_detail`;
CREATE TABLE IF NOT EXISTS `employee_w4_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `additional_withholding` decimal(30,0) DEFAULT NULL,
  `as_on_date` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `tax_override_type` int(11) DEFAULT NULL,
  `tax_override_value` decimal(30,0) DEFAULT NULL,
  `total_tax_liability` decimal(30,0) DEFAULT NULL,
  `employee_tax_id` bigint(20) NOT NULL,
  `filing_status_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKEF54ABE290F23357` (`filing_status_id`),
  KEY `FKEF54ABE2B4DC09F3` (`employee_tax_id`),
  CONSTRAINT `FKEF54ABE2B4DC09F3` FOREIGN KEY (`employee_tax_id`) REFERENCES `employee_tax` (`id`),
  CONSTRAINT `FKEF54ABE290F23357` FOREIGN KEY (`filing_status_id`) REFERENCES `filing_status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.employee_w4_detail: ~0 rows (approximately)
/*!40000 ALTER TABLE `employee_w4_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_w4_detail` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.employment_history
DROP TABLE IF EXISTS `employment_history`;
CREATE TABLE IF NOT EXISTS `employment_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hire_date` date DEFAULT NULL,
  `termination_date` date DEFAULT NULL,
  `employee_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK189A8341F50D33C4` (`employee_id`),
  CONSTRAINT `FK189A8341F50D33C4` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.employment_history: ~0 rows (approximately)
/*!40000 ALTER TABLE `employment_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `employment_history` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.federal_state_holiday
DROP TABLE IF EXISTS `federal_state_holiday`;
CREATE TABLE IF NOT EXISTS `federal_state_holiday` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `holiday_date` date NOT NULL,
  `holiday_description` longtext,
  `state_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK739ED08850ECA612` (`state_id`),
  CONSTRAINT `FK739ED08850ECA612` FOREIGN KEY (`state_id`) REFERENCES `us_state` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.federal_state_holiday: ~0 rows (approximately)
/*!40000 ALTER TABLE `federal_state_holiday` DISABLE KEYS */;
/*!40000 ALTER TABLE `federal_state_holiday` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.federal_state_tax_rate
DROP TABLE IF EXISTS `federal_state_tax_rate`;
CREATE TABLE IF NOT EXISTS `federal_state_tax_rate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `calculated_slab_amount` decimal(30,0) DEFAULT NULL,
  `ceiling` decimal(30,0) DEFAULT NULL,
  `date_of_entry` datetime DEFAULT NULL,
  `max_value` decimal(30,0) DEFAULT NULL,
  `min_value` decimal(30,0) DEFAULT NULL,
  `rate` decimal(5,0) DEFAULT NULL,
  `release_date` datetime DEFAULT NULL,
  `wef` datetime DEFAULT NULL,
  `filing_status_id` bigint(20) NOT NULL,
  `tax_type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKE25B6C490F23357` (`filing_status_id`),
  KEY `FKE25B6C491F7B727` (`tax_type_id`),
  CONSTRAINT `FKE25B6C491F7B727` FOREIGN KEY (`tax_type_id`) REFERENCES `tax_type` (`id`),
  CONSTRAINT `FKE25B6C490F23357` FOREIGN KEY (`filing_status_id`) REFERENCES `filing_status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.federal_state_tax_rate: ~0 rows (approximately)
/*!40000 ALTER TABLE `federal_state_tax_rate` DISABLE KEYS */;
/*!40000 ALTER TABLE `federal_state_tax_rate` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.filing_status
DROP TABLE IF EXISTS `filing_status`;
CREATE TABLE IF NOT EXISTS `filing_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.filing_status: ~4 rows (approximately)
/*!40000 ALTER TABLE `filing_status` DISABLE KEYS */;
INSERT INTO `filing_status` (`id`, `description`, `status`) VALUES
	(1, NULL, 'Single'),
	(2, NULL, 'Married Filing Jointly or Qualified Widow(er)'),
	(3, NULL, 'Head of Household'),
	(4, NULL, 'Married Filing Separately');
/*!40000 ALTER TABLE `filing_status` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.fund_category
DROP TABLE IF EXISTS `fund_category`;
CREATE TABLE IF NOT EXISTS `fund_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `category_name` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.fund_category: ~3 rows (approximately)
/*!40000 ALTER TABLE `fund_category` DISABLE KEYS */;
INSERT INTO `fund_category` (`id`, `category_name`, `description`) VALUES
	(1, 'Payroll Fund', NULL),
	(2, 'Tax Fund', NULL),
	(3, 'Employee Payment Fund', NULL);
/*!40000 ALTER TABLE `fund_category` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.message
DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` longtext NOT NULL,
  `received_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `subject` varchar(255) NOT NULL,
  `from_person_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK38EB000797C34E8F` (`from_person_id`),
  CONSTRAINT `FK38EB000797C34E8F` FOREIGN KEY (`from_person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.message: ~0 rows (approximately)
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.mobile_device
DROP TABLE IF EXISTS `mobile_device`;
CREATE TABLE IF NOT EXISTS `mobile_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `device_id` varchar(45) NOT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `registered_on` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `company_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKF00CF093D007852B` (`company_user_id`),
  CONSTRAINT `FKF00CF093D007852B` FOREIGN KEY (`company_user_id`) REFERENCES `company_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.mobile_device: ~0 rows (approximately)
/*!40000 ALTER TABLE `mobile_device` DISABLE KEYS */;
/*!40000 ALTER TABLE `mobile_device` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.other_transaction
DROP TABLE IF EXISTS `other_transaction`;
CREATE TABLE IF NOT EXISTS `other_transaction` (
  `id` bigint(20) NOT NULL,
  `transaction_type` varchar(45) NOT NULL,
  `ach_detail_id` bigint(20) NOT NULL,
  `check_detail_id` bigint(20) NOT NULL,
  `company_id` bigint(20) NOT NULL,
  `fund_category_id` bigint(20) NOT NULL,
  `payment_mode_id` bigint(20) NOT NULL,
  `payroll_id` bigint(20) NOT NULL,
  `payroll_company_id` bigint(20) NOT NULL,
  `wire_transfer_detail_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK5E49982F3C03D02F` (`fund_category_id`),
  KEY `FK5E49982F123D1A90` (`company_id`),
  KEY `FK5E49982F94F4459` (`payroll_company_id`),
  KEY `FK5E49982F56CD7990` (`payroll_id`),
  KEY `FK5E49982F9FB6FBBD` (`payment_mode_id`),
  KEY `FK5E49982FCF9F603D` (`ach_detail_id`),
  KEY `FK5E49982F67F42EBA` (`wire_transfer_detail_id`),
  KEY `FK5E49982F6F6ECFC1` (`check_detail_id`),
  CONSTRAINT `FK5E49982F6F6ECFC1` FOREIGN KEY (`check_detail_id`) REFERENCES `check_detail` (`id`),
  CONSTRAINT `FK5E49982F123D1A90` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FK5E49982F3C03D02F` FOREIGN KEY (`fund_category_id`) REFERENCES `fund_category` (`id`),
  CONSTRAINT `FK5E49982F56CD7990` FOREIGN KEY (`payroll_id`) REFERENCES `payroll` (`id`),
  CONSTRAINT `FK5E49982F67F42EBA` FOREIGN KEY (`wire_transfer_detail_id`) REFERENCES `wire_transfer_detail` (`id`),
  CONSTRAINT `FK5E49982F94F4459` FOREIGN KEY (`payroll_company_id`) REFERENCES `payroll_company` (`id`),
  CONSTRAINT `FK5E49982F9FB6FBBD` FOREIGN KEY (`payment_mode_id`) REFERENCES `payment_mode` (`id`),
  CONSTRAINT `FK5E49982FCF9F603D` FOREIGN KEY (`ach_detail_id`) REFERENCES `ach_detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.other_transaction: ~0 rows (approximately)
/*!40000 ALTER TABLE `other_transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `other_transaction` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.payment_mode
DROP TABLE IF EXISTS `payment_mode`;
CREATE TABLE IF NOT EXISTS `payment_mode` (
  `id` bigint(20) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `mode` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `mode` (`mode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.payment_mode: ~3 rows (approximately)
/*!40000 ALTER TABLE `payment_mode` DISABLE KEYS */;
INSERT INTO `payment_mode` (`id`, `description`, `mode`) VALUES
	(1, NULL, 'Check'),
	(2, NULL, 'ACH'),
	(3, NULL, 'Wire Transfer');
/*!40000 ALTER TABLE `payment_mode` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.payroll
DROP TABLE IF EXISTS `payroll`;
CREATE TABLE IF NOT EXISTS `payroll` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `approval_date` datetime DEFAULT NULL,
  `check_date` datetime NOT NULL,
  `check_stub_message` varchar(100) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `end_date` datetime NOT NULL,
  `payroll_status` int(11) DEFAULT NULL,
  `payroll_type` int(11) DEFAULT NULL,
  `start_date` datetime NOT NULL,
  `company_id` bigint(20) NOT NULL,
  `payroll_schedule_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKD11E9D25123D1A90` (`company_id`),
  KEY `FKD11E9D2543864E7B` (`payroll_schedule_id`),
  CONSTRAINT `FKD11E9D2543864E7B` FOREIGN KEY (`payroll_schedule_id`) REFERENCES `payroll_schedule` (`id`),
  CONSTRAINT `FKD11E9D25123D1A90` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.payroll: ~0 rows (approximately)
/*!40000 ALTER TABLE `payroll` DISABLE KEYS */;
/*!40000 ALTER TABLE `payroll` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.payroll_company
DROP TABLE IF EXISTS `payroll_company`;
CREATE TABLE IF NOT EXISTS `payroll_company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email_id` varchar(45) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `payroll_company_name` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `website` varchar(45) DEFAULT NULL,
  `address_id` bigint(20) NOT NULL,
  `person_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKFE4C8B636448E4` (`person_id`),
  KEY `FKFE4C8B6317A2D330` (`address_id`),
  CONSTRAINT `FKFE4C8B6317A2D330` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FKFE4C8B636448E4` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.payroll_company: ~0 rows (approximately)
/*!40000 ALTER TABLE `payroll_company` DISABLE KEYS */;
/*!40000 ALTER TABLE `payroll_company` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.payroll_earning
DROP TABLE IF EXISTS `payroll_earning`;
CREATE TABLE IF NOT EXISTS `payroll_earning` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `calculated_earning` decimal(30,0) NOT NULL,
  `calculated_y2d` decimal(30,0) NOT NULL,
  `earning_value_type` int(11) NOT NULL,
  `value` decimal(30,0) NOT NULL,
  `employee_earning_id` bigint(20) NOT NULL,
  `employee_payroll_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK507AD3F0B4176513` (`employee_earning_id`),
  KEY `FK507AD3F02E6CAB33` (`employee_payroll_id`),
  CONSTRAINT `FK507AD3F02E6CAB33` FOREIGN KEY (`employee_payroll_id`) REFERENCES `employee_payroll` (`id`),
  CONSTRAINT `FK507AD3F0B4176513` FOREIGN KEY (`employee_earning_id`) REFERENCES `employee_earning` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.payroll_earning: ~0 rows (approximately)
/*!40000 ALTER TABLE `payroll_earning` DISABLE KEYS */;
/*!40000 ALTER TABLE `payroll_earning` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.payroll_frequency
DROP TABLE IF EXISTS `payroll_frequency`;
CREATE TABLE IF NOT EXISTS `payroll_frequency` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `check_date` date NOT NULL,
  `holiday_check_date_option` varchar(10) NOT NULL,
  `period_end_date` date NOT NULL,
  `period_start_date` date NOT NULL,
  `company_id` bigint(20) NOT NULL,
  `pay_frequency_type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKED60B2C2123D1A90` (`company_id`),
  KEY `FKED60B2C2EB64FA3E` (`pay_frequency_type_id`),
  CONSTRAINT `FKED60B2C2EB64FA3E` FOREIGN KEY (`pay_frequency_type_id`) REFERENCES `pay_frequency_type` (`id`),
  CONSTRAINT `FKED60B2C2123D1A90` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.payroll_frequency: ~0 rows (approximately)
/*!40000 ALTER TABLE `payroll_frequency` DISABLE KEYS */;
/*!40000 ALTER TABLE `payroll_frequency` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.payroll_fund_requirement
DROP TABLE IF EXISTS `payroll_fund_requirement`;
CREATE TABLE IF NOT EXISTS `payroll_fund_requirement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `check_amount` decimal(30,0) DEFAULT NULL,
  `company_deduction` decimal(30,0) DEFAULT NULL,
  `company_tax` decimal(30,0) DEFAULT NULL,
  `direct_deposit_amount` decimal(30,0) DEFAULT NULL,
  `employee_deduction` decimal(30,0) DEFAULT NULL,
  `employee_tax` decimal(30,0) DEFAULT NULL,
  `vjs_fee` decimal(30,0) DEFAULT NULL,
  `payroll_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKB8E471A356CD7990` (`payroll_id`),
  CONSTRAINT `FKB8E471A356CD7990` FOREIGN KEY (`payroll_id`) REFERENCES `payroll` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.payroll_fund_requirement: ~0 rows (approximately)
/*!40000 ALTER TABLE `payroll_fund_requirement` DISABLE KEYS */;
/*!40000 ALTER TABLE `payroll_fund_requirement` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.payroll_invoice
DROP TABLE IF EXISTS `payroll_invoice`;
CREATE TABLE IF NOT EXISTS `payroll_invoice` (
  `id` bigint(20) NOT NULL,
  `generated_on` datetime DEFAULT NULL,
  `invoice_amount` decimal(30,0) DEFAULT NULL,
  `invoice_no` varchar(45) DEFAULT NULL,
  `payment_due_date` date DEFAULT NULL,
  `payment_or_cancellation_date` date DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `payroll_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK3A7B921356CD7990` (`payroll_id`),
  CONSTRAINT `FK3A7B921356CD7990` FOREIGN KEY (`payroll_id`) REFERENCES `payroll` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.payroll_invoice: ~0 rows (approximately)
/*!40000 ALTER TABLE `payroll_invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `payroll_invoice` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.payroll_plan
DROP TABLE IF EXISTS `payroll_plan`;
CREATE TABLE IF NOT EXISTS `payroll_plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` longtext,
  `plan_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `plan_name` (`plan_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.payroll_plan: ~0 rows (approximately)
/*!40000 ALTER TABLE `payroll_plan` DISABLE KEYS */;
/*!40000 ALTER TABLE `payroll_plan` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.payroll_schedule
DROP TABLE IF EXISTS `payroll_schedule`;
CREATE TABLE IF NOT EXISTS `payroll_schedule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `approved_on` datetime DEFAULT NULL,
  `check_date` date NOT NULL,
  `period_end_date` date NOT NULL,
  `period_start_date` date NOT NULL,
  `status` int(11) DEFAULT NULL,
  `payroll_frequency_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKC56B2071394EB039` (`payroll_frequency_id`),
  CONSTRAINT `FKC56B2071394EB039` FOREIGN KEY (`payroll_frequency_id`) REFERENCES `payroll_frequency` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.payroll_schedule: ~0 rows (approximately)
/*!40000 ALTER TABLE `payroll_schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `payroll_schedule` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.payroll_section
DROP TABLE IF EXISTS `payroll_section`;
CREATE TABLE IF NOT EXISTS `payroll_section` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `section_description` longtext,
  `section_name` varchar(45) NOT NULL,
  `parent_section_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK3B14CDCBB7E34045` (`parent_section_id`),
  CONSTRAINT `FK3B14CDCBB7E34045` FOREIGN KEY (`parent_section_id`) REFERENCES `section` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.payroll_section: ~0 rows (approximately)
/*!40000 ALTER TABLE `payroll_section` DISABLE KEYS */;
/*!40000 ALTER TABLE `payroll_section` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.payroll_section_role_map
DROP TABLE IF EXISTS `payroll_section_role_map`;
CREATE TABLE IF NOT EXISTS `payroll_section_role_map` (
  `payroll_section_id` bigint(20) NOT NULL,
  `company_role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`company_role_id`,`payroll_section_id`),
  KEY `FK76C4E207927F4B59` (`payroll_section_id`),
  KEY `FK76C4E2072ADCC14B` (`company_role_id`),
  CONSTRAINT `FK76C4E2072ADCC14B` FOREIGN KEY (`company_role_id`) REFERENCES `company_role` (`id`),
  CONSTRAINT `FK76C4E207927F4B59` FOREIGN KEY (`payroll_section_id`) REFERENCES `payroll_section` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.payroll_section_role_map: ~0 rows (approximately)
/*!40000 ALTER TABLE `payroll_section_role_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `payroll_section_role_map` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.payroll_tax_contribution
DROP TABLE IF EXISTS `payroll_tax_contribution`;
CREATE TABLE IF NOT EXISTS `payroll_tax_contribution` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_contri` decimal(30,0) DEFAULT NULL,
  `employee_contri` decimal(30,0) DEFAULT NULL,
  `payroll_id` bigint(20) NOT NULL,
  `tax_authority_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK95436FFE56CD7990` (`payroll_id`),
  KEY `FK95436FFEE9BF034D` (`tax_authority_id`),
  CONSTRAINT `FK95436FFEE9BF034D` FOREIGN KEY (`tax_authority_id`) REFERENCES `tax_authority` (`id`),
  CONSTRAINT `FK95436FFE56CD7990` FOREIGN KEY (`payroll_id`) REFERENCES `payroll` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.payroll_tax_contribution: ~0 rows (approximately)
/*!40000 ALTER TABLE `payroll_tax_contribution` DISABLE KEYS */;
/*!40000 ALTER TABLE `payroll_tax_contribution` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.pay_frequency_type
DROP TABLE IF EXISTS `pay_frequency_type`;
CREATE TABLE IF NOT EXISTS `pay_frequency_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` longtext,
  `no_of_days` int(11) NOT NULL,
  `type` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.pay_frequency_type: ~4 rows (approximately)
/*!40000 ALTER TABLE `pay_frequency_type` DISABLE KEYS */;
INSERT INTO `pay_frequency_type` (`id`, `description`, `no_of_days`, `type`) VALUES
	(1, NULL, 30, 'Monthly'),
	(2, NULL, 15, 'Semi-Monthly'),
	(3, NULL, 7, 'Weekly'),
	(4, NULL, 14, 'Bi-Weekly');
/*!40000 ALTER TABLE `pay_frequency_type` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.person
DROP TABLE IF EXISTS `person`;
CREATE TABLE IF NOT EXISTS `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `designation` varchar(45) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `ext` varchar(10) DEFAULT NULL,
  `fax` varchar(15) DEFAULT NULL,
  `first_name` varchar(30) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `middle_name` varchar(30) DEFAULT NULL,
  `mobile` varchar(15) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `ssn` varchar(9) DEFAULT NULL,
  `contact_type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKC4E39B556F7C0D91` (`contact_type_id`),
  CONSTRAINT `FKC4E39B556F7C0D91` FOREIGN KEY (`contact_type_id`) REFERENCES `contact_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.person: ~0 rows (approximately)
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
/*!40000 ALTER TABLE `person` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.plan_feature
DROP TABLE IF EXISTS `plan_feature`;
CREATE TABLE IF NOT EXISTS `plan_feature` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` longtext,
  `service` varchar(45) NOT NULL,
  `payroll_plan_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `service` (`service`),
  KEY `FKC5A173E0BA60E5BB` (`payroll_plan_id`),
  CONSTRAINT `FKC5A173E0BA60E5BB` FOREIGN KEY (`payroll_plan_id`) REFERENCES `payroll_plan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.plan_feature: ~0 rows (approximately)
/*!40000 ALTER TABLE `plan_feature` DISABLE KEYS */;
/*!40000 ALTER TABLE `plan_feature` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.section
DROP TABLE IF EXISTS `section`;
CREATE TABLE IF NOT EXISTS `section` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.section: ~0 rows (approximately)
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
/*!40000 ALTER TABLE `section` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.security_question
DROP TABLE IF EXISTS `security_question`;
CREATE TABLE IF NOT EXISTS `security_question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_desc` longtext,
  `group_no` int(11) NOT NULL,
  `question` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `group_no` (`group_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.security_question: ~0 rows (approximately)
/*!40000 ALTER TABLE `security_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `security_question` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.standard_deduction_rate
DROP TABLE IF EXISTS `standard_deduction_rate`;
CREATE TABLE IF NOT EXISTS `standard_deduction_rate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_of_entry` datetime DEFAULT NULL,
  `max_value` decimal(30,0) DEFAULT NULL,
  `min_value` decimal(30,0) DEFAULT NULL,
  `rate` decimal(30,0) DEFAULT NULL,
  `release_date` datetime DEFAULT NULL,
  `wef` datetime DEFAULT NULL,
  `filing_status_id` bigint(20) NOT NULL,
  `us_state_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK1F3C3A3C1FD55A93` (`us_state_id`),
  KEY `FK1F3C3A3C90F23357` (`filing_status_id`),
  CONSTRAINT `FK1F3C3A3C90F23357` FOREIGN KEY (`filing_status_id`) REFERENCES `filing_status` (`id`),
  CONSTRAINT `FK1F3C3A3C1FD55A93` FOREIGN KEY (`us_state_id`) REFERENCES `us_state` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.standard_deduction_rate: ~0 rows (approximately)
/*!40000 ALTER TABLE `standard_deduction_rate` DISABLE KEYS */;
/*!40000 ALTER TABLE `standard_deduction_rate` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.suta_tax_rate
DROP TABLE IF EXISTS `suta_tax_rate`;
CREATE TABLE IF NOT EXISTS `suta_tax_rate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ceiling` decimal(30,0) DEFAULT NULL,
  `date_of_entry` datetime DEFAULT NULL,
  `rate` decimal(7,3) DEFAULT NULL,
  `release_date` datetime DEFAULT NULL,
  `wef` datetime DEFAULT NULL,
  `company_id` bigint(20) NOT NULL,
  `tax_type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKA560E6E4123D1A90` (`company_id`),
  KEY `FKA560E6E491F7B727` (`tax_type_id`),
  CONSTRAINT `FKA560E6E491F7B727` FOREIGN KEY (`tax_type_id`) REFERENCES `tax_type` (`id`),
  CONSTRAINT `FKA560E6E4123D1A90` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.suta_tax_rate: ~0 rows (approximately)
/*!40000 ALTER TABLE `suta_tax_rate` DISABLE KEYS */;
/*!40000 ALTER TABLE `suta_tax_rate` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.system_message
DROP TABLE IF EXISTS `system_message`;
CREATE TABLE IF NOT EXISTS `system_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_received` datetime NOT NULL,
  `message_text` longtext NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.system_message: ~0 rows (approximately)
/*!40000 ALTER TABLE `system_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_message` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.system_message_user_map
DROP TABLE IF EXISTS `system_message_user_map`;
CREATE TABLE IF NOT EXISTS `system_message_user_map` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` int(11) NOT NULL,
  `company_user_id` bigint(20) NOT NULL,
  `system_message_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK8FE6E0304C9D48B1` (`system_message_id`),
  KEY `FK8FE6E030D007852B` (`company_user_id`),
  CONSTRAINT `FK8FE6E030D007852B` FOREIGN KEY (`company_user_id`) REFERENCES `company_user` (`id`),
  CONSTRAINT `FK8FE6E0304C9D48B1` FOREIGN KEY (`system_message_id`) REFERENCES `system_message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.system_message_user_map: ~0 rows (approximately)
/*!40000 ALTER TABLE `system_message_user_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_message_user_map` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.system_task
DROP TABLE IF EXISTS `system_task`;
CREATE TABLE IF NOT EXISTS `system_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_received` datetime NOT NULL,
  `due_date` datetime DEFAULT NULL,
  `key_value` varchar(250) NOT NULL,
  `redirecting_url` varchar(200) DEFAULT NULL,
  `task_text` varchar(250) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.system_task: ~0 rows (approximately)
/*!40000 ALTER TABLE `system_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_task` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.system_task_user_map
DROP TABLE IF EXISTS `system_task_user_map`;
CREATE TABLE IF NOT EXISTS `system_task_user_map` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` int(11) NOT NULL,
  `company_user_id` bigint(20) NOT NULL,
  `system_task_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK20A88A52D007852B` (`company_user_id`),
  KEY `FK20A88A524DD2D6A3` (`system_task_id`),
  CONSTRAINT `FK20A88A524DD2D6A3` FOREIGN KEY (`system_task_id`) REFERENCES `system_task` (`id`),
  CONSTRAINT `FK20A88A52D007852B` FOREIGN KEY (`company_user_id`) REFERENCES `company_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.system_task_user_map: ~0 rows (approximately)
/*!40000 ALTER TABLE `system_task_user_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_task_user_map` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.tax_authority
DROP TABLE IF EXISTS `tax_authority`;
CREATE TABLE IF NOT EXISTS `tax_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority_name` varchar(45) NOT NULL,
  `description` longtext,
  `email_id` varchar(30) DEFAULT NULL,
  `fax` varchar(15) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `web_address` varchar(20) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `us_county_id` bigint(20) DEFAULT NULL,
  `us_state_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `authority_name` (`authority_name`),
  KEY `FKC81767AF1FD55A93` (`us_state_id`),
  KEY `FKC81767AF17A2D330` (`address_id`),
  KEY `FKC81767AF8C3CC621` (`us_county_id`),
  CONSTRAINT `FKC81767AF8C3CC621` FOREIGN KEY (`us_county_id`) REFERENCES `us_county` (`id`),
  CONSTRAINT `FKC81767AF17A2D330` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FKC81767AF1FD55A93` FOREIGN KEY (`us_state_id`) REFERENCES `us_state` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.tax_authority: ~5 rows (approximately)
/*!40000 ALTER TABLE `tax_authority` DISABLE KEYS */;
INSERT INTO `tax_authority` (`id`, `authority_name`, `description`, `email_id`, `fax`, `phone`, `web_address`, `address_id`, `us_county_id`, `us_state_id`) VALUES
	(1, 'Federal (IRS)', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(2, 'Virginia Tax Authority', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
	(3, 'Marylan Tax Athority', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2),
	(4, 'Baltimore Tax Athority', NULL, NULL, NULL, NULL, NULL, NULL, 6, 2),
	(5, 'Calvert Tax Athority', NULL, NULL, NULL, NULL, NULL, NULL, 8, 2);
/*!40000 ALTER TABLE `tax_authority` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.tax_authority_deposit_cycle
DROP TABLE IF EXISTS `tax_authority_deposit_cycle`;
CREATE TABLE IF NOT EXISTS `tax_authority_deposit_cycle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `max_tax_amount` decimal(30,0) DEFAULT NULL,
  `min_tax_amount` decimal(30,0) DEFAULT NULL,
  `tax_authority_id` bigint(20) NOT NULL,
  `tax_deposit_cycle_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKF441BF75E9BF034D` (`tax_authority_id`),
  KEY `FKF441BF75723491D2` (`tax_deposit_cycle_id`),
  CONSTRAINT `FKF441BF75723491D2` FOREIGN KEY (`tax_deposit_cycle_id`) REFERENCES `tax_deposit_cycle` (`id`),
  CONSTRAINT `FKF441BF75E9BF034D` FOREIGN KEY (`tax_authority_id`) REFERENCES `tax_authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.tax_authority_deposit_cycle: ~0 rows (approximately)
/*!40000 ALTER TABLE `tax_authority_deposit_cycle` DISABLE KEYS */;
/*!40000 ALTER TABLE `tax_authority_deposit_cycle` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.tax_authority_transaction
DROP TABLE IF EXISTS `tax_authority_transaction`;
CREATE TABLE IF NOT EXISTS `tax_authority_transaction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `transaction_type` varchar(45) NOT NULL,
  `ach_detail_id` bigint(20) NOT NULL,
  `check_detail_id` bigint(20) NOT NULL,
  `company_id` bigint(20) NOT NULL,
  `fund_category_id` bigint(20) NOT NULL,
  `payment_mode_id` bigint(20) NOT NULL,
  `payroll_id` bigint(20) NOT NULL,
  `payroll_company_id` bigint(20) NOT NULL,
  `tax_authority_id` bigint(20) NOT NULL,
  `wire_transfer_detail_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK7B538A4E3C03D02F` (`fund_category_id`),
  KEY `FK7B538A4E123D1A90` (`company_id`),
  KEY `FK7B538A4E94F4459` (`payroll_company_id`),
  KEY `FK7B538A4E56CD7990` (`payroll_id`),
  KEY `FK7B538A4E9FB6FBBD` (`payment_mode_id`),
  KEY `FK7B538A4EE9BF034D` (`tax_authority_id`),
  KEY `FK7B538A4ECF9F603D` (`ach_detail_id`),
  KEY `FK7B538A4E67F42EBA` (`wire_transfer_detail_id`),
  KEY `FK7B538A4E6F6ECFC1` (`check_detail_id`),
  CONSTRAINT `FK7B538A4E6F6ECFC1` FOREIGN KEY (`check_detail_id`) REFERENCES `check_detail` (`id`),
  CONSTRAINT `FK7B538A4E123D1A90` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FK7B538A4E3C03D02F` FOREIGN KEY (`fund_category_id`) REFERENCES `fund_category` (`id`),
  CONSTRAINT `FK7B538A4E56CD7990` FOREIGN KEY (`payroll_id`) REFERENCES `payroll` (`id`),
  CONSTRAINT `FK7B538A4E67F42EBA` FOREIGN KEY (`wire_transfer_detail_id`) REFERENCES `wire_transfer_detail` (`id`),
  CONSTRAINT `FK7B538A4E94F4459` FOREIGN KEY (`payroll_company_id`) REFERENCES `payroll_company` (`id`),
  CONSTRAINT `FK7B538A4E9FB6FBBD` FOREIGN KEY (`payment_mode_id`) REFERENCES `payment_mode` (`id`),
  CONSTRAINT `FK7B538A4ECF9F603D` FOREIGN KEY (`ach_detail_id`) REFERENCES `ach_detail` (`id`),
  CONSTRAINT `FK7B538A4EE9BF034D` FOREIGN KEY (`tax_authority_id`) REFERENCES `tax_authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.tax_authority_transaction: ~0 rows (approximately)
/*!40000 ALTER TABLE `tax_authority_transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `tax_authority_transaction` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.tax_deposit_cycle
DROP TABLE IF EXISTS `tax_deposit_cycle`;
CREATE TABLE IF NOT EXISTS `tax_deposit_cycle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cycle` varchar(45) NOT NULL,
  `description` longtext,
  `no_of_days` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `cycle` (`cycle`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.tax_deposit_cycle: ~4 rows (approximately)
/*!40000 ALTER TABLE `tax_deposit_cycle` DISABLE KEYS */;
INSERT INTO `tax_deposit_cycle` (`id`, `cycle`, `description`, `no_of_days`) VALUES
	(1, 'Next Day', NULL, 1),
	(2, 'Semi-Weekly', NULL, 3),
	(3, 'Monthly', NULL, 15),
	(4, 'Quarterly', NULL, 90);
/*!40000 ALTER TABLE `tax_deposit_cycle` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.tax_type
DROP TABLE IF EXISTS `tax_type`;
CREATE TABLE IF NOT EXISTS `tax_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` longtext,
  `paid_by_company` bit(1) NOT NULL,
  `paid_by_employee` bit(1) NOT NULL,
  `tax_name` varchar(45) NOT NULL,
  `tax_authority_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `tax_name` (`tax_name`),
  KEY `FKEF7B9B4EE9BF034D` (`tax_authority_id`),
  CONSTRAINT `FKEF7B9B4EE9BF034D` FOREIGN KEY (`tax_authority_id`) REFERENCES `tax_authority` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.tax_type: ~10 rows (approximately)
/*!40000 ALTER TABLE `tax_type` DISABLE KEYS */;
INSERT INTO `tax_type` (`id`, `description`, `paid_by_company`, `paid_by_employee`, `tax_name`, `tax_authority_id`) VALUES
	(1, NULL, b'00000000', b'10000000', 'Federal Income Tax', 1),
	(2, NULL, b'10000000', b'00000000', 'Federal Unemployement Tax', 1),
	(3, NULL, b'10000000', b'10000000', 'Medicare', 1),
	(4, NULL, b'10000000', b'10000000', 'Social Security', 1),
	(5, NULL, b'00000000', b'10000000', 'Virginia State Income Tax', 2),
	(6, NULL, b'10000000', b'00000000', 'Virginia State Unemployement Tax', 2),
	(8, NULL, b'00000000', b'10000000', 'Maryland State Income Tax', 3),
	(9, NULL, b'10000000', b'00000000', 'Maryland State Unemployement Tax', 3),
	(10, NULL, b'00000000', b'10000000', 'Baltimore Local Tax', 4),
	(11, NULL, b'00000000', b'10000000', 'Calvert Local Tax', 5);
/*!40000 ALTER TABLE `tax_type` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.to_message_map
DROP TABLE IF EXISTS `to_message_map`;
CREATE TABLE IF NOT EXISTS `to_message_map` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` int(11) DEFAULT NULL,
  `person_message_id` bigint(20) NOT NULL,
  `to_person_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6A360560B34DB420` (`to_person_id`),
  KEY `FK6A3605609D78B97A` (`person_message_id`),
  CONSTRAINT `FK6A3605609D78B97A` FOREIGN KEY (`person_message_id`) REFERENCES `message` (`id`),
  CONSTRAINT `FK6A360560B34DB420` FOREIGN KEY (`to_person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.to_message_map: ~0 rows (approximately)
/*!40000 ALTER TABLE `to_message_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `to_message_map` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.transaction_body
DROP TABLE IF EXISTS `transaction_body`;
CREATE TABLE IF NOT EXISTS `transaction_body` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` longtext,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.transaction_body: ~0 rows (approximately)
/*!40000 ALTER TABLE `transaction_body` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction_body` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.us_city
DROP TABLE IF EXISTS `us_city`;
CREATE TABLE IF NOT EXISTS `us_city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(45) NOT NULL,
  `us_state_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKF6DFB66C1FD55A93` (`us_state_id`),
  CONSTRAINT `FKF6DFB66C1FD55A93` FOREIGN KEY (`us_state_id`) REFERENCES `us_state` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.us_city: ~20 rows (approximately)
/*!40000 ALTER TABLE `us_city` DISABLE KEYS */;
INSERT INTO `us_city` (`id`, `city_name`, `us_state_id`) VALUES
	(1, 'Virginia Beach', 1),
	(2, 'Norfolk', 1),
	(3, 'Richmond', 1),
	(4, 'Newport News', 1),
	(5, 'Alexandria', 1),
	(6, 'Hampton', 1),
	(7, 'Roanoke', 1),
	(8, 'Danville', 1),
	(9, 'Hopewell', 1),
	(10, 'Bristol', 1),
	(11, 'Falls Church', 1),
	(12, 'Galax', 1),
	(13, 'Bedford', 1),
	(14, 'Norton', 1),
	(15, 'Emporia', 1),
	(16, 'Covington', 1),
	(17, 'Aberdeen', 2),
	(18, 'Annapolis', 2),
	(19, 'Baltimore', 2),
	(20, 'Brunswick', 2);
/*!40000 ALTER TABLE `us_city` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.us_county
DROP TABLE IF EXISTS `us_county`;
CREATE TABLE IF NOT EXISTS `us_county` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `county_name` varchar(45) NOT NULL,
  `us_state_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `us_state_id` (`us_state_id`,`county_name`),
  KEY `FKBE20B20B1FD55A93` (`us_state_id`),
  CONSTRAINT `FKBE20B20B1FD55A93` FOREIGN KEY (`us_state_id`) REFERENCES `us_state` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.us_county: ~10 rows (approximately)
/*!40000 ALTER TABLE `us_county` DISABLE KEYS */;
INSERT INTO `us_county` (`id`, `county_name`, `us_state_id`) VALUES
	(1, 'Accomack', 1),
	(2, 'Albemarle', 1),
	(3, 'Alleghany', 1),
	(4, 'Amelia', 1),
	(5, 'Amherst', 1),
	(6, 'Baltimore', 2),
	(7, 'Baltimore City', 2),
	(8, 'Calvert', 2),
	(9, 'Caroline', 2),
	(10, 'Dorchester', 2);
/*!40000 ALTER TABLE `us_county` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.us_state
DROP TABLE IF EXISTS `us_state`;
CREATE TABLE IF NOT EXISTS `us_state` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `state_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `state_name` (`state_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.us_state: ~2 rows (approximately)
/*!40000 ALTER TABLE `us_state` DISABLE KEYS */;
INSERT INTO `us_state` (`id`, `state_name`) VALUES
	(2, 'Maryland'),
	(1, 'Virginia');
/*!40000 ALTER TABLE `us_state` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.wire_transfer_detail
DROP TABLE IF EXISTS `wire_transfer_detail`;
CREATE TABLE IF NOT EXISTS `wire_transfer_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` decimal(30,0) NOT NULL,
  `payment_status` int(11) DEFAULT NULL,
  `status_description` varchar(255) DEFAULT NULL,
  `tx_date` date NOT NULL,
  `wt_tx_no` varchar(45) NOT NULL,
  `payment_mode_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK5D830B6B9FB6FBBD` (`payment_mode_id`),
  CONSTRAINT `FK5D830B6B9FB6FBBD` FOREIGN KEY (`payment_mode_id`) REFERENCES `payment_mode` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.wire_transfer_detail: ~0 rows (approximately)
/*!40000 ALTER TABLE `wire_transfer_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `wire_transfer_detail` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.workers_compensation_tax_rate
DROP TABLE IF EXISTS `workers_compensation_tax_rate`;
CREATE TABLE IF NOT EXISTS `workers_compensation_tax_rate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_of_entry` datetime DEFAULT NULL,
  `rate` decimal(7,3) DEFAULT NULL,
  `release_date` datetime DEFAULT NULL,
  `wef` datetime DEFAULT NULL,
  `company_worker_compensation_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKD32DA32F89DFC12C` (`company_worker_compensation_id`),
  CONSTRAINT `FKD32DA32F89DFC12C` FOREIGN KEY (`company_worker_compensation_id`) REFERENCES `company_worker_compensation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.workers_compensation_tax_rate: ~0 rows (approximately)
/*!40000 ALTER TABLE `workers_compensation_tax_rate` DISABLE KEYS */;
/*!40000 ALTER TABLE `workers_compensation_tax_rate` ENABLE KEYS */;


-- Dumping structure for table payroll_system_db.workers_compensation_tax_rate_view
DROP TABLE IF EXISTS `workers_compensation_tax_rate_view`;
CREATE TABLE IF NOT EXISTS `workers_compensation_tax_rate_view` (
  `code` varchar(45) NOT NULL,
  `id` bigint(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  `rate` decimal(7,3) NOT NULL,
  `state_name` varchar(45) NOT NULL,
  PRIMARY KEY (`code`,`id`,`description`,`rate`,`state_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table payroll_system_db.workers_compensation_tax_rate_view: ~0 rows (approximately)
/*!40000 ALTER TABLE `workers_compensation_tax_rate_view` DISABLE KEYS */;
/*!40000 ALTER TABLE `workers_compensation_tax_rate_view` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
