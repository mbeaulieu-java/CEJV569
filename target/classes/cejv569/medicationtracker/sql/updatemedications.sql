update medication 
set format_id = ?,
measurement_id = ?,
brand_name = ?,
generic_name = ?,
ingredients_id = ?
where medication.id = ?;