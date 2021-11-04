update med_history
set 
user_medication_id = ?,
datetime_taken = ?,
number_doses = ?
where med_history.id = ?;