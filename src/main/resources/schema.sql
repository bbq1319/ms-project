CREATE TABLE IF NOT EXISTS `account` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
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

CREATE TABLE IF NOT EXISTS `menu_group` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(50) NOT NULL,
    `priority` integer NOT NULL,
    `show_flag` boolean NOT NULL DEFAULT FALSE,
    `created_date` TIMESTAMP NOT NULL,
    `created_by` varchar(300) NOT NULL,
    `modified_date` TIMESTAMP NOT NULL,
    `modified_by` varchar(300) NOT NULL
);

CREATE TABLE IF NOT EXISTS `menu` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(50) NOT NULL,
    `price` integer NOT NULL,
    `menu_group_id` bigint NOT NULL,
    `show_flag` boolean NOT NULL DEFAULT FALSE,
    `created_date` TIMESTAMP NOT NULL,
    `created_by` varchar(300) NOT NULL,
    `modified_date` TIMESTAMP NOT NULL,
    `modified_by` varchar(300) NOT NULL
);

CREATE TABLE IF NOT EXISTS `option_group` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(50) NOT NULL,
    `created_date` TIMESTAMP NOT NULL,
    `created_by` varchar(300) NOT NULL,
    `modified_date` TIMESTAMP NOT NULL,
    `modified_by` varchar(300) NOT NULL
);

CREATE TABLE IF NOT EXISTS `option` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(50) NOT NULL,
    `option_group_id` bigint NOT NULL,
    `price` integer NOT NULL,
    `created_date` TIMESTAMP NOT NULL,
    `created_by` varchar(300) NOT NULL,
    `modified_date` TIMESTAMP NOT NULL,
    `modified_by` varchar(300) NOT NULL
);

CREATE TABLE IF NOT EXISTS `menu_option_group` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `menu_id` bigint NOT NULL,
    `option_group_id` bigint NOT NULL,
    `priority` integer NOT NULL,
    `created_date` TIMESTAMP NOT NULL,
    `created_by` varchar(300) NOT NULL
);