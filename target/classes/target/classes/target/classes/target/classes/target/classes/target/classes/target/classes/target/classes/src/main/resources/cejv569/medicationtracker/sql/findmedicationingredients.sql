select ingredients.id, ingredients.common_name, ingredients.scientific_name, ingredients.medicinal, medication.id,
medication_ingredients.medication_id, medication_ingredients.ingredients_id
from 
medication inner join medication_ingredients
on (medication_ingredients.medication_id = medication.id)
inner join ingredients 
on (ingredients.id = medication_ingredients.ingredient_id)
where medication.id = ?;