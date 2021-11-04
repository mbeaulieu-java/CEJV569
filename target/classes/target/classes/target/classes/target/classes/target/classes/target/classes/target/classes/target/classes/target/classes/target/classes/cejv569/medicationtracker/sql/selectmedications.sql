select 
medication.id, medication.format_id, medication.measurement_id,medication.brand_name,
medication.generic_name,medication.ingredients_id, 
formats.label, 
measurement_units.unit_name
from medication
inner join formats 
on (formats.id = medication.format_id)
inner join medication as medication_measurement
on (medication_measurement.format_id = formats.id)
inner join measurement_units 
on (measurement_units.id = medication_measurement.measurement_id);



