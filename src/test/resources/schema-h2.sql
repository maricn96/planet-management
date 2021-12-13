CREATE TABLE `planet` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	`distance_from_sun` INT(11) NOT NULL,
	`mass` INT(11) NOT NULL,
	`surface_area` INT(11) NOT NULL,
	`average_surface_temperature` INT(11)
);

CREATE TABLE `satellite` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`mass` INT(11) NOT NULL,
	`name` VARCHAR(255) NOT NULL,
	`natural_satellite` BOOLEAN NULL,
	`surface_area` INT(11) NOT NULL,
	`planet_id` INT(11) NOT NULL
);

ALTER TABLE satellite ADD CONSTRAINT fk98o7nt81aoc48jo46u9j3477m FOREIGN KEY (planet_id) REFERENCES planet(id);

