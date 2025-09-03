-- The following iis a sample of a Store Procedure

Create a procedure with IF Condition
DELIMITER $$

CREATE PROCEDURE CheckGPA(IN student_id INT, OUT status VARCHAR(20))
BEGIN
    DECLARE gpa_value DECIMAL(3,2);
SELECT GPA INTO gpa_value FROM Students WHERE StudentID = student_id;

IF gpa_value >= 3.5 THEN
        SET status = 'Excellent';
    ELSEIF gpa_value >= 2.0 THEN
        SET status = 'Good';
ELSE
        SET status = 'Needs Improvement';
END IF;
END $$

DELIMITER ;
