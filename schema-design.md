## MySQL Database Design

### Tables

#### Patients
<table>
<tr>
    <th> Column name</th>
<th>Data type</th>
<th>Primary key</th>
<th>Foreign key</th>
<th>Constraints</th>
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
    <td>email</td>
 <td>String</td>
 <td></td>
 <td></td>
 <td>NOT NULL</td>
</tr>
<tr>
    <td>dob</td>
 <td>Date</td>
 <td></td>
 <td></td>
 <td>NOT NULL</td>
</tr>
<tr>
    <td>date_registered</td>
 <td>Date</td>
 <td></td>
 <td></td>
 <td>NOT NULL</td>
</tr>
<tr>
    <td>sex</td>
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
    <td>name</td>
 <td>String</td>
 <td></td>
 <td></td>
 <td></td>
</tr>
<tr>
    <td>username</td>
 <td>String</td>
 <td></td>
 <td></td>
 <td></td>
</tr>
<tr>
    <td>email</td>
 <td>String</td>
 <td></td>
 <td></td>
 <td>NOT NULL</td>
</tr>
<tr>
    <td>dob</td>
 <td>Date</td>
 <td></td>
 <td></td>
 <td>NOT NULL</td>
</tr>
<tr>
    <td>date_registered</td>
 <td>Date</td>
 <td></td>
 <td></td>
 <td>NOT NULL</td>
</tr>
<tr>
    <td>sex</td>
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
    <td>name</td>
 <td>String</td>
 <td></td>
 <td></td>
 <td></td>
</tr>
<tr>
    <td>username</td>
 <td>String</td>
 <td></td>
 <td></td>
 <td></td>
</tr>
<tr>
    <td>email</td>
 <td>String</td>
 <td></td>
 <td></td>
 <td>NOT NULL</td>
</tr>
<tr>
    <td>dob</td>
 <td>Date</td>
 <td></td>
 <td></td>
 <td>NOT NULL</td>
</tr>
<tr>
    <td>date_registered</td>
 <td>Date</td>
 <td></td>
 <td></td>
 <td>NOT NULL</td>
</tr>
<tr>
    <td>sex</td>
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
    <td>name</td>
 <td>String</td>
 <td></td>
 <td></td>
 <td></td>
</tr>
<tr>
    <td>username</td>
 <td>String</td>
 <td></td>
 <td></td>
 <td></td>
</tr>
<tr>
    <td>email</td>
 <td>String</td>
 <td></td>
 <td></td>
 <td>NOT NULL</td>
</tr>
<tr>
    <td>dob</td>
 <td>Date</td>
 <td></td>
 <td></td>
 <td>NOT NULL</td>
</tr>
<tr>
    <td>date_registered</td>
 <td>Date</td>
 <td></td>
 <td></td>
 <td>NOT NULL</td>
</tr>
<tr>
    <td>sex</td>
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
</table>






