update user_medication
set 
number_units = ?,
purchase_date = ?,
expiry_date = ?,
alert = ?,
currently_taking = ?,
recurrent = ?,
max_daily_dose = ?
where user_id = ? and
medication_id = ?;