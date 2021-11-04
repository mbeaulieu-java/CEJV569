update effect_history
set 
user_med_id = ?,
effect_id = ?,
severity = ?,
duration = ?,
effect_date = ?
where effect_history.id = ?;
