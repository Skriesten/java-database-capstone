USE cms;
CREATE PROCEDURE GetDailyAppointmentReportByDoctor()
BEGIN
    SELECT d.name, a.appointment_time, a.reason_for_visit
    FROM appointment a, doctor d
    where a.doctor_id = d.id
    group by d.name, a.appointment_time, a.reason_for_visit;
end;