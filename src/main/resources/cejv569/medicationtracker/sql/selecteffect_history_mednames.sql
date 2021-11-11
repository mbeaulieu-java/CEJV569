select 
effect_history.id, effect_history.user_med_id, effect_history.effect_date,
user_medication.id, user_medication.medication_id, user_medication.user_id,
medication.id, medication.name from effect_history inner join user_medication
on (user_medication.id = effect_history.user_med_id) 
inner join medication 
on (medication.id = user_medication.medication_id)
where user_medication.user_id = ?
order by effect_history.effect_date;
