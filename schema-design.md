## MySQL Database Design

### Tables

### Patients
<table>
    <tr>
            <th> Column name</th>
            <th>Data type</th>
            <th>Primary key</th>
            <th>Foreign key</th>
            <th>Constraints</th>
    </tr>
    <tr>
            <td>id</td>
             <td>Long</td>
             <td>YES</td>
             <td></td>
             <td>NOT NULL, AUTO-GENERATE</td>
    </tr>
    <tr>
            <td>name</td>
             <td>String</td>
             <td></td>
             <td></td>
             <td>NOT NULL</td>
    </tr>
    <tr>
            <td>username</td>
             <td>String</td>
             <td></td>
             <td></td>
             <td>NOT NULL</td>
    </tr>
    <tr>
            <td>password</td>
             <td>String</td>
             <td></td>
             <td></td>
             <td>NOT NULL, hide in API response @JsonIgnore</td>
    </tr>
    <tr>
            <td>email</td>
             <td>String</td>
             <td></td>
             <td></td>
             <td>NOT NULL, UNIQUE, @Email</td>
    </tr>
 <tr>
            <td>phone</td>
             <td>String</td>
             <td></td>
             <td></td>
             <td>NOT NULL</td>
    </tr>
    <tr>
            <td>role</td>
             <td>String</td>
             <td></td>
             <td></td>
             <td>NOT NULL</td>
    </tr>
    <tr>
            <td>date_of_birth</td>
             <td>Date</td>
             <td></td>
             <td></td>
             <td>NOT NULL, date <= today</td>
    </tr>
    <tr>
            <td>date_registered</td>
             <td>Date</td>
             <td></td>
             <td></td>
             <td>NOT NULL</td>
    </tr>
    <tr>
            <td>gender</td>
             <td>String</td>
             <td></td>
             <td></td>
             <td>MALE/FEMALE</td>
    </tr>
    <tr>
                <td>address</td>
                 <td>String</td>
                 <td></td>
                 <td></td>
                 <td></td>
    </tr>
<tr>
        <td>emergency_contact</td>
         <td>String</td>
         <td></td>
         <td></td>
         <td>NOT NULL, size <= 200</td>
</tr>
<tr>
        <td>active_yn</td>
         <td>Boolean</td>
         <td></td>
         <td></td>
         <td>NOT NULL, True = YES/False = NO</td>
</tr>
</table>
        

### Doctors
<table>
<tr>
        <th> Column name</th>
        <th>Data type</th>
        <th>Primary key</th>
        <th>Foreign key</th>
        <th>Constraints</th>
</tr>
<tr>
        <td>id</td>
         <td>Long</td>
         <td>YES</td>
         <td></td>
         <td>NOT NULL, AUTO-GENERATE</td>
</tr>
<tr>
        <td>name</td>
         <td>String</td>
         <td></td>
         <td></td>
         <td>NOT NULL</td>
</tr>
<tr>
        <td>username</td>
         <td>String</td>
         <td></td>
         <td></td>
         <td>NOT NULL</td>
</tr>
<tr>
        <td>password</td>
         <td>String</td>
         <td></td>
         <td></td>
         <td>NOT NULL, hide in API response @JsonIgnore)</td>
</tr>
<tr>
        <td>role</td>
         <td>String</td>
         <td></td>
         <td></td>
         <td>NOT NULL</td>
</tr>
<tr>
        <td>email</td>
         <td>String</td>
         <td></td>
         <td></td>
         <td>NOT NULL, UNIQUE, @Email</td>
</tr>
<tr>
        <td>phone</td>
         <td>String</td>
         <td></td>
         <td></td>
         <td>NOT NULL</td>
</tr>
<tr>
        <td>date_hired</td>
         <td>Date</td>
         <td></td>
         <td></td>
         <td>NOT NULL, date_hired <= today</td>
</tr>
<tr>
        <td>specialty</td>
         <td>Long</td>
         <td></td>
         <td>YES</td>
         <td>NOT NULL</td>
</tr>
<tr>
        <td>active_yn</td>
         <td>Boolean</td>
         <td></td>
         <td></td>
         <td>NOT NULL</td>
</tr>
<tr>
        <td>salary</td>
         <td>Double</td>
         <td></td>
         <td></td>
         <td></td>
</tr>
<tr>
        <td>license_no</td>
         <td>String</td>
         <td></td>
         <td></td>
         <td>NOT NULL</td>
</tr>
<tr>
        <td>clinic_address</td>
         <td>String</td>
         <td></td>
         <td></td>
         <td>NOT NULL, size <= 200</td>
