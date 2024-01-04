CREATE TABLE IF NOT EXISTS `account` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `email` varchar(300) NOT NULL,
    `password` varchar(60) NOT NULL,
    `name` varchar(30) NOT NULL,
    `role` integer NOT NULL,
    `use_flag` boolean NOT NULL DEFAULT TRUE,
    `created_date` TIMESTAMP NOT NULL,
    `created_by` varchar(300) NOT NULL,
    `modified_date` TIMESTAMP NOT NULL,
    `modified_by` varchar(300) NOT NULL
);