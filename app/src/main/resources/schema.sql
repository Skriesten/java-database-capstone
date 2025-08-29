-- Switch to the cms database
USE cms;

-- Create doctor table
CREATE TABLE doctor (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        email VARCHAR(255) NOT NULL UNIQUE,
                        name VARCHAR(100) NOT NULL,
                        password VARCHAR(100) NOT NULL,
                        phone VARCHAR(20) NOT NULL,
                        role VARCHAR(35) NOT NULL,
                        specialty VARCHAR(100),
                        date_hired DATE NOT NULL,
                        active BOOLEAN,
                        salary DOUBLE,
                        medical_license_no VARCHAR(100) NOT NULL ,
                        clinic_address VARCHAR(200) NOT NULL
);


-- Create doctor_available_times table
CREATE TABLE doctor_available_times (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        doctor_id INT NOT NULL,
                                        available_times VARCHAR(50),
                                        FOREIGN KEY (doctor_id) REFERENCES doctor(id)
);

-- Create patient table
CREATE TABLE patient (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         email VARCHAR(255) NOT NULL UNIQUE,
                        user_name VARCHAR(50),
                         password VARCHAR(100) NOT NULL,
                         phone VARCHAR(20),
                        address VARCHAR(255) NOT NULL ,
                        role VARCHAR(35),
                        date_of_birth DATE NOT NULL,
                        date_registered DATETIME NOT NULL,
                        gender VARCHAR(10) NOT NULL ,
                        emergency_contact VARCHAR(100),
                        active BOOLEAN NOT NULL
);

-- Create appointment table
CREATE TABLE appointment (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             appointment_time DATETIME NOT NULL,
                             doctor_id INT NOT NULL,
                             patient_id INT NOT NULL,
                             status INT NOT NULL,
                            reason_for_visit VARCHAR(200),
                             FOREIGN KEY (doctor_id) REFERENCES doctor(id),
                             FOREIGN KEY (patient_id) REFERENCES patient(id)
);

-- Create admin table
CREATE TABLE admin (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(200) NOT NULL ,
                       username VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(100) NOT NULL,
                        role VARCHAR(35) NOT NULL ,
                       email VARCHAR(255) NOT NULL UNIQUE,
                        date_hired DATETIME NOT NULL

);

-- Create patient_record table
CREATE TABLE patient_record(
                        id INT AUTO_INCREMENT PRIMARY KEY ,
                        patient_id INT,
                        doctor_id INT,
                        date_of_visit DATETIME NOT NULL ,
                        visit_summary TEXT,
                        FOREIGN KEY (doctor_id) REFERENCES doctor(id),
                        FOREIGN KEY (patient_id) REFERENCES patient(id)
);