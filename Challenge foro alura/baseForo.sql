CREATE DATABASE forum;

USE forum;

CREATE TABLE author (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE course (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE topic (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    creation_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    status ENUM('active', 'closed') DEFAULT 'active',
    author_id INT,
    course_id INT,
    FOREIGN KEY (author_id) REFERENCES author(id),
    FOREIGN KEY (course_id) REFERENCES course(id)
);
