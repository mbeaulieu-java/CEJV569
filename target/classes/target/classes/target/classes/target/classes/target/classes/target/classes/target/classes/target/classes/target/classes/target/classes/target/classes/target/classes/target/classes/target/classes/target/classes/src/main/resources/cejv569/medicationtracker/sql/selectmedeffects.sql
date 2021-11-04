select effect_history.id, effect_history.user_med_id, effect_history.effect_id,
	   effect_history.severity, effect_history.duration,effect_history.effect_date,
       user_medication.id, user_medication.purchase_date,user_medication.medication_id,user_medication.user_id,
       medication.id, medication.brand_name, medication.generic_name
from effect_history inner join user_medication 
on (user_medication.id = effect_history.user_med_id)
inner join medication 
on (medication.id = user_medication.medication_id)
order by user_medication.purchase_date, medication.id;

       
       
 