-- --------------------------------------------------------
-- Host:                         192.168.1.5
-- Server version:               5.1.59-community - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2013-02-01 15:59:11
-- --------------------------------------------------------

-- Dumping data for table payroll_system_db.address_type: ~6 rows (approximately)
INSERT INTO `address_type` (`id`, `description`, `type`) VALUES
	(1, NULL, 'Shipping'),
	(2, NULL, 'Business'),
	(3, NULL, 'Legal'),
	(4, NULL, 'Normal'),
	(5, NULL, 'Work'),
	(6, NULL, 'Personal');


-- Dumping data for table payroll_system_db.allowance_type: ~2 rows (approximately)
INSERT INTO `allowance_type` (`id`, `description`, `type`) VALUES
	(1, NULL, 'Personal'),
	(2, NULL, 'Dependent');


-- Dumping data for table payroll_system_db.contact_type: ~4 rows (approximately)
INSERT INTO `contact_type` (`id`, `description`, `type`) VALUES
	(1, NULL, 'Authorized'),
	(2, NULL, 'Primary'),
	(3, NULL, 'Billing'),
	(4, NULL, 'Normal');



-- Dumping data for table payroll_system_db.deduction_category: ~2 rows (approximately)
INSERT INTO `deduction_category` (`id`, `category`, `description`) VALUES
	(1, 'Retirement Plans', NULL),
	(2, 'Insurance Premiums', NULL);


-- Dumping data for table payroll_system_db.deduction: ~7 rows (approximately)
INSERT INTO `deduction` (`id`, `deduction_name`, `description`, `deduction_category_id`) VALUES
	(1, '401K', NULL, 1),
	(2, 'SEP IRA', NULL, 1),
	(3, 'SH 401K', NULL, 1),
	(4, 'Dental', NULL, 2),
	(5, 'Medical', NULL, 2),
	(6, 'Miscellaneous', NULL, 2),
	(7, 'Vision', NULL, 2);

-- Dumping data for table payroll_system_db.earning_category: ~3 rows (approximately)
INSERT INTO `earning_category` (`id`, `category`, `description`) VALUES
	(1, 'Default Earnings', NULL),
	(2, 'Additional Earnings', NULL),
	(3, 'Paid Time Off', NULL);

-- Dumping data for table payroll_system_db.earning: ~11 rows (approximately)
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


-- Dumping data for table payroll_system_db.employee_section: ~4 rows (approximately)
INSERT INTO `employee_section` (`id`, `description`, `section_name`) VALUES
	(1, NULL, 'Personal Info'),
	(2, NULL, 'Employment Info'),
	(3, NULL, 'Security Profile'),
	(4, NULL, 'View Tax Info');

-- Dumping data for table payroll_system_db.filing_status: ~4 rows (approximately)
INSERT INTO `filing_status` (`id`, `description`, `status`) VALUES
	(1, NULL, 'Single'),
	(2, NULL, 'Married Filing Jointly or Qualified Widow(er)'),
	(3, NULL, 'Head of Household'),
	(4, NULL, 'Married Filing Separately');

-- Dumping data for table payroll_system_db.fund_category: ~3 rows (approximately)
INSERT INTO `fund_category` (`id`, `category_name`, `description`) VALUES
	(1, 'Payroll Fund', NULL),
	(2, 'Tax Fund', NULL),
	(3, 'Employee Payment Fund', NULL);

-- Dumping data for table payroll_system_db.payment_mode: ~3 rows (approximately)
INSERT INTO `payment_mode` (`id`, `description`, `mode`) VALUES
	(1, NULL, 'Check'),
	(2, NULL, 'ACH'),
	(3, NULL, 'Wire Transfer');


-- Dumping data for table payroll_system_db.us_state: ~2 rows (approximately)
INSERT INTO `us_state` (`id`, `state_name`) VALUES
	(2, 'Maryland'),
	(1, 'Virginia');

-- Dumping data for table payroll_system_db.us_city: ~20 rows (approximately)
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

-- Dumping data for table payroll_system_db.us_county: ~10 rows (approximately)
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


-- Dumping data for table payroll_system_db.tax_authority: ~5 rows (approximately)
INSERT INTO `tax_authority` (`id`, `authority_name`, `description`, `email_id`, `fax`, `phone`, `web_address`, `address_id`, `us_county_id`, `us_state_id`) VALUES
	(1, 'Federal (IRS)', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(2, 'Virginia Tax Authority', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
	(3, 'Marylan Tax Athority', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2),
	(4, 'Baltimore Tax Athority', NULL, NULL, NULL, NULL, NULL, NULL, 6, 2),
	(5, 'Calvert Tax Athority', NULL, NULL, NULL, NULL, NULL, NULL, 8, 2);


-- Dumping data for table payroll_system_db.tax_deposit_cycle: ~4 rows (approximately)
INSERT INTO `tax_deposit_cycle` (`id`, `cycle`, `description`, `no_of_days`) VALUES
	(1, 'Next Day', NULL, 1),
	(2, 'Semi-Weekly', NULL, 3),
	(3, 'Monthly', NULL, 15),
	(4, 'Quarterly', NULL, 90);


-- Dumping data for table payroll_system_db.tax_type: ~10 rows (approximately)
INSERT INTO `tax_type` (`id`, `description`, `paid_by_company`, `paid_by_employee`, `tax_name`, `tax_authority_id`) VALUES
	(1, NULL, 0, 1, 'Federal Income Tax', 1),
	(2, NULL, 1, 0, 'Federal Unemployement Tax', 1),
	(3, NULL, 1, 1, 'Medicare', 1),
	(4, NULL, 1, 1, 'Social Security', 1),
	(5, NULL, 0, 1, 'Virginia State Income Tax', 2),
	(6, NULL, 1, 0, 'Virginia State Unemployement Tax', 2),
	(8, NULL, 0, 1, 'Maryland State Income Tax', 3),
	(9, NULL, 1, 0, 'Maryland State Unemployement Tax', 3),
	(10, NULL, 0, 1, 'Baltimore Local Tax', 4),
	(11, NULL, 0, 1, 'Calvert Local Tax', 5);