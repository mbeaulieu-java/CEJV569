select 
medication.id, medication.name, user_medication.medication_id, user_medication.purchase_date,
med_history.id, med_history.user_medication_id,med_history.datetime_taken,
med_history.number_doses
from med_history inner join user_medication
on (user_medication.id = med_history.user_medication_id)
inner join medication 
on (medication.id = user_medication.medication_id)
order by user_medication.purchase_date;


