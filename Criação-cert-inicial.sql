DROP DATABASE certgen;
CREATE DATABASE IF NOT EXISTS certgen;
USE certgen;

-- Remove functions if they exist
DROP FUNCTION IF EXISTS uuid_to_bin;
DROP FUNCTION IF EXISTS bin_to_uuid;

-- Create function to convert UUID string to BINARY(16)
DELIMITER //
CREATE FUNCTION uuid_to_bin(uuid_str CHAR(36)) RETURNS BINARY(16)
    DETERMINISTIC
    RETURN UNHEX(REPLACE(uuid_str,'-',''));
//
DELIMITER ;

-- Create function to convert BINARY(16) to UUID string
DELIMITER //
CREATE FUNCTION bin_to_uuid(bin_uuid BINARY(16)) RETURNS CHAR(36)
    DETERMINISTIC
    RETURN INSERT(INSERT(INSERT(INSERT(HEX(bin_uuid),9,0,'-'),14,0,'-'),19,0,'-'),24,0,'-');
//
DELIMITER ;

-- Remove tables if they exist
DROP TABLE IF EXISTS enrollment;
DROP TABLE IF EXISTS people;
DROP TABLE IF EXISTS classes;
DROP TABLE IF EXISTS courses;

-- Create Courses table
CREATE TABLE courses (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create Classes table
CREATE TABLE classes (
    id BINARY(16) PRIMARY KEY,
    course_id BINARY(16) NOT NULL,
    name VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

-- Create People table
CREATE TABLE people (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create Enrollment table (Many-to-Many relationship between People and Classes)
CREATE TABLE enrollment (
    id BINARY(16) PRIMARY KEY,
    class_id BINARY(16) NOT NULL,
    person_id BINARY(16) NOT NULL,
    enrollment_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- ✅ Added created_at
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (class_id) REFERENCES classes(id) ON DELETE CASCADE,
    FOREIGN KEY (person_id) REFERENCES people(id) ON DELETE CASCADE,
    UNIQUE (class_id, person_id) -- Prevents duplicate enrollments
);

-- Show tables structure
SHOW TABLES;

GRANT ALL PRIVILEGES ON certgen.* TO 'gsanto'@'%';
FLUSH PRIVILEGES;

USE certgen;
SHOW TABLES;
SELECT * FROM courses;

insert into courses (courses.id,courses.created_at,name,description)
values (uuid(), now(), "On Boarding", "Treinamento de On Boarding Segurança da Informação e Privacaidade de Dados - ISO 27001:2022");