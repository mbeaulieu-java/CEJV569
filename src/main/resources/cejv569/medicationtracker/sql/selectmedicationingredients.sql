select medication_ingredients.id as id, medication_ingredients.med_id as med_id, medication_ingredients.ingredient_id as ingredient_id,
ingredients.name as name from medication_ingredients join ingredients on ingredients.id = medication_ingredients.ingredient_id order by med_id, name asc;
