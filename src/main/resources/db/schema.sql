CREATE TABLE student (
    ID INT NOT NULL PRIMARY KEY,
    name VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_thai_520_w2 NOT NULL,
    surname VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_thai_520_w2 NOT NULL,
    birthday DATE NOT NULL,
    yearjoin INT NOT NULL,
    faculty VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_thai_520_w2 NOT NULL,
    major VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_thai_520_w2 NOT NULL,
    gpa DECIMAL(3,2) CHECK (gpa BETWEEN 0.00 AND 4.00),
    status ENUM('active', 'inactive', 'graduated', 'suspended') NOT NULL
);

CREATE TABLE course (
    course_id INT PRIMARY KEY AUTO_INCREMENT,
    course_code VARCHAR(10) NOT NULL,
    course_name VARCHAR(100) NOT NULL,
    credit INT NOT NULL
);

CREATE TABLE semester (
    semester_id INT PRIMARY KEY AUTO_INCREMENT,
    year INT NOT NULL,
    term ENUM('1', '2', 'Summer') NOT NULL
);

CREATE TABLE enrollment (
    enrollment_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    semester_id INT NOT NULL,
    score DECIMAL(5,2),
    grade VARCHAR(2),

    FOREIGN KEY (student_id) REFERENCES student(ID),
    FOREIGN KEY (course_id) REFERENCES course(course_id),
    FOREIGN KEY (semester_id) REFERENCES semester(semester_id)
);
