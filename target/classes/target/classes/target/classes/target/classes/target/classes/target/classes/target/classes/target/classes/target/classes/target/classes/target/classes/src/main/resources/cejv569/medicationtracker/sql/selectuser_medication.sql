select 
user_medication.id, user_medication.medication_id, user_medication.number_units, 
user_medication.purchase_date, user_medication.expiry_date, user_medication.alert, 
user_medication.currently_taking, user_medication.recurrent, user_medication.max_daily_dose, 
user_medication.user_id,
medication.id, medication.brand_name, medication.generic_name,medication.measurement_id,
measurement_units.id, measurement_units.unit_name
from user_medication inner join medication 
on (medication.id = user_medication.medication_id)
inner join measurement_units 
on (measurement_units.id = medication.measurement_id)
where user_medication.user_id = ?;

