select medication_ingredients.id as id, medication_ingredients.medication_id as medication_id, medication_ingredients.ingredient_id as ingredient_id,
ingredients.name as name from medication_ingredients join ingredients on ingredients.id = medication_ingredients.ingredient_id order by medication_id, name asc;  
