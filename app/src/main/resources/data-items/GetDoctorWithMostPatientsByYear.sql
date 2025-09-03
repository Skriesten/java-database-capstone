CREATE PROCEDURE GetDoctorWithMostPatientsByYear()
BEGIN
    select d.name, year(a.appointment_time) as Year, count(a.doctor_id)  as "Total per Year"
    from doctor d join appointment a
    where d.id = a.doctor_id
    group by d.name,year(a.appointment_time)
    order by sum(a.doctor_id) desc
    limit 1;
end;