USE cms
CREATE PROCEDURE GetDoctorWithMostPatientsByMonth()
BEGIN
    select d.name, year(a.appointment_time), month(a.appointment_time), count(a.doctor_id)
    from doctor d join appointment a
    where d.id = a.doctor_id
    group by d.name,year(a.appointment_time), month(a.appointment_time)
    order by sum(a.doctor_id) desc
    limit 1;
END;