</tr>
</table>


### Appointments
<table>
<tr>
        <th> Column name</th>
        <th>Data type</th>
        <th>Primary key</th>
        <th>Foreign key</th>
        <th>Constraints</th>
</tr>
<tr>
        <td>id</td>
         <td>Long</td>
         <td>YES</td>
         <td></td>
         <td>NOT NULL, AUTO-GENERATE</td>
</tr>
<tr>
        <td>patient_id</td>
         <td>Long</td>
         <td>NO</td>
         <td>YES</td>
         <td>NOT NULL</td>
</tr>
<tr>
        <td>doctor_id</td>
         <td>Long</td>
         <td>NO</td>
         <td>YES</td>
         <td>NOT NULL</td>
</tr>
<tr>
        <td>appt_date</td>
         <td>Date</td>
         <td></td>
         <td></td>
         <td>date >= today</td>
</tr>
<tr>
        <td>appt_time_start</td>
         <td>Time</td>
         <td></td>
         <td></td>
         <td>time >= now</td>
</tr>
<tr>
        <td>appt_time_end</td>
         <td>Time</td>
         <td></td>
         <td></td>
         <td>time > appt_time_start</td>
</tr>
<tr>
        <td>reason_for_visit</td>
         <td>String</td>
         <td></td>
         <td></td>
         <td>NOT NULL</td>
</tr>
</table>


### Admin
<table>
<tr>
        <th> Column name</th>
        <th>Data type</th>
        <th>Primary key</th>
        <th>Foreign key</th>
        <th>Constraints</th>
</tr>
<tr>
        <td>id</td>
         <td>Long</td>
         <td>YES</td>
         <td></td>
         <td>NOT NULL, AUTO-GENERATE</td>
</tr>
<tr>
        <td>name</td>
         <td>String</td>
         <td></td>
         <td></td>
         <td>NOT NULL</td>
</tr>
<tr>
        <td>username</td>
         <td>String</td>
         <td></td>
         <td></td>
         <td>NOT NULL</td>
</tr>
<tr>
        <td>password</td>
         <td>String</td>
         <td></td>
         <td></td>
         <td>NOT NULL</td>
</tr>
<tr>
        <td>role</td>
         <td>String</td>
         <td></td>
         <td></td>
         <td>NOT NULL</td>
</tr>
<tr>
        <td>email</td>
         <td>String</td>
         <td></td>
         <td></td>
         <td>NOT NULL, UNIQUE</td>
</tr>
<tr>
        <td>date_hired</td>
         <td>Date</td>
         <td></td>
         <td></td>
         <td>NOT NULL, date_hired <= today</td>
</tr>
</table>

### Patient_Record
//  This is a detail table to record each visit to the doctor.  //
<table>
    <tr>
            <th> Column name</th>
            <th>Data type</th>
            <th>Primary key</th>
            <th>Foreign key</th>
            <th>Constraints</th>
    </tr>
    <tr>
            <td>id</td>
             <td>Long</td>
             <td>YES</td>
             <td></td>
             <td>NOT NULL, AUTO-GENERATE</td>
    </tr>
    <tr>
            <td>patient_id</td>
             <td>Long</td>
             <td></td>
             <td>YES</td>
             <td>NOT NULL</td>
    </tr>
    <tr>
            <td>doctor_id</td>
             <td>Long</td>
             <td></td>
             <td>YES</td>
             <td>NOT NULL</td>
    </tr>
    <tr>
            <td>date_of_visit</td>
             <td>Date</td>
             <td></td>
             <td>/td>
             <td>NOT NULL, default = now</td>
    </tr>
    <tr>
            <td>visit_summary</td>
             <td>String</td>
             <td></td>
             <td></td>
             <td>NOT NULL, min >= 3 & max <= 1000</td>
    </tr>
</table>


## MongoDB Database Design


### Prescriptions
{<br>
<pre>"prescription_id": "01937",<br>
"patient_name": "John Smith",<br>
"doctor_name":"Matthew Healer",<br>
"start_date":"9/1/2025",<br>
"end_date":"3/1/2026",<br>
"pharmacy_name":"Walgreen",<br>
"pharmacy_license_no":"01443"<br>
"pharmacy_phone_no":"840-443-9875"<br>
"medication":{<br>
        "generic_name": "Gabapentin",<br>
        "brand_name": "Neurontin",<br>
        "strength":"600mg",<br>
        "dosage":"1 tablet every 12 hours",<br>
        "dispense_amount":"90 tablets",<br>
        "refills":"3"<br>
        }<br></pre> 
}<br>